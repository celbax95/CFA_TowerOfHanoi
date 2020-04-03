package com.example.towerofhanoi.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.towerofhanoi.R;

public class DisksButtonsAdapter extends BaseAdapter {

    private Context context;
    private int maxDisks;

    public DisksButtonsAdapter(Context context, int maxDisks) {
        this.maxDisks = maxDisks;
        this.context = context;
    }

    @Override
    public int getCount() {
        return maxDisks;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position+1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        assert inflater != null;
        @SuppressLint({"ViewHolder", "InflateParams"})
        View rowView = inflater.inflate(R.layout.item_scores_disksbutton, null);

        Button buttonText = rowView.findViewById(R.id.item_scores_disksButton);
        buttonText.setText(String.valueOf(getItemId(position)));

        return rowView;
    }
}
