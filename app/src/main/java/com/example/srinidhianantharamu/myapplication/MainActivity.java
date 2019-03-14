package com.example.srinidhianantharamu.myapplication;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String url = "https://diva-guest-experience.azurewebsites.net/visitors/detail/check/63115ff0-4628-11e9-a1c3-739a92953fe5";

    private RecyclerView mList;

    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private List<Guest> guestList;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mList = findViewById(R.id.my_recycler_view);

        guestList = new ArrayList<>();
        adapter = new GuestListAdapter(getApplicationContext(),guestList);

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dividerItemDecoration = new DividerItemDecoration(mList.getContext(), linearLayoutManager.getOrientation());

        mList.setHasFixedSize(true);
        mList.setLayoutManager(linearLayoutManager);
        mList.addItemDecoration(dividerItemDecoration);
        mList.setAdapter(adapter);

        getData();
    }

    private void getData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray result = jsonObject.getJSONArray(response);
                    for(int i=0; i<result.length(); i++) {
                        String Surl= result.getJSONObject(i).getString("guestNames");
                        JSONArray obj = new JSONArray(Surl);
                        for(int j=0; j<obj.length(); j++) {
                            JSONObject json_data = obj.getJSONObject(i);
                            System.out.println("--sdsd"+json_data);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                adapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
                progressDialog.dismiss();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}