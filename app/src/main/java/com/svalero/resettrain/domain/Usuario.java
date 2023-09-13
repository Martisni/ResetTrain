package com.svalero.resettrain.domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Usuario {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo
    private String name;
    @ColumnInfo
    private String surname;
    @ColumnInfo
    private String objective;
    @ColumnInfo
    private String password;
    @ColumnInfo
    private String birthdate;
    @ColumnInfo
    private String email;
    @ColumnInfo
    private String phone;
    @ColumnInfo
    private boolean lesion;

    public Usuario() {}
    public Usuario(String name, String surname, String objective, String password, String birthdate, String email, String phone){
        this.name = name;
        this.surname = surname;
        this.objective = objective;
        this.password = password;
        this.birthdate = birthdate;
        this.email = email;
        this.phone = phone;
        this.lesion = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isLesion() {
        return lesion;
    }

    public void setLesion(boolean lesion) {
        this.lesion = lesion;
    }
}