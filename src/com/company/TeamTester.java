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

        Player v1 = new Player("Janilou","Sy", "Senior", "Computer Science", 95);
        Player v2 = new Player("Jacob", "Drake","Junior", "Cybersecurity", 96);
        Player v3 = new Player("Hailey", "Porter","Sophomore", "Humanities", 95);
        Player v4 = new Player("Adriana", "Lanier","Freshman", "Art", 98);
        Player v5 = new Player("Kevin", "Preston","Junior", "Business", 67);
        Player v6 = new Player("Preston","Leston", "Freshman", "Law", 89);

        //Adding to the same apparatus
        v1.setApparatusStatus(ApparatusIndex.VT);
        v1.setApparatusOrder(ApparatusIndex.VT, 1);
        v2.setApparatusStatus(ApparatusIndex.VT);
        v2.setApparatusOrder(ApparatusIndex.VT, 3);
        v3.setApparatusStatus(ApparatusIndex.VT);
        v3.setApparatusOrder(ApparatusIndex.VT, 4);
        v4.setApparatusStatus(ApparatusIndex.VT);
        v4.setApparatusOrder(ApparatusIndex.VT, 2);
        v5.setApparatusStatus(ApparatusIndex.VT);
        v5.setApparatusOrder(ApparatusIndex.VT, 6);
        v6.setApparatusStatus(ApparatusIndex.VT);
        v6.setApparatusOrder(ApparatusIndex.VT, 5);

        team2.addGymnasts(v1);
        team2.addGymnasts(v2);
        team2.addGymnasts(v3);
        team2.addGymnasts(v4);
        team2.addGymnasts(v5);
        team2.addGymnasts(v6);


        Player b1 = new Player("Rebecca","Black", "Senior", "Computer Science", 93);
        Player b2 = new Player("Sarah","Nancy", "Junior", "Law", 86);
        Player b3 = new Player("Courtney","Miller", "Sophomore", "Humanities", 85);
        Player b4 = new Player("Olivia", "Sui","Freshman", "Art", 88);
        Player b5 = new Player("Lara", "Croft","Junior", "Business", 87);
        Player b6 = new Player("Shayne", "Topp","Freshman", "Humanities", 99);

        //Adding to the same apparatus
        b1.setApparatusStatus(ApparatusIndex.UB);
        b1.setApparatusOrder(ApparatusIndex.UB, 1);
        b2.setApparatusStatus(ApparatusIndex.UB);
        b2.setApparatusOrder(ApparatusIndex.UB, 3);
        b3.setApparatusStatus(ApparatusIndex.UB);
        b3.setApparatusOrder(ApparatusIndex.UB, 4);
        b4.setApparatusStatus(ApparatusIndex.UB);
        b4.setApparatusOrder(ApparatusIndex.UB, 2);
        b5.setApparatusStatus(ApparatusIndex.UB);
        b5.setApparatusOrder(ApparatusIndex.UB, 6);
        b6.setApparatusStatus(ApparatusIndex.UB);
        b6.setApparatusOrder(ApparatusIndex.UB, 5);

        team2.addGymnasts(b1);
        team2.addGymnasts(b2);
        team2.addGymnasts(b3);
        team2.addGymnasts(b4);
        team2.addGymnasts(b5);
        team2.addGymnasts(b6);

        Player bb1 = new Player("Hannah", "Pangilinan","Senior", "Arts", 83);
        Player bb2 = new Player("Emily", "Wesley","Junior", "Graphic Design", 86);
        Player bb3 = new Player("Maddie", "Lee", "Sophomore", "Humanities", 95);
        Player bb4 = new Player("Olivia","Smith", "Freshman", "Law", 88);
        Player bb5 = new Player("Sara", "Smith", "Junior", "Gender Studies", 87);
        Player bb6 = new Player("Lisa", "Newmann", "Freshman", "Humanities", 79);

        //Adding to the same apparatus
        bb1.setApparatusStatus(ApparatusIndex.BB);
        bb1.setApparatusOrder(ApparatusIndex.BB, 1);
        bb2.setApparatusStatus(ApparatusIndex.BB);
        bb2.setApparatusOrder(ApparatusIndex.BB, 3);
        bb3.setApparatusStatus(ApparatusIndex.BB);
        bb3.setApparatusOrder(ApparatusIndex.BB, 4);
        bb4.setApparatusStatus(ApparatusIndex.BB);
        bb4.setApparatusOrder(ApparatusIndex.BB, 2);
        bb5.setApparatusStatus(ApparatusIndex.BB);
        bb5.setApparatusOrder(ApparatusIndex.BB, 6);
        bb6.setApparatusStatus(ApparatusIndex.BB);
        bb6.setApparatusOrder(ApparatusIndex.BB, 5);

        team2.addGymnasts(bb1);
        team2.addGymnasts(bb2);
        team2.addGymnasts(bb3);
        team2.addGymnasts(bb4);
        team2.addGymnasts(bb5);
        team2.addGymnasts(bb6);

        Player f1 = new Player("Saddie", "William","Senior", "Mathematics", 83);
        Player f2 = new Player("Isabella","Doe", "Junior", "Aerospace", 86);
        Player f3 = new Player("Paula", "Deen","Sophomore", "Computer Engineering", 95);
        Player f4 = new Player("Adeline","Bermudez", "Freshman", "Nursing", 88);
        Player f5 = new Player("Aubrie", "Chanel","Junior", "Law", 87);
        Player f6 = new Player("Allysa","Torres", "Freshman", "Humanities", 79);

        //Adding to the same apparatus
        f1.setApparatusStatus(ApparatusIndex.FX);
        f1.setApparatusOrder(ApparatusIndex.FX, 1);
        f2.setApparatusStatus(ApparatusIndex.FX);
        f2.setApparatusOrder(ApparatusIndex.FX, 3);
        f3.setApparatusStatus(ApparatusIndex.FX);
        f3.setApparatusOrder(ApparatusIndex.FX, 4);
        f4.setApparatusStatus(ApparatusIndex.FX);
        f4.setApparatusOrder(ApparatusIndex.FX, 2);
        f5.setApparatusStatus(ApparatusIndex.FX);
        f5.setApparatusOrder(ApparatusIndex.FX, 6);
        f6.setApparatusStatus(ApparatusIndex.FX);
        f6.setApparatusOrder(ApparatusIndex.FX, 5);

        team2.addGymnasts(f1);
        team2.addGymnasts(f2);
        team2.addGymnasts(f3);
        team2.addGymnasts(f4);
        team2.addGymnasts(f5);
        team2.addGymnasts(f6);

    }
}
