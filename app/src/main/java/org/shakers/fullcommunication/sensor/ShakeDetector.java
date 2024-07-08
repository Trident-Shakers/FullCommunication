package org.shakers.fullcommunication.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;


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
    private static final int SHAKE_WAIT_TIME_MS_SHORT = 100;
    private static final int SHAKE_WAIT_TIME_MS = 1000;
    private static final int LOG_INTERVAL = 1000;

    private long mShakeTimestamp;
    private long mLastLogTimestamp;
    private float mLastX, mLastY, mLastZ;

    private final OnShakeListener mListener;

    public interface OnShakeListener {
        void onShake();

        void unShake();
    }

    public ShakeDetector(OnShakeListener listener) {
        mListener = listener;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        long now = System.currentTimeMillis();

        if ((now - mLastLogTimestamp) > LOG_INTERVAL) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            float deltaX = x - mLastX;
            float deltaY = y - mLastY;
            float deltaZ = z - mLastZ;

            float delta = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);

            Log.d("ShakeDetector",
                    "-----------------------------------\n" +
                            "onSensorChanged:\n" +
                            "x: " + x + " last-x: " + mLastX + "\n" +
                            "y: " + y + " last-y: " + mLastY + "\n" +
                            "z: " + z + " last-z: " + mLastZ + "\n" +
                            "delta: " + delta + "\n" +
                            "now: " + now + "\n" +
                            "mShakeTimestamp: " + mShakeTimestamp + "\n" +
                            "SHAKE_WAIT_TIME_MS: " + SHAKE_WAIT_TIME_MS + "\n" +
                            "SHAKE_THRESHOLD: " + SHAKE_THRESHOLD + "\n" +
                            "result: " + (delta > SHAKE_THRESHOLD) + "\n-----------------------------------");

            mLastLogTimestamp = now;
        }
        if ((now - mShakeTimestamp) > SHAKE_WAIT_TIME_MS_SHORT) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            float deltaX = x - mLastX;
            float deltaY = y - mLastY;
            float deltaZ = z - mLastZ;

            if (deltaX > SHAKE_THRESHOLD || deltaY > SHAKE_THRESHOLD || deltaZ > SHAKE_THRESHOLD) {
                mShakeTimestamp = now;
                mListener.onShake();
            } else {
                mListener.unShake();
            }

            mLastX = x;
            mLastY = y;
            mLastZ = z;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // センサーの精度が変更された場合の処理（必要な場合）
    }

    public void start(SensorManager sensorManager) {
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        if (sensor != null) {
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }
}
