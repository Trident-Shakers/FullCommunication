package org.shakers.fullcommunication.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import org.shakers.fullcommunication.R;
import org.shakers.fullcommunication.data.TopicCountData;
import org.shakers.fullcommunication.sensor.ShakeDetector;
import org.shakers.fullcommunication.ui.animation.AnimationHelper;

import java.util.ArrayList;
import java.util.List;

public class TopicFragment extends Fragment {

    private static class TopicTime {
        String topic;
        long time;

        public TopicTime(String topic, long time) {
            this.topic = topic;
            this.time = time;
        }

        public String getTopic() {
            return topic;
        }

        public long getTime() {
            return time;
        }
    }

    private ShakeDetector shakeDetector;
    private AnimationHelper animationHelper;
    private int debugButtonClickCount = 0;
    private TextView topicText;
    private final ArrayList<TopicTime> topicTimeList = new ArrayList<>();
    private final ArrayList<Long> timeList = new ArrayList<>();

    private final AnimationHelper.OnAnimationEndListener onAnimationEndListener = new AnimationHelper.OnAnimationEndListener() {
        @Override
        public void onAnimationEnd() {
            // トピックを表示するロジックを更新
            changeTopic(topicText);
        }
    };

    TopicCountData topicCountData;
    List<String> topicList;

    public TopicFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        animationHelper = new AnimationHelper(onAnimationEndListener);
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
        FrameLayout frameLayout = view.findViewById(R.id.frameLayout);

        shakeDetector = new ShakeDetector(new ShakeDetector.OnShakeListener() {
            @Override
            public void onShake() {
                // シェイク検知時の処理
                animationHelper.startFasterAnimation(frameLayout);
            }

            @Override
            public void unShake() {
                // シェイク解除時の処理
                animationHelper.startNormalAnimation(frameLayout);
            }
        });

        mButtonFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (topicTimeList.isEmpty()) {
                    Toast.makeText(requireActivity(), "まだ何も話せていません！！\n仲良くしてください！！", Toast.LENGTH_SHORT).show();
                    return;
                }
                for (TopicTime topicTime : topicTimeList) {
                    TopicTimeData(topicTime);
                }
                ((MainActivity) requireActivity()).loadFragment(new ResultFragment());
            }
        });

        topicText = view.findViewById(R.id.topic);
        topicCountData = new TopicCountData(requireActivity());
        topicList = topicCountData.getTopicList();

        //話題の初期化
        changeTopic(topicText);

        debugStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeTopic(topicText);
            }
        });

        debugButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationHelper.startNormalAnimation(frameLayout);
            }
        });

        fasterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationHelper.startFasterAnimation(frameLayout);
            }
        });


        return view;
    }

    private void TopicTimeData(TopicTime topicTime) {
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("topic_time_count_list", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String value = sharedPreferences.getString("topic_time_count_list", null);
        //topic-timeの形式でStringに格納
        if (value != null) {
            value = value + "," + topicTime.getTopic() + "-" + topicTime.getTime();
        } else {
            value = topicTime.getTopic() + "-" + topicTime.getTime();
        }
        //作ったStringをSharedPreferencesに格納
        editor.putString("topic_time_count_list", value);
        editor.apply();
    }

    private void changeTopic(TextView tv) {
        // 最初にdebugButtonが押された時刻を記録
        long currentTimeStamp = System.currentTimeMillis();
        timeList.add(currentTimeStamp);
        if (timeList.size() > 1) {
            long elapsedTime = timeList.get(timeList.size() - 1) - timeList.get(timeList.size() - 2);
            Log.d("TopicFragment", "Elapsed Time: " + elapsedTime + "ms");
            TopicTime topicTime = new TopicTime(String.valueOf(tv.getText()), elapsedTime);
            topicTimeList.add(topicTime);
        }
        // トピックを表示するロジックを更新
        if (debugButtonClickCount < topicList.size()) {
            tv.setText(topicList.get(debugButtonClickCount));
            debugButtonClickCount++;
        } else {
            for (TopicTime topicTime : topicTimeList) {
                TopicTimeData(topicTime);
            }
            ((MainActivity) requireActivity()).loadFragment(new ResultFragment());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        SensorManager sensorManager = (SensorManager) requireActivity().getSystemService(Context.SENSOR_SERVICE);
        shakeDetector.start(sensorManager); // ShakeDetector を開始
    }

    @Override
    public void onPause() {
        super.onPause();
        SensorManager sensorManager = (SensorManager) requireActivity().getSystemService(Context.SENSOR_SERVICE);
        sensorManager.unregisterListener(shakeDetector); // リスナーの登録解除
    }
}
