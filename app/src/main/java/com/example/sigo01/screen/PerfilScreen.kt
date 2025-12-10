package com.example.sigo01.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sigo01.R
import com.example.sigo01.viewmodel.PerfilViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilScreen(
    onBack: () -> Unit = {},
    onAbrirCambiarContrasena: () -> Unit = {},
    viewModel: PerfilViewModel
) {
    val alumno by viewModel.alumno.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.cargarPerfil()
    }

    alumno?.let { data ->

        var nombre by remember(data) { mutableStateOf(data.nombre) }
        var apellido1 by remember(data) { mutableStateOf(data.apellido1) }
        var apellido2 by remember(data) { mutableStateOf(data.apellido2 ?: "") }
        var telefono by remember(data) { mutableStateOf(data.telefono ?: "") }
        var correoPersonal by remember(data) { mutableStateOf(data.correoPersonal ?: "") }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Mi Perfil", fontWeight = FontWeight.Bold) },
                    navigationIcon = {
                        IconButton(onClick = onBack) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Regresar")
                        }
                    }
                )
            }
        ) { padding ->

            Column(
                Modifier
                    .padding(padding)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(Modifier.height(10.dp))

                // FOTO DE PERFIL DECORATIVA
                Box(
                    contentAlignment = Alignment.BottomEnd,
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFE0E0E0))
                ) {
                    Icon(
                        Icons.Default.Person,
                        contentDescription = null,
                        modifier = Modifier.size(80.dp),
                        tint = Color(0xFF757575)
                    )

                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary)
                            .padding(6.dp)
                            .clickable { }
                    ) {
                        Icon(
                            Icons.Default.Edit,
                            contentDescription = "Editar foto",
                            tint = Color.White
                        )
                    }
                }

                Spacer(Modifier.height(20.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFF5F5F5)
                    ),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(Modifier.padding(16.dp)) {

                        Text(
                            "Información Personal",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(Modifier.height(12.dp))

                        OutlinedTextField(
                            value = nombre,
                            onValueChange = { nombre = it },
                            label = { Text("Nombre") },
                            modifier = Modifier.fillMaxWidth()
                        )

                        OutlinedTextField(
                            value = apellido1,
                            onValueChange = { apellido1 = it },
                            label = { Text("Primer apellido") },
                            modifier = Modifier.fillMaxWidth()
                        )

                        OutlinedTextField(
                            value = apellido2,
                            onValueChange = { apellido2 = it },
                            label = { Text("Segundo apellido") },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }

                Spacer(Modifier.height(20.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFF5F5F5)
                    ),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(Modifier.padding(16.dp)) {

                        Text(
                            "Contacto",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(Modifier.height(12.dp))

                        OutlinedTextField(
                            value = telefono,
                            onValueChange = { telefono = it },
                            label = { Text("Teléfono") },
                            modifier = Modifier.fillMaxWidth()
                        )

                        OutlinedTextField(
                            value = correoPersonal,
                            onValueChange = { correoPersonal = it },
                            label = { Text("Correo personal") },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }

                Spacer(Modifier.height(25.dp))

                Button(
                    onClick = {
                        viewModel.actualizarAlumno(
                            data.copy(
                                nombre = nombre,
                                apellido1 = apellido1,
                                apellido2 = apellido2,
                                telefono = telefono,
                                correoPersonal = correoPersonal
                            )
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text("Guardar cambios", fontSize = 18.sp)
                }

                Spacer(Modifier.height(16.dp))
            }
        }
    }
}
