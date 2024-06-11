package org.shakers.fullcomunication.ui;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

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

        Button debugButton = findViewById(R.id.debug_button);
        debugButton.setOnClickListener(v -> {
            // DebugFragmentを読み込むようにする
            loadFragment(new DebugFragment());
        });
    }

    /**
     * Fragmentを読み込めます。
     * <p>
     * Fragmentで使う場合の例: getActivity().loadFragment(new GenreChoiceFragment());
     * </p>
     *
     * @param fragment - 読み込むFragmentをnew FragmentName()で指定してください。
     */
    public void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


}