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
import com.svalero.resettrain.domain.Rutina;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AddRutinaActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_add_rutina);
    }

    public void addRutina(View view){
        EditText etModalidad = findViewById(R.id.modalidadEditText);
        EditText etSeries = findViewById(R.id.seriesEditText);
        EditText etRepeticiones = findViewById(R.id.repeticionesEditText);
        EditText etDias = findViewById(R.id.diasEditText);
        EditText etDuracion = findViewById(R.id.duracionEditText);

        String modalidad = etModalidad.getText().toString();
        String series = etSeries.getText().toString();
        String repeticiones = etRepeticiones.getText().toString();
        String dias = etDias.getText().toString();
        String duracion = etDuracion.getText().toString();

        Rutina rutina = new Rutina(modalidad, series, repeticiones, dias, duracion);
        final AppDatabase database = Room.databaseBuilder(this, AppDatabase.class, "rutina")
                .allowMainThreadQueries().build();
        database.rutinaDao().insert(rutina);

        etModalidad.setText("");
        etSeries.setText("");
        etRepeticiones.setText("");
        etDias.setText("");
        etDuracion.setText("");
        etModalidad.requestFocus();
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