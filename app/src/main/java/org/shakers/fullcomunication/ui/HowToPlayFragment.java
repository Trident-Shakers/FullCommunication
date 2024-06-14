package org.shakers.fullcomunication.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;

import org.shakers.fullcomunication.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HowToPlayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HowToPlayFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HowToPlayFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HowToPlayFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HowToPlayFragment newInstance(String param1, String param2) {
        HowToPlayFragment fragment = new HowToPlayFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.nav_host_fragment, new TitleFragment());
            transaction.commit();
        });

        Button button1 = view.findViewById(R.id.button1);
        Button button2 = view.findViewById(R.id.button2);
        Button button3 = view.findViewById(R.id.button3);
        Button button4 = view.findViewById(R.id.button4);

        final int[] i = {1};

        button1.setOnClickListener(v -> {
            switch (i[0]) {
                case 1:
                    button1.setBackgroundResource(R.drawable.circle_button);
                    button1.setTextColor(getResources().getColor(R.color.primary_color));
                    break;
                case 2:
                    button2.setBackgroundResource(R.drawable.circle_button);
                    button2.setTextColor(getResources().getColor(R.color.primary_color));
                    break;
                case 3:
                    button3.setBackgroundResource(R.drawable.circle_button);
                    button3.setTextColor(getResources().getColor(R.color.primary_color));
                    break;
                case 4:
                    button4.setBackgroundResource(R.drawable.circle_button);
                    button4.setTextColor(getResources().getColor(R.color.primary_color));
                    break;
            }
            i[0] = 1;
            button1.setBackgroundResource(R.drawable.clicked_circle_button);
            button1.setTextColor(getResources().getColor(R.color.white));
        });
        button2.setOnClickListener(v -> {
            switch (i[0]) {
                case 1:
                    button1.setBackgroundResource(R.drawable.circle_button);
                    button1.setTextColor(getResources().getColor(R.color.primary_color));
                    break;
                case 2:
                    button2.setBackgroundResource(R.drawable.circle_button);
                    button2.setTextColor(getResources().getColor(R.color.primary_color));
                    break;
                case 3:
                    button3.setBackgroundResource(R.drawable.circle_button);
                    button3.setTextColor(getResources().getColor(R.color.primary_color));
                    break;
                case 4:
                    button4.setBackgroundResource(R.drawable.circle_button);
                    button4.setTextColor(getResources().getColor(R.color.primary_color));
                    break;
            }
            i[0] = 2;
            button2.setBackgroundResource(R.drawable.clicked_circle_button);
            button2.setTextColor(getResources().getColor(R.color.white));
        });
        button3.setOnClickListener(v -> {
            switch (i[0]) {
                case 1:
                    button1.setBackgroundResource(R.drawable.circle_button);
                    button1.setTextColor(getResources().getColor(R.color.primary_color));
                    break;
                case 2:
                    button2.setBackgroundResource(R.drawable.circle_button);
                    button2.setTextColor(getResources().getColor(R.color.primary_color));
                    break;
                case 3:
                    button3.setBackgroundResource(R.drawable.circle_button);
                    button3.setTextColor(getResources().getColor(R.color.primary_color));
                    break;
                case 4:
                    button4.setBackgroundResource(R.drawable.circle_button);
                    button4.setTextColor(getResources().getColor(R.color.primary_color));
                    break;
            }
            i[0] = 3;
            button3.setBackgroundResource(R.drawable.clicked_circle_button);
            button3.setTextColor(getResources().getColor(R.color.white));
        });
        button4.setOnClickListener(v -> {
            switch (i[0]) {
                case 1:
                    button1.setBackgroundResource(R.drawable.circle_button);
                    button1.setTextColor(getResources().getColor(R.color.primary_color));
                    break;
                case 2:
                    button2.setBackgroundResource(R.drawable.circle_button);
                    button2.setTextColor(getResources().getColor(R.color.primary_color));
                    break;
                case 3:
                    button3.setBackgroundResource(R.drawable.circle_button);
                    button3.setTextColor(getResources().getColor(R.color.primary_color));
                    break;
                case 4:
                    button4.setBackgroundResource(R.drawable.circle_button);
                    button4.setTextColor(getResources().getColor(R.color.primary_color));
                    break;
            }
            i[0] = 4;
            button4.setBackgroundResource(R.drawable.clicked_circle_button);
            button4.setTextColor(getResources().getColor(R.color.white));
        });
        return view;
    }
}

