package com.example.nhom8_lab04;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.TextViewOnReceiveContentListener;

import android.os.Bundle;
import android.widget.TextView;

public class Display extends AppCompatActivity {

    TextView tvHello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        tvHello = findViewById(R.id.tv_dp_hello);
        String name = getIntent().getStringExtra("username");
        tvHello.setText("Welcome back, "+ name);
    }
}