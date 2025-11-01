package com.cibertec.ciberbet.models

data class Transaccion(
    var idTransaccion: String = "",
    var idUsuario: String = "",
    var tipo: String = "", // deposito, retiro, apuesta o ganancia
    var monto: Double = 0.0,
    var saldoAnterior: Double = 0.0,
    var saldoNuevo: Double = 0.0,
    var descripcion: String = "",
    var fecha: String = ""
)
