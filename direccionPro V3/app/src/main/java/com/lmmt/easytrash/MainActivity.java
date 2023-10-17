package com.lmmt.easytrash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ISReguistrarse(View view) {
        Intent a = new Intent(MainActivity.this, NuevoUsuario.class);
        startActivity(a);
    }

    public void ISIniciarSecion(View view) {
        Intent b = new Intent(MainActivity.this, IniciarSecion.class);
        startActivity(b);
    }


}