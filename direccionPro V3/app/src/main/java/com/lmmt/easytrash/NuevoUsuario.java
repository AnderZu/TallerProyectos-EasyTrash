package com.lmmt.easytrash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Pojo.Usuarios;
import Util.Util;

public class NuevoUsuario extends AppCompatActivity implements View.OnClickListener {
    EditText edtNombres, edtApellidosPater, edtApellidosMater, edtDni, edtTele, edtDireccion;
    Button btnGuardar;
    FirebaseDatabase database;
    DatabaseReference myDb;

    Util u;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_usuario);
        edtNombres = findViewById(R.id.edtNUNombre);
        edtApellidosPater = findViewById(R.id.edtNUApellidoPaterno);
        edtApellidosMater = findViewById(R.id.edtNUApellidoMaterno);
        edtDni = findViewById(R.id.edtNUDni);
        edtTele= findViewById(R.id.edtNUTelefono);
        edtDireccion = findViewById(R.id.edtNUDireccion);
        btnGuardar = findViewById(R.id.btnNUReguistrarUsuario);
        myDb = FirebaseDatabase.getInstance().getReference();

        btnGuardar.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {



        String nombres = edtNombres.getText().toString();
        String apellidoPaterno = edtApellidosPater.getText().toString();
        String apellidoMaterno = edtApellidosMater.getText().toString();
        String dni = edtDni.getText().toString();
        String telefono = edtTele.getText().toString();
        String direccion = edtDireccion.getText().toString();

        String id = myDb.push().getKey();
        Usuarios usuarios = new Usuarios(id, nombres, apellidoPaterno, apellidoMaterno, dni, telefono, direccion);
        myDb.child("Usuarios").child(dni).setValue(usuarios);
        Toast.makeText(this, "Usuario registrado", Toast.LENGTH_SHORT).show();
    }




    public void Inicio(View view) {
        Intent f = new Intent(NuevoUsuario.this, IniciarSecion.class);
        startActivity(f);
    }
}