package com.example.flashlearn.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios")
data class Usuario(
    @PrimaryKey val id: String,
    val nome: String,
    val email: String,
    val localizacao: String? = null,
    val dataCriacao: Long = System.currentTimeMillis()
) 