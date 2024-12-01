package com.rcs_rocket_english.controls;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.rcs_rocket_english.R;

public class NavBarMenu extends LinearLayout {
    ImageButton btnProfile;
    ImageView lightning, hearts;
    private OnNavItemSelectedListener listener;

    // Interfaz para la comunicación entre el control y las actividades
    public interface OnNavItemSelectedListener {
        void onNavItemSelected(int itemId);
    }

    // Método para registrar el listener
    public void setOnNavItemSelectedListener(OnNavItemSelectedListener listener) {
        this.listener = listener;
    }

    public NavBarMenu(Context context) {
        super(context);
        inicializar();
    }

    public NavBarMenu(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inicializar();
    }

    public NavBarMenu(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inicializar();
    }

    public NavBarMenu(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        inicializar();
    }

    public void inicializar() {
        LayoutInflater li = LayoutInflater.from(getContext());
        li.inflate(R.layout.navbar_menu, this, true);

        btnProfile = findViewById(R.id.btnProfile);
        lightning = findViewById(R.id.lightning);
        hearts = findViewById(R.id.hearts);

        btnProfile.setBackgroundResource(R.drawable.btn_profile);
        lightning.setBackgroundResource(R.drawable.icon_lightning);
        hearts.setBackgroundResource(R.drawable.icon_heart);

        asignarEventos();
    }

    public void asignarEventos() {
        btnProfile.setOnClickListener(evento);
        lightning.setOnClickListener(evento);
        hearts.setOnClickListener(evento);
    }

    private OnClickListener evento = new OnClickListener() {
        @Override
        public void onClick(View view) {
            if (listener != null) {
                if (view == btnProfile) {
                    listener.onNavItemSelected(R.id.btnChallenge);
                } else if (view == lightning) {
                    listener.onNavItemSelected(R.id.btnHome);
                } else if (view == hearts) {
                    listener.onNavItemSelected(R.id.btnSettings);
                }
            }
        }
    };

    // Método para cambiar la imagen del btnProfile dependiendo de la clase o estado
    public void cambiarImagenPerfil(int estado) {
        switch (estado) {
            case 1:
                btnProfile.setBackgroundResource(R.drawable.btn_profile);
                break;
            case 2:
                btnProfile.setBackgroundResource(R.drawable.btn_challenge);
                break;
            default:
                btnProfile.setBackgroundResource(R.drawable.btn_profile); // Imagen por defecto
                break;
        }
    }
}
