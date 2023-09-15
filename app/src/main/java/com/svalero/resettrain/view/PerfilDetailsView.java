package com.svalero.resettrain.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.svalero.resettrain.R;
import com.svalero.resettrain.contract.PerfilDetailsContract;
import com.svalero.resettrain.domain.Perfil;
import com.svalero.resettrain.presenter.PerfilDetailsPresenter;

public class PerfilDetailsView extends AppCompatActivity implements PerfilDetailsContract.View {

    private PerfilDetailsPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_details);

        presenter = new PerfilDetailsPresenter(this);
        Intent intent = getIntent();
        long id = intent.getLongExtra("id", 0);
        if(id == 0) return;

        presenter.loadPerfil(id);
    }

    @Override
    public void showPerfil(Perfil perfil) {

    }

    @Override
    public void showError(String message) {

    }
}