package com.rcs_rocket_english;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;

public class LoadingActivity extends AppCompatActivity {

    private Handler handler = new Handler();
    private Runnable loadSecondGifRunnable;
    private DataBase db; // Instancia de la base de datos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_page);

        ImageView loadingGif = findViewById(R.id.loadingGif);
        ImageView appNameGif = findViewById(R.id.appNameText);

        // Cargar el GIF usando Glide
        Glide.with(this).load(R.drawable.gif_rocket).into(loadingGif);

        // Definir la tarea de cargar el segundo GIF
        loadSecondGifRunnable = new Runnable() {
            @Override
            public void run() {
                if (!isFinishing() && !isDestroyed()) {
                    Glide.with(LoadingActivity.this).load(R.drawable.gif_rocket_english_text).into(appNameGif);
                    appNameGif.setVisibility(View.VISIBLE); // Hacer visible el segundo GIF
                }
            }
        };

        // Programar la carga del segundo GIF con un retraso
        handler.postDelayed(loadSecondGifRunnable, 700);

        // Iniciar actividad principal después de 4 segundos
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Abrir form o iniciar app
                // Inicializar la base de datos
                db = new DataBase(LoadingActivity.this);
                Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
                // Verificar si hay usuarios registrados
                if (db.hasUsers()) { // Continuar con la app
                    intent = new Intent(LoadingActivity.this, MainActivity.class);
                } else { // Abrir el formulario
                    intent = new Intent(LoadingActivity.this, LoginActivity.class);
                }
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // Animacion de salida
                finish(); // Cierra la actividad de carga
            }
        }, 4500);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null); // Cancelar tareas pendientes
    }
}
