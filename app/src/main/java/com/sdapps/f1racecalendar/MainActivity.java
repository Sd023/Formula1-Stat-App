package com.sdapps.f1racecalendar;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.sdapps.f1racecalendar.fragments.ConstructorStandings;
import com.sdapps.f1racecalendar.fragments.DriverStandings;

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
    private final String DRIVER_FRAGMENT = "DRIVER_FRAGMENT";
    private final String CONSTRUCTOR_FRAGMENT = "CONSTRUCTOR_FRAGMENT";

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
        String url = "https://ergast.com/api/f1/current/driverStandings.json";
        Handler d = new Handler();

        d.post(new Runnable() {

            @Override
            public void run() {
                progressDialog.setTitle("Loading..");
                progressDialog.setMessage("Fetching driver information..");
                progressDialog.show();
                getDriverResponse(url);
                getConstructorDetails();
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
                    JSONArray con = details.getJSONArray("Constructors");
                    for(int j=0; j<con.length(); j++){
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
            Toast.makeText(MainActivity.this, "DriverView!", Toast.LENGTH_SHORT).show();
            //switchToFragment(DRIVER_FRAGMENT);

        }
        if (view.getId() == R.id.constructorView) {
            Toast.makeText(MainActivity.this, "ConstructorView!", Toast.LENGTH_SHORT).show();
            //switchToFragment(CONSTRUCTOR_FRAGMENT);

        }
    }

    private void switchToFragment(String fragmentCode){
        try{
            switch (fragmentCode){
                case DRIVER_FRAGMENT:
                case CONSTRUCTOR_FRAGMENT:

            }
        }catch (Exception e){
            e.printStackTrace();
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
                    onConSuccess(constructorBOList);
                } catch (Exception e) {
                    onFail(e.toString());
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
        progressDialog.dismiss();

    }

    public static class ConstructorAdapter extends RecyclerView.Adapter<ConstructorAdapter.ViewHolder> {

        private List<ConstructorBO> consList;
        Context context;

        public ConstructorAdapter(List<ConstructorBO> consList, Context context) {
            this.consList = consList;
            this.context = context;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.driver_card_standings, parent, false);
            context = parent.getContext();
            return new ConstructorAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            StringBuilder stringBuilder = new StringBuilder();
            holder.constructorName.setText(stringBuilder.append(consList.get(position).getConstructorName()));
            holder.points.setText(new StringBuilder().append("Points: ").append(consList.get(position).getPoints()));
            holder.standing.setText(consList.get(position).getPosition());

            try {
                if (consList.get(position).getConId().equalsIgnoreCase(F1Constants.ALFA_ROMEO))
                    holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.alfa_romeo_racing));
                else if (consList.get(position).getConId().equalsIgnoreCase(F1Constants.ALPHATAURI))
                    holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.alphatauri));
                else if (consList.get(position).getConId().equalsIgnoreCase(F1Constants.ALPINE))
                    holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.alpine));
                else if (consList.get(position).getConId().equalsIgnoreCase(F1Constants.ASTON_MARTIN))
                    holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.aston_martin));
                else if (consList.get(position).getConId().equalsIgnoreCase(F1Constants.FERRARI))
                    holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.ferrari));
                else if (consList.get(position).getConId().equalsIgnoreCase(F1Constants.HAAS))
                    holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.haas));
                else if (consList.get(position).getConId().equalsIgnoreCase(F1Constants.MCLAREN))
                    holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.mclaren));
                else if (consList.get(position).getConId().equalsIgnoreCase(F1Constants.MERCEDES))
                    holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.mercedes));
                else if (consList.get(position).getConId().equalsIgnoreCase(F1Constants.RED_BULL))
                    holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.redbull_racing));
                else if (consList.get(position).getConId().equalsIgnoreCase(F1Constants.WILLIAMS))
                    holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.williams));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public int getItemCount() {
            return 5;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView constructorName, points, standing;
            CardView cardView;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                this.constructorName = itemView.findViewById(R.id.driverName);
                this.cardView = itemView.findViewById(R.id.cardView);
                this.points = itemView.findViewById(R.id.points);
                this.standing = itemView.findViewById(R.id.standing);

            }

        }
    }
}