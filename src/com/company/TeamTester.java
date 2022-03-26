package com.company;

public class TeamTester {
    public static void main(String[] args) {
        //Create Instances of the player
        Player v1 = new Player("Janilou", "Senior", "Computer Science", "95");
        Player v2 = new Player("Jacob", "Junior", "Computer Science", "93");
        Player v3 = new Player("Hailey", "Sophomore", "Computer Science", "92");
        Player v4 = new Player("Adriana", "Freshman", "Computer Science", "93");

        //Adding the apparatus and their order
        v1.playerApparatus.setApparatusStatus(ApparatusIndex.UB);
        v1.playerApparatus.setOrderApparatus(ApparatusIndex.UB, 2);
        v1.printAll();

        v2.playerApparatus.setApparatusStatus(ApparatusIndex.BB);
        v2.playerApparatus.setOrderApparatus(ApparatusIndex.BB, 1);
        v2.printAll();

        v3.playerApparatus.setApparatusStatus(ApparatusIndex.FX);
        v3.playerApparatus.setOrderApparatus(ApparatusIndex.FX, 1);
        v3.printAll();

        v4.playerApparatus.setApparatusStatus(ApparatusIndex.VT);
        v4.playerApparatus.setApparatusStatus(ApparatusIndex.UB);
        v4.playerApparatus.setOrderApparatus(ApparatusIndex.UB, 1);
        v4.playerApparatus.setOrderApparatus(ApparatusIndex.VT, 1);
        v4.printAll();


    }
}
