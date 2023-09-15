package com.svalero.resettrain.presenter;

import com.svalero.resettrain.contract.PerfilRegisterContract;
import com.svalero.resettrain.domain.Perfil;
import com.svalero.resettrain.model.PerfilRegisterModel;
import com.svalero.resettrain.view.PerfilRegisterView;

public class PerfilRegisterPresenter implements PerfilRegisterContract.Presenter,
    PerfilRegisterContract.Model.OnRegisterPerfilListener {

    private PerfilRegisterModel model;
    private PerfilRegisterView view;

    public PerfilRegisterPresenter(PerfilRegisterView view) {
        model = new PerfilRegisterModel();
        this.view = view;
    }

    @Override
    public void onRegisterPerfilSuccess(Perfil perfil) {
        view.showMessage("El perfil " + perfil.getId() + " se ha registrado correctamente");
    }

    @Override
    public void onRegisterPerfilError(String message) {
        view.showError("Error al registrar el perfil");
    }

    @Override
    public void registerPerfil(Perfil perfil) {
        model.registerPerfil(perfil,this);
    }
}
