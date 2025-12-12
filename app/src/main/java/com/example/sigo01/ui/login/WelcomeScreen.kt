package com.example.sigo01.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sigo01.R
import com.example.sigo01.data.model.UserResponse

/* -------------------------------------------------------------
    PANTALLA DE PERFIL (WELCOME SCREEN)
--------------------------------------------------------------*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WelcomeScreen(
    user: UserResponse,
    navController: NavController
) {
    var nombre by remember { mutableStateOf(user.personFullName) }
    var correo by remember { mutableStateOf(user.email) }
    var telefono by remember { mutableStateOf("") }
    var password by remember { mutableStateOf(user.password) }

    Scaffold(
        topBar = { TopBarPerfil(navController) },
        bottomBar = { BottomNavBar(navController) }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFF5F5F5))
        ) {

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {

                    Text(
                        text = "Datos personales",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color(0xFF007F3F)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = nombre,
                        onValueChange = { nombre = it },
                        label = { Text("Nombre completo") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = correo,
                        onValueChange = { correo = it },
                        label = { Text("Correo electrónico") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = telefono,
                        onValueChange = { telefono = it },
                        label = { Text("Número de teléfono") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Contraseña") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = {
                            println("Nuevo nombre: $nombre")
                            println("Nuevo correo: $correo")
                            println("Nuevo teléfono: $telefono")
                            println("Nueva contraseña: $password")
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007F3F)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Guardar cambios", color = Color.White, fontSize = 16.sp)
                    }
                }
            }
        }
    }
}

/* -------------------------------------------------------------
    TOP BAR DE PERFIL
--------------------------------------------------------------*/

@Composable
fun TopBarPerfil(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF007F3F))
            .padding(16.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {

            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Regresar",
                tint = Color.White,
                modifier = Modifier
                    .size(28.dp)
                    .clickable { navController.popBackStack() }
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = "Perfil del Alumno",
                fontSize = 20.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )

            Image(
                painter = painterResource(id = R.drawable.sigo),
                contentDescription = "",
                modifier = Modifier.size(40.dp)
            )
        }
    }
}

/* -------------------------------------------------------------
    BOTTOM NAV BAR
--------------------------------------------------------------*/

@Composable
fun BottomNavBar(navController: NavController) {
    NavigationBar(containerColor = Color.White) {

        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("home") },
            icon = { Icon(painterResource(R.drawable.icon_home), contentDescription = "") }
        )

        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("perfil") },
            icon = { Icon(painterResource(R.drawable.icn_usuario), contentDescription = "") }
        )

        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("actividades") },
            icon = { Icon(painterResource(R.drawable.icon_calendario), contentDescription = "") }
        )

        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("pagos") },
            icon = { Icon(painterResource(R.drawable.icon_pagos), contentDescription = "") }
        )
    }
}

/* -------------------------------------------------------------
    PANTALLA: ACTIVIDADES
--------------------------------------------------------------*/

@Composable
fun ActividadesScreen(navController: NavController) {

    Scaffold(
        topBar = { TopBarActividades() },
        bottomBar = { BottomNavBar(navController) }
    ) { padding ->

        Column(
            modifier = Modifier.padding(padding)
        ) {

            TabsActividades()

            ContentActividades()
        }
    }
}

/* -------------------------------------------------------------
    TOP BAR DE ACTIVIDADES
--------------------------------------------------------------*/

@Composable
fun TopBarActividades() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF007F3F))
            .padding(16.dp)
    ) {
        Text(
            text = "Actividades",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterStart)
        )
    }
}

/* -------------------------------------------------------------
    TABS (Actividades / Cursos / Eventos)
--------------------------------------------------------------*/

@Composable
fun TabsActividades() {
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("Actividades", "Cursos", "Eventos")

    TabRow(selectedTabIndex = selectedTab) {
        tabs.forEachIndexed { index, text ->
            Tab(
                selected = selectedTab == index,
                onClick = { selectedTab = index },
                text = { Text(text) }
            )
        }
    }
}

/* -------------------------------------------------------------
    CONTENIDO DE ACTIVIDADES (Imágenes)
--------------------------------------------------------------*/

@Composable
fun ContentActividades() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {

        item {
            ImagenActividad(
                titulo = "Programación de Videojuegos",
                descripcion = "Curso introductorio...",
                imagen = R.drawable.curso1
            )
        }

        item {
            ImagenActividad(
                titulo = "Programación de Apps",
                descripcion = "Aprende a crear apps...",
                imagen = R.drawable.curso2
            )
        }

        item {
            ImagenActividad(
                titulo = "Talleres y Eventos",
                descripcion = "Eventos próximos...",
                imagen = R.drawable.evento1
            )
        }
    }
}

/* -------------------------------------------------------------
    TARJETA DE ACTIVIDAD
--------------------------------------------------------------*/

@Composable
fun ImagenActividad(titulo: String, descripcion: String, imagen: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column {
            Image(
                painter = painterResource(imagen),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
            )
            Text(titulo, fontWeight = FontWeight.Bold, modifier = Modifier.padding(8.dp))
            Text(descripcion, modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp))
        }
    }
}
