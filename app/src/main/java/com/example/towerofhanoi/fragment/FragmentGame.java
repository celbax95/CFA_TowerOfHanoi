package com.example.towerofhanoi.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.towerofhanoi.R;
import com.example.towerofhanoi.app.FragmentManager;
import com.example.towerofhanoi.model.Disk;
import com.example.towerofhanoi.model.GameListener;
import com.example.towerofhanoi.model.HanoiGame;
import com.example.towerofhanoi.model.Rod;
import com.example.towerofhanoi.model.Score;
import com.example.towerofhanoi.repository.ScoresRepository;
import com.example.towerofhanoi.repository.Settings;
import com.example.towerofhanoi.view.DiskView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FragmentGame extends Fragment implements GameListener {
    private static final double GET_H_FROM_S = 3600D;
    private static final double GET_M_FROM_S = 60D;

    private static final int LEFT_ROD = 0;
    private static final int MIDDLE_ROD = 1;
    private static final int RIGHT_ROD = 2;

    private static final int DISK_UP_ON_MOVE = 50;

    private static final int MAX_DISK_LARGE_HEIGHT = 10;

    private static final int UNDEFINED_DISK_COUNT = -1;

    private HanoiGame game;

    private LinearLayout[] rods;

    private TextView time;
    private TextView moves;

    public FragmentGame(Context context, FragmentManager fragmentManager, String name) {
        super(context, fragmentManager, name);
        diskViews = new HashMap<>();
        game = new HanoiGame();
        game.setGameListener(this);
    }

    private FrameLayout gameFrame;

    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        @SuppressLint("InflateParams")
        final View v = inflater.inflate(R.layout.fragment_game, null);

        gameFrame = v.findViewById(R.id.game_frame);

        rods = new LinearLayout[] {
                v.findViewById(R.id.game_ll_rod_left),
                v.findViewById(R.id.game_ll_rod_middle),
                v.findViewById(R.id.game_ll_rod_right)
        };

        time = v.findViewById(R.id.game_text_time);
        time.setText("");

        moves = v.findViewById(R.id.game_text_moves);
        moves.setText("0");

        initGame();

        initButtonBack(v);

        initButtonReset(v);

        return v;
    }

    private ImageButton resetButton;

    private void initButtonReset(View v) {
        resetButton = v.findViewById(R.id.game_button_reset);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });

    }

    @Override
    public void onStop() {
        game.stopTimer();
        super.onStop();
    }

    @Override
    public void onPause() {
        game.setPause(true);
        super.onPause();
    }

    @Override
    public void onResume() {
        game.setPause(false);
        super.onResume();
    }

    @Override
    public void onStart() {
        resetGame();
        super.onStart();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setRodsClickable(boolean state) {
        if (state) {
            for (int i=0; i < rods.length; i++) {
                initRod(rods,i);
            }
        } else {
            for (LinearLayout rod : rods) {
                rod.setOnTouchListener(null);
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initRod(LinearLayout[] rs, final int ind) {

        final LinearLayout r = rs[ind];

        final LinearLayout rl = rs[LEFT_ROD];
        final LinearLayout rm = rs[MIDDLE_ROD];
        final LinearLayout rr = rs[RIGHT_ROD];

        r.setOnTouchListener(new View.OnTouchListener() {
            float dX;
            float dY;

            Disk d = null;
            DiskView dv;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:

                        d = game.getRod(ind).getTopDisk();

                        if (d == null) {
                            return false;
                        }

                        dv = diskViews.get(d);

                        assert dv != null;
                        dX = dv.getX() - event.getRawX();

                        int[] rLoc = new int[2];
                        r.getLocationOnScreen(rLoc);

                        dv.setY(rLoc[1]-dv.getDiskHeight()-DISK_UP_ON_MOVE);
                        dv.invalidate();
                        break;

                    case MotionEvent.ACTION_MOVE:
                        if (d == null) {
                            return false;
                        }
                        dv.setX(event.getRawX() + dX);
                        dv.invalidate();
                        break;

                    case MotionEvent.ACTION_UP:
                        if (d == null) {
                            return false;
                        }
                        float rlXC = rl.getX()+rl.getWidth()/2f;
                        float rmXC = rm.getX()+rm.getWidth()/2f;
                        float rrXC = rr.getX()+rr.getWidth()/2f;

                        float dXC = dv.getX()+dv.getDiskWidth()/2f;

                        float distToRl = (float) Math.pow(dXC- rlXC, 2);
                        float distToRm = (float) Math.pow(dXC - rmXC, 2);
                        float distToRr = (float) Math.pow(dXC- rrXC, 2);

                        float minDist = Math.min(Math.min(distToRl, distToRm), distToRr);

                        Rod addRod = null;

                        if (minDist == distToRl) {
                            addRod = game.getRod(LEFT_ROD);

                        } else if (minDist == distToRm) {
                            addRod = game.getRod(MIDDLE_ROD);
                        } else if (minDist == distToRr) {
                            addRod = game.getRod(RIGHT_ROD);
                        }

                        if (addRod != null && addRod.canAdd(d)) {
                            addRod.addDisk(d.getHolder().getAndRemove());
                            game.addMove();
                        }

                        dv.setInitialized(false);
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
        TypedValue value = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.color_menu_button_text, value, true);
        resetButton.setColorFilter(value.data);

        Set<Disk> ks = diskViews.keySet();

        for (Disk d : ks) {
            gameFrame.removeView(diskViews.get(d));
        }

        diskViews = new HashMap<>();

        initGame();
    }

    private int diskCount;

    private void initGame() {
        diskCount = Settings.getInstance(context).getDiskCount();

        List<Disk> disks = game.initGame(diskCount);

        for (Disk d : disks) {
            DiskView diskView = new DiskView(context, d, rods, 3, diskCount <= MAX_DISK_LARGE_HEIGHT);
            diskView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.NO_GRAVITY));
            gameFrame.addView(diskView);
            this.diskViews.put(d, diskView);
        }

        setRodsClickable(true);

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

    @Override
    public void onGameWin() {
        if (diskCount != UNDEFINED_DISK_COUNT) {
            ScoresRepository sr = ScoresRepository.getInstance(context);

            Score s = new Score();
            s.setDate(System.currentTimeMillis());
            s.setDisks(diskCount);
            s.setMoves(game.getMoveCount());
            s.setTime(game.getSecondsSinceStarted());

            sr.add(s);

            setRodsClickable(false);
            resetButton.setColorFilter(Color.YELLOW);
        }
    }

    @Override
    public void onMovementsChanged(int movementCount) {
        moves.setText(String.valueOf(movementCount));
    }

    @Override
    public void onTimeChanged(long time) {
        this.time.setText(getTimeString(time));
    }

    @SuppressLint("DefaultLocale")
    private String getTimeString(long time) {
        long tmpTime = time;

        String ret = "";
        boolean firstDone = false;

        int h = (int) Math.floor(tmpTime/ GET_H_FROM_S);
        if (h != 0) {
            ret+=(h+":");
            firstDone = true;
        }
        tmpTime%= GET_H_FROM_S;

        int m = (int) Math.floor(tmpTime/ GET_M_FROM_S);
        if (m != 0 || firstDone) {
            ret = String.format(ret+"%02d:", m);
            firstDone = true;
        }
        tmpTime%= GET_M_FROM_S;

        int s = (int) Math.floor(tmpTime);
        ret = String.format(ret+"%02d", s);

        return ret;
    }
}
