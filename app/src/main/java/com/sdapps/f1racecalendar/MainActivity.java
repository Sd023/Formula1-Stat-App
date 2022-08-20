package com.sdapps.f1racecalendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

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
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.sdapps.f1racecalendar.homeFragment.Moto2HomeFragment;
import com.sdapps.f1racecalendar.homeFragment.Moto3HomeFragment;
import com.sdapps.f1racecalendar.homeFragment.MotogpHomeFragment;

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

    TabLayout tabLayout;
    ViewPager viewPager;
    PageAdapter pageAdapter;
    RequestQueue requestQueue;
    ArrayList<HashMap<String,String>> driverDetailList;
    TextView driverText, nextRaceTitle, day_counter,hour_counter;
    String driverFullName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        nextRaceTitle =findViewById(R.id.nextRaceTitle);
        day_counter =  findViewById(R.id.day_counter);
        hour_counter = findViewById(R.id.hour_counter);
        tabLayout = findViewById(R.id.homeTabLayout);
        viewPager = findViewById(R.id.homeViewPager);

        initView();

        tabLayout.addTab(tabLayout.newTab().setText("MotoGP"));
        tabLayout.addTab(tabLayout.newTab().setText("Moto2"));
        tabLayout.addTab(tabLayout.newTab().setText("Moto3"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        pageAdapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        callRecyclerView();

    }

    private void callRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,true));
        HomeCardAdapter cardAdapter = new HomeCardAdapter();
        recyclerView.setAdapter(cardAdapter);
    }

    private void initView(){
        nextRaceTitle.setText("Austrian Grand Prix");
        day_counter.setText(" 01");
        hour_counter.setText("24");
        driverDetailList = new ArrayList<>();
        String url  = "https://ergast.com/api/f1/2022/drivers/leclerc.json";
        requestQueue = Volley.newRequestQueue(this);
    }



    class PageAdapter extends FragmentPagerAdapter{

        private int tabsCount;

        public PageAdapter(FragmentManager fm, int tabsCount) {
            super(fm);
            this.tabsCount = tabsCount;
        }


        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0 : return new MotogpHomeFragment();
                case 1 : return new Moto2HomeFragment();
                case 2 : return new Moto3HomeFragment();
                default:
                   return null;
            }
        }

        @Override
        public int getCount() {
            return tabsCount;
        }
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