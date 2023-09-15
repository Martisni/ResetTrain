package com.svalero.resettrain.presenter;

import com.svalero.resettrain.contract.UsuarioRegisterContract;
import com.svalero.resettrain.domain.Usuario;
import com.svalero.resettrain.model.UsuarioRegisterModel;
import com.svalero.resettrain.view.UsuarioRegisterView;

public class UsuarioRegisterPresenter implements UsuarioRegisterContract.Presenter,
    UsuarioRegisterContract.Model.OnRegisterUsuarioListener {

    private UsuarioRegisterModel model;
    private UsuarioRegisterView view;

    public UsuarioRegisterPresenter(UsuarioRegisterView view) {
        model = new UsuarioRegisterModel();
        this.view = view;
    }

    @Override
    public void onRegisterUsuarioSuccess(Usuario usuario) {
        view.showMessage("El usuario " + usuario.getId() + " se ha registrado correctamente");
    }

    @Override
    public void onRegisterUsuarioError(String message) {
        view.showError("Error al registrar usuario");
    }

    @Override
    public void registerUsuario(Usuario usuario) {
        model.registerUsuario(usuario, this);
    }
}
