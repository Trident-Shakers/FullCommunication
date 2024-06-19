package org.shakers.fullcommunication.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.MaterialToolbar;

import org.shakers.fullcommunication.R;

import java.util.HashMap;
import java.util.Map;

class ButtonResources {
    int numberId;
    int textId;
    int imageId;

    ButtonResources(int numberId, int textId, int imageId) {
        this.numberId = numberId;
        this.textId = textId;
        this.imageId = imageId;
    }
}

public class HowToPlayFragment extends Fragment {
    private Map<Integer, ButtonResources> buttonResourcesMap = new HashMap<>();

    public HowToPlayFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_how_to_play, container, false);

        MaterialToolbar toolbar = view.findViewById(R.id.topAppBar);

        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) requireActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(v -> {
            // Handle the back button event
            ((MainActivity) requireActivity()).loadFragment(new TitleFragment());
        });

        buttonResourcesMap.put(R.id.button1, new ButtonResources(R.string.htp_one, R.string.htp_genreTips, R.drawable.tips_genre));
        buttonResourcesMap.put(R.id.button2, new ButtonResources(R.string.htp_two, R.string.htp_topicTips, R.drawable.tips_topic));
        buttonResourcesMap.put(R.id.button3, new ButtonResources(R.string.htp_three, R.string.htp_talkTips, R.drawable.tips_talk));

        setupButton(view, R.id.button1);
        setupButton(view, R.id.button2);
        setupButton(view, R.id.button3);

        return view;
    }

    private void setupButton(View view, int buttonId) {
        Button button = view.findViewById(buttonId);
        TextView number = view.findViewById(R.id.number);
        TextView text = view.findViewById(R.id.tipsText);
        ImageView image = view.findViewById(R.id.tipsImage);
        button.setOnClickListener(v -> {
            resetButtons(view);
            setTexts(buttonId, number, text, image);
            button.setBackgroundResource(R.drawable.clicked_circle_button);
            button.setTextColor(getResources().getColor(R.color.white, null));
        });
    }

    private void resetButtons(View view) {
        int[] buttonIds = {R.id.button1, R.id.button2, R.id.button3};
        for (int buttonId : buttonIds) {
            Button button = view.findViewById(buttonId);
            button.setBackgroundResource(R.drawable.circle_button);
            button.setTextColor(getResources().getColor(R.color.primary_color, null));
        }
    }

    private void setTexts(int buttonId, TextView tipsNumber, TextView tipsText, ImageView tipsImage) {
        ButtonResources resources = buttonResourcesMap.get(buttonId);
        if (resources != null) {
            tipsNumber.setText(resources.numberId);
            tipsText.setText(resources.textId);
            tipsImage.setImageResource(resources.imageId);
        }
    }
}