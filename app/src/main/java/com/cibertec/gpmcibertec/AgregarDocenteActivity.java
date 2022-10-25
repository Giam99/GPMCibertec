package com.cibertec.gpmcibertec;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class AgregarDocenteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_docente);

        this.setTitle("Agregar Docente");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}