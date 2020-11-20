package com.unicundi.mantenimientodenaves;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.unicundi.mantenimientodenaves.model.Actividades_usuarios;
import com.unicundi.mantenimientodenaves.model.insumo;

import java.util.ArrayList;
import java.util.List;


public class UsuarioActividades extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private String Uid;
    private List<Actividades_usuarios> listaactividades = new ArrayList<Actividades_usuarios>();
    ArrayAdapter<Actividades_usuarios> ArrayAdapterActividad;
    private EditText InsumosEdit;
    Actividades_usuarios ActividadSeleccionada;
    private ListView lv_actividades;
    DatabaseReference databaseReference;
    Spinner sppinerIns;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_actividades);
        getSupportActionBar().setTitle("TAREAS DIARIAS");
        //sppinerIns = findViewById(R.id.sppinerInsumo);
        InsumosEdit = findViewById(R.id.EditInsumos);
        lv_actividades = findViewById(R.id.LV_Actividades);
        databaseReference = FirebaseDatabase.getInstance().getReference();

        listarActividades();
        //listaInsumos();

        lv_actividades.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ActividadSeleccionada = (Actividades_usuarios) parent.getItemAtPosition(position);
            }
        });

    }

    private void listarActividades() {
        mAuth = FirebaseAuth.getInstance();
        Uid = mAuth.getCurrentUser().getUid();
        databaseReference.child("ActividadesDiarias").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listaactividades.clear();
                for(DataSnapshot objSnapshot : snapshot.getChildren()){
                    Actividades_usuarios actusers = objSnapshot.getValue(Actividades_usuarios.class);
                    if(actusers.getCodEmpleado().equals(Uid)&& actusers.getEstado().equals("Faltante")){
                        listaactividades.add(actusers);
                    }
                    ArrayAdapterActividad = new ArrayAdapter<Actividades_usuarios>( UsuarioActividades.this, R.layout.row, listaactividades);
                    lv_actividades.setAdapter(ArrayAdapterActividad);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void RegistrarActividad(View view){
        String insumos = InsumosEdit.getText().toString();
        Actividades_usuarios actUser = new Actividades_usuarios();
        actUser.setIdActividad(ActividadSeleccionada.getIdActividad());
        actUser.setNombreActividad(ActividadSeleccionada.getNombreActividad());
        actUser.setCodEmpleado(ActividadSeleccionada.getCodEmpleado());
        actUser.setNombre(ActividadSeleccionada.getNombre());
        actUser.setCodigo(ActividadSeleccionada.getCodigo());
        actUser.setEstado("Resuelta");
        actUser.setInsumos(insumos);
        databaseReference.child("ActividadesDiarias").child(actUser.getCodigo()).setValue(actUser);
        //Toast.makeText(this, "Tarea Registrada como resuelta", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(UsuarioActividades.this, Facturas.class);
        intent.putExtra("CodActividad:", actUser.getIdActividad());
        intent.putExtra("NombreAct:", actUser.getNombreActividad());
        intent.putExtra("Insumos:", actUser.getInsumos());
        startActivity(intent);
        //listarActividades();
    }

    /*public void listaInsumos(){
        final List<insumo> insumos = new ArrayList<>();
        databaseReference.child("insumos").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        String idInsumo = ds.getKey();
                        String nombreIns = ds.child("nombre").getValue().toString();
                        String cantidad = ds.child("cantidad").getValue().toString();
                        String valor = ds.child("valor").getValue().toString();
                        insumos.add(new insumo(idInsumo, nombreIns, cantidad, valor));
                    }
                    ArrayAdapter<insumo> arrayAdapter = new ArrayAdapter<>(UsuarioActividades.this, android.R.layout.simple_dropdown_item_1line, insumos);
                    sppinerIns.setAdapter(arrayAdapter);
                    sppinerIns.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }*/
}