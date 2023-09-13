package com.svalero.resettrain;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.svalero.resettrain.database.AppDatabase;
import com.svalero.resettrain.domain.LanguageItem;
import com.svalero.resettrain.domain.Perfil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AddPerfilActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_add_perfil);
    }
    public void addPerfil(View view) {
        EditText etRitmo = findViewById(R.id.ritmoEditText);
        EditText etMedidas = findViewById(R.id.medidasEditText);
        EditText etPeso = findViewById(R.id.pesoEditText);
        EditText etFecha = findViewById(R.id.fechaEditText);

        String ritmo = etRitmo.getText().toString();
        String medidas = etMedidas.getText().toString();
        String peso = etPeso.getText().toString();
        String fecha = etFecha.getText().toString();

        Perfil perfil = new Perfil(ritmo, medidas, peso, fecha);
        final AppDatabase database = Room.databaseBuilder(this, AppDatabase.class, "perfil")
                .allowMainThreadQueries().build();
        database.perfilDao().insert(perfil);

        etRitmo.setText("");
        etMedidas.setText("");
        etPeso.setText("");
        etFecha.setText("");
        etRitmo.requestFocus();
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

}