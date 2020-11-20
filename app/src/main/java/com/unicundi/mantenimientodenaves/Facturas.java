package com.unicundi.mantenimientodenaves;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.unicundi.mantenimientodenaves.model.FacturaModel;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class Facturas extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Date fechaact = new Date();
    TextView fechaA;
    int anio = 0, mes = 0, dia = 0;
    Random rdn = new Random();
    TextView numFactura, Actividades, Insumos;
    EditText  TotalFactura;
    private String nombre;
    private String apellido;

    TextView nomPersona;
    String Uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facturas);
        getSupportActionBar().setTitle("FACTURA");
        //-----------------------------------------------------
        //---Conexion Base de datos firebase a las tablas
        mAuth= FirebaseAuth.getInstance();
        mDatabase = (DatabaseReference) FirebaseDatabase.getInstance().getReference().child("usuarios").child("empleados");
        //-----------------------------------------------------
        nomPersona = findViewById(R.id.NamePerTV);
        //-----------------------------------------------------
        Uid = mAuth.getCurrentUser().getUid();
        mDatabase.child(Uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    nombre = snapshot.child("nombre").getValue().toString();
                    apellido = snapshot.child("apellido").getValue().toString();

                    nomPersona.setText(nombre + " " +apellido);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        numFactura = findViewById(R.id.FacturaNum);
        fechaA = findViewById(R.id.FechaActual);
        fechaA=(TextView)findViewById(R.id.FechaActual);
        fechaActual();
        numFactura=(TextView)findViewById(R.id.FacturaNum);
        numFactura();
        Actividades = findViewById(R.id.TextActividad);
        Insumos = findViewById(R.id.TextInsumos);

        Actividades.setText(getIntent().getStringExtra("NombreAct:"));
        Insumos.setText(getIntent().getStringExtra("Insumos:"));
        TotalFactura = findViewById(R.id.TotalFact);
        inicializarFirebase();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_agregar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        String nombrePer = nomPersona.getText().toString();
        String numFact =  numFactura.getText().toString();
        String fechaActual = fechaA.getText().toString();
        String actividad = Actividades.getText().toString();
        String insumos = Insumos.getText().toString();
        String cobro = TotalFactura.getText().toString();

        switch (item.getItemId()){
            case R.id.icon_check: {
                if(actividad.equals("")||cobro.equals("")){
                    validacion();
                    //Metodo sumar cantidad
                    //sumarCantidad ();
                }else{
                    FacturaModel model = new FacturaModel();

                    model.setCodPer(Uid);
                    model.setFacturaID(UUID.randomUUID().toString());
                    model.setNombrePer(nombrePer);
                    model.setNumFactura(numFact);
                    model.setFecha(fechaActual);
                    model.setDescripcion(actividad);
                    model.setCantidad(insumos);
                    model.setTotalFactura(cobro);
                    databaseReference.child("facturas").child(model.getFacturaID()).setValue(model);
                    Toast.makeText(this, "Guardar", Toast.LENGTH_LONG).show();
                    limparEditText();
                }
                break;
            }
            default:break;
        }
        return true;
    }

    //--- Limpiar EditText
    private void limparEditText() {
        TotalFactura.setText("");
    }

    //---Metodo validacion de los edit text
    private void validacion() {
        String cobro = TotalFactura.getText().toString();
        if(cobro.equals("")){
            TotalFactura.setError("Requerido");
        }
    }

    //Método de fecha actual
    public void fechaActual(){
        Calendar calendario = Calendar.getInstance();
        anio = calendario.get(Calendar.YEAR);
        mes = calendario.get(Calendar.MONTH);
        int dia = calendario.get(Calendar.DAY_OF_MONTH);
        fechaA.setText(dia+"/"+(mes+1)+"/"+anio);
    }

    //Método de número random para la factura
    public void numFactura(){
        numFactura.setText(String.valueOf(rdn.nextInt(9000)));
    }

    //---Metodo para inicializar firebase
    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }
}