package com.example.towerofhanoi.fragment;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.towerofhanoi.R;
import com.example.towerofhanoi.app.MainActivity;
import com.example.towerofhanoi.init.InitApplication;

public class FragmentMenu extends Fragment {

    MainActivity context;

    ImageButton darkLightModeButton;

    public FragmentMenu(MainActivity context) {
        this.context = context;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.fragment_menu, null);

        createDarkLightModeButton(v);

        return v;
    }

    private void createDarkLightModeButton(View v) {
        darkLightModeButton = v.findViewById(R.id.menu_button_darkLightMode);

        if (InitApplication.getInstance(context).isNightModeEnabled()) {
            darkLightModeButton.setImageResource(R.drawable.emoji_moon);
        } else {
            darkLightModeButton.setImageResource(R.drawable.emoji_sun);
        }

        darkLightModeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.toggleNightMode();
            }
        });
    }
}