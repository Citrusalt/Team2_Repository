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
        List<Team> testTeam = d.getAllTeams();     //Reads from the Database
        System.out.println("Size of the list:" + d.getAllTeams().size());
        Player cz = new Player("Cole", "Zandy","Senior", "CompSci",99);
        testTeam.get(0).getAllGymnasts().get(0).printAll();

    }
}
