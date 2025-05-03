package com.example.flashlearn.controllers

import Baralho
import com.ppm.repository.BaralhoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import org.bson.types.ObjectId

class BaralhoController(
    private val repository: BaralhoRepository
) {
    suspend fun getAllBaralhos(): Flow<Baralho> {
        val lista = repository.buscarTodos()
        return lista.asFlow()
    }

    /*
    suspend fun getBaralhoById(id: ObjectId): Baralho? {
        return repository.buscarPorId(id)
    }

    suspend fun inserirBaralho(baralho: Baralho) {
        repository.inserir(baralho)
    }

    suspend fun atualizarBaralho(baralho: Baralho) {
        repository.atualizar(baralho)
    }

    suspend fun deletarBaralho(id: ObjectId) {
        repository.deletar(id)
    }
    */
}
