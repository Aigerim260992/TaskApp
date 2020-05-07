package com.example.taskapp.ui.onboard;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.taskapp.MainActivity;
import com.example.taskapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BoardFragment extends Fragment {

    Button button;

    public BoardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_board, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textTitle = view.findViewById(R.id.textTitle);
        ImageView imageView = view.findViewById(R.id.imageView);
        button = view.findViewById(R.id.buttonStart);

        int pos = getArguments().getInt("pos");
        switch (pos){
            case 0:
                imageView.setImageResource(R.drawable.mickey1);
                textTitle.setText("Привет!");
                button.setVisibility(view.INVISIBLE);
                break;
            case 1:
                imageView.setImageResource(R.drawable.mickey2);
                textTitle.setText("Как дела?");
                button.setVisibility(view.INVISIBLE);
                break;
            case 2:
                imageView.setImageResource(R.drawable.mickey3);
                textTitle.setText("Что делаешь?");
                button.setVisibility(view.VISIBLE);
                break;
        }


    }
}
