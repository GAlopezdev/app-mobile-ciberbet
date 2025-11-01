package com.cibertec.ciberbet.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.cibertec.ciberbet.MainActivity
import com.cibertec.ciberbet.data.database.AppApplication
import com.cibertec.ciberbet.databinding.AppActivityRegisterBinding
import com.cibertec.ciberbet.models.Usuario
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: AppActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AppActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Este código se ejecuta cuando el usuario hace clic en el botón de login
        binding.textViewLogin.setOnClickListener {
            handleInicioSesion() // Llama a la función que definimos abajo
        }
        binding.buttonRegister.setOnClickListener {
            handleRegister() // Llama a la función que definimos abajo
        }
    }

    private fun handleInicioSesion() {
        val register = Intent(this, MainActivity::class.java)
        startActivity(register)
        finish()
    }

    private fun handleRegister() {
        val nombres = binding.editTextFullName.text.toString().trim()
        val dni = binding.editTextDni.text.toString().trim()
        val telefono = binding.editTextPhone.text.toString().trim()
        val correo = binding.editTextEmail.text.toString().trim()
        val password = binding.editTextPassword.text.toString().trim()

        if (nombres.isNotEmpty() && dni.isNotEmpty() && telefono.isNotEmpty() &&
            correo.isNotEmpty() && password.isNotEmpty()) {
            val usuario = Usuario(
                nombres = nombres,
                dni = dni,
                telefono = telefono,
                correo = correo,
                password = password,
                flgEli = false
            )

            // 🚀 Ejecutar en un hilo secundario
            lifecycleScope.launch(Dispatchers.IO) {
                AppApplication.database.usuarioDao().addUsuario(usuario)

                // ⚡ Cambiamos al hilo principal para mostrar el Toast
                launch(Dispatchers.Main) {
                    Toast.makeText(this@RegisterActivity, "Registro exitoso", Toast.LENGTH_SHORT).show()
                    handleInicioSesion()
                }
            }
        } else {
            Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
        }
    }
}