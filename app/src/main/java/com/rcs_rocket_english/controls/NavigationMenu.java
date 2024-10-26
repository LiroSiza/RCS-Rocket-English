package com.rcs_rocket_english.controls;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.rcs_rocket_english.R;

public class NavigationMenu extends LinearLayout {
    ImageButton btnCallenge, btnHome, btnSettings;
    private OnNavItemSelectedListener listener;

    // Interfaz para la comunicación entre el control y las actividades
    public interface OnNavItemSelectedListener {
        void onNavItemSelected(int itemId);
    }
    // Método para registrar el listener
    public void setOnNavItemSelectedListener(OnNavItemSelectedListener listener) {
        this.listener = listener;
    }

    public NavigationMenu(Context context) {
        super(context);
        inicializar();
    }

    public NavigationMenu(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inicializar();
    }

    public NavigationMenu(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inicializar();
    }

    public NavigationMenu(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        inicializar();
    }

    public void inicializar(){
        LayoutInflater li = LayoutInflater.from(getContext());
        li.inflate(R.layout.navigation_menu, this, true);

        btnCallenge = findViewById(R.id.btnChallenge);
        btnHome = findViewById(R.id.btnHome);
        btnSettings = findViewById(R.id.btnSettings);

        btnCallenge.setBackgroundResource(R.drawable.btn_challenge);
        btnHome.setBackgroundResource(R.drawable.btn_home);
        btnSettings.setBackgroundResource(R.drawable.btn_settings);

        asignarEventos();
    }

    public void asignarEventos(){
        btnCallenge.setOnClickListener(evento);
        btnHome.setOnClickListener(evento);
        btnSettings.setOnClickListener(evento);
    }

    private OnClickListener evento = new OnClickListener() {
        @Override
        public void onClick(View view) {
            if (listener != null) {
                if (view == btnCallenge) {
                    listener.onNavItemSelected(R.id.btnChallenge);
                } else if (view == btnHome) {
                    listener.onNavItemSelected(R.id.btnHome);
                } else if (view == btnSettings) {
                    listener.onNavItemSelected(R.id.btnSettings);
                }
            }
        }
    };

}
