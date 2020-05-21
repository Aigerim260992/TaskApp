package com.example.taskapp.ui.onboard;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.taskapp.MainActivity;
import com.example.taskapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class  BoardFragment extends Fragment {

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
//        ImageView imageView = view.findViewById(R.id.imageView);
        Button button = view.findViewById(R.id.buttonStart);
        LinearLayout linearLayout = view.findViewById(R.id.Linear);
      LottieAnimationView lottieAnimationView = view.findViewById(R.id.lottieAnimationView);

      int pos = getArguments().getInt("pos");
        switch (pos) {
            case 0:
//                imageView.setImageResource(R.drawable.mickey1);
             lottieAnimationView.setAnimation(R.raw.animation);
                textTitle.setText("Hi)");
                button.setVisibility(view.INVISIBLE);
                linearLayout.setBackgroundColor(getResources().getColor(R.color.color_board3));
                break;
            case 1:
//                imageView.setImageResource(R.drawable.mickey2);
                lottieAnimationView.setAnimation(R.raw.checklist);
                textTitle.setText("Start your day with me)");
                button.setVisibility(view.INVISIBLE);
                linearLayout.setBackgroundColor(getResources().getColor(R.color.color_board2));
                break;
            case 2:
//                imageView.setImageResource(R.drawable.mickey3);
                lottieAnimationView.setAnimation(R.raw.confetti);
                textTitle.setText("Let's go!");
                button.setVisibility(view.VISIBLE);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        saveIsShown();
                        Intent intent = new Intent(getContext(), MainActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    }
                });
                linearLayout.setBackgroundColor(getResources().getColor(R.color.color_board1));

        }
    }
    private void saveIsShown() {
        SharedPreferences preferences = getActivity().getSharedPreferences("settings", Context.MODE_PRIVATE);
        preferences.edit().putBoolean("isShown", true).apply();
    }
}