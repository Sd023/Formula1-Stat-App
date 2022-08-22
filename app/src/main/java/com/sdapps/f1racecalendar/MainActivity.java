package com.sdapps.f1racecalendar;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements JSONCall{

    RequestQueue requestQueue;
    TextView  nextRaceTitle, day_counter,hour_counter;
    Button fetchData;
    Context context;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        nextRaceTitle =findViewById(R.id.nextRaceTitle);
        day_counter =  findViewById(R.id.day_counter);
        hour_counter = findViewById(R.id.hour_counter);
        fetchData = findViewById(R.id.fetchData);
        recyclerView = findViewById(R.id.recyclerView);

        initView();

    }
    private void initView(){
        nextRaceTitle.setText("Austrian Grand Prix");
        day_counter.setText(" 01");
        hour_counter.setText("24");
        String url  = "https://ergast.com/api/f1/2022/drivers/bottas.json";
        Handler d = new Handler();

        fetchData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d.post(new Runnable() {
                    @Override
                    public void run() {
                        getJsonResponse(url);
                    }
                });
            }
        });

        requestQueue = Volley.newRequestQueue(this);


    }


    private void getJsonResponse(String url){

        DriverdataBO driverdataBO = new DriverdataBO();
        List<DriverdataBO> driverList = new ArrayList<DriverdataBO>();
        JsonObjectRequest driverDetailsRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    JSONObject MRdata = response.getJSONObject("MRData");
                    JSONObject driverTable = MRdata.getJSONObject("DriverTable");
                    JSONArray driver = driverTable.getJSONArray("Drivers");
                    JSONObject driverObj = driver.getJSONObject(0);
                    String driverFirstName = driverObj.getString("givenName");
                    String driverLastName = driverObj.getString("familyName");
                    String driverNumber = driverObj.getString("permanentNumber");
                    String driverCode = driverObj.getString("code");
                    String driverNationality = driverObj.getString("nationality");
                    String driverFullName = driverFirstName + " " + driverLastName;

                    driverdataBO.setDriverName(driverFullName);
                    driverdataBO.setDriverNumber(driverNumber);
                    driverdataBO.setDriverNationality(driverNationality);
                    driverdataBO.setDriverCode(driverCode);

                    driverList.add(driverdataBO);
                    Log.d("SUCCESS", " : " + driverFullName);
                    onSuccess(driverList);

                }catch (Exception ex){
                    ex.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(driverDetailsRequest);
    }

    @Override
    public void onSuccess(List<DriverdataBO> driverDataList) {
        Log.d("SUCCESS","" + driverDataList.size());
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,true));
        HomeCardAdapter cardAdapter = new HomeCardAdapter(driverDataList, context);
        recyclerView.setAdapter(cardAdapter);
    }

    @Override
    public void onFail(String msg) {
        Toast.makeText(this, "Message Failed", Toast.LENGTH_SHORT).show();

    }
}