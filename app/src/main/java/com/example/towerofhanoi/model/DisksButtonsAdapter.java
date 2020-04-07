package com.example.towerofhanoi.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.towerofhanoi.R;
import com.example.towerofhanoi.repository.Settings;

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
    private int minDisks;

    private int selected;

    public DisksButtonsAdapter(Context context, int maxDisks) {
        this.maxDisks = maxDisks;
        this.minDisks = Settings.MIN_DISKS_NUMBER;
        this.context = context;
        selected = Settings.getInstance(context).getDiskCount();
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
        holder.diskButton.setText(String.valueOf(position+minDisks));

        final int disksNumber = position+minDisks;

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
        return position+minDisks;
    }

    @Override
    public int getItemCount() {
        return maxDisks-minDisks;
    }
}
