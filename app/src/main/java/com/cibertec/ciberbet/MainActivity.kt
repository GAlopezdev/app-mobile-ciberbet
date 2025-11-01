package com.cibertec.ciberbet

// --- IMPORTACIONES NECESARIAS ---
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cibertec.ciberbet.activities.HomeActivity
import com.cibertec.ciberbet.databinding.ActivityMainBinding
// ---------------------------------

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Este código se ejecuta cuando el usuario hace clic en el botón de login
        binding.buttonLogin.setOnClickListener {
            handleLogin() // Llama a la función que definimos abajo
        }
    }

    /**
     * Esta función se encarga de verificar el email y la contraseña
     * y navegar a la siguiente pantalla si son correctos.
     */
    private fun handleLogin() {
        val email = binding.editTextEmail.text.toString()
        val password = binding.editTextPassword.text.toString()

        // cuenta para iniciar secion
        if (email == "" && password == "") {
            Toast.makeText(this, "¡Bienvenido!", Toast.LENGTH_SHORT).show()

            //  la pantalla principal (HomeActivity)
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish() // Cierra la pantalla de login para que no se pueda volver atrás
        } else {
            Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
        }
    }
}