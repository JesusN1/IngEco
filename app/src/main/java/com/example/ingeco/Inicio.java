package com.example.ingeco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Inicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
    }

    public void frances(View view) {
        Intent intent1 = new Intent(Inicio.this, MainActivity.class);
        startActivity(intent1);
    }

    public void aleman(View view) {
        Intent intent2 = new Intent(Inicio.this, MainActivity2.class);
        startActivity(intent2);
    }

    public void ingles(View view) {
        Intent intent3 = new Intent(Inicio.this, MainActivity2.class);
        startActivity(intent3);
    }

    public void flat(View view) {
        Intent intent4 = new Intent(Inicio.this, MainActivity4.class);
        startActivity(intent4);
    }
}