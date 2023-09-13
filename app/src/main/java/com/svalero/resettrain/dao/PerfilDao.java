package com.svalero.resettrain.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.svalero.resettrain.domain.Perfil;

import java.util.List;

@Dao
public interface PerfilDao {

    @Query("SELECT * FROM perfil")
    List<Perfil> getAll();
    @Query("SELECT * FROM perfil WHERE ritmo = :ritmo")
    Perfil getByRitmo(String ritmo);
    @Query("DELETE FROM perfil WHERE ritmo = :ritmo")
    void deleteByRitmo(String ritmo);
    @Insert
    void insert(Perfil perfil);
    @Delete
    void delete(Perfil perfil);
    @Update
    void update(Perfil perfil);

}
