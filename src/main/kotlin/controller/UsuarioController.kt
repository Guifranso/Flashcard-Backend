//package com.example.flashlearn.controllers
//
//import com.example.flashlearn.data.models.Usuario
//import com.example.flashlearn.data.repositories.UsuarioRepository
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.map
//
//class UsuarioController(
//    private val repository: UsuarioRepository
//) {
//    fun getUsuario(id: String): Flow<Usuario?> {
//        return repository.getUsuario(id)
//    }
//
//    suspend fun criarUsuario(nome: String, email: String): Result<Usuario> {
//        return try {
//            val usuario = Usuario(
//                id = java.util.UUID.randomUUID().toString(),
//                nome = nome,
//                email = email
//            )
//            repository.criarUsuario(usuario)
//            Result.success(usuario)
//        } catch (e: Exception) {
//            Result.failure(e)
//        }
//    }
//
//    suspend fun atualizarUsuario(usuario: Usuario): Result<Usuario> {
//        return try {
//            repository.atualizarUsuario(usuario)
//            Result.success(usuario)
//        } catch (e: Exception) {
//            Result.failure(e)
//        }
//    }
//
//    suspend fun atualizarLocalizacao(usuarioId: String, localizacao: String): Result<Usuario> {
//        return try {
//            val usuario = repository.getUsuario(usuarioId).map { it }.value
//            if (usuario != null) {
//                val usuarioAtualizado = usuario.copy(localizacao = localizacao)
//                repository.atualizarUsuario(usuarioAtualizado)
//                Result.success(usuarioAtualizado)
//            } else {
//                Result.failure(Exception("Usuário não encontrado"))
//            }
//        } catch (e: Exception) {
//            Result.failure(e)
//        }
//    }
//}