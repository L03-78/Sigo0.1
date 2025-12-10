package com.example.sigo01.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.sigo01.data.entity.Actividad

@Dao
interface ActividadDao {
    @Query("SELECT * FROM actividades")
    suspend fun getAll(): List<Actividad>

    @Insert
    suspend fun insert(act: Actividad)
}
