package com.example.sigo01.data.entity


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "alumnos")
data class Alumno(
    @PrimaryKey val matricula: String,
    val nombre: String,
    val apellido1: String,
    val apellido2: String?,
    val correoInstitucional: String,
    val correoPersonal: String?,
    val telefono: String?,
    val password: String,
    val sexo: String?,
    val fechaNacimiento: String?,
    val nss: String?,
    val carrera: String?,
    val grupo: String?,
    val tutor: String?
)
