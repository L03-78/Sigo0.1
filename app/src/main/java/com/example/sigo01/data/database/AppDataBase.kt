package com.example.sigo01.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.sigo01.data.dao.*
import com.example.sigo01.data.entity.*

@Database(
    entities = [
        Alumno::class,
        Cuatrimestre::class,
        Materia::class,
        Actividad::class,
        Curso::class,
        Evento::class
    ],
    version = 3, // ← SUBIDO
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun alumnoDao(): AlumnoDao
    abstract fun cuatrimestreDao(): CuatrimestreDao
    abstract fun materiaDao(): MateriaDao
    abstract fun actividadDao(): ActividadDao
    abstract fun cursoDao(): CursoDao
    abstract fun eventoDao(): EventoDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "sigo01_database"
                )
                    // ← NECESARIO PARA EVITAR EL CRASH
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}
