package com.application.plugins

import com.application.domain.UserDataSource
import com.application.routes.getUserInfoRoute
import com.application.routes.signUp
import com.application.secure.hashing.HashingService
import com.application.secure.hashing.SHA256HashingService
import com.application.secure.token.TokenService
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.java.KoinJavaComponent.inject

fun Application.configureRouting() {
   routing {
      val hashingService: HashingService by inject(SHA256HashingService::class.java)
      val tokenService: TokenService by inject(TokenService::class.java)
      val userDataSource: UserDataSource by inject(UserDataSource::class.java)

      signUp(userDataSource, hashingService)
      getUserInfoRoute(userDataSource)
   }
}
