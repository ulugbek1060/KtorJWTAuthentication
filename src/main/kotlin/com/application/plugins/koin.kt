package com.application.plugins

import com.application.id.koinModule
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin


fun Application.configureKoin() {
   install(Koin){
      modules(koinModule)
   }
}