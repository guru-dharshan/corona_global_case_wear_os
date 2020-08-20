package com.example.wearosdemo;

import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.common.api.Response;
import org.json.JSONObject;
import com.android.volley.Request;
import com.android.volley.RequestQueue;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;


public class MainActivity extends WearableActivity {

    TextView todaycase,totalcase,todaydeath,totaldeath,todayrecovery,totalrecovery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todaycase=findViewById(R.id.todaycase);
        totalcase=findViewById(R.id.totalcase);
        todaydeath=findViewById(R.id.todaydeath);
        totaldeath=findViewById(R.id.totaldeath);
        todayrecovery=findViewById(R.id.todayrecovery);
        totalrecovery=findViewById(R.id.totalrecovery);

        String url  = "https://corona.lmao.ninja/v2/all/";
        StringRequest request = new StringRequest(Request.Method.GET, url, new com.android.volley.Response.Listener<String>() {
                @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response.toString());



                    todaycase.setText(jsonObject.getString("todayCases"));
                    totalcase.setText(jsonObject.getString("cases"));
                    todaydeath.setText(jsonObject.getString("todayDeaths"));
                    totaldeath.setText(jsonObject.getString("deaths"));
                    todayrecovery.setText(jsonObject.getString("todayRecovered"));
                    totalrecovery.setText(jsonObject.getString("recovered"));


                } catch (JSONException e) {
                    e.printStackTrace();

                }


            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);


        // Enables Always-on
        setAmbientEnabled();
    }

}
