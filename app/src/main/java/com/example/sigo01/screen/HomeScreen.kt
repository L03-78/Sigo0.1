package com.example.sigo01.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sigo01.actividades.ActividadesTab
import com.example.sigo01.actividades.CursosTab
import com.example.sigo01.actividades.EventosTab
import com.example.sigo01.R
import com.example.sigo01.perfil.BottomNavigationBar



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onOpenPerfil: () -> Unit,           // lo usas para abrir perfil
    onOpenHistorial: () -> Unit,        // añadido para abrir historial
    onOpenItem: () -> Unit = {}         // opcional para abrir detalles de items
) {

    var selectedTab by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {

                        Image(
                            painter = painterResource(id = R.drawable.sigo),
                            contentDescription = null,
                            modifier = Modifier.size(32.dp)
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        Text("Inicio", fontSize = 20.sp)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF1DBF9F)
                )
            )
        },
        bottomBar = {
            BottomNavigationBar(
                onOpenPerfil = onOpenPerfil,
                onOpenHistorial = onOpenHistorial
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {

            val tabs = listOf("Actividades", "Cursos", "Eventos")

            TabRow(
                selectedTabIndex = selectedTab,
                containerColor = Color.White
            ) {
                tabs.forEachIndexed { index, text ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = { Text(text) }
                    )
                }
            }

            when (selectedTab) {
                0 -> ActividadesTab(onOpenItem)
                1 -> CursosTab(onOpenItem)
                2 -> EventosTab(onOpenItem)
            }
        }
    }
}
