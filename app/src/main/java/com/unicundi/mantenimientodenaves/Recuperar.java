package com.unicundi.mantenimientodenaves;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthResult;

import com.google.firebase.auth.FirebaseAuth;



public class Recuperar extends AppCompatActivity {

    private EditText correo;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar);
        correo = findViewById(R.id.txt_correo);
        mAuth = FirebaseAuth.getInstance();
    }

    public void restaurar(View view){
        String correo1 = correo.getText().toString();
         if(!correo1.isEmpty()){
             resetPassword();
         }
         else{
             Toast.makeText(this,"Debe ingresar un correo valido",Toast.LENGTH_SHORT).show();
         }
    }
    private void resetPassword(){
        String correo1 = correo.getText().toString();
        mAuth.setLanguageCode("es");
        mAuth.sendPasswordResetEmail(correo1).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Recuperar.this, "Correo enviado", Toast.LENGTH_SHORT).show();
                    Intent inicio =new Intent(Recuperar.this, MainActivity.class);
                    startActivity(inicio);
                }else{
                    Toast.makeText(Recuperar.this, "No se ha podido restablecer la contraseña", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}