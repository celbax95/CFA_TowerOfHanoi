package com.example.towerofhanoi.fragment;

import android.os.Bundle;
import android.util.Log;
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
import com.example.towerofhanoi.model.DiskSelectorListener;
import com.example.towerofhanoi.model.DisksButtonsAdapter;
import com.example.towerofhanoi.model.Score;
import com.example.towerofhanoi.model.ScoresAdapter;
import com.example.towerofhanoi.repository.ScoresRepository;
import com.example.towerofhanoi.repository.Settings;

import java.util.List;

public class FragmentScores extends Fragment implements DiskSelectorListener {

    private static final String NAME = "SCORES";
    private Button timeBtn;
    private Button movesBtn;
    private Button dateBtn;

    private DisksButtonsAdapter disksButtonsAdapter;

    private ScoresAdapter scoresAdapter;
    private ScoresRepository scoresRepository;

    private Orderer orderer;
    private int diskCountSelected;

    public FragmentScores(MainActivity context, FragmentManager fragmentManager, String name) {
        super(context, fragmentManager, name);

        scoresRepository = ScoresRepository.getInstance(context);

        orderer = Orderer.DATE;
        diskCountSelected = 0;
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

        initDateButton(v);
        initMovesButton(v);
        initTimeButton(v);

        initListScores(v);

        return v;
    }

    private void selectOrder(Orderer orderer) {
        this.orderer = orderer;
        dateBtn.setSelected(orderer == Orderer.DATE);
        movesBtn.setSelected(orderer == Orderer.MOVES);
        timeBtn.setSelected(orderer == Orderer.TIME);
    }

    private void initTimeButton(View v) {
        timeBtn = v.findViewById(R.id.scores_button_time);

        timeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectOrder(Orderer.TIME);
                refreshScoreList();
            }
        });
    }

    private void initMovesButton(View v) {
        movesBtn = v.findViewById(R.id.scores_button_moves);

        movesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectOrder(Orderer.MOVES);
                refreshScoreList();
            }
        });
    }

    private void initDateButton(View v) {
        dateBtn = v.findViewById(R.id.scores_button_date);

        dateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectOrder(Orderer.DATE);
                refreshScoreList();
            }
        });
    }

    private void initListScores(View v) {
        scoresAdapter = new ScoresAdapter(context);

        ListView scores = v.findViewById(R.id.scores_listView_scores);
        scores.setAdapter(scoresAdapter);

        selectOrder(Orderer.DATE);
        diskCountSelected = disksButtonsAdapter.getSelected();
    }

    @Override
    public void onStart() {
        refreshScoreList();
        super.onStart();
    }

    private void refreshScoreList() {
        List<Score> scores = null;

        switch (orderer) {
            case DATE:
                scores = scoresRepository.getOrderedByDate(diskCountSelected);
                break;
            case MOVES:
                scores = scoresRepository.getOrderedByMoves(diskCountSelected);
                break;
            case TIME:
                scores = scoresRepository.getOrderedByTime(diskCountSelected);
                break;
        }
        if (scores != null) {
            Log.d("SCORES", "REFRESH");
            scoresAdapter.setScores(scores);
            scoresAdapter.notifyDataSetChanged();
        }
    }

    private void initBackButton(View v) {
        ImageButton backButton = v.findViewById(R.id.scores_button_back);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initListDisksButtons(View v) {
        RecyclerView listDisksButtons = v.findViewById(R.id.scores_listView_disksButtons);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        listDisksButtons.setLayoutManager(layoutManager);

        disksButtonsAdapter = new DisksButtonsAdapter(context, Settings.MAX_DISK_COUNT);
        disksButtonsAdapter.setSelectionListener(this);
        listDisksButtons.setAdapter(disksButtonsAdapter);
        disksButtonsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDiskSelectorSelected(int diskCount) {
        diskCountSelected = diskCount;
        refreshScoreList();
    }

    private enum Orderer {
        DATE, MOVES, TIME
    }
}
