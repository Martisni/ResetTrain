package com.svalero.resettrain.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.svalero.resettrain.domain.Rutina;

import java.util.List;

@Dao
public interface RutinaDao {

    @Query("SELECT * FROM rutina")
    List<Rutina> getAll();
    @Query("SELECT * FROM rutina WHERE modalidad = :modalidad")
    Rutina getByModalidad(String modalidad);
    @Query("DELETE FROM rutina WHERE modalidad = :modalidad")
    void deleteByModalidad(String modalidad);
    @Insert
    void insert(Rutina rutina);
    @Delete
    void delete(Rutina rutina);
    @Update
    void update(Rutina rutina);

}
