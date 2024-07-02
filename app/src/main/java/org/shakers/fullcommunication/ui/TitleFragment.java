package org.shakers.fullcommunication.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import org.shakers.fullcommunication.R;

public class TitleFragment extends Fragment {

    public TitleFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_title, container, false);
        //Fragmentの場合
        Button startButton = view.findViewById(R.id.button_start);
        startButton.setOnClickListener(v -> {
            Log.d("TitleFragment", "onClick: startButton");

            // ジャンル選択画面に遷移
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.nav_host_fragment, new GenreChoiceFragment())
                    .commit();
        });
        Button htpbutton = view.findViewById(R.id.button_how_to_play);
        htpbutton.setOnClickListener(v -> ((MainActivity) requireActivity()).loadFragment(new HowToPlayFragment()));
        return view;
    }
}