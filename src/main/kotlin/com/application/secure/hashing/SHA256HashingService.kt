package com.application.secure.hashing

import org.apache.commons.codec.binary.Hex
import org.apache.commons.codec.digest.DigestUtils
import java.security.SecureRandom

class SHA256HashingService : HashingService {

   override fun generate(value: String, saltLength: Int): SaltedHash {
      val salt = SecureRandom.getInstance("SHA1PRNG").generateSeed(saltLength)
      val saltAsHex = Hex.encodeHexString(salt)
      val hash = DigestUtils.sha256Hex("$saltAsHex$value")
      return SaltedHash(
         salt = saltAsHex,
         hash = hash
      )
   }

   override fun verify(value: String, saltedHash: SaltedHash): Boolean {
      return DigestUtils.sha256Hex(saltedHash.salt + value) == saltedHash.hash
   }
}