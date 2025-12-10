package com.example.sigo01.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "eventos")
data class Evento(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val titulo: String,
    val lugar: String,
    val imagen: Int
)
