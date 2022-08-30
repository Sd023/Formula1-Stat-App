package com.sdapps.f1racecalendar;

import android.app.ProgressDialog;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements JSONCall, View.OnClickListener {

    RequestQueue requestQueue;
    TextView nextRaceTitle, day_counter, hour_counter, constantDriverStandings, constantConstructorStandings;
    Button driverView, constructorView;
    Context context;
    RecyclerView recyclerView, constructorRV;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        nextRaceTitle = findViewById(R.id.nextRaceTitle);
        day_counter = findViewById(R.id.day_counter);
        hour_counter = findViewById(R.id.hour_counter);
        recyclerView = findViewById(R.id.recyclerView);
        constructorRV = findViewById(R.id.constructorRecyclerView);

        constantDriverStandings = findViewById(R.id.constantDriverStandings);
        constantConstructorStandings = findViewById(R.id.constantConstructorStandings);
        driverView = findViewById(R.id.driverView);
        constructorView = findViewById(R.id.constructorView);
        driverView.setOnClickListener(this);
        constructorView.setOnClickListener(this);
        progressDialog = new ProgressDialog(this);

        initView();

    }

    private void initView() {
        nextRaceTitle.setText("Austrian Grand Prix");
        day_counter.setText(" 01");
        hour_counter.setText("24");
        constantDriverStandings.setVisibility(View.VISIBLE);
        constantConstructorStandings.setVisibility(View.VISIBLE);

        driverView.setVisibility(View.VISIBLE);
        constructorView.setVisibility(View.VISIBLE);
        String url = "https://ergast.com/api/f1/current/driverStandings.json";
        Handler d = new Handler();

        d.post(new Runnable() {

            @Override
            public void run() {
                progressDialog.setTitle("Loading..");
                progressDialog.setMessage("Fetching driver information..");
                progressDialog.show();
                getDriverResponse(url);
            }
        });


        requestQueue = Volley.newRequestQueue(this);


    }


    private void getDriverResponse(String url) {
        List<DriverdataBO> driverList = new ArrayList<DriverdataBO>();
        JsonObjectRequest driverDetailsRequest = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            try {
                JSONObject MRdata = response.getJSONObject("MRData");
                JSONObject driverTable = MRdata.getJSONObject("StandingsTable");
                JSONArray driver = driverTable.getJSONArray("StandingsLists");
                JSONObject standingsObj = driver.getJSONObject(0);
                JSONArray jsonArray = standingsObj.getJSONArray("DriverStandings");

                for (int i = 0; i < jsonArray.length(); i++) {
                    DriverdataBO driverdataBO = new DriverdataBO();
                    JSONObject details = jsonArray.getJSONObject(i);
                    String tablePosition = details.getString("position");
                    String points = details.getString("points");
                    String totalWins = details.getString("wins");
                    JSONObject driverDetail = details.getJSONObject("Driver");
                    String driverID = driverDetail.getString("driverId");
                    String driverGivenName = driverDetail.getString("givenName");
                    String driverFamilyName = driverDetail.getString("familyName");
                    String dateOfBirth = driverDetail.getString("dateOfBirth");
                    String nationality = driverDetail.getString("nationality");
                    String permanentNumber = driverDetail.getString("permanentNumber");
                    String code = driverDetail.getString("code");

                    String driverFullName = driverGivenName + " " + driverFamilyName;
                    driverdataBO.setPosition(tablePosition);
                    driverdataBO.setTotalPoints(points);
                    driverdataBO.setWins(totalWins);
                    driverdataBO.setDriverId(driverID);
                    driverdataBO.setDriverName(driverFullName);
                    driverdataBO.setDOB(dateOfBirth);
                    driverdataBO.setDriverNationality(nationality);
                    driverdataBO.setDriverNumber(permanentNumber);
                    driverdataBO.setDriverCode(code);

                    driverList.add(driverdataBO);
                    Log.d("POINTS", " : " + " " + driverID + " " + points);

                }

                //Log.d("SUCCESS", " : " + driverFullName);
                onSuccess(driverList);

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }, error -> onFail(error.toString()));

        requestQueue.add(driverDetailsRequest);
    }

    @Override
    public void onSuccess(List<DriverdataBO> driverDataList) {
        Log.d("SUCCESS", "" + driverDataList.size());
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        HomeCardAdapter cardAdapter = new HomeCardAdapter(driverDataList, context);
        recyclerView.setAdapter(cardAdapter);
        progressDialog.dismiss();

    }

    @Override
    public void onFail(String msg) {
        Toast.makeText(this, "Message Failed", Toast.LENGTH_SHORT).show();
        progressDialog.dismiss();

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.driverView) {
            Toast.makeText(MainActivity.this, "DriverView!", Toast.LENGTH_SHORT).show();
        }
        if (view.getId() == R.id.constructorView) {
            Toast.makeText(MainActivity.this, "ConstructorView!", Toast.LENGTH_SHORT).show();

        }
    }
}