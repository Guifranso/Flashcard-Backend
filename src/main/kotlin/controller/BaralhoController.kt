package com.example.flashlearn.controllers

import Baralho
import com.ppm.repository.BaralhoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

class BaralhoController(
    private val repository: BaralhoRepository
) {
    suspend fun getAllBaralhos(): Flow<Baralho> {
        val lista = repository.buscarTodos()
        return lista.asFlow()
    }
} 