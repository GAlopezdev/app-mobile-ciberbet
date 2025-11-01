package com.cibertec.ciberbet.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.ciberbet.databinding.ItemEquipoBinding
import com.cibertec.ciberbet.models.Equipo

class EquipoAdapter(
    private val listaEquipos: List<Equipo>,
    private val deportesMap: Map<String, String>, // id_deporte -> nombre_deporte
    private val onEditClick: (Equipo) -> Unit,
    private val onDeleteClick: (Equipo) -> Unit
) : RecyclerView.Adapter<EquipoAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemEquipoBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemEquipoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val equipo = listaEquipos[position]

        holder.binding.apply {
            tvNombreEquipo.text = equipo.nombre
            tvPaisEquipo.text = equipo.pais

            // Mostrar nombre del deporte
            val nombreDeporte = deportesMap[equipo.idDeporte] ?: "Sin deporte"
            tvDeporteEquipo.text = nombreDeporte

            // Por ahora imagen por defecto, luego cargaremos con Glide/Picasso
            ivLogoEquipo.setImageResource(android.R.drawable.ic_menu_gallery)

            btnEditarEquipo.setOnClickListener {
                onEditClick(equipo)
            }

            btnEliminarEquipo.setOnClickListener {
                onDeleteClick(equipo)
            }
        }
    }

    override fun getItemCount() = listaEquipos.size
}