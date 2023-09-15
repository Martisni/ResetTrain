package com.svalero.resettrain.contract;

import com.svalero.resettrain.domain.Usuario;

import java.util.List;

public interface UsuarioDetailsContract {

    interface Model{
        interface OnLoadUsuarioListener{

            void onLoadUsuarioSuccess(Usuario usuarios);
            void onLoadUsuarioError(String message);
        }

        void loadUsuarioById(long id, OnLoadUsuarioListener listener);

    }

    interface View {
        void showUsuario(Usuario usuario);
        void showError(String message);
    }

    interface Presenter {
        void loadUsuario(long id);
    }

}
