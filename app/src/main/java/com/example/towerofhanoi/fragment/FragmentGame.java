package com.example.towerofhanoi.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.towerofhanoi.R;
import com.example.towerofhanoi.app.FragmentManager;
import com.example.towerofhanoi.model.Disk;
import com.example.towerofhanoi.model.HanoiGame;
import com.example.towerofhanoi.view.DiskView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FragmentGame extends Fragment {

    private HanoiGame game;

    private LinearLayout[] rods;

    public FragmentGame(Context context, FragmentManager fragmentManager, String name) {
        super(context, fragmentManager, name);
        diskViews = new HashMap<>();
        game = new HanoiGame();
    }

    private FrameLayout gameFrame;

    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        final View v = inflater.inflate(R.layout.fragment_game, null);

        gameFrame = v.findViewById(R.id.game_frame);

        rods = new LinearLayout[] {
                v.findViewById(R.id.game_ll_rod_left),
                v.findViewById(R.id.game_ll_rod_middle),
                v.findViewById(R.id.game_ll_rod_right)
        };

        initButtonBack(v);

        initGame();

        initRod(rods,0, v);

        initButtonReset(v);

        return v;
    }

    private void initButtonReset(View v) {
        ImageButton b = v.findViewById(R.id.game_button_reset);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });

    }

    @SuppressLint("ClickableViewAccessibility")
    private void initRod(LinearLayout[] rs, final int ind, View v) {

        LinearLayout r = rs[ind];

        final LinearLayout rl = rs[0];
        final LinearLayout rm = rs[1];
        final LinearLayout rr = rs[2];

        r.setOnTouchListener(new View.OnTouchListener() {
            float dX;
            float dY;

            Disk d = null;
            DiskView dv;


            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int lastAction;

                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:

                        d = game.getRod(ind).getTopDisk();
                        dv = diskViews.get(d);

                        dX = dv.getX() - event.getRawX();
                        // dY = view.getY() - event.getRawY();
                        dv.setY(100);
                        lastAction = MotionEvent.ACTION_DOWN;
                        break;

                    case MotionEvent.ACTION_MOVE:
                        // view.setY(event.getRawY() + dY);
                        dv.setX(event.getRawX() + dX);
                        lastAction = MotionEvent.ACTION_MOVE;
                        break;

                    case MotionEvent.ACTION_UP:

                        float rlXC = rl.getX()+rl.getWidth()/2f;
                        float rmXC = rm.getX()+rm.getWidth()/2f;
                        float rrXC = rr.getX()+rr.getWidth()/2f;

                        float distToRl = (float) Math.pow((dv.getX()+dv.getWidth()/2f) - rlXC, 2);
                        float distToRm = (float) Math.pow((dv.getX()+dv.getWidth()/2f) - rmXC, 2);
                        float distToRr = (float) Math.pow((dv.getX()+dv.getWidth()/2f) - rrXC, 2);

                        d(distToRl, distToRm, distToRr);

                        float minDist = Math.min(Math.min(distToRl, distToRm), distToRr);

                        if (minDist == distToRl) {
                            game.getRod(0).addDisk(d.getHolder().getAndRemove());
                        } else if (minDist == distToRm) {
                            game.getRod(1).addDisk(d.getHolder().getAndRemove());
                        } else if (minDist == distToRr) {
                            game.getRod(2).addDisk(d.getHolder().getAndRemove());
                        }

                        dv.invalidate();

                        break;

                    default:
                        return false;
                }
                return true;
            }
        });
    }

    private Map<Disk, DiskView> diskViews;

    private void resetGame() {

        Set<Disk> ks = diskViews.keySet();

        for (Disk d : ks) {
            gameFrame.removeView(diskViews.get(d));
        }

        diskViews = new HashMap<>();

        initGame();
    }

    private void initGame() {
        // Settings.getInstance(context).getDisksNumber()

        List<Disk> disks = game.initGame(23);

        for (Disk d : disks) {
            DiskView diskView = new DiskView(context, d, rods, 3);
            gameFrame.addView(diskView);
            this.diskViews.put(d, diskView);
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

    private void d(Object... o) {

        String s = "";

        for (Object o1 : o) {
            s += o1.toString() + " ";
        }

        Log.d("FRAG_GAME", s.substring(0, s.length() - 1));
    }

    @Override
    public boolean onBackPressed() {
        fragmentManager.setFragment(FragmentManager.MENU);

        return false;
    }
}
