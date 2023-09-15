package com.svalero.resettrain.model;

import android.database.sqlite.SQLiteConstraintException;

import com.svalero.resettrain.api.ResetTrainApi;
import com.svalero.resettrain.api.ResetTrainApiInterface;
import com.svalero.resettrain.contract.PerfilRegisterContract;
import com.svalero.resettrain.contract.UsuarioRegisterContract;
import com.svalero.resettrain.domain.Perfil;
import com.svalero.resettrain.domain.Usuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilRegisterModel implements PerfilRegisterContract.Model {

    @Override
    public void registerPerfil(Perfil perfil, OnRegisterPerfilListener listener) {
        try {
            ResetTrainApiInterface resetTrainApi = ResetTrainApi.buildInstance();
            Call<Perfil> callPerfiles = resetTrainApi.addPerfil(perfil);
            callPerfiles.enqueue(new Callback<Perfil>() {
                @Override
                public void onResponse(Call<Perfil> call, Response<Perfil> response) {
                   Perfil perfil = response.body();
                    listener.onRegisterPerfilSuccess(perfil);
                }

                @Override
                public void onFailure(Call<Perfil> call, Throwable t) {
                    t.printStackTrace();
                    String message = "fallo";
                    listener.onRegisterPerfilError(message);
                }
            });
        } catch (SQLiteConstraintException sce) {
            sce.printStackTrace();
        }
    }
}
