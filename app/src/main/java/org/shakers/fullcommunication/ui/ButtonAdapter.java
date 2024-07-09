package org.shakers.fullcommunication.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;


import androidx.recyclerview.widget.RecyclerView;

import org.shakers.fullcommunication.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ButtonAdapter extends RecyclerView.Adapter<ButtonViewHolder> {
    private int buttonCount;
    private Context context;

    public ButtonAdapter(int buttonCount, Context context) {
        this.buttonCount = buttonCount;
        this.context = context;
    }

    @Override
    public ButtonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_genre_button, parent, false);
        return new ButtonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ButtonViewHolder holder, int position) {
        String[] buttonTexts = loadButtonTexts();
        if (buttonTexts != null && position < buttonTexts.length) {
            holder.button.setText(buttonTexts[position]);
            holder.button.setTextOn(buttonTexts[position]);
            holder.button.setTextOff(buttonTexts[position]);
        }
        // 文字数に応じてフォントサイズを調整
        switch (buttonTexts[position].length()) {
            case 1:
                holder.button.setTextSize(28);
                break;
            case 2:
                holder.button.setTextSize(24);
                break;
            case 3:
                holder.button.setTextSize(20);
                break;
            case 4:
                holder.button.setTextSize(16);
                break;
            default:
                holder.button.setTextSize(14);
                break;
        }
        // 必要に応じてクリックリスナーを設定する
        holder.button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences preferences = context.getSharedPreferences("DATA", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                String key = String.valueOf(position + 1); // 1から21までのキーを生成
                if (isChecked) {
                    // ToggleButtonがONのときの処理
                    // ボタンのテキストをSharedPreferencesに保存
                    editor.putString(key, holder.button.getText().toString());
                } else {
                    // ToggleButtonがOFFのときの処理
                    // SharedPreferencesからボタンのテキストを削除
                    editor.remove(key);
                }
                editor.apply();
            }
        });
    }

    @Override
    public int getItemCount() {
        return buttonCount;
    }

    private String[] loadButtonTexts() {
        try {
            InputStream is = context.getAssets().open("genreList.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            return lines.toArray(new String[0]);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}