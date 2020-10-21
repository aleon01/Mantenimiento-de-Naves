package com.unicundi.mantenimientodenaves;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.unicundi.mantenimientodenaves.model.Usuarios;

import java.util.Objects;

public class RegistroUser extends AppCompatActivity {

    private EditText correoEdit, nombreEdit, apellidoEdit, identifiacionEdit, direccionEdit, telefonoEdit, codigo, contrasenaEdit, confirmarcontrasenaEdit;
    private FirebaseAuth mAuth;

    private String email = "";
    private String password = "";
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    Usuarios usuarios = new Usuarios();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_user);

        mAuth = FirebaseAuth.getInstance();

        correoEdit = findViewById(R.id.editTextCorreo);
        contrasenaEdit = findViewById(R.id.editTextContrasena);

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void registros(View view){
        try {
            String id = Objects.requireNonNull(mAuth.getCurrentUser().getUid());
            mDatabase.child("usuarios").child(id).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        usuarios.setNombre(snapshot.child("nombre").getValue().toString());
                        usuarios.setApellido(snapshot.child("nombre").getValue().toString());
                        usuarios.setIdentifcacion(snapshot.child("nombre").getValue().toString());
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }catch (Exception e){

        }
    }
}