package com.example.taskapp.ui.home;

import android.graphics.Color;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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
        if(position %2 ==0) {
            holder.layout.setBackgroundColor(Color.LTGRAY);
        } else {
            holder.layout.setBackgroundColor(Color.WHITE);
        }
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

        private LinearLayout layout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            texTitle = itemView.findViewById(R.id.textTitle);
            textDesc = itemView.findViewById(R.id.textDesc);
            layout = itemView.findViewById(R.id.layout);
           itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   onItemClickListener.onItemClick(getAdapterPosition());
               }
           });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemClickListener.onItemLongClickListener( getAdapterPosition());
                    return true;
                }
            });

        }

        public void bind(Task task) {
            texTitle.setText(task.getTitle());
            textDesc.setText(task.getDesc());
        }
    }
}
