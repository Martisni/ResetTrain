package com.svalero.resettrain.model;

import android.content.Context;

import com.svalero.resettrain.api.ResetTrainApi;
import com.svalero.resettrain.api.ResetTrainApiInterface;
import com.svalero.resettrain.contract.UsuarioDetailsContract;
import com.svalero.resettrain.contract.UsuarioListContract;
import com.svalero.resettrain.domain.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsuarioDetailsModel implements UsuarioDetailsContract.Model {

    private Context context;

    public UsuarioDetailsModel(Context context) {
        this.context = context;
    }

    @Override
    public void loadUsuarioId(long id, OnLoadUsuarioListener listener) {
        ResetTrainApiInterface resetTrainApi = ResetTrainApi.buildInstance();
        Call<Usuario> callUsuarios = resetTrainApi.getUsuarioId(id);
        callUsuarios.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                Usuario usuarios = response.body();
                listener.onLoadUsuarioSuccess(usuarios);
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                t.printStackTrace();
                String message = "fallo";
                listener.onLoadUsuarioError(message);
            }
        });
    }

    @Override
    public Usuario getUsuario(long id) {
        return null;
    }

}
