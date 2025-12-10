package com.example.sigo01.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sigo01.data.entity.Alumno
import com.example.sigo01.data.repository.AlumnoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AdminViewModel(private val repo: AlumnoRepository) : ViewModel() {

    private val _alumnos = MutableStateFlow<List<Alumno>>(emptyList())
    val alumnos: StateFlow<List<Alumno>> = _alumnos

    fun cargarAlumnos() {
        viewModelScope.launch {
            _alumnos.value = repo.getAllAlumnos()
        }
    }
}
