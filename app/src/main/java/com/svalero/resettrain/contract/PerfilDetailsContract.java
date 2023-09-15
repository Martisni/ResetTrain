package com.svalero.resettrain.contract;

import com.svalero.resettrain.domain.Perfil;

public interface PerfilDetailsContract {

    interface Model{

        interface OnLoadPerfilListener{

            void onLoadPerfilSuccess(Perfil perfil);
            void onLoadPerfilError(String message);
        }

        void loadPerfilById(long id, OnLoadPerfilListener listener);

    }

    interface View {
        void showPerfil(Perfil perfil);
        void showError(String message);
    }

    interface Presenter {
        void loadPerfil(long id);
    }

}
