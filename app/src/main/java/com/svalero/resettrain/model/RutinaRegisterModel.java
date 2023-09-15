package com.svalero.resettrain.model;

import android.database.sqlite.SQLiteConstraintException;

import com.svalero.resettrain.api.ResetTrainApi;
import com.svalero.resettrain.api.ResetTrainApiInterface;
import com.svalero.resettrain.contract.RutinaRegisterContract;
import com.svalero.resettrain.contract.UsuarioRegisterContract;
import com.svalero.resettrain.domain.Rutina;
import com.svalero.resettrain.domain.Usuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RutinaRegisterModel implements RutinaRegisterContract.Model {

    @Override
    public void registerRutina(Rutina rutina, OnRegisterRutinaListener listener) {
        try {
            ResetTrainApiInterface resetTrainApi = ResetTrainApi.buildInstance();
            Call<Rutina> callRutinas = resetTrainApi.addRutina(rutina);
            callRutinas.enqueue(new Callback<Rutina>() {
                @Override
                public void onResponse(Call<Rutina> call, Response<Rutina> response) {
                    Rutina rutina = response.body();
                    listener.onRegisterRutinaSuccess(rutina);
                }

                @Override
                public void onFailure(Call<Rutina> call, Throwable t) {
                    t.printStackTrace();
                    String message = "fallo";
                    listener.onRegisterRutinaError(message);
                }
            });
        } catch (SQLiteConstraintException sce) {
            sce.printStackTrace();
        }
    }
}
