package com.rcs_rocket_english;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.animation.ScaleAnimation;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.rcs_rocket_english.controls.NavBarMenu;
import com.rcs_rocket_english.controls.NavigationMenu;
import com.rcs_rocket_english.levels.ExerciseVersionFive;
import com.rcs_rocket_english.levels.ExerciseVersionFour;
import com.rcs_rocket_english.levels.ExerciseVersionOne;
import com.rcs_rocket_english.levels.ExerciseVersionSix;
import com.rcs_rocket_english.levels.ExerciseVersionThree;
import com.rcs_rocket_english.levels.ExerciseVersionTwo;

import java.util.Random;

public class GalaxiesActivity extends AppCompatActivity implements NavBarMenu.OnNavItemSelectedListener, NavigationMenu.OnNavItemSelectedListener {

    private DataBase db; // Instancia de la base de datos
    private NavBarMenu navBarMenu;
    private NavigationMenu navigationMenu;
    private String galaxyName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Recibir el intent, dependiendo la galaxia
        int layoutId = getIntent().getIntExtra("layout_id", R.layout.galaxy_levels_v1); // Default si no se pasa
        setContentView(layoutId);

        // Obtener el nombre de la galaxia basado en el layout recibido
        if (R.layout.galaxy_levels_v1 == layoutId) {
            galaxyName = "Gramatica";
        } else if (R.layout.galaxy_levels_v2 == layoutId) {
            galaxyName = "Lectura";
        } else if (R.layout.galaxy_levels_v3 == layoutId) {
            galaxyName = "Vocabulario";
        } else {
            galaxyName = "Escucha";
        }

        // Inicializa los controles
        navBarMenu = findViewById(R.id.navbarMenu);
        navigationMenu = findViewById(R.id.navbarControl);

        navBarMenu.cambiarImagenPerfil(2);

        // Configurar los listeners de los menús
        navBarMenu.setOnNavItemSelectedListener(this);
        navigationMenu.setOnNavItemSelectedListener(this);

        // Forzar modo oscuro
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        // Configurar los botones de asteroides
        configureAsteroidButtons();

        int exp = getExperience();
        String expString = String.valueOf(exp);

        //inicializar datos de lightning y hearts
        navBarMenu.setTextLightning(expString);
    }

    private void configureAsteroidButtons() {
        // Lista de botones de asteroides
        int[] asteroidIds = {
                R.id.asteroid_1, R.id.asteroid_2, R.id.asteroid_3,
                R.id.asteroid_4, R.id.asteroid_5, R.id.asteroid_6
        };

        // Asignar listeners a cada botón de asteroide
        for (int i = 0; i < asteroidIds.length; i++) {
            ImageButton asteroidButton = findViewById(asteroidIds[i]);
            int asteroidIndex = i + 1; // Índice del asteroide (1 a 6)
            asteroidButton.setOnClickListener(v -> onAsteroidClicked(asteroidIndex));
        }
    }

    private void onAsteroidClicked(int asteroidIndex) {
        Random random = new Random();
        Intent intent;

        // Verificar qué actividad abrir dependiendo de la galaxia y el asteroide
        switch (galaxyName) {
            case "Gramatica":
                intent = new Intent(this, ExerciseVersionFive.class);
                if (asteroidIndex % 2 == 0) {
                    // Ejemplo de alternar entre actividades
                    intent = new Intent(this, ExerciseVersionFour.class);
                }
                break;

            case "Lectura":
                intent = new Intent(this, ExerciseVersionTwo.class);
                break;

            case "Vocabulario":
                int randomActivity = random.nextInt(2); // Generar actividad aleatoria
                if (asteroidIndex == 1) {
                    intent = new Intent(this, ExerciseVersionSix.class);
                } else if (asteroidIndex == 2) {
                    intent = new Intent(this, ExerciseVersionFour.class);
                } else if(asteroidIndex == 3){
                    intent = new Intent(this, ExerciseVersionThree.class); // Por defecto
                }else{
                    if(randomActivity == 0){
                        intent = new Intent(this, ExerciseVersionThree.class);
                    }else if(randomActivity == 1){
                        intent = new Intent(this, ExerciseVersionSix.class);
                    }else{
                        intent = new Intent(this, ExerciseVersionFour.class);
                    }
                }
                break;
            default:
                intent = new Intent(this, ExerciseVersionOne.class); // Por defecto
                break;
        }

        DataBase db = new DataBase(this);
        //db.listContA();
        //db.q();
        int progress = db.getProgressOfGalaxy(galaxyName);
        // Pasar información adicional al intent
        if(progress > asteroidIndex-1){
            intent.putExtra("used", true);
        }else if(progress == asteroidIndex-1){
            intent.putExtra("used", false);
        }else{
            return;
        }

        intent.putExtra("galaxy_name", galaxyName);
        intent.putExtra("asteroid_index", asteroidIndex);

        // Iniciar la actividad
        startActivity(intent);
    }

    @Override
    public void onNavItemSelected(int itemId) {
        if (itemId == R.id.btnSettings) {
            openActivity(SettingsActivity.class);
        } else if (itemId == R.id.btnHome || itemId == R.id.btnProfile) {
            finish();
        }
    }

    private void openActivity(Class<?> activityClass) {
        Intent intent = new Intent(this, activityClass);
        startActivity(intent);
    }

    public int getExperience(){
        // Inicializar la instancia de la base de datos
        db = new DataBase(this);
        // Obtener la experiencia del primer usuario
        return db.getExperience();
    }
}
