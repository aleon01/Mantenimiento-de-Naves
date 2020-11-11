package com.unicundi.mantenimientodenaves;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.SharedPreferences;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    Button btn_administrador;
    Button btn_empleado;
    private FirebaseAuth Auth;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Auth= FirebaseAuth.getInstance();

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


    public void irARegistro (View view){
        Intent registro =new Intent(this, RegistroUser.class);
        startActivity(registro);
    }

    public void irARecuperar (View view){
        Intent recuperar =new Intent(this, Recuperar.class);
        startActivity(recuperar);
    }

}