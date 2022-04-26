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

import com.example.playasapp.Objetos.Todo;
import com.example.playasapp.R;

import java.util.List;


/**
 *
 * Clase AdapTodos tiene los cardView de las playas, piscinas que vayamos a mostrar
 *
 * Todos los cardView de esta clase se almacenan en un arrayList de objecto Todos
 *
 * Usamos el ViewHolder
 *
 *
 */


public class AdapTodos extends RecyclerView.Adapter<AdapTodos.TodosHolder> {

    List<Todo> todoList;
    Context context;
    ThumbListener viewModel;

    public AdapTodos(List<Todo> todoList, Context context){
        this.todoList = todoList;
        this.context = context;
    }

    @NonNull
    @Override
    public TodosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_todo, parent, false);
        return new TodosHolder(view);
    }

    public void onBindViewHolder(@NonNull TodosHolder holder, int position){
        Todo todos = todoList.get(position);
        holder.txtNombreTodo.setText(todos.getNombreTodo());
        holder.descriptTodo.setText(todos.getDescriptTodo());
        holder.ubicacionTodo.setText(todos.getUbicacionTodo());
        holder.puntosTodo.setText(todos.getPuntuacionTodo());

    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }
    
    public interface ThumbListener{
        void onThumb(Todo todo);
        
        void onComments(Todo todo);
    }

    public class TodosHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageButton imagenTodo;
        TextView txtNombreTodo;
        TextView ubicacionTodo;
        TextView descriptTodo;
        EditText puntosTodo;

        public TodosHolder(@NonNull View itemView){
            super(itemView);
            txtNombreTodo = itemView.findViewById(R.id.txtNombreTodo);
            ubicacionTodo = itemView.findViewById(R.id.ubicacionTodo);
            descriptTodo = itemView.findViewById(R.id.descriptTodo);
            puntosTodo = itemView.findViewById(R.id.puntosTodo);
            imagenTodo = itemView.findViewById(R.id.imgTodo);
        }

        @Override
        public void onClick(View v) {

            if(v.getId() == R.id.imgTodo){
                viewModel.onThumb(todoList.get(getAdapterPosition()));
            }else{
                viewModel.onComments(todoList.get(getAdapterPosition()));
            }
        }
    }
}
