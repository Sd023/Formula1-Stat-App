package com.sdapps.f1racecalendar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class MainActivity extends AppCompatActivity implements VolleyCallback {

    Button fetchData;
    RequestQueue requestQueue;
    ArrayList<HashMap<String,String>> driverDetailList;
    VolleyCallback callback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        fetchData = (Button) findViewById(R.id.fetchDriver);
        driverDetailList = new ArrayList<>();
        String url  = "https://ergast.com/api/f1/2022/drivers/bottas.json";
        requestQueue = Volley.newRequestQueue(this);

        fetchData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getJsonResponse(url, new VolleyCallback() {
                    @Override
                    public void responseOK(HashMap<String, String> driverNameList) {
                        Log.d("STATUS OK", ": "+ driverNameList.get("driverFirstName"));
                    }

                    @Override
                    public void responseError(String error) {
                       Log.d("STATUS", "FAILED");
                    }
                });
            }
        });
    }

    private void getJsonResponse(String url, final VolleyCallback callback){


        JsonObjectRequest driverDetailsRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    JSONObject MRdata = response.getJSONObject("MRData");
                    for(int mrData = 0; mrData< MRdata.length(); mrData++){
                       JSONObject driverTable = MRdata.getJSONObject("DriverTable");
                        for(int i=0; i<driverTable.length(); i++){
                            JSONArray driver = driverTable.getJSONArray("Drivers");

                            for(int j=0; j<driver.length(); j++){
                                JSONObject driverObj = driver.getJSONObject(j);
                                String driverName = driverObj.getString("givenName");
                                String driverLastName = driverObj.getString("familyName");
                                List<String> list = new ArrayList<>();
                                list.add(driverName);
                                Log.d("driverNames", " : "+ driverName);

                            }
                        }
                    }

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
    public void responseOK(HashMap<String, String> hashMapDriver) {

    }

    @Override
    public void responseError(String error) {

    }
}