package com.svalero.resettrain.contract;

import com.svalero.resettrain.domain.Rutina;

public interface RutinaDetailsContract {

    interface Model{

        interface OnLoadRutinaListener{

            void onLoadRutinaSuccess(Rutina rutina);
            void onLoadRutinaError(String message);
        }

        void loadRutinaById(long id, OnLoadRutinaListener listener);

    }

    interface View {
        void showRutina(Rutina rutina);
        void showError(String message);
    }

    interface Presenter {
        void loadRutina(long id);
    }

}
