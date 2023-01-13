package com.sdapps.f1racecalendar


import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.sdapps.f1racecalendar.Adapters.ConstructorAdapter
import com.sdapps.f1racecalendar.Adapters.HomeCardAdapter
import com.sdapps.f1racecalendar.Listener.DataListeners
import com.sdapps.f1racecalendar.Model.CircuitBO
import com.sdapps.f1racecalendar.Model.ConstructorBO
import com.sdapps.f1racecalendar.Model.DriverdataBO
import org.json.JSONArray
import org.json.JSONObject

/*develop branch commit*/
class MainActivity() : AppCompatActivity(),
    DataListeners, View.OnClickListener {
    private lateinit var requestQueue: RequestQueue
    private lateinit var nextRaceTitle: TextView
    private lateinit var dayCounter: TextView
    private lateinit var hourCounter: TextView
    private lateinit var constantDriverStandings: TextView
    private lateinit var constantConstructorStandings: TextView
    private lateinit var driverView: Button
    private lateinit var constructorView: Button
    private lateinit var context: Context
    private lateinit var recyclerView: RecyclerView
    private lateinit var constructorRV: RecyclerView
    private lateinit var progressDialog: ProgressDialog



    private val DRIVER_ACTIVITY = "DRIVER_FRAGMENT"
    private val CONSTRUCTOR_ACTIVITY = "CONSTRUCTOR_FRAGMENT"
    private val homeHelper: HomeHelper? = HomeHelper()
    var handler: Handler? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        context = this.applicationContext
        nextRaceTitle = findViewById(R.id.nextRaceTitle)
        dayCounter = findViewById(R.id.day_counter)
        hourCounter = findViewById(R.id.hour_counter)
        recyclerView = findViewById(R.id.recyclerView)
        constructorRV = findViewById(R.id.constructorRecyclerView)
        constantDriverStandings = findViewById(R.id.constantDriverStandings)
        constantConstructorStandings = findViewById(R.id.constantConstructorStandings)
        driverView = findViewById(R.id.driverView)
        homeHelper!!.getImage()
        constructorView = findViewById(R.id.constructorView)
        initView()
        handler = Handler()

    }

    private fun initView() {
        driverView.setOnClickListener(this)
        constructorView.setOnClickListener(this)
        progressDialog = ProgressDialog(this)
        progressDialog.setCancelable(false)
        constantDriverStandings.visibility = View.GONE
        constantConstructorStandings.visibility = View.GONE
        driverView.visibility = View.GONE
        constructorView.visibility = View.GONE
        nextRaceTitle.text = "Austrian Grand Prix"
        dayCounter.text = " 01"
        hourCounter.text = "24"
        val d = Handler()
        d.post {
            progressDialog.setTitle("Loading..")
            progressDialog.setMessage("Fetching driver information..")
            progressDialog.show()
            driverResponse
            constructorDetails
        }
        requestQueue = Volley.newRequestQueue(this)
    }

    //Log.d("SUCCESS", " : " + driverFullName);
    private val driverResponse: Unit
        private get() {
            val url = "https://ergast.com/api/f1/current/driverStandings.json"
            val driverList: MutableList<DriverdataBO> = ArrayList()
            val driverDetailsRequest =
                JsonObjectRequest(Request.Method.GET, url, null, { response: JSONObject ->
                    try {
                        val MRdata: JSONObject = response.getJSONObject("MRData")
                        val driverTable: JSONObject = MRdata.getJSONObject("StandingsTable")
                        val driver: JSONArray = driverTable.getJSONArray("StandingsLists")
                        val standingsObj: JSONObject = driver.getJSONObject(0)
                        val jsonArray: JSONArray = standingsObj.getJSONArray("DriverStandings")
                        for (i in 0 until jsonArray.length()) {
                            val driverdataBO: DriverdataBO = DriverdataBO()
                            val details: JSONObject = jsonArray.getJSONObject(i)
                            val tablePosition: String = details.getString("position")
                            val points: String = details.getString("points")
                            val totalWins: String = details.getString("wins")
                            val con: JSONArray = details.getJSONArray("Constructors")
                            for (j in 0 until con.length()) {
                                val d: JSONObject = con.getJSONObject(j)
                                val conName: String = d.getString("name")
                                driverdataBO.constructorName = conName
                            }
                            Log.d("TAG", "-----" + driverdataBO.constructorName)
                            val driverDetail: JSONObject = details.getJSONObject("Driver")
                            val driverID: String = driverDetail.getString("driverId")
                            val driverGivenName: String = driverDetail.getString("givenName")
                            val driverFamilyName: String = driverDetail.getString("familyName")
                            val dateOfBirth: String = driverDetail.getString("dateOfBirth")
                            val nationality: String = driverDetail.getString("nationality")
                            val permanentNumber: String = driverDetail.getString("permanentNumber")
                            val code: String = driverDetail.getString("code")
                            val driverFullName: String = driverGivenName + " " + driverFamilyName
                            driverdataBO.position = tablePosition
                            driverdataBO.totalPoints = points
                            driverdataBO.wins = totalWins
                            driverdataBO.driverId = driverID
                            driverdataBO.driverName = driverFullName
                            driverdataBO.dob = dateOfBirth
                            driverdataBO.driverNationality = nationality
                            driverdataBO.driverNumber = permanentNumber
                            driverdataBO.driverCode = code
                            driverList.add(driverdataBO)
                            Log.d("POINTS", " : " + " " + driverID + " " + points)
                        }
                        //Log.d("SUCCESS", " : " + driverFullName);
                        onSuccess(driverList)

                    } catch (ex: Exception) {
                        ex.printStackTrace()
                    }
                }, { error: VolleyError -> onFail(error.toString()) })
            requestQueue.add(driverDetailsRequest)
        }

    override fun onClick(view: View) {
        if (view.id == R.id.driverView) {
            switchToActivity(DRIVER_ACTIVITY)
        }
        if (view.id == R.id.constructorView) {
            switchToActivity(CONSTRUCTOR_ACTIVITY)
        }
    }

    private fun switchToActivity(activityCode: String) {
        if (activityCode.equals(DRIVER_ACTIVITY, ignoreCase = true)) {
            startActivity(Intent(this@MainActivity, DriverStandingsActivity::class.java))
        }
        if (activityCode.equals(CONSTRUCTOR_ACTIVITY, ignoreCase = true)) {
            startActivity(Intent(this@MainActivity, ConstructorStandingsActivity::class.java))
        }
    }

    private val constructorDetails: Unit
        get() {
            val url = "https://ergast.com/api/f1/2022/constructorStandings.json"
            val constructorBOList: MutableList<ConstructorBO> = ArrayList()
            val jsonObjectRequest =
                JsonObjectRequest(Request.Method.GET, url, null, Response.Listener { response ->
                    try {
                        val MRdata = response.getJSONObject("MRData")
                        val driverTable = MRdata.getJSONObject("StandingsTable")
                        val driver = driverTable.getJSONArray("StandingsLists")
                        val standingsObj = driver.getJSONObject(0)
                        val jsonArray = standingsObj.getJSONArray("ConstructorStandings")
                        for (i in 0 until jsonArray.length()) {
                            val constructorBO = ConstructorBO()
                            val consDetail = jsonArray.getJSONObject(i)
                            val position = consDetail.getString("position")
                            val wins = consDetail.getString("wins")
                            val points = consDetail.getString("points")
                            val cons = consDetail.getJSONObject("Constructor")
                            val constructorId = cons.getString("constructorId")
                            val consName = cons.getString("name")
                            val consNation = cons.getString("nationality")
                            constructorBO.constructorName = consName
                            constructorBO.points = points
                            constructorBO.position = position
                            constructorBO.wins = wins
                            constructorBO.nationality = consNation
                            constructorBO.conId = constructorId
                            constructorBOList.add(constructorBO)
                        }
                        Log.d("TAG", ":----> CONS ENDED <-----")
                        onConSuccessd(constructorBOList)
                    } catch (e: Exception) {
                        onFail(e.toString())
                        e.printStackTrace()
                    }
                }, { error: VolleyError -> onFail(error.toString()) })
            requestQueue!!.add(jsonObjectRequest)
            handler!!.postDelayed(object : Runnable {
                override fun run() {
                    Log.d("TAG", ":----> CIRCUIT STARTED <------")
                    currentCircuitDetails
                }
            }, 19000)
        }

    private val currentCircuitDetails: Unit
        private get() {
            val url = "https://ergast.com/api/f1/current.json"
            val circuitBOList: List<CircuitBO> = ArrayList()
            val jsonObjectRequest = JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                object : Response.Listener<JSONObject> {
                    override fun onResponse(response: JSONObject) {
                        try {
                            val MRdata = response.getJSONObject("MRData")
                            val raceTable = MRdata.getJSONObject("RaceTable")
                            val jsonArray = raceTable.getJSONArray("Races")
                            for (i in 0 until jsonArray.length()) {
                                val circuitBO = CircuitBO()
                                val circuitDetail = jsonArray.getJSONObject(i)
                                val raceName = circuitDetail.getString("raceName")
                                circuitBO.raceName = raceName
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
                            Log.d("TAG", ":----> CIRCUIT ENDED <------")
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                },
                { error: VolleyError -> onFail(error.toString()) })
            requestQueue.add(jsonObjectRequest)
        }

    override fun onSuccess(driverDataList: List<DriverdataBO?>?) {
        context = this.applicationContext
        constantDriverStandings.visibility = View.VISIBLE
        driverView.visibility = View.VISIBLE
        Log.d("SUCCESS", "" + driverDataList?.size)
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val cardAdapter = HomeCardAdapter(driverDataList, context)
        recyclerView.adapter = cardAdapter
        progressDialog.dismiss()
    }

    override fun onConSuccess(constructorBOList: List<ConstructorBO?>?) {
        TODO("Not yet implemented")
    }


    override fun onFail(msg: String?) {
        Toast.makeText(this, "Message Failed", Toast.LENGTH_SHORT).show()
        Log.d("TAG", ":----> CIRCUIT STARTED <------")
        progressDialog.dismiss()
    }

     fun onConSuccessd(constructorBOList: List<ConstructorBO>) {
         context = this.applicationContext
        constantConstructorStandings.visibility = View.VISIBLE
        constructorView.visibility = View.VISIBLE
        Log.d("CONSUCCESS", "" + constructorBOList.size)
        constructorRV.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val cardAdapter = ConstructorAdapter(constructorBOList, context)
        constructorRV.adapter = cardAdapter
    }

    override fun onCirSuccess(circuitBOS: List<CircuitBO?>?) {
        TODO("Not yet implemented")
    }

}