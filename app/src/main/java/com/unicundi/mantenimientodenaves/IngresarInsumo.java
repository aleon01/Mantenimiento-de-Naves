package com.unicundi.mantenimientodenaves;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;

import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.unicundi.mantenimientodenaves.model.insumo;

public class IngresarInsumo extends AppCompatActivity {

        private EditText codigoEdit,nombreEdit,cantidadEdit,valorEdit;
        FirebaseDatabase firebaseDatabase;
        DatabaseReference databaseReference;
        private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_ingresar_insumo);

            codigoEdit =findViewById(R.id.editTextCodigo);
            nombreEdit = findViewById(R.id.editTextNombre);
            cantidadEdit=findViewById(R.id.txt_Nombre);
            valorEdit = findViewById(R.id.txt_Cantidad);

            inicializarFirebase();

        }

        private void inicializarFirebase() {
            FirebaseApp.initializeApp(this);
            firebaseDatabase = FirebaseDatabase.getInstance();
            databaseReference = firebaseDatabase.getReference();

        }
        public void  registrarInsumo  (View view) {
            String codigo = codigoEdit.getText().toString();
            String nombre = nombreEdit.getText().toString();
            String cantidad = cantidadEdit.getText().toString();
            String valor = valorEdit.getText().toString();
            if  (codigo.isEmpty() ||  nombre.isEmpty() || cantidad.isEmpty() ||valor.isEmpty() ){
                validacion();

            }else{
                insumo i =new insumo (codigo, nombre, cantidad, valor);
                i .setCodigo(codigo);
                i.setNombre(nombre);
                i.setCantidad(cantidad);
                i.setValor(valor);
                Task<Void> insumos = databaseReference.child("insumos").child(i.getCodigo()).setValue(i);
                Intent inicio =new Intent(IngresarInsumo.this, IngresarInsumo.class);
                startActivity(inicio);
                //Toast.makeText(getApplicationContext(), "Insumo Registrado." + email, Toast.LENGTH_SHORT).show();
            }
            limpiarcajas();
        }



        private void limpiarcajas (){
            codigoEdit.setText("");
            nombreEdit.setText("");
            cantidadEdit.setText("");
            valorEdit.setText("");

            if(codigoEdit.equals("")){
                codigoEdit.setError("Requerido");
            } else if(nombreEdit.equals("")){
                nombreEdit.setError("Requerido");
            } else if(cantidadEdit.equals("")){
                cantidadEdit.setError("Requerido");
            } else if(valorEdit.equals("")){
                valorEdit.setError("Requerido");

            }
        }

        private void validacion(){
            String codigo = codigoEdit.getText().toString();
            String nombre = nombreEdit.getText().toString();
            String cantidad = cantidadEdit.getText().toString();
            String valor= valorEdit.getText().toString();

            if(codigo.equals("")){
                codigoEdit.setError("Requerido");
            } else if(nombre.equals("")){
                nombreEdit.setError("Requerido");
            } else if(cantidad.equals("")){
                cantidadEdit.setError("Requerido");
            } else if(valor.equals("")){
                valorEdit.setError("Requerido");

            }
        }
}