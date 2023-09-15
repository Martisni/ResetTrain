package com.svalero.resettrain.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.svalero.resettrain.R;
import com.svalero.resettrain.contract.RutinaDetailsContract;
import com.svalero.resettrain.domain.Rutina;
import com.svalero.resettrain.presenter.RutinaDetailsPresenter;
import com.svalero.resettrain.presenter.RutinaListPresenter;

public class RutinaDetailsView extends AppCompatActivity implements RutinaDetailsContract.View {

    private RutinaDetailsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rutina_details);

        presenter = new RutinaDetailsPresenter(this);

        Intent intent = getIntent();
        long id = intent.getLongExtra("id", 0);
        if(id == 0) return;

    }

    @Override
    public void showRutina(Rutina rutina) {

    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}