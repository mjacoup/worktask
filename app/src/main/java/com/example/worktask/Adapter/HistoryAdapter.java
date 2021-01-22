package com.example.worktask.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.worktask.Models.HistoryModel;
import com.example.worktask.R;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private ArrayList<HistoryModel> history_operation;
    private LayoutInflater mInflater;

    public HistoryAdapter(Context context, ArrayList<HistoryModel> history_operation) {
        this.mInflater = LayoutInflater.from(context);
        this.history_operation = history_operation;
    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.history_card, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.number.setText(history_operation.get(position).getNumber() + "");
        holder.operation.setText(history_operation.get(position).getOperator());
    }

    @Override
    public int getItemCount() {
        return history_operation.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView operation;
        TextView number;

        ViewHolder(View itemView) {
            super(itemView);
            number = itemView.findViewById(R.id.number);
            operation = itemView.findViewById(R.id.operation_symbol);
        }


    }


}

