package com.example.idea_returns;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recyclerHOme extends RecyclerView.Adapter<recyclerHOme.MyViewHolder> {
    private ArrayList<userHome> userList;
    public recyclerHOme(ArrayList<userHome>userList){
        this.userList=userList;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTxt;
        private TextView ideaTxt;
        public MyViewHolder(final View view){
            super(view);
            nameTxt=view.findViewById(R.id.textView4);
            ideaTxt=view.findViewById(R.id.textView6);
        }
    }

    @NonNull
    @Override
    public recyclerHOme.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.list2,parent,false);
        return new MyViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(@NonNull recyclerHOme.MyViewHolder holder, int position) {
        String name =userList.get(position).getUsername();
        String idea=userList.get(position).getIdea();
        holder.nameTxt.setText(name);
        holder.ideaTxt.setText(idea);

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }


}
