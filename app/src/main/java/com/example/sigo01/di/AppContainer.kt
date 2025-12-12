package com.example.sigo01.di

import android.content.Context
import com.example.sigo01.data.AuthRepository
import com.example.sigo01.data.local.TokenManager

/**
 * Contenedor de dependencias para la aplicación.
 *
 * Esta clase gestiona la creación de instancias de objetos que se necesitan en toda la aplicación,
 * como repositorios y gestores de datos. Sigue el patrón de inyección manual de dependencias.
 *
 * @param appContext El contexto de la aplicación, necesario para inicializar dependencias
 * como [TokenManager].
 */
class AppContainer(appContext: Context) {

    /**
     * Instancia de [TokenManager] para toda la aplicación.
     */
    private val tokenManager = TokenManager(appContext)

    /**
     * Instancia única (singleton) del [AuthRepository].
     *
     * Se inicializa de forma diferida (`lazy`) para que solo se cree cuando se accede por primera vez.
     * Se le inyecta el `tokenManager` como dependencia.
     */
    val authRepository: AuthRepository by lazy {
        AuthRepository(tokenManager)
    }
}
