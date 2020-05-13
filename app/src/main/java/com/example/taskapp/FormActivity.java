package com.example.taskapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.taskapp.models.Task;

public class FormActivity extends AppCompatActivity {

    private EditText editTitle;
    private EditText editDesc;

    Task task;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Новая задача");
        }

        editTitle = findViewById(R.id.editTitle);
        editDesc = findViewById(R.id.editDesc);

        getIncomingIntent();
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void getIncomingIntent(){
        task = (Task) getIntent().getSerializableExtra(("task"));
        if(task!= null){
            editTitle.setText(task.getTitle());
            editDesc.setText(task.getDesc());
        }

    }

    public void onSave(View view) {
        if(task != null){
            task.setTitle(editTitle.getText().toString());
            task.setDesc(editDesc.getText().toString());
            App.getInstance().getDatabase().taskDao().update(task);
        } else {
            task = new Task(editTitle.getText().toString(), editDesc.getText().toString());
            App.getInstance().getDatabase().taskDao().insert(task); // запись в базу данных
        }
        finish();
    }
}
