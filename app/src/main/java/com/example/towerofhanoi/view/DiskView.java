package com.example.towerofhanoi.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import com.example.towerofhanoi.model.Disk;
import com.example.towerofhanoi.model.Rod;

import java.util.Random;

public class DiskView extends View {

    public static final double MIN_WIDTH = 0.1;
    public static final double MAX_WIDTH = 0.95;
    private static final int COLOR_RANGE_MAX = 220;
    private static final int COLOR_RANGE_MIN = 150;
    private static final int DISK_HEIGHT = 15;
    private static final int DISK_SPACING = 7;
    float baseHeight;
    int color;
    private Disk disk;
    private LinearLayout[] rods;
    private boolean initialized;
    private float x;
    private float y;
    private int width;
    private int height;

    public DiskView(Context context, Disk disk, LinearLayout[] rods, float baseHeight) {
        super(context);
        initialized = false;
        this.disk = disk;
        this.rods = rods;
        this.baseHeight = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                baseHeight,
                getResources().getDisplayMetrics()
        );

        paint = new Paint();

        color = getRandomColor();
    }

    private static int getRandomColor() {
        Random rand = new Random();

        int range = COLOR_RANGE_MAX - COLOR_RANGE_MIN;

        int r = rand.nextInt(range) + COLOR_RANGE_MIN;
        int g = rand.nextInt(range) + COLOR_RANGE_MIN;
        int b = rand.nextInt(range) + COLOR_RANGE_MIN;

        return Color.rgb(r, r, r);
    }

    public Disk getDisk() {
        return disk;
    }

    public void setDisk(Disk disk) {
        this.disk = disk;
    }

    private Paint paint;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (!initialized) {
            Rod rod = disk.getHolder();
            int id = rod.getId();

            LinearLayout holder = rods[id];

            //int baseHeight = holder.getHeight();
            int baseWidth = holder.getWidth();

            int maxDisks = rod.getMaxSize();

            width = (int) ((((disk.getSize() - 1) * MAX_WIDTH / maxDisks) + MIN_WIDTH) * baseWidth);

            height = DISK_HEIGHT;

            @SuppressLint("DrawAllocation") int[] location = new int[2];
            holder.getLocationOnScreen(location);

            x = location[0] + holder.getWidth() / 2f - width / 2f;

            y = location[1] + holder.getHeight() - height * disk.getHeight() - baseHeight - DISK_SPACING * (disk.getHeight());

            initialized = true;
        }

        paint.setColor(color);

        //p.setColor(Color.parseColor("#e0e0e0"));

        paint.setTextSize(15);

        canvas.drawRoundRect(x, y, x + width, y + height, 10, 10, paint);

        paint.setColor(Color.BLACK);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(String.valueOf(disk.getSize()),
                x + width / 2f,
                y + height / 2f - ((paint.descent() + paint.ascent()) / 2f),
                paint);
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public void setX(float x) {
        this.x = x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public void setY(float y) {
        this.y = y;
    }

    public boolean isInitialized() {
        return initialized;
    }

    public void setInitialized(boolean initialized) {
        this.initialized = initialized;
    }

    @Override
    public void invalidate() {
        initialized = false;
        super.invalidate();
    }

    private void d(Object... o) {

        String s = "";

        for (Object o1 : o) {
            s += o1.toString() + " ";
        }

        Log.d("DISK_VIEW", s.substring(0, s.length() - 1));
    }
}