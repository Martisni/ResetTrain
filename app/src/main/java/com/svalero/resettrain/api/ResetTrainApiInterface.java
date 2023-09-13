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

    @GET("/gym/usuarios")
    Call<List<Usuario>> getUsuario();

    @POST("/gym/usuarios")
    Call<Usuario> addUsuario(@Body Usuario usuario);

    @DELETE("/gym/usuarios")
    Call<Void> deleteUsuario(@Path("id") long id);

    @PUT("/gym/usuarios")
    Call<Usuario> updateUsuario(@Path("id") long id, @Body Usuario usuario);

    @POST("/gym/usuarios")
    Call<Usuario> addUsuario();

    @GET("/gym/usuarios/{id}")
    Call<Usuario> getUsuarioId(@Path("id") long id);

    @GET("/gym/perfiles/{id}")
    Call<Perfil> getPerfilId(@Path("id") long id);

    @GET("/gym/rutinas/{id}")
    Call<Rutina> getRutinaId(@Path("id") long id);



}
