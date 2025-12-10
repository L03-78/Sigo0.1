package com.example.sigo01.actividades


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TarjetaMateria(
    nombre: String,
    profesor: String,
    unidades: List<Pair<String, String>>
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Text(text = nombre, fontSize = 18.sp)
            Text(text = "Profesor: $profesor", fontSize = 14.sp)
            Spacer(modifier = Modifier.height(8.dp))

            // Unidades SOLO DE ADORNO
            unidades.forEach { (titulo, _) ->
                Text(text = "- $titulo", fontSize = 13.sp)
            }
        }
    }
}

