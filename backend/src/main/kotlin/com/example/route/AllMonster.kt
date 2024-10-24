package com.example.route

import com.example.model.ApiResponse
import com.example.repository.IMonsterRepository
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.getAllMonsters() {
    val monsterRepository: IMonsterRepository by inject()

    get("/v1/monsters") {
        try {
            val apiResponse = monsterRepository.getAllMonsters()
            call.respond(message = apiResponse, status = HttpStatusCode.OK)
        } catch (e: Exception) {
            call.respond(
                message = ApiResponse(success = false, message = "Something went wrong!"),
                status = HttpStatusCode.BadRequest
            )
        }
    }
}