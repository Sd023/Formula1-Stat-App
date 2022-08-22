package com.sdapps.f1racecalendar;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HomeCardAdapter extends RecyclerView.Adapter<HomeCardAdapter.ViewHolder> {

    private List<DriverdataBO> driverDataList ;
    Context context;

    public HomeCardAdapter(List<DriverdataBO> driverDataList, Context context) {
        this.driverDataList = driverDataList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view= layoutInflater.inflate(R.layout.driver_card_standings, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int index = holder.getAdapterPosition();
        holder.driverName.setText(driverDataList.get(index).getDriverName());
        holder.driverCode.setText(driverDataList.get(index).getDriverNumber());
        holder.driverNumber.setText(driverDataList.get(index).getDriverNumber());
        String driveCode = driverDataList.get(position).getDriverCode();

    }

    @Override
    public int getItemCount() {
        return driverDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView driverName, driverCode, driverNumber;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.driverName = itemView.findViewById(R.id.driverName);
            this.driverCode = itemView.findViewById(R.id.driverTeamNo);
            this.driverNumber = itemView.findViewById(R.id.standing);
            this.cardView = itemView.findViewById(R.id.cardView);

        }
    }
}

