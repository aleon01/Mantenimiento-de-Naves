package com.unicundi.mantenimientodenaves;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.unicundi.mantenimientodenaves.model.Usuarios;

public class Registro extends AppCompatActivity{

    private EditText nombreEdit, apellidoEdit, identifiacionEdit, direccionEdit, telefonoEdit, correoEdit, codigo, contrasenaEdit, confirmarcontrasenaEdit;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        nombreEdit = findViewById(R.id.editTextNombre);
        apellidoEdit = findViewById(R.id.editTextApellido);
        identifiacionEdit = findViewById(R.id.editTextIdentificacion);
        telefonoEdit = findViewById(R.id.editTextTelefono);
        direccionEdit = findViewById(R.id.editTextDireccion);
        correoEdit = findViewById(R.id.editTextCorreo);
        contrasenaEdit = findViewById(R.id.editTextContrasena);
        confirmarcontrasenaEdit = findViewById(R.id.editTextConfContrasena);

        inicializarFirebase();
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public void registrarEmpleado(View view) {
        String nombre = nombreEdit.getText().toString();
        String apellido = apellidoEdit.getText().toString();
        String identificacion = identifiacionEdit.getText().toString();
        String telefono = telefonoEdit.getText().toString();
        String direccion = direccionEdit.getText().toString();
        String correo = correoEdit.getText().toString();
        String contrasena = contrasenaEdit.getText().toString();
        String confcontrasena = confirmarcontrasenaEdit.getText().toString();
        int rol = 3; //rolempleado
        int estado = 1; //estado activo
        if(contrasena.equals(confcontrasena)){
            if (nombre.isEmpty() || apellido.isEmpty() || identificacion.isEmpty() || telefono.isEmpty() || direccion.isEmpty() || correo.isEmpty() || contrasena.isEmpty()){
                validacion();
            }else {
                Usuarios user = new Usuarios();
                user.setNombre(nombre);
                user.setApellido(apellido);
                if (identificacion.length() >= 8 && identificacion.length() <= 10) {
                    user.setIdentifcacion(identificacion);
                    if (telefono.length() >= 7 && telefono.length() <= 10){
                        user.setTelefono(telefono);
                        user.setDireccion(direccion);
                        if(Patterns.EMAIL_ADDRESS.matcher(correo).matches()){
                            user.setCorreo(correo);
                            if(contrasena.length() >= 6) {
                                user.setContrasena(contrasena);
                                user.setRol(rol);
                                user.setEstado(estado);
                                databaseReference.child("usuarios").child(user.getNombre()).setValue(user);
                                Toast.makeText(this, "Usuario agregado", Toast.LENGTH_SHORT).show();
                            }else{
                                contrasenaEdit.setError("Ingrese min 6 caracteres");
                                contrasenaEdit.requestFocus();
                            }
                        }else{
                            correoEdit.setError("Verifique el correo");
                            correoEdit.requestFocus();
                        }
                    }else{
                        telefonoEdit.setError("Verifique el telefono");
                        telefonoEdit.requestFocus();
                    }
                }else{
                        identifiacionEdit.setError("Verifique la Identificación");
                        identifiacionEdit.requestFocus();
                }
                limpiarCajas();
            }

        }else{
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
        }

    }

    private void limpiarCajas(){
        nombreEdit.setText("");
        apellidoEdit.setText("");
        identifiacionEdit.setText("");
        telefonoEdit.setText("");
        direccionEdit.setText("");
        correoEdit.setText("");
        contrasenaEdit.setText("");
        confirmarcontrasenaEdit.setText("");
    }

    private void validacion(){
        String nombre = nombreEdit.getText().toString();
        String apellido = apellidoEdit.getText().toString();
        String identificacion = identifiacionEdit.getText().toString();
        String telefono = telefonoEdit.getText().toString();
        String direccion = direccionEdit.getText().toString();
        String correo = correoEdit.getText().toString();
        String contrasena = contrasenaEdit.getText().toString();

        if(nombre.equals("")){
            nombreEdit.setError("Requerido");
        } else if(apellido.equals("")){
            apellidoEdit.setError("Requerido");
        } else if(identificacion.equals("")){
            identifiacionEdit.setError("Requerido");
        } else if(telefono.equals("")){
            telefonoEdit.setError("Requerido");
        } else if(direccion.equals("")){
            direccionEdit.setError("Requerido");
        } else if(correo.equals("")){
            correoEdit.setError("Requerido");
        } else if(contrasena.equals("")){
            contrasenaEdit.setError("Requerido");
        }
    }
}