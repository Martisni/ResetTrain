package com.svalero.resettrain.model;

import com.svalero.resettrain.api.ResetTrainApi;
import com.svalero.resettrain.api.ResetTrainApiInterface;
import com.svalero.resettrain.contract.UsuarioListContract;
import com.svalero.resettrain.domain.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsuarioListModel implements UsuarioListContract.Model {


    @Override
    public void loadAllUsuarios(OnLoadUsuariosListener listener) {
        ResetTrainApiInterface resetTrainApi = ResetTrainApi.buildInstance();
        Call<List<Usuario>> callUsuarios = resetTrainApi.getUsuario();
        callUsuarios.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                List<Usuario> usuarios = response.body();
                listener.onLoadUsuariosSuccess(usuarios);
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                t.printStackTrace();
                String message = "fallo";
                listener.onLoadUsuariosError(message);
            }
        });
    }
}
