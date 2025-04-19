//package com.example.flashlearn.data.models
//
//import androidx.room.Entity
//import androidx.room.PrimaryKey
//import androidx.room.ForeignKey
//
//@Entity(
//    tableName = "cartas",
//    foreignKeys = [
//        ForeignKey(
//            entity = Baralho::class,
//            parentColumns = ["id"],
//            childColumns = ["baralhoId"],
//            onDelete = ForeignKey.CASCADE
//        )
//    ]
//)
//data class Carta(
//    @PrimaryKey val id: String,
//    val frente: String,
//    val verso: String,
//    val baralhoId: String,
//    val nivelDificuldade: Int = 0,
//    val ultimaRevisao: Long? = null,
//    val proximaRevisao: Long? = null
//)