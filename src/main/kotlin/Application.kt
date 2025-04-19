package com.ppm

import Baralho
import com.ppm.repository.BaralhoRepository
import com.ppm.routes.baralhoRoutes
import io.ktor.server.application.*
import io.ktor.server.routing.*
import kotlinx.coroutines.runBlocking
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    val client = KMongo.createClient().coroutine
    val db = client.getDatabase("Flashcard")
    val flashcardRepo = BaralhoRepository(db)

    runBlocking {
        val resultados: List<Baralho> = flashcardRepo.buscarTodos()
        println("Baralhos encontrados na inicialização:")
        resultados.forEach { println(it) }
    }

    routing {
        baralhoRoutes()
    }


    configureSerialization()
    configureHTTP()
    configureRouting()
}
