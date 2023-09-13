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
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.svalero.resettrain.R;
import com.svalero.resettrain.contract.UsuarioRegisterContract;
import com.svalero.resettrain.database.AppDatabase;
import com.svalero.resettrain.domain.LanguageItem;
import com.svalero.resettrain.domain.Usuario;
import com.svalero.resettrain.presenter.UsuarioRegisterPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class UsuarioRegisterView extends AppCompatActivity implements UsuarioRegisterContract.View {

    private Usuario usuario;
    private UsuarioRegisterPresenter presenter;


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
        setContentView(R.layout.activity_add_usuario);
    }

    public void addUsuario(View view) {
        EditText etName = findViewById(R.id.nameEditText);
        EditText etSurname = findViewById(R.id.apellidosEditText);
        EditText etObjetive = findViewById(R.id.descriptionEditText);
        EditText etPassword = findViewById(R.id.contrasenaEditText);
        EditText etCumple = findViewById(R.id.fechaEditText);
        EditText etEmail = findViewById(R.id.emailEditText);
        EditText etPhone = findViewById(R.id.movilEditText);

        String name = etName.getText().toString();
        String surname = etSurname.getText().toString();
        String objetive = etObjetive.getText().toString();
        String password = etPassword.getText().toString();
        String birthdate = etCumple.getText().toString();
        String email = etEmail.getText().toString();
        String phone = etPhone.getText().toString();

        Usuario usuario = new Usuario(name, surname, objetive, password, birthdate, email, phone);
        presenter.registerUsuario(usuario);

        etName.setText("");
        etSurname.setText("");
        etObjetive.setText("");
        etPassword.setText("");
        etCumple.setText("");
        etEmail.setText("");
        etPhone.setText("");
        etName.requestFocus();
    }

    public void goBackButton(View view) {
        onBackPressed();
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showError(String errorMessage) {

    }

    @Override
    public void resetForm() {

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