package com.svalero.resettrain.model;

import android.content.Context;

import com.svalero.resettrain.api.ResetTrainApi;
import com.svalero.resettrain.api.ResetTrainApiInterface;
import com.svalero.resettrain.contract.PerfilDetailsContract;
import com.svalero.resettrain.domain.Perfil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilDetailsModel implements PerfilDetailsContract.Model {

    private Context context;

    public PerfilDetailsModel(Context context) {
        this.context = context;
    }

    @Override
    public void loadPerfilById(long id, OnLoadPerfilListener listener) {
        ResetTrainApiInterface resetTrainApi = ResetTrainApi.buildInstance();
        Call<Perfil> callPerfiles = resetTrainApi.getPerfilById(id);
        callPerfiles.enqueue(new Callback<Perfil>() {
            @Override
            public void onResponse(Call<Perfil> call, Response<Perfil> response) {
                Perfil perfil = response.body();
                listener.onLoadPerfilSuccess(perfil);
            }

            @Override
            public void onFailure(Call<Perfil> call, Throwable t) {
                t.printStackTrace();
                String message = "fallo";
                listener.onLoadPerfilError(message);
            }
        });
    }
}