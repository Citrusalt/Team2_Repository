package com.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class will contain all gymnasts (separated into apparatus) for each team (college).
 * NOTE: I removed the list for each apparatus, instead, I created a method that returns a list
 *      of all players for an apparatus.
 */

public class Team implements Serializable {
    private String teamName;
    private String teamLogo;                        //Contain like the path for the logo
    private List<Player> allGymnasts;
    private TeamScore teamScores;
    private  List<Player> vaultGymnast;
    private  List<Player> barGymnast;
    private  List<Player> beamGymnast;
    private  List<Player> floorGymnast;


    public Team(String teamName, String teamLogo) {
        this.teamName = teamName;
        this.teamLogo = teamLogo;
        this.allGymnasts = new ArrayList<>();
        this.vaultGymnast = new ArrayList<>();
        this.barGymnast = new ArrayList<>();
        this.beamGymnast = new ArrayList<>();
        this.floorGymnast = new ArrayList<>();
        this.teamScores = new TeamScore();

    }
    //Getters
    public String getTeamName(){return teamName;}
    public String getTeamLogo(){return teamLogo;}

    //Method that will get all Vault Gymnasts. Returns a list of Players.
    public List<Player> getVaultGymnasts() {
        List<Player> vaultGymnasts = new ArrayList<>();
        for (int i = 0; i < allGymnasts.size(); i++){
            if(allGymnasts.get(i).getApparatusIn().contains(ApparatusStatus.VAULT)){
                vaultGymnasts.add(allGymnasts.get(i));
            }
        }
        return vaultGymnasts;
    }
    //Method that will get all Bars Gymnasts. Returns a list of Players.
    public List<Player> getBarGymnasts() {
        List<Player> barGymnasts = new ArrayList<>();
        for (int i = 0; i < allGymnasts.size(); i++){
            if(allGymnasts.get(i).getApparatusIn().contains(ApparatusStatus.BAR)){
                barGymnasts.add(allGymnasts.get(i));
            }
        }
        return barGymnasts;
    }
    //Method that will get all Beam Gymnasts. Returns a list of Players.
    public List<Player> getBeamGymnasts() {
        List<Player> beamGymnasts = new ArrayList<>();
        for (int i = 0; i < allGymnasts.size(); i++){
            if(allGymnasts.get(i).getApparatusIn().contains(ApparatusStatus.BEAM)){
                beamGymnasts.add(allGymnasts.get(i));
            }
        }
        return beamGymnasts;
    }
    //Method that will get all Floor Gymnasts. Returns a list of Players.
    public List<Player> getFloorGymnasts() {
        List<Player> floorGymnasts = new ArrayList<>();
        for (int i = 0; i < allGymnasts.size(); i++){
            if(allGymnasts.get(i).getApparatusIn().contains(ApparatusStatus.FLOOR)){
                floorGymnasts.add(allGymnasts.get(i));
            }
        }
        return floorGymnasts;
    }

    //Method that will add a player to the allGymnasts
    public void addGymnasts(Player p){
        allGymnasts.add(p);
    }


}


