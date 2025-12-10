package com.example.sigo01.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sigo01.viewmodel.LoginViewModel
@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onForgotClick: () -> Unit = {},
    onLoginClick: () -> Unit = {},
    onAdminClick: () -> Unit = {}   // admin oculto
) {

    var matricula by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val loginSuccess by viewModel.loginSuccess.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(60.dp))

        Text(text = "Inicia sesión", fontSize = 26.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Ingresa tu Matrícula Institucional", fontSize = 14.sp, color = Color.Gray)

        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(
            value = matricula,
            onValueChange = { matricula = it },
            placeholder = { Text("Matrícula") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(15.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            placeholder = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(25.dp))

        Button(
            onClick = {
                viewModel.login(matricula, password)
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
        ) {
            Text("Continuar", color = Color.White)
        }

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = "¿Olvidaste tu contraseña?",
            color = Color.Gray,
            fontSize = 14.sp,
            modifier = Modifier.clickable { onForgotClick() }
        )

        // ADMIN INVISIBLE
        Text(
            text = "Admin",
            fontSize = 12.sp,
            color = Color.Transparent,
            modifier = Modifier
                .padding(top = 40.dp)
                .clickable { onAdminClick() }
        )
    }

    LaunchedEffect(loginSuccess) {
        if (loginSuccess == true) {
            onLoginClick()
        }
    }
}
