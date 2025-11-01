package com.cibertec.ciberbet.models

data class Evento(
    val idEvento: String = "",
    val idDeporte: String = "",
    val equipoLocal: String = "", // id del equipo
    val equipoVisitante: String = "", // id del equipo
    val fecha_hora: String = "",
    val ubicacion: String = "",
    val estadoEvento: String = "Programado", // Programado, En vivo o finalizado
    val resultadoLocal: Int = 0,
    val resultadoVisitante: Int = 0
)
