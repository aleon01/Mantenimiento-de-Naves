package com.unicundi.mantenimientodenaves;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.unicundi.mantenimientodenaves.adapters.UsuariosAdapter;
import com.unicundi.mantenimientodenaves.model.Usuarios;

import java.util.ArrayList;
import java.util.List;

public class ListaEmp extends AppCompatActivity {

    private DatabaseReference mDatabase;

    private UsuariosAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private ArrayList<Usuarios> mUsuarioList = new ArrayList<>();
    ArrayAdapter<Usuarios> arrayAdapterPersona;
    //private ListView listView;

    //FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_emp);

        //listView = findViewById(R.id.lv_datosUsuarios);
        mRecyclerView = (RecyclerView) findViewById(R.id.RecycleViewUser);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(ListaEmp.this));
        mDatabase = FirebaseDatabase.getInstance().getReference();

        getUsuarios();
    }

    public void getUsuarios(){
        Query dataQuery = mDatabase.child("usuarios").child("empleados");
        dataQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    mUsuarioList.clear();
                    for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()) {
                        String nombre = objSnaptshot.child("nombre").getValue().toString();
                        String apellido = objSnaptshot.child("apellido").getValue().toString();
                        mUsuarioList.add(new Usuarios(nombre, apellido));
                        // Usuarios p = objSnaptshot.getValue(Usuarios.class);
                        //listPerson.add(p);

                        //arrayAdapterPersona = new ArrayAdapter<Usuarios>(ListaEmp.this, android.R.layout.simple_list_item_1, listPerson);
                        //listView.setAdapter(arrayAdapterPersona);
                    }
                    mAdapter = new UsuariosAdapter(mUsuarioList, R.layout.usuario_view);
                    mRecyclerView.setAdapter(mAdapter);
                }else {
                    Log.e("no hay registros: ", "");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void eliminar(){
        /*FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "User account deleted.");
                        }
                    }
                });*/
    }
}