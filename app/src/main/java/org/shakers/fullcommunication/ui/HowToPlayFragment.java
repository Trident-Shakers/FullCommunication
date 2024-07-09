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
import java.util.Objects;

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

/**
 * プレイ方法画面のFragment
 * <p>プレイ方法を説明する画面です。</p>
 * 画面遷移の流れ:
 * <br>
 * {@link TitleFragment} -> HowToPlayFragment
 * <br>
 * HowToPlayFragment -> {@link TopicFragment}
 */
public class HowToPlayFragment extends Fragment {
    private final Map<Integer, ButtonResources> buttonResourcesMap = new HashMap<>();
    private static final int[] BUTTON_IDS = {R.id.button1, R.id.button2, R.id.button3};
    private TextView numberTextView;
    private TextView tipsTextView;
    private ImageView tipsImageView;

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
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(v -> ((MainActivity) requireActivity()).loadFragment(new TitleFragment()));

        buttonResourcesMap.put(R.id.button1, new ButtonResources(R.string.htp_button_one, R.string.htp_textview_genreTips, R.drawable.tips_genre));
        buttonResourcesMap.put(R.id.button2, new ButtonResources(R.string.htp_button_two, R.string.htp_textview_topicTips, R.drawable.tips_topic));
        buttonResourcesMap.put(R.id.button3, new ButtonResources(R.string.htp_button_three, R.string.htp_textview_talkTips, R.drawable.tips_talk));

        numberTextView = view.findViewById(R.id.number);
        tipsTextView = view.findViewById(R.id.tipsText);
        tipsImageView = view.findViewById(R.id.tipsImage);

        for (int buttonId : BUTTON_IDS) {
            setupButton(view, buttonId);
        }

        return view;
    }

    private void setupButton(View view, int buttonId) {
        Button button = view.findViewById(buttonId);
        button.setOnClickListener(v -> {
            resetButtons(view);
            setTexts(buttonId);
            button.setBackgroundResource(R.drawable.clicked_circle_button);
            button.setTextColor(getResources().getColor(R.color.white, null));
        });
    }

    private void resetButtons(View view) {
        for (int buttonId : BUTTON_IDS) {
            Button button = view.findViewById(buttonId);
            button.setBackgroundResource(R.drawable.circle_button);
            button.setTextColor(getResources().getColor(R.color.primary_color, null));
        }
    }

    private void setTexts(int buttonId) {
        ButtonResources resources = buttonResourcesMap.get(buttonId);
        if (resources != null) {
            numberTextView.setText(resources.numberId);
            tipsTextView.setText(resources.textId);
            tipsImageView.setImageResource(resources.imageId);
        }
    }
}
