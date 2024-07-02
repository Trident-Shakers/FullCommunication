package org.shakers.fullcommunication.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.shakers.fullcommunication.R;

/**
 * 全体の流れの最後に来るFragmentです
 * <p>ここでは、結果を表示する画面を表示します。</p>
 * 画面遷移の流れ:
 * <br>
 * {@link TopicFragment} -> ResultFragment
 * <br>
 * ResultFragment -> {@link TitleFragment}
 */
public class ResultFragment extends Fragment {
    public ResultFragment() {
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
        View view =  inflater.inflate(R.layout.fragment_result, container, false);
        Button title_back_button = view.findViewById(R.id.title_back_button);
        title_back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) requireActivity()).loadFragment(new TitleFragment());
            }
        });
        return view;
    }
}