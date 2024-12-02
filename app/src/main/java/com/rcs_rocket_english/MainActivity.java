package com.rcs_rocket_english;

import static androidx.core.content.ContextCompat.startActivity;

import android.os.Bundle;
import android.content.Intent;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.rcs_rocket_english.controls.Galaxy;
import com.rcs_rocket_english.controls.NavigationMenu;
import com.rcs_rocket_english.controls.NavBarMenu;

public class MainActivity extends AppCompatActivity
        implements NavBarMenu.OnNavItemSelectedListener, NavigationMenu.OnNavItemSelectedListener {

public class MainActivity extends AppCompatActivity implements NavigationMenu.OnNavItemSelectedListener {

    private NavBarMenu navBarMenu;
    private NavigationMenu navigationMenu;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ImageButton ast1,ast2,ast3,ast4,ast5,ast6,ast7,ast8,ast9,ast10;
        TextView text1;
        String whatever;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // Forzar modo oscuro
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
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


        // Recorrer el array de botones y asignar el OnTouchListener a cada uno
        for (int id : buttonIds) {
            ImageButton button = findViewById(id);
            setButtonTouchListener(button, scaleUp, scaleDown);  // Asignar listener
        }
        // Configurar listeners para los botones de las galaxias
        configureGalaxyListener(R.id.galaxyUno, R.layout.galaxy_levels_v1);
        configureGalaxyListener(R.id.galaxyDos, R.layout.galaxy_levels_v2);
        configureGalaxyListener(R.id.galaxyTres, R.layout.galaxy_levels_v3);
        configureGalaxyListener(R.id.galaxyCuatro, R.layout.galaxy_levels_v4);

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
            openActivity(ProfileActivity.class);
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
