package com.example.sigo01.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sigo01.data.dao.MateriaDao

class MateriaViewModelFactory(private val dao: MateriaDao) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MateriaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MateriaViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
