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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.towerofhanoi.R;
import com.example.towerofhanoi.repository.Settings;

import java.util.ArrayList;

public class DisksButtonsAdapter extends RecyclerView.Adapter<DisksButtonsAdapter.MyViewHolder> {

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public Button diskButton;
        public MyViewHolder(View v) {
            super(v);

            diskButton = (Button) v;
        }
    }

    private Context context;
    private int maxDisks;

    private int selected;

    public DisksButtonsAdapter(Context context, int maxDisks) {
        this.maxDisks = maxDisks;
        this.context = context;
        selected = Settings.getInstance(context).getDisksNumber();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_scores_disksbutton, parent, false);

        return new MyViewHolder(view);
    }

    public int getSelected() {
        return selected;
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.diskButton.setText(String.valueOf(position+1));

        final int disksNumber = position+1;

        if (selected == disksNumber) {
            holder.diskButton.setSelected(true);
        } else {
            holder.diskButton.setSelected(false);
        }

        holder.diskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisksButtonsAdapter.this.setSelected(disksNumber);
                DisksButtonsAdapter.this.notifyDataSetChanged();
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position+1;
    }

    @Override
    public int getItemCount() {
        return maxDisks;
    }
}
