package org.shakers.fullcommunication.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import org.shakers.fullcommunication.R;
import org.shakers.fullcommunication.data.TopicCountMockData;
import org.shakers.fullcommunication.ui.animation.AnimationHelper;

public class TopicFragment extends Fragment {

    private AnimationHelper animationHelper;

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
        FrameLayout frameLayout = view.findViewById(R.id.frameLayout);

        mButtonFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TopicCountMockData topicCountMockData = new TopicCountMockData();
                requireActivity();
                SharedPreferences.Editor editor = requireActivity().getSharedPreferences("topic_time_count_list", Context.MODE_PRIVATE).edit();
                Log.d("TopicFragment", topicCountMockData.getTopicTimeListString());
                editor.putString("topic_time_count_list", topicCountMockData.getTopicTimeListString());
                editor.apply();

                ((MainActivity) requireActivity()).loadFragment(new ResultFragment());
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

        return view;
    }
}
