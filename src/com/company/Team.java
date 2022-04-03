package com.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * This class will contain all gymnasts (separated into apparatus) for each team (college).
 * Note: At some point we should add a checker if the order is valid, player is not a duplicate, etc. Maybe on this team class.
 */

public class Team implements Serializable {
    private String teamName;
    private String teamLogo;                        //Contain like the path for the logo
    private  List<Player> vaultGymnast;             //
    private  List<Player> barGymnast;
    private  List<Player> beamGymnast;
    private  List<Player> floorGymnast;
    private TeamScore teamScores;


    public Team(String teamName, String teamLogo) {
        this.teamName = teamName;
        this.teamLogo = teamLogo;
        this.vaultGymnast = new ArrayList<>();
        this.barGymnast = new ArrayList<>();
        this.beamGymnast = new ArrayList<>();
        this.floorGymnast = new ArrayList<>();
        this.teamScores = new TeamScore();
    }
    public String getTeamName(){return teamName;}
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
    public TeamScore getTeamScores(){
        return this.teamScores;
    }


}

class sortByOverallScore implements Comparator<Team> {
    public int compare(Team team1, Team team2)
    {
        return (int) ((team1.getTeamScores().getvaultScore() + team1.getTeamScores().getbarScore() + team1.getTeamScores().getbeamScore() + team1.getTeamScores().getfloorScore()) - (team2.getTeamScores().getvaultScore() + team2.getTeamScores().getbarScore() + team2.getTeamScores().getbeamScore() + team2.getTeamScores().getfloorScore()));
    }
}


