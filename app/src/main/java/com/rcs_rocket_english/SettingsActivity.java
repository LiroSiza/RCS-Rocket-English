package com.rcs_rocket_english;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Switch para habilitar/deshabilitar sonidos
        Switch soundSwitch = findViewById(R.id.sound_switch);

        // Acceso a SharedPreferences
        SharedPreferences preferences = getSharedPreferences("AppPrefs", MODE_PRIVATE);

        // Obtén el estado actual del sonido (true por defecto)
        boolean isSoundEnabled = preferences.getBoolean("sound_enabled", true);
        soundSwitch.setChecked(isSoundEnabled);

        // Escucha los cambios en el switch y guarda la preferencia
        soundSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("sound_enabled", isChecked); // Guarda el estado del sonido
            editor.apply();
        });

        // Configuración del botón de regreso
        ImageButton backButton = findViewById(R.id.btnReturn);
        backButton.setOnClickListener(v -> {
            // Reproduce sonido usando SoundUtil
            SoundUtil.playSound(this, R.raw.sound_button_click_two);
            finish();
        });

        backButton.setBackgroundResource(R.drawable.btn_baseline_arrow_back_ios_new_24);
    }
}
