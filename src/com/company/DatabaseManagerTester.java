package com.company;

import java.util.List;

public class DatabaseManagerTester {
    public static void main(String[] args) {
        DatabaseManager d = new DatabaseManager();
        TeamTester t = new TeamTester();            //Creates a team class sample from the TeamTester class
        Team sampleTeam = t.allTeams.get(0);        //The sample class is in the first index of the list
        System.out.println("This is the class from the TeamTester: " + sampleTeam.getTeamName());
        d.saveTeam(sampleTeam);
        List<Team> testTeam = d.loadAllTeams();     //Reads from the Database
        System.out.println("This is from reading from the Database: " + testTeam.get(0).getTeamName());

    }
}
