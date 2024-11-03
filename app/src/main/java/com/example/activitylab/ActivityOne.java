package com.example.activitylab;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.jetbrains.annotations.NotNull;

public class ActivityOne extends AppCompatActivity {

    private int onCreateCounter = 0;
    private int onRestartCounter = 0;
    private int onStartCounter = 0;
    private int onResumeCounter = 0;

    private TextView onCreateCounterTextView;
    private TextView onRestartCounterTextView;
    private TextView onStartCounterTextView;
    private TextView onResumeCounterTextView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        this.onCreateCounterTextView = findViewById(R.id.activityOneOnCreateCounter);
        this.onRestartCounterTextView = findViewById(R.id.activityOneOnRestartCounter);
        this.onStartCounterTextView = findViewById(R.id.activityOneOnStartCounter);
        this.onResumeCounterTextView = findViewById(R.id.activityOneOnResumeCounter);
        this.button = findViewById(R.id.activityOneButton);

        this.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityOne.this, ActivityTwo.class);
                startActivity(intent);
            }
        });

        if (savedInstanceState != null) {
            this.onCreateCounter = savedInstanceState.getInt("onCreateCounter", 0);
            this.onRestartCounter = savedInstanceState.getInt("onRestartCounter", 0);
            this.onStartCounter = savedInstanceState.getInt("onStartCounter", 0);
            this.onResumeCounter = savedInstanceState.getInt("onResumeCounter", 0);
        }

        this.onCreateCounter++;
        displayCounts();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        this.onRestartCounter++;
        displayCounts();
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.onStartCounter++;
        displayCounts();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.onResumeCounter++;
        displayCounts();
    }

    @Override
    protected void onSaveInstanceState(@NotNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("onCreateCounter", onCreateCounter);
        savedInstanceState.putInt("onRestartCounter", onRestartCounter);
        savedInstanceState.putInt("onStartCounter", onStartCounter);
        savedInstanceState.putInt("onResumeCounter", onResumeCounter);
    }

    @SuppressLint("SetTextI18n")
    protected void displayCounts() {
        this.onCreateCounterTextView.setText(getString(R.string.oncreatecounter) + this.onCreateCounter);
        this.onRestartCounterTextView.setText(getString(R.string.onrestartcounter) + this.onRestartCounter);
        this.onStartCounterTextView.setText(getString(R.string.onstartcounter) + this.onStartCounter);
        this.onResumeCounterTextView.setText(getString(R.string.onresumecounter) + this.onResumeCounter);
    }
}