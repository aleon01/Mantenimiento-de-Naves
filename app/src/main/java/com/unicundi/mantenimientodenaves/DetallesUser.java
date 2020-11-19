package com.unicundi.mantenimientodenaves;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DetallesUser extends AppCompatActivity {

    private TextView Nombre, Apellido;
    String Dato1, Dato2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_user);

        Nombre = findViewById(R.id.textV_Nombre);
        Apellido = findViewById(R.id.textV_Apellido);

        Nombre.setText(getIntent().getStringExtra("Nombre: "));
    }

    /*private void  getData(){
        if(getIntent().hasExtra("nombre") && getIntent().hasExtra("apellido")){
            Dato1 = getIntent().getStringExtra("nombre");
            Dato2 = getIntent().getStringExtra("apellido");
        }else{
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }

    private void setData(){
        Nombre.setText(Dato1);
        Apellido.setText(Dato2);
    }*/

}