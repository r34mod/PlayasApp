package com.example.playasapp.InfoView;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.playasapp.R;

import java.util.List;

/**
 *
 * Clase OnBoardAdap que esta conectado con InfoActivity y es el adaptador para mostrar los fragmentos
 *
 * Para mostrar estos fragmentos, creas un ArrayList de tipo OnBoardItems donde tiene los atributos
 * de imagen, titulo y descripcion
 *
 *
 */

public class OnBoardAdap extends RecyclerView.Adapter<OnBoardAdap.OnBoardViewHolder> {

    private List<OnBoardItem> onBoardItemList;

    public OnBoardAdap(List<OnBoardItem> onBoardItems){
        this.onBoardItemList = onBoardItems;
    }

    @NonNull
    @Override
    public OnBoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        return new OnBoardViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_container_onboard, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull OnBoardViewHolder holder, int position){
        holder.setOnboardData(onBoardItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return onBoardItemList.size();
    }

    class OnBoardViewHolder extends RecyclerView.ViewHolder{
        private TextView textTitulo;
        private TextView textDescripcion;
        private ImageView imagen;

        OnBoardViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitulo = itemView.findViewById(R.id.textTittle);
            textDescripcion = itemView.findViewById(R.id.textDescription);
            imagen = itemView.findViewById(R.id.imgOnboard);
        }

        void setOnboardData(OnBoardItem onboardItem){
            textTitulo.setText(onboardItem.getTitulo());
            textDescripcion.setText(onboardItem.getDescripcion());
            imagen.setImageResource(onboardItem.getImagen());
        }
    }
}
