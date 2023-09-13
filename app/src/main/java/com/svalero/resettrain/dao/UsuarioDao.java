package com.svalero.resettrain.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.svalero.resettrain.domain.Usuario;

import java.util.List;

@Dao
public interface UsuarioDao {
    @Query("SELECT * FROM usuario")
    List<Usuario> getAll();
    @Query("SELECT * FROM usuario WHERE name = :name")
    Usuario getByName(String name);
    @Query("DELETE FROM usuario WHERE name = :name")
    void deleteByName(String name);
    @Insert
    void insert(Usuario usuario);
    @Delete
    void delete(Usuario usuario);

    @Update
    void update(Usuario usuario);
}
