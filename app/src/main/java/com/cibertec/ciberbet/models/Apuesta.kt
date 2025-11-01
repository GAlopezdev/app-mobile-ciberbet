package com.cibertec.ciberbet.models

data class Apuesta(
    var idApuesta: String = "",
    var idUsuario: String = "",
    var idEvento: String = "",
    var equipoApostado: String = "",
    var montoApostado: Double = 0.0,
    var cuotaAplicada: Double = 0.0,
    var gananciaPotencial: Double = 0.0,
    var estado: String = "pendiente", // pendiente, ganada o perdida
    var fecha: String = ""

)
