package com.example.sigo01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.sigo01.data.database.AppDatabase
import com.example.sigo01.data.repository.AlumnoRepository
import com.example.sigo01.data.entity.Alumno
import com.example.sigo01.data.entity.Cuatrimestre
import com.example.sigo01.data.entity.Materia
import com.example.sigo01.viewmodel.LoginViewModel
import com.example.sigo01.viewmodel.PerfilViewModel
import com.example.sigo01.viewmodel.HistorialViewModel
import com.example.sigo01.viewmodel.CuatrimestreViewModel
import com.example.sigo01.screen.HomeScreen
import com.example.sigo01.screen.LoginScreen
import com.example.sigo01.screen.PerfilScreen
import com.example.sigo01.screen.RecuperarScreen
import com.example.sigo01.perfil.CambiarContraseñaDialog
import com.example.sigo01.perfil.CuatrimestreDetalleScreen
import com.example.sigo01.perfil.HistorialAcademicoScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // BASE DE DATOS
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "sigo01_database"
        )
            .fallbackToDestructiveMigration()
            .build()

        val alumnoDao = db.alumnoDao()
        val cuatriDao = db.cuatrimestreDao()
        val materiaDao = db.materiaDao()

        val alumnoRepo = AlumnoRepository(alumnoDao)

        lifecycleScope.launch(Dispatchers.IO) {

            val existe = alumnoRepo.getAlumno("utm241037ti")

            if (existe == null) {
                alumnoRepo.insertAlumno(
                    Alumno(
                        matricula = "utm241037ti",
                        password = "Lucia09",
                        nombre = "Lucia",
                        apellido1 = "Molinero",
                        apellido2 = "Garcia",
                        correoInstitucional = "Lucia@sigo.com",
                        correoPersonal = "luciamolinero@gmail.com",
                        telefono = "447826643",
                        sexo = "F",
                        fechaNacimiento = "08/02/2006",
                        nss = "12345678901",
                        carrera = "Tecnologias de la informacion",
                        grupo = "4b",
                        tutor = "Dra. Gricelda Rodriguez Robledo"
                    )
                )
            }
        }

        // INSERTAR CUATRIMESTRES SOLO SI NUNCA SE HAN INSERTADO
        lifecycleScope.launch(Dispatchers.IO) {

            val existentes = cuatriDao.countByMatricula("utm241037ti")

            if (existentes == 0) {

                cuatriDao.insert(
                    Cuatrimestre(
                        matriculaAlumno = "utm241037ti",
                        nombre = "1er Cuatrimestre",
                        fechaInicio = "09/09/2024",
                        fechaFin = "20/12/2024",
                        promedio = 9.5,
                        carrera = "Tecnologias de la informacion",
                        grupo = "1B Matutino",
                        tutor = "M.G.T.I Gerardo Chavez Hernandez",
                        desempeno = "ESTRATEGICO",
                        progreso = 100
                    )
                )

                cuatriDao.insert(
                    Cuatrimestre(
                        matriculaAlumno = "utm241037ti",
                        nombre = "2do Cuatrimestre",
                        fechaInicio = "13/01/2025",
                        fechaFin = "30/04/2025",
                        promedio = 9.5,
                        carrera = "Tecnologias de la informacion",
                        grupo = "2B Matutino",
                        tutor = "Dra. Olga Leticia Robles Garcia",
                        desempeno = "ESTRATEGICO",
                        progreso = 100
                    )
                )

                cuatriDao.insert(
                    Cuatrimestre(
                        matriculaAlumno = "utm241037ti",
                        nombre = "3er Cuatrimestre",
                        fechaInicio = "06/05/2025",
                        fechaFin = "29/08/2025",
                        promedio = 9.2,
                        carrera = "Tecnologias de la informacion",
                        grupo = "3B Matutino",
                        tutor = "M.G.T.I Omar Ordoñez Toledo",
                        desempeno = "AUTONOMO",
                        progreso = 100
                    )
                )

                cuatriDao.insert(
                    Cuatrimestre(
                        matriculaAlumno = "utm241037ti",
                        nombre = "4to Cuatrimestre",
                        fechaInicio = "08/09/2025",
                        fechaFin = "19/12/2025",
                        promedio = 8.0,
                        carrera = "Tecnologias de la informacion",
                        grupo = "4B Matutino",
                        tutor = "Dra. Gricelda Rodriguez Robledo",
                        desempeno = "POR CAPTURAR",
                        progreso = 49
                    )
                )
            }
        }
        // DESPUÉS de insertar cuatrimestres
        lifecycleScope.launch(Dispatchers.IO) {

            val existentesMaterias = materiaDao.countMaterias()

            if (existentesMaterias == 0) {

                materiaDao.insertarMateria(
                    Materia(
                        nombre = "Matemáticas",
                        profesor = "Ing. López",
                        cuatrimestreId = 1
                    )
                )

                materiaDao.insertarMateria(
                    Materia(
                        nombre = "Programación",
                        profesor = "Ing. Torres",
                        cuatrimestreId = 1
                    )
                )

                materiaDao.insertarMateria(
                    Materia(
                        nombre = "Bases de Datos",
                        profesor = "Lic. Pérez",
                        cuatrimestreId = 2
                    )
                )

                materiaDao.insertarMateria(
                    Materia(
                        nombre = "Desarrollo Móvil",
                        profesor = "Mtro. Hernández",
                        cuatrimestreId = 3
                    )
                )
            }
        }

        val loginVM = LoginViewModel(alumnoRepo)
        val perfilVM = PerfilViewModel(alumnoRepo)
        val historialVM = HistorialViewModel(cuatriDao)
        val cuatriVM = CuatrimestreViewModel(materiaDao)

        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {

                    val nav = rememberNavController()

                    NavHost(navController = nav, startDestination = "login") {

                        composable("login") {
                            LoginScreen(
                                viewModel = loginVM,
                                onLoginClick = { nav.navigate("home") },
                                onForgotClick = { nav.navigate("recuperar") }
                            )


                    }

                        composable("home") {
                            HomeScreen(
                                onOpenPerfil = { nav.navigate("perfil") },
                                onOpenHistorial = { nav.navigate("historial") }
                            )
                        }

                        composable("perfil") {
                            PerfilScreen(
                                viewModel = perfilVM,
                                onBack = { nav.popBackStack() },
                                onAbrirCambiarContrasena = { nav.navigate("cambiar_contrasena") }
                            )
                        }

                        composable("historial") {
                            HistorialAcademicoScreen(
                                viewModel = historialVM,
                                onBack = { nav.popBackStack() },
                                onOpenCuatrimestre = { id ->
                                    nav.navigate("detalle/$id")
                                }
                            )
                        }

                        composable("detalle/{id}") { back ->
                            val id = back.arguments?.getString("id")!!.toInt()
                            CuatrimestreDetalleScreen(
                                tituloCuatri = "Cuatrimestre",
                                cuatriId = id,
                                viewModel = cuatriVM,
                                onBack = { nav.popBackStack() }
                            )
                        }

                        composable("recuperar") {
                            RecuperarScreen(
                                onBackClick = { nav.popBackStack() },
                                onRecoverClick = { nav.popBackStack() }
                            )
                        }

                        composable("cambiar_contrasena") {
                            CambiarContraseñaDialog(
                                onCancel = { nav.popBackStack() },
                                onAceptar = { nav.popBackStack() }
                            )
                        }

                    }
                }
            }
        }
    }
}
