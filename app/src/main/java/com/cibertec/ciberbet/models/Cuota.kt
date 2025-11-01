package com.cibertec.ciberbet.models

data class Cuota(
    var idCuota: String = "",
    var idEvento: String = "",
    var tipoApuesta: String = "", // ganador, empate, goles, etc.
    var descripcion: String = "", // "Equipo Local gana"
    var valorCuota: Double = 0.0,
    var estado: String = "activa" // activa, cerrada
)
