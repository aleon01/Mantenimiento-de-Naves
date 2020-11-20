package com.unicundi.mantenimientodenaves;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuEmpleado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_empleado);
        getSupportActionBar().setTitle("EMPLEADO");
    }

    public void irAPerfilEmpleado (View view){
        Intent perfilEmpleado =new Intent(this, PerfilEmpleado.class);
        startActivity(perfilEmpleado);
    }
    public void irAInsumos (View view){
        Intent insumos =new Intent(this, IngresarInsumo.class);
        startActivity(insumos);
    }
    public void irAFacturas (View view){
        Intent facturas =new Intent(this, FacturasVisualizarEmp.class);
        startActivity(facturas);
    }

    public void irATareas (View view){
        Intent Actividades =new Intent(getApplicationContext(), UsuarioActividades.class);
        startActivity(Actividades);
    }
}