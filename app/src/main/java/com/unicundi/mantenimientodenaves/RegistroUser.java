package com.unicundi.mantenimientodenaves;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.unicundi.mantenimientodenaves.model.Usuarios;

public class RegistroUser extends AppCompatActivity {

    Usuarios user = new Usuarios();
    private EditText nombreEdit, apellidoEdit, identifiacionEdit, direccionEdit, telefonoEdit, correoEdit, codigo, contrasenaEdit, confirmarcontrasenaEdit;

    final FirebaseAuth _auth = FirebaseAuth.getInstance();
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    Spinner documentoSpinner;
    String[] documento;

    private String nombre;
    private String apellido;
    private String tipoIdentificacion;
    private String identificacion;
    private String telefono;
    private String direccion;
    private String email;
    private String password;
    private String confcontrasena;
    private int rol;  //rol empleado
    private int estado;  //estado activo

    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    Usuarios usuarios = new Usuarios();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_user);

        nombreEdit = findViewById(R.id.editTextNombre);
        apellidoEdit = findViewById(R.id.editTextApellido);
        documentoSpinner = (Spinner) findViewById(R.id.tipoIdentificacionEdit);
        documento = getResources().getStringArray(R.array.tipodocumento);
        identifiacionEdit = findViewById(R.id.editTextIdentificacion);
        telefonoEdit = findViewById(R.id.editTextTelefono);
        direccionEdit = findViewById(R.id.editTextDireccion);
        correoEdit = findViewById(R.id.editTextCorreo);
        contrasenaEdit = findViewById(R.id.editTextContrasena);
        confirmarcontrasenaEdit = findViewById(R.id.editTextConfContrasena);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, documento);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        documentoSpinner.setAdapter(adapter);

        inicializarFirebase();
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public void registros(View view){
        nombre = nombreEdit.getText().toString();
        apellido = apellidoEdit.getText().toString();
        tipoIdentificacion = documentoSpinner.getSelectedItem().toString();
        identificacion = identifiacionEdit.getText().toString();
        telefono = telefonoEdit.getText().toString();
        direccion = direccionEdit.getText().toString();
        email = correoEdit.getText().toString();
        password = contrasenaEdit.getText().toString();
        confcontrasena = confirmarcontrasenaEdit.getText().toString();
        rol = 3;
        estado = 1;
        if(password.equals(confcontrasena)){
            if (nombre.isEmpty() || apellido.isEmpty() || identificacion.isEmpty() || telefono.isEmpty() || direccion.isEmpty() || email.isEmpty() || password.isEmpty()){
                validacion();
            }else {
                _auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegistroUser.this, new OnCompleteListener<AuthResult>(){
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                       if (task.isSuccessful()) {

                           user.setNombre(nombre);
                           user.setApellido(apellido);
                           user.setTidentificacion(tipoIdentificacion);
                           if (identificacion.length() >= 8 && identificacion.length() <= 10) {
                               user.setIdentifcacion(identificacion);
                               if (telefono.length() >= 7 && telefono.length() <= 10){
                                   user.setTelefono(telefono);
                                   user.setDireccion(direccion);
                                   if(Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                                       user.setCorreo(email);
                                       if(password.length() >= 6) {
                                           user.setContrasena(password);
                                           user.setRol(rol);
                                           user.setEstado(estado);
                                           databaseReference.child("usuarios").child(user.getNombre()).setValue(user);
                                           Intent inicio =new Intent(RegistroUser.this, MainActivity.class);
                                           startActivity(inicio);
                                           /**AlertDialog.Builder alerta = new AlertDialog.Builder(RegistroUser.this);
                                           alerta.setMessage("Registro Exitoso").setCancelable(true).setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                               @Override
                                               public void onClick(DialogInterface dialog, int which) {

                                               }
                                           });*/
                                           Toast.makeText(getApplicationContext(), "Usuario creado." + email, Toast.LENGTH_SHORT).show();
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

                       } else {
                          Toast.makeText(getApplicationContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                       }
                    }
                });
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

    /**public void tDocumento(){
        documentoSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                user.setTidentificacion(documento[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(RegistroUser.this, "Seleccione un item", Toast.LENGTH_SHORT).show();
            }
        });
    }*/
}