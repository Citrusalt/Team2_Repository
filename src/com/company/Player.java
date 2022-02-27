package com.company;
/**
 * This class will store personal information for each gymnast
 * Comment: maybe  we can add string that will contain picPath since we have to display their pic (?) -jlou
 * */
public class Player {

    //Constructor
    public Player (String playerName, String playerClass, String playerMajor, String playerAvg){
        this.playerName = playerName;
        this.playerClass = playerClass;
        this.playerMajor = playerMajor;
        this.playerAvg = playerAvg;
        this.playerApparatus = new PlayerRole();
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

    //Print all info. For testing purposes only.
    public void printAll(){
        System.out.println("----------- Player Information ----------");
        System.out.println("Name: " + this.playerName);
        System.out.println("Class: " + this.playerClass);
        System.out.println("Major: " + this.playerMajor);
        System.out.println("Average: " + this.playerAvg);

        System.out.println("Apparatus in: ");
        for(int i = 0; i < 4; i++){
            if(playerApparatus.getApparatusStatus()[i] == true){
                switch(i){
                    case 0: System.out.println("[Vaults]" + " order number is " + this.playerApparatus.getOrderApparatus()[i]);
                        break;
                    case 1: System.out.println("[Bars]" + " order number is " + this.playerApparatus.getOrderApparatus()[i]);
                        break;
                    case 2: System.out.println("[Beams]" + " order number is " + this.playerApparatus.getOrderApparatus()[i]);
                        break;
                    case 3: System.out.println("[Floors]" +  " order number is " + this.playerApparatus.getOrderApparatus()[i]);
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
    PlayerRole playerApparatus;

}
