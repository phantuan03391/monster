package com.example.plugins

import com.example.route.getAllMonsters
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Welcome to Monster App!")
        }
        getAllMonsters()
    }
}
