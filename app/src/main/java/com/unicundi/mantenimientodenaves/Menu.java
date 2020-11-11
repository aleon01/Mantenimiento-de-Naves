package com.unicundi.mantenimientodenaves;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void irAPerfil (View view){
        Intent perfil =new Intent(this, Perfil.class);
        startActivity(perfil);
    }

    public void irAInsumos (View view){
        Intent insumos =new Intent(this, Insumos.class);
        startActivity(insumos);
    }

    public void irAEmpleados (View view){
        Intent insumos =new Intent(this, ListaEmp.class);
        startActivity(insumos);
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