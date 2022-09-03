package com.example.idea_returns;



import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarInputStream;

public class Search extends AppCompatActivity {
    private ArrayList<user> userList;
    private RecyclerView recyclerView;
    private recyclerAdapter.RecyclerViewClickListener listener;
    EditText search;
    ImageView b1;
    //TextView textView;
    String url="https://mysummerproject.000webhostapp.com/search.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        recyclerView= findViewById(R.id.recyclerView);
        userList=new ArrayList<>();

        search=(EditText) findViewById(R.id.Searchtext);
        String st1=search.getText().toString();
        b1=(ImageView) findViewById(R.id.button4);
        // textView=(TextView)findViewById(R.id.textView7);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // RequestQueue requestQueue=new RequestQueue(this);
               /*StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(Search.this, "Response"+response, Toast.LENGTH_SHORT).show();
                        builder.setTitle("Server Response");
                        builder.setMessage("Response"+response);
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                search.setText(" ");
                            }
                        });
                        AlertDialog alertDialog= builder.create();
                        alertDialog.show();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Search.this, "String error", Toast.LENGTH_SHORT).show();
                    }
                })

               {
                   @Nullable
                   @Override
                   protected Map<String, String> getParams() throws AuthFailureError {
                       HashMap<String,String> params=new HashMap<String,String>();
                       params.put("name",search.getText().toString());
                       return params;
                   }
               }
                ;Mysingleton.getInstance(Search.this).addTorequestque(stringRequest);*/

                JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i=0;i<response.length();i++){
                            try {
                                JSONObject jsonObject=response.getJSONObject(i);
                                String name=jsonObject.getString("email");
                                userList.add(new user(name));
                                //textView.setText(textView.getText()+"\n" + name);
                            }
                            catch (JSONException e)
                            {
                                e.printStackTrace();
                            }}
                        setAdapter();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Search.this, "Array  error", Toast.LENGTH_SHORT).show();
                    }
                });Mysingleton.getInstance(Search.this).addTorequestque(jsonArrayRequest);
            }
        });
    }
    private void setAdapter(){
        setOnClickListener();
        recyclerAdapter adapter=new recyclerAdapter(userList,listener);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setOnClickListener() {
        listener=new recyclerAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int pos) {
                Intent intent=new Intent(getApplicationContext(),profileActivity.class);
                intent.putExtra("username",userList.get(pos).getUsername());
                startActivity(intent);
            }
        };
    }

    public void HOME(View view)
    {
        Intent intent=new Intent(this,Home.class);
        startActivity(intent);
    }
    public void SEARCH(View view)
    {
        Intent intent=new Intent(this, Search.class);
        startActivity(intent);
    }
    public void INBOX(View view)
    {
        Intent intent=new Intent(this, Inbox.class);
        startActivity(intent);
    }
}