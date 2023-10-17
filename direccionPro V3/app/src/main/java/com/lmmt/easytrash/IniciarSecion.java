package com.lmmt.easytrash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class IniciarSecion extends AppCompatActivity {
    EditText edtDni, edtTel;
    TextView Dnival,telval;
    Button btnIniciar;
    private DatabaseReference myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_secion);

        edtDni = findViewById(R.id.edtISDni);
        edtTel = findViewById(R.id.edtISTelefono);
        Dnival = findViewById(R.id.valdni);
        telval = findViewById(R.id.valtel);
        myDb = FirebaseDatabase.getInstance().getReference();
        //muestre un activity donde se puede cambiar la contraseña

        btnIniciar = findViewById(R.id.btnISIniciarSecion);
        // Si el email es tu codigo y el password es 123456
        // se muestra una interfce de Operaciones
        // se debe validar que el emailusuario y el password tenga el formato correcto

    }
    public void iniciarSesion(View view) {
        String userName = edtDni.getText().toString();
        String pass = edtTel.getText().toString();
        boolean verUser = false;
        boolean verPass = false;
        myDb.child("Usuarios").child(userName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    String dni = dataSnapshot.child("dni").getValue().toString();
                    Dnival.setText(dni);
                }
                else {
                    Dnival.setText("Los datos no existen");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        myDb.child("Usuarios").child(userName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    String telefono = dataSnapshot.child("telefono").getValue().toString();
                    telval.setText(telefono);
                }
                else {
                    telval.setText("Los datos no exiten");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        if (!userName.equals(Dnival.getText().toString())){
            edtDni.setError("Numero de DNI Incorrecto");

        }else {
            verUser = true;
        }

        if (!pass.equals(telval.getText().toString())){
            edtTel.setError("Numero de telefono Incorrecto");
        }else {
            verPass = true;
        }
        if(verUser && verPass) mostrarActivityMain();
    }

    private void mostrarActivityMain(){
        Bundle enviardni = new Bundle();
        enviardni.putString("keydni", edtDni.getText().toString());
        Intent f = new Intent(IniciarSecion.this, Perfil.class);
        f.putExtras(enviardni);
        startActivity(f);
    }
}