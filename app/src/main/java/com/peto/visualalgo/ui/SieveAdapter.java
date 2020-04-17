package com.peto.visualalgo.ui;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.peto.visualalgo.R;
import com.peto.visualalgo.model.NumberItem;

import java.util.List;

public class SieveAdapter extends RecyclerView.Adapter<SieveAdapter.CustomViewHolder> {
    private final List<NumberItem> numberList;
    private int colorIdPrime, colorIdNonPrime;

    SieveAdapter(List<NumberItem> numberList, int colorIdPrime, int colorIdNonPrime) {
        this.numberList = numberList;
        this.colorIdPrime = colorIdPrime;
        this.colorIdNonPrime = colorIdNonPrime;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.number_view, parent, false);
        return new CustomViewHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        NumberItem numberItem = numberList.get(position);
        holder.tvNumberItem.setText(String.format("%d", numberItem.getValue()));
        if (numberItem.isTraversed()) {
            holder.tvNumberItem.setBackgroundColor(numberItem.isPrime() ? colorIdPrime : colorIdNonPrime);
        }
    }

    @Override
    public int getItemCount() {
        return numberList.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView tvNumberItem;

        CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNumberItem = itemView.findViewById(R.id.tv_number_item);
        }
    }
}
