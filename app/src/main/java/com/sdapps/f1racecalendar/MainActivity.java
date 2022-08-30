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

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.Path;

public class MainActivity extends AppCompatActivity implements JSONCall , View.OnClickListener {

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

        driverView.setOnClickListener(this);
        constructorView.setOnClickListener(this);
        String url = "https://ergast.com/api/f1/2022/drivers.json";
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
                JSONObject driverTable = MRdata.getJSONObject("DriverTable");
                JSONArray driver = driverTable.getJSONArray("Drivers");
                for (int nextDriver = 0; nextDriver < driver.length(); nextDriver++) {
                    DriverdataBO driverdataBO = new DriverdataBO();
                    JSONObject driverObj = driver.getJSONObject(nextDriver);
                    String driverFirstName = driverObj.getString("givenName");
                    String driverLastName = driverObj.getString("familyName");
                    String driverNumber = driverObj.getString("permanentNumber");
                    String driverCode = driverObj.getString("code");
                    String driverNationality = driverObj.getString("nationality");
                    String driverFullName = driverFirstName + " " + driverLastName;
                    driverdataBO.setDriverName(driverFullName);
                    driverdataBO.setDriverNumber(driverNumber);
                    driverdataBO.setDriverCode(driverCode);
                    driverdataBO.setDriverNationality(driverNationality);
                    driverList.add(driverdataBO);
                }
                //Log.d("SUCCESS", " : " + driverFullName);
                onSuccess(driverList);

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }, error -> onFail(error.toString()));

        driverDetailsRequest.setRetryPolicy(
                new DefaultRetryPolicy(5000,
                3,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
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
        Log.d("ERROR_CODE", "JSON_FETCH_FAILED :  " + " --- " + msg);
        Toast.makeText(this, "Fetching driver information failed!", Toast.LENGTH_SHORT).show();
        progressDialog.dismiss();


    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.driverView){
            Toast.makeText(MainActivity.this, "Button Driver", Toast.LENGTH_SHORT).show();
        }
        else if(view.getId() == R.id.constructorView){
            Toast.makeText(MainActivity.this, "Constructor!", Toast.LENGTH_SHORT).show();
        }

    }
}