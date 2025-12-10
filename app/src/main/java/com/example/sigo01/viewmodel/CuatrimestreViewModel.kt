package com.example.sigo01.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sigo01.data.dao.MateriaDao
import com.example.sigo01.data.entity.Materia
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CuatrimestreViewModel(private val dao: MateriaDao) : ViewModel() {

    private val _materias = MutableStateFlow<List<Materia>>(emptyList())
    val materias: StateFlow<List<Materia>> = _materias

    fun cargarMaterias(cuatriId: Int) {
        viewModelScope.launch {
            _materias.value = dao.obtenerMaterias(cuatriId)
        }
    }
}
