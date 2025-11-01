package com.cibertec.ciberbet.models

data class Equipo(
    var idEquipo: String = "",
    var nombre: String = "",
    var pais: String = "",
    var idDeporte: String = "",
    var logoUrl: String = "" // URL del logo del equipo
)
