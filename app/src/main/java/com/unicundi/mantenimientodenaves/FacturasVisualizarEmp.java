package com.unicundi.mantenimientodenaves;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.unicundi.mantenimientodenaves.model.FacturaModel;

import java.util.ArrayList;
import java.util.List;

public class FacturasVisualizarEmp extends AppCompatActivity {

    private List<FacturaModel> listFactura = new ArrayList<FacturaModel>();
    ArrayAdapter<FacturaModel> arrayAdapterFactura;
    private FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    RecyclerView rView;

    ListView listViewF;
    String Uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facturas_visualizar_emp);

        getSupportActionBar().setTitle("FACTURAS");


        listViewF=findViewById(R.id.lt_factEmpleado);
        mAuth= FirebaseAuth.getInstance();
        Uid = mAuth.getCurrentUser().getUid();
        inicializarFirebase();
        listaDatos();
    }

    private void listaDatos() {
        mAuth = FirebaseAuth.getInstance();
        Uid = mAuth.getCurrentUser().getUid();
        databaseReference.child("facturas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listFactura.clear();
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    FacturaModel model = dataSnapshot.getValue(FacturaModel.class);
                    if(model.getCodPer().equals(Uid)){
                        listFactura.add(model);
                    }
                    arrayAdapterFactura = new ArrayAdapter<FacturaModel>(FacturasVisualizarEmp.this, R.layout.row, listFactura);
                    listViewF.setAdapter(arrayAdapterFactura);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void inicializarFirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }
}