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

public class GalaxiesActivity extends AppCompatActivity implements NavBarMenu.OnNavItemSelectedListener, NavigationMenu.OnNavItemSelectedListener {

    private NavBarMenu navBarMenu;
    private NavigationMenu navigationMenu;
    private ImageButton lastPressedButton = null;  // Variable para guardar el último botón presionado

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Recibir el intent, dependiendo la galaxia
        int layoutId = getIntent().getIntExtra("layout_id", R.layout.galaxy_levels_v1); // Default si no se pasa
        // Configurar el layout dinámico
        setContentView(layoutId);

        // Inicializa los controles
        navBarMenu = findViewById(R.id.navbarMenu);
        navigationMenu = findViewById(R.id.navbarControl);

        // Registra el listener para ambos controles
        navBarMenu.setOnNavItemSelectedListener(this);
        navigationMenu.setOnNavItemSelectedListener(this);

        // Forzar modo oscuro
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        // Definir la animación de aumento y disminución de tamaño
        ScaleAnimation scaleUp = new ScaleAnimation(
                1.0f, 1.1f,  // Escala en X
                1.0f, 1.1f,  // Escala en Y
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f, // Pivot en el centro
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f
        );
        scaleUp.setDuration(100);
        scaleUp.setFillAfter(true);

        ScaleAnimation scaleDown = new ScaleAnimation(
                1.1f, 1.0f,  // Escala en X
                1.1f, 1.0f,  // Escala en Y
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f
        );
        scaleDown.setDuration(100);
        scaleDown.setFillAfter(true);

        // Lista de IDs de los ImageButton
        int[] buttonIds = {
                R.id.asteroid_1, R.id.asteroid_2, R.id.asteroid_3, R.id.asteroid_4,
                R.id.asteroid_5, R.id.asteroid_6
        };

        // Recorrer el array de botones y asignar el OnTouchListener a cada uno
        for (int id : buttonIds) {
            ImageButton button = findViewById(id);
            setButtonTouchListener(button, scaleUp, scaleDown);  // Asignar listener
        }
    }

    // Función para asignar el OnTouchListener a un ImageButton
    private void setButtonTouchListener(ImageButton button, ScaleAnimation scaleUp, ScaleAnimation scaleDown) {
        button.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if (lastPressedButton != null && lastPressedButton != v) {
                        // Si hay un botón presionado previamente, detener su animación
                        lastPressedButton.clearAnimation();
                    }
                    v.startAnimation(scaleUp); // Aplicar la animación al presionar
                    lastPressedButton = (ImageButton) v; // Guardar el botón actual
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    v.startAnimation(scaleDown); // Restaurar el tamaño al soltar
                    v.performClick(); // Llamar a performClick para manejar correctamente el clic
                    break;
            }
            return false;  // No interferir con otros eventos de toque
        });
    }

    @Override
    public void onNavItemSelected(int itemId) {
        if (itemId == R.id.btnSettings) {
            openActivity(ProfileActivity.class);
        } else if (itemId == R.id.btnHome) {
            // Reproduce sonido usando SoundUtil
            SoundUtil.playSound(this, R.raw.sound_button_click_two);
            finish();
        } else if (itemId == R.id.btnProfile) {
            // Reproduce sonido usando SoundUtil
            SoundUtil.playSound(this, R.raw.sound_button_click_two);
            finish();
        }
    }

    // Método para abrir actividades
    private void openActivity(Class<?> activityClass) {
        Intent intent = new Intent(GalaxiesActivity.this, activityClass);
        startActivity(intent);
    }
}
