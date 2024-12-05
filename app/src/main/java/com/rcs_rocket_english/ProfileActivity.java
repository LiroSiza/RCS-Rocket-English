package com.rcs_rocket_english;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        // Boton de return a la actividad principal
        ImageButton btnReturn = findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(v -> {
            // Reproduce sonido usando SoundUtil
            SoundUtil.playSound(this, R.raw.sound_button_click_two);
            finish();
        });
    }
}
