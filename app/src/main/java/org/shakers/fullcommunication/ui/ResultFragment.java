package org.shakers.fullcommunication.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.shakers.fullcommunication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ResultFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResultFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ResultFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ResultFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ResultFragment newInstance(String param1, String param2) {
        ResultFragment fragment = new ResultFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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

