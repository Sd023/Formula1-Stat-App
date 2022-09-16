package com.sdapps.f1racecalendar;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.sdapps.f1racecalendar.Adapters.ConstructorAdapter;
import com.sdapps.f1racecalendar.Adapters.HomeCardAdapter;
import com.sdapps.f1racecalendar.Listener.JSONCall;
import com.sdapps.f1racecalendar.Model.CircuitBO;
import com.sdapps.f1racecalendar.Model.ConstructorBO;
import com.sdapps.f1racecalendar.Model.DriverdataBO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements JSONCall, View.OnClickListener {

    RequestQueue requestQueue;
    TextView nextRaceTitle;
    TextView day_counter;
    TextView hour_counter;
    TextView constantDriverStandings;
    TextView constantConstructorStandings;
    Button driverView, constructorView;
    Context context;
    RecyclerView recyclerView, constructorRV;
    ProgressDialog progressDialog;
    private final String DRIVER_ACTIVITY = "DRIVER_FRAGMENT";
    private final String CONSTRUCTOR_ACTIVITY = "CONSTRUCTOR_FRAGMENT";
    Handler handler;

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
        initView();
        handler = new Handler();

    }

    private void initView() {
        driverView.setOnClickListener(this);
        constructorView.setOnClickListener(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        constantDriverStandings.setVisibility(View.GONE);
        constantConstructorStandings.setVisibility(View.GONE);
        driverView.setVisibility(View.GONE);
        constructorView.setVisibility(View.GONE);
        nextRaceTitle.setText("Austrian Grand Prix");
        day_counter.setText(" 01");
        hour_counter.setText("24");
        Handler d = new Handler();

        d.post(new Runnable() {

            @Override
            public void run() {
                progressDialog.setTitle("Loading..");
                progressDialog.setMessage("Fetching driver information..");
                progressDialog.show();
                getDriverResponse();
                getConstructorDetails();
            }
        });


        requestQueue = Volley.newRequestQueue(this);


    }


    private void getDriverResponse() {
        final String url = "https://ergast.com/api/f1/current/driverStandings.json";
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
                    JSONArray con = details.getJSONArray("Constructors");
                    for (int j = 0; j < con.length(); j++) {
                        JSONObject d = con.getJSONObject(j);
                        String conName = d.getString("name");
                        driverdataBO.setConstructorName(conName);
                    }
                    Log.d("TAG", "-----" + driverdataBO.getConstructorName());
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
    public void onClick(View view) {
        if (view.getId() == R.id.driverView) {
            switchToActivity(DRIVER_ACTIVITY);

        }
        if (view.getId() == R.id.constructorView) {
            switchToActivity(CONSTRUCTOR_ACTIVITY);

        }
    }

    private void switchToActivity(String activityCode) {
        if (activityCode.equalsIgnoreCase(DRIVER_ACTIVITY)) {
            startActivity(new Intent(MainActivity.this, DriverStandingsActivity.class));
        }
        if (activityCode.equalsIgnoreCase(CONSTRUCTOR_ACTIVITY)) {
            startActivity(new Intent(MainActivity.this, ConstructorStandingsActivity.class));
        }


    }

    private void getConstructorDetails() {
        final String url = "https://ergast.com/api/f1/2022/constructorStandings.json";
        List<ConstructorBO> constructorBOList = new ArrayList<ConstructorBO>();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject MRdata = response.getJSONObject("MRData");
                    JSONObject driverTable = MRdata.getJSONObject("StandingsTable");
                    JSONArray driver = driverTable.getJSONArray("StandingsLists");
                    JSONObject standingsObj = driver.getJSONObject(0);
                    JSONArray jsonArray = standingsObj.getJSONArray("ConstructorStandings");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        ConstructorBO constructorBO = new ConstructorBO();
                        JSONObject consDetail = jsonArray.getJSONObject(i);

                        String position = consDetail.getString("position");
                        String wins = consDetail.getString("wins");
                        String points = consDetail.getString("points");
                        JSONObject cons = consDetail.getJSONObject("Constructor");
                        String constructorId = cons.getString("constructorId");
                        String consName = cons.getString("name");
                        String consNation = cons.getString("nationality");


                        constructorBO.setConstructorName(consName);
                        constructorBO.setPoints(points);
                        constructorBO.setPosition(position);
                        constructorBO.setWins(wins);
                        constructorBO.setNationality(consNation);
                        constructorBO.setConId(constructorId);

                        constructorBOList.add(constructorBO);

                    }
                    Log.d("TAG", ":----> CONS ENDED <-----");
                    onConSuccess(constructorBOList);
                } catch (Exception e) {
                    onFail(e.toString());
                    e.printStackTrace();
                }

            }
        }, error -> onFail(error.toString()));
        requestQueue.add(jsonObjectRequest);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("TAG", ":----> CIRCUIT STARTED <------");
                getCurrentCircuitDetails();
            }
        }, 19000);
    }

    private void getCurrentCircuitDetails() {
        final String url = "https://ergast.com/api/f1/current/.json";
        List<CircuitBO> circuitBOList = new ArrayList<>();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject MRdata = response.getJSONObject("MRData");
                    JSONObject raceTable = MRdata.getJSONObject("RaceTable");
                    JSONArray jsonArray = raceTable.getJSONArray("Races");

                    for(int i = 0; i<jsonArray.length();i++){
                        CircuitBO circuitBO = new CircuitBO();
                        JSONObject circuitDetail = jsonArray.getJSONObject(i);
                        String raceName = circuitDetail.getString("raceName");
                        circuitBO.setRaceName(raceName);
                        /*JSONObject FP1sessions = circuitDetail.getJSONObject("FirstPractice");
                        JSONObject FP2sessions = circuitDetail.getJSONObject("SecondPractice");
                        JSONObject FP3sessions = circuitDetail.getJSONObject("ThirdPractice");
                        JSONObject quali = circuitDetail.getJSONObject("Qualifying");
                        Commented SESSION TIMINGS FOR NOW
                        String raceTime = circuitDetail.getString("date");
                        String raceDate = circuitDetail.getString("time");
                        String FP1date = FP1sessions.getString("date");
                        String FP1time = FP1sessions.getString("time");
                        String FP2date = FP2sessions.getString("date");
                        String FP2time = FP2sessions.getString("time");
                        String FP3date = FP3sessions.getString("date");
                        String FP3time = FP3sessions.getString("time");
                        String qualiDate = quali.getString("date");
                        String qualiTime = quali.getString("time");
                        */
                    }
                    Log.d("TAG", ":----> CIRCUIT ENDED <------");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, error -> onFail(error.toString()));
        requestQueue.add(jsonObjectRequest);


    }


    @Override
    public void onSuccess(List<DriverdataBO> driverDataList) {
        constantDriverStandings.setVisibility(View.VISIBLE);
        driverView.setVisibility(View.VISIBLE);
        Log.d("SUCCESS", "" + driverDataList.size());
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        HomeCardAdapter cardAdapter = new HomeCardAdapter(driverDataList, context);
        recyclerView.setAdapter(cardAdapter);
        progressDialog.dismiss();
    }

    @Override
    public void onConSuccess(List<ConstructorBO> constructorBOList) {
        constantConstructorStandings.setVisibility(View.VISIBLE);
        constructorView.setVisibility(View.VISIBLE);
        Log.d("CONSUCCESS", "" + constructorBOList.size());
        constructorRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ConstructorAdapter cardAdapter = new ConstructorAdapter(constructorBOList, context);
        constructorRV.setAdapter(cardAdapter);
    }

    @Override
    public void onFail(String msg) {
        Toast.makeText(this, "Message Failed", Toast.LENGTH_SHORT).show();
        Log.d("TAG", ":----> CIRCUIT STARTED <------");

        progressDialog.dismiss();

    }

    @Override
    public void onCirSuccess(List<CircuitBO> circuitBOS) {

    }
}