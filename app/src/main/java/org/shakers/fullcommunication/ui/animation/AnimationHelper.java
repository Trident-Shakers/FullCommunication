package org.shakers.fullcommunication.ui.animation;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

public class AnimationHelper {

    private float currentScale = 1.0f;
    private ValueAnimator debugAnimator;

    public void startDebugAnimation(FrameLayout frameLayout) {
        currentScale = frameLayout.getScaleX();
        if (debugAnimator != null && debugAnimator.isRunning()) {
            debugAnimator.cancel();
        }
        debugAnimator = ValueAnimator.ofFloat(currentScale, 5.0f);
        debugAnimator.setDuration(5000);

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
                shrinkAnimator.setDuration(500);
                shrinkAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(@NonNull ValueAnimator animation) {
                        float scale = (float) animation.getAnimatedValue();
                        frameLayout.setScaleX(scale);
                        frameLayout.setScaleY(scale);
                    }
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
        ValueAnimator fasterAnimator = ValueAnimator.ofFloat(currentScale, 5.0f);
        fasterAnimator.setDuration(500);

        if (debugAnimator != null) {
            debugAnimator.cancel();
        }
        fasterAnimator.cancel();

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
                shrinkAnimator.setDuration(500);
                shrinkAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(@NonNull ValueAnimator animation) {
                        float scale = (float) animation.getAnimatedValue();
                        frameLayout.setScaleX(scale);
                        frameLayout.setScaleY(scale);
                    }
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
