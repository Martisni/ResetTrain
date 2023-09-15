package com.svalero.resettrain.model;

import com.svalero.resettrain.api.ResetTrainApi;
import com.svalero.resettrain.api.ResetTrainApiInterface;
import com.svalero.resettrain.contract.PerfilListContract;
import com.svalero.resettrain.contract.UsuarioListContract;
import com.svalero.resettrain.domain.Perfil;
import com.svalero.resettrain.domain.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilListModel implements PerfilListContract.Model {

    @Override
    public void loadAllPerfiles(OnLoadPerfilesListener listener) {
        ResetTrainApiInterface resetTrainApi = ResetTrainApi.buildInstance();
        Call<List<Perfil>> callPerfiles = resetTrainApi.getPerfil();
        callPerfiles.enqueue(new Callback<List<Perfil>>() {
            @Override
            public void onResponse(Call<List<Perfil>> call, Response<List<Perfil>> response) {
                List<Perfil> perfiles = response.body();
                listener.onLoadPerfilesSuccess(perfiles);
            }

            @Override
            public void onFailure(Call<List<Perfil>> call, Throwable t) {
                t.printStackTrace();
                String message = "fallo";
                listener.onLoadPerfilesError(message);
            }
        });
    }
}
