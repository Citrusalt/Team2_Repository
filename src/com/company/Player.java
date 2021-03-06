package com.company;

import java.io.Serializable;

/**
 * This class will store personal information for each gymnast
 * */
public class Player implements Serializable {
    private String playerfName;
    private String playerlName;
    private String playerClass;
    private String playerMajor;
    private String playerPicture;
    private double[] playerAvg;                   //Make it into a list double

    private boolean[] apparatusStatus;
    private int[] apparatusOrder;
    private PlayerScore playerScore;

    //Constructor
    public Player (String playerfName, String playerlName, String playerClass, String playerMajor, String playerPicture, double[] playerAvg){
        this.playerfName = playerfName;
        this.playerlName = playerlName;
        this.playerClass = playerClass;
        this.playerMajor = playerMajor;
        this.playerPicture = playerPicture;
        this.playerAvg = playerAvg;
        //Store the playerAverage to the playerScore's playerAverage
        this.apparatusStatus = new boolean[]{false, false, false, false};
        this.apparatusOrder = new int[]{0,0,0,0};
        this.playerScore = new PlayerScore();
    }

    //getters
    public String getPlayerfName(){return playerfName;}

    public String getPlayerlName(){return playerlName;}

    public String getPlayerClass(){return playerClass;}

    public String getPlayerMajor(){return playerMajor;}

    public int[] getApparatusOrder() {return apparatusOrder;}           //Returns the whole array

    public PlayerScore getPlayerScore() {
        return playerScore;
    }

    public boolean getApparatusStatusByIndex(int i) {          //Passed in the index of which apparatus they want to check
        return apparatusStatus[i];
    }       //Returns the Status by Index

    //Setters
    public void setPlayerfName(String playerfName) {
        this.playerfName = playerfName;
    }

    public void setPlayerlName(String playerlName) {
        this.playerlName = playerlName;
    }

    public void setPlayerClass(String playerClass) {
        this.playerClass = playerClass;
    }

    public void setPlayerMajor(String playerMajor) {
        this.playerMajor = playerMajor;
    }

    public void setApparatusStatusByIndex(int i, boolean b){apparatusStatus[i] = b; }   //Sets the apparatus status by Index

    public void setApparatusOrderByIndex(int appIdx, int order) {
        this.apparatusOrder[appIdx] = order;
    }      //Sets the apparatus order by index

    public String getPlayerPicture() {
        return playerPicture;
    }

    public void setPlayerPicture(String playerPicture) {
        this.playerPicture = playerPicture;
    }

    public double[] getPlayerAvg() {
        return playerAvg;
    }

    public void setPlayerAvg(double[] playerAvg) {
        this.playerAvg = playerAvg;
    }

    public boolean[] getApparatusStatus() {
        return apparatusStatus;
    }

    public void setApparatusStatus(boolean[] apparatusStatus) {
        this.apparatusStatus = apparatusStatus;
    }

    public void setApparatusOrder(int[] apparatusOrder) {
        this.apparatusOrder = apparatusOrder;
    }

    public void setPlayerScore(PlayerScore playerScore) {
        this.playerScore = playerScore;
    }

    //Print all info. For testing purposes only.
    public void printAll(){
        System.out.println("----------- Player Information ----------");
        System.out.println("First Name: " + this.playerfName);
        System.out.println("Last Name: " + this.playerlName);
        System.out.println("Class: " + this.playerClass);
        System.out.println("Major: " + this.playerMajor);
        System.out.println("Vault Average: " + this.playerAvg[ApparatusIndex.VT]);
        System.out.println("Bar Average: " + this.playerAvg[ApparatusIndex.UB]);
        System.out.println("Beam Average: " + this.playerAvg[ApparatusIndex.BB]);
        System.out.println("Floor Average: " + this.playerAvg[ApparatusIndex.FX]);


        System.out.println("Apparatus in: ");
        for(int i = 0; i < 4; i++){
            if(apparatusStatus[i] == true){
                switch(i){
                    case 0: System.out.println("Player is in [Vaults], Order: " + apparatusOrder[i]);
                        break;
                    case 1: System.out.println("Player is in [Bars], Order: " + apparatusOrder[i]);
                        break;
                    case 2: System.out.println("Player is in [Beams], Order: " + apparatusOrder[i]);
                        break;
                    case 3: System.out.println("Player is in [Floors], Order: " + apparatusOrder[i]);
                        break;
                }
            }
        }
        System.out.println("------------------------------------");
    }
}
