package com.example.taskapp.ui.gallery;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskapp.R;

import java.io.File;
import java.util.ArrayList;

public class GalleryFragment extends Fragment {

   RecyclerView recyclerFiles;
     ArrayList<String> list = new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

       return inflater.inflate(R.layout.fragment_gallery, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getPermissions();
        recyclerFiles = view.findViewById(R.id.recyclerFiles);
        recyclerFiles.setLayoutManager(new LinearLayoutManager(getContext()));
        FileAdapter adapter = new FileAdapter(list);
        recyclerFiles.setAdapter(adapter);
        list.add("AAAA");
     }

    private void getPermissions() {
        if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            getFiles();
        } else{
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},101);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 101){
            getPermissions();
        }
    }

    private void getFiles(){
        File folder = new File(Environment.getExternalStorageDirectory(),"DCIM/Camera");
//       if(!folder.exists()) folder.mkdir();
        for(File file: folder.listFiles()){
            Log.e("TAG", "file = " + file.getAbsolutePath());
            list.add(file.getAbsolutePath());
        }
    }
}
