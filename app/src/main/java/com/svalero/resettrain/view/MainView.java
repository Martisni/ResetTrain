package com.svalero.resettrain.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.svalero.resettrain.R;
import com.svalero.resettrain.domain.LanguageItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class MainView extends AppCompatActivity {

    ViewFlipper v_flipper;

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
        setContentView(R.layout.activity_main);

        int images[] = {R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4};

        v_flipper = findViewById(R.id.v_flipper);

        for (int image: images){
            flipperImages(image);
        }

        Button addUsuario = findViewById(R.id.add_usuario_button_menu);
        addUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), UsuarioRegisterView.class);
                startActivityForResult(intent, 0);
            }
        });

        Button viewUsuario = findViewById(R.id.view_usuario_button);
        viewUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), UsuarioListView.class);
                startActivityForResult(intent, 0);
            }
        });

        Button addRutina = findViewById(R.id.add_rutina_button_menu);
        addRutina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RutinaRegisterView.class);
                startActivityForResult(intent, 0);
            }
        });

        Button viewRutina = findViewById(R.id.view_rutina_button);
        viewRutina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RutinaListView.class);
                startActivityForResult(intent, 0);
            }
        });

        Button addPerfil = findViewById(R.id.add_perfil_button_menu);
        addPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PerfilRegisterView.class);
                startActivityForResult(intent, 0);
            }
        });

        Button viewPerfil = findViewById(R.id.view_perfil_button);
        viewPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PerfilListView.class);
                startActivityForResult(intent, 0);
            }
        });
    }
    public void flipperImages(int image){
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(3000);
        v_flipper.setAutoStart(true);

        v_flipper.setInAnimation(this, android.R.anim.slide_out_right);
        v_flipper.setOutAnimation(this, android.R.anim.slide_in_left);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(item.getItemId() == R.id.action_change_language){
            showLanguageDialog();
            return true;
        } else if (item.getItemId() == R.id.mapView) {
            Intent intent = new Intent(this, MapsView.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.activity_view_rutina) {
            Intent intent = new Intent(this, RutinaListFavoritoView.class);
            startActivity(intent);
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