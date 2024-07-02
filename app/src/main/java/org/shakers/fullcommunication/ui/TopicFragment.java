package org.shakers.fullcommunication.ui;

import android.animation.Animator;
import android.animation.ValueAnimator;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TopicFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TopicFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private  float currentScaleX = 1.0f;
    private  float currentScaleY = 1.0f;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ValueAnimator debugAnimator;

    public TopicFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TopicFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TopicFragment newInstance(String param1, String param2) {
        TopicFragment fragment = new TopicFragment();
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
                // ValueAnimatorのインスタンスを作成
                debugAnimator = ValueAnimator.ofFloat(1.0f, 5.0f);

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
                debugAnimator.start();
            }
        });




        fasterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 現在のスケールを取得
                currentScaleX = frameLayout.getScaleX();
                currentScaleY = frameLayout.getScaleY();
                Log.d("TAG", "currentScaleX: " + currentScaleX + " currentScaleY: " + currentScaleY);

                ValueAnimator fasterAnimator = ValueAnimator.ofFloat(currentScaleX, 5.0f);

                fasterAnimator.setDuration(500);
                //
                if(debugAnimator != null) {
                    debugAnimator.cancel();
                }
                fasterAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float scale = (float) animation.getAnimatedValue();
                        frameLayout.setScaleX(scale);
                        frameLayout.setScaleY(scale);
                    }
                });
                fasterAnimator.addListener(new Animator.AnimatorListener() {

                    @Override
                    public void onAnimationEnd(@NonNull Animator animation) {
                        ValueAnimator shrinkAnimator = ValueAnimator.ofFloat(5.0f, 1.0f);
                        shrinkAnimator.setDuration(500);

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


        return view;
    }
}