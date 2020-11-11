package com.unicundi.mantenimientodenaves;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.unicundi.mantenimientodenaves.model.Usuarios;

public class PerfilEmpleado extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    String Uid;
    private String nombre;
    private String apellido;
    private String tipoIdentificacion;
    private String identificacion;
    private String telefono;
    private String direccion;
    private String email;
    private String password;
    private String rol;  //rol empleado
    private String estado;  //estado activo
    private TextView nombreEdit, apellidoEdit, identifiacionEdit;
    private EditText direccionEdit, telefonoEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_empleado);

        mAuth= FirebaseAuth.getInstance();
        mDatabase = (DatabaseReference) FirebaseDatabase.getInstance().getReference().child("usuarios").child("empleados");

        nombreEdit = findViewById(R.id.tV_Nombre);
        apellidoEdit = findViewById(R.id.tV_Apellido);
        identifiacionEdit = findViewById(R.id.tV_Documento);
        telefonoEdit = findViewById(R.id.editT_Telefono);
        direccionEdit = findViewById(R.id.editT_Direccion);

        Uid = mAuth.getCurrentUser().getUid();
        mDatabase.child(Uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    nombre = snapshot.child("nombre").getValue().toString();
                    apellido = snapshot.child("apellido").getValue().toString();
                    tipoIdentificacion = snapshot.child("tidentificacion").getValue().toString();
                    identificacion = snapshot.child("identifcacion").getValue().toString();
                    telefono = snapshot.child("telefono").getValue().toString();
                    direccion = snapshot.child("direccion").getValue().toString();
                    email = snapshot.child("correo").getValue().toString();
                    password = snapshot.child("contrasena").getValue().toString();
                    rol = snapshot.child("rol").getValue().toString();
                    estado = snapshot.child("estado").getValue().toString();

                    nombreEdit.setText(nombre);
                    apellidoEdit.setText(apellido);
                    identifiacionEdit.setText(identificacion);
                    telefonoEdit.setText(telefono);
                    direccionEdit.setText(direccion);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void modificar(View view){
        Usuarios user = new Usuarios();
        user.setCod(Uid);
        user.setNombre(nombre);
        user.setApellido(apellido);
        user.setTidentificacion(tipoIdentificacion);
        user.setIdentifcacion(identificacion);
        user.setTelefono(telefonoEdit.getText().toString().trim());
        user.setDireccion(direccionEdit.getText().toString().trim());
        user.setCorreo(email);
        user.setContrasena(password);
        mDatabase.child(user.getCod()).setValue(user);
        Toast.makeText(getApplicationContext(), "Datos actualizados.", Toast.LENGTH_SHORT).show();
    }

    public void cerrarSesion(View view){
        mAuth.signOut();
        finishAffinity();
        startActivity(new Intent(PerfilEmpleado.this, MainActivity.class));
    }
}