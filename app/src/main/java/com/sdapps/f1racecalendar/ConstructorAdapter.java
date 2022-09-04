package com.sdapps.f1racecalendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ConstructorAdapter extends RecyclerView.Adapter<ConstructorAdapter.ViewHolder> {

    private List<ConstructorBO> consList;
    Context context;

    public ConstructorAdapter(List<ConstructorBO> consList, Context context) {
        this.consList = consList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.driver_card_standings, parent, false);
        context = parent.getContext();
        return new ConstructorAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StringBuilder stringBuilder = new StringBuilder();
        holder.constructorName.setText(stringBuilder.append(consList.get(position).getConstructorName()));
        holder.points.setText(new StringBuilder().append("Points: ").append(consList.get(position).getPoints()));
        holder.standing.setText(consList.get(position).getPosition());

        try {
            if (consList.get(position).getConId().equalsIgnoreCase(F1Constants.ALFA_ROMEO))
                holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.alfa_romeo_racing));
            else if (consList.get(position).getConId().equalsIgnoreCase(F1Constants.ALPHATAURI))
                holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.alphatauri));
            else if (consList.get(position).getConId().equalsIgnoreCase(F1Constants.ALPINE))
                holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.alpine));
            else if (consList.get(position).getConId().equalsIgnoreCase(F1Constants.ASTON_MARTIN))
                holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.aston_martin));
            else if (consList.get(position).getConId().equalsIgnoreCase(F1Constants.FERRARI))
                holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.ferrari));
            else if (consList.get(position).getConId().equalsIgnoreCase(F1Constants.HAAS))
                holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.haas));
            else if (consList.get(position).getConId().equalsIgnoreCase(F1Constants.MCLAREN))
                holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.mclaren));
            else if (consList.get(position).getConId().equalsIgnoreCase(F1Constants.MERCEDES))
                holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.mercedes));
            else if (consList.get(position).getConId().equalsIgnoreCase(F1Constants.RED_BULL))
                holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.redbull_racing));
            else if (consList.get(position).getConId().equalsIgnoreCase(F1Constants.WILLIAMS))
                holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.williams));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView constructorName, points, standing;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.constructorName = itemView.findViewById(R.id.driverName);
            this.cardView = itemView.findViewById(R.id.cardView);
            this.points = itemView.findViewById(R.id.points);
            this.standing = itemView.findViewById(R.id.standing);

        }

    }
}