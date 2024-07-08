package org.shakers.fullcommunication.ui.animation;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.Log;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

public class AnimationHelper {

    private float currentScale = 1.0f;
    private ValueAnimator debugAnimator;
    private ValueAnimator fasterAnimator;
    final long MIDDLE_DURATION = 5000;
    final long FASTER_DURATION = 500;
    final float MAX_SCALE = 5.0f;

    public void startDebugAnimation(FrameLayout frameLayout) {
        currentScale = frameLayout.getScaleX();
        if (debugAnimator != null && debugAnimator.isRunning()) {
            debugAnimator.cancel();
        }
        if(fasterAnimator != null && fasterAnimator.isRunning()) {
            fasterAnimator.cancel();
        }
        debugAnimator = ValueAnimator.ofFloat(currentScale, MAX_SCALE);
        float middle_duration = MIDDLE_DURATION/MAX_SCALE*(MAX_SCALE-currentScale);
        debugAnimator.setDuration((long) middle_duration);

        debugAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(@NonNull ValueAnimator animation) {
                float scale = (float) animation.getAnimatedValue();
                frameLayout.setScaleX(scale);
                frameLayout.setScaleY(scale);
            }
        });

        debugAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationEnd(@NonNull Animator animation) {
                ValueAnimator shrinkAnimator = ValueAnimator.ofFloat(5.0f, 1.0f);
                shrinkAnimator.setDuration(FASTER_DURATION);
                shrinkAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(@NonNull ValueAnimator animation) {
                        float scale = (float) animation.getAnimatedValue();
                        frameLayout.setScaleX(scale);
                        frameLayout.setScaleY(scale);
                    }
                });
                shrinkAnimator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationEnd(@NonNull Animator animation) {
                        Log.d("AnimationHelper", "Shrink animation ended");
                        // ここで話題を変更する

                    }

                    @Override
                    public void onAnimationStart(@NonNull Animator animation) {
                        Log.d("AnimationHelper", "Shrink animation started");
                    }
                    @Override
                    public void onAnimationCancel(@NonNull Animator animation) {}
                    @Override
                    public void onAnimationRepeat(@NonNull Animator animation) {}
                });

                shrinkAnimator.start();
            }

            @Override
            public void onAnimationStart(@NonNull Animator animation) {}

            @Override
            public void onAnimationCancel(@NonNull Animator animation) {}

            @Override
            public void onAnimationRepeat(@NonNull Animator animation) {}
        });
        debugAnimator.start();
    }

    public void startFasterAnimation(FrameLayout frameLayout) {
        currentScale = frameLayout.getScaleX();
        fasterAnimator = ValueAnimator.ofFloat(currentScale, MAX_SCALE);
        float faster_duration = FASTER_DURATION/MAX_SCALE*(MAX_SCALE-currentScale);
        fasterAnimator.setDuration((long) faster_duration);

        if (debugAnimator != null && debugAnimator.isRunning()) {
            debugAnimator.cancel();
        }
        if(fasterAnimator != null && fasterAnimator.isRunning()) {
            fasterAnimator.cancel();
        }

        fasterAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(@NonNull ValueAnimator animation) {
                float scale = (float) animation.getAnimatedValue();
                frameLayout.setScaleX(scale);
                frameLayout.setScaleY(scale);
            }
        });

        fasterAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationEnd(@NonNull Animator animation) {
                ValueAnimator shrinkAnimator = ValueAnimator.ofFloat(5.0f, 1.0f);
                shrinkAnimator.setDuration(FASTER_DURATION);
                shrinkAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(@NonNull ValueAnimator animation) {
                        float scale = (float) animation.getAnimatedValue();
                        frameLayout.setScaleX(scale);
                        frameLayout.setScaleY(scale);
                    }
                });

                shrinkAnimator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationEnd(@NonNull Animator animation) {
                        Log.d("AnimationHelper", "Shrink animation ended");
                        // ここで話題を変更する

                    }

                    @Override
                    public void onAnimationStart(@NonNull Animator animation) {
                        Log.d("AnimationHelper", "Shrink animation started");
                    }
                    @Override
                    public void onAnimationCancel(@NonNull Animator animation) {}
                    @Override
                    public void onAnimationRepeat(@NonNull Animator animation) {}
                });

                shrinkAnimator.start();
            }

            @Override
            public void onAnimationStart(@NonNull Animator animation) {}

            @Override
            public void onAnimationCancel(@NonNull Animator animation) {}

            @Override
            public void onAnimationRepeat(@NonNull Animator animation) {}
        });
        fasterAnimator.start();
    }
}
