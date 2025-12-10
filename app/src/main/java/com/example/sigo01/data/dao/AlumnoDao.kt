package com.example.sigo01.data.dao

import androidx.room.*
import com.example.sigo01.data.entity.Alumno
@Dao
interface AlumnoDao {
    @Query("SELECT password FROM alumnos WHERE matricula = :matricula LIMIT 1")
    suspend fun getPassword(matricula: String): String?

    // ---------------------- LOGIN ----------------------
    @Query("SELECT * FROM alumnos WHERE matricula = :matricula AND password = :password LIMIT 1")
    suspend fun login(matricula: String, password: String): Alumno?

    // ----------------- OBTENER UN SOLO ALUMNO -----------------
    @Query("SELECT * FROM alumnos WHERE matricula = :matricula LIMIT 1")
    suspend fun getAlumno(matricula: String): Alumno?

    // ---------------------- INSERTAR ----------------------
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlumno(alumno: Alumno)

    // ---------------------- ACTUALIZAR ----------------------
    @Update
    suspend fun actualizarAlumno(alumno: Alumno)

    // ---------------------- ADMIN: VER TODOS ----------------------
    @Query("SELECT * FROM alumnos ORDER BY apellido1 ASC")
    suspend fun getAllAlumnos(): List<Alumno>
}
