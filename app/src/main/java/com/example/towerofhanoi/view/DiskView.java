package com.example.towerofhanoi.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.towerofhanoi.model.Disk;
import com.example.towerofhanoi.model.Rod;

import java.util.Arrays;

public class DiskView extends View {

    private static int DISK_HEIGHT = 10;

    private Disk disk;
    private LinearLayout[] rods;

    float baseHeight;

    public DiskView(Context context, Disk disk, LinearLayout[] rods, float baseHeight) {
        super(context);
        this.disk = disk;
        this.rods = rods;
        this.baseHeight = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                baseHeight,
                getResources().getDisplayMetrics()
        );
        d(this.baseHeight);
    }

    public Disk getDisk() {
        return disk;
    }

    public void setDisk(Disk disk) {
        this.disk = disk;
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

        int width = (int) ((disk.getSize()/(float)maxDisks)*baseWidth);

        int height = DISK_HEIGHT;

        int[] location = new int[2];
        holder.getLocationOnScreen(location);

        float x = location[0] + holder.getWidth()/2f - width/2f;

        float y = location[1]+holder.getHeight()-height-baseHeight;

        @SuppressLint("DrawAllocation") Paint p = new Paint();
        p.setColor(Color.RED);

        canvas.drawRoundRect(x, y, x+width, y+height, 10,10, p);

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
