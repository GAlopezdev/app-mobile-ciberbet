package com.cibertec.ciberbet.models

data class Usuario(
    var idUsuario: String = "",
    var nombreUsuario: String = "",
    var email: String = "",
    var nombres: String = "",
    var apellidos: String = "",
    var saldoActual: Double = 0.0,
    var fechaRegistro: String = ""
)
