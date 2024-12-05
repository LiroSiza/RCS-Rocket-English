package com.rcs_rocket_english.controls;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.rcs_rocket_english.DataBase;
import com.rcs_rocket_english.R;

import android.widget.TextView;

public class NavBarMenu extends LinearLayout {

    ImageButton btnProfile;
    ImageView lightning, hearts;
    TextView textlightning, textHearts;
    private OnNavItemSelectedListener listener;

    public interface OnNavItemSelectedListener {
        void onNavItemSelected(int itemId);
    }

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
        textlightning = findViewById(R.id.textlightning);
        textHearts = findViewById(R.id.textHearts);

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
                    listener.onNavItemSelected(R.id.btnProfile);
                }
            }
        }
    };

    public void cambiarImagenPerfil(int estado) {
        switch (estado) {
            case 1:
                btnProfile.setBackgroundResource(R.drawable.btn_profile);
                break;
            case 2:
                btnProfile.setBackgroundResource(R.drawable.btn_baseline_arrow_back_ios_new_24);
                break;
            default:
                btnProfile.setBackgroundResource(R.drawable.btn_profile);
                break;
        }
    }

    // Métodos para manejar textlightning
    public String getTextLightning() {
        return textlightning.getText().toString();
    }

    public void setTextLightning(String text) {
        textlightning.setText(text);
    }

    // Métodos para manejar textHearts
    public String getTextHearts() {
        return textHearts.getText().toString();
    }

    public void setTextHearts(String text) {
        textHearts.setText(text);
    }
}
