package com.ppm

import io.ktor.serialization.gson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.bson.types.ObjectId

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        gson {
            registerTypeAdapter(ObjectId::class.java, ObjectIdAdapter())
            setPrettyPrinting()          // se quiser
        }
    }
    routing {
        get("/json/gson") {
                call.respond(mapOf("hello" to "world"))
            }
    }
}
