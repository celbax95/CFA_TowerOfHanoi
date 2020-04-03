package com.example.towerofhanoi.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.towerofhanoi.R;
import com.example.towerofhanoi.app.MainActivity;

public class FragmentScores extends Fragment {

    MainActivity context;

    ListView listDisksButtons;

    public FragmentScores(MainActivity context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.fragment_scores, null);

        createListDisksButtons(v);

        return v;
    }

    private void createListDisksButtons(View v) {
        listDisksButtons = v.findViewById(R.id.scores_listView_disksButtons);

    }

}
