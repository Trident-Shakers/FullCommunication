package org.shakers.fullcommunication.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.appbar.MaterialToolbar;

import org.shakers.fullcommunication.R;

import java.util.Objects;

/**
 * ジャンル選択画面のFragment
 * <p>ジャンル選択画面です。</p>
 * 画面遷移の流れ:
 * <br>
 * {@link TitleFragment} -> GenreChoiceFragment
 * <br>
 * GenreChoiceFragment -> {@link TopicFragment}
 */
public class GenreChoiceFragment extends Fragment {
    public GenreChoiceFragment() {
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
        View view = inflater.inflate(R.layout.fragment_genre_choice, container, false);

        MaterialToolbar toolbar = view.findViewById(R.id.topAppBarGC);

        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(v -> {
            // Handle the back button event
            ((MainActivity) requireActivity()).loadFragment(new TitleFragment());
        });

        Button nextButton = view.findViewById(R.id.buttonNext);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) requireActivity()).loadFragment(new TopicFragment());
            }
        });

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3)); // 3列に設定
        recyclerView.setAdapter(new ButtonAdapter(21, getContext())); // 3 × 7 = 21個のボタン
        return view;
    }
}