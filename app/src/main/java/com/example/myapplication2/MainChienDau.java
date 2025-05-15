package com.example.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainChienDau extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_chien_dau);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        FrameLayout btnA1 = findViewById(R.id.btnAi1);
        FrameLayout btnA2 = findViewById(R.id.btnAi2);
        FrameLayout btnA3 = findViewById(R.id.btnAi3);
        FrameLayout btnA4 = findViewById(R.id.btnAi4);

        btnA1.setOnClickListener(v -> openMainActivityWithMonsterId(1));
        btnA2.setOnClickListener(v -> openMainActivityWithMonsterId(2));
        btnA3.setOnClickListener(v -> openMainActivityWithMonsterId(3));
        btnA4.setOnClickListener(v -> openMainActivityWithMonsterId(4));
    }

    private void openMainActivityWithMonsterId(int i) {
        Intent intent = new Intent(MainChienDau.this, MainChienDau1.class);
        intent.putExtra("monster_id", i); // Truyền id con quái
        startActivity(intent);
    }
}