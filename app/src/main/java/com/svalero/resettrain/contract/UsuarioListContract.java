package com.svalero.resettrain.contract;

import com.svalero.resettrain.domain.Usuario;

import java.util.List;

public interface UsuarioListContract {

    interface Model{
        interface OnLoadUsuariosListener{
            void onLoadUsuariosSuccess(List<Usuario> usuarios);
            void onLoadUsuariosError(String message);
        }
        void loadAllUsuarios(OnLoadUsuariosListener listener);
    }

    interface View {
        void showUsuarios(List<Usuario> usuarios);
        void showError(String message);
    }

    interface Presenter {
        void loadAllUsuarios();
    }

}
