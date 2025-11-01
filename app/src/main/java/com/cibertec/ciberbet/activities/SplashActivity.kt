package com.cibertec.ciberbet.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.cibertec.ciberbet.MainActivity
import com.cibertec.ciberbet.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Muestra el diseño de la pantalla de carga
        setContentView(R.layout.pantalladecarga)

        // Espera 2 segundos y luego abre la MainActivity
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Cierra esta pantalla para que no se pueda volver atrás
        }, 2000)
    }
}