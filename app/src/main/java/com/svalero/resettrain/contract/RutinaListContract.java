package com.svalero.resettrain.contract;

import com.svalero.resettrain.domain.Perfil;
import com.svalero.resettrain.domain.Rutina;

import java.util.List;

public interface RutinaListContract {

    interface Model{
        interface OnLoadRutinasListener {
            void onLoadRutinasSuccess(List<Rutina> rutinas);
            void onLoadRutinasError(String message);
        }
        void loadAllRutinas(OnLoadRutinasListener listener);
    }

    interface View {
        void showRutinas(List<Rutina> rutinas);
        void showError(String message);
    }

    interface Presenter {
        void loadAllRutinas();
    }

}
