package com.example.sigo01.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sigo01.data.repository.AlumnoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RecuperarViewModel(private val repo: AlumnoRepository) : ViewModel() {

    private val _password = MutableStateFlow<String?>(null)
    val password: StateFlow<String?> = _password

    fun recuperar(matricula: String) {
        viewModelScope.launch {
            val result = repo.recuperarPassword(matricula)
            _password.value = result
        }
    }
}
