package com.example.towerofhanoi.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.towerofhanoi.model.Disk;
import com.example.towerofhanoi.model.Rod;

import java.util.Arrays;
import java.util.Random;

public class DiskView extends View {

    private static final int COLOR_RANGE_MAX = 220;
    private static final int COLOR_RANGE_MIN = 150;

    private static final int DISK_HEIGHT = 15;

    private static final int DISK_SPACING = 7;

    public static final double MIN_WIDTH = 0.1;

    public static final double MAX_WIDTH = 0.95;

    private Disk disk;
    private LinearLayout[] rods;

    float baseHeight;

    int color;

    public DiskView(Context context, Disk disk, LinearLayout[] rods, float baseHeight) {
        super(context);
        this.disk = disk;
        this.rods = rods;
        this.baseHeight = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                baseHeight,
                getResources().getDisplayMetrics()
        );
        color = getRandomColor();
    }

    public Disk getDisk() {
        return disk;
    }

    public void setDisk(Disk disk) {
        this.disk = disk;
    }

    private static int getRandomColor() {
        Random rand = new Random();

        int range = COLOR_RANGE_MAX-COLOR_RANGE_MIN;

        int r = rand.nextInt(range) + COLOR_RANGE_MIN;
        int g = rand.nextInt(range) + COLOR_RANGE_MIN;
        int b = rand.nextInt(range) + COLOR_RANGE_MIN;

        return Color.rgb(r,r,r);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Rod rod = disk.getHolder();
        int id = rod.getId();

        LinearLayout holder = rods[id];

        //int baseHeight = holder.getHeight();
        int baseWidth = holder.getWidth();

        int maxDisks = rod.getMaxSize();

        int width = (int) ((((disk.getSize()-1)*MAX_WIDTH/maxDisks)+MIN_WIDTH)*baseWidth);

        int height = DISK_HEIGHT;

        int[] location = new int[2];
        holder.getLocationOnScreen(location);

        float x = location[0] + holder.getWidth()/2f - width/2f;

        float y = location[1]+holder.getHeight()-height*disk.getHeight()-baseHeight - DISK_SPACING*(disk.getHeight());

        @SuppressLint("DrawAllocation") Paint p = new Paint();

        p.setColor(color);

        //p.setColor(Color.parseColor("#e0e0e0"));

        p.setTextSize(15);

        canvas.drawRoundRect(x, y, x+width, y+height, 10,10, p);

        p.setColor(Color.BLACK);
        p.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        p.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(String.valueOf(disk.getSize()), x+width/2f, y+height/2f - ((p.descent() + p.ascent()) / 2f), p);

        invalidate();
    }

    private void d(Object...o) {

        String s = "";

        for (Object o1 : o) {
            s += o1.toString() + " ";
        }

        Log.d("DISK_VIEW", s.substring(0,s.length()-1));
    }
}
