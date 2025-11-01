package com.cibertec.ciberbet.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.cibertec.ciberbet.R
import com.cibertec.ciberbet.databinding.InicioBinding
import com.cibertec.ciberbet.fragments.DeportesFragment
import com.cibertec.ciberbet.fragments.EquiposFragment
import com.cibertec.ciberbet.fragments.EventosFragment
import com.cibertec.ciberbet.fragments.HomeFragment
import com.cibertec.ciberbet.fragments.MisApuestasFragment
import com.cibertec.ciberbet.fragments.PerfilFragment

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: InicioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = InicioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Cargar fragment inicial (Home)
        if (savedInstanceState == null) {
            cargarFragment(HomeFragment())
        }

        // Configurar el Bottom Navigation
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    cargarFragment(HomeFragment())
                    true
                }
                R.id.nav_apuestas -> {
                    cargarFragment(MisApuestasFragment())
                    true
                }
                R.id.nav_perfil -> {
                    cargarFragment(PerfilFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun cargarFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
