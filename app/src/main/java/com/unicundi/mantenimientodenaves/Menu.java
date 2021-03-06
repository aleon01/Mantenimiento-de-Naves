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
        getSupportActionBar().setTitle("ADMINISTRADOR");
    }

    public void irAPerfil (View view){
        Intent perfil =new Intent(this, Perfil.class);
        startActivity(perfil);
    }

    public void irAInsumos (View view){
        Intent insumos =new Intent(this, IngresarInsumo.class);
        startActivity(insumos);
    }

    public void irAEmpleados (View view){
        Intent insumos =new Intent(this, ListaEmp.class);
        startActivity(insumos);
    }

    public void irATareas (View view){
        Intent actividadesA =new Intent(getApplicationContext(), ActividadesAdmin.class);
        startActivity(actividadesA);
    }

    public void irAsignarActividades (View view){
        Intent actividadesAsigna = new Intent(getApplicationContext(), AsignarActividadAdmi.class);
        startActivity(actividadesAsigna);
    }

    public void irAMantenimiento (View view){
        Intent fac =new Intent(this, FcaturasVisualizar.class);
        startActivity(fac);
    }
}