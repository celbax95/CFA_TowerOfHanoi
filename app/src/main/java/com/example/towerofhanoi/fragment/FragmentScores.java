package com.example.towerofhanoi.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.towerofhanoi.R;
import com.example.towerofhanoi.app.FragmentManager;
import com.example.towerofhanoi.app.MainActivity;
import com.example.towerofhanoi.model.DisksButtonsAdapter;
import com.example.towerofhanoi.model.Score;
import com.example.towerofhanoi.model.ScoresAdapter;
import com.example.towerofhanoi.repository.ScoresRepository;

import java.util.ArrayList;

public class FragmentScores extends Fragment {

    private static final String NAME = "SCORES";

    private ScoresAdapter scoresAdapter;

    private ScoresRepository scoresRepository;

    public FragmentScores(MainActivity context, FragmentManager fragmentManager, String name) {
        super(context, fragmentManager,name);

        scoresRepository = ScoresRepository.getInstance(context);
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

        initListDisksButtons(v);

        initBackButton(v);

        initListScores(v);

        initDateButton(v);
        initMovesButton(v);
        initTimeButton(v);

        return v;
    }

    private void initTimeButton(View v) {
        Button timeBtn = v.findViewById(R.id.scores_button_time);

        timeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoresAdapter.setScores(scoresRepository.getOrderedByTime(1));
            }
        });
    }

    private void initMovesButton(View v) {
        Button movesBtn = v.findViewById(R.id.scores_button_moves);

        movesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoresAdapter.setScores(scoresRepository.getOrderedByMoves(1));
            }
        });
    }

    private void initDateButton(View v) {
        Button dateBtn = v.findViewById(R.id.scores_button_date);

        dateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoresAdapter.setScores(scoresRepository.getOrderedByDate(1));
            }
        });
    }

    private void initListScores(View v) {
        scoresAdapter = new ScoresAdapter(context);

        ListView scores = v.findViewById(R.id.scores_listView_scores);
        scores.setAdapter(scoresAdapter);

        ArrayList<Score> s = new ArrayList<Score>();
        s.add(new Score(System.currentTimeMillis(), 1 , 50, 56416161561982L));
        s.add(new Score(System.currentTimeMillis(), 1 , 7587, 654284L));
        s.add(new Score(System.currentTimeMillis(), 1 , 785, 654268428561L));
        s.add(new Score(System.currentTimeMillis(), 1 , 786578, 56416161561982L));
        s.add(new Score(System.currentTimeMillis(), 1 , 93, 652498494L));
        s.add(new Score(System.currentTimeMillis(), 1 , 93, 652498494L));
        s.add(new Score(System.currentTimeMillis(), 1 , 93, 652498494L));
        s.add(new Score(System.currentTimeMillis(), 1 , 93, 652498494L));
        s.add(new Score(System.currentTimeMillis(), 1 , 93, 652498494L));
        s.add(new Score(System.currentTimeMillis(), 1 , 93, 652498494L));

        scoresAdapter.setScores(s);
        scoresAdapter.notifyDataSetChanged();
    }

    private void initBackButton(View v) {
        ImageButton backButton = v.findViewById(R.id.scores_button_back);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.setFragment(FragmentManager.MENU);
            }
        });
    }

    private void initListDisksButtons(View v) {
        RecyclerView listDisksButtons = v.findViewById(R.id.scores_listView_disksButtons);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        listDisksButtons.setLayoutManager(layoutManager);

        DisksButtonsAdapter disksButtonsAdapter = new DisksButtonsAdapter(context, 10);
        listDisksButtons.setAdapter(disksButtonsAdapter);
        disksButtonsAdapter.notifyDataSetChanged();
    }
}
