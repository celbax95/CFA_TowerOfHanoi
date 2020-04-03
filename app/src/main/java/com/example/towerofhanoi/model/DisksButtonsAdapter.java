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

    public DisksButtonsAdapter(Context context, int maxDisks) {
        this.maxDisks = maxDisks;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_scores_disksbutton, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.diskButton.setText(String.valueOf(position+1));
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
