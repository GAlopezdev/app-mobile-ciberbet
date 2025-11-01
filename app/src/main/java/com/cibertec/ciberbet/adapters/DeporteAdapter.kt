package com.cibertec.ciberbet.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.ciberbet.databinding.ItemDeporteBinding
import com.cibertec.ciberbet.models.Deporte

class DeporteAdapter (
    private val listaDeportes: List<Deporte>,
    private val onEditClick: (Deporte) -> Unit,
    private val onDeleteClick: (Deporte) -> Unit
) : RecyclerView.Adapter<DeporteAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemDeporteBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDeporteBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val deporte = listaDeportes[position]

        holder.binding.apply {
            tvNombreDeporte.text = deporte.nombre
            tvDescripcionDeporte.text = deporte.descripcion

            btnEditarDeporte.setOnClickListener {
                onEditClick(deporte)
            }

            btnEliminarDeporte.setOnClickListener {
                onDeleteClick(deporte)
            }
        }
    }

    override fun getItemCount() = listaDeportes.size
}