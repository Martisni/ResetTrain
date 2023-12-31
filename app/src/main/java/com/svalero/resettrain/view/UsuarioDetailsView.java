package com.svalero.resettrain.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.svalero.resettrain.R;
import com.svalero.resettrain.contract.UsuarioDetailsContract;
import com.svalero.resettrain.domain.Usuario;
import com.svalero.resettrain.presenter.UsuarioDetailsPresenter;

public class UsuarioDetailsView extends AppCompatActivity implements UsuarioDetailsContract.View {

    private UsuarioDetailsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_details);

        presenter = new UsuarioDetailsPresenter(this);

        Intent intent = getIntent();
        long id = intent.getLongExtra("id", 0);
        if(id == 0) return;

        presenter.loadUsuario(id);
    }

    @Override
    public void showUsuario(Usuario usuario) {

    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}