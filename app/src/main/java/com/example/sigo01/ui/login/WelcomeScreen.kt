package com.example.sigo01.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sigo01.R
import com.example.sigo01.data.model.UserResponse
import kotlinx.coroutines.launch

/* -------------------------------------------------------------
   WELCOME SCREEN
--------------------------------------------------------------*/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WelcomeScreen(
    user: MutableState<UserResponse>,
    navController: NavController
) {
    var nombre by remember { mutableStateOf(user.value.personFullName ?: "") }
    var correo by remember { mutableStateOf(user.value.email ?: "") }
    var username by remember { mutableStateOf(user.value.username ?: "") }
    var password by remember { mutableStateOf(user.value.password ?: "") }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = { TopBarPerfil(navController) },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(0xFFF5F5F5))
                .verticalScroll(rememberScrollState())
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
                        value = username,
                        onValueChange = { username = it },
                        label = { Text("Nombre de usuario") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Contraseña") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = user.value.profileName ?: "",
                        onValueChange = {},
                        label = { Text("Perfil") },
                        enabled = false,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = user.value.personId.toString(),
                        onValueChange = {},
                        label = { Text("ID de persona") },
                        enabled = false,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = user.value.roles.joinToString(", "),
                        onValueChange = {},
                        label = { Text("Roles") },
                        enabled = false,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = user.value.id.toString(),
                        onValueChange = {},
                        label = { Text("ID") },
                        enabled = false,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = user.value.register ?: "",
                        onValueChange = {},
                        label = { Text("Registro") },
                        enabled = false,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = user.value.registerUser ?: "",
                        onValueChange = {},
                        label = { Text("Usuario de registro") },
                        enabled = false,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = user.value.active.toString(),
                        onValueChange = {},
                        label = { Text("Activo") },
                        enabled = false,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = user.value.termsConditions.toString(),
                        onValueChange = {},
                        label = { Text("Términos y condiciones") },
                        enabled = false,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = user.value.messageControl ?: "",
                        onValueChange = {},
                        label = { Text("Control de mensaje") },
                        enabled = false,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = user.value.accessModule ?: "",
                        onValueChange = {},
                        label = { Text("Módulo de acceso") },
                        enabled = false,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = {
                            user.value = user.value.copy(
                                personFullName = nombre,
                                email = correo,
                                username = username,
                                password = password
                            )

                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    message = "Guardado exitosamente",
                                    withDismissAction = true
                                )
                            }
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
    TOP BAR + MENÚ DE 3 PUNTITOS
--------------------------------------------------------------*/
@Composable
fun TopBarPerfil(navController: NavController) {

    var menuExpanded by remember { mutableStateOf(false) }
    var showLogoutDialog by remember { mutableStateOf(false) }

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

            Spacer(modifier = Modifier.width(8.dp))

            IconButton(onClick = { menuExpanded = true }) {
                Icon(
                    Icons.Default.MoreVert,
                    contentDescription = "Menú",
                    tint = Color.White
                )
            }

            DropdownMenu(
                expanded = menuExpanded,
                onDismissRequest = { menuExpanded = false },

                // ⭐ QUE EL MENÚ SALGA DEL OTRO LADO ⭐
                offset = DpOffset(x = 180.dp, y = 10.dp)
            ) {

                DropdownMenuItem(
                    text = { Text("Historial académico") },
                    onClick = {
                        menuExpanded = false
                        navController.navigate("historial")
                    }
                )

                DropdownMenuItem(
                    text = { Text("Pagos") },
                    onClick = {
                        menuExpanded = false
                        navController.navigate("pagos")
                    }
                )

                Divider()

                DropdownMenuItem(
                    text = { Text("Cerrar sesión", color = Color.Red) },
                    onClick = {
                        menuExpanded = false
                        showLogoutDialog = true
                    }
                )
            }
        }
    }

    if (showLogoutDialog) {
        AlertDialog(
            onDismissRequest = { showLogoutDialog = false },
            title = { Text("Cerrar sesión") },
            text = { Text("¿Estás seguro que deseas cerrar sesión?") },

            confirmButton = {
                TextButton(
                    onClick = {
                        showLogoutDialog = false
                        navController.navigate("login") {
                            popUpTo(0)
                            launchSingleTop = true
                        }
                    }
                ) {
                    Text("Sí", color = Color.Red)
                }
            },

            dismissButton = {
                TextButton(onClick = { showLogoutDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }
}


/* -------------------------------------------------------------
   HISTORIAL ACADÉMICO
--------------------------------------------------------------*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistorialScreen(navController: NavController) {

    val lista = listOf(
        "4to Cuatrimestre",
        "3er Cuatrimestre",
        "2do Cuatrimestre",
        "1er Cuatrimestre"
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Historial Académico") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "")
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {

            Text("Cuatrimestres", fontWeight = FontWeight.Bold, fontSize = 18.sp)

            Spacer(Modifier.height(16.dp))

            lista.forEachIndexed { index, item ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                        .clickable {
                            navController.navigate("cuatrimestre/${index + 1}")
                        },
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(item, fontSize = 16.sp)
                        Icon(Icons.Default.MoreVert, contentDescription = "")
                    }
                }
            }
        }
    }
}

/* -------------------------------------------------------------
   DETALLE DEL CUATRIMESTRE
--------------------------------------------------------------*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleCuatrimestreScreen(
    navController: NavController,
    numero: Int
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("${numero}° Cuatrimestre") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "")
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) {

                Column(Modifier.padding(16.dp)) {

                    Text(
                        "Inglés II",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )

                    Spacer(Modifier.height(8.dp))

                    OutlinedTextField(
                        value = "9.8",
                        onValueChange = {},
                        label = { Text("Calificación") },
                        enabled = false,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(Modifier.height(8.dp))

                    OutlinedTextField(
                        value = "Aprobado",
                        onValueChange = {},
                        label = { Text("Estatus") },
                        enabled = false,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

/* -------------------------------------------------------------
   PANTALLA DE PAGOS
--------------------------------------------------------------*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PagosScreen(navController: NavController) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pagos") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "")
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {

            Text("Nombre del alumno", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text("C.U.: 21845678", color = Color.Gray)

            Spacer(Modifier.height(16.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(Modifier.padding(16.dp)) {
                    Text("Enero 2025")
                    Text("Estado: Pagado", color = Color(0xFF007F3F))
                }
            }
        }
    }
}

/* -------------------------------------------------------------
   NAVEGACIÓN COMPLETA
--------------------------------------------------------------*/

@Composable
fun AppNavigation(navController: NavHostController, user: UserResponse) {
    val userState = remember { mutableStateOf(user) }

    NavHost(navController, startDestination = "welcome") {

        composable("welcome") { WelcomeScreen(userState, navController) }

        composable("historial") { HistorialScreen(navController) }

        composable("cuatrimestre/{num}") { backStack ->
            val num = backStack.arguments?.getString("num")?.toInt() ?: 1
            DetalleCuatrimestreScreen(navController, num)
        }

        composable("pagos") { PagosScreen(navController) }

        composable("login") {
            Text("Pantalla Login (placeholder)")
        }
    }
}
