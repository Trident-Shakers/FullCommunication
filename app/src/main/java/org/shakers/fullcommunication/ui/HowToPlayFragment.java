package org.shakers.fullcommunication.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.MaterialToolbar;

import org.shakers.fullcommunication.R;

public class HowToPlayFragment extends Fragment {
    public HowToPlayFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_how_to_play, container, false);

        MaterialToolbar toolbar = view.findViewById(R.id.topAppBar);

        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) requireActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(v -> {
            // Handle the back button event
            ((MainActivity) requireActivity()).loadFragment(new TitleFragment());
        });

        setupButton(view, R.id.button1);
        setupButton(view, R.id.button2);
        setupButton(view, R.id.button3);

        return view;
    }

    private void setupButton(View view, int buttonId) {
        Button button = view.findViewById(buttonId);
        button.setOnClickListener(v -> {
            resetButtons(view);
            button.setBackgroundResource(R.drawable.clicked_circle_button);
            button.setTextColor(getResources().getColor(R.color.white, null));
        });
    }

    private void resetButtons(View view) {
        int[] buttonIds = {R.id.button1, R.id.button2, R.id.button3};
        for (int buttonId : buttonIds) {
            Button button = view.findViewById(buttonId);
            button.setBackgroundResource(R.drawable.circle_button);
            button.setTextColor(getResources().getColor(R.color.primary_color, null));
        }
    }
}