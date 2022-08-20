package com.sdapps.f1racecalendar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

public class MainActivity extends AppCompatActivity implements JSONCall{

    Button fetchData;
    RequestQueue requestQueue;
    ArrayList<HashMap<String,String>> driverDetailList;
    TextView driverText, nextRaceTitle, day_counter,hour_counter;
    String driverFullName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        fetchData = (Button) findViewById(R.id.fetchDriver);
//        driverText = (TextView) findViewById(R.id.driverName);
        nextRaceTitle = (TextView) findViewById(R.id.nextRaceTitle);
        day_counter = (TextView) findViewById(R.id.day_counter);
        hour_counter = (TextView) findViewById(R.id.hour_counter);


        nextRaceTitle.setText("Austrian Grand Prix");
        day_counter.setText(" 01");
        hour_counter.setText("24");
        driverDetailList = new ArrayList<>();
        String url  = "https://ergast.com/api/f1/2022/drivers/leclerc.json";
        requestQueue = Volley.newRequestQueue(this);

       /* fetchData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Handler handler = new Handler();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        getJsonResponse(url);
                    }
                });

            }
        });*/
    }

    private void getJsonResponse(String url){
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
                    driverFullName = driverFirstName + " " + driverLastName;
                    onSuccess(driverFullName);
                    Log.d("TAG", "driverFullName" + driverFullName);

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
    public void onSuccess(String driverName) {
        driverText.setText(driverName);
    }

    @Override
    public void onFail(String msg) {
        Toast.makeText(this, "Message Failed", Toast.LENGTH_SHORT).show();

    }
}