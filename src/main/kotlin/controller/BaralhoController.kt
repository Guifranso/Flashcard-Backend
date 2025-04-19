package com.example.flashlearn.controllers

import com.example.flashlearn.data.models.Baralho
import com.example.flashlearn.data.repositories.BaralhoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BaralhoController(
    private val repository: BaralhoRepository
) {
    fun getBaralhos(usuarioId: String): Flow<List<Baralho>> {
        return repository.getBaralhos(usuarioId)
    }

    fun getBaralho(id: String): Flow<Baralho?> {
        return repository.getBaralho(id)
    }

    suspend fun criarBaralho(nome: String, descricao: String, usuarioId: String): Result<Baralho> {
        return try {
            val baralho = Baralho(
                id = java.util.UUID.randomUUID().toString(),
                nome = nome,
                descricao = descricao,
                usuarioId = usuarioId
            )
            repository.criarBaralho(baralho)
            Result.success(baralho)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun atualizarBaralho(baralho: Baralho): Result<Baralho> {
        return try {
            val baralhoAtualizado = baralho.copy(
                ultimaModificacao = System.currentTimeMillis()
            )
            repository.atualizarBaralho(baralhoAtualizado)
            Result.success(baralhoAtualizado)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun deletarBaralho(id: String): Result<Unit> {
        return try {
            repository.deletarBaralho(id)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun sincronizarBaralhos(usuarioId: String): Result<Unit> {
        return try {
            repository.sincronizarBaralhos(usuarioId)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
} 