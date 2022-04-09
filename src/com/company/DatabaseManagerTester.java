package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class DatabaseManagerTester {
    public static void main(String[] args) {
        DatabaseManager d = new DatabaseManager();
        TeamTester t = new TeamTester();            //Creates a team class sample from the TeamTester class
        Team sampleTeam = t.team2;        //The sample class is in the first index of the list
        System.out.println("This is the class from the TeamTester: " + sampleTeam.getTeamName());
        d.saveTeam(sampleTeam);
        List<Team> testTeam = d.loadAllTeams();     //Reads from the Database
        System.out.println("This is from reading from the Database: " + testTeam.get(0).getTeamName());
        Player cz = new Player("Cole", "Senior", "CompSci",99);

        sampleTeam.printAll();
        sampleTeam.rearrangePlayerList(sampleTeam.getBarGymnasts().get(5), 2, ApparatusIndex.UB);
        sampleTeam.printAll();
    }
}
