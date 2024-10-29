package com.rcs_rocket_english.controls;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.rcs_rocket_english.R;

public class FollowUpStadistics extends LinearLayout {
    private TextView titleTextView;
    private ImageView iconImageView;
    private TextView valueTextView;

    public FollowUpStadistics(Context context) {
        super(context);
        init(context);
    }

    public FollowUpStadistics(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TextView getTitleTextView() {
        return titleTextView;
    }

    public void setTitleTextView(String titleTextView) {
        this.titleTextView.setText(titleTextView);
    }

    public ImageView getIconImageView() {
        return iconImageView;
    }

    public void setIconImageView(int iconImageView) {
        this.iconImageView.setImageResource(iconImageView);
    }

    public TextView getValueTextView() {
        return valueTextView;
    }

    public void setValueTextView(String valueTextView) {
        this.valueTextView.setText(valueTextView);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.follow_up_stadistics, this, true);
        titleTextView = findViewById(R.id.titleTextView);
        iconImageView = findViewById(R.id.iconImageView);
        valueTextView = findViewById(R.id.valueTextView);
    }
}
