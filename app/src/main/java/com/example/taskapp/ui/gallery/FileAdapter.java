package com.example.taskapp.ui.gallery;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.taskapp.R;
import com.example.taskapp.ui.home.TaskAdapter;

import java.util.ArrayList;

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.ViewHolder> {

    public ArrayList<String> list;

    public FileAdapter(ArrayList<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_files, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

      TextView textFile = itemView.findViewById(R.id.textFiles);

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
    }

        public void onBind(String s) {
        textFile.setText(s);
        }
    }
}
