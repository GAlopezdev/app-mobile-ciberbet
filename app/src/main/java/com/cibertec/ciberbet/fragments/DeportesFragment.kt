package com.cibertec.ciberbet.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.cibertec.ciberbet.R
import com.cibertec.ciberbet.adapters.DeporteAdapter
import com.cibertec.ciberbet.databinding.FragmentDeportesBinding
import com.cibertec.ciberbet.models.Deporte
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class DeportesFragment : Fragment() {
    private var _binding: FragmentDeportesBinding? = null
    private val binding get() = _binding!!

    private lateinit var database: DatabaseReference
    private lateinit var deporteAdapter: DeporteAdapter
    private val listaDeportes = mutableListOf<Deporte>()

    // Variable para modo edición
    private var deporteEditando: Deporte? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDeportesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        database = FirebaseDatabase.getInstance().getReference("deportes")
        setupRecyclerView()
        cargarDeportes()
        setupListeners()
    }

    private fun setupRecyclerView() {
        deporteAdapter = DeporteAdapter(
            listaDeportes,
            onEditClick = { deporte -> iniciarEdicion(deporte) },
            onDeleteClick = { deporte -> confirmarEliminar(deporte) }
        )

        binding.recyclerDeportes.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = deporteAdapter
        }
    }

    private fun setupListeners() {
        binding.btnGuardarDeporte.setOnClickListener {
            if (deporteEditando != null) {
                actualizarDeporte()
            } else {
                agregarDeporte()
            }
        }

        binding.btnCancelar.setOnClickListener {
            cancelarEdicion()
        }
    }

    private fun cargarDeportes() {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                listaDeportes.clear()
                for (data in snapshot.children) {
                    val deporte = data.getValue(Deporte::class.java)
                    deporte?.let { listaDeportes.add(it) }
                }
                deporteAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(
                    context,
                    "Error al cargar deportes: ${error.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun agregarDeporte() {
        val nombre = binding.etNombreDeporte.text.toString().trim()
        val descripcion = binding.etDescripcionDeporte.text.toString().trim()

        if (nombre.isEmpty()) {
            binding.tilNombreDeporte.error = "El nombre es obligatorio"
            return
        }

        binding.tilNombreDeporte.error = null

        val id = database.push().key ?: return
        val deporte = Deporte(id, nombre, descripcion)

        database.child(id).setValue(deporte)
            .addOnSuccessListener {
                Toast.makeText(context, "Deporte agregado correctamente", Toast.LENGTH_SHORT).show()
                limpiarCampos()
            }
            .addOnFailureListener { error ->
                Toast.makeText(
                    context,
                    "Error al agregar: ${error.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }

    private fun iniciarEdicion(deporte: Deporte) {
        deporteEditando = deporte

        binding.etNombreDeporte.setText(deporte.nombre)
        binding.etDescripcionDeporte.setText(deporte.descripcion)
        binding.btnGuardarDeporte.text = "Actualizar"
        binding.btnCancelar.visibility = View.VISIBLE

        // Scroll hacia arriba para ver el formulario
        binding.root.scrollTo(0, 0)
    }

    private fun actualizarDeporte() {
        val nombre = binding.etNombreDeporte.text.toString().trim()
        val descripcion = binding.etDescripcionDeporte.text.toString().trim()

        if (nombre.isEmpty()) {
            binding.tilNombreDeporte.error = "El nombre es obligatorio"
            return
        }

        binding.tilNombreDeporte.error = null

        deporteEditando?.let { deporte ->
            val deporteActualizado = deporte.copy(
                nombre = nombre,
                descripcion = descripcion
            )

            database.child(deporte.idDeporte).setValue(deporteActualizado)
                .addOnSuccessListener {
                    Toast.makeText(
                        context,
                        "Deporte actualizado correctamente",
                        Toast.LENGTH_SHORT
                    ).show()
                    cancelarEdicion()
                }
                .addOnFailureListener { error ->
                    Toast.makeText(
                        context,
                        "Error al actualizar: ${error.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }
    }

    private fun confirmarEliminar(deporte: Deporte) {
        AlertDialog.Builder(requireContext())
            .setTitle("Eliminar Deporte")
            .setMessage("¿Estás seguro de eliminar ${deporte.nombre}?")
            .setPositiveButton("Eliminar") { _, _ ->
                eliminarDeporte(deporte)
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun eliminarDeporte(deporte: Deporte) {
        database.child(deporte.idDeporte).removeValue()
            .addOnSuccessListener {
                Toast.makeText(
                    context,
                    "Deporte eliminado correctamente",
                    Toast.LENGTH_SHORT
                ).show()
            }
            .addOnFailureListener { error ->
                Toast.makeText(
                    context,
                    "Error al eliminar: ${error.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }

    private fun cancelarEdicion() {
        deporteEditando = null
        limpiarCampos()
        binding.btnGuardarDeporte.text = "Agregar"
        binding.btnCancelar.visibility = View.GONE
    }

    private fun limpiarCampos() {
        binding.etNombreDeporte.text?.clear()
        binding.etDescripcionDeporte.text?.clear()
        binding.tilNombreDeporte.error = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}