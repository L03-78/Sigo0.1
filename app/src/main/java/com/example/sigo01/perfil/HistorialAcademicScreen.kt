package com.example.sigo01.perfil

import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import com.example.sigo01.R
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistorialAcademicoScreen(onBack: () -> Unit = {}) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Historial Académico") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            painter = painterResource(R.drawable.ic_back),
                            contentDescription = "Regresar"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF1DBF9F)
                )
            )
        }
    ) { padding ->

        Column(
            Modifier
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {

            Text("Cuatrimestres", fontSize = 20.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(12.dp))

            repeat(4) { index ->
                CuatrimestreCard(numero = index + 1)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun CuatrimestreCard(numero: Int) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(Color.White)
    ) {

        Column(Modifier.padding(16.dp)) {

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Row {
                    Image(
                        painter = painterResource(id = R.drawable.icon_calendario),
                        contentDescription = null,
                        modifier = Modifier.size(32.dp)
                    )

                    Spacer(Modifier.width(10.dp))

                    // ✔️ Línea corregida sin perder esencia
                    Text(
                        text = "${numero}° cuatrimestre",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Text(
                    "Ago-Dic 2024",
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("Carrera") }
            )
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("Grupo") }
            )
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("Tutor") }
            )
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("Promedio") }
            )
        }
    }
}
