package com.rcs_rocket_english.levels;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.rcs_rocket_english.R;

public class ExerciseVersionOne extends AppCompatActivity {
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ejercicio_d);

        ImageView gif = findViewById(R.id.gifImageView);

        // Cargar el GIF usando Glide
        Glide.with(this).load(R.drawable.gif_voice).into(gif);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null); // Cancelar tareas pendientes
    }
}
