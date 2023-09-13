package com.svalero.resettrain.contract;

import com.svalero.resettrain.domain.Usuario;

public interface UsuarioDetailsContract {

    interface Model{
        Usuario getUsuario(long id);

        interface OnLoadUsuarioListener{

            void onLoadUsuarioSuccess(Usuario usuarios);
            void onLoadUsuarioError(String message);
        }

        void loadUsuarioId(long id, OnLoadUsuarioListener listener);

    }

    interface View {
        void showUsuario(Usuario usuario);
        void showError(String message);
    }

    interface Presenter {
        void loadUsuario(long id);
    }

}
