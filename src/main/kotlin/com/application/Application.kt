package com.application

import com.application.plugins.*
import io.ktor.server.application.*

fun main(args: Array<String>): Unit =
   io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
   configureKoin()
   configureSecurity()
   configureSerialization()
   configureRouting()
}
