package com.example.sigo01.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cuatrimestres")
data class Cuatrimestre(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val matriculaAlumno: String,
    val nombre: String,
    val fechaInicio: String,
    val fechaFin: String,
    val promedio: Double? = null,
    val carrera: String? = null,
    val grupo: String? = null,
    val tutor: String? = null,
    val desempeno: String? = null,
    val progreso: Int? = null
)

