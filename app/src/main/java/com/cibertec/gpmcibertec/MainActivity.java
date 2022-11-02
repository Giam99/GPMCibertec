package com.cibertec.gpmcibertec;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnAgregarDocente, btnAgregarDoc2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAgregarDocente = (Button) findViewById(R.id.btnAgregarDocente);
        btnAgregarDoc2 = (Button) findViewById(R.id.btnAgregarDoc2);

        btnAgregarDocente.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, AgregarDocenteActivity.class));
            }
        });

        btnAgregarDoc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateDocFragment fm = new CreateDocFragment();
                fm.show(getSupportFragmentManager(), "Navegar a Fragment");
            }
        });
    }
}