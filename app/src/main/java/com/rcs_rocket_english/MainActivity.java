package com.rcs_rocket_english;

import static androidx.core.content.ContextCompat.startActivity;

import android.os.Bundle;
import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.rcs_rocket_english.controls.NavigationMenu;
import com.rcs_rocket_english.controls.NavBarMenu;

public class MainActivity extends AppCompatActivity
        implements NavBarMenu.OnNavItemSelectedListener, NavigationMenu.OnNavItemSelectedListener {

    private NavBarMenu navBarMenu;
    private NavigationMenu navigationMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.galaxies);  // Asegúrate de usar tu layout aquí

        navBarMenu = findViewById(R.id.navbarMenu);
        navBarMenu.cambiarImagenPerfil(1); // Cambia la imagen a btn_profile_estado_1

        //inicializar datos de lightning y hearts
        navBarMenu.setTextLightning("5");
        navBarMenu.setTextLightning("10");


        // Inicializa los controles
        navBarMenu = findViewById(R.id.navbarMenu);
        navigationMenu = findViewById(R.id.navbarControl);

        // Registra el listener para ambos controles
        navBarMenu.setOnNavItemSelectedListener(this);
        navigationMenu.setOnNavItemSelectedListener(this);
    }

    @Override
    public void onNavItemSelected(int itemId) {
        if (itemId == R.id.btnChallenge) {
            openActivity(ProfileActivity.class);
        } else if (itemId == R.id.btnHome) {
            // Descomenta para abrir la actividad Home
            // openActivity(HomeActivity.class);
        } else if (itemId == R.id.btnSettings) {
            // Descomenta para abrir la actividad Settings
            // openActivity(SettingsActivity.class);
        } else if (itemId == R.id.btnProfile) {
            openActivity(ProfileActivity.class);
        } else {
            // Agrega un bloque else para el caso desconocido
            Toast.makeText(this, "Opción desconocida", Toast.LENGTH_SHORT).show();
        }
    }

    // Método para abrir actividades
    private void openActivity(Class<?> activityClass) {
        Intent intent = new Intent(MainActivity.this, activityClass);
        startActivity(intent);
    }
}
