package com.example.sigo01.actividades

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import com.example.sigo01.R
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CursosTab(onOpenItem: () -> Unit) {

    Column(
        Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        Text("Cursos Activos", fontSize = 20.sp)

        Spacer(modifier = Modifier.height(12.dp))

        CourseCard(
            titulo = "Programación de videojuegos",
            descripcion = "Un curso completo para aprender diseño...",
            imagen = R.drawable.curso1,
            onOpenItem = onOpenItem
        )

        Spacer(modifier = Modifier.height(16.dp))

        CourseCard(
            titulo = "Programación de Apps",
            descripcion = "Aprende a crear una aplicación real...",
            imagen = R.drawable.curso2,
            onOpenItem = onOpenItem
        )
    }
}

@Composable
fun CourseCard(
    titulo: String,
    descripcion: String,
    imagen: Int,
    onOpenItem: () -> Unit
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        onClick = onOpenItem
    ) {

        Column(Modifier.padding(16.dp)) {

            Image(
                painter = painterResource(id = imagen),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(titulo, fontSize = 20.sp)
            Text(descripcion, color = Color.Gray)

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = onOpenItem,
                colors = ButtonDefaults.buttonColors(Color.Black),
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Ver Más", color = Color.White)
            }
        }
    }
}
