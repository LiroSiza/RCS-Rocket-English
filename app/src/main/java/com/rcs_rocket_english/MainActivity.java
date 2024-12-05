package com.rcs_rocket_english;

import static androidx.core.content.ContextCompat.startActivity;

import android.os.Bundle;
import android.content.Intent;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.rcs_rocket_english.controls.Galaxy;
import com.rcs_rocket_english.controls.NavigationMenu;
import com.rcs_rocket_english.controls.NavBarMenu;

public class MainActivity extends AppCompatActivity
        implements NavBarMenu.OnNavItemSelectedListener, NavigationMenu.OnNavItemSelectedListener {

    private NavBarMenu navBarMenu;
    private NavigationMenu navigationMenu;
    private Galaxy galaxyUno, galaxyDos, galaxyTres, galaxyCuatro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.galaxies);

        navBarMenu = findViewById(R.id.navbarMenu);
        navBarMenu.cambiarImagenPerfil(1);

        //inicializar datos de lightning y hearts
        navBarMenu.setTextLightning("5");
        navBarMenu.setTextHearts("10");

        // Inicializa los controles
        navigationMenu = findViewById(R.id.navbarControl);

        // Registra el listener para ambos controles
        navBarMenu.setOnNavItemSelectedListener(this);
        navigationMenu.setOnNavItemSelectedListener(this);

        // Obtener las referencias a las galaxias
        galaxyUno = findViewById(R.id.galaxyUno);
        galaxyDos = findViewById(R.id.galaxyDos);
        galaxyTres = findViewById(R.id.galaxyTres);
        galaxyCuatro = findViewById(R.id.galaxyCuatro);

        DataBase db = new DataBase(this);
        int progress = db.getProgressOfGalaxy("Gramatica");
        float progressFloat = (float) progress / 6;
        galaxyUno.setProgress(progressFloat);

        progress = db.getProgressOfGalaxy("Lectura");
        float progressFloat2 = (float) progress / 6;
        galaxyDos.setProgress(progress);

        progress = db.getProgressOfGalaxy("Vocabulario");
        float progressFloat3 = (float) progress / 6;
        galaxyTres.setProgress(progress);



        // Configurar listeners para los botones de las galaxias
        configureGalaxyListener(R.id.galaxyUno, R.layout.galaxy_levels_v1);
        configureGalaxyListener(R.id.galaxyDos, R.layout.galaxy_levels_v2);
        configureGalaxyListener(R.id.galaxyTres, R.layout.galaxy_levels_v3);
        configureGalaxyListener(R.id.galaxyCuatro, R.layout.galaxy_levels_v4);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Update the galaxy progress every time the activity is resumed
        updateGalaxyProgress();
    }

    private void updateGalaxyProgress() {
        DataBase db = new DataBase(this);
        int progress = db.getProgressOfGalaxy("Gramatica");
        float progressFloat = (float) progress / 6;
        galaxyUno.setProgress(progressFloat);

        progress = db.getProgressOfGalaxy("Lectura");
        float progressFloat2 = (float) progress / 6;
        galaxyDos.setProgress(progress);

        progress = db.getProgressOfGalaxy("Vocabulario");
        float progressFloat3 = (float) progress / 6;
        galaxyTres.setProgress(progress);
    }

    private void configureGalaxyListener(int buttonId, int layoutId) {
        Galaxy button = findViewById(buttonId);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, GalaxiesActivity.class);
            intent.putExtra("layout_id", layoutId); // Layout correspondiente
            startActivity(intent);
        });
    }

    @Override
    public void onNavItemSelected(int itemId) {
        if (itemId == R.id.btnChallenge) {
            // openActivity(ProfileActivity.class);
        } else if (itemId == R.id.btnHome) {
            // openActivity(HomeActivity.class);
        } else if (itemId == R.id.btnSettings) {
            openActivity(SettingsActivity.class);
        } else if (itemId == R.id.btnProfile) {
            openActivity(ProfileActivity.class);
        }
    }

    // Método para abrir actividades
    private void openActivity(Class<?> activityClass) {
        Intent intent = new Intent(MainActivity.this, activityClass);
        startActivity(intent);
    }
}