package com.example.eventure;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eventure.view.InterestItem;

import java.util.List;

public class InterestAdapter extends RecyclerView.Adapter<InterestAdapter.InterestViewHolder> {
    private List<InterestItem> interestList;

    // Constructor
    public InterestAdapter(List<InterestItem> interestList) {
        this.interestList = interestList;
    }

    public class InterestViewHolder extends RecyclerView.ViewHolder {
        TextView txtInterest;
        ImageView imageView;

        public InterestViewHolder(View itemView) {
            super(itemView);
            txtInterest = itemView.findViewById(R.id.textInterest);
            imageView = itemView.findViewById(R.id.imageInterest);
        }
    }

    @NonNull
    @Override
    public InterestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item1, parent, false);
        return new InterestViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull InterestViewHolder holder, int position) {
        InterestItem item = interestList.get(position);
        holder.txtInterest.setText(item.getInterest());
        holder.imageView.setImageResource(item.getImageRes());
    }

    @Override
    public int getItemCount() {
        return interestList.size();
}
}