package com.rcs_rocket_english.levels;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.rcs_rocket_english.DataBase;
import com.rcs_rocket_english.R;
import com.rcs_rocket_english.excObjects.excC;

import java.util.List;

public class ExerciseVersionFive extends AppCompatActivity {

    int attempt=0;
    int turn=0;
    Button next, r1, r2;
    TextView emptyWord, firstWord, emptyWord2, secondWord;
    Boolean used;
    String galaxyName;
    int asteroidIndex;
    List<excC> excList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ejercicio_c);

        emptyWord = findViewById(R.id.textView14);
        firstWord = findViewById(R.id.textView15);
        emptyWord2 = findViewById(R.id.textView16);
        secondWord = findViewById(R.id.textView17);

        r1 = findViewById(R.id.button6);
        r2 = findViewById(R.id.button7);
        next = findViewById(R.id.button8);

        used = getIntent().getBooleanExtra("used", false);
        galaxyName = getIntent().getStringExtra("galaxy_name");
        asteroidIndex = getIntent().getIntExtra("asteroid_index", 1);

        retrieveData();

        fillData();

        next.setOnClickListener(evento);
        r1.setOnClickListener(evento);
        r2.setOnClickListener(evento);

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    private void retrieveData() {
        DataBase db = new DataBase(this);
        this.excList = db.getExcC(used, galaxyName);
        db.listContC();
    }

    private void fillData() {

        firstWord.setText(excList.get(attempt).getText1());
        secondWord.setText(excList.get(attempt).getText2());
        r1.setText(excList.get(attempt).getAnswer1());
        r2.setText(excList.get(attempt).getAnswer2());

    }

    public View.OnClickListener evento = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            Button button = (Button) view;
            if(button.getId()== next.getId()){
                evaluate();
            }else{
                turn++;
                if(turn%2 != 0){
                    emptyWord.setText(button.getText());
                }else{
                    emptyWord2.setText(button.getText());
                }
            }
        }
    };

    private void evaluate(){
        Boolean correct;
        if(emptyWord.getText().equals(excList.get(attempt).getAnswer1()) && emptyWord2.getText().equals(excList.get(attempt).getAnswer2())){
            correct = true;
        }else{
            correct = false;
        }
        if(correct){
            attempt++;
            if(attempt<excList.size()){
                emptyWord.setText("    ");
                emptyWord2.setText("    ");
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
        db.setUsedC(excList);
    }

}
