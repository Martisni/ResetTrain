package com.svalero.resettrain.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.resettrain.R;
import com.svalero.resettrain.adapters.PerfilAdapter;
import com.svalero.resettrain.contract.PerfilListContract;
import com.svalero.resettrain.domain.LanguageItem;
import com.svalero.resettrain.domain.Perfil;
import com.svalero.resettrain.presenter.PerfilListPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PerfilListView extends AppCompatActivity implements PerfilListContract.View {

    private List<Perfil> perfilList;
    private PerfilAdapter adapter;
    private PerfilListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Obtener el código del idioma de las preferencias compartidas
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String languageCode = sharedPreferences.getString("language_code", "en"); // Valor predeterminado: inglés

        // Establecer el idioma en tiempo de ejecución
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_perfil);

        presenter = new PerfilListPresenter(this);
        perfilList = new ArrayList<>();
        initializeRecyclerView();
    }

    private void initializeRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.perfil_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new PerfilAdapter(this, perfilList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume(){
        super.onResume();
        presenter.loadAllPerfiles();
    }

    public void goBackButton(View view) {
        onBackPressed();
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(item.getItemId() == R.id.action_change_language){
            showLanguageDialog();
            return true;
        }

        return false;
    }

    private void showLanguageDialog() {
        final List<LanguageItem> languageList = new ArrayList<>();
        // Agregar los idiomas disponibles a la lista
        languageList.add(new LanguageItem("English", "en"));
        languageList.add(new LanguageItem("Español", "es"));
        // Agregar más idiomas según sea necesario

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Language");

        String[] languageNames = new String[languageList.size()];
        for (int i = 0; i < languageList.size(); i++) {
            languageNames[i] = languageList.get(i).getName();
        }

        builder.setItems(languageNames, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String selectedLanguageCode = languageList.get(which).getCode();
                changeLanguage(selectedLanguageCode);
            }
        });

        builder.show();
    }

    private void changeLanguage(String languageCode) {
        // Guardar el código del idioma en las preferencias compartidas
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("language_code", languageCode);
        editor.apply();

        // Recrear la actividad principal
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }


    @Override
    public void showPerfiles(List<Perfil> perfiles) {
        perfilList.clear();
        perfilList.addAll(perfiles);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void showError(String message) {

    }
}