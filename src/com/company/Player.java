package com.company;

import java.io.Serializable;

/**
 * This class will store personal information for each gymnast
 * Comment: maybe  we can add string that will contain picPath since we have to display their pic (?) -jlou
 * */
public class Player implements Serializable {

    //Constructor
    public Player (String playerName, String playerClass, String playerMajor, String playerAvg){
        this.playerName = playerName;
        this.playerClass = playerClass;
        this.playerMajor = playerMajor;
        this.playerAvg = playerAvg;
        for(int i = 0; i<3; i++){
            this.apparatusStatus[i] = false;
        }
    }

    //getters
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

    //Method that sets if they play apparatus. Pass in the index.
    public void setApparatusStatus(int i){
        apparatusStatus[i] = true;
    }

    public boolean[] getApparatusStatus() {
        return apparatusStatus;
    }

    //Print all info. For testing purposes only.
    public void printAll(){
        System.out.println("----------- Player Information ----------");
        System.out.println("Name: " + this.playerName);
        System.out.println("Class: " + this.playerClass);
        System.out.println("Major: " + this.playerMajor);
        System.out.println("Average: " + this.playerAvg);

        System.out.println("Apparatus in: ");
        for(int i = 0; i < 4; i++){
            if(apparatusStatus[i] == true){
                switch(i){
                    case 0: System.out.println("Player is in [Vaults]");
                        break;
                    case 1: System.out.println("Player is in [Bars]");
                        break;
                    case 2: System.out.println("Player is in [Beams]");
                        break;
                    case 3: System.out.println("Player is in [Floors]");
                        break;
                }
            }
        }
        System.out.println("------------------------------------");
    }

    private String playerName;
    private String playerClass;
    private String playerMajor;
    private String playerAvg;                   //Make it into a list double
    private String playerPic;
    private boolean[] apparatusStatus;

}
