package com.company;

public class Apparatus {
    private String apparatusName;
    private Player[] team1ActivePlayers;
    private Player[] team2ActivePlayers;
    private Player[] team3ActivePlayers;
    private Player[] team4ActivePlayers;
    private boolean needsTimer;

    //Constructor
    public Apparatus (String apparatusName, Player[] team1ActivePlayers, Player[] team2ActivePlayers, Player[] team3ActivePlayers, Player[] team4ActivePlayers, boolean needsTimer)
    {
        this.apparatusName = apparatusName;
        this.team1ActivePlayers = team1ActivePlayers;
        this.team2ActivePlayers = team2ActivePlayers;
        this.team3ActivePlayers = team3ActivePlayers;
        this.team4ActivePlayers = team4ActivePlayers;
        this.needsTimer = needsTimer;
    }

    //Getters
    public String getApparatusName() {return apparatusName;}

    public Player[] getTeam1ActivePlayers() {return team1ActivePlayers;}

    public Player[] getTeam2ActivePlayers() {return team2ActivePlayers;}

    public Player[] getTeam3ActivePlayers() {return team3ActivePlayers;}

    public Player[] getTeam4ActivePlayers() {return team4ActivePlayers;}

    public boolean getNeedsTimer(){return needsTimer;}

    //Setters
    public void setApparatusName(String apparatusName) {this.apparatusName = apparatusName;}

    public void setTeam1ActivePlayers(Player[] team1ActivePlayers) {this.team1ActivePlayers = team1ActivePlayers;}

    public void setTeam2ActivePlayers(Player[] team2ActivePlayers) {this.team2ActivePlayers = team2ActivePlayers;}

    public void setTeam3ActivePlayers(Player[] team3ActivePlayers) {this.team3ActivePlayers = team3ActivePlayers;}

    public void setTeam4ActivePlayers(Player[] team4ActivePlayers) {this.team4ActivePlayers = team4ActivePlayers;}

    public void setNeedsTimer(boolean needsTimer){this.needsTimer = needsTimer;}


    //Methods

}