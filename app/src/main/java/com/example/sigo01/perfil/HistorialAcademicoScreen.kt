package com.example.sigo01.perfil

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sigo01.R
import com.example.sigo01.data.Sesion
import com.example.sigo01.viewmodel.HistorialViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistorialAcademicoScreen(
    onBack: () -> Unit = {},
    onOpenCuatrimestre: (Int) -> Unit,
    viewModel: HistorialViewModel
) {

    val lista by viewModel.cuatrimestres.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.cargarHistorial(Sesion.matricula ?: "")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Historial Académico", fontSize = 20.sp) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            painterResource(R.drawable.ic_back),
                            contentDescription = "Regresar"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF009688)
                )
            )
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {

            items(lista) { cuatri ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                        .clickable { onOpenCuatrimestre(cuatri.id) },
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {

                    Column(Modifier.padding(16.dp)) {

                        // ⭐ Título
                        Text(
                            text = cuatri.nombre,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "${cuatri.fechaInicio} - ${cuatri.fechaFin}",
                            color = Color.Gray,
                            fontSize = 14.sp
                        )

                        Spacer(Modifier.height(12.dp))

                        // 🚀 Información de cuatrimestre
                        TextoFila("Carrera", cuatri.carrera ?: "")
                        TextoFila("Grupo", cuatri.grupo ?: "")
                        TextoFila("Tutor", cuatri.tutor ?: "")
                        TextoFila("Desempeño", cuatri.desempeno ?: "")
                        TextoFila("Progreso", "${cuatri.progreso ?: 0}%")

                        Spacer(Modifier.height(8.dp))

                        Text(
                            "Promedio: ${cuatri.promedio ?: 0.0}",
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF00796B)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TextoFila(titulo: String, valor: String) {
    Column(Modifier.padding(bottom = 6.dp)) {
        Text(titulo, fontWeight = FontWeight.Bold, fontSize = 14.sp)
        Text(valor, color = Color.DarkGray, fontSize = 14.sp)
    }
}
