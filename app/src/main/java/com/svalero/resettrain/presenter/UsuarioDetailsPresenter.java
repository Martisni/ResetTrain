package com.svalero.resettrain.presenter;

import com.svalero.resettrain.contract.UsuarioDetailsContract;
import com.svalero.resettrain.contract.UsuarioListContract;
import com.svalero.resettrain.domain.Usuario;
import com.svalero.resettrain.model.UsuarioDetailsModel;
import com.svalero.resettrain.model.UsuarioListModel;
import android.content.Context;
import java.util.List;

public class UsuarioDetailsPresenter implements UsuarioDetailsContract.Presenter,
    UsuarioDetailsContract.Model.OnLoadUsuarioListener {

    private UsuarioDetailsModel model;
    private UsuarioDetailsContract.View view;
    private Context context;

    public UsuarioDetailsPresenter(UsuarioDetailsContract.View view) {
        this.model = new UsuarioDetailsModel(context);
        this.view = view;
    }


    @Override
    public void onLoadUsuarioSuccess(Usuario usuario) {
        view.showUsuario(usuario);
    }

    @Override
    public void onLoadUsuarioError(String message) {
        view.showError(message);
    }

    @Override
    public void loadUsuario(long id) {
        model.getUsuario(id);
    }
}
