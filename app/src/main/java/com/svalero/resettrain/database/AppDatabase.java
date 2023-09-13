package com.svalero.resettrain.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.svalero.resettrain.dao.PerfilDao;
import com.svalero.resettrain.dao.RutinaDao;
import com.svalero.resettrain.dao.UsuarioDao;
import com.svalero.resettrain.domain.Perfil;
import com.svalero.resettrain.domain.Rutina;
import com.svalero.resettrain.domain.Usuario;

@Database(entities = {Usuario.class, Rutina.class, Perfil.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UsuarioDao usuarioDao();
    public abstract RutinaDao rutinaDao();
    public abstract PerfilDao perfilDao();

}
