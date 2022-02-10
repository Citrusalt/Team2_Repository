package com.company;

public class Player {

    public Player (String playerName, String playerClass, String playerMajor, String playerAvg){
        this.playerName = playerName;
        this.playerClass = playerClass;
        this.playerMajor = playerMajor;
        this.playerAvg = playerAvg;
    }


    /*
    Below are public get functions for Player Object
     */
    public String getPlayerName(){return playerName;}

    public String getPlayerClass(){return playerClass;}

    public String getPlayerMajor(){return playerMajor;}

    public String getPlayerAvg(){return playerAvg;}

    private String playerName;
    private String playerClass;
    private String playerMajor;
    private String playerAvg;
}
