package com.ppm.database

import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

class MongoClientProvider {
    private val client = KMongo.createClient().coroutine
    val database: CoroutineDatabase = client.getDatabase("Flashcard")
}