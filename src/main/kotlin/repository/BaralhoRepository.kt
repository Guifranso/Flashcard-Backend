package com.ppm.repository

import Baralho
import com.ppm.database.MongoClientProvider
import org.bson.types.ObjectId
import org.koin.java.KoinJavaComponent.get
import org.koin.java.KoinJavaComponent.inject
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class BaralhoRepository(private val db: CoroutineDatabase) {

    private val collection = db.getCollection<Baralho>("Baralho")

    suspend fun inserir(deck: Baralho) = collection.insertOne(deck)

    suspend fun buscarPorId(id: ObjectId): Baralho? = collection.findOneById(id)

    suspend fun buscarPorUsuario(usuarioId: String): List<Baralho> =
        collection.find(Baralho::id_usuario eq usuarioId).toList()

    suspend fun buscarTodos(): List<Baralho> = collection.find().toList()

    suspend fun atualizar(deck: Baralho) = collection.replaceOneById(deck.id, deck)

    suspend fun deletar(id: ObjectId) = collection.deleteOneById(id)
}