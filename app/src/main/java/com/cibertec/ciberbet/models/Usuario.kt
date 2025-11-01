package com.cibertec.ciberbet.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuario")
data class Usuario(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    var nombres: String,
    var dni: String,
    var telefono: String,
    var correo: String,
    var password: String,
    val flgEli: Boolean = false
)

