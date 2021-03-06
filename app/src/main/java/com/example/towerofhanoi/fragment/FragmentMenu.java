package com.example.towerofhanoi.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.towerofhanoi.R;
import com.example.towerofhanoi.app.FragmentManager;
import com.example.towerofhanoi.app.MainActivity;
import com.example.towerofhanoi.init.InitApplication;

public class FragmentMenu extends Fragment {

    ImageButton darkLightModeButton;

    Button scoresButton;

    public FragmentMenu(MainActivity context, FragmentManager fragmentManager, String name) {
        super(context, fragmentManager, name);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        @SuppressLint("InflateParams")
        View v = inflater.inflate(R.layout.fragment_menu, null);

        initDarkLightModeButton(v);

        initPlayButton(v);

        initScoresButton(v);

        initSettingsButton(v);

        return v;
    }

    private void initSettingsButton(View v) {
        ImageButton settingsButton = v.findViewById(R.id.menu_button_settings);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.setFragment(FragmentManager.SETTINGS);
            }
        });
    }

    private void initPlayButton(View v) {
        Button playButton = v.findViewById(R.id.menu_button_play);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.setFragment(FragmentManager.GAME);
            }
        });
    }

    private void initScoresButton(View v) {
        scoresButton = v.findViewById(R.id.menu_button_scores);
        scoresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.setFragment(FragmentManager.SCORES);
            }
        });
    }

    private void initDarkLightModeButton(View v) {
        darkLightModeButton = v.findViewById(R.id.menu_button_darkLightMode);

        if (InitApplication.getInstance(context).isNightModeEnabled()) {
            darkLightModeButton.setImageResource(R.drawable.emoji_moon);
        } else {
            darkLightModeButton.setImageResource(R.drawable.emoji_sun);
        }

        darkLightModeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.toggleNightMode();
            }
        });
    }
}
