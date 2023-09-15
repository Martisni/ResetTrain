package com.svalero.resettrain.presenter;

import com.svalero.resettrain.contract.PerfilListContract;
import com.svalero.resettrain.contract.UsuarioListContract;
import com.svalero.resettrain.domain.Perfil;
import com.svalero.resettrain.domain.Usuario;
import com.svalero.resettrain.model.PerfilListModel;
import com.svalero.resettrain.model.UsuarioListModel;

import java.util.List;

public class PerfilListPresenter implements PerfilListContract.Presenter,
    PerfilListContract.Model.OnLoadPerfilesListener {

    private PerfilListModel model;
    private  PerfilListContract.View view;

    public PerfilListPresenter(PerfilListContract.View view) {
        this.model = new PerfilListModel();
        this.view = view;
    }

    @Override
    public void loadAllPerfiles() {
        model.loadAllPerfiles(this);
    }

    @Override
    public void onLoadPerfilesSuccess(List<Perfil> perfiles) {
        view.showPerfiles(perfiles);
    }

    @Override
    public void onLoadPerfilesError(String message) {
        view.showError(message);
    }
}
