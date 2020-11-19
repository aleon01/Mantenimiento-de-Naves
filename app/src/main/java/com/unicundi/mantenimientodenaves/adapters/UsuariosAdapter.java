package com.unicundi.mantenimientodenaves.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.unicundi.mantenimientodenaves.DetallesUser;
import com.unicundi.mantenimientodenaves.ListaEmp;
import com.unicundi.mantenimientodenaves.R;
import com.unicundi.mantenimientodenaves.model.SelectUsuario;
import com.unicundi.mantenimientodenaves.model.Usuarios;

import java.util.ArrayList;

public class UsuariosAdapter extends RecyclerView.Adapter<UsuariosAdapter.ViewHolder>  {
//private View.OnClickListener listener;

    private int resourse;
    private ArrayList<Usuarios> usuariosList;
    private Context context;
    private SelectUsuario selectUsuario;

    public UsuariosAdapter(ArrayList<Usuarios> usuariosList, int resourse, Context context){
        this.usuariosList = usuariosList;
        this.resourse = resourse;
        this.context = context;
        this.selectUsuario = (ListaEmp)context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(resourse, viewGroup, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int index) {

        Usuarios usuarios = usuariosList.get(index);

        viewHolder.textViewUsuario.setText(usuarios.getNombre());
        viewHolder.textViewUsuarioApe.setText(usuarios.getApellido());

        viewHolder.textViewUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectUsuario.onItemClick(usuariosList.get(viewHolder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return usuariosList.size();
    }

     public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewUsuario;
        private TextView textViewUsuarioApe;
        public ImageView mDeleteImage;
        RelativeLayout mainLayout;
        public View view;

        public ViewHolder(View view){
            super(view);
            this.view = view;
            this.textViewUsuario = (TextView) view.findViewById(R.id.textViewUsuarioNom);
            this.textViewUsuarioApe = (TextView) view.findViewById(R.id.textViewUsuarioApe);
            this.mainLayout = view.findViewById(R.id.mainLayout);
            this.mDeleteImage = view.findViewById(R.id.imgDelete);

        }

    }
}
