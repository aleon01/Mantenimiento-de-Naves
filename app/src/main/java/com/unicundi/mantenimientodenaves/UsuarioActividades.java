package com.unicundi.mantenimientodenaves;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.unicundi.mantenimientodenaves.model.Actividades_usuarios;

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
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_actividades);

        InsumosEdit = findViewById(R.id.EditInsumos);
        lv_actividades = findViewById(R.id.LV_Actividades);


        inicializarFirebase();
        listarActividades();

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


                    ArrayAdapterActividad = new ArrayAdapter<Actividades_usuarios>( UsuarioActividades.this, android.R.layout.simple_list_item_1, listaactividades);
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
        Toast.makeText(this, "Tarea Registrada como resuelta", Toast.LENGTH_SHORT).show();
        listarActividades();


    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }
}