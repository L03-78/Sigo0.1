package com.example.sigo01.perfil

import androidx.compose.foundation.border
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
import com.example.sigo01.viewmodel.CuatrimestreViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CuatrimestreDetalleScreen(
    tituloCuatri: String,
    cuatriId: Int,
    viewModel: CuatrimestreViewModel,
    onBack: () -> Unit
) {

    val materias by viewModel.materias.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.cargarMaterias(cuatriId)
    }

    Column(Modifier.fillMaxSize()) {

        TopAppBar(
            title = { Text(tituloCuatri, color = Color.White) },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(
                        painterResource(R.drawable.ic_back),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFF00A99D)
            )
        )

        Spacer(Modifier.height(20.dp))

        LazyColumn(Modifier.padding(16.dp)) {
            items(materias) { materia ->
                MateriaCard(materia)
                Spacer(Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun MateriaCard(materia: com.example.sigo01.data.entity.Materia) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {

        Column(Modifier.padding(16.dp)) {

            Row(verticalAlignment = Alignment.CenterVertically) {

                Icon(
                    painterResource(R.drawable.icon_materias),
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.size(40.dp)
                )

                Spacer(Modifier.width(10.dp))

                Column {
                    Text(materia.nombre, fontWeight = FontWeight.Bold)
                    Text(materia.profesor, fontSize = 12.sp, color = Color.Gray)
                    Text("Materia del cuatrimestre", fontSize = 12.sp, color = Color.Gray)
                }
            }

            Spacer(Modifier.height(12.dp))

            CampoInfo("Proyecto")
            CampoInfo("Evaluación")
            CampoInfo("Desempeño")

            Spacer(Modifier.height(10.dp))

            CampoInfo("Unidades Trabajadas")
            CampoInfo("Puntos y Porcentaje")
            CampoInfo("Asistencia")
        }
    }
}

@Composable
fun CampoInfo(titulo: String) {
    Column {
        Text(titulo, fontSize = 12.sp, color = Color.Gray)
        Spacer(Modifier.height(4.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(35.dp)
                .border(1.dp, Color.LightGray, RoundedCornerShape(6.dp))
        )
        Spacer(Modifier.height(8.dp))
    }
}
