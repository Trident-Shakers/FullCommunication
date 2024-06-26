package org.shakers.fullcommunication.ui;

import android.view.View;

import android.widget.Button;
import android.widget.ToggleButton;

import androidx.recyclerview.widget.RecyclerView;

import org.shakers.fullcommunication.R;

public class ButtonViewHolder extends RecyclerView.ViewHolder {
    public ToggleButton button;

    public ButtonViewHolder(View view) {
        super(view);
        button = view.findViewById(R.id.genreButton);
    }
}
