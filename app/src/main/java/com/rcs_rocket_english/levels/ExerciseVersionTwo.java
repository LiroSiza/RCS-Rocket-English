package com.rcs_rocket_english.levels;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ejercicio_e);

        // Se obtiene el nombre de la galaxia
        String galaxyName = getIntent().getStringExtra("galaxy_name");

        db = new DataBase(this); // Inicializar la base de datos

        // Llamar al método para obtener todos los registros de la tabla contE
        Cursor cursor = db.getFirstThreeRecordsByCategoryContE(galaxyName);

        // Verificar si hay registros y agregarlos a la lista
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String subtitle = cursor.getString(cursor.getColumnIndex("subtitle"));
                String text = cursor.getString(cursor.getColumnIndex("text"));
                String option1 = cursor.getString(cursor.getColumnIndex("option1"));
                String option2 = cursor.getString(cursor.getColumnIndex("option2"));
                String option3 = cursor.getString(cursor.getColumnIndex("option3"));
                String answer = cursor.getString(cursor.getColumnIndex("answer"));

                // Crear un nuevo registro y agregarlo a la lista
                Record record = new Record(title, subtitle, text, option1, option2, option3, answer);
                records.add(record);

            } while (cursor.moveToNext());
        }

        // Configurar la interfaz con el primer registro
        setRecordData(currentRecordIndex);

        // Configurar los botones de respuestas
        Button button1 = findViewById(R.id.respuesta1);
        Button button2 = findViewById(R.id.respuesta2);
        Button button3 = findViewById(R.id.respuesta3);

        button1.setOnClickListener(v -> selectAnswer(button1));
        button2.setOnClickListener(v -> selectAnswer(button2));
        button3.setOnClickListener(v -> selectAnswer(button3));

        Button checkButton = findViewById(R.id.btnComprobar);
        checkButton.setOnClickListener(v -> checkAnswer());
    }

    // Método para seleccionar la respuesta
    private void selectAnswer(Button selected) {
        // Desmarcar el botón seleccionado previamente
        if (selectedButton != null) {
            selectedButton.setBackgroundColor(getResources().getColor(R.color.defaultAnswer)); // Cambiar a color original
        }

        // Marcar el nuevo botón seleccionado
        selected.setBackgroundColor(getResources().getColor(R.color.selectedAnswer)); // Cambiar a color seleccionado
        selectedButton = selected;
    }

    // Método para verificar la respuesta
    private void checkAnswer() {
        if (selectedButton != null) {
            // Obtener la respuesta correcta del registro actual
            String correctAnswer = records.get(currentRecordIndex).getAnswer();
            String selectedAnswer = selectedButton.getText().toString();

            // Verificar si la respuesta es correcta
            if (selectedAnswer.equals(correctAnswer)) {
                Toast.makeText(this, "¡Respuesta correcta!", Toast.LENGTH_SHORT).show();

                // Desmarcar el botón seleccionado
                selectedButton.setBackgroundColor(getResources().getColor(R.color.defaultAnswer)); // Cambiar a color original
                selectedButton = null;

                // Avanzar al siguiente registro si existe
                if (currentRecordIndex < records.size() - 1) {
                    currentRecordIndex++;
                    setRecordData(currentRecordIndex);
                } else {
                    Toast.makeText(this, "¡Felicidades, completaste todos los niveles!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Respuesta incorrecta, inténtalo de nuevo.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Por favor selecciona una respuesta", Toast.LENGTH_SHORT).show();
        }
    }

    // Método para actualizar los TextView con los datos del registro actual
    private void setRecordData(int recordIndex) {
        // Obtener el registro actual
        Record record = records.get(recordIndex);

        // Obtener las referencias a los TextView
        TextView titleTextView = findViewById(R.id.textView21);
        TextView subtitleTextView = findViewById(R.id.textView22);
        TextView textTextView = findViewById(R.id.textView23);

        // Establecer los textos de los TextView
        titleTextView.setText(record.getTitle());
        subtitleTextView.setText(record.getSubtitle());
        textTextView.setText(record.getText());

        // Establecer las opciones de respuesta en los botones
        Button button1 = findViewById(R.id.respuesta1);
        Button button2 = findViewById(R.id.respuesta2);
        Button button3 = findViewById(R.id.respuesta3);

        button1.setText(record.getOption1());
        button2.setText(record.getOption2());
        button3.setText(record.getOption3());
    }

    // Clase para representar un registro
    private static class Record {
        private final String title;
        private final String subtitle;
        private final String text;
        private final String option1;
        private final String option2;
        private final String option3;
        private final String answer;

        public Record(String title, String subtitle, String text, String option1, String option2, String option3, String answer) {
            this.title = title;
            this.subtitle = subtitle;
            this.text = text;
            this.option1 = option1;
            this.option2 = option2;
            this.option3 = option3;
            this.answer = answer;
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

        public String getAnswer() {
            return answer;
        }
    }
}
