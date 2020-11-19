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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.unicundi.mantenimientodenaves.model.Actividades;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ActividadesAdmin extends AppCompatActivity {

    private List<Actividades> listaactividades = new ArrayList<Actividades>();
    ArrayAdapter<Actividades> ArrayAdapterActividad;
    private EditText Actividadedit, Descripcionedit;
    private ListView lv_actividades;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Actividades ActividadSeleccionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividades_admin);

        Actividadedit = findViewById(R.id.editTextActividad);
        Descripcionedit = findViewById(R.id.editTextDescripcion);
        lv_actividades = findViewById(R.id.LV_Actividades);
        inicializarFirebase();
        listarActividades();

        lv_actividades.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ActividadSeleccionada = (Actividades) parent.getItemAtPosition(position);
                Actividadedit.setText(ActividadSeleccionada.getNombreActividad());
                Descripcionedit.setText((ActividadSeleccionada.getDescripcionActividad()));
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
                    ArrayAdapterActividad = new ArrayAdapter<Actividades>( ActividadesAdmin.this, android.R.layout.simple_list_item_1, listaactividades);
                    lv_actividades.setAdapter(ArrayAdapterActividad);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public void EliminarActividades(View view){
        Actividades act = new Actividades();
        act.setIdActividad(ActividadSeleccionada.getIdActividad());
        act.setEstadoActvidad(0);
        act.setNombreActividad(Actividadedit.getText().toString().trim());
        act.setDescripcionActividad(Descripcionedit.getText().toString().trim());
        databaseReference.child("Actividad").child(act.getIdActividad()).setValue(act);
        limpiarCajas();

    }
    public void EditarActividad(View view) {
        Actividades act = new Actividades();
        act.setIdActividad(ActividadSeleccionada.getIdActividad());
        act.setNombreActividad(Actividadedit.getText().toString().trim());
        act.setEstadoActvidad(ActividadSeleccionada.getEstadoActvidad());
        act.setDescripcionActividad(Descripcionedit.getText().toString().trim());
        databaseReference.child("Actividad").child(act.getIdActividad()).setValue(act);
        limpiarCajas();
    }

    public void registrarActividad(View view) {
        String actividad = Actividadedit.getText().toString();
        String descripcion = Descripcionedit.getText().toString();
        int estado = 1;

        if(actividad.isEmpty() || descripcion.isEmpty()){
            validacion();
        }else{
            Actividades act = new Actividades();
            act.setEstadoActvidad(estado);
            act.setIdActividad(UUID.randomUUID().toString());
            act.setNombreActividad(actividad);
            act.setDescripcionActividad(descripcion);
            databaseReference.child("Actividad").child(act.getIdActividad()).setValue(act);
            Toast.makeText(this, "Actividad Agregada", Toast.LENGTH_SHORT).show();
        }
        limpiarCajas();

    }

    private void limpiarCajas(){
        Actividadedit.setText("");
        Descripcionedit.setText("");

    }

    private void validacion(){
        String actividad = Actividadedit.getText().toString();
        String descripcion = Descripcionedit.getText().toString();

        if(actividad.equals("")){
            Actividadedit.setError("Requerido");
        } else if(descripcion.equals("")) {
            Descripcionedit.setError("Requerido");
        }
    }
}