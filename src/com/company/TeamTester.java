package com.company;

import java.util.ArrayList;
import java.util.List;

public class TeamTester {
    public Team team2;
    public TeamTester(){
        //Creates a complete instance of team.
        //This will be long.
        //Create Instances of the players
        team2 = new Team("TEAM2", "//SOMEPATH");
        double[] score = {8.5, 9, 10, 9};
        Player v1 = new Player("Janilou","Sy", "Senior", "Computer Science","", score );
        Player v2 = new Player("Jacob", "Drake","Junior", "Cybersecurity", "", score);
        Player v3 = new Player("Hailey", "Porter","Sophomore", "Humanities", "", score);
        Player v4 = new Player("Adriana", "Lanier","Freshman", "Art", "", score);
        Player v5 = new Player("Kevin", "Preston","Junior", "Business", "", score);
        Player v6 = new Player("Preston","Leston", "Freshman", "Law", "", score);

        //Adding to the same apparatus
        v1.setApparatusStatusByIndex(ApparatusIndex.VT, true);
        v1.setApparatusOrderByIndex(ApparatusIndex.VT, 1);
        v2.setApparatusStatusByIndex(ApparatusIndex.VT, true);
        v2.setApparatusOrderByIndex(ApparatusIndex.VT, 3);
        v3.setApparatusStatusByIndex(ApparatusIndex.VT, true);
        v3.setApparatusOrderByIndex(ApparatusIndex.VT, 4);
        v4.setApparatusStatusByIndex(ApparatusIndex.VT, true);
        v4.setApparatusOrderByIndex(ApparatusIndex.VT, 2);
        v5.setApparatusStatusByIndex(ApparatusIndex.VT, true);
        v5.setApparatusOrderByIndex(ApparatusIndex.VT, 6);
        v6.setApparatusStatusByIndex(ApparatusIndex.VT, true);
        v6.setApparatusOrderByIndex(ApparatusIndex.VT, 5);

        team2.addGymnasts(v1);
        team2.addGymnasts(v2);
        team2.addGymnasts(v3);
        team2.addGymnasts(v4);
        team2.addGymnasts(v5);
        team2.addGymnasts(v6);


        Player b1 = new Player("Rebecca","Black", "Senior", "Computer Science", "", score);
        Player b2 = new Player("Sarah","Nancy", "Junior", "Law", "", score);
        Player b3 = new Player("Courtney","Miller", "Sophomore", "Humanities", "", score);
        Player b4 = new Player("Olivia", "Sui","Freshman", "Art", "", score);
        Player b5 = new Player("Lara", "Croft","Junior", "Business", "", score);
        Player b6 = new Player("Shayne", "Topp","Freshman", "Humanities", "", score);

        //Adding to the same apparatus
        b1.setApparatusStatusByIndex(ApparatusIndex.UB, true);
        b1.setApparatusOrderByIndex(ApparatusIndex.UB, 1);
        b2.setApparatusStatusByIndex(ApparatusIndex.UB, true);
        b2.setApparatusOrderByIndex(ApparatusIndex.UB, 3);
        b3.setApparatusStatusByIndex(ApparatusIndex.UB, true);
        b3.setApparatusOrderByIndex(ApparatusIndex.UB, 4);
        b4.setApparatusStatusByIndex(ApparatusIndex.UB, true);
        b4.setApparatusOrderByIndex(ApparatusIndex.UB, 2);
        b5.setApparatusStatusByIndex(ApparatusIndex.UB, true);
        b5.setApparatusOrderByIndex(ApparatusIndex.UB, 6);
        b6.setApparatusStatusByIndex(ApparatusIndex.UB, true);
        b6.setApparatusOrderByIndex(ApparatusIndex.UB, 5);

        team2.addGymnasts(b1);
        team2.addGymnasts(b2);
        team2.addGymnasts(b3);
        team2.addGymnasts(b4);
        team2.addGymnasts(b5);
        team2.addGymnasts(b6);

        Player bb1 = new Player("Hannah", "Pangilinan","Senior", "Arts", "", score);
        Player bb2 = new Player("Emily", "Wesley","Junior", "Graphic Design", "", score);
        Player bb3 = new Player("Maddie", "Lee", "Sophomore", "Humanities", "", score);
        Player bb4 = new Player("Olivia","Smith", "Freshman", "Law", "", score);
        Player bb5 = new Player("Sara", "Smith", "Junior", "Gender Studies", "", score);
        Player bb6 = new Player("Lisa", "Newmann", "Freshman", "Humanities", "", score);

        //Adding to the same apparatus
        bb1.setApparatusStatusByIndex(ApparatusIndex.BB, true);
        bb1.setApparatusOrderByIndex(ApparatusIndex.BB, 1);
        bb2.setApparatusStatusByIndex(ApparatusIndex.BB, true);
        bb2.setApparatusOrderByIndex(ApparatusIndex.BB, 3);
        bb3.setApparatusStatusByIndex(ApparatusIndex.BB, true);
        bb3.setApparatusOrderByIndex(ApparatusIndex.BB, 4);
        bb4.setApparatusStatusByIndex(ApparatusIndex.BB, true);
        bb4.setApparatusOrderByIndex(ApparatusIndex.BB, 2);
        bb5.setApparatusStatusByIndex(ApparatusIndex.BB, true);
        bb5.setApparatusOrderByIndex(ApparatusIndex.BB, 6);
        bb6.setApparatusStatusByIndex(ApparatusIndex.BB, true);
        bb6.setApparatusOrderByIndex(ApparatusIndex.BB, 5);

        team2.addGymnasts(bb1);
        team2.addGymnasts(bb2);
        team2.addGymnasts(bb3);
        team2.addGymnasts(bb4);
        team2.addGymnasts(bb5);
        team2.addGymnasts(bb6);

        Player f1 = new Player("Saddie", "William","Senior", "Mathematics", "", score);
        Player f2 = new Player("Isabella","Doe", "Junior", "Aerospace", "", score);
        Player f3 = new Player("Paula", "Deen","Sophomore", "Computer Engineering", "", score);
        Player f4 = new Player("Adeline","Bermudez", "Freshman", "Nursing", "", score);
        Player f5 = new Player("Aubrie", "Chanel","Junior", "Law", "", score);
        Player f6 = new Player("Allysa","Torres", "Freshman", "Humanities", "", score);

        //Adding to the same apparatus
        f1.setApparatusStatusByIndex(ApparatusIndex.FX, true);
        f1.setApparatusOrderByIndex(ApparatusIndex.FX, 1);
        f2.setApparatusStatusByIndex(ApparatusIndex.FX, true);
        f2.setApparatusOrderByIndex(ApparatusIndex.FX, 3);
        f3.setApparatusStatusByIndex(ApparatusIndex.FX, true);
        f3.setApparatusOrderByIndex(ApparatusIndex.FX, 4);
        f4.setApparatusStatusByIndex(ApparatusIndex.FX, true);
        f4.setApparatusOrderByIndex(ApparatusIndex.FX, 2);
        f5.setApparatusStatusByIndex(ApparatusIndex.FX, true);
        f5.setApparatusOrderByIndex(ApparatusIndex.FX, 6);
        f6.setApparatusStatusByIndex(ApparatusIndex.FX, true);
        f6.setApparatusOrderByIndex(ApparatusIndex.FX, 5);

        team2.addGymnasts(f1);
        team2.addGymnasts(f2);
        team2.addGymnasts(f3);
        team2.addGymnasts(f4);
        team2.addGymnasts(f5);
        team2.addGymnasts(f6);

    }
}
