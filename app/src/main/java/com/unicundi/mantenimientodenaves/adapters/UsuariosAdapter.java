package com.unicundi.mantenimientodenaves.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.unicundi.mantenimientodenaves.R;
import com.unicundi.mantenimientodenaves.model.Usuarios;

import java.util.ArrayList;

public class UsuariosAdapter extends RecyclerView.Adapter<UsuariosAdapter.ViewHolder> {

    private int resourse;
    private ArrayList<Usuarios> usuariosList;

    public UsuariosAdapter(ArrayList<Usuarios> usuariosList, int resourse){
        this.usuariosList = usuariosList;
        this.resourse = resourse;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(resourse, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int index) {

        Usuarios usuarios = usuariosList.get(index);
        viewHolder.textViewUsuario.setText(usuarios.getNombre());
        viewHolder.textViewUsuarioApe.setText(usuarios.getApellido());

    }

    @Override
    public int getItemCount() {
        return usuariosList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewUsuario;
        private TextView textViewUsuarioApe;
        public View view;

        public ViewHolder(View view){
            super(view);

            this.view = view;
            this.textViewUsuario = (TextView) view.findViewById(R.id.textViewUsuarioNom);
            this.textViewUsuarioApe = (TextView) view.findViewById(R.id.textViewUsuarioApe);
        }
    }
}
