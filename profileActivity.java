package com.example.idea_returns;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class profileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        TextView pname=findViewById(R.id.pname);
        String username="Username";
        Bundle extras= getIntent().getExtras();
        if(extras!=null){
            username=extras.getString("username");
        }
        pname.setText(username);
    }
}
