package com.company;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * This class will store personal information for each gymnast
 *
 * */
public class Player implements Serializable {

    private String playerName;
    private String playerClass;
    private String playerMajor;
    private double playerAvg;                   //Make it into a list double
    private String playerPic;
    //private boolean[] apparatusStatus = new boolean[4];             //OMIT
    Set<ApparatusStatus> apparatusIn;
    //Constructor
    public Player (String playerName, String playerClass, String playerMajor, double playerAvg){
        this.playerName = playerName;
        this.playerClass = playerClass;
        this.playerMajor = playerMajor;
        this.playerAvg = playerAvg;
        apparatusIn = new HashSet<>();
        //apparatusStatus = new boolean[]{false,false,false,false};       //OMIT

    }

    //getters
    public String getPlayerName(){return playerName;}

    public String getPlayerClass(){return playerClass;}

    public String getPlayerMajor(){return playerMajor;}

    public double getPlayerAvg(){return playerAvg;}

    public Set<ApparatusStatus> getApparatusIn(){return apparatusIn;}

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

    public void setPlayerAvg(double playerAvg) {
        this.playerAvg = playerAvg;
    }

    //Method that adds if they participate in an apparatus
    public void addApparatus(ApparatusStatus status){
        apparatusIn.add(status);
    }

    //Method that deletes an ApparatusStatus from apparatusIn
    public void deleteApparatus(ApparatusStatus status){
        apparatusIn.remove(status);
    }

//
//    //Method that sets if they play apparatus. Pass in the index.           //OMIT
//    public void setApparatusStatus(int i){
//        apparatusStatus[i] = true;
//    }
//
//    public boolean[] getApparatusStatus() {                   //OMIT
//        return apparatusStatus;
//    }       //OMIT

    //Print all info. For testing purposes only.
    public void printAll(){
        System.out.println("----------- Player Information ----------");
        System.out.println("Name: " + this.playerName);
        System.out.println("Class: " + this.playerClass);
        System.out.println("Major: " + this.playerMajor);
        System.out.println("Average: " + this.playerAvg);

        System.out.println("Apparatus in: ");
        System.out.println(apparatusIn.toString());
//        for(int i = 0; i < 4; i++){                                       //OMIT
//            if(apparatusStatus[i] == true){
//                switch(i){
//                    case 0: System.out.println("Player is in [Vaults]");
//                        break;
//                    case 1: System.out.println("Player is in [Bars]");
//                        break;
//                    case 2: System.out.println("Player is in [Beams]");
//                        break;
//                    case 3: System.out.println("Player is in [Floors]");
//                        break;
//                }
//            }
//        }
        System.out.println("------------------------------------");
    }


}
