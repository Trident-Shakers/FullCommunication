package org.shakers.fullcommunication.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import org.shakers.fullcommunication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Button endButton = view.findViewById(R.id.back_to_title_button);

        endButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSharedPreferences();
                ((MainActivity) requireActivity()).loadFragment(new TitleFragment());
            }
        });

        setPlace(getTopicTime(), firstPlace, secondPlace, thirdPlace);

        return view;
    }

    /**
     * 話された時間が長い順に話題をTextViewにセットします。
     * <p>
     * Topic-Time,Topic-Time...の形式で記述されている文字列を受け取ります。
     * </p>
     *
     * @param value       トピックと時間の書かれた文字列
     * @param firstPlace  １位のTextView
     * @param secondPlace ２位のTextView
     * @param thirdPlace  ３位のTextView
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

            //switch文でスライスした話題が3つ未満の場合の処理を追加
            switch (list.size()) {
                case 1:
                    firstPlace.setText(list.get(0).getKey());
                    secondPlace.setText("------------");
                    thirdPlace.setText("------------");
                    break;
                case 2:
                    firstPlace.setText(list.get(0).getKey());
                    secondPlace.setText(list.get(1).getKey());
                    thirdPlace.setText("------------");
                    break;
                default:
                    firstPlace.setText(list.get(0).getKey());
                    secondPlace.setText(list.get(1).getKey());
                    thirdPlace.setText(list.get(2).getKey());
                    break;
            }
        }
    }

    /**
     * SharedPreferencesの"topic_time_count_list"に<br>
     * 保存されているデータを取得します。
     *
     * @return Topic-Time,Topic-Time...の形式で記述されている文字列
     */
    private String getTopicTime() {
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("topic_time_count_list", Context.MODE_PRIVATE);
        return sharedPreferences.getString("topic_time_count_list", null);
    }

    /**
     * SharedPreferencesに保存されているデータを削除します。
     */
    private void clearSharedPreferences() {
        clearSharedPreferencesByName("DATA");
        clearSharedPreferencesByName("topic_time_count_list");
    }

    private void clearSharedPreferencesByName(String name) {
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}