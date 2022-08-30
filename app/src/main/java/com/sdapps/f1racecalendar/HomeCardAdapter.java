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
        holder.driverName.setText(driverDataList.get(position).getDriverName());
        holder.driverCode.setText(driverDataList.get(position).getDriverCode());
        holder.driverNumber.setText(driverDataList.get(position).getDriverNumber());

        if (driverDataList.get(position).getDriverCode().equals("ALB"))
            holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.days_red));
        else if (driverDataList.get(position).getDriverCode().equalsIgnoreCase("ALO"))
            holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.yamaha_blue));
        else if (driverDataList.get(position).getDriverCode().equalsIgnoreCase("BOT"))
            holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.purple_200));
        else if (driverDataList.get(position).getDriverCode().equalsIgnoreCase("GAS"))
            holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.teal_200));
        else if (driverDataList.get(position).getDriverCode().equalsIgnoreCase("HAM"))
            holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.purple_700));
        else if (driverDataList.get(position).getDriverCode().equalsIgnoreCase("HUL"))
            holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.card_view_home));
        else if (driverDataList.get(position).getDriverCode().equalsIgnoreCase("LAT"))
            holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.days_red));
        else if (driverDataList.get(position).getDriverCode().equalsIgnoreCase("LEC"))
            holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.purple_700));
        else if (driverDataList.get(position).getDriverCode().equalsIgnoreCase("MAG"))
            holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.yamaha_blue));
        else if (driverDataList.get(position).getDriverCode().equalsIgnoreCase("NOR"))
            holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.teal_200));
        else if (driverDataList.get(position).getDriverCode().equalsIgnoreCase("OCO"))
            holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.teal_700));
        else if (driverDataList.get(position).getDriverCode().equalsIgnoreCase("PER"))
            holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.card_view_home));
        else if (driverDataList.get(position).getDriverCode().equalsIgnoreCase("RIC"))
            holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.days_red));
        else if (driverDataList.get(position).getDriverCode().equalsIgnoreCase("SAI"))
            holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.yamaha_blue));
        else if (driverDataList.get(position).getDriverCode().equalsIgnoreCase("MSC"))
            holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.teal_200));
        else if (driverDataList.get(position).getDriverCode().equalsIgnoreCase("STR"))
            holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.card_view_home));
        else if (driverDataList.get(position).getDriverCode().equalsIgnoreCase("VET"))
            holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.purple_700));
        else if (driverDataList.get(position).getDriverCode().equalsIgnoreCase("VER"))
            holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.purple_200));
        else if (driverDataList.get(position).getDriverCode().equalsIgnoreCase("TSU"))
            holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.teal_200));
        else if (driverDataList.get(position).getDriverCode().equalsIgnoreCase("ZHO"))
            holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.days_red));


    }

    @Override
    public int getItemCount() {
        return 5;
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

