package com.rcs_rocket_english.levels;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.rcs_rocket_english.DataBase;
import com.rcs_rocket_english.R;

import java.util.ArrayList;
import java.util.List;

public class ExerciseVersionTwo extends AppCompatActivity {

    private DataBase db; // Instancia de la base de datos
    private List<Record> records = new ArrayList<>(); // Lista de registros
    private int currentRecordIndex = 0; // Índice del registro actual

    private Button selectedButton = null; // Variable para almacenar el botón seleccionado

    private String correctAnswer; // Variable para almacenar la respuesta correcta

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ejercicio_e);

        // Se obtiene el nombre de la galaxia
        String galaxyName = getIntent().getStringExtra("galaxy_name");
        // En la actividad receptora:
        boolean used = getIntent().getBooleanExtra("used", false); // Valor predeterminado false si no se pasa el extra
        Log.d("Activity", "used value: " + used);

        // Convertir el valor booleano de 'used' a 1 o 0
        int usedValue = used ? 1 : 0;


        db = new DataBase(this); // Inicializar la base de datos

        // Llamar al método para obtener todos los registros de la tabla contE
        Cursor cursor = db.getFirstThreeRecordsByCategoryContE(galaxyName, usedValue);

        // Verificar si hay registros y agregarlos a la lista
        if (cursor != null && cursor.moveToFirst()) {
            do {
                // Obtener los valores del cursor y agregar los registros a la lista
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
                @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex("title"));
                @SuppressLint("Range") String subtitle = cursor.getString(cursor.getColumnIndex("subtitle"));
                @SuppressLint("Range") String text = cursor.getString(cursor.getColumnIndex("text"));
                @SuppressLint("Range") String option1 = cursor.getString(cursor.getColumnIndex("option1"));
                @SuppressLint("Range") String option2 = cursor.getString(cursor.getColumnIndex("option2"));
                @SuppressLint("Range") String option3 = cursor.getString(cursor.getColumnIndex("option3"));
                @SuppressLint("Range") String answer = cursor.getString(cursor.getColumnIndex("answer"));

                // Crear un nuevo registro y agregarlo a la lista
                records.add(new Record(id, title, subtitle, text, option1, option2, option3, answer));

            } while (cursor.moveToNext());
        }

        // Cargar el primer registro
        loadRecord(currentRecordIndex);

        // Configurar los botones de respuestas
        Button button1 = findViewById(R.id.respuesta1);
        Button button2 = findViewById(R.id.respuesta2);
        Button button3 = findViewById(R.id.respuesta3);

        button1.setOnClickListener(v -> selectAnswer(button1));
        button2.setOnClickListener(v -> selectAnswer(button2));
        button3.setOnClickListener(v -> selectAnswer(button3));

        // Configurar el botón de comprobar
        Button checkButton = findViewById(R.id.btnComprobar);
        checkButton.setOnClickListener(v -> {
            if (selectedButton != null) {
                // Verificar si la respuesta seleccionada es correcta
                if (selectedButton.getText().toString().equals(correctAnswer)) {
                    // Respuesta correcta
                    Toast.makeText(ExerciseVersionTwo.this, "¡Respuesta correcta!", Toast.LENGTH_SHORT).show();

                    // Actualizar el progreso o realizar la siguiente acción
                    if (currentRecordIndex < records.size() - 1) {
                        // Si no es el último ejercicio, pasar al siguiente
                        currentRecordIndex++;
                        loadRecord(currentRecordIndex);
                    } else {
                        // Si es el último ejercicio, actualizar la galaxia
                        db.updateProgressByName(galaxyName); // Actualizar el progreso en la base de datos

                        // Llamar a la función para marcar los tres ejercicios como "usados"
                        db.markExercisesAsUsed(records.get(0).getId(), records.get(1).getId(), records.get(2).getId());
                        finish(); // Cerrar la actividad
                    }

                } else {
                    // Respuesta incorrecta
                    Toast.makeText(ExerciseVersionTwo.this, "Respuesta incorrecta", Toast.LENGTH_SHORT).show();
                }

                // Deseleccionar el botón
                selectedButton.setBackgroundColor(getResources().getColor(R.color.defaultAnswer));
                selectedButton = null;
            } else {
                Toast.makeText(ExerciseVersionTwo.this, "Selecciona una respuesta", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Método para cargar un registro
    private void loadRecord(int index) {
        if (index >= 0 && index < records.size()) {
            // Obtener el registro actual
            Record currentRecord = records.get(index);

            // Mostrar los datos en los TextViews
            TextView titleTextView = findViewById(R.id.textViewTitle);
            TextView subtitleTextView = findViewById(R.id.textViewSubtitle);
            TextView textTextView = findViewById(R.id.textViewText);
            Button option1Button = findViewById(R.id.respuesta1);
            Button option2Button = findViewById(R.id.respuesta2);
            Button option3Button = findViewById(R.id.respuesta3);

            titleTextView.setText(currentRecord.getTitle());
            subtitleTextView.setText(currentRecord.getSubtitle());
            textTextView.setText(currentRecord.getText());
            option1Button.setText(currentRecord.getOption1());
            option2Button.setText(currentRecord.getOption2());
            option3Button.setText(currentRecord.getOption3());

            // Establecer la respuesta correcta
            correctAnswer = currentRecord.getAnswer();

            // Deseleccionar cualquier botón seleccionado anteriormente
            option1Button.setBackgroundColor(getResources().getColor(R.color.defaultAnswer));
            option2Button.setBackgroundColor(getResources().getColor(R.color.defaultAnswer));
            option3Button.setBackgroundColor(getResources().getColor(R.color.defaultAnswer));
        }
    }

    // Método para seleccionar una respuesta
    private void selectAnswer(Button button) {
        if (selectedButton != null) {
            // Deseleccionar el botón previamente seleccionado
            selectedButton.setBackgroundColor(getResources().getColor(R.color.defaultAnswer));
        }

        // Seleccionar el nuevo botón
        selectedButton = button;
        selectedButton.setBackgroundColor(getResources().getColor(R.color.selectedAnswer));
    }

    // Clase Record para representar los registros de contE
    private class Record {
        private int id;
        private String title, subtitle, text, option1, option2, option3, answer;

        public Record(int id, String title, String subtitle, String text, String option1, String option2, String option3, String answer) {
            this.id = id;
            this.title = title;
            this.subtitle = subtitle;
            this.text = text;
            this.option1 = option1;
            this.option2 = option2;
            this.option3 = option3;
            this.answer = answer;
        }

        public int getId() {
            return id;
        }

        public String getAnswer() {
            return answer;
        }

        public String getTitle() {
            return title;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public String getText() {
            return text;
        }

        public String getOption1() {
            return option1;
        }

        public String getOption2() {
            return option2;
        }

        public String getOption3() {
            return option3;
        }
    }
}
