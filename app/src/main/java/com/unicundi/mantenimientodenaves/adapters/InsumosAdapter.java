package com.unicundi.mantenimientodenaves.adapters;
    import android.text.Layout;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.unicundi.mantenimientodenaves.R;
import com.unicundi.mantenimientodenaves.model.insumo;

import java.util.ArrayList;

    public class InsumosAdapter extends RecyclerView.Adapter<InsumosAdapter.ViewHolder> {

        private int resourse ;
        private ArrayList<insumo> insumoList ;
        //private Object ViewGroup;

        public InsumosAdapter(ArrayList<insumo> insumolist , int resourse) {
            this.insumoList =insumolist;
            this.resourse= resourse;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
            View view = LayoutInflater.from(parent.getContext()).inflate(resourse , parent , false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
            insumo Insumo  = insumoList.get(position);
            holder.textViewInsumo.setText(Insumo.getNombre());

        }

        @Override
        public int getItemCount(){return insumoList.size();  }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private TextView textViewInsumo;

            public View view ;

            public ViewHolder(View view) {
                super(view);
                this.view =view ;
             //   this.textViewInsumo= (TextView) view.findViewById(R.id.textViewInsumoNom);

            }
        }
}
