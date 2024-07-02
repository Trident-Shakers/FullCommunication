package org.shakers.fullcommunication.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import org.shakers.fullcommunication.R;

/**
 * タイトル画面のFragment
 * <p>ジャンル選択画面に遷移するボタンを配置しています。</p>
 * 画面遷移の流れ:
 * <br>
 * TitleFragment -> {@link GenreChoiceFragment}
 * <br>
 * TitleFragment -> {@link HowToPlayFragment}
 * <br>
 * {@link ResultFragment} -> TitleFragment
 */
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
        startButton.setOnClickListener(v -> ((MainActivity) requireActivity()).loadFragment(new GenreChoiceFragment()));
        Button htpbutton = view.findViewById(R.id.button_how_to_play);
        htpbutton.setOnClickListener(v -> ((MainActivity) requireActivity()).loadFragment(new HowToPlayFragment()));
        return view;
    }
}