package com.company;

import java.util.ArrayList;
import java.util.List;

/**
 * This class will contain all gymnasts (separated into apparatus) for each team (college).
 * Note: At some point we should add a checker if the order is valid, player is not a duplicate, etc. Maybe on this team class.
 */

public class Team {private String teamName;
    private String teamLogo;                        //Contain like the path for the logo
    private  List<Player> vaultGymnast;             //
    private  List<Player> barGymnast;
    private  List<Player> beamGymnast;
    private  List<Player> floorGymnast;

    //Constructor
    public Team() {
        this.teamName = new String();
        this.teamLogo = new String();
        this.vaultGymnast = new ArrayList<>();
        this.floorGymnast = new ArrayList<>();
        this.barGymnast = new ArrayList<>();
        this.beamGymnast = new ArrayList<>();
    }
    //Method that adds a player to the vault list
    public void addPlayerVaultGymnast(Player p){
        vaultGymnast.add(p);
    }
    //Method that adds a player to the bar list
    public void addPlayerBarGymnast(Player p){

        barGymnast.add(p);
    }
    //Method that adds a player to the beam list
    public void addPlayerBeamGymnast(Player p){

        beamGymnast.add(p);
    }
    //Method that adds a player to the floor list
    public void addPlayerFloorGymnast(Player p){

        floorGymnast.add(p);
    }

    //Method that deletes a player from the vault list
    public void deletePlayerVaultGymnast(Player p){
        vaultGymnast.remove(p);
    }
    //Method that deletes a player from the bar list
    public void deletePlayerBarGymnast(Player p){
        barGymnast.remove(p);
    }
    //Method that deletes a player from the beam list
    public void deletePlayerBeamGymnast(Player p){
        beamGymnast.remove(p);
    }
    //Method that deletes a player from the floor list
    public void deletePlayerFloorGymnast(Player p){
        floorGymnast.remove(p);
    }

    //Method that gets the list vaultGymnast
    public List<Player> getVaultGymnast(){

        return this.vaultGymnast;
    }
    //Method that gets the list barGymnast
    public List<Player> getBarGymnast(){

        return this.barGymnast;
    }
    //Method that gets the list beamGymnast
    public List<Player> getBeamGymnast(){

        return this.beamGymnast;
    }
    //Method that gets the list floorGymnast
    public List<Player> getFloorGymnast(){

        return this.floorGymnast;
    }
}


