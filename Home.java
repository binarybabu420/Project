package com.example.idea_returns;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Home extends AppCompatActivity {
    private ArrayList<userHome> userList;
    private RecyclerView recyclerView;
    String url="https://mysummerproject.000webhostapp.com/home.php";
    ImageView btn;
    TextView welcome;
    ImageView logout;
ImageView h;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        SharedPreferences preferences1=getSharedPreferences("us",MODE_PRIVATE);
        SharedPreferences.Editor editor1=preferences1.edit();

         String s=preferences1.getString("email","");
     logout=(ImageView)findViewById(R.id.logout);
      h=(ImageView)findViewById(R.id.imageView);
        welcome=(TextView)findViewById(R.id.welcome);
        welcome.setText("Welcome "+s);
        recyclerView= findViewById(R.id.recyclerHome);
        userList=new ArrayList<>();
      logout.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              SharedPreferences preferences=getSharedPreferences("checkbox",MODE_PRIVATE);
              SharedPreferences.Editor editor=preferences.edit();
              editor.putString("checkbox","false");
              editor.apply();
              SharedPreferences preferences1=getSharedPreferences("us",MODE_PRIVATE);
              SharedPreferences.Editor editor1=preferences1.edit();
              editor1.putString("email","");
              finish();


              //Intent intent4=new Intent(Home.this,MainActivity.class);
              //startActivity(intent4);


          }
      });


        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for(int i=0;i<response.length();i++){
                    try {
                        JSONObject jsonObject=response.getJSONObject(i);
                        String idea=jsonObject.getString("email");
                        String name=jsonObject.getString("idea");
                        String idea1="";
                        if(idea.length()<10) {
                            int k=idea.length();
                            idea1 = idea.substring(0, k);
                        }
                        else
                           idea1 = idea.substring(0, 10);
                        userList.add(new userHome(name,idea1));

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
                Toast.makeText(Home.this, "Array  error", Toast.LENGTH_SHORT).show();
            }
        });Mysingleton.getInstance(Home.this).addTorequestque(jsonArrayRequest);
        //Toast.makeText(this, "Response"+s, Toast.LENGTH_SHORT).show();

        btn=(ImageView) findViewById(R.id.imageView5);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 =new Intent(Home.this,Post.class);
                intent1.putExtra("email",s);
                startActivity(intent1);
            }
        });
    }
    private void setAdapter(){
        recyclerHOme adapter=new recyclerHOme(userList);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

   public void HO(View view)
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