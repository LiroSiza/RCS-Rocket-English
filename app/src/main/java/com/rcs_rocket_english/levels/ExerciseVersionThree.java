package com.rcs_rocket_english.levels;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.rcs_rocket_english.DataBase;
import com.rcs_rocket_english.R;

import java.util.Vector;

public class ExerciseVersionThree extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ejercicio_f);


        DataBase db = new DataBase(this);
        //db.seeData();
        /*Vector<String> lista =  db.listaDatos();
        for(String s : lista){
            Log.d("mine", s);
        }*/
        db.listContF();

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
