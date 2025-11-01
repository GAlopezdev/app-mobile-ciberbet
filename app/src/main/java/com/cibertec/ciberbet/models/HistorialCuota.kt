package com.cibertec.ciberbet.models

data class HistorialCuota(
    var idHistorialCuota: String = "",
    var idCuota: String = "",
    var valorAnterior: Double = 0.0,
    var valorNuevo: Double = 0.0,
    var fechaCambio: String = ""
)
