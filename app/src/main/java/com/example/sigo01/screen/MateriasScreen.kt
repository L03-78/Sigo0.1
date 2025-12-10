package com.example.sigo01.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sigo01.actividades.TarjetaMateria
import com.example.sigo01.data.database.AppDatabase
import com.example.sigo01.viewmodel.MateriaViewModel
import com.example.sigo01.viewmodel.MateriaViewModelFactory
@Composable
fun MateriasScreen(
    cuatrimestreId: Int,
    viewModel: MateriaViewModel
) {
    val materiasState = viewModel.materias.collectAsState()

    LaunchedEffect(cuatrimestreId) {
        viewModel.cargarMateriasPorCuatrimestre(cuatrimestreId)
    }

    LazyColumn {
        items(materiasState.value) { materia ->
            TarjetaMateria(
                nombre = materia.nombre,
                profesor = materia.profesor,

                // UNIDADES DE ADORNO AQUI
                unidades = listOf(
                    "Unidad 1" to "",
                    "Unidad 2" to "",
                    "Unidad 3" to ""
                )
            )
        }
    }
}
