package com.example.sigo01.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sigo01.data.Sesion
import com.example.sigo01.data.entity.Alumno
import com.example.sigo01.data.repository.AlumnoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PerfilViewModel(private val repo: AlumnoRepository) : ViewModel() {

    private val _alumno = MutableStateFlow<Alumno?>(null)
    val alumno: StateFlow<Alumno?> = _alumno

    fun cargarPerfil() {
        viewModelScope.launch {
            val matricula = Sesion.matricula ?: return@launch
            _alumno.value = repo.getAlumno(matricula)
        }
    }

    fun actualizarAlumno(alumnoActualizado: Alumno) {
        viewModelScope.launch {
            repo.insertAlumno(alumnoActualizado)   // Update en Room
            _alumno.value = alumnoActualizado
        }
    }
}

