package com.svalero.resettrain.presenter;

import android.content.Context;

import com.svalero.resettrain.contract.PerfilDetailsContract;
import com.svalero.resettrain.domain.Perfil;
import com.svalero.resettrain.model.PerfilDetailsModel;


public class PerfilDetailsPresenter implements PerfilDetailsContract.Presenter,
    PerfilDetailsContract.Model.OnLoadPerfilListener {

    private PerfilDetailsModel model;
    private PerfilDetailsContract.View view;
    private Context context;

    public PerfilDetailsPresenter(PerfilDetailsContract.View view) {
        this.model = new PerfilDetailsModel(context);
        this.view = view;
    }

    @Override
    public void loadPerfil(long id) {
        model.loadPerfilById(id,this);
    }

    @Override
    public void onLoadPerfilSuccess(Perfil perfil) {
        view.showPerfil(perfil);
    }

    @Override
    public void onLoadPerfilError(String message) {
        view.showError(message);
    }
}
