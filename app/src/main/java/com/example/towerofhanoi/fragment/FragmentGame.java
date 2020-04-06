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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.towerofhanoi.R;
import com.example.towerofhanoi.app.FragmentManager;

public class FragmentGame extends Fragment {

    private int on;

    public FragmentGame(Context context, FragmentManager fragmentManager, String name) {
        super(context, fragmentManager, name);
        on = 1;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.fragment_game, null);

        final ViewGroup _root = (ViewGroup)v.findViewById(R.id.root);

        final LinearLayout red = v.findViewById(R.id.red);
        final LinearLayout blue = v.findViewById(R.id.blue);

        final Button b = v.findViewById(R.id.drag);

        red.setOnTouchListener(new View.OnTouchListener() {
            float dX;
            float dY;

            boolean onMe = false;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int lastAction;

                if (on != 2) {
                    return false;
                }

                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        dX = b.getX() - event.getRawX();
                        // dY = view.getY() - event.getRawY();
                        b.setY(100);
                        lastAction = MotionEvent.ACTION_DOWN;
                        break;

                    case MotionEvent.ACTION_MOVE:
                        // view.setY(event.getRawY() + dY);
                        b.setX(event.getRawX() + dX);
                        lastAction = MotionEvent.ACTION_MOVE;
                        break;

                    case MotionEvent.ACTION_UP:

                        float blueXC = blue.getX()+blue.getWidth()/2f;
                        final float blueYC = blue.getY()+blue.getHeight()/2f;

                        float redXC = red.getX()+red.getWidth()/2f;
                        final float redYC = red.getY()+red.getHeight()/2f;

                        float distToRed = (float) Math.pow((b.getY()+b.getHeight()/2f) - redYC, 2)
                                + (float) Math.pow((b.getX()+b.getWidth()/2f) - redXC, 2);
                        float distToBlue = (float) Math.pow((b.getY()+b.getHeight()/2f) - blueYC, 2)
                                + (float) Math.pow((b.getX()+b.getWidth()/2f) - blueXC, 2);

                        Log.d(String.valueOf(redYC), String.valueOf(blueYC));

                        if (distToBlue < distToRed) {
                            b.setX(blueXC-b.getWidth()/2f);
                            b.setY(blueYC-b.getHeight()/2f);
                            on = 1;

                        } else {
                            b.setX(redXC-b.getWidth()/2f);
                            b.setY(redYC-b.getHeight()/2f);
                            on = 2;
                        }

                        break;

                    default:
                        return false;
                }
                return true;
            }
        });

        blue.setOnTouchListener(new View.OnTouchListener() {
            float dX;
            float dY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int lastAction;

                if (on != 1) {
                    return false;
                }

                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        dX = b.getX() - event.getRawX();
                        // dY = view.getY() - event.getRawY();
                        b.setY(100);
                        lastAction = MotionEvent.ACTION_DOWN;
                        break;

                    case MotionEvent.ACTION_MOVE:
                        // view.setY(event.getRawY() + dY);
                        b.setX(event.getRawX() + dX);
                        lastAction = MotionEvent.ACTION_MOVE;
                        break;

                    case MotionEvent.ACTION_UP:

                        float blueXC = blue.getX()+blue.getWidth()/2f;
                        final float blueYC = blue.getY()+blue.getHeight()/2f;

                        float redXC = red.getX()+red.getWidth()/2f;
                        final float redYC = red.getY()+red.getHeight()/2f;

                        float distToRed = (float) Math.pow((b.getY()+b.getHeight()/2f) - redYC, 2)
                                + (float) Math.pow((b.getX()+b.getWidth()/2f) - redXC, 2);
                        float distToBlue = (float) Math.pow((b.getY()+b.getHeight()/2f) - blueYC, 2)
                                + (float) Math.pow((b.getX()+b.getWidth()/2f) - blueXC, 2);

                        Log.d(String.valueOf(redYC), String.valueOf(blueYC));

                        if (distToBlue < distToRed) {
                            b.setX(blueXC-b.getWidth()/2f);
                            b.setY(blueYC-b.getHeight()/2f);
                            on = 1;

                        } else {
                            b.setX(redXC-b.getWidth()/2f);
                            b.setY(redYC-b.getHeight()/2f);
                            on = 2;
                        }

                        break;

                    default:
                        return false;
                }
                return true;
            }
        });
        return v;
    }

    private void d(Object o) {
        Log.d("DEBUG", o.toString());
    }

    @Override
    public boolean onBackPressed() {
        fragmentManager.setFragment(FragmentManager.MENU);

        return false;
    }
}
