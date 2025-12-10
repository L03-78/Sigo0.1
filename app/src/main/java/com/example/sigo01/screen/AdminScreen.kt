package com.example.sigo01.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sigo01.viewmodel.AdminViewModel

@Composable
fun AdminScreen(
    onBack: () -> Unit,
    viewModel: AdminViewModel
) {
    val lista by viewModel.alumnos.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.cargarAlumnos()
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        Text("Panel Administrador", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(16.dp))

        Button(onClick = onBack) {
            Text("Regresar")
        }

        Spacer(Modifier.height(20.dp))

        LazyColumn {
            items(lista) { alumno ->
                Text("Nombre: ${alumno.nombre}", style = MaterialTheme.typography.bodyLarge)
                Text("Matrícula: ${alumno.matricula}")
                Text("Contraseña: ${alumno.password}")
                Divider(Modifier.padding(vertical = 8.dp))
            }
        }
    }
}
