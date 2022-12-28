package com.application.plugins

import com.application.secure.token.TokenConfig
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*

fun Application.configureSecurity() {
   val config = getTokenConfig()
   authentication {
      jwt {
         realm = this@configureSecurity.environment.config.property("jwt.realm").getString()
         verifier(
            JWT
               .require(Algorithm.HMAC256(config.secret))
               .withAudience(config.audience)
               .withIssuer(config.issuer)
               .build()
         )
         validate { credential ->
            if (credential.payload.audience.contains(config.audience)) JWTPrincipal(credential.payload) else null
         }
      }
   }

}

fun Application.getTokenConfig(): TokenConfig {
   return TokenConfig(
      issuer = environment.config.property("jwt.issuer").getString(),
      audience = environment.config.property("jwt.audience").getString(),
      expiresIn = 365L * 1000L * 60L * 24L,
      secret = System.getenv("JWT_SECRET")
   )
}

