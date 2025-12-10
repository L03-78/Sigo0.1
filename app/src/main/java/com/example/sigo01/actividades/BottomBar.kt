package com.example.sigo01.actividades

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import com.example.sigo01.R
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
@Composable
fun BottomBar(
    onHome: () -> Unit,
    onPerfil: () -> Unit,
    onHistorial: () -> Unit,
    onPagos: () -> Unit
) {
    NavigationBar {

        // Home
        NavigationBarItem(
            selected = false,
            onClick = { onHome() },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.icon_home),
                    contentDescription = "Inicio"
                )
            }
        )

        // Perfil
        NavigationBarItem(
            selected = false,
            onClick = { onPerfil() },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.icn_usuario),
                    contentDescription = "Perfil"
                )
            }
        )

        // Historial académico
        NavigationBarItem(
            selected = false,
            onClick = { onHistorial() },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_historial),
                    contentDescription = "Historial Académico"
                )
            }
        )

        // Pagos (si lo usarás después)
        NavigationBarItem(
            selected = false,
            onClick = { onPagos() },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.icon_pagos),
                    contentDescription = "Pagos"
                )
            }
        )
    }
}