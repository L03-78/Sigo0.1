package com.example.sigo01.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sigo01.data.dao.CuatrimestreDao
import com.example.sigo01.data.entity.Cuatrimestre
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HistorialViewModel(private val cuatriDao: CuatrimestreDao) : ViewModel() {

    private val _cuatrimestres = MutableStateFlow<List<Cuatrimestre>>(emptyList())
    val cuatrimestres: StateFlow<List<Cuatrimestre>> = _cuatrimestres

    fun cargarHistorial(matricula: String) {
        viewModelScope.launch {
            _cuatrimestres.value = cuatriDao.getCuatrimestres(matricula)
        }
    }
}
