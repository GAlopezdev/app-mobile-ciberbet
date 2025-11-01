package com.cibertec.ciberbet.models

data class TipoApuesta(
    var idTipoApuesta: String = "",
    var nombre: String = "", // Ganador, Empate, Más/Menos Goles, etc
    var descripcion: String = ""
)
