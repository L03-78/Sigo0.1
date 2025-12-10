package com.example.sigo01.data.entity



import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "materias")
data class Materia(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val nombre: String,
    val profesor: String,
    val cuatrimestreId: Int
)
