package com.company;

import java.util.HashMap;

public class TeamTester {
    static final int vault = 0;
    static final int bar = 1;
    static final int beam = 2;
    static final int floor = 3;
    public static void main(String[] args) {
        //Create Instances of the player
        Player v1 = new Player("Janilou", "Senior", "Computer Science", "95");
        Player v2 = new Player("Jacob", "Junior", "Computer Science", "93");
        Player v3 = new Player("Hailey", "Sophomore", "Computer Science", "92");
        Player v4 = new Player("Adriana", "Freshman", "Computer Science", "93");

        //Adding the apparatus and their order
        v1.playerApparatus.setApparatusStatus(bar);
        v1.playerApparatus.setOrderApparatus(bar, 2);
        v1.printAll();

        v2.playerApparatus.setApparatusStatus(beam);
        v2.playerApparatus.setOrderApparatus(beam, 1);
        v2.printAll();

        v3.playerApparatus.setApparatusStatus(floor);
        v3.playerApparatus.setOrderApparatus(floor, 1);
        v3.printAll();

        v4.playerApparatus.setApparatusStatus(vault);
        v4.playerApparatus.setApparatusStatus(bar);
        v4.playerApparatus.setOrderApparatus(bar, 1);
        v4.playerApparatus.setOrderApparatus(vault, 1);
        v4.printAll();
    }
}
