package com.example.towerofhanoi.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.towerofhanoi.R;
import com.example.towerofhanoi.app.FragmentManager;
import com.example.towerofhanoi.repository.ScoresRepository;
import com.example.towerofhanoi.repository.Settings;

public class FragmentSettings extends Fragment {
    public FragmentSettings(Context context, FragmentManager fragmentManager, String name) {
        super(context, fragmentManager, name);
    }

    private static final String REMOVE_BUTTON_TEXT = "Delete all Scores";
    private static final String REMOVE_BUTTON_TEXT_WARNING = "Are your sure ?";

    private Settings settings;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        settings = Settings.getInstance(context);
        diskCount = settings.getDiskCount();

        deleteButtonWarningState = null;

        @SuppressLint("InflateParams")
        View v = inflater.inflate(R.layout.fragment_settings, null);

        initBackground(v);

        initBackbutton(v);

        initDiskCountSelection(v);

        initDeleteScoreButton(v);

        return v;
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initBackground(View v) {
        LinearLayout background = v.findViewById(R.id.settings_ll_background);
        background.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                setDeleteButtonWarningState(false);
                return false;
            }
        });
    }

    private void initDeleteScoreButton(View v) {
        deleteScoresButton = v.findViewById(R.id.settings_button_deleteScores);

        setDeleteButtonWarningState(false);
    }

    private Boolean deleteButtonWarningState;

    private void setDeleteButtonWarningState(Boolean state) {
        if ( state == deleteButtonWarningState)
            return;

        deleteButtonWarningState = state;
        if (!state) {
            deleteScoresButton.setText(REMOVE_BUTTON_TEXT);

            deleteScoresButton.getBackground().setColorFilter(null);

            deleteScoresButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setDeleteButtonWarningState(true);
                }
            });
        } else {
            deleteScoresButton.setText(REMOVE_BUTTON_TEXT_WARNING);

            deleteScoresButton.getBackground().setColorFilter(
                    Color.parseColor("#ff0000"), PorterDuff.Mode.OVERLAY);

            deleteScoresButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setDeleteButtonWarningState(false);

                    ScoresRepository.getInstance(context).removeAll();
                }
            });
        }
    }

    private Button deleteScoresButton;

    private TextView diskCountText;

    @SuppressLint("SetTextI18n")
    private void refreshDiskCountText() {
        String value = (String) diskCountText.getText();

        value = value.substring(0,value.indexOf(":")+1) + " " + diskCount;

        diskCountText.setText(value);
    }

    private void d(Object... o) {

        String s = "";

        for (Object o1 : o) {
            s += o1.toString() + " ";
        }

        Log.d("FRAG_SETTINGS", s.substring(0, s.length() - 1));
    }

    private int diskCount;

    private void initDiskCountSelection(View v) {
        SeekBar sb = v.findViewById(R.id.settings_seekbar_diskCount);
        diskCountText = v.findViewById(R.id.settings_text_diskCount);

        sb.setMax(Settings.MAX_DISK_COUNT-Settings.MIN_DISK_COUNT);
        sb.setProgress(diskCount-Settings.MIN_DISK_COUNT);

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                diskCount = Settings.MIN_DISK_COUNT + progress;
                refreshDiskCountText();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        refreshDiskCountText();
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
        saveDiskCount();

        fragmentManager.setFragment(FragmentManager.MENU);

        return false;
    }

    private void saveDiskCount() {
        if (diskCount != settings.getDiskCount()) {
            settings.setDiskCount(diskCount);
        }
    }
}
