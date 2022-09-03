package com.example.idea_returns;

public class userHome {
    private String username;
    private String idea;
    public userHome(String username,String idea){
        this.username=username;//.substring(0,5);
        this.idea=idea;//.substring(0,5);
    }
    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username=username;
    }
    public String getIdea()
    {
        return idea;
    }

    public void setIdea(String idea) {
        this.idea = idea;
    }
}
