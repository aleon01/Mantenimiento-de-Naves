package com.unicundi.mantenimientodenaves;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.unicundi.mantenimientodenaves.adapters.UsuariosAdapter;
import com.unicundi.mantenimientodenaves.model.SelectUsuario;
import com.unicundi.mantenimientodenaves.model.Usuarios;

import java.util.ArrayList;

public class ListaEmp extends AppCompatActivity implements SelectUsuario {

    private DatabaseReference mDatabase;
    Usuarios user = new Usuarios();
    private UsuariosAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private ArrayList<Usuarios> mUsuarioList = new ArrayList<>();
    Usuarios userselect;
    View vista;
    Button fabEliminar;
    Activity actividad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_emp);

        mRecyclerView = (RecyclerView) findViewById(R.id.RecycleViewUser);
        fabEliminar= findViewById(R.id.id_Eliminar);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(ListaEmp.this));
        mDatabase = FirebaseDatabase.getInstance().getReference();

        Query dataQuery = mDatabase.child("usuarios").child("empleados");
        dataQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    mUsuarioList.clear();
                    for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()) {
                        //String id = objSnaptshot.child("nombre").getValue().toString();
                        String nombre = objSnaptshot.child("nombre").getValue().toString();
                        String apellido = objSnaptshot.child("apellido").getValue().toString();
                        mUsuarioList.add(new Usuarios(nombre, apellido));
                    }
                    mAdapter = new UsuariosAdapter(mUsuarioList, R.layout.usuario_view, ListaEmp.this);
                    mRecyclerView.setAdapter(mAdapter);
                }else {
                    Log.e("no hay registros: ", "");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        fabEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(actividad,"Debe seleccionar un Jugador para poder eliminarlo",Toast.LENGTH_SHORT).show();

            }
        });


    }

    public void eliminar(int position){
        mUsuarioList.remove(position);
        mAdapter.notifyItemRemoved(position);

    }

    @Override
    public void onItemClick(Usuarios usuarios) {
        Intent intent = new Intent(ListaEmp.this, DetallesUser.class);
        intent.putExtra("Nombre: ", usuarios.getNombre());
    }

    @Override
    public void onLongItemCLick(int position) {

    }
}