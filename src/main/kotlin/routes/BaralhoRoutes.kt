package com.ppm.routes

import com.example.flashlearn.controllers.BaralhoController
import com.ppm.repository.BaralhoRepository
import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

fun Route.baralhoRoutes() {
    val client = KMongo.createClient().coroutine
    val db = client.getDatabase("Flashcard")
    val repository = BaralhoRepository(db)
    val controller = BaralhoController(repository)

    route("/baralhos") {
        get {
            try {
                val baralhos = controller.getAllBaralhos()
                call.respond(baralhos)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, "Erro ao buscar os baralhos: ${e.message}")
            }
        }
    }

}