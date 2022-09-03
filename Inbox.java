package com.example.idea_returns;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Inbox extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);
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