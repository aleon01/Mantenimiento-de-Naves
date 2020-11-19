package com.unicundi.mantenimientodenaves;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.unicundi.mantenimientodenaves.model.Actividades;
import com.unicundi.mantenimientodenaves.model.Actividades_usuarios;
import com.unicundi.mantenimientodenaves.model.Usuarios;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AsignarActividadAdmi extends AppCompatActivity {

    private List<Actividades> listaactividades = new ArrayList<Actividades>();
    private List<Usuarios> listaUsuarios = new ArrayList<Usuarios>();
    private List<Actividades_usuarios> listaActUsers = new ArrayList<Actividades_usuarios>();
    private ListView lv_actividades;
    private ListView lv_empleados;
    private ListView lv_ActividadesUsuarios;
    ArrayAdapter<Actividades> ArrayAdapterActividad;
    ArrayAdapter<Usuarios> ArrayAdapterUsuarios;
    ArrayAdapter<Actividades_usuarios> ArrayAdapterUsuariosAct;
    Actividades ActividadSeleccionada;
    Usuarios UsuariosSeleccionados;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignar_actividad_admi);

        lv_actividades = findViewById(R.id.LV_Actividades);
        lv_empleados = findViewById(R.id.LV_Empleados);
        lv_ActividadesUsuarios = findViewById(R.id.LV_ActividadesUsuarios);
        inicializarFirebase();
        listarActividades();
        listarEmpleados();
        listarActividadesUsuarios();

        lv_actividades.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ActividadSeleccionada = (Actividades) parent.getItemAtPosition(position);
                Toast.makeText(getBaseContext(), ActividadSeleccionada.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        lv_empleados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UsuariosSeleccionados = (Usuarios) parent.getItemAtPosition(position);
                Toast.makeText(getBaseContext(), UsuariosSeleccionados.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void listarActividadesUsuarios() {
        databaseReference.child("ActividadesDiarias").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listaActUsers.clear();
                for(DataSnapshot objSnapshot : snapshot.getChildren()){
                    String nombre = objSnapshot.child("nombre").getValue().toString();
                    String nombreA = objSnapshot.child("nombreActividad").getValue().toString();
                    listaActUsers.add(new Actividades_usuarios(nombre,nombreA));

                    ArrayAdapterUsuariosAct = new ArrayAdapter<Actividades_usuarios>( AsignarActividadAdmi.this, android.R.layout.simple_list_item_1, listaActUsers);
                    lv_ActividadesUsuarios.setAdapter(ArrayAdapterUsuariosAct);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void listarEmpleados() {
        databaseReference.child("usuarios").child("empleados").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listaUsuarios.clear();
                for(DataSnapshot objSnapshot : snapshot.getChildren()){
                    Usuarios user = objSnapshot.getValue(Usuarios.class);
                    listaUsuarios.add(user);

                    ArrayAdapterUsuarios = new ArrayAdapter<Usuarios>( AsignarActividadAdmi.this, android.R.layout.simple_list_item_1, listaUsuarios);
                    lv_empleados.setAdapter(ArrayAdapterUsuarios);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void listarActividades() {
        databaseReference.child("Actividad").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listaactividades.clear();
                for(DataSnapshot objSnapshot : snapshot.getChildren()){
                    Actividades act = objSnapshot.getValue(Actividades.class);
                    if(act.getEstadoActvidad() != 0) {
                        listaactividades.add(act);
                    }
                    ArrayAdapterActividad = new ArrayAdapter<Actividades>( AsignarActividadAdmi.this, android.R.layout.simple_list_item_1, listaactividades);
                    lv_actividades.setAdapter(ArrayAdapterActividad);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void AsignarTareas(View view){
        Actividades_usuarios actUser = new Actividades_usuarios();
        actUser.setIdActividad(ActividadSeleccionada.getIdActividad());
        actUser.setNombreActividad(ActividadSeleccionada.getNombreActividad());
        actUser.setCodEmpleado(UsuariosSeleccionados.getCod());
        actUser.setNombre(UsuariosSeleccionados.getNombre());
        actUser.setCodigo(UUID.randomUUID().toString());
        actUser.setInsumos("sin insumos - tarea no resuelta");
        actUser.setEstado("Faltante");
        databaseReference.child("ActividadesDiarias").child(actUser.getCodigo()).setValue(actUser);
        Toast.makeText(this, "Tarea Asignada", Toast.LENGTH_SHORT).show();

    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }
}