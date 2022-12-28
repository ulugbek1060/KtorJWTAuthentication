package com.application.secure.token

data class TokenConfig(
   val issuer: String,
   val audience: String,
   val expiresIn: Long,
   val secret: String
)