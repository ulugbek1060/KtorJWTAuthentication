package com.application.id

import com.application.Constants
import com.application.data.UserDataSourceImpl
import com.application.domain.UserDataSource
import com.application.secure.hashing.HashingService
import com.application.secure.hashing.SHA256HashingService
import com.application.secure.token.JwtTokenService
import com.application.secure.token.TokenConfig
import com.application.secure.token.TokenService
import io.ktor.server.application.*
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

val koinModule = module {
   single {
      val mongoPassword = System.getenv("MONGO_PASSWORD")
      println(mongoPassword)
      KMongo.createClient(
            connectionString = "mongodb+srv://ulugbek1060:$mongoPassword@cluster0.go3aicg.mongodb.net/${Constants.DATABASE_NAME}?retryWrites=true&w=majority"
         ).coroutine.getDatabase(Constants.DATABASE_NAME)
   }

   single<UserDataSource> {
      UserDataSourceImpl(get())
   }

   single<HashingService> {
      SHA256HashingService()
   }

   single<TokenService> {
      JwtTokenService()
   }

   single { }
}
