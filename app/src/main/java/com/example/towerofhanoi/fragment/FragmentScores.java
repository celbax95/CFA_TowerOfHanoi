package com.example.towerofhanoi.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.towerofhanoi.R;
import com.example.towerofhanoi.app.FragmentManager;
import com.example.towerofhanoi.app.MainActivity;

public class FragmentScores extends Fragment {

    private static final String NAME = "SCORES";

    ListView listDisksButtons;
    ImageButton backButton;

    public FragmentScores(MainActivity context, FragmentManager fragmentManager, String name) {
        super(context, fragmentManager,name);
    }

    @Override
    public boolean onBackPressed() {

        fragmentManager.setFragment(FragmentManager.MENU);

        return false;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.fragment_scores, null);

        createListDisksButtons(v);

        createBackButton(v);

        return v;
    }

    private void createBackButton(View v) {
        backButton = v.findViewById(R.id.scores_button_back);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.setFragment(FragmentManager.MENU);
            }
        });

    }

    private void createListDisksButtons(View v) {
        listDisksButtons = v.findViewById(R.id.scores_listView_disksButtons);

    }

}
