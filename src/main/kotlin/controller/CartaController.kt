//package com.example.flashlearn.controllers
//
//import com.example.flashlearn.data.models.Carta
//import com.example.flashlearn.data.repositories.CartaRepository
//import kotlinx.coroutines.flow.Flow
//import java.util.concurrent.TimeUnit
//
//class CartaController(
//    private val repository: CartaRepository
//) {
//    fun getCartas(baralhoId: String): Flow<List<Carta>> {
//        return repository.getCartasByBaralho(baralhoId)
//    }
//
//    fun getCartasParaRevisar(): Flow<List<Carta>> {
//        return repository.getCartasParaRevisar()
//    }
//
//    suspend fun criarCarta(frente: String, verso: String, baralhoId: String): Result<Carta> {
//        return try {
//            val carta = Carta(
//                id = java.util.UUID.randomUUID().toString(),
//                frente = frente,
//                verso = verso,
//                baralhoId = baralhoId,
//                nivelDificuldade = 0,
//                proximaRevisao = System.currentTimeMillis() + TimeUnit.DAYS.toMillis(1)
//            )
//            repository.createCarta(carta)
//            Result.success(carta)
//        } catch (e: Exception) {
//            Result.failure(e)
//        }
//    }
//
//    suspend fun atualizarCarta(carta: Carta): Result<Carta> {
//        return try {
//            repository.createCarta(carta)
//            Result.success(carta)
//        } catch (e: Exception) {
//            Result.failure(e)
//        }
//    }
//
//    suspend fun marcarRevisao(carta: Carta, acertou: Boolean): Result<Carta> {
//        return try {
//            val novoNivel = if (acertou) carta.nivelDificuldade + 1 else 0
//            val proximaRevisao = calcularProximaRevisao(novoNivel)
//
//            val cartaAtualizada = carta.copy(
//                nivelDificuldade = novoNivel,
//                ultimaRevisao = System.currentTimeMillis(),
//                proximaRevisao = proximaRevisao
//            )
//
//            repository.createCarta(cartaAtualizada)
//            Result.success(cartaAtualizada)
//        } catch (e: Exception) {
//            Result.failure(e)
//        }
//    }
//
//    private fun calcularProximaRevisao(nivel: Int): Long {
//        val baseDelay = when (nivel) {
//            0 -> TimeUnit.MINUTES.toMillis(5)    // 5 minutos
//            1 -> TimeUnit.HOURS.toMillis(1)      // 1 hora
//            2 -> TimeUnit.DAYS.toMillis(1)       // 1 dia
//            3 -> TimeUnit.DAYS.toMillis(3)       // 3 dias
//            4 -> TimeUnit.DAYS.toMillis(7)       // 1 semana
//            5 -> TimeUnit.DAYS.toMillis(14)      // 2 semanas
//            else -> TimeUnit.DAYS.toMillis(30)   // 1 mês
//        }
//        return System.currentTimeMillis() + baseDelay
//    }
//
//    suspend fun sincronizarCartas(baralhoId: String): Result<Unit> {
//        return try {
//            repository.refreshCartas(baralhoId)
//            Result.success(Unit)
//        } catch (e: Exception) {
//            Result.failure(e)
//        }
//    }
//}