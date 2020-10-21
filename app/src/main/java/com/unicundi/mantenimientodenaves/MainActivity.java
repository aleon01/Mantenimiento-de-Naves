package com.unicundi.mantenimientodenaves;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView correo;
    private TextView contraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void irARegistro (View view){
        Intent registro =new Intent(this, RegistroUser.class);
        startActivity(registro);
    }

    public void irAMenu (View view){
        Intent menu =new Intent(this, MainActivity.class);
        startActivity(menu);
    }
}