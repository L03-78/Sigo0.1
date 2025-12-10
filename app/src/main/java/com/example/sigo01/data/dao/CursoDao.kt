package com.example.sigo01.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.sigo01.data.entity.Curso

@Dao
interface CursoDao {

    @Query("SELECT * FROM cursos")
    suspend fun getCursos(): List<Curso>

    @Insert
    suspend fun insertCurso(curso: Curso)
}
