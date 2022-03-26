package com.company;

import java.util.ArrayList;
import java.util.List;

public class TeamTester {
    List<Team> allTeams;
    public TeamTester(){
        //Create Instances of the player
        Player v1 = new Player("Janilou", "Senior", "Computer Science", "95");
        Player v2 = new Player("Jacob", "Junior", "Computer Science", "93");
        Player v3 = new Player("Hailey", "Sophomore", "Computer Science", "92");
        Player v4 = new Player("Adriana", "Freshman", "Computer Science", "93");

        //Adding the apparatus and their order
        v1.playerApparatus.setApparatusStatus(ApparatusIndex.UB);
        v1.playerApparatus.setOrderApparatus(ApparatusIndex.UB, 2);
        //v1.printAll();

        v2.playerApparatus.setApparatusStatus(ApparatusIndex.BB);
        v2.playerApparatus.setOrderApparatus(ApparatusIndex.BB, 1);
        //v2.printAll();

        v3.playerApparatus.setApparatusStatus(ApparatusIndex.FX);
        v3.playerApparatus.setOrderApparatus(ApparatusIndex.FX, 1);
        //v3.printAll();

        v4.playerApparatus.setApparatusStatus(ApparatusIndex.VT);
        v4.playerApparatus.setApparatusStatus(ApparatusIndex.UB);
        v4.playerApparatus.setOrderApparatus(ApparatusIndex.UB, 1);
        v4.playerApparatus.setOrderApparatus(ApparatusIndex.VT, 1);
        //v4.printAll();
        Team team2 = new Team("TEAM12", "//SOMEPATH");
        team2.addPlayerBarGymnast(v1);
        team2.addPlayerBarGymnast(v4);
        team2.addPlayerBeamGymnast(v2);
        team2.addPlayerFloorGymnast(v3);
        team2.addPlayerVaultGymnast(v4);
        allTeams = new ArrayList<>();
        allTeams.add(team2);
    }
}
