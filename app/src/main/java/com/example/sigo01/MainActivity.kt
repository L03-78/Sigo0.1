package com.example.sigo01

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.sigo01.data.model.UserResponse
import com.example.sigo01.di.LoginViewModelFactory
import com.example.sigo01.ui.login.DetalleCuatrimestreScreen
import com.example.sigo01.ui.login.HistorialScreen
import com.example.sigo01.ui.login.LoginViewModel
import com.example.sigo01.ui.login.PagosScreen
import com.example.sigo01.ui.login.WelcomeScreen
import com.google.gson.Gson

object Routes {
    const val LOGIN = "login_screen"
    const val WELCOME = "welcome_screen/{userJson}"

    fun welcome(userJson: String): String = "welcome_screen/$userJson"
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                AppScreenEntry()
            }
        }
    }
}

@Composable
fun AppScreenEntry() {

    val appContext = LocalContext.current.applicationContext
    val application = appContext as SigoLoginApplication
    val authRepository = application.container.authRepository
    val factory = LoginViewModelFactory(authRepository)

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN
    ) {


        composable(Routes.LOGIN) {

            val viewModel: LoginViewModel = viewModel(factory = factory)

            LoginScreen(
                viewModel = viewModel,
                onNavigateToWelcome = {
                    val json = Gson().toJson(it)
                    navController.navigate(Routes.welcome(json))
                }
            )
        }


        composable(
            route = Routes.WELCOME,
            arguments = listOf(navArgument("userJson") { type = NavType.StringType })
        ) { backStackEntry ->

            val userJson = backStackEntry.arguments?.getString("userJson")
            val user = Gson().fromJson(userJson, UserResponse::class.java)

            if (user != null) {

                val userState = remember { mutableStateOf(user) }
                WelcomeScreen(user = userState, navController = navController)
            } else {
                Text("Error al cargar datos del usuario")
            }
        }

        composable("actividades") {
            Text(
                "Pantalla de Actividades",
                modifier = Modifier.padding(20.dp)
            )
        }

        composable("historial") {
            HistorialScreen(navController)
        }

        composable(
            "cuatrimestre/{num}",
            arguments = listOf(navArgument("num") { type = NavType.IntType })
        ) { backStack ->

            val num = backStack.arguments?.getInt("num") ?: 1
            DetalleCuatrimestreScreen(navController, num)
        }


        composable("pagos") {
            PagosScreen(navController)
        }
    }
}


@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onNavigateToWelcome: (UserResponse) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(uiState.loginSuccess) {
        if (uiState.loginSuccess && uiState.user != null) {
            Toast.makeText(context, "Bienvenido ${uiState.user!!.personFullName}", Toast.LENGTH_LONG).show()
            onNavigateToWelcome(uiState.user!!)
        }
    }

    LaunchedEffect(uiState.errorMessage) {
        uiState.errorMessage?.let {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Inicia sesión",
            style = MaterialTheme.typography.headlineSmall,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Ingresa tu Matrícula Institucional",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = uiState.username,
            onValueChange = viewModel::onUsernameChange,
            placeholder = { Text("Matrícula") },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = uiState.password,
            onValueChange = viewModel::onPasswordChange,
            placeholder = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        if (uiState.isLoading) {
            CircularProgressIndicator()
            Spacer(modifier = Modifier.height(24.dp))
        }

        Button(
            onClick = viewModel::login,
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text("Continuar", color = Color.White)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "¿Olvidaste tu contraseña?",
            color = Color.Gray,
            modifier = Modifier.clickable { }
        )
    }
}
