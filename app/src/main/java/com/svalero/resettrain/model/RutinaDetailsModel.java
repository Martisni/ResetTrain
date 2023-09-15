package com.svalero.resettrain.model;

import android.content.Context;

import com.svalero.resettrain.api.ResetTrainApi;
import com.svalero.resettrain.api.ResetTrainApiInterface;
import com.svalero.resettrain.contract.PerfilDetailsContract;
import com.svalero.resettrain.contract.RutinaDetailsContract;
import com.svalero.resettrain.domain.Perfil;
import com.svalero.resettrain.domain.Rutina;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RutinaDetailsModel implements RutinaDetailsContract.Model {

    private Context context;

    public RutinaDetailsModel(Context context) {
        this.context = context;
    }

    @Override
    public void loadRutinaById(long id, OnLoadRutinaListener listener) {
        ResetTrainApiInterface resetTrainApi = ResetTrainApi.buildInstance();
        Call<Rutina> callRutinas = resetTrainApi.getRutinaById(id);
        callRutinas.enqueue(new Callback<Rutina>() {
            @Override
            public void onResponse(Call<Rutina> call, Response<Rutina> response) {
                Rutina rutina = response.body();
                listener.onLoadRutinaSuccess(rutina);
            }

            @Override
            public void onFailure(Call<Rutina> call, Throwable t) {
                t.printStackTrace();
                String message = "fallo";
                listener.onLoadRutinaError(message);
            }
        });
    }
}