package com.cibertec.ciberbet.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.cibertec.ciberbet.R
import com.cibertec.ciberbet.activities.AdminActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class PerfilFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Layout temporal con botón para ir al panel admin
        val layout = LinearLayout(requireContext()).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(32, 32, 32, 32)
        }

        val textView = TextView(requireContext()).apply {
            text = "👤 Mi Perfil\n\n" +
                    "Usuario: admin@ciberbet.com\n" +
                    "Saldo: S/ 1,000.00\n\n" +
                    "Aquí podrás:\n" +
                    "• Ver tu información personal\n" +
                    "• Consultar tu saldo\n" +
                    "• Realizar depósitos/retiros\n" +
                    "• Editar tu cuenta\n" +
                    "• Cerrar sesión\n\n"
            textSize = 18f
        }
        layout.addView(textView)

        // Botón para ir al panel de administración
        val btnAdmin = Button(requireContext()).apply {
            text = "🔧 Panel de Administración"
            setOnClickListener {
                val intent = Intent(requireContext(), AdminActivity::class.java)
                startActivity(intent)
            }
        }
        layout.addView(btnAdmin)

        return layout
    }
}