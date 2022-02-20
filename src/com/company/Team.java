package com.company;

import java.util.HashMap;

/**
 * This class will contain all gymnasts (separated into apparatus) for each team (college).
 * Each team division is a HashMap, this will help keep track who the players are for each apparatus and their order in that apparatus.
 * Note: I will have to create a remove methods for each apparatus team. The order are in random on the map, have to think of how to arrange them.
 */

public class Team {
    private String teamName;
    private String teamLogo;                                  //Contain like the path for the logo
    private final HashMap<Player, Integer> vaultGymnast;      //Hashmap<Player, OrderInTheApparatus>
    private final HashMap<Player, Integer> floorGymnast;
    private final HashMap<Player, Integer> barGymnast;
    private final HashMap<Player, Integer> beamGymnast;

    //Constructor
    Team() {
        vaultGymnast = new HashMap<>();
        floorGymnast = new HashMap<>();
        barGymnast = new HashMap<>();
        beamGymnast = new HashMap<>();
    }
    //Method that will contain the path for the team logo
    public void setTeamLogo(String s){
        this.teamLogo = s;
    }
    //Method that will set the team name
    public void setTeamName(String s){
        this.teamName = s;
    }

    //Method that will add a player into the vault division of the team
    //Checks if there's enough players, the player exist, someone is alr in that order.
    public boolean addPlayerVaults(Player p, int order){
        if(vaultGymnast.size() < 6 && !vaultGymnast.containsKey(p) && !vaultGymnast.containsValue(order)
            && order <= 6){
            vaultGymnast.put(p, order);
            return true;
        }else return false;
    }

    //Method that will add a player into the floor division of the team
    public boolean addPlayerFloors(Player p, int order){
        if(floorGymnast.size() < 6 && !floorGymnast.containsKey(p) && !floorGymnast.containsValue(order)
                && order <= 6){
            floorGymnast.put(p, order);
            return true;
        }else return false;
    }

    //Method that will add a player into the bar division of the team
    public boolean addPlayerBar(Player p, int order){
        if(barGymnast.size() < 6 && !barGymnast.containsKey(p) && !barGymnast.containsValue(order)
                && order <= 6){
            barGymnast.put(p, order);
            return true;
        }else return false;
    }

    //Method that will add a player into the beam division of the team
    public boolean addPlayerBeam(Player p, int order){
        if(beamGymnast.size() < 6 && !beamGymnast.containsKey(p) && !beamGymnast.containsValue(order)
                && order <= 6){
            beamGymnast.put(p, order);
            return true;
        }else return false;
    }

    //Getters
    public String getTeamName() {
        return teamName;
    }

    public HashMap<Player, Integer> getVaultGymnast() {
        return vaultGymnast;
    }

    public HashMap<Player, Integer> getFloorGymnast() {
        return floorGymnast;
    }

    public HashMap<Player, Integer> getBarGymnast() {
        return barGymnast;
    }

    public HashMap<Player, Integer> getBeamGymnast() {
        return beamGymnast;
    }

    //This method is for test only. Prints all information.
    public void printAll(){
        System.out.println(teamLogo);
        System.out.println("-------------");
        System.out.println("    Vaults:");
        for (Player name: vaultGymnast.keySet()) {
            String key = name.getPlayerName();
            String value = vaultGymnast.get(name).toString();
            System.out.println(key + "  | Order:" + value);
        }
        System.out.println("-------------");
        System.out.println("    Floors:");
        for (Player name: floorGymnast.keySet()) {
            String key = name.getPlayerName();
            String value = floorGymnast.get(name).toString();
            System.out.println(key + "  | Order:" + value);
        }
        System.out.println("-------------");
        System.out.println("    Bars:");
        for (Player name: barGymnast.keySet()) {
            String key = name.getPlayerName();
            String value = barGymnast.get(name).toString();
            System.out.println(key + "  | Order:" + value);
        }
        System.out.println("-------------");
        System.out.println("    Beams:");
        for (Player name: beamGymnast.keySet()) {
            String key = name.getPlayerName();
            String value = beamGymnast.get(name).toString();
            System.out.println(key + "  | Order:" + value);
        }

    }
}


