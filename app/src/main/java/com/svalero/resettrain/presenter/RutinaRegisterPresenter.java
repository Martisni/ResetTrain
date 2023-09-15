package com.svalero.resettrain.presenter;

import com.svalero.resettrain.contract.RutinaRegisterContract;
import com.svalero.resettrain.contract.UsuarioRegisterContract;
import com.svalero.resettrain.domain.Rutina;
import com.svalero.resettrain.domain.Usuario;
import com.svalero.resettrain.model.RutinaRegisterModel;
import com.svalero.resettrain.model.UsuarioRegisterModel;
import com.svalero.resettrain.view.RutinaRegisterView;
import com.svalero.resettrain.view.UsuarioRegisterView;

public class RutinaRegisterPresenter implements RutinaRegisterContract.Presenter,
    RutinaRegisterContract.Model.OnRegisterRutinaListener {

    private RutinaRegisterModel model;
    private RutinaRegisterView view;

    public RutinaRegisterPresenter(RutinaRegisterView view) {
        model = new RutinaRegisterModel();
        this.view = view;
    }

    @Override
    public void onRegisterRutinaSuccess(Rutina rutina) {
        view.showMessage("El usuario " + rutina.getId() + " se ha registrado correctamente");
    }

    @Override
    public void onRegisterRutinaError(String message) {
        view.showError("Error al registrar usuario");
    }

    @Override
    public void registerRutina(Rutina rutina) {
        model.registerRutina(rutina, this);
    }
}
