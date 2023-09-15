package com.svalero.resettrain.api;

import com.svalero.resettrain.domain.Perfil;
import com.svalero.resettrain.domain.Rutina;
import com.svalero.resettrain.domain.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ResetTrainApiInterface {

    @GET("/gym/users")
    Call<List<Usuario>> getUsuario();

    @GET("/gym/users/{id}")
    Call<Usuario> getUsuarioById(@Path("id") long id);

    @POST("/gym/usuario")
    Call<Usuario> addUsuario(@Body Usuario usuario);

    @DELETE("/gym/usuario/{id}")
    Call<Void> deleteUsuario(@Path("id") long id);

    @PUT("/gym/usuario/{id}")
    Call<Usuario> updateUsuario(@Path("id") long id, @Body Usuario usuario);

    @GET("/gym/perfils")
    Call<List<Perfil>> getPerfil();

    @GET("/gym/perfils/{id}")
    Call<Perfil> getPerfilById(@Path("id") long id);

    @POST("/gym/perfiles")
    Call<Perfil> addPerfil(@Body Perfil perfil);

    @DELETE("/gym/perfil/{id}")
    Call<Void> deletePerfil(@Path("id") long id);

    @PUT("/gym/perfil/{id}")
    Call<Usuario> updatePerfil(@Path("id") long id, @Body Perfil perfil);

    @GET("/gym/rutins")
    Call<List<Rutina>> getRutina();

    @GET("/gym/rutins/{id}")
    Call<Rutina> getRutinaById(@Path("id") long id);

    @POST("/gym/rutinas")
    Call<Rutina> addRutina(@Body Rutina rutina);

    @DELETE("/gym/rutina/{id}")
    Call<Void> deleteRutina(@Path("id") long id);

    @PUT("/gym/rutinas/{id}")
    Call<Usuario> updateRutina(@Path("id") long id, @Body Rutina rutina);




}