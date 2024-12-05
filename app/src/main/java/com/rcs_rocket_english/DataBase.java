package com.rcs_rocket_english;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Random;
import java.util.Vector;

public class DataBase extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "datos";

    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("PRAGMA foreign_keys = ON;");

        db.execSQL("CREATE TABLE user (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "alias TEXT, " +
                "country TEXT, " +
                "experience INTEGER, " +
                "completed_levels INTEGER, " +
                "signup_date LONG)");

        db.execSQL("CREATE TABLE logros (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "description TEXT, " +
                "route INTEGER, " +
                "unlocked BOOLEAN)");

        db.execSQL("CREATE TABLE galaxy (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "description TEXT, " +
                "progress INTEGER)");

        db.execSQL("CREATE TABLE asteroids (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "galaxy_id INTEGER NOT NULL, " +
                "active BOOLEAN NOT NULL, " +
                "layout_id INTEGER NOT NULL, " +
                "FOREIGN KEY (galaxy_id) REFERENCES galaxy(id), " +
                "FOREIGN KEY (layout_id) REFERENCES layout(id))");

        db.execSQL("CREATE TABLE layout (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT)");

        db.execSQL("CREATE TABLE contA (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "layout_id INTEGER NOT NULL, " +
                "category TEXT NOT NULL, " +
                "title TEXT NOT NULL, " +
                "phrase TEXT, " +
                "option1 TEXT NOT NULL, " +
                "option2 TEXT NOT NULL, " +
                "option3 TEXT NOT NULL, " +
                "answer TEXT NOT NULL, " +
                "used BOOLEAN NOT NULL, " +
                "FOREIGN KEY (layout_id) REFERENCES layout(id))");

        db.execSQL("CREATE TABLE contB (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "layout_id INTEGER NOT NULL, " +
                "category TEXT NOT NULL, " +
                "title TEXT NOT NULL, " +
                "phrase TEXT, " +
                "text TEXT NOT NULL, " +
                "used BOOLEAN NOT NULL, " +
                "FOREIGN KEY (layout_id) REFERENCES layout(id))");

        db.execSQL("CREATE TABLE contC (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "layout_id INTEGER NOT NULL, " +
                "category TEXT NOT NULL, " +
                "title TEXT NOT NULL, " +
                "text1 TEXT, " +
                "text2 TEXT NOT NULL, " +
                "answer1 TEXT, " +
                "answer2 TEXT, " +
                "used BOOLEAN NOT NULL, " +
                "FOREIGN KEY (layout_id) REFERENCES layout(id))");

        db.execSQL("CREATE TABLE contD (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "layout_id INTEGER NOT NULL, " +
                "category TEXT NOT NULL, " +
                "title TEXT NOT NULL, " +
                "resource_string TEXT NOT NULL, " +
                "answer TEXT NOT NULL, " +
                "used BOOLEAN NOT NULL, " +
                "FOREIGN KEY (layout_id) REFERENCES layout(id))");



        db.execSQL("CREATE TABLE contE (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "layout_id INTEGER NOT NULL, " +
                "category TEXT NOT NULL, " +
                "title TEXT NOT NULL, " +
                "subtitle TEXT, " +
                "text TEXT NOT NULL, " +
                "option1 TEXT NOT NULL, " +
                "option2 TEXT NOT NULL, " +
                "option3 TEXT NOT NULL, " +
                "answer TEXT NOT NULL, " +
                "used BOOLEAN NOT NULL, " +
                "FOREIGN KEY (layout_id) REFERENCES layout(id))");

        db.execSQL("CREATE TABLE contF (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "layout_id INTEGER NOT NULL, " +
                "category TEXT NOT NULL, " +
                "title TEXT NOT NULL, " +
                "r1 TEXT NOT NULL, " +
                "r2 TEXT NOT NULL, " +
                "r3 TEXT NOT NULL, " +
                "r4 TEXT NOT NULL, " +
                "r5 TEXT NOT NULL, " +
                "r6 TEXT NOT NULL, " +
                "used BOOLEAN NOT NULL, " +
                "FOREIGN KEY (layout_id) REFERENCES layout(id))");

        //insercion de los datos

        db.execSQL("INSERT INTO galaxy (name, description, progress) VALUES ('Gramatica', 'Verbos, adjetivos, preposiciones y pronombres', 0)");
        db.execSQL("INSERT INTO galaxy (name, description, progress) VALUES ('Lectura', 'Lectura de textos', 0)");
        db.execSQL("INSERT INTO galaxy (name, description, progress) VALUES ('Vocabulario', 'Palabras y frases de uso diario', 0)");
        db.execSQL("INSERT INTO galaxy (name, description, progress) VALUES ('Escucha', 'Escucha y analiza conversaciones', 0)");


        db.execSQL("INSERT INTO layout (id) VALUES (1)");
        db.execSQL("INSERT INTO layout (id) VALUES (2)");
        db.execSQL("INSERT INTO layout (id) VALUES (3)");
        db.execSQL("INSERT INTO layout (id) VALUES (4)");
        db.execSQL("INSERT INTO layout (id) VALUES (5)");
        db.execSQL("INSERT INTO layout (id) VALUES (6)");

        for (int i = 1; i <= 6; i++) { // Assuming galaxy IDs are 1 to 6
            for (int j = 1; j <= 6; j++) { // Create 6 asteroids per galaxy
                int active = (j == 1) ? 1 : 0; // First asteroid active, others not
                db.execSQL("INSERT INTO asteroids (galaxy_id, active, layout_id) VALUES (" + i + ", " + active + ", " + j + ")");
            }
        }




        db.execSQL("INSERT INTO contA (layout_id, category, title, phrase, option1, option2, option3, answer, used) VALUES (6, 'Vocabulario', 'Selecciona la traduccion correcta', 'beber', 'drink', 'drive', 'drone', 'drink', 0)");
        db.execSQL("INSERT INTO contA (layout_id, category, title, phrase, option1, option2, option3, answer, used) VALUES (6, 'Vocabulario', 'Selecciona la traducción correcta', 'manzana', 'apple', 'apply', 'ample', 'apple', 0)");
        db.execSQL("INSERT INTO contA (layout_id, category, title, phrase, option1, option2, option3, answer, used) VALUES (6, 'Vocabulario', 'Selecciona la traducción correcta', 'correr', 'run', 'rush', 'row', 'run', 0)");
        db.execSQL("INSERT INTO contA (layout_id, category, title, phrase, option1, option2, option3, answer, used) VALUES (6, 'Vocabulario', 'Selecciona la traducción correcta', 'jardín', 'garden', 'yard', 'garland', 'garden', 0)");
        db.execSQL("INSERT INTO contA (layout_id, category, title, phrase, option1, option2, option3, answer, used) VALUES (6, 'Vocabulario', 'Selecciona la traducción correcta', 'libro', 'book', 'block', 'back', 'book', 0)");
        db.execSQL("INSERT INTO contA (layout_id, category, title, phrase, option1, option2, option3, answer, used) VALUES (6, 'Vocabulario', 'Selecciona la traducción correcta', 'flor', 'flower', 'flour', 'flow', 'flower', 0)");
        db.execSQL("INSERT INTO contA (layout_id, category, title, phrase, option1, option2, option3, answer, used) VALUES (6, 'Vocabulario', 'Selecciona la traducción correcta', 'puerta', 'door', 'dock', 'dorm', 'door', 0)");
        db.execSQL("INSERT INTO contA (layout_id, category, title, phrase, option1, option2, option3, answer, used) VALUES (6, 'Vocabulario', 'Selecciona la traducción correcta', 'luz', 'light', 'lit', 'load', 'light', 0)");
        db.execSQL("INSERT INTO contA (layout_id, category, title, phrase, option1, option2, option3, answer, used) VALUES (6, 'Vocabulario', 'Selecciona la traducción correcta', 'silla', 'chair', 'chart', 'charm', 'chair', 0)");
        db.execSQL("INSERT INTO contA (layout_id, category, title, phrase, option1, option2, option3, answer, used) VALUES (6, 'Vocabulario', 'Selecciona la traducción correcta', 'mesa', 'table', 'tale', 'tablet', 'table', 0)");
        db.execSQL("INSERT INTO contA (layout_id, category, title, phrase, option1, option2, option3, answer, used) VALUES (6, 'Vocabulario', 'Selecciona la traducción correcta', 'camino', 'path', 'patch', 'pact', 'path', 0)");
        db.execSQL("INSERT INTO contA (layout_id, category, title, phrase, option1, option2, option3, answer, used) VALUES (6, 'Vocabulario', 'Selecciona la traducción correcta', 'pluma', 'feather', 'pen', 'plot', 'feather', 0)");
        db.execSQL("INSERT INTO contA (layout_id, category, title, phrase, option1, option2, option3, answer, used) VALUES (6, 'Vocabulario', 'Selecciona la traducción correcta', 'niño', 'child', 'chill', 'chip', 'child', 0)");
        db.execSQL("INSERT INTO contA (layout_id, category, title, phrase, option1, option2, option3, answer, used) VALUES (6, 'Vocabulario', 'Selecciona la traducción correcta', 'agua', 'water', 'wait', 'ware', 'water', 0)");
        db.execSQL("INSERT INTO contA (layout_id, category, title, phrase, option1, option2, option3, answer, used) VALUES (6, 'Vocabulario', 'Selecciona la traducción correcta', 'sol', 'sun', 'son', 'sue', 'sun', 0)");
        db.execSQL("INSERT INTO contA (layout_id, category, title, phrase, option1, option2, option3, answer, used) VALUES (6, 'Vocabulario', 'Selecciona la traducción correcta', 'carro', 'car', 'cart', 'core', 'car', 0)");
        db.execSQL("INSERT INTO contA (layout_id, category, title, phrase, option1, option2, option3, answer, used) VALUES (6, 'Vocabulario', 'Selecciona la traducción correcta', 'casa', 'house', 'hose', 'hub', 'house', 0)");
        db.execSQL("INSERT INTO contA (layout_id, category, title, phrase, option1, option2, option3, answer, used) VALUES (6, 'Vocabulario', 'Selecciona la traducción correcta', 'montaña', 'mountain', 'mound', 'mint', 'mountain', 0)");
        db.execSQL("INSERT INTO contA (layout_id, category, title, phrase, option1, option2, option3, answer, used) VALUES (6, 'Vocabulario', 'Selecciona la traducción correcta', 'árbol', 'tree', 'treat', 'three', 'tree', 0)");

        db.execSQL("INSERT INTO contA (layout_id, category, title, phrase, option1, option2, option3, answer, used) VALUES (6, 'Gramatica', 'Selecciona la clasificación correcta', 'ran', 'Present', 'Past', 'Future', 'Past', 0)");
        db.execSQL("INSERT INTO contA (layout_id, category, title, phrase, option1, option2, option3, answer, used) VALUES (6, 'Gramatica', 'Selecciona la clasificación correcta', 'ran', 'Present', 'Past', 'Future', 'Past', 0)");
        db.execSQL("INSERT INTO contA (layout_id, category, title, phrase, option1, option2, option3, answer, used) VALUES (7, 'Gramatica', 'Selecciona la clasificación correcta', 'eat', 'Present', 'Past', 'Future', 'Present', 0)");
        db.execSQL("INSERT INTO contA (layout_id, category, title, phrase, option1, option2, option3, answer, used) VALUES (8, 'Gramatica', 'Selecciona la clasificación correcta', 'played', 'Present', 'Past', 'Future', 'Past', 0)");
        db.execSQL("INSERT INTO contA (layout_id, category, title, phrase, option1, option2, option3, answer, used) VALUES (9, 'Gramatica', 'Selecciona la clasificación correcta', 'will go', 'Present', 'Past', 'Future', 'Future', 0)");
        db.execSQL("INSERT INTO contA (layout_id, category, title, phrase, option1, option2, option3, answer, used) VALUES (10, 'Gramatica', 'Selecciona la clasificación correcta', 'was', 'Present', 'Past', 'Future', 'Past', 0)");
        db.execSQL("INSERT INTO contA (layout_id, category, title, phrase, option1, option2, option3, answer, used) VALUES (11, 'Gramatica', 'Selecciona la clasificación correcta', 'is playing', 'Present', 'Past', 'Future', 'Present', 0)");
        db.execSQL("INSERT INTO contA (layout_id, category, title, phrase, option1, option2, option3, answer, used) VALUES (12, 'Gramatica', 'Selecciona la clasificación correcta', 'played', 'Present', 'Past', 'Future', 'Past', 0)");
        db.execSQL("INSERT INTO contA (layout_id, category, title, phrase, option1, option2, option3, answer, used) VALUES (13, 'Gramatica', 'Selecciona la clasificación correcta', 'will eat', 'Present', 'Past', 'Future', 'Future', 0)");

        db.execSQL("INSERT INTO contA (layout_id, category, title, phrase, option1, option2, option3, answer, used) VALUES (6, 'Gramatica', 'Selecciona la clasificación correcta', 'had eaten', 'Present Perfect', 'Past Perfect', 'Future Perfect', 'Past Perfect', 0)");
        db.execSQL("INSERT INTO contA (layout_id, category, title, phrase, option1, option2, option3, answer, used) VALUES (7, 'Gramatica', 'Selecciona la clasificación correcta', 'will have finished', 'Present Perfect', 'Past Perfect', 'Future Perfect', 'Future Perfect', 0)");
        db.execSQL("INSERT INTO contA (layout_id, category, title, phrase, option1, option2, option3, answer, used) VALUES (8, 'Gramatica', 'Selecciona la clasificación correcta', 'had been running', 'Present Perfect Continuous', 'Past Perfect Continuous', 'Future Perfect Continuous', 'Past Perfect Continuous', 0)");
        db.execSQL("INSERT INTO contA (layout_id, category, title, phrase, option1, option2, option3, answer, used) VALUES (9, 'Gramatica', 'Selecciona la clasificación correcta', 'will have been studying', 'Present Perfect Continuous', 'Past Perfect Continuous', 'Future Perfect Continuous', 'Future Perfect Continuous', 0)");
        db.execSQL("INSERT INTO contA (layout_id, category, title, phrase, option1, option2, option3, answer, used) VALUES (10, 'Gramatica', 'Selecciona la clasificación correcta', 'would speak', 'Present', 'Past', 'Conditional', 'Conditional', 0)");
        db.execSQL("INSERT INTO contA (layout_id, category, title, phrase, option1, option2, option3, answer, used) VALUES (11, 'Gramatica', 'Selecciona la clasificación correcta', 'has been', 'Present', 'Past', 'Present Perfect', 'Present Perfect', 0)");
        db.execSQL("INSERT INTO contA (layout_id, category, title, phrase, option1, option2, option3, answer, used) VALUES (12, 'Gramatica', 'Selecciona la clasificación correcta', 'were going', 'Present Continuous', 'Past Continuous', 'Future Continuous', 'Past Continuous', 0)");
        db.execSQL("INSERT INTO contA (layout_id, category, title, phrase, option1, option2, option3, answer, used) VALUES (13, 'Gramatica', 'Selecciona la clasificación correcta', 'will be working', 'Present Continuous', 'Past Continuous', 'Future Continuous', 'Future Continuous', 0)");
        db.execSQL("INSERT INTO contA (layout_id, category, title, phrase, option1, option2, option3, answer, used) VALUES (14, 'Gramatica', 'Selecciona la clasificación correcta', 'had been studying', 'Present Perfect Continuous', 'Past Perfect Continuous', 'Future Perfect Continuous', 'Past Perfect Continuous', 0)");
        db.execSQL("INSERT INTO contA (layout_id, category, title, phrase, option1, option2, option3, answer, used) VALUES (15, 'Gramatica', 'Selecciona la clasificación correcta', 'will have been working', 'Present Perfect Continuous', 'Past Perfect Continuous', 'Future Perfect Continuous', 'Future Perfect Continuous', 0)");
        db.execSQL("INSERT INTO contA (layout_id, category, title, phrase, option1, option2, option3, answer, used) VALUES (16, 'Gramatica', 'Selecciona la clasificación correcta', 'would have gone', 'Present Perfect', 'Past Perfect', 'Conditional Perfect', 'Conditional Perfect', 0)");
        db.execSQL("INSERT INTO contA (layout_id, category, title, phrase, option1, option2, option3, answer, used) VALUES (17, 'Gramatica', 'Selecciona la clasificación correcta', 'have been eating', 'Present Perfect Continuous', 'Past Perfect Continuous', 'Future Perfect Continuous', 'Present Perfect Continuous', 0)");




        db.execSQL("INSERT INTO contB (layout_id, category, title, phrase, text, used) VALUES (2, 'Vocabulario', 'Traduce el texto', 'El cielo es azul', 'The sky is blue', 0)");
        db.execSQL("INSERT INTO contB (layout_id, category, title, phrase, text, used) VALUES (2, 'Vocabulario', 'Traduce el texto', 'Ella esta leyendo un libro', 'She is reading a book', 0)");
        db.execSQL("INSERT INTO contB (layout_id, category, title, phrase, text, used) VALUES (2, 'Vocabulario', 'Traduce el texto', 'Cierra la puerta', 'Close the door', 0)");
        db.execSQL("INSERT INTO contB (layout_id, category, title, phrase, text, used) VALUES (2, 'Vocabulario', 'Traduce el texto', 'El perro esta durmiendo', 'The dog is sleeping', 0)");
        db.execSQL("INSERT INTO contB (layout_id, category, title, phrase, text, used) VALUES (2, 'Vocabulario', 'Traduce el texto', 'Necesito un boligrafo', 'I need a pen', 0)");
        db.execSQL("INSERT INTO contB (layout_id, category, title, phrase, text, used) VALUES (2, 'Vocabulario', 'Traduce el texto', 'Ellos estan cocinando la cena', 'They are cooking dinner', 0)");
        db.execSQL("INSERT INTO contB (layout_id, category, title, phrase, text, used) VALUES (2, 'Vocabulario', 'Traduce el texto', '¿Donde esta el bano?', 'Where is the bathroom?', 0)");
        db.execSQL("INSERT INTO contB (layout_id, category, title, phrase, text, used) VALUES (2, 'Vocabulario', 'Traduce el texto', 'El reloj esta en la mesa', 'The clock is on the table', 0)");
        db.execSQL("INSERT INTO contB (layout_id, category, title, phrase, text, used) VALUES (2, 'Vocabulario', 'Traduce el texto', 'Estamos viendo una pelicula', 'We are watching a movie', 0)");
        db.execSQL("INSERT INTO contB (layout_id, category, title, phrase, text, used) VALUES (2, 'Vocabulario', 'Traduce el texto', 'Los pajaros estan volando', 'The birds are flying', 0)");
        db.execSQL("INSERT INTO contB (layout_id, category, title, phrase, text, used) VALUES (2, 'Vocabulario', 'Traduce el texto', 'Ella esta usando una computadora', 'She is using a computer', 0)");
        db.execSQL("INSERT INTO contB (layout_id, category, title, phrase, text, used) VALUES (2, 'Vocabulario', 'Traduce el texto', 'La ventana esta abierta', 'The window is open', 0)");
        db.execSQL("INSERT INTO contB (layout_id, category, title, phrase, text, used) VALUES (2, 'Vocabulario', 'Traduce el texto', 'El esta jugando al futbol', 'He is playing football', 0)");
        db.execSQL("INSERT INTO contB (layout_id, category, title, phrase, text, used) VALUES (2, 'Vocabulario', 'Traduce el texto', 'Escribe tu nombre aqui', 'Write your name here', 0)");
        db.execSQL("INSERT INTO contB (layout_id, category, title, phrase, text, used) VALUES (2, 'Vocabulario', 'Traduce el texto', 'Las estrellas estan brillando', 'The stars are shining', 0)");
        db.execSQL("INSERT INTO contB (layout_id, category, title, phrase, text, used) VALUES (2, 'Vocabulario', 'Traduce el texto', 'El bebe esta llorando', 'The baby is crying', 0)");
        db.execSQL("INSERT INTO contB (layout_id, category, title, phrase, text, used) VALUES (2, 'Vocabulario', 'Traduce el texto', 'Estoy escribiendo una carta', 'I am writing a letter', 0)");
        db.execSQL("INSERT INTO contB (layout_id, category, title, phrase, text, used) VALUES (2, 'Vocabulario', 'Traduce el texto', 'Ellos estan estudiando en la biblioteca', 'They are studying in the library', 0)");


        db.execSQL("INSERT INTO CONTC (layout_id, category, title, text1, text2, answer1, answer2, used) VALUES (3, 'Gramatica', 'Completa la oracion', 'Maria?', 'Maria', 'Is she', 'She is', 0)");
        db.execSQL("INSERT INTO CONTC (layout_id, category, title, text1, text2, answer1, answer2, used) VALUES (3, 'Gramatica', 'Completa la oracion', 'Pedro', 'Pedro?', 'He is', 'Is he', 0)");
        db.execSQL("INSERT INTO CONTC (layout_id, category, title, text1, text2, answer1, answer2, used) VALUES (3, 'Gramatica', 'Completa la oracion', 'married?', 'married', 'Are they', 'They are', 0)");
        db.execSQL("INSERT INTO CONTC (layout_id, category, title, text1, text2, answer1, answer2, used) VALUES (3, 'Gramatica', 'Completa la oracion', 'dog?', 'dog', 'Is it the', 'This is the', 0)");
        db.execSQL("INSERT INTO CONTC (layout_id, category, title, text1, text2, answer1, answer2, used) VALUES (3, 'Gramatica', 'Completa la oracion', 'your parents?', 'your parents', 'Are those', 'Those are', 0)");
        db.execSQL("INSERT INTO CONTC (layout_id, category, title, text1, text2, answer1, answer2, used) VALUES (3, 'Gramatica', 'Completa la oracion', 'the teacher?', 'the teacher', 'Is he', 'He is', 0)");
        db.execSQL("INSERT INTO CONTC (layout_id, category, title, text1, text2, answer1, answer2, used) VALUES (3, 'Gramatica', 'Completa la oracion', 'rather be working now?', 'be working now', 'Would you', 'You would', 0)");
        db.execSQL("INSERT INTO CONTC (layout_id, category, title, text1, text2, answer1, answer2, used) VALUES (3, 'Gramatica', 'Completa la oracion', 'been trying to solve this for hours?', 'tried to solve this for hours', 'Have you', 'You have', 0)");
        db.execSQL("INSERT INTO CONTC (layout_id, category, title, text1, text2, answer1, answer2, used) VALUES (3, 'Gramatica', 'Completa la oracion', 'this problem be more complicated than it seems?', 'more complicated than it seems', 'Might', 'It might', 0)");
        db.execSQL("INSERT INTO CONTC (layout_id, category, title, text1, text2, answer1, answer2, used) VALUES (3, 'Gramatica', 'Completa la oracion', 'be finally getting somewhere?', 'getting somewhere', 'Could it', 'It has been', 0)");
        db.execSQL("INSERT INTO CONTC (layout_id, category, title, text1, text2, answer1, answer2, used) VALUES (3, 'Gramatica', 'Completa la oracion', 'be rethinking our entire approach?', 'rethink our entire approach', 'Should we', 'We should', 0)");
        db.execSQL("INSERT INTO CONTC (layout_id, category, title, text1, text2, answer1, answer2, used) VALUES (3, 'Gramatica', 'Completa la oracion', 'plan this for months?', 'been planning this for months?', 'Would they', 'Have they', 0)");
        db.execSQL("INSERT INTO CONTC (layout_id, category, title, text1, text2, answer1, answer2, used) VALUES (3, 'Gramatica', 'Completa la oracion', 'done this earlier', 'been fine', 'If we had', 'We would have', 0)");
        db.execSQL("INSERT INTO CONTC (layout_id, category, title, text1, text2, answer1, answer2, used) VALUES (3, 'Gramatica', 'Completa la oracion', 'consider alternative solutions', 'been considering alternative solutions?', 'You might', 'Have you', 0)");
        db.execSQL("INSERT INTO CONTC (layout_id, category, title, text1, text2, answer1, answer2, used) VALUES (3, 'Gramatica', 'Completa la oracion', 'been working on this project for years?', ' be working on this project for years', 'Have you been', 'You will', 0)");
        db.execSQL("INSERT INTO CONTC (layout_id, category, title, text1, text2, answer1, answer2, used) VALUES (3, 'Gramatica', 'Completa la oracion', ' we overlooking some critical details?', 'overlooking some critical details', 'Are', 'are', 0)");



        db.execSQL("INSERT INTO contE (layout_id, category, title, subtitle, text, option1, option2, option3, answer, used) VALUES (5, 'Lectura','Sleecciona la afirmacion correcta', " +
                "'The solar system and its planets', " +
                "'The solar system consists of the Sun and the objects that orbit it, including eight planets. " +
                "These planets are divided into two groups: the inner planets and the outer planets. The inner planets—Mercury, Venus," +
                " Earth, and Mars—are closer to the Sun and have rocky surfaces. The outer planets—Jupiter, Saturn, Uranus, and Neptune—are much " +
                "larger and are primarily made of gas. While the inner planets are relatively small, the outer planets are known as gas giants due" +
                " to their enormous size and composition.', 'The outer planets are smaller than the inner planets and are made of rock.'," +
                "'The inner planets are closer to the Sun and have rocky surfaces.', " +
                "'The solar system has nine planets, all of which are the same size.','The inner planets are closer to the Sun and have rocky surfaces.', 0)");

        db.execSQL("INSERT INTO contE (layout_id, category, title, subtitle, text, option1, option2, option3, answer, used) VALUES (5, 'Lectura', 'Selecciona la afirmacion correcta', 'The water cycle', 'The water cycle describes how water circulates through the environment. It involves evaporation, where water turns into vapor, condensation, where vapor forms clouds, and precipitation, where water falls back to Earth as rain or snow.', 'The water cycle only happens in the ocean.', 'Evaporation is when water turns into vapor.', 'Precipitation turns water into clouds.', 'Evaporation is when water turns into vapor.', 0)");
        db.execSQL("INSERT INTO contE (layout_id, category, title, subtitle, text, option1, option2, option3, answer, used) VALUES (5, 'Lectura', 'Selecciona la afirmacion correcta', 'Renewable energy sources', 'Renewable energy sources, such as solar and wind power, are sustainable because they are replenished naturally, unlike fossil fuels.', 'Renewable energy is finite.', 'Solar power is a renewable energy source.', 'Fossil fuels are renewable energy.', 'Solar power is a renewable energy source.', 0)");
        db.execSQL("INSERT INTO contE (layout_id, category, title, subtitle, text, option1, option2, option3, answer, used) VALUES (5, 'Lectura', 'Selecciona la afirmacion correcta', 'Animal adaptations', 'Animals adapt to their environments through physical or behavioral changes. For example, desert animals have adaptations to conserve water, while polar animals have thick fur to stay warm.', 'All animals adapt in the same way.', 'Polar animals have thick fur to stay warm.', 'Desert animals have no adaptations.', 'Polar animals have thick fur to stay warm.', 0)");
        db.execSQL("INSERT INTO contE (layout_id, category, title, subtitle, text, option1, option2, option3, answer, used) VALUES (5, 'Lectura', 'Selecciona la afirmacion correcta', 'The food chain', 'The food chain represents the flow of energy through an ecosystem, starting with plants that produce their food and ending with top predators.', 'The food chain begins with predators.', 'Plants produce their food.', 'Energy does not flow in ecosystems.', 'Plants produce their food.', 0)");
        db.execSQL("INSERT INTO contE (layout_id, category, title, subtitle, text, option1, option2, option3, answer, used) VALUES (5, 'Lectura', 'Selecciona la afirmacion correcta', 'Earth’s layers', 'The Earth has layers: the crust, the mantle, and the core. The crust is the outermost layer, the mantle is semi-liquid, and the core is divided into liquid and solid parts.', 'The mantle is the outermost layer.', 'The core has liquid and solid parts.', 'The crust is semi-liquid.', 'The core has liquid and solid parts.', 0)");
        db.execSQL("INSERT INTO contE (layout_id, category, title, subtitle, text, option1, option2, option3, answer, used) VALUES (5, 'Lectura', 'Selecciona la afirmacion correcta', 'Volcanoes', 'Volcanoes are openings in the Earth’s crust where molten rock, gases, and ash escape. They are often found near tectonic plate boundaries.', 'Volcanoes release molten rock and gases.', 'Volcanoes only release gases.', 'Volcanoes are far from tectonic plates.', 'Volcanoes release molten rock and gases.', 0)");
        db.execSQL("INSERT INTO contE (layout_id, category, title, subtitle, text, option1, option2, option3, answer, used) VALUES (5, 'Lectura', 'Selecciona la afirmacion correcta', 'The human skeleton', 'The human skeleton provides structure, protects organs, and facilitates movement. It consists of 206 bones, including the skull, spine, ribs, and limbs.', 'The skeleton has 206 bones.', 'Bones protect only the brain.', 'The skeleton is made of muscles.', 'The skeleton has 206 bones.', 0)");
        db.execSQL("INSERT INTO contE (layout_id, category, title, subtitle, text, option1, option2, option3, answer, used) VALUES (5, 'Lectura', 'Selecciona la afirmacion correcta', 'Cloud types', 'Clouds are classified based on their appearance and altitude. For example, cumulus clouds are fluffy and low, while cirrus clouds are wispy and high.', 'Cumulus clouds are wispy.', 'Cirrus clouds are high and wispy.', 'All clouds are at the same altitude.', 'Cirrus clouds are high and wispy.', 0)");
        db.execSQL("INSERT INTO contE (layout_id, category, title, subtitle, text, option1, option2, option3, answer, used) VALUES (5, 'Lectura', 'Selecciona la afirmacion correcta', 'Energy conservation', 'Energy conservation means using energy efficiently and avoiding waste. Simple actions like turning off lights and using energy-efficient appliances help conserve energy.', 'Energy conservation means wasting energy.', 'Turning off lights helps conserve energy.', 'Energy-efficient appliances waste energy.', 'Turning off lights helps conserve energy.', 0)");
        db.execSQL("INSERT INTO contE (layout_id, category, title, subtitle, text, option1, option2, option3, answer, used) VALUES (5, 'Lectura', 'Selecciona la afirmacion correcta', 'The solar system', 'The solar system consists of the Sun and the objects orbiting it. There are eight planets, divided into rocky inner planets and gaseous outer planets.', 'The solar system has ten planets.', 'The inner planets are rocky.', 'The outer planets are rocky.', 'The inner planets are rocky.', 0)");
        db.execSQL("INSERT INTO contE (layout_id, category, title, subtitle, text, option1, option2, option3, answer, used) VALUES (5, 'Lectura', 'Selecciona la afirmacion correcta', 'Photosynthesis', 'Photosynthesis is the process by which plants convert sunlight into energy. It takes place in the chloroplasts of cells, using carbon dioxide and water to produce glucose and oxygen.', 'Photosynthesis occurs in animal cells.', 'Plants use sunlight to produce energy.', 'Oxygen is not produced in photosynthesis.', 'Plants use sunlight to produce energy.', 0)");
        db.execSQL("INSERT INTO contE (layout_id, category, title, subtitle, text, option1, option2, option3, answer, used) VALUES (5, 'Lectura', 'Selecciona la afirmacion correcta', 'The water cycle', 'The water cycle describes how water moves through evaporation, condensation, and precipitation. Water from oceans and lakes evaporates, forms clouds, and returns to the Earth as rain.', 'The water cycle has no evaporation.', 'Water returns to Earth as rain.', 'Clouds do not form in the water cycle.', 'Water returns to Earth as rain.', 0)");
        db.execSQL("INSERT INTO contE (layout_id, category, title, subtitle, text, option1, option2, option3, answer, used) VALUES (5, 'Lectura', 'Selecciona la afirmacion correcta', 'The digestive system', 'The digestive system breaks down food into nutrients that the body can absorb. Major organs include the stomach, intestines, and liver.', 'The digestive system does not break down food.', 'The stomach is part of the digestive system.', 'Nutrients are not absorbed in the digestive system.', 'The stomach is part of the digestive system.', 0)");
        db.execSQL("INSERT INTO contE (layout_id, category, title, subtitle, text, option1, option2, option3, answer, used) VALUES (5, 'Lectura', 'Selecciona la afirmacion correcta', 'Ecosystems', 'An ecosystem is a community of living organisms interacting with their physical environment. It includes plants, animals, and microorganisms.', 'Ecosystems have only animals.', 'Ecosystems include plants and animals.', 'Microorganisms are not part of ecosystems.', 'Ecosystems include plants and animals.', 0)");
        db.execSQL("INSERT INTO contE (layout_id, category, title, subtitle, text, option1, option2, option3, answer, used) VALUES (5, 'Lectura', 'Selecciona la afirmacion correcta', 'States of matter', 'Matter exists in different states: solid, liquid, and gas. These states are determined by the arrangement and movement of particles.', 'All matter is in a liquid state.', 'The states of matter are solid, liquid, and gas.', 'Particles do not move in matter.', 'The states of matter are solid, liquid, and gas.', 0)");
        db.execSQL("INSERT INTO contE (layout_id, category, title, subtitle, text, option1, option2, option3, answer, used) VALUES (5, 'Lectura', 'Selecciona la afirmacion correcta', 'Gravity', 'Gravity is the force that attracts objects toward one another. On Earth, it keeps us grounded and governs the motion of celestial bodies.', 'Gravity does not affect motion.', 'Gravity keeps objects grounded.', 'Gravity repels objects from the Earth.', 'Gravity keeps objects grounded.', 0)");
        db.execSQL("INSERT INTO contE (layout_id, category, title, subtitle, text, option1, option2, option3, answer, used) VALUES (5, 'Lectura', 'Selecciona la afirmacion correcta', 'Human senses', 'Humans have five primary senses: sight, hearing, taste, touch, and smell. Each sense is associated with specific organs.', 'Humans only have three senses.', 'Sight and hearing are human senses.', 'Smell is not a sense.', 'Sight and hearing are human senses.', 0)");

        db.execSQL("INSERT INTO CONTF (layout_id, category, title, r1, r2, r3, r4, r5, r6, used) VALUES (6, 'Vocabulary', 'Selecciona los pares', 'Hello', 'Hola', 'World', 'Mundo', 'Bye', 'Adios', 0)");
        db.execSQL("INSERT INTO CONTF (layout_id, category, title, r1, r2, r3, r4, r5, r6, used) VALUES (6, 'Vocabulary', 'Selecciona los pares', 'Casco', 'Helmet', 'Cabeza', 'Head', 'Llanta', 'Tire', 0)");
        db.execSQL("INSERT INTO CONTF (layout_id, category, title, r1, r2, r3, r4, r5, r6, used) VALUES (6, 'Vocabulary', 'Selecciona los pares', 'Palma', 'Palm', 'Mano', 'Hand', 'Árbol', 'Tree', 0)");
        db.execSQL("INSERT INTO CONTF (layout_id, category, title, r1, r2, r3, r4, r5, r6, used) VALUES (6, 'Vocabulary', 'Selecciona los pares', 'Pendiente', 'Earring', 'Colina', 'Hill', 'Tarea', 'Homework', 0)");
        db.execSQL("INSERT INTO CONTF (layout_id, category, title, r1, r2, r3, r4, r5, r6, used) VALUES (6, 'Vocabulary', 'Selecciona los pares', 'Planta', 'Plant', 'Piso', 'Floor', 'Zapato', 'Shoe', 0)");
        db.execSQL("INSERT INTO CONTF (layout_id, category, title, r1, r2, r3, r4, r5, r6, used) VALUES (6, 'Vocabulary', 'Selecciona los pares', 'Gato', 'Cat', 'Manta', 'Blanket', 'Perro', 'Dog', 0)");
        db.execSQL("INSERT INTO CONTF (layout_id, category, title, r1, r2, r3, r4, r5, r6, used) VALUES (6, 'Vocabulary', 'Selecciona los pares', 'Marca', 'Brand', 'Huella', 'Footprint', 'Sello', 'Seal', 0)");
        db.execSQL("INSERT INTO CONTF (layout_id, category, title, r1, r2, r3, r4, r5, r6, used) VALUES (6, 'Vocabulary', 'Selecciona los pares', 'Cubo', 'Bucket', 'Dado', 'Dice', 'Caja', 'Box', 0)");
        db.execSQL("INSERT INTO CONTF (layout_id, category, title, r1, r2, r3, r4, r5, r6, used) VALUES (6, 'Vocabulary', 'Selecciona los pares', 'Pata', 'Leg', 'Brazo', 'Arm', 'Cola', 'Tail', 0)");
        db.execSQL("INSERT INTO CONTF (layout_id, category, title, r1, r2, r3, r4, r5, r6, used) VALUES (6, 'Vocabulary', 'Selecciona los pares', 'Vela', 'Candle', 'Barco', 'Boat', 'Bandera', 'Flag', 0)");
        db.execSQL("INSERT INTO CONTF (layout_id, category, title, r1, r2, r3, r4, r5, r6, used) VALUES (6, 'Vocabulary', 'Selecciona los pares', 'Pala', 'Shovel', 'Remo', 'Paddle', 'Hacha', 'Axe', 0)");
        db.execSQL("INSERT INTO CONTF (layout_id, category, title, r1, r2, r3, r4, r5, r6, used) VALUES (6, 'Vocabulary', 'Selecciona los pares', 'Sobre', 'Envelope', 'Bajo', 'Under', 'Encima', 'On top', 0)");
        db.execSQL("INSERT INTO CONTF (layout_id, category, title, r1, r2, r3, r4, r5, r6, used) VALUES (6, 'Vocabulary', 'Selecciona los pares', 'Rayo', 'Lightning', 'Ola', 'Wave', 'Nube', 'Cloud', 0)");
        db.execSQL("INSERT INTO CONTF (layout_id, category, title, r1, r2, r3, r4, r5, r6, used) VALUES (6, 'Vocabulary', 'Selecciona los pares', 'Boca', 'Mouth', 'Diente', 'Tooth', 'Lengua', 'Tongue', 0)");
        db.execSQL("INSERT INTO CONTF (layout_id, category, title, r1, r2, r3, r4, r5, r6, used) VALUES (6, 'Vocabulary', 'Selecciona los pares', 'Rama', 'Branch', 'Hoja', 'Leaf', 'Raíz', 'Root', 0)");
        db.execSQL("INSERT INTO CONTF (layout_id, category, title, r1, r2, r3, r4, r5, r6, used) VALUES (6, 'Vocabulary', 'Selecciona los pares', 'Cerdo', 'Pig', 'Oveja', 'Sheep', 'Vaca', 'Cow', 0)");
        db.execSQL("INSERT INTO CONTF (layout_id, category, title, r1, r2, r3, r4, r5, r6, used) VALUES (6, 'Vocabulary', 'Selecciona los pares', 'Espina', 'Thorn', 'Flor', 'Flower', 'Semilla', 'Seed', 0)");
        db.execSQL("INSERT INTO CONTF (layout_id, category, title, r1, r2, r3, r4, r5, r6, used) VALUES (6, 'Vocabulary', 'Selecciona los pares', 'Nieve', 'Snow', 'Lluvia', 'Rain', 'Hielo', 'Ice', 0)");
        db.execSQL("INSERT INTO CONTF (layout_id, category, title, r1, r2, r3, r4, r5, r6, used) VALUES (6, 'Vocabulary', 'Selecciona los pares', 'Nervio', 'Nerve', 'Músculo', 'Muscle', 'Hueso', 'Bone', 0)");
        db.execSQL("INSERT INTO CONTF (layout_id, category, title, r1, r2, r3, r4, r5, r6, used) VALUES (6, 'Vocabulary', 'Selecciona los pares', 'Esquina', 'Corner', 'Orilla', 'Edge', 'Centro', 'Center', 0)");




    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void guardaDatos(String nombre, long fecha){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO "+DATABASE_NAME+" VALUES (null, '"+nombre+"', "+fecha+")");
    }


    @SuppressLint("Range")
    public void listaDatosGalaxy() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM galaxy order by id", null);
        while (cursor.moveToNext()) {
            // Get column values by their names or indices
             int id = cursor.getInt(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String description = cursor.getString(cursor.getColumnIndex("description"));
            int progress = cursor.getInt(cursor.getColumnIndex("progress"));

            // Log the values for the current row
            Log.d("mine", "ID: " + id + ", Name: " + name + ", Description: " + description + ", Progress: " + progress);
        }
        cursor.close();
    }

    @SuppressLint("Range")
    public void listAsteroids() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM asteroids ORDER BY id", null);
        Log.d("mine", "entered ");


        while (cursor.moveToNext()) {
            // Get column values by their names or indices
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            int galaxy_id = cursor.getInt(cursor.getColumnIndex("galaxy_id"));
            int active = cursor.getInt(cursor.getColumnIndex("active"));
            int layout_id = cursor.getInt(cursor.getColumnIndex("layout_id"));



            // Log the values for the current row
            Log.d("mine", "ID: " + id + ", galaxy_id: " + galaxy_id + ", active: " + active + ", layout_id: " + layout_id);
        }
        cursor.close();

        /*SQLiteDatabase db = this.getWritableDatabase();
        for (int i = 1; i <= 6; i++) { // Assuming galaxy IDs are 1 to 6
            for (int j = 1; j <= 6; j++) { // Create 6 asteroids per galaxy
                int active = (j == 1) ? 1 : 0; // First asteroid active, others not
                db.execSQL("INSERT INTO asteroids (galaxy_id, active, layout_id) VALUES (" + i + ", " + active + ", " + j + ")");
            }
        }

        db.close();*/



    }

    @SuppressLint("Range")
    public void listLayouts(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM layout ORDER BY id", null);

        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            Log.d("mine", "ID: " + id);
        }

        /*SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO layout (id) VALUES (6)");
        db.close();*/
    }

    @SuppressLint("Range")
    public void listContA(){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM contA ORDER BY id", null);
        while(cursor.moveToNext()){
            int layout_id = cursor.getInt(cursor.getColumnIndex("layout_id"));
            String category = cursor.getString(cursor.getColumnIndex("category"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String phrase = cursor.getString(cursor.getColumnIndex("phrase"));
            String option1 = cursor.getString(cursor.getColumnIndex("option1"));
            String option2 = cursor.getString(cursor.getColumnIndex("option2"));
            String option3 = cursor.getString(cursor.getColumnIndex("option3"));
            String answer = cursor.getString(cursor.getColumnIndex("answer"));
            int used = cursor.getInt(cursor.getColumnIndex("used"));

            Log.d("mine", "layout_id: " + layout_id + ", category: " + category + ", title: " + title + ", phrase: " + phrase + ", option1: " + option1 + ", option2: " + option2 + ", option3: " + option3 + ", answer: " + answer + ", used: " + used);

        }


    }
    @SuppressLint("Range")
    public void listContB(){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM contB ORDER BY id", null);
        while(cursor.moveToNext()){
            int layout_id = cursor.getInt(cursor.getColumnIndex("layout_id"));
            String category = cursor.getString(cursor.getColumnIndex("category"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String phrase = cursor.getString(cursor.getColumnIndex("phrase"));
            String text = cursor.getString(cursor.getColumnIndex("text"));
            int used = cursor.getInt(cursor.getColumnIndex("used"));

            Log.d("mine", "layout_id: " + layout_id + ", category: " + category + ", title: " + title + ", phrase: " + phrase + ", text: " + text + ", used: " + used);

        }

    }


    @SuppressLint("Range")
    public void listContC(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM contC ORDER BY id", null);
        while(cursor.moveToNext()){
            int layout_id = cursor.getInt(cursor.getColumnIndex("layout_id"));
            String category = cursor.getString(cursor.getColumnIndex("category"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String text1 = cursor.getString(cursor.getColumnIndex("text1"));
            String text2 = cursor.getString(cursor.getColumnIndex("text2"));
            String answer1 = cursor.getString(cursor.getColumnIndex("answer1"));
            String answer2 = cursor.getString(cursor.getColumnIndex("answer2"));
            int used = cursor.getInt(cursor.getColumnIndex("used"));

            Log.d("mine", "layout_id: " + layout_id + ", category: " + category + ", title: " + title + ", text1: " + text1 + ", text2: " + text2 + ", answer1: " + answer1 + ", answer2: " + answer2 + ", used: " + used);

        }

    }

    @SuppressLint("Range")
    public void listContD(){
        /*SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM contD ORDER BY id", null);
        while(cursor.moveToNext()){
            int layout_id = cursor.getInt(cursor.getColumnIndex("layout_id"));
            String category = cursor.getString(cursor.getColumnIndex("category"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String text1 = cursor.getString(cursor.getColumnIndex("text1"));
            String text2 = cursor.getString(cursor.getColumnIndex("text2"));
            String answer1 = cursor.getString(cursor.getColumnIndex("answer1"));
            String answer2 = cursor.getString(cursor.getColumnIndex("answer2"));
            int used = cursor.getInt(cursor.getColumnIndex("used"));

            Log.d("mine", "layout_id: " + layout_id + ", category: " + category + ", title: " + title + ", text1: " + text1 + ", text2: " + text2 + ", answer1: " + answer1 + ", answer2: " + answer2 + ", used: " + used);

        }*/
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO contD (layout_id, category, title, resource_string, answer, used) VALUES (4, 'Listening', 'Selecciona el orden correcto', '', '', 0)");
        /*db.execSQL("CREATE TABLE contD (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "layout_id INTEGER NOT NULL, " +
                "category TEXT NOT NULL, " +
                "title TEXT NOT NULL, " +
                "resource_string TEXT NOT NULL, " +
                "answer TEXT NOT NULL, " +
                "used BOOLEAN NOT NULL, " +
                "FOREIGN KEY (layout_id) REFERENCES layout(id))");*/




    }

    @SuppressLint("Range")
    public void listContE(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM contE ORDER BY id", null);
        while(cursor.moveToNext()) {
            int layout_id = cursor.getInt(cursor.getColumnIndex("layout_id"));
            String category = cursor.getString(cursor.getColumnIndex("category"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String subtitle = cursor.getString(cursor.getColumnIndex("subtitle"));
            String text = cursor.getString(cursor.getColumnIndex("text"));
            String option1 = cursor.getString(cursor.getColumnIndex("option1"));
            String option2 = cursor.getString(cursor.getColumnIndex("option2"));
            String option3 = cursor.getString(cursor.getColumnIndex("option3"));
            String answer = cursor.getString(cursor.getColumnIndex("answer"));
            int used = cursor.getInt(cursor.getColumnIndex("used"));

            Log.d("mine", "layout_id: " + layout_id + ", category: " + category + ", title: " + title + ", subtitle: " + subtitle + ", text: " + text + ", option1: " + option1 + ", option2: " + option2 + ", option3: " + option3 + ", answer: " + answer + ", used: " + used);

        }

    }
    @SuppressLint("Range")
    public void listContF(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM contF ORDER BY id", null);
        while(cursor.moveToNext()) {
            int layout_id = cursor.getInt(cursor.getColumnIndex("layout_id"));
            String category = cursor.getString(cursor.getColumnIndex("category"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String r1 = cursor.getString(cursor.getColumnIndex("r1"));
            String r2 = cursor.getString(cursor.getColumnIndex("r2"));
            String r3 = cursor.getString(cursor.getColumnIndex("r3"));
            String r4 = cursor.getString(cursor.getColumnIndex("r4"));
            String r5 = cursor.getString(cursor.getColumnIndex("r5"));
            String r6 = cursor.getString(cursor.getColumnIndex("r6"));
            int used = cursor.getInt(cursor.getColumnIndex("used"));

            Log.d("mine", "layout_id: " + layout_id + ", category: " + category + ", title: " + title + ", r1: " + r1 + ", r2: " + r2 + ", r3: " + r3 + ", r4: " + r4 + ", r5: " + r5 + ", r6: " + r6 + ", used: " + used);


        }

        /*db.execSQL("CREATE TABLE contF (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "layout_id INTEGER NOT NULL, " +
                "category TEXT NOT NULL, " +
                "title TEXT NOT NULL, " +
                "r1 TEXT NOT NULL, " +
                "r2 TEXT NOT NULL, " +
                "r3 TEXT NOT NULL, " +
                "r4 TEXT NOT NULL, " +
                "r5 TEXT NOT NULL, " +
                "r6 TEXT NOT NULL, " +
                "used BOOLEAN NOT NULL, " +
                "FOREIGN KEY (layout_id) REFERENCES layout(id))");*/
    }

    public void borraDatos(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+DATABASE_NAME);
    }

    public void que(){
        SQLiteDatabase db = this.getWritableDatabase();
        for (int i = 1; i <= 4; i++) { // Assuming galaxy IDs are 1 to 6
            for (int j = 1; j <= 6; j++) { // Create 6 asteroids per galaxy
                int active = (j == 1) ? 1 : 0; // First asteroid active, others not
                int layout_id = 0;
                if(i==1){
                    layout_id = 3;
                } else if (i==2) {
                    layout_id = 5;
                } else if (i==3) {
                    if(j%2 == 0)
                        layout_id = 6;
                    else {
                        Random random = new Random();
                        layout_id = random.nextInt(2) + 1;
                    }
                } else if (i==4) {
                    layout_id = 4;
                }
                db.execSQL("INSERT INTO asteroids (galaxy_id, active, layout_id) VALUES (" + i + ", " + active + ", " + layout_id + ")");
            }
        }
    }
    @SuppressLint("Range")
    // Método para obtener el progreso de una galaxia específica
    public int getProgressOfGalaxy(String galaxyName) {
        SQLiteDatabase db = this.getReadableDatabase();
        int progress = -1;  // Valor predeterminado si no se encuentra la galaxia

        // Definir la consulta
        String query = "SELECT progress FROM galaxy WHERE name = ?";
        Cursor cursor = db.rawQuery(query, new String[]{galaxyName});

        // Si encontramos la galaxia, obtenemos el progreso
        if (cursor != null && cursor.moveToFirst()) {
            progress = cursor.getInt(cursor.getColumnIndex("progress"));
            cursor.close();
        }

        return progress;
    }
}
