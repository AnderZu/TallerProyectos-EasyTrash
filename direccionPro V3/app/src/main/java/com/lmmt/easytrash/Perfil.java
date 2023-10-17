package com.lmmt.easytrash;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Pojo.Usuarios;

public class Perfil extends AppCompatActivity {

    ImageView imgCaptura;
    EditText edtPENombres, edtPEApellidosPater, edtPEApellidosMater, edtPEDni, edtPETele, edtPEDireccion;
    Button btnactualizar;
    private List<Usuarios> listUsuario = new ArrayList<Usuarios>();
    ArrayAdapter<Usuarios> arrayAdapterUsuarios;
    FirebaseDatabase database;
    private DatabaseReference myDb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        edtPENombres = findViewById(R.id.edtPNombre);
        edtPEApellidosPater = findViewById(R.id.edtPApellidoPA);
        edtPEApellidosMater = findViewById(R.id.edtPApellidoMA);
        edtPETele= findViewById(R.id.edtPTlefono);
        edtPEDni= findViewById(R.id.edtPAdni);
        edtPEDireccion = findViewById(R.id.edtPDIreccion);
        btnactualizar= findViewById(R.id.btnPActualizar);
        myDb = FirebaseDatabase.getInstance().getReference();
        imgCaptura = findViewById(R.id.imgPerfil);
        imgCaptura = findViewById(R.id.imgPerfil);
        Bundle recibirdni = getIntent().getExtras();
        myDb.child("Usuarios").child(recibirdni.getString("keydni")).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    String nombres = dataSnapshot.child("nombres").getValue().toString();
                    String apellidosPaterno = dataSnapshot.child("apellidosPaterno").getValue().toString();
                    String apellidoMaterno = dataSnapshot.child("apellidosMaterno").getValue().toString();
                    String telefono = dataSnapshot.child("telefono").getValue().toString();
                    String dni = dataSnapshot.child("dni").getValue().toString();
                    String direccion = dataSnapshot.child("direccion").getValue().toString();

                    edtPENombres.setText(nombres);
                    edtPEApellidosPater.setText(apellidosPaterno);
                    edtPEApellidosMater.setText(apellidoMaterno);
                    edtPETele.setText(telefono);
                    edtPEDni.setText(dni);
                    edtPEDireccion.setText(direccion);
                }
                else {
                    edtPENombres.setText("no llega nada");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        imgCaptura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent iCam = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(iCam,123);
            }

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 123)
        {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imgCaptura.setImageBitmap(photo);

        }
    }



    public void PActualizar(View view) {
        String nombres = edtPENombres.getText().toString();
        String apellidoPaterno = edtPEApellidosPater.getText().toString();
        String apellidoMaterno = edtPEApellidosMater.getText().toString();
        String dni = edtPEDni.getText().toString();
        String telefono = edtPETele.getText().toString();
        String direccion = edtPEDireccion.getText().toString();

        String id = myDb.push().getKey();
        Usuarios usuarios = new Usuarios(id, nombres, apellidoPaterno, apellidoMaterno, dni, telefono, direccion);
        myDb.child("Usuarios").child(dni).setValue(usuarios);

        Toast.makeText(Perfil.this, "Perfil Actualizado", Toast.LENGTH_SHORT).show();
    }

    public void PPerfil(View view) {
        Intent g = new Intent(Perfil.this, Perfil.class);
        startActivity(g);
    }

    public void PDireccion(View view) {
        Intent h = new Intent(Perfil.this, MapsActivity.class);
        startActivity(h);
    }

    public void PHorarios(View view) {
        Intent i = new Intent(Perfil.this, Horarios.class);
        startActivity(i);
    }
    public void PChat(View view) {
        Intent chat = new Intent(Perfil.this, Chatbot.class);
        startActivity(chat);
    }
}