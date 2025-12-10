package com.example.sigo01.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sigo01.data.Sesion
import com.example.sigo01.data.repository.AlumnoRepository
import kotlinx.coroutines.launch

class CambiarContraseñaViewModel(private val repo: AlumnoRepository) : ViewModel() {

    fun cambiarPassword(nueva: String) {
        viewModelScope.launch {
            val matricula = Sesion.matricula ?: return@launch
            val alumno = repo.getAlumno(matricula) ?: return@launch

            repo.insertAlumno(
                alumno.copy(password = nueva)
            )
        }
    }
}
