package com.svalero.resettrain.contract;

import com.svalero.resettrain.domain.Perfil;

public interface PerfilRegisterContract {

    interface Model{
       interface OnRegisterPerfilListener{
           void onRegisterPerfilSuccess(Perfil perfil);
           void onRegisterPerfilError(String message);
       }

       void registerPerfil(Perfil perfil, OnRegisterPerfilListener listener);
    }

    interface View{
        void showMessage(String message);
        void resetForm();
        void showError(String errorMessage);
    }

    interface Presenter{
        void registerPerfil(Perfil perfil);
    }

}
