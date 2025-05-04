package com.ppm

import Baralho
import com.example.flashlearn.controllers.BaralhoController
import com.ppm.di.appModule
import com.ppm.repository.BaralhoRepository
import com.ppm.routes.baralhoRoutes
import io.ktor.server.application.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.routing.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import org.koin.ktor.ext.get
import org.koin.ktor.plugin.Koin
import org.koin.logger.SLF4JLogger

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {

    install(Koin) {
        SLF4JLogger()
        modules(appModule)
    }


    val flashcardRepo = this.get<BaralhoRepository>()
    val controller = this.get<BaralhoController>()

    runBlocking {
        val resultadosController: Flow<Baralho> = controller.getAllBaralhos()
        val resultados: List<Baralho> = flashcardRepo.buscarTodos()
        println("Baralhos encontrados na inicialização:")
        resultadosController.collect { println(it) }
    }

    routing {
        swaggerUI(path = "swagger", swaggerFile = "openapi/documentation.yaml")
        baralhoRoutes(controller)
    }

    configureSerialization()
    configureHTTP()
    configureRouting()
}