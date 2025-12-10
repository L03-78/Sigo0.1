// app/src/main/java/com/example/sigo01/data/repository/AlumnoRepository.kt
package com.example.sigo01.data.repository

import com.example.sigo01.data.dao.AlumnoDao
import com.example.sigo01.data.entity.Alumno

class AlumnoRepository(private val dao: AlumnoDao) {
    suspend fun recuperarPassword(matricula: String): String? {
        return dao.getPassword(matricula)
    }

    suspend fun login(matricula: String, password: String) =
        dao.login(matricula, password)

    suspend fun getAlumno(matricula: String) =
        dao.getAlumno(matricula)

    // CORRECTO: usar el nombre real del DAO
    suspend fun getAllAlumnos(): List<Alumno> =
        dao.getAllAlumnos()

    // CORRECTO: exponer inserción de un alumno
    suspend fun insertAlumno(alumno: Alumno) =
        dao.insertAlumno(alumno)

    // opcional: insertar lista (si lo quieres)
    suspend fun insertAlumnos(lista: List<Alumno>) {
        lista.forEach { dao.insertAlumno(it) }
    }
}


