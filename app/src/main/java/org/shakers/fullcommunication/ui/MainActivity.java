package org.shakers.fullcommunication.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import org.shakers.fullcommunication.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 初期画面はTitleFragmentを読み込む
        loadFragment(new TitleFragment());
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
        transaction.commit();
    }

    /**
     * SharedPreferencesに保存されているデータを全て削除します。
     */
    void resetAllPreferences() {
        clearSharedPreferences();
    }

    /**
     * SharedPreferencesに保存されているデータを削除します。
     */
    private void clearSharedPreferences() {
        clearSharedPreferencesByName("DATA");
        clearSharedPreferencesByName("topic_time_count_list");
    }

    private void clearSharedPreferencesByName(String name) {
        SharedPreferences sharedPreferences = getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

}