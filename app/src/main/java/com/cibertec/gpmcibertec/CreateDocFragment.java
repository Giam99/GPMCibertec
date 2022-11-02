package com.cibertec.gpmcibertec;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CreateDocFragment extends DialogFragment {

    //Declaracion de Variables
    Button btnAgregar;
    EditText codigo, nombres, apellidos;
    private FirebaseFirestore mfirestore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_create_doc, container, false);

        mfirestore = FirebaseFirestore.getInstance();

        codigo = (EditText) v.findViewById(R.id.txtCodigo);
        nombres = (EditText) v.findViewById(R.id.txtNombres);
        apellidos = (EditText) v.findViewById(R.id.txtApellidos);
        btnAgregar = (Button) v.findViewById(R.id.btnAgregar);

        btnAgregar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String codigoDoc = codigo.getText().toString().trim();
                String nombresDoc = nombres.getText().toString().trim();
                String apellidosDoc = apellidos.getText().toString().trim();

                if(codigoDoc.isEmpty() && nombresDoc.isEmpty() && apellidosDoc.isEmpty()){
                    Toast.makeText(getContext(),"Ingresar los Datos",Toast.LENGTH_SHORT).show();
                }else{
                    postDoc(codigoDoc,nombresDoc,apellidosDoc);
                }
            }
        });

        return v;
    }

    private void postDoc(String codigoDoc, String nombresDoc, String apellidosDoc) {
        Map<String, Object> map = new HashMap<>();
        map.put("codigo", codigoDoc);
        map.put("nombres", nombresDoc);
        map.put("apellidos", apellidosDoc);

        mfirestore.collection("docente").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getContext(),"Creado Exitosamente", Toast.LENGTH_SHORT).show();
                getDialog().dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(),"Error al Ingresar Datos", Toast.LENGTH_SHORT).show();
            }
        });
    }
}