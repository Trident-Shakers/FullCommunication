package org.shakers.fullcommunication.ui;

import android.view.View;

import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;

import org.shakers.fullcommunication.R;

public class ButtonViewHolder extends RecyclerView.ViewHolder {
    public Button button;

    public ButtonViewHolder(View view) {
        super(view);
        button = view.findViewById(R.id.button);
    }
}
