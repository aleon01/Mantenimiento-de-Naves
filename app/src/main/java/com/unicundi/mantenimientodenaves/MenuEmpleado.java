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
    }

    public void irAInsumos (View view){
        Intent insumos =new Intent(this, Insumos.class);
        startActivity(insumos);
    }
    public void irAFacturas (View view){
        Intent facturas =new Intent(this, Facturas.class);
        startActivity(facturas);
    }

    public void irATareas (View view){
        Intent tareas =new Intent(this, Tareas.class);
        startActivity(tareas);
    }

    public void irAMantenimiento (View view){
        Intent informes =new Intent(this, InformedeMantenimiento.class);
        startActivity(informes);
    }
}