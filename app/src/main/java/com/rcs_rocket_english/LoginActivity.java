package com.rcs_rocket_english;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextName, editTextAlias;
    private Spinner editTextCountry;  // Cambiar EditText a Spinner
    private Button btnEmpezar;

    // Definir la lista de países
    String[] countries = {"Selecciona un país", "México", "Estados Unidos", "España", "Argentina", "Colombia"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_form);

        // Inicializar los EditText, Spinner y el Button
        editTextName = findViewById(R.id.editTextName);
        editTextAlias = findViewById(R.id.editTextAlias);
        editTextCountry = findViewById(R.id.editTextCountry);  // Spinner
        btnEmpezar = findViewById(R.id.btnStart);

        // Crear un ArrayAdapter con la lista de países
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, countries);

        // Especificar el layout para los elementos del Spinner
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Asignar el adaptador al Spinner
        editTextCountry.setAdapter(adapter);

        // Configurar el listener para el botón "Empezar"
        btnEmpezar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener los valores de los EditText y Spinner
                String name = editTextName.getText().toString().trim();
                String alias = editTextAlias.getText().toString().trim();
                String country = editTextCountry.getSelectedItem().toString();

                // Validar que los campos no estén vacíos
                if (name.isEmpty() || alias.isEmpty() || country.equals("Selecciona un país")) {
                    // Manejar datos vacíos
                    Log.d("LoginActivity", "Por favor, completa todos los campos.");
                } else {
                    // Mostrar los datos en consola
                    Log.d("LoginActivity", "Nombre: " + name);
                    Log.d("LoginActivity", "Alias: " + alias);
                    Log.d("LoginActivity", "País: " + country);

                    DataBase db = new DataBase(LoginActivity.this);

                    // Registrar un nuevo usuario
                    db.registerUser(name, alias, country);


                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("alias", alias);
                    intent.putExtra("country", country);
                    startActivity(intent);
                    // Aquí puedes continuar con la lógica de tu aplicación
                    finish();
                }
            }
        });
    }
}
