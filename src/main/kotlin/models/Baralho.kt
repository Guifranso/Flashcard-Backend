package com.example.flashlearn.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey

@Entity(
    tableName = "baralhos",
    foreignKeys = [
        ForeignKey(
            entity = Usuario::class,
            parentColumns = ["id"],
            childColumns = ["usuarioId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Baralho(
    @PrimaryKey val id: String,
    val nome: String,
    val descricao: String,
    val usuarioId: String,
    val dataCriacao: Long = System.currentTimeMillis(),
    val ultimaModificacao: Long = System.currentTimeMillis()
) 