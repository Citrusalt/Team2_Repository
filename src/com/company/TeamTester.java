package com.company;

import java.util.ArrayList;
import java.util.List;

public class TeamTester {
    public Team team2;
    public TeamTester(){
        //Create Instances of the player
        team2 = new Team("TEAM2", "//SOMEPATH");

        Player v1 = new Player("Janilou", "Senior", "Computer Science", 95);
        Player v2 = new Player("Jacob", "Junior", "Cybersecurity", 96);
        Player v3 = new Player("Hailey", "Sophomore", "Humanities", 95);
        Player v4 = new Player("Adriana", "Freshman", "Art", 98);
        Player v5 = new Player("Tyler", "Junior", "Business", 67);
        Player v6 = new Player("Hunter", "Freshman", "Law", 89);

        //Adding to the same apparatus
        v1.setApparatusStatus(ApparatusIndex.UB);
        v1.setApparatusOrder(ApparatusIndex.UB, 1);
        v2.setApparatusStatus(ApparatusIndex.UB);
        v2.setApparatusOrder(ApparatusIndex.UB, 3);
        v3.setApparatusStatus(ApparatusIndex.UB);
        v3.setApparatusOrder(ApparatusIndex.UB, 4);
        v4.setApparatusStatus(ApparatusIndex.UB);
        v4.setApparatusOrder(ApparatusIndex.UB, 2);
        v5.setApparatusStatus(ApparatusIndex.UB);
        v5.setApparatusOrder(ApparatusIndex.UB, 6);
        v6.setApparatusStatus(ApparatusIndex.UB);
        v6.setApparatusOrder(ApparatusIndex.UB, 5);

        team2.addGymnasts(v1);
        team2.addGymnasts(v2);
        team2.addGymnasts(v3);
        team2.addGymnasts(v4);
        team2.addGymnasts(v5);
        team2.addGymnasts(v6);

    }
}
