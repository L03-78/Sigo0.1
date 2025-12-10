package com.example.sigo01.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sigo01.data.Sesion
import com.example.sigo01.data.repository.AlumnoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val repo: AlumnoRepository) : ViewModel() {

    private val _loginSuccess = MutableStateFlow<Boolean?>(null)
    val loginSuccess: StateFlow<Boolean?> = _loginSuccess

    fun login(matricula: String, password: String) {
        viewModelScope.launch {
            val alumno = repo.login(matricula, password)

            if (alumno != null) {
                // Guardamos la sesión
                Sesion.matricula = alumno.matricula
                _loginSuccess.value = true
            } else {
                _loginSuccess.value = false
            }
        }
    }
}

