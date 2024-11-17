package com.rcs_rocket_english;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.animation.ScaleAnimation;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.rcs_rocket_english.controls.NavigationMenu;

public class MainActivity extends AppCompatActivity implements NavigationMenu.OnNavItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ImageButton ast1,ast2,ast3,ast4,ast5,ast6,ast7,ast8,ast9,ast10;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.galaxy_levels_v1);
      
        // Forzar modo oscuro
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        // Control personalizado
        //NavigationMenu navMenu = findViewById(R.id.navbarControl);
        //navMenu.setOnNavItemSelectedListener(this);

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
                R.id.asteroid_5, R.id.asteroid_6, R.id.asteroid_7, R.id.asteroid_8,
                R.id.asteroid_9, R.id.asteroid_10
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
                    v.startAnimation(scaleUp); // Aplicar la animación al presionar
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

    }
}