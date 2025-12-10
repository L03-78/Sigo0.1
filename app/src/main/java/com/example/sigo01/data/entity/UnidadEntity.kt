package com.example.sigo01.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "unidades")
data class UnidadEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val materiaId: Int,
    val titulo: String,
    val descripcion: String
)
