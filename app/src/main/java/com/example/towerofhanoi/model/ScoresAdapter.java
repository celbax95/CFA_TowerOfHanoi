package com.example.towerofhanoi.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.towerofhanoi.R;

import java.util.ArrayList;
import java.util.List;

public class ScoresAdapter extends BaseAdapter {

    private Context context;

    private List<Score> scores;

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }

    public ScoresAdapter(Context context) {
        this.context = context;
        scores = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return scores.size() +1;
    }

    @Override
    public Object getItem(int position) {
        return scores.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (position < scores.size()) {

            Score s = scores.get(position);

            View v = inflater.inflate(R.layout.item_scores, null);

            LinearLayout itemLayout = ((LinearLayout)v.findViewById(R.id.item_scores_layout));

            if (position == 0) {
                itemLayout.setBackground(null);
            }

            ((TextView) v.findViewById(R.id.item_scores_date)).setText(s.getDateString());
            ((TextView) v.findViewById(R.id.item_scores_time)).setText(s.getTimeString());
            ((TextView) v.findViewById(R.id.item_scores_moves)).setText(String.valueOf(s.getMoves()));

            return v;
        } else {
            View v = inflater.inflate(R.layout.empty_layout_40dp, null);


            return v;
        }
    }
}
