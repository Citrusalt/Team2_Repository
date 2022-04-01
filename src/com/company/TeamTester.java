package com.company;

import java.util.ArrayList;
import java.util.List;

public class TeamTester {
    Team sampleTeam;
    public TeamTester(){
        sampleTeam = new Team("UAH", "");
        //Create Instances of the player
        Player v1 = new Player("Janilou", "Senior", "Computer Science", 96);
        Player v2 = new Player("Jacob", "Junior", "Arts", 97);
        Player v3 = new Player("Hailey", "Sophomore", "Business", 92);
        Player v4 = new Player("Adriana", "Freshman", "Nursing", 90);
        Player v5 = new Player("Justin ", "Sophomore", "Mechanical Engineering", 64);
        Player v6 = new Player("Kevin", "Freshman", "Law", 74);

        //Adding the apparatus and their order
        v1.addApparatus(ApparatusStatus.VAULT);
        v1.printAll();

        v2.addApparatus(ApparatusStatus.BAR);
        v2.printAll();

        v3.addApparatus(ApparatusStatus.FLOOR);
        v3.printAll();

        v4.addApparatus(ApparatusStatus.BAR);
        v4.addApparatus(ApparatusStatus.BEAM);
        v4.printAll();

        v5.addApparatus(ApparatusStatus.VAULT);
        v6.addApparatus(ApparatusStatus.BAR);


        sampleTeam.addGymnasts(v1);
        sampleTeam.addGymnasts(v2);
        sampleTeam.addGymnasts(v3);
        sampleTeam.addGymnasts(v4);
        sampleTeam.addGymnasts(v5);
        sampleTeam.addGymnasts(v6);
    }
}
