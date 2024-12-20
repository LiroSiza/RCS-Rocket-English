package com.rcs_rocket_english.controls;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

import com.rcs_rocket_english.R;

public class Galaxy extends AppCompatButton {
    float escala = getResources().getDisplayMetrics().density;
    Paint pFondo = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint pTexto = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint pDesc = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint pProgBar = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint pProgBarTotal = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint pProg = new Paint(Paint.ANTI_ALIAS_FLAG);

    private LinearGradient gradient;
    private int width;
    private int height;
    private float progress = 0F;
    String descripción;

    public Galaxy(@NonNull Context context) {
        super(context);
        initialize();
    }

    public Galaxy(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public Galaxy(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    private void initialize() {
        // Setup the paint for the text
        pTexto.setColor(Color.WHITE);
        pTexto.setTextSize(80f);
        pTexto.setFakeBoldText(true);

        pDesc.setColor(Color.WHITE);
        pDesc.setTextSize(60f);
        pDesc.setFakeBoldText(true);

        pProg.setColor(Color.WHITE);
        pProg.setTextSize(60f);
        pProg.setFakeBoldText(true);


        Typeface typeface = getResources().getFont(R.font.agdasima_regular); // Reference the font

        pTexto.setTypeface(typeface);

        pDesc.setTypeface(typeface);

        pProg.setTypeface(typeface);

        //leer de un archivo el progreso de la galaxia
        descripción = "File not found";
    }

    public void setProgress(float progress) {
        if (progress > 1F)
            progress = 1F;
        else if (progress < 0F)
            progress = 0F;
        else
            this.progress = progress;
        invalidate();
    }

    public float getProgress() {
        return progress;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // Get the button's dimensions
        width = getWidth();
        height = getHeight();

        /*gradient = new LinearGradient(0, 0, width, height,
                Color.parseColor("#ff0000"), // Orange color
                Color.parseColor("#0000FF"), // Blue color
                Shader.TileMode.CLAMP);*/

        //Black and purple
        gradient = new LinearGradient(0, 0, width * 3, height * 3,
                Color.parseColor("#121212"), //black
                Color.parseColor("#ffffff"),
                Shader.TileMode.CLAMP);
        pFondo.setShader(gradient);
        RectF rect = new RectF(0, 0, width, height);
        canvas.drawRoundRect(rect, 40f, 40f, pFondo);

        //Progress bar total
        pProgBarTotal.setColor(Color.GRAY);
        RectF rect3 = new RectF(getWidth() / 20, getHeight() * 76 / 100, width * 83 / 100, height - 50);
        canvas.drawRoundRect(rect3, 40f, 40f, pProgBarTotal);

        //Progress bar
        if(progress != 0) {
            pProgBar.setColor(Color.YELLOW);
            RectF rect2 = new RectF(getWidth() / 20, getHeight() * 76 / 100, (width * 83 / 100) * progress, height - 50);
            canvas.drawRoundRect(rect2, 40f, 40f, pProgBar);
        }


        float textWidth = pTexto.measureText(getText().toString());
        float textX = getWidth() / 20; // Center the text horizontally
        float textY = getHeight() * 30 / 100; // Center the text vertically
        canvas.drawText(getText().toString(), textX, textY, pTexto);

        //descripcion


        float textWidth2 = pDesc.measureText(descripción);
        float textX2 = getWidth() / 20; // Center the text horizontally
        float textY2 = getHeight() * 48 / 100; // Center the text vertically
        canvas.drawText(descripción, textX2, textY2, pDesc);

        //progreso
        String progressText = (int) (progress * 100) + "%";
        float textWidth3 = pProg.measureText(progressText);
        float textX3 = width * 87 / 100; //  horizontally
        float textY3 = height - 65; //  vertically
        canvas.drawText(progressText, textX3, textY3, pProg);


    }
}

