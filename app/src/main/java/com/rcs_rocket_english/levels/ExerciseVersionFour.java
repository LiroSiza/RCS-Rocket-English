package com.rcs_rocket_english.levels;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.rcs_rocket_english.DataBase;
import com.rcs_rocket_english.R;
import com.rcs_rocket_english.excObjects.excA;

import java.util.List;

public class ExerciseVersionFour extends AppCompatActivity {

    int attempt=0;
    Button btnNext, r1, r2, r3;
    TextView phrase;
    Boolean used;
    String galaxyName;
    int asteroidIndex;
    List<excA> excList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ejercicio_a);

        btnNext = findViewById(R.id.button5);
        r1 = findViewById(R.id.button2);
        r2 = findViewById(R.id.button3);
        r3 = findViewById(R.id.button4);

        phrase = findViewById(R.id.textView11);

        used = getIntent().getBooleanExtra("used", false);
        galaxyName = getIntent().getStringExtra("galaxy_name");
        asteroidIndex = getIntent().getIntExtra("asteroid_index", 1);

        retrieveData();

        fillData();

        r1.setOnClickListener(evento);
        r2.setOnClickListener(evento);
        r3.setOnClickListener(evento);


    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void retrieveData(){
        DataBase db = new DataBase(this);
        this.excList = db.getExcA(used, galaxyName);
        db.listContA();

    }

    private void fillData(){
        phrase.setText(excList.get(attempt).getPhrase());
        r1.setText(excList.get(attempt).getOption1());
        r2.setText(excList.get(attempt).getOption2());
        r3.setText(excList.get(attempt).getOption3());
    }

    public View.OnClickListener evento = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            Button button = (Button) v;
            Boolean correct;

            if(button.getText().equals(excList.get(attempt).getAnswer())){
                correct = true;
            }else{
                correct = false;
            }

            if(correct){
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
    };

    private void increaseProgress(){
        DataBase db = new DataBase(this);
        db.increaseProgress(galaxyName);
    }

    private void setAsUsed(){
        DataBase db = new DataBase(this);
        db.setUsedA(excList);
    }

}
