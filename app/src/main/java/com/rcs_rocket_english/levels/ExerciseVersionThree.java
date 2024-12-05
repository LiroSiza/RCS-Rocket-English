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
import com.rcs_rocket_english.exc2Objects.RecordVocabulary;

import java.util.ArrayList;
import java.util.List;

public class ExerciseVersionThree extends AppCompatActivity {

    private DataBase db; // Instancia de la base de datos
    List<RecordVocabulary> records = new ArrayList<>(); // Lista de registros
    private int currentRecordIndex = 0; // Índice del registro actual
    private Button selectedButton = null; // Variable para almacenar el botón seleccionado
    private String correctAnswer; // Variable para almacenar la respuesta correcta

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ejercicio_f); // Asegúrate de que este sea tu layout

        String galaxyName = getIntent().getStringExtra("galaxy_name");
        boolean used = getIntent().getBooleanExtra("used", false); // Valor predeterminado false si no se pasa el extra

        // Obtener referencias de los botones del layout
        Button btnRespuesta1 = findViewById(R.id.btnRespuesta1);
        Button btnRespuesta2 = findViewById(R.id.btnRespuesta2);
        Button btnRespuesta3 = findViewById(R.id.btnRespuesta3);
        Button btnRespuesta4 = findViewById(R.id.btnRespuesta4);
        Button btnRespuesta5 = findViewById(R.id.btnRespuesta5);
        Button btnRespuesta6 = findViewById(R.id.btnRespuesta6);

        // Instanciar la base de datos
        db = new DataBase(this);
        // Llamar al método para obtener los registros
        if(used){ // Mostrar los usados
            records = db.getRecordsByCategoryContFUsed();
        }else{ // Mostrar los NO usados
            records = db.getRecordsByCategoryContF();
        }

        // Verificar si hay registros
        if (records != null && !records.isEmpty()) {
            // Llenar los botones con las respuestas de los registros obtenidos
            RecordVocabulary record = records.get(0); // Tomamos el primer registro (si hay más, puedes usar un bucle)

            // Asignar las respuestas a los botones
            btnRespuesta1.setText(record.getR1());
            btnRespuesta2.setText(record.getR2());
            btnRespuesta3.setText(record.getR3());
            btnRespuesta4.setText(record.getR4());
            btnRespuesta5.setText(record.getR5());
            btnRespuesta6.setText(record.getR6());

            // Si necesitas cambiar el título u otros elementos, puedes hacerlo también
            TextView titulo = findViewById(R.id.titulo);
            titulo.setText(record.getTitle()); // Suponiendo que quieres usar el título del record
        } else {
            // Si no se obtienen registros, puedes manejarlo aquí
            Log.d("mine", "No hay registros disponibles.");
        }
    }

}


