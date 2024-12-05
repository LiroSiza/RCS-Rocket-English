package com.rcs_rocket_english.levels;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.rcs_rocket_english.DataBase;
import com.rcs_rocket_english.R;
import com.rcs_rocket_english.excObjects.excB;
import com.rcs_rocket_english.excObjects.excC;

import java.util.List;

public class ExerciseVersionSix extends AppCompatActivity {

    int attempt=0;

    TextView phrase;
    EditText answer;
    Button next;

    Boolean used;
    String galaxyName;
    int asteroidIndex;
    List<excB> excList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ejercicio_b);

        phrase = findViewById(R.id.textView19);
        answer = findViewById(R.id.textView20);
        next = findViewById(R.id.button9);

        used = getIntent().getBooleanExtra("used", false);
        galaxyName = getIntent().getStringExtra("galaxy_name");
        asteroidIndex = getIntent().getIntExtra("asteroid_index", 1);

        retrieveData();

        fillData();

        next.setOnClickListener(evento);


    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    private void retrieveData() {
        DataBase db = new DataBase(this);
        this.excList = db.getExcB(used);
        db.listContC();
    }

    private void fillData() {

        phrase.setText(excList.get(attempt).getPhrase());
        answer.setText(excList.get(attempt).getText().substring(0,10));

    }

    private View.OnClickListener evento = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            evaluate();
        }
    };

    private void evaluate() {
        if(answer.getText().toString().equals(excList.get(attempt).getText())) {
            attempt++;
            if(attempt<excList.size()){
                fillData();
            }else {
                //Increase progress of galaxy
                if(!used) {
                    increaseProgress();
                }

                //Set these excercises as used
                setAsUsed();


                //Call next activity
                finish();
            }
        }
    }

    private void increaseProgress(){
        DataBase db = new DataBase(this);
        db.increaseProgress(galaxyName);
    }

    private void setAsUsed(){
        DataBase db = new DataBase(this);
        db.setUsedB(excList);
    }


}
