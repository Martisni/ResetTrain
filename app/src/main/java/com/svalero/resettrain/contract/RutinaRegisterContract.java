package com.svalero.resettrain.contract;

import com.svalero.resettrain.domain.Rutina;

public interface RutinaRegisterContract {

    interface Model{
       interface OnRegisterRutinaListener{
           void onRegisterRutinaSuccess(Rutina rutina);
           void onRegisterRutinaError(String message);
       }

       void registerRutina(Rutina rutina, OnRegisterRutinaListener listener);
    }

    interface View{
        void showMessage(String message);
        void resetForm();
        void showError(String errorMessage);
    }

    interface Presenter{
        void registerRutina(Rutina rutina);
    }

}
