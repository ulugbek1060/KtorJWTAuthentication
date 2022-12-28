package com.application.secure.hashing

data class SaltedHash(
   val salt: String,
   val hash: String
)
