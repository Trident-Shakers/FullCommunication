package org.shakers.fullcommunication.ui.animation;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

public class AnimationHelper {

    OnAnimationEndListener listener;
    private float currentScale = 1.0f;
    private ValueAnimator shrinkAnimator;
    private ValueAnimator normalSpeedAnimator;
    private ValueAnimator fasterAnimator;
    private boolean animationCanceled_normal = false;
    private boolean animationCanceled_faster = false;
    final long MIDDLE_DURATION = 180000;
    final long SHRINK_DURATION = 500;
    final long FASTER_DURATION = 1000;
    final float MAX_SCALE = 5.0f;

    public interface OnAnimationEndListener {
        void onAnimationEnd();
    }

    public AnimationHelper(OnAnimationEndListener listener) {
        this.listener = listener;
    }

    public void startNormalAnimation(FrameLayout frameLayout) {
        if (shrinkAnimator != null && shrinkAnimator.isRunning()) {
            return;
        }
        currentScale = frameLayout.getScaleX();
        if (normalSpeedAnimator != null && normalSpeedAnimator.isRunning()) {
            return;
        }
        if (fasterAnimator != null && fasterAnimator.isRunning()) {
            fasterAnimator.cancel();
        }
        normalSpeedAnimator = ValueAnimator.ofFloat(currentScale, MAX_SCALE);

        float middle_duration = MIDDLE_DURATION / MAX_SCALE * (MAX_SCALE - currentScale);
        normalSpeedAnimator.setDuration((long) middle_duration);

        normalSpeedAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(@NonNull ValueAnimator animation) {
                float scale = (float) animation.getAnimatedValue();
                frameLayout.setScaleX(scale);
                frameLayout.setScaleY(scale);
            }
        });

        normalSpeedAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationEnd(@NonNull Animator animation) {
                shrinkAnimator = ValueAnimator.ofFloat(5.0f, 1.0f);
                shrinkAnimator.setDuration(SHRINK_DURATION);
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
                        listener.onAnimationEnd();
                    }

                    @Override
                    public void onAnimationStart(@NonNull Animator animation) {
                    }

                    @Override
                    public void onAnimationCancel(@NonNull Animator animation) {
                    }

                    @Override
                    public void onAnimationRepeat(@NonNull Animator animation) {
                    }
                });
                if (!animationCanceled_normal) shrinkAnimator.start();
            }

            @Override
            public void onAnimationStart(@NonNull Animator animation) {
                animationCanceled_normal = false;
            }

            @Override
            public void onAnimationCancel(@NonNull Animator animation) {
                animationCanceled_normal = true;
            }

            @Override
            public void onAnimationRepeat(@NonNull Animator animation) {
            }
        });
        normalSpeedAnimator.start();
    }

    public void startFasterAnimation(FrameLayout frameLayout) {
        if (shrinkAnimator != null && shrinkAnimator.isRunning()) {
            return;
        }
        if (normalSpeedAnimator != null && normalSpeedAnimator.isRunning()) {
            normalSpeedAnimator.cancel();
        }
        if (fasterAnimator != null && fasterAnimator.isRunning()) {
            return;
        }
        currentScale = frameLayout.getScaleX();
        fasterAnimator = ValueAnimator.ofFloat(currentScale, MAX_SCALE);
        float faster_duration = FASTER_DURATION / MAX_SCALE * (MAX_SCALE - currentScale);
        fasterAnimator.setDuration((long) faster_duration);



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
                shrinkAnimator = ValueAnimator.ofFloat(5.0f, 1.0f);
                shrinkAnimator.setDuration(SHRINK_DURATION);
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
                        listener.onAnimationEnd();
                    }

                    @Override
                    public void onAnimationStart(@NonNull Animator animation) {
                    }

                    @Override
                    public void onAnimationCancel(@NonNull Animator animation) {
                    }

                    @Override
                    public void onAnimationRepeat(@NonNull Animator animation) {
                    }
                });
                if (!animationCanceled_faster) shrinkAnimator.start();
            }

            @Override
            public void onAnimationStart(@NonNull Animator animation) {
                animationCanceled_faster = false;
            }

            @Override
            public void onAnimationCancel(@NonNull Animator animation) {
                animationCanceled_faster = true;
            }

            @Override
            public void onAnimationRepeat(@NonNull Animator animation) {
            }
        });
        fasterAnimator.start();
    }
}
