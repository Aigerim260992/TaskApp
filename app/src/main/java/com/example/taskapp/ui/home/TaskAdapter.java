package com.example.taskapp.ui.home;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskapp.R;
import com.example.taskapp.models.Task;
import com.example.taskapp.ui.OnItemClickListener;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder>  {

    private ArrayList<Task> list;

    private OnItemClickListener onItemClickListener;

    public TaskAdapter(ArrayList<Task> list) {

        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_task, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView texTitle;
        private TextView textDesc;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            texTitle = itemView.findViewById(R.id.textTitle);
            textDesc = itemView.findViewById(R.id.textDesc);
           itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   onItemClickListener.onItemClick(getAdapterPosition());
               }
           });
        }

        public void bind(Task task) {
            texTitle.setText(task.getTitle());
            textDesc.setText(task.getDesc());
        }
    }
}
