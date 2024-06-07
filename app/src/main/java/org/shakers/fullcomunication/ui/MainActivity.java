package org.shakers.fullcomunication.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import org.shakers.fullcomunication.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Title Fragmentを読み込むようにする
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.nav_host_fragment, new TitleFragment())
                .commit();
    }
}