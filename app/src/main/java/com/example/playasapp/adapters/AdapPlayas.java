package com.example.playasapp.adapters;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.playasapp.Peds.Piscina;
import com.example.playasapp.Peds.Playa;
import com.example.playasapp.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class AdapPlayas extends RecyclerView.Adapter<AdapPlayas.PlayasHolder> {

    private Context context;
    private List<Playa> playa;
    ThumbListener modelView;



    public AdapPlayas(ArrayList<Playa> playaArrayList, Context context) {
        this.playa = playaArrayList;
        this.context = context;
    }



    @NonNull
    @Override
    public PlayasHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_playas, parent, false);
        return new PlayasHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayasHolder holder, int position) {
        Playa playas = playa.get(position);
        holder.txtNombrePlaya.setText(playas.getNombrePlaya());
        holder.ubicacionPlaya.setText(playas.getUbicacionPlaya());
        holder.descriptPlaya.setText(playas.getDescripcionPlaya());
        holder.puntosPlaya.setText(playas.getPuntuacionPlaya());
    }



    @Override
    public int getItemCount() {
        return playa.size();
    }


    public interface ThumbListener{
        void onThumb(Playa playa);

        void onComments(Playa playa);
    }

    public class PlayasHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageButton imgPlaya;
        TextView txtNombrePlaya;
        TextView ubicacionPlaya;
        TextView descriptPlaya;
        EditText puntosPlaya;

        public PlayasHolder(@NonNull View itemView){
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
                modelView.onThumb(playa.get(getAdapterPosition()));
            }else{
                modelView.onComments(playa.get(getAdapterPosition()));
            }
        }
    }
}
