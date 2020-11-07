package com.unicundi.mantenimientodenaves;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Button btn_administrador;
    Button btn_empleado;
    private FirebaseAuth Auth;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Auth= FirebaseAuth.getInstance();
        setContentView(R.layout.activity_main);
        preferences = getApplicationContext().getSharedPreferences("typeUser",MODE_PRIVATE);
        final SharedPreferences.Editor editor = preferences.edit();
        btn_administrador = findViewById(R.id.btn_Admi);
        btn_empleado = findViewById(R.id.btn_Empleado);
        btn_administrador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("usuarios","Administrador");
                editor.apply();
                login();
            }
        });
        btn_empleado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("usuarios","empleados");
                editor.apply();
                login();
            }
        });
    }

    private void login() {
        Intent intent = new Intent(MainActivity.this, Login.class);
        startActivity(intent);
    }
    // saber si ya inicio sesion o no
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = Auth.getCurrentUser();
    }

}