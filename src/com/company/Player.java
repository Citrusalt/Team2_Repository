package com.company;
/**
 * This class will store personal information for each gymnast
 * Comment: maybe  we can add string that will contain picPath since we have to display their pic (?) -jlou
 * */
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

    //Setters
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setPlayerClass(String playerClass) {
        this.playerClass = playerClass;
    }

    public void setPlayerMajor(String playerMajor) {
        this.playerMajor = playerMajor;
    }

    public void setPlayerAvg(String playerAvg) {
        this.playerAvg = playerAvg;
    }

    private String playerName;
    private String playerClass;
    private String playerMajor;
    private String playerAvg;
    //private String playerPic;
}
