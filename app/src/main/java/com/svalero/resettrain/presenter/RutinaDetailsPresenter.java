package com.svalero.resettrain.presenter;

import android.content.Context;

import com.svalero.resettrain.contract.PerfilDetailsContract;
import com.svalero.resettrain.contract.RutinaDetailsContract;
import com.svalero.resettrain.domain.Perfil;
import com.svalero.resettrain.domain.Rutina;
import com.svalero.resettrain.model.PerfilDetailsModel;
import com.svalero.resettrain.model.RutinaDetailsModel;


public class RutinaDetailsPresenter implements RutinaDetailsContract.Presenter,
    RutinaDetailsContract.Model.OnLoadRutinaListener {

    private RutinaDetailsModel model;
    private RutinaDetailsContract.View view;
    private Context context;

    public RutinaDetailsPresenter(RutinaDetailsContract.View view) {
        this.model = new RutinaDetailsModel(context);
        this.view = view;
    }

    @Override
    public void onLoadRutinaSuccess(Rutina rutina) {
        view.showRutina(rutina);
    }

    @Override
    public void onLoadRutinaError(String message) {
        view.showError(message);
    }

    @Override
    public void loadRutina(long id) {
        model.loadRutinaById(id, this);
    }
}
