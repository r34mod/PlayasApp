package com.example.playasapp.fragmentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.playasapp.R;
import com.example.playasapp.adapters.AdapPlayas;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Playa extends Fragment {

    public Playa(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        final ProgressBar bar;
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        View view = inflater.inflate(R.layout.fragment_playa, container, false);

        bar = view.findViewById(R.id.bar);
        final RecyclerView recyclerView;
        final ArrayList<Playa> playaArrayList;
        final AdapPlayas adapPlayas;

        LinearLayoutManager linearLayoutManager;

        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);

        recyclerView = view.findViewById(R.id.playa_recycle);

        recyclerView.setLayoutManager(linearLayoutManager);

        playaArrayList = new ArrayList<>();

        adapPlayas = new AdapPlayas(playaArrayList, getContext());

        recyclerView.setAdapter(adapPlayas);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("Playa");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    recyclerView.setVisibility(View.VISIBLE);
                    bar.setVisibility(View.GONE);
                    playaArrayList.removeAll(playaArrayList);
                    for(DataSnapshot snapshot1 : snapshot.getChildren()){
                        Playa playa = snapshot1.getValue(Playa.class);
                        playaArrayList.add(playa);
                    }

                    adapPlayas.notifyDataSetChanged();
                }else{
                    bar.setVisibility(View.VISIBLE);
                    Toast.makeText(getContext(), "No existen datos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }
}
