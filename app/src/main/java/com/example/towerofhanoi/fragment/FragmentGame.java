package com.example.towerofhanoi.fragment;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.towerofhanoi.R;
import com.example.towerofhanoi.app.FragmentManager;
import com.example.towerofhanoi.model.Disk;
import com.example.towerofhanoi.model.HanoiGame;
import com.example.towerofhanoi.repository.Settings;
import com.example.towerofhanoi.view.DiskView;

import java.util.List;

public class FragmentGame extends Fragment {

    private HanoiGame game;

    private LinearLayout[] rods;

    public FragmentGame(Context context, FragmentManager fragmentManager, String name) {
        super(context, fragmentManager, name);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        final View v = inflater.inflate(R.layout.fragment_game, null);

        rods = new LinearLayout[] {
                v.findViewById(R.id.game_ll_rod_left),
                v.findViewById(R.id.game_ll_rod_middle),
                v.findViewById(R.id.game_ll_rod_right)
        };

        initButtonBack(v);

        initGame(v);

        return v;
    }

    private void initGame(View v) {
        // Settings.getInstance(context).getDisksNumber()
        game = new HanoiGame();

        List<Disk> disks = game.initGame(23);

        FrameLayout gameFrame = v.findViewById(R.id.game_frame);

        for (Disk d : disks) {
            DiskView diskView = new DiskView(context, d, rods, 3);
            gameFrame.addView(diskView);
        }

        gameFrame.invalidate();
    }

    private void initButtonBack(View v) {
        ImageButton back = v.findViewById(R.id.game_button_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void d(Object o) {
        Log.d("GAME", o.toString());
    }

    @Override
    public boolean onBackPressed() {
        fragmentManager.setFragment(FragmentManager.MENU);

        return false;
    }
}
