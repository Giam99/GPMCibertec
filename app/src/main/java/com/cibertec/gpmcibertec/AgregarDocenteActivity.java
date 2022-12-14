package com.cibertec.gpmcibertec;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AgregarDocenteActivity extends AppCompatActivity {

    //Declaracion de Variables
    Button btnAgregar;
    EditText codigo, nombres, apellidos;
    private FirebaseFirestore mfirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_docente);

        //Titulo del Layout
        this.setTitle("Agregar Docente");
        //Retornar metodo
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Llamando a la base de datos
        mfirestore = FirebaseFirestore.getInstance();

        codigo = (EditText) findViewById(R.id.txtCodigo);
        nombres = (EditText) findViewById(R.id.txtNombres);
        apellidos = (EditText) findViewById(R.id.txtApellidos);
        btnAgregar = (Button) findViewById(R.id.btnAgregar);

        btnAgregar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String codigoDoc = codigo.getText().toString().trim();
                String nombresDoc = nombres.getText().toString().trim();
                String apellidosDoc = apellidos.getText().toString().trim();

                if(codigoDoc.isEmpty() && nombresDoc.isEmpty() && apellidosDoc.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Ingresar los Datos",Toast.LENGTH_SHORT).show();
                }else{
                    postDoc(codigoDoc,nombresDoc,apellidosDoc);
                }
            }
        });
    }

    //Metodo para Ingresar Datos de Docente a la BD
    private void postDoc(String codigoDoc, String nombresDoc, String apellidosDoc) {
        Map<String, Object> map = new HashMap<>();
        map.put("codigo", codigoDoc);
        map.put("nombres", nombresDoc);
        map.put("apellidos", apellidosDoc);

        mfirestore.collection("docente").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getApplicationContext(),"Creado Exitosamente", Toast.LENGTH_SHORT).show();
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"Error al Ingresar Datos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Flecha para retroceder
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}