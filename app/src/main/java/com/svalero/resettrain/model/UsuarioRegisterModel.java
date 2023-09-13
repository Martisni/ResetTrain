package com.svalero.resettrain.model;

import android.database.sqlite.SQLiteConstraintException;

import com.svalero.resettrain.api.ResetTrainApi;
import com.svalero.resettrain.api.ResetTrainApiInterface;
import com.svalero.resettrain.contract.UsuarioRegisterContract;
import com.svalero.resettrain.domain.Usuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsuarioRegisterModel implements UsuarioRegisterContract.Model {


    @Override
    public void registerUsuario(Usuario usuario, OnRegisterUsuarioListener listener) {
        try {
            ResetTrainApiInterface resetTrainApi = ResetTrainApi.buildInstance();
            Call<Usuario> callUsuarios = resetTrainApi.addUsuario(usuario);
            callUsuarios.enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                    Usuario usuario = response.body();
                    listener.onRegisterSuccess(usuario);
                }

                @Override
                public void onFailure(Call<Usuario> call, Throwable t) {
                    t.printStackTrace();
                    String message = "fallo";
                    listener.onRegisterError(message);
                }
            });
        } catch (SQLiteConstraintException sce) {
            sce.printStackTrace();
        }
    }
}
