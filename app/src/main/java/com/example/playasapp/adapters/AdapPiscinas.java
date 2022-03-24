package com.example.playasapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.playasapp.Peds.Piscina;
import com.example.playasapp.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class AdapPiscinas extends RecyclerView.Adapter<AdapPiscinas.PiscinasHolder>{

    private Context context;
    public List<Piscina> piscina;
    ThumbListener viewModel;

    public AdapPiscinas(Context context, List<Piscina> piscina, ThumbListener viewModel){
        this.context = context;
        this.piscina = piscina;
        this.viewModel = viewModel;
        }

    public AdapPiscinas(ArrayList<Piscina> piscinaArrayList, Context context) {
        this.piscina = piscinaArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public PiscinasHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_pools, parent, false);
        return new PiscinasHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PiscinasHolder holder, int position) {
        Piscina piscinas = piscina.get(position);
        holder.txtNombrePiscina.setText(piscinas.getNombrePiscina());
        holder.descriptPiscina.setText(piscinas.getDescripcionPiscina());
        holder.ubicacionPiscina.setText(piscinas.getUbicacionPiscina());
        holder.puntosPiscina.setText(piscinas.getPuntuacionPiscina());
    }

    @Override
    public int getItemCount() {
        return piscina.size();
    }


    public interface ThumbListener{
        void onThumb(Piscina piscina);

        void onComments(Piscina piscina);
    }


    public class PiscinasHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageButton imagenPiscina;
        TextView txtNombrePiscina;
        TextView ubicacionPiscina;
        TextView descriptPiscina;
        EditText puntosPiscina;

        public PiscinasHolder(@NonNull View itemView){
            super(itemView);
            txtNombrePiscina = itemView.findViewById(R.id.txtNombrePiscina);
            ubicacionPiscina = itemView.findViewById(R.id.ubicacionPiscina);
            descriptPiscina = itemView.findViewById(R.id.descriptPiscina);
            puntosPiscina = itemView.findViewById(R.id.puntosPiscina);
            imagenPiscina = itemView.findViewById(R.id.imagenPiscina);
        }


        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.imagenPiscina){
                viewModel.onThumb(piscina.get(getAdapterPosition()));
            }else{
                viewModel.onComments(piscina.get(getAdapterPosition()));
            }
        }
    }

}
