package com.example.sigo01.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecuperarScreen(
    onBackClick: () -> Unit = {},
    onRecoverClick: (String) -> Unit = {}
) {

    var usuario by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        // FLECHA REGRESAR
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Regresar",
            modifier = Modifier
                .size(28.dp)
                .clickable { onBackClick() }
        )

        Spacer(modifier = Modifier.height(25.dp))

        // TITULO
        Text(
            text = "Recuperación de contraseña",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(12.dp))

        // TARJETA INFORMATIVA
        Card(
            colors = CardDefaults.cardColors(Color(0xFFD9E9FF)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Ingresa tu matrícula o correo institucional. " +
                        "Si existe en el sistema, te enviaremos tu contraseña.",
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center,
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(35.dp))

        // CAMPO DE MATRICULA
        OutlinedTextField(
            value = usuario,
            onValueChange = { usuario = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            label = { Text("Matrícula o correo institucional") },
            singleLine = true,
            shape = MaterialTheme.shapes.medium
        )

        Spacer(modifier = Modifier.height(30.dp))

        // BOTÓN
        Button(
            onClick = { onRecoverClick(usuario) },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(Color.Black),
            shape = MaterialTheme.shapes.medium
        ) {
            Text("Recuperar", color = Color.White)
        }
    }
}
