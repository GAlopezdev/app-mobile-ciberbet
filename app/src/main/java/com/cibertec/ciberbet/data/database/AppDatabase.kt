package com.cibertec.ciberbet.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cibertec.ciberbet.data.dao.UsuarioDAO
import com.cibertec.ciberbet.models.Usuario

@Database(entities = [Usuario::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun usuarioDao() : UsuarioDAO
}