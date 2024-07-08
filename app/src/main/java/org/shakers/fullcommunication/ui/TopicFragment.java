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

import androidx.fragment.app.Fragment;

import org.shakers.fullcommunication.R;
import org.shakers.fullcommunication.data.TopicCountData;
import org.shakers.fullcommunication.sensor.ShakeDetector;
import org.shakers.fullcommunication.ui.animation.AnimationHelper;

import java.util.List;

public class TopicFragment extends Fragment {

    private ShakeDetector shakeDetector;
    private AnimationHelper animationHelper;
    private int debugButtonClickCount = 0;
    private long debugButtonPressTime = 0;
    private long finishButtonPressTime = 0;
    private TextView topicText;

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
        Button finishButton = view.findViewById(R.id.debug_finish_button);
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
                ((MainActivity) requireActivity()).loadFragment(new ResultFragment());
            }
        });

        topicText = view.findViewById(R.id.topic);
        topicCountData = new TopicCountData(requireActivity());
        topicList = topicCountData.getTopicList();

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

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTopicCount(topicText);
            }
        });

        return view;
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

    private void changeTopic(TextView tv) {
        // 最初にdebugButtonが押された時刻を記録
        if (debugButtonClickCount == 0) {
            debugButtonPressTime = System.currentTimeMillis();
        }
        // トピックを表示するロジックを更新
        if (debugButtonClickCount < topicList.size()) {
            tv.setText(topicList.get(debugButtonClickCount));
            debugButtonClickCount++;
        }
    }

    private void saveTopicCount(TextView tv) {
        if (debugButtonClickCount > 0) { // debugButtonが少なくとも一度は押されていることを確認
            finishButtonPressTime = System.currentTimeMillis();
            long elapsedTime = finishButtonPressTime - debugButtonPressTime;
            Log.d("TopicFragment", "Elapsed Time: " + elapsedTime + "ms");
            // トピック表示を空白にする
            tv.setText("");
            // 時間を保存するロジックを呼び出し
            if (debugButtonClickCount > 1) { // debugButtonが一度以上押された後にfinishButtonが押された場合
                TopicTimeData(topicList.get(debugButtonClickCount - 2), elapsedTime);
            }
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
