package com.svalero.resettrain.model;

import com.svalero.resettrain.api.ResetTrainApi;
import com.svalero.resettrain.api.ResetTrainApiInterface;
import com.svalero.resettrain.contract.PerfilListContract;
import com.svalero.resettrain.contract.RutinaListContract;
import com.svalero.resettrain.domain.Perfil;
import com.svalero.resettrain.domain.Rutina;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RutinaListModel implements RutinaListContract.Model {

    @Override
    public void loadAllRutinas(OnLoadRutinasListener listener) {
        ResetTrainApiInterface resetTrainApi = ResetTrainApi.buildInstance();
        Call<List<Rutina>> callRutinas = resetTrainApi.getRutina();
        callRutinas.enqueue(new Callback<List<Rutina>>() {
            @Override
            public void onResponse(Call<List<Rutina>> call, Response<List<Rutina>> response) {
                List<Rutina> rutinas = response.body();
                listener.onLoadRutinasSuccess(rutinas);
            }

            @Override
            public void onFailure(Call<List<Rutina>> call, Throwable t) {
                t.printStackTrace();
                String message = "fallo";
                listener.onLoadRutinasError(message);
            }
        });
    }
}
