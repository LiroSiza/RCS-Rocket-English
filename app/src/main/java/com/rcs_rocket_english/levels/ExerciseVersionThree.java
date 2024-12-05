package com.rcs_rocket_english.levels;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.rcs_rocket_english.DataBase;
import com.rcs_rocket_english.R;
import com.rcs_rocket_english.exc2Objects.RecordVocabulary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExerciseVersionThree extends AppCompatActivity {

    private DataBase db;
    private List<RecordVocabulary> records = new ArrayList<>();
    private Map<String, String> correctPairs = new HashMap<>();
    private Map<Button, String> selectedButtons = new HashMap<>();
    private List<Button> allButtons = new ArrayList<>();
    private Button btnComprobar;
    private int correctMatchesCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ejercicio_f);

        initializeDatabase();
        initializeButtons();
        setupButtonListeners();
    }

    private void initializeDatabase() {
        db = new DataBase(this);
        boolean used = getIntent().getBooleanExtra("used", false);
        records = used ? db.getRecordsByCategoryContFUsed() : db.getRecordsByCategoryContF();

        if (records.isEmpty()) {
            Toast.makeText(this, "No hay registros disponibles", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
    }

    private void initializeButtons() {
        // Initialize all buttons
        allButtons.add(findViewById(R.id.btnRespuesta1));
        allButtons.add(findViewById(R.id.btnRespuesta2));
        allButtons.add(findViewById(R.id.btnRespuesta3));
        allButtons.add(findViewById(R.id.btnRespuesta4));
        allButtons.add(findViewById(R.id.btnRespuesta5));
        allButtons.add(findViewById(R.id.btnRespuesta6));
        btnComprobar = findViewById(R.id.btnComprobar);

        // Set button texts from the first record
        RecordVocabulary currentRecord = records.get(0);
        allButtons.get(0).setText(currentRecord.getR1());
        allButtons.get(1).setText(currentRecord.getR2());
        allButtons.get(2).setText(currentRecord.getR3());
        allButtons.get(3).setText(currentRecord.getR4());
        allButtons.get(4).setText(currentRecord.getR5());
        allButtons.get(5).setText(currentRecord.getR6());

        // Shuffle button positions to randomize pair locations
        Collections.shuffle(allButtons);

        // Create correct pairs
        correctPairs.put(currentRecord.getR1(), currentRecord.getR2());
        correctPairs.put(currentRecord.getR3(), currentRecord.getR4());
        correctPairs.put(currentRecord.getR5(), currentRecord.getR6());

        // Initially disable check button
        btnComprobar.setEnabled(false);
    }

    private void setupButtonListeners() {
        View.OnClickListener buttonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button selectedButton = (Button) v;

                // Prevent re-selecting the same button
                if (selectedButtons.containsKey(selectedButton)) {
                    return;
                }

                // Highlight selected button
                selectedButton.setBackgroundColor(Color.YELLOW);
                selectedButtons.put(selectedButton, selectedButton.getText().toString());

                // Check for a pair when two buttons are selected
                if (selectedButtons.size() == 2) {
                    Button[] buttons = selectedButtons.keySet().toArray(new Button[2]);
                    String value1 = selectedButtons.get(buttons[0]);
                    String value2 = selectedButtons.get(buttons[1]);

                    // Verify if the pair is correct
                    if (isCorrectPair(value1, value2)) {
                        buttons[0].setBackgroundColor(Color.GREEN);
                        buttons[1].setBackgroundColor(Color.GREEN);
                        buttons[0].setEnabled(false);
                        buttons[1].setEnabled(false);
                        correctMatchesCount++;

                        // Check if all pairs are matched
                        if (correctMatchesCount == 3) {
                            btnComprobar.setEnabled(true);
                            Toast.makeText(ExerciseVersionThree.this,
                                    "¡Felicidades! Todos los pares están correctos.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        // Reset unmatched buttons
                        buttons[0].setBackgroundColor(Color.WHITE);
                        buttons[1].setBackgroundColor(Color.WHITE);
                    }

                    // Clear selection for next attempt
                    selectedButtons.clear();
                }
            }
        };

        // Set click listener for all buttons
        for (Button button : allButtons) {
            button.setOnClickListener(buttonListener);
        }

        // Set up check button
        btnComprobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mark exercise as completed and close activity
                setResult(RESULT_OK);
                finish();
            }
        });
    }

    private boolean isCorrectPair(String value1, String value2) {
        return (correctPairs.containsKey(value1) && correctPairs.get(value1).equals(value2)) ||
                (correctPairs.containsKey(value2) && correctPairs.get(value2).equals(value1));
    }

    @Override
    protected void onDestroy() {
        if (db != null) {
            db.close();
        }
        super.onDestroy();
    }
}