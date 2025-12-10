package com.example.sigo01.data.repository

import com.example.sigo01.data.dao.MateriaDao
import com.example.sigo01.data.entity.Materia

class MateriaRepository(private val dao: MateriaDao) {

    suspend fun insertarMateria(materia: Materia) {
        dao.insertarMateria(materia)
    }

    suspend fun obtenerMaterias(id: Int): List<Materia> =
        dao.obtenerMaterias(id)

    suspend fun countMaterias(): Int =
        dao.countMaterias()
}
