package com.svalero.resettrain.contract;

import com.svalero.resettrain.domain.Perfil;

import java.util.List;

public interface PerfilListContract {

    interface Model{
        interface OnLoadPerfilesListener{
            void onLoadPerfilesSuccess(List<Perfil> perfiles);
            void onLoadPerfilesError(String message);
        }
        void loadAllPerfiles(OnLoadPerfilesListener listener);
    }

    interface View {
        void showPerfiles(List<Perfil> perfiles);
        void showError(String message);
    }

    interface Presenter {
        void loadAllPerfiles();
    }

}
