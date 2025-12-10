package com.example.sigo01.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.sigo01.data.entity.Cuatrimestre

@Dao
interface CuatrimestreDao {

    @Query("SELECT * FROM cuatrimestres WHERE matriculaAlumno = :matricula")
    suspend fun getCuatrimestres(matricula: String): List<Cuatrimestre>

    @Query("SELECT COUNT(*) FROM cuatrimestres WHERE matriculaAlumno = :matricula")
    suspend fun countByMatricula(matricula: String): Int

    @Insert
    suspend fun insert(cuatri: Cuatrimestre)
}
