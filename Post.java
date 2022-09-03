package com.example.idea_returns;



import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Post extends AppCompatActivity {
    EditText textView;
    Button home;
    String url="https://mysummerproject.000webhostapp.com/post.php";
    Button post;
    AlertDialog.Builder builder;
    String email2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        //loginIDEA =new LoginIDEA();
        //email2=(EditText) )((loginIDEA.email)
        builder=new AlertDialog.Builder(this);
        post=(Button) findViewById(R.id.post_butt);
        home=(Button)findViewById(R.id.home_post);
        textView=(EditText) findViewById(R.id.textView5);

        // LoginIDEA loginIDEA=new LoginIDEA();
        //  email2=loginIDEA.p;
        Toast.makeText(Post.this, "email"+email2, Toast.LENGTH_SHORT).show();
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        builder.setTitle("Response");
                       builder.setMessage(""+response);
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                textView.setText(" ");
                            }
                        });
                        AlertDialog alertDialog= builder.create();
                        alertDialog.show();
                        Toast.makeText(Post.this, ""+response, Toast.LENGTH_SHORT).show();
                    }
                }
                        , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Post.this, "Unsuccesfull", Toast.LENGTH_SHORT).show();
                        //   Toast.makeText(loginIDEA, "email"+email2, Toast.LENGTH_SHORT).show();
                    }
                }
                )
                {
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String,String>params=new HashMap<String,String>();
                        String email=getIntent().getStringExtra("email");
                        params.put("idea",textView.getText().toString());
                        params.put("email",email);
                        return params;
                    }
                };Mysingleton.getInstance(Post.this).addTorequestque(stringRequest);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Post.this,Home.class);
                startActivity(intent);
            }
        });

    }

}