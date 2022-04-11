package com.example.playasapp.InfoView;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.playasapp.R;

import java.util.List;

public class OnBoardAdap extends RecyclerView.Adapter<OnBoardAdap.OnboardViewHolder> {

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
}
