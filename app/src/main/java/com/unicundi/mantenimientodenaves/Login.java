package com.unicundi.mantenimientodenaves;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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


public class Login extends AppCompatActivity {

    SharedPreferences preferences;  // es igual a mpref

    private EditText correo;
    private EditText contrasena;
    private FirebaseAuth mAuth;
    DatabaseReference database;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("INICIAR SESION");

        correo = findViewById(R.id.txt_correo);
        contrasena = findViewById(R.id.txt_contrasena);
        btnLogin = findViewById(R.id.btn_Login);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();
        preferences = getApplicationContext().getSharedPreferences("typeUser",MODE_PRIVATE);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irAMenu();
            }
        });
    }

    private void irAMenu(){
        String correo1 = correo.getText().toString();
        String contrasena1 = contrasena.getText().toString();

        mAuth.signInWithEmailAndPassword(correo1, contrasena1)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String user = preferences.getString("usuarios", "");
                            if (user.equals("Administrador")) {
                                Intent intent = new Intent(Login.this, Menu.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            } else if (user.equals("empleados")) {
                                Intent intent = new Intent(Login.this, MenuEmpleado.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }

                        } else {
                            Toast.makeText(getApplicationContext(), "Contraseña incorrecta.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
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