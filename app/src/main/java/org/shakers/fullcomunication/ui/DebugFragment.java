package org.shakers.fullcomunication.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import org.shakers.fullcomunication.R;

/**
 * 各画面への遷移を確認するためのデバッグ用Fragment
 * MainActivityから遷移するためのボタンを配置しています。
 * このFragmentはデバッグ用途でのみ使用してください。
 */
public class DebugFragment extends Fragment {

    public DebugFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_debug, container, false);

        Button titleButton = view.findViewById(R.id.button_title);
        Button genreChoiceButton = view.findViewById(R.id.button_genre_choice);
        Button howToPlayButton = view.findViewById(R.id.button_how_to_play_debug);
        Button topicOfferingButton = view.findViewById(R.id.button_topic_offering);
//        Button resultButton = view.findViewById(R.id.button_result);

        titleButton.setOnClickListener(v -> ((MainActivity) getActivity()).loadFragment(new TitleFragment()));
        genreChoiceButton.setOnClickListener(v -> ((MainActivity) getActivity()).loadFragment(new GenreChoiceFragment()));
        howToPlayButton.setOnClickListener(v -> ((MainActivity) getActivity()).loadFragment(new HowToPlayFragment()));
        topicOfferingButton.setOnClickListener(v -> ((MainActivity) getActivity()).loadFragment(new TopicFragment()));
//        resultButton.setOnClickListener(v -> ((MainActivity) getActivity()).loadFragment(new ResultFragment()));

        return view;
    }
}
