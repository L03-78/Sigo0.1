package com.example.sigo01.perfil

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff

@Composable
fun CambiarContraseñaDialog(
    onCancel: () -> Unit,
    onAceptar: (String, String) -> Unit
) {

    var actual by remember { mutableStateOf("") }
    var nueva by remember { mutableStateOf("") }

    var mostrarActual by remember { mutableStateOf(false) }
    var mostrarNueva by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onCancel,
        title = {
            Text("Cambiar Contraseña", fontSize = 20.sp)
        },
        text = {

            Column {

                Card(colors = CardDefaults.cardColors(Color(0xFFD9E9FF))) {
                    Text(
                        "Importante: Al cambiar tu contraseña la sesión se cerrará por seguridad.",
                        modifier = Modifier.padding(12.dp)
                    )
                }

                Spacer(Modifier.height(12.dp))

                // CONTRASEÑA ACTUAL
                OutlinedTextField(
                    value = actual,
                    onValueChange = { actual = it },
                    label = { Text("Actual contraseña") },
                    visualTransformation = if (mostrarActual) VisualTransformation.None
                    else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { mostrarActual = !mostrarActual }) {
                            Icon(
                                imageVector = if (mostrarActual) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                                contentDescription = null
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(8.dp))

                // NUEVA CONTRASEÑA
                OutlinedTextField(
                    value = nueva,
                    onValueChange = { nueva = it },
                    label = { Text("Nueva contraseña") },
                    visualTransformation = if (mostrarNueva) VisualTransformation.None
                    else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { mostrarNueva = !mostrarNueva }) {
                            Icon(
                                imageVector = if (mostrarNueva) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                                contentDescription = null
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onCancel) {
                Text("Cancelar")
            }
        },
        confirmButton = {
            Button(
                onClick = { onAceptar(actual, nueva) },
                colors = ButtonDefaults.buttonColors(Color.Black)
            ) {
                Text("Aceptar", color = Color.White)
            }
        }
    )
}
