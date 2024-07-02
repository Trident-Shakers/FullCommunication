package org.shakers.fullcommunication.ui;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.FrameLayout;

import org.shakers.fullcommunication.R;
import org.shakers.fullcommunication.data.TopicCountMockData;

public class TopicFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private  float currentScale = 1.0f;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ValueAnimator debugAnimator;

    public TopicFragment() {
        // Required empty public constructor
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
        View view =  inflater.inflate(R.layout.fragment_topic, container, false);
        /**
         * 終了ボタンの処理
         */
        Button mButtonFinish = view.findViewById(R.id.finish_button);
        mButtonFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) requireActivity()).loadFragment(new ResultFragment());
            }
        });
        Button debugButton = view.findViewById(R.id.debug_button);
        Button fasterButton = view.findViewById(R.id.debug_faster_button);
        FrameLayout frameLayout = view.findViewById(R.id.frameLayout);

        debugButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentScale = frameLayout.getScaleX();
                if (debugAnimator != null && debugAnimator.isRunning()) {
                    debugAnimator.cancel();
                }
                // ValueAnimatorのインスタンスを作成
                debugAnimator = ValueAnimator.ofFloat(currentScale, 5.0f);

                // アニメーションの時間を1000ミリ秒に設定
                debugAnimator.setDuration(5000);

                // アニメーションの進行に応じてビューのスケールファクターを更新
                debugAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float scale = (float) animation.getAnimatedValue();
                        frameLayout.setScaleX(scale);
                        frameLayout.setScaleY(scale);
                    }
                });

                /**
                 * 縮むアニメーション
                 */
                debugAnimator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationEnd(@NonNull Animator animation) {
                        ValueAnimator shrinkAnimator = ValueAnimator.ofFloat(5.0f, 1.0f);
                        shrinkAnimator.setDuration(500);
                        currentScale = 1.0f;

                        shrinkAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator animation) {
                                float scale = (float) animation.getAnimatedValue();
                                frameLayout.setScaleX(scale);
                                frameLayout.setScaleY(scale);
                            }

                        });
                        shrinkAnimator.start();
                    }
                    @Override
                    public void onAnimationStart(Animator animation) {}

                    @Override
                    public void onAnimationCancel(Animator animation) {}

                    @Override
                    public void onAnimationRepeat(Animator animation) {}
                });
                debugAnimator.start();
            }
        });

        /**
         * 早くなるデバッグ用のボタン
         */
        fasterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 現在のスケールを取得
                currentScale = frameLayout.getScaleX();

                ValueAnimator fasterAnimator = ValueAnimator.ofFloat(currentScale, 5.0f);

                fasterAnimator.setDuration(500);
                //
                if(debugAnimator != null) {
                    debugAnimator.cancel();
                }
                if(fasterAnimator != null ){
                    fasterAnimator.cancel();
                }
                fasterAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float scale = (float) animation.getAnimatedValue();
                        frameLayout.setScaleX(scale);
                        frameLayout.setScaleY(scale);
                    }
                });
                /**
                 * 縮むアニメーション
                 */
                fasterAnimator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationEnd(@NonNull Animator animation) {
                        ValueAnimator shrinkAnimator = ValueAnimator.ofFloat(5.0f, 1.0f);
                        shrinkAnimator.setDuration(500);
                        currentScale = 1.0f;

                        shrinkAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator animation) {
                                float scale = (float) animation.getAnimatedValue();
                                frameLayout.setScaleX(scale);
                                frameLayout.setScaleY(scale);
                            }

                        });
                        shrinkAnimator.start();
                    }
                    @Override
                    public void onAnimationStart(Animator animation) {}

                    @Override
                    public void onAnimationCancel(Animator animation) {}

                    @Override
                    public void onAnimationRepeat(Animator animation) {}
                });
                fasterAnimator.start();

            }
        });

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

        return view;
    }
}