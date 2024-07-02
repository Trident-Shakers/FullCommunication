package org.shakers.fullcommunication.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.shakers.fullcommunication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        View view = inflater.inflate(R.layout.fragment_result, container, false);

        TextView firstPlace = view.findViewById(R.id.ranking_first_topic);
        TextView secondPlace = view.findViewById(R.id.ranking_second_topic);
        TextView thirdPlace = view.findViewById(R.id.ranking_third_topic);

        // トピックと時間の書かれた文字列を取得
        view.getContext();
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("topic_time_count_list", Context.MODE_PRIVATE);
        String value = sharedPreferences.getString("topic_time_count_list", null);

        setPlace(value, firstPlace, secondPlace, thirdPlace);

        return view;
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


    /**
     * 話された時間が長い順に話題をTextViewにセットします。
     * <p>
     *     Topic-Time,Topic-Time...の形式で記述されている文字列を受け取ります。
     * </p>
     *
     * @param value トピックと時間の書かれた文字列
     * @param firstPlace １位のTextView
     * @param secondPlace ２位のTextView
     * @param thirdPlace ３位のTextView
     */
    private void setPlace(String value, TextView firstPlace, TextView secondPlace, TextView thirdPlace) {
        if (value != null) {
            String[] valueArray = value.split(",");
            HashMap<String, Integer> map = new HashMap<>();

            for (String s : valueArray) {
                String[] parts = s.split("-");
                map.put(parts[0], Integer.parseInt(parts[1]));
            }

            List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
            list.sort(Map.Entry.<String, Integer>comparingByValue().reversed());

            firstPlace.setText(list.get(0).getKey());
            secondPlace.setText(list.get(1).getKey());
            thirdPlace.setText(list.get(2).getKey());
        }
    }
}

