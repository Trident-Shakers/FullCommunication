package org.shakers.fullcomunication.ui;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.shakers.fullcomunication.R;
import org.shakers.fullcomunication.sensor.ShakeDetector;

public class MainActivity extends AppCompatActivity {

    private SensorManager mSensorManager;
    private ShakeDetector mShakeDetector;
    private Sensor linearAccelerometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        linearAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);

        mShakeDetector = new ShakeDetector(new ShakeDetector.OnShakeListener() {
            @Override
            public void onShake() {
                Toast.makeText(MainActivity.this, "Shake!", Toast.LENGTH_SHORT).show();
            }
        });

        mSensorManager.registerListener(mShakeDetector, linearAccelerometer, SensorManager.SENSOR_DELAY_UI);

    }
}