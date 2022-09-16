package com.sdapps.f1racecalendar;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.sdapps.f1racecalendar.Model.DriverdataBO;

import java.util.ArrayList;

public class DriverStandingsActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_standings);

        ArrayList<DriverdataBO> list = new ArrayList<>();
        listView = findViewById(R.id.listView);

        list.add(new DriverdataBO("MAX VERSTAPPEN", "200"));
        list.add(new DriverdataBO("MAX VERSTAPPEN", "200"));
        list.add(new DriverdataBO("MAX VERSTAPPEN", "200"));
        list.add(new DriverdataBO("MAX VERSTAPPEN", "200"));
        list.add(new DriverdataBO("MAX VERSTAPPEN", "200"));
        list.add(new DriverdataBO("MAX VERSTAPPEN", "200"));
        list.add(new DriverdataBO("MAX VERSTAPPEN", "200"));
        list.add(new DriverdataBO("MAX VERSTAPPEN", "200"));
        list.add(new DriverdataBO("MAX VERSTAPPEN", "200"));
        list.add(new DriverdataBO("MAX VERSTAPPEN", "200"));
        list.add(new DriverdataBO("MAX VERSTAPPEN", "200"));
        list.add(new DriverdataBO("MAX VERSTAPPEN", "200"));
        list.add(new DriverdataBO("MAX VERSTAPPEN", "200"));
        list.add(new DriverdataBO("MAX VERSTAPPEN", "200"));
        list.add(new DriverdataBO("MAX VERSTAPPEN", "200"));
        list.add(new DriverdataBO("MAX VERSTAPPEN", "200"));
        list.add(new DriverdataBO("MAX VERSTAPPEN", "200"));
        list.add(new DriverdataBO("MAX VERSTAPPEN", "200"));
        list.add(new DriverdataBO("MAX VERSTAPPEN", "200"));
        list.add(new DriverdataBO("MAX VERSTAPPEN", "200"));
        list.add(new DriverdataBO("MAX VERSTAPPEN", "200"));

        DriverListAdapter driverListAdapter = new DriverListAdapter(this, list);
        listView.setAdapter(driverListAdapter);
    }
}

  class DriverListAdapter extends ArrayAdapter<DriverdataBO> {


    public DriverListAdapter(@NonNull Context context, ArrayList<DriverdataBO> list) {
        super(context, R.layout.driver__list,list);
    }

      @NonNull
      @Override
      public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
          // convertView which is recyclable view
          View currentItemView = convertView;

          // of the recyclable view is null then inflate the custom layout for the same
          if (currentItemView == null) {
              currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.driver__list, parent, false);
          }

        /*  TextView name = currentItemView.findViewById(R.id.driverName);
          TextView point = currentItemView.findViewById(R.id.driverPoints);

          name.setText(getItem(position).getDriverName());
          po.setText(getItem(position).getDriverPoints());*/

          return currentItemView;

      }
  }