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
    private int currentRound = 0;

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
        // Inicializar botones si aún no están inicializados
        if (allButtons.isEmpty()) {
            allButtons.add(findViewById(R.id.btnRespuesta1));
            allButtons.add(findViewById(R.id.btnRespuesta2));
            allButtons.add(findViewById(R.id.btnRespuesta3));
            allButtons.add(findViewById(R.id.btnRespuesta4));
            allButtons.add(findViewById(R.id.btnRespuesta5));
            allButtons.add(findViewById(R.id.btnRespuesta6));
            btnComprobar = findViewById(R.id.btnComprobar);
        }

        // Restablecer para una nueva ronda
        correctMatchesCount = 0;
        selectedButtons.clear();
        correctPairs.clear();

        // Habilitar todos los botones
        for (Button button : allButtons) {
            button.setEnabled(true);
            button.setBackgroundColor(Color.WHITE);
        }

        // Deshabilitar botón de comprobación inicialmente
        btnComprobar.setEnabled(false);

        // Cargar datos de la ronda actual
        loadRoundData();
    }

    private void loadRoundData() {
        // Verificar si se han completado todas las rondas
        if (currentRound >= records.size()) {
            Toast.makeText(this, "Ejercicio completado", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        RecordVocabulary currentRecord = records.get(currentRound);

        // Crear una lista de todos los textos para mezclar
        List<String> allTexts = new ArrayList<>();
        allTexts.add(currentRecord.getR1());
        allTexts.add(currentRecord.getR2());
        allTexts.add(currentRecord.getR3());
        allTexts.add(currentRecord.getR4());
        allTexts.add(currentRecord.getR5());
        allTexts.add(currentRecord.getR6());

        // Mezclar los textos
        Collections.shuffle(allTexts);

        // Establecer textos de botones de la lista mezclada
        for (int i = 0; i < allButtons.size(); i++) {
            allButtons.get(i).setText(allTexts.get(i));
        }

        // Crear pares correctos
        correctPairs.put(currentRecord.getR1(), currentRecord.getR2());
        correctPairs.put(currentRecord.getR3(), currentRecord.getR4());
        correctPairs.put(currentRecord.getR5(), currentRecord.getR6());
    }

    private void setupButtonListeners() {
        View.OnClickListener buttonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button selectedButton = (Button) v;

                // Evitar volver a seleccionar el mismo botón
                if (selectedButtons.containsKey(selectedButton)) {
                    return;
                }

                // Resaltar botón seleccionado
                selectedButton.setBackgroundColor(Color.YELLOW);
                selectedButtons.put(selectedButton, selectedButton.getText().toString());

                // Revisar par cuando se seleccionan dos botones
                if (selectedButtons.size() == 2) {
                    Button[] buttons = selectedButtons.keySet().toArray(new Button[2]);
                    String value1 = selectedButtons.get(buttons[0]);
                    String value2 = selectedButtons.get(buttons[1]);

                    // Verificar si el par es correcto
                    if (isCorrectPair(value1, value2)) {
                        buttons[0].setBackgroundColor(Color.GREEN);
                        buttons[1].setBackgroundColor(Color.GREEN);
                        buttons[0].setEnabled(false);
                        buttons[1].setEnabled(false);
                        correctMatchesCount++;

                        // Verificar si todos los pares están emparejados
                        if (correctMatchesCount == 3) {
                            currentRound++;

                            // Si se completaron todas las rondas, habilitar botón de comprobación
                            if (currentRound >= records.size()) {
                                btnComprobar.setEnabled(true);
                                Toast.makeText(ExerciseVersionThree.this,
                                        "¡Felicidades! Todos los ejercicios están completados.",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                // Preparar siguiente ronda
                                Toast.makeText(ExerciseVersionThree.this,
                                        "Ronda completada. Continúa con la siguiente.",
                                        Toast.LENGTH_SHORT).show();

                                // Importante: llamar a initializeButtons para preparar la siguiente ronda
                                initializeButtons();
                            }
                        }
                    } else {
                        // Restablecer botones no coincidentes
                        buttons[0].setBackgroundColor(Color.WHITE);
                        buttons[1].setBackgroundColor(Color.WHITE);
                    }

                    // Limpiar selección para el siguiente intento
                    selectedButtons.clear();
                }
            }
        };

        // Establecer listener de clic para todos los botones
        for (Button button : allButtons) {
            button.setOnClickListener(buttonListener);
        }

        // Configurar botón de comprobación
        btnComprobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Marcar ejercicio como completado y cerrar actividad
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