package com.svalero.resettrain.presenter;

import com.svalero.resettrain.contract.UsuarioListContract;
import com.svalero.resettrain.domain.Usuario;
import com.svalero.resettrain.model.UsuarioListModel;

import java.util.List;

public class UsuarioListPresenter implements UsuarioListContract.Presenter,
    UsuarioListContract.Model.OnLoadUsuariosListener {

    private UsuarioListModel model;
    private  UsuarioListContract.View view;

    public UsuarioListPresenter(UsuarioListContract.View view) {
        this.model = new UsuarioListModel();
        this.view = view;
    }

    @Override
    public void onLoadUsuariosSuccess(List<Usuario> usuarios) {
        view.showUsuarios(usuarios);

    }

    @Override
    public void onLoadUsuariosError(String message) {
        view.showError(message);
    }

    @Override
    public void loadAllUsuarios() {
        model.loadAllUsuarios(this);
    }
}
