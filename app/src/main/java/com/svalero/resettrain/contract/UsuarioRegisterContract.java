package com.svalero.resettrain.contract;

import com.svalero.resettrain.domain.Usuario;

public interface UsuarioRegisterContract {

    interface Model{
       interface OnRegisterUsuarioListener{
           void onRegisterUsuarioSuccess(Usuario usuario);
           void onRegisterUsuarioError(String message);
       }

       void registerUsuario(Usuario usuario, OnRegisterUsuarioListener listener);
    }

    interface View{
        void showMessage(String message);
        void resetForm();
        void showError(String errorMessage);
    }

    interface Presenter{
        void registerUsuario(Usuario usuario);
    }

}
