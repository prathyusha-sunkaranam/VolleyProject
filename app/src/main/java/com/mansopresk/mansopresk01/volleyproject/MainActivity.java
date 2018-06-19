package com.mansopresk.mansopresk01.volleyproject;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static String url = "https://jsonplaceholder.typicode.com/posts";
    private RecyclerView recyclerview;
    private Adapter volleyadapter;
    private Activity activity;
    ProgressBar progress;
    LinearLayoutManager manager;
    private String TAG = MainActivity.class.getSimpleName();
    ArrayList<Model> volleydata=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //progress = (ProgressBar) findViewById(R.id.donor_progress);
       // progress.getIndeterminateDrawable().setColorFilter(0xFFFF0000, android.graphics.PorterDuff.Mode.MULTIPLY);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        listapi();

    }
    private void listapi() {
        //progress.setVisibility(View.VISIBLE);
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        final StringRequest getRequest = new StringRequest(Request.Method.GET, url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONArray contacts =new JSONArray(response);

                            for (int i = 0; i < contacts.length(); i++) {
                                JSONObject c = contacts.getJSONObject(i);
                                String userid=c.getString("userId");
                                String id = c.getString("id");
                                String title = c.getString("title");
                                String body = c.getString("body");

//                        validation(name,pass);
                               volleydata.add(new Model(userid,id,title,body));
                               volleyadapter = new Adapter(MainActivity.this,volleydata);
                               recyclerview.setAdapter(volleyadapter);
                              //  manager.setOrientation(LinearLayoutManager.VERTICAL);
                               recyclerview.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                            }
                        } catch (final JSONException e) {
                            Log.e(TAG, "Json parsing error: " + e.getMessage());
                            e.printStackTrace();
                        }

                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Toast.makeText(getApplicationContext(), "No Network Connection", Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() {
                Toast.makeText(activity, "Successful", Toast.LENGTH_SHORT).show();
                return null;
            }
        };
        queue.add(getRequest);
        recyclerview.setAdapter(volleyadapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }
}
