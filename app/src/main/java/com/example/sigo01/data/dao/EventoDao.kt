package com.example.sigo01.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.sigo01.data.entity.Evento

@Dao
interface EventoDao {

    @Query("SELECT * FROM eventos")
    suspend fun getEventos(): List<Evento>

    @Insert
    suspend fun insertEvento(evento: Evento)
}
