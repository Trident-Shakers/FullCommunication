package org.shakers.fullcommunication.ui;

import static android.content.ContentValues.TAG;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.recyclerview.widget.RecyclerView;

import org.shakers.fullcommunication.R;

public class ButtonAdapter extends RecyclerView.Adapter<ButtonViewHolder> {
    private int buttonCount;

    public ButtonAdapter(int buttonCount) {
        this.buttonCount = buttonCount;
    }

    @Override
    public ButtonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_genre_button, parent, false);
        return new ButtonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ButtonViewHolder holder, int position) {
//        holder.button.setText("Button " + (position + 1));
//        // 必要に応じてクリックリスナーを設定する
//        holder.button.setOnClickListener(v -> {
//            // クリック時の処理
//        });
    }

    @Override
    public int getItemCount() {
        return buttonCount;
    }
}

