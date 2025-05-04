package com.ppm.routes

import Baralho
import com.example.flashlearn.controllers.BaralhoController
import com.ppm.repository.BaralhoRepository
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import kotlinx.coroutines.flow.toList
import org.bson.types.ObjectId
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo


fun Route.baralhoRoutes(controller: BaralhoController) {

    route("/baralhos") {

        /* GET /baralhos – lista todos */
        get {
            runCatching { controller.getAllBaralhos().toList() }
                .onSuccess { call.respond(it) }
                .onFailure {
                    call.respond(
                        HttpStatusCode.InternalServerError,
                        "Erro ao buscar baralhos: ${it.message}"
                    )
                }
        }

        /* GET /baralhos/{id} – busca por ID */
        get("{id}") {
            val idParam = call.parameters["id"]
            val objectId = runCatching { ObjectId(idParam) }.getOrNull()
            if (objectId == null) {
                return@get call.respond(HttpStatusCode.BadRequest, "ID inválido: $idParam")
            }

            runCatching { controller.getBaralhoById(objectId) }
                .onSuccess { baralho ->
                    if (baralho != null) call.respond(baralho)
                    else call.respond(HttpStatusCode.NotFound, "Baralho não encontrado")
                }
                .onFailure {
                    call.respond(
                        HttpStatusCode.InternalServerError,
                        "Erro ao buscar baralho: ${it.message}"
                    )
                }
        }

        /* POST /baralhos – cria um novo */
        post {
            val novo = runCatching { call.receive<Baralho>() }
                .getOrElse {
                    return@post call.respond(
                        HttpStatusCode.BadRequest,
                        "JSON inválido: ${it.message}"
                    )
                }

            runCatching { controller.inserirBaralho(novo) }
                .onSuccess { call.respond(HttpStatusCode.Created, novo) }
                .onFailure {
                    call.respond(
                        HttpStatusCode.InternalServerError,
                        "Erro ao salvar baralho: ${it.message}"
                    )
                }
        }

        /* PUT /baralhos/{id} – atualiza existente */
        put("{id}") {
            val idParam = call.parameters["id"]
            val objectId = runCatching { ObjectId(idParam) }.getOrNull()
            if (objectId == null) {
                return@put call.respond(HttpStatusCode.BadRequest, "ID inválido: $idParam")
            }

            val atualizado = runCatching { call.receive<Baralho>() }
                .getOrElse {
                    return@put call.respond(
                        HttpStatusCode.BadRequest,
                        "JSON inválido: ${it.message}"
                    )
                }
                .copy(id = objectId)  // garante que o ID do body bata com o do path

            runCatching { controller.atualizarBaralho(atualizado) }
                .onSuccess { call.respond(HttpStatusCode.OK, atualizado) }
                .onFailure {
                    call.respond(
                        HttpStatusCode.InternalServerError,
                        "Erro ao atualizar baralho: ${it.message}"
                    )
                }
        }

        /* DELETE /baralhos/{id} – deleta por ID */
        delete("{id}") {
            val idParam = call.parameters["id"]
            val objectId = runCatching { ObjectId(idParam) }.getOrNull()
            if (objectId == null) {
                return@delete call.respond(HttpStatusCode.BadRequest, "ID inválido: $idParam")
            }

            runCatching { controller.deletarBaralho(objectId) }
                .onSuccess { call.respond(HttpStatusCode.NoContent) }
                .onFailure {
                    call.respond(
                        HttpStatusCode.InternalServerError,
                        "Erro ao deletar baralho: ${it.message}"
                    )
                }
        }
    }
}