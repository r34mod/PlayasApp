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

import com.example.playasapp.Objetos.Todo;
import com.example.playasapp.R;
import com.example.playasapp.adapters.AdapTodos;
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
 * Clase de fragmento all , donde obtenemos los dos obj de Playa, Piscina
 *
 *
 *
 */

public class all extends Fragment {
    public all(){

    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final ProgressBar bar;
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        View view = inflater.inflate(R.layout.fragment_todo, container, false);

        bar = view.findViewById(R.id.barTodo);

        final RecyclerView recyclerView;
        final ArrayList<Todo> todoArrayList;
        final AdapTodos adapTodos;

        LinearLayoutManager linearLayoutManager;

        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);

        recyclerView = view.findViewById(R.id.todo_recycle);
        recyclerView.setLayoutManager(linearLayoutManager);

        todoArrayList = new ArrayList<>();

        adapTodos = new AdapTodos(todoArrayList, getContext());

        recyclerView.setAdapter(adapTodos);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("Todos");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    recyclerView.setVisibility(View.VISIBLE);
                    bar.setVisibility(View.GONE);
                    todoArrayList.removeAll(todoArrayList);
                    for(DataSnapshot snapshot1 : snapshot.getChildren()){
                        Todo todo = snapshot1.getValue(Todo.class);
                        todoArrayList.add(todo);
                    }

                    adapTodos.notifyDataSetChanged();
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
