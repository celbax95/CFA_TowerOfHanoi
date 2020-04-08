package com.example.towerofhanoi.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.towerofhanoi.R;
import com.example.towerofhanoi.app.FragmentManager;

public class FragmentSettings extends Fragment {
    public FragmentSettings(Context context, FragmentManager fragmentManager, String name) {
        super(context, fragmentManager, name);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        @SuppressLint("InflateParams")
        View v = inflater.inflate(R.layout.fragment_settings, null);

        initBackbutton(v);

        return v;
    }

    private void initBackbutton(View v) {
        ImageButton backButton = v.findViewById(R.id.settings_button_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public boolean onBackPressed() {
        fragmentManager.setFragment(FragmentManager.MENU);

        return false;
    }
}
