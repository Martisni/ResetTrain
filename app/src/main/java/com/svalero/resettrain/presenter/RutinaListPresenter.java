package com.svalero.resettrain.presenter;

import com.svalero.resettrain.contract.PerfilListContract;
import com.svalero.resettrain.contract.RutinaListContract;
import com.svalero.resettrain.domain.Perfil;
import com.svalero.resettrain.domain.Rutina;
import com.svalero.resettrain.model.PerfilListModel;
import com.svalero.resettrain.model.RutinaListModel;

import java.util.List;

public class RutinaListPresenter implements RutinaListContract.Presenter,
    RutinaListContract.Model.OnLoadRutinasListener {

    private RutinaListModel model;
    private  RutinaListContract.View view;

    public RutinaListPresenter(RutinaListContract.View view) {
        this.model = new RutinaListModel();
        this.view = view;
    }

    @Override
    public void onLoadRutinasSuccess(List<Rutina> rutinas) {
        view.showRutinas(rutinas);
    }

    @Override
    public void onLoadRutinasError(String message) {
        view.showError(message);
    }

    @Override
    public void loadAllRutinas() {
        model.loadAllRutinas(this);
    }
}
