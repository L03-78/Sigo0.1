package com.example.sigo01.perfil

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.sigo01.R
@Composable
fun BottomNavigationBar(
    onOpenPerfil: () -> Unit,
    onOpenHistorial: () -> Unit
) {

    NavigationBar(containerColor = Color.White) {

        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.icon_home),
                    contentDescription = "Inicio"
                )
            },
            label = { Text("Inicio") }
        )

        NavigationBarItem(
            selected = false,
            onClick = onOpenHistorial,
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_historial),
                    contentDescription = "Historial"
                )
            },
            label = { Text("Historial") }
        )

        NavigationBarItem(
            selected = false,
            onClick = onOpenPerfil,
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.icn_usuario),
                    contentDescription = "Perfil"
                )
            },
            label = { Text("Perfil") }
        )
    }
}
