package com.example.sigo01.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.sigo01.data.entity.Materia
@Dao
interface MateriaDao {

    @Insert
    suspend fun insertarMateria(materia: Materia)

    @Query("SELECT * FROM materias WHERE cuatrimestreId = :id")
    suspend fun obtenerMaterias(id: Int): List<Materia>

    @Query("SELECT COUNT(*) FROM materias")
    suspend fun countMaterias(): Int
}
