package com.application.routes

import com.application.domain.UserDataSource
import com.application.secure.hashing.HashingService
import io.ktor.server.routing.*

fun Route.signUp(
   userDataSource: UserDataSource,
   hashingService: HashingService
) {

   post("/signup") {

   }

}