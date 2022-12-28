package com.application.secure.token

interface TokenService {
   fun generate(config: TokenConfig, vararg claims: TokenClaim): String
}