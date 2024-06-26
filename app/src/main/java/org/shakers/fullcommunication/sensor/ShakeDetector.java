package org.shakers.fullcommunication.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

/**
 * 振ったことを検知するクラス
 * <p>
 * このクラスは、端末が振られたことを検知するためのクラスです。
 * このクラスは、{@link SensorEventListener} を実装しており、
 * {@link SensorManager#registerListener(SensorEventListener, Sensor, int)} に登録し
 * {@link SensorEvent} が発生した際に、{@link ShakeDetector.OnShakeListener#onShake()}
 * が呼び出されます。
 * </p>
 */
public class ShakeDetector implements SensorEventListener {
    private static final float SHAKE_THRESHOLD = 20.0f;
    private static final int SHAKE_WAIT_TIME_MS = 500;
    private static final int SHAKE_DURATION_MS = 3000;
    private static final int SHAKE_COUNT = 3;

    private float mLastX, mLastY, mLastZ;

    private final OnShakeListener mListener;
    private final List<Long> mShakeTimestamps = new ArrayList<>();

    public interface OnShakeListener {
        void onShake();
    }

    public ShakeDetector(OnShakeListener listener) {
        mListener = listener;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        long now = System.currentTimeMillis();

        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        float deltaX = x - mLastX;
        float deltaY = y - mLastY;
        float deltaZ = z - mLastZ;

        float delta = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);

        if (delta > SHAKE_THRESHOLD) {
            mShakeTimestamps.add(now);
            Log.d("ShakeDetector", "Shake detected: " + mShakeTimestamps.size() + " times");
            // 古いタイムスタンプを削除
            while (!mShakeTimestamps.isEmpty() && now - mShakeTimestamps.get(0) > SHAKE_DURATION_MS) {
                mShakeTimestamps.remove(0);
            }
            // 一定時間内にSHAKE_COUNT回以上の振動があった場合
            if (mShakeTimestamps.size() >= SHAKE_COUNT) {
                mListener.onShake();
                mShakeTimestamps.clear(); // 検知後にリストをリセット
            }
        }
        mLastX = x;
        mLastY = y;
        mLastZ = z;
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // センサーの精度が変更された場合の処理（必要な場合）
    }
}