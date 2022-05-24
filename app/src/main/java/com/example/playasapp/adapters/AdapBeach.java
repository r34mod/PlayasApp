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

import com.example.playasapp.PlayasDB.Beachs;
import com.example.playasapp.R;

import java.util.ArrayList;
import java.util.List;

public class AdapBeach extends RecyclerView.Adapter<AdapBeach.BeachsHolder>{
    private Context context;
    private List<Beachs> models;
    ThumbListener modelView;



    public AdapBeach(Context context, ArrayList<Beachs> beachs, ThumbListener modelView) {
        this.context = context;
        this.models = beachs;
        this.modelView = modelView;
    }



    @NonNull
    @Override
    public AdapBeach.BeachsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_playas, parent, false);
        return new AdapBeach.BeachsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BeachsHolder holder, int position) {
        Beachs beachs = models.get(position);
        holder.txtNombrePlaya.setText(beachs.name);
        holder.ubicacionPlaya.setText(beachs.latlon);
        holder.descriptPlaya.setText(beachs.address);
        holder.puntosPlaya.setText(beachs.beachsId);
    }


    public void getAllModels(List<Beachs> models) {
        this.models = models;
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public interface ThumbListener {
        void onThumb(Beachs beachs);

        void onComments(Beachs beachs);
    }



    public class BeachsHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageButton imgPlaya;
        TextView txtNombrePlaya;
        TextView ubicacionPlaya;
        TextView descriptPlaya;
        EditText puntosPlaya;

        public BeachsHolder(@NonNull View itemView){
            super(itemView);
            txtNombrePlaya = itemView.findViewById(R.id.txtNombrePlaya);
            ubicacionPlaya = itemView.findViewById(R.id.ubicacionPlaya);
            descriptPlaya = itemView.findViewById(R.id.descriptPlaya);
            imgPlaya = itemView.findViewById(R.id.imagenPlaya);
            puntosPlaya = itemView.findViewById(R.id.puntosPlaya);
            //imgPlaya.setOnClickListener();
        }

        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.imagenPlaya){
                modelView.onThumb(models.get(getAdapterPosition()));
            }else{
                modelView.onComments(models.get(getAdapterPosition()));
            }
        }
    }
}
