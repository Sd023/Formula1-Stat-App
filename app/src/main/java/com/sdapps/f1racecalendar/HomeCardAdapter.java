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

public class HomeCardAdapter extends RecyclerView.Adapter<HomeCardAdapter.ViewHolder> {

    private List<DriverdataBO> driverDataList;
    Context context;

    public HomeCardAdapter(List<DriverdataBO> driverDataList, Context context) {
        this.driverDataList = driverDataList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.driver_card_standings, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StringBuilder stringBuilder = new StringBuilder();
        holder.driverName.setText(stringBuilder.append(driverDataList.get(position).getDriverName()).append("\n").append(driverDataList.get(position).getDriverNumber()).toString());
        holder.driverNumber.setText(new StringBuilder().append(driverDataList.get(position).getConstructorName()));
        holder.points.setText(new StringBuilder().append("Points: ").append(driverDataList.get(position).getTotalPoints()));
        holder.standing.setText(driverDataList.get(position).getPosition());

        try {
            if (driverDataList.get(position).getDriverCode().equalsIgnoreCase(F1Constants.ALBON))
                holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.williams));
            else if (driverDataList.get(position).getDriverCode().equalsIgnoreCase(F1Constants.LATIFI))
                holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.williams));
            else if (driverDataList.get(position).getDriverCode().equalsIgnoreCase(F1Constants.ALONSO))
                holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.alpine));
            else if (driverDataList.get(position).getDriverCode().equalsIgnoreCase(F1Constants.OCON))
                holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.alpine));
            else if (driverDataList.get(position).getDriverCode().equalsIgnoreCase(F1Constants.BOTTAS))
                holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.alfa_romeo_racing));
            else if (driverDataList.get(position).getDriverCode().equalsIgnoreCase(F1Constants.ZHOU))
                holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.alfa_romeo_racing));
            else if (driverDataList.get(position).getDriverCode().equalsIgnoreCase(F1Constants.GASLY))
                holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.alphatauri));
            else if (driverDataList.get(position).getDriverCode().equalsIgnoreCase(F1Constants.TSUNODA))
                holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.alphatauri));
            else if (driverDataList.get(position).getDriverCode().equalsIgnoreCase(F1Constants.HAMILTON))
                holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.mercedes));
            else if (driverDataList.get(position).getDriverCode().equalsIgnoreCase(F1Constants.RUSSELL))
                holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.mercedes));
            else if (driverDataList.get(position).getDriverCode().equalsIgnoreCase(F1Constants.HULKENBERG))
                holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.aston_martin));
            else if (driverDataList.get(position).getDriverCode().equalsIgnoreCase(F1Constants.STROLL))
                holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.aston_martin));
            else if (driverDataList.get(position).getDriverCode().equalsIgnoreCase(F1Constants.VETTEL))
                holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.aston_martin));
            else if (driverDataList.get(position).getDriverCode().equalsIgnoreCase(F1Constants.LECLERC))
                holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.ferrari));
            else if (driverDataList.get(position).getDriverCode().equalsIgnoreCase(F1Constants.SAINZ))
                holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.ferrari));
            else if (driverDataList.get(position).getDriverCode().equalsIgnoreCase(F1Constants.KMAG)) {
                holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.haas));
                holder.driverName.setTextColor(context.getResources().getColor(R.color.black));
                holder.driverNumber.setTextColor(context.getResources().getColor(R.color.black));
                holder.points.setTextColor(context.getResources().getColor(R.color.black));
                holder.standing.setTextColor(context.getResources().getColor(R.color.black));
            } else if (driverDataList.get(position).getDriverCode().equalsIgnoreCase(F1Constants.SCHUMACHER)) {
                holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.haas));
                holder.driverName.setTextColor(context.getResources().getColor(R.color.black));
                holder.driverNumber.setTextColor(context.getResources().getColor(R.color.black));
                holder.points.setTextColor(context.getResources().getColor(R.color.black));
                holder.standing.setTextColor(context.getResources().getColor(R.color.black));
            } else if (driverDataList.get(position).getDriverCode().equalsIgnoreCase(F1Constants.NORRIS))
                holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.mclaren));
            else if (driverDataList.get(position).getDriverCode().equalsIgnoreCase(F1Constants.RICCIARDO))
                holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.mclaren));
            else if (driverDataList.get(position).getDriverCode().equalsIgnoreCase(F1Constants.PEREZ))
                holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.redbull_racing));
            else if (driverDataList.get(position).getDriverCode().equalsIgnoreCase(F1Constants.VERSTAPPEN))
                holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.redbull_racing));
            else
                holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.generic));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView driverName, driverNumber, points, standing;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.driverName = itemView.findViewById(R.id.driverName);
            this.driverNumber = itemView.findViewById(R.id.driverTeamNo);
            this.cardView = itemView.findViewById(R.id.cardView);
            this.points = itemView.findViewById(R.id.points);
            this.standing = itemView.findViewById(R.id.standing);

        }
    }

}

