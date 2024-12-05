package com.rcs_rocket_english;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;

public class SoundUtil {
    public static void playSound(Context context, int resId) {
        // Acceso a SharedPreferences para verificar si el sonido está habilitado
        SharedPreferences preferences = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE);
        boolean isSoundEnabled = preferences.getBoolean("sound_enabled", true);

        // Reproduce el sonido solo si está habilitado
        if (isSoundEnabled) {
            MediaPlayer mediaPlayer = MediaPlayer.create(context, resId);
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(MediaPlayer::release); // Libera el recurso después de reproducir
        }
    }
}
