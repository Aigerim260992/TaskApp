package com.example.taskapp.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ReceiverCallNotAllowedException;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskapp.App;
import com.example.taskapp.FormActivity;
import com.example.taskapp.R;
import com.example.taskapp.models.Task;
import com.example.taskapp.ui.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class HomeFragment extends Fragment {

    private TaskAdapter adapter;
    private ArrayList<Task> list = new ArrayList<>();
    private AlertDialog.Builder ad;
    private boolean sorted = false;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
      return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        list.addAll (App.getInstance().getDatabase().taskDao().getAll()); // чтение с базы данных
        adapter = new TaskAdapter(list);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                Task task = list.get(pos);
                Intent intent = new Intent(getActivity(), FormActivity.class);
                intent.putExtra("task", task);
                startActivity(intent);
            }

            @Override
            public void onItemLongClickListener(final int pos) {
                ad = new AlertDialog.Builder(getActivity());
                ad.setTitle("Внимание!");
                ad.setMessage("Вы действительно хотите удалить?");
                ad.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        App.getInstance().getDatabase().taskDao().delete(list.get(pos));
                    }
                });
                ad.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                ad.show();
            }
        });

        loadData();
    }

     private void loadData() {
        App.getInstance()
                .getDatabase()
                .taskDao()
                .getAllLive()
                .observe(getViewLifecycleOwner(), new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                list.clear();
                list.addAll(tasks);
                adapter.notifyDataSetChanged();// чтение2, слушает изменения в базе данных
            }
        });
    }

    private void loadDataSorted() {
        App.getInstance()
                .getDatabase()
                .taskDao()
                .getAllSortedLive()
                .observe(getViewLifecycleOwner(), new Observer<List<Task>>() {
                    @Override
                    public void onChanged(List<Task> tasks) {
                        list.clear();
                        list.addAll(tasks);
                         adapter.notifyDataSetChanged();// чтение2, слушает изменения в базе данных
                    }
                });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.action_sort){
            if(sorted){
                loadData();
                sorted = false;
            } else{
                loadDataSorted();
                sorted = true;
            }
        }

        return true;
    }

    //    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        Task task = (Task) data.getSerializableExtra("task");
//        list.add(task);
//        adapter.notifyDataSetChanged();
//        Log.e("TAG", "title="+ task.getTitle());
//        Log.e("TAG", "desc="+ task.getDesc());
//    }


}
