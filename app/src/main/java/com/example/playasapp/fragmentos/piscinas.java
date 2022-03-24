package com.example.playasapp.fragmentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.playasapp.Peds.Piscina;
import com.example.playasapp.R;
import com.example.playasapp.adapters.AdapPiscinas;
import com.example.playasapp.adapters.AdapPlayas;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 *
 * Clase piscinas, usamos el fragmento para usar el recycle view y mostrar las piscinas alojadas en CardView
 *
 * @author
 *
 * @version
 *
 *
 */

public class piscinas extends Fragment {

    public piscinas(){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final ProgressBar bar;
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        View view = inflater.inflate(R.layout.fragment_piscina, container, false);

        bar = view.findViewById(R.id.barPiscina);

        final RecyclerView recyclerView;
        final ArrayList<Piscina> piscinaArrayList;
        final AdapPiscinas adapPiscinas;

        LinearLayoutManager linearLayoutManager;

        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);

        recyclerView = view.findViewById(R.id.piscina_recycle);
        recyclerView.setLayoutManager(linearLayoutManager);

        piscinaArrayList = new ArrayList<>();

        adapPiscinas = new AdapPiscinas(piscinaArrayList, getContext());

        recyclerView.setAdapter(adapPiscinas);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("Playa");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    recyclerView.setVisibility(View.VISIBLE);
                    bar.setVisibility(View.GONE);
                    piscinaArrayList.removeAll(piscinaArrayList);
                    for(DataSnapshot snapshot1 : snapshot.getChildren()){
                        Piscina piscina = snapshot1.getValue(Piscina.class);
                        piscinaArrayList.add(piscina);
                    }

                    adapPiscinas.notifyDataSetChanged();
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
