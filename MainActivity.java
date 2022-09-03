package com.example.idea_returns;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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

public class MainActivity extends AppCompatActivity {
    TextView signup;

    Button button;
    EditText email;
    EditText password;
    CheckBox remember;
    String url2="https://mysummerproject.000webhostapp.com/login_account.php";
    AlertDialog.Builder builder;
    SharedPreferences getShared;
    String Username="username";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=(Button)findViewById(R.id.log);
        email=(EditText)findViewById(R.id.editTextTextEmailAddress);
        builder=new AlertDialog.Builder(this);
         signup=(TextView) findViewById(R.id.button8);
        password=(EditText)findViewById(R.id.editTextTextPassword);
        remember=(CheckBox) findViewById(R.id.rememberMe);

        SharedPreferences preferences=getSharedPreferences("checkbox",MODE_PRIVATE);
        String checkbox=preferences.getString("remember","");
        SharedPreferences preferences1=getSharedPreferences("us",MODE_PRIVATE);
        String us=preferences1.getString("email","");

        //Toast.makeText(this, "email :"+email, Toast.LENGTH_SHORT).show();
        if(checkbox.equals("true"))
        {
            //String username=preferences1.getString("email","");
            Intent intent=new Intent(MainActivity.this,Home.class);
            startActivity(intent);
        }
        else if(checkbox.equals("false"))
        {
            Toast.makeText(this, "Please sign in", Toast.LENGTH_SHORT).show();
        }

       /* if(getShared.contains(Username ));
        {
            Intent intent=new Intent(MainActivity.this,Home.class);
            startActivity(intent);
        }*/

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(email.getText().toString()!=""||password.getText().toString()!=""){
                StringRequest stringRequest=new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        Toast.makeText(MainActivity.this, "Response"+response, Toast.LENGTH_SHORT).show();
                        if(response.toString().equals("true"))
                        {
                            /*String p=email.getText().toString();
                            SharedPreferences.Editor editor=getShared.edit();
                            editor.putString("username",p);
                            editor.apply();*/
                           // String username=preferences1.getString("email","");
                            SharedPreferences preferences1=getSharedPreferences("us",MODE_PRIVATE);
                            SharedPreferences.Editor editor1=preferences1.edit();
                            editor1.putString("email",email.getText().toString());
                            editor1.apply();
                            Intent intent=new Intent(MainActivity.this,Home.class);

                           // intent.putExtra("Username",p);
                            startActivity(intent);
                        }
                        else
                            Toast.makeText(MainActivity.this, "Invalid User id Password", Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "failed", Toast.LENGTH_SHORT).show();
                    }

                }

                )
                {
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params=new HashMap<String, String>();
                        params.put("username",email.getText().toString());
                        params.put("password",password.getText().toString());

                        return params;
                    }
                };
                   Mysingleton.getInstance(MainActivity.this).addTorequestque(stringRequest);
               }
               }

        });
        remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked())
                {
                    SharedPreferences preferences=getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString("remember","true");

                    editor.apply();
                    SharedPreferences preferences1=getSharedPreferences("us",MODE_PRIVATE);
                    SharedPreferences.Editor editor1=preferences1.edit();
                    editor1.putString("email",email.getText().toString());
                    editor1.apply();
                    Toast.makeText(MainActivity.this, "Checked", Toast.LENGTH_SHORT).show();
                }
                else if(!buttonView.isChecked()){
                    SharedPreferences preferences=getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString("remember","false");
                    editor.apply();
                    Toast.makeText(MainActivity.this, "Unchecked", Toast.LENGTH_SHORT).show();
                }
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,signup.class);
                startActivity(i);
            }
        });
    }
}