package org.shakers.fullcommunication.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import org.shakers.fullcommunication.R;
import org.shakers.fullcommunication.ui.animation.AnimationHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TopicFragment extends Fragment {

    private AnimationHelper animationHelper;
    private int debugButtonClickCount = 0;
    private long debugButtonPressTime = 0;
    private long finishButtonPressTime = 0;

    public TopicFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        animationHelper = new AnimationHelper();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_topic, container, false);
        Button mButtonFinish = view.findViewById(R.id.finish_button);
        Button debugButton = view.findViewById(R.id.debug_button);
        Button fasterButton = view.findViewById(R.id.debug_faster_button);
        Button debugStartButton = view.findViewById(R.id.debug_start_button);
        Button finishButton = view.findViewById(R.id.debug_finish_button);
        FrameLayout frameLayout = view.findViewById(R.id.frameLayout);

        mButtonFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) requireActivity()).loadFragment(new ResultFragment());
            }
        });

        TextView topicText = view.findViewById(R.id.topic);
        String[] topicValues = getTopicValues();

        debugStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 最初にdebugButtonが押された時刻を記録
                if (debugButtonClickCount == 0) {
                    debugButtonPressTime = System.currentTimeMillis();
                }
                // トピックを表示するロジックを更新
                if (debugButtonClickCount < topicValues.length) {
                    String topicValue = topicValues[debugButtonClickCount];
                    topicText.setText(topicValue);
                    debugButtonClickCount++;
                }
            }
        });

        debugButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationHelper.startDebugAnimation(frameLayout);
            }
        });

        fasterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationHelper.startFasterAnimation(frameLayout);
            }
        });

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (debugButtonClickCount > 0) { // debugButtonが少なくとも一度は押されていることを確認
                    finishButtonPressTime = System.currentTimeMillis();
                    long elapsedTime = finishButtonPressTime - debugButtonPressTime;
                    Log.d("TopicFragment", "Elapsed Time: " + elapsedTime + "ms");
                    // トピック表示を空白にする
                    topicText.setText("");
                    // 時間を保存するロジックを呼び出し
                    if (debugButtonClickCount > 1) { // debugButtonが一度以上押された後にfinishButtonが押された場合
                        TopicTimeData(topicValues[debugButtonClickCount - 2], elapsedTime);
                    }
                }
            }
        });

        return view;
    }

    private String[] getTopicValues() {
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("DATA", Context.MODE_PRIVATE);
        List<String> topicTimeList = new ArrayList<>();
        for (int i = 1; i <= 21; i++) {
            String key = String.valueOf(i);
            String value = sharedPreferences.getString(key, null);
            if (value != null) {
                topicTimeList.add(value);
            }
        }
        Collections.shuffle(topicTimeList);
        return topicTimeList.toArray(new String[0]);
    }

    private void TopicTimeData(String topic, long time) {
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("topic_time_count_list", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String value = sharedPreferences.getString("topic_time_count_list", null);

        //topic-timeの形式でStringに格納
        if (value != null) {
            value = value + "," + topic + "-" + time;
        } else {
            value = topic + "-" + time;
        }

        //作ったStringをSharedpreferencesに格納
        editor.putString("topic_time_count_list", value);

        editor.apply();
    }
}
