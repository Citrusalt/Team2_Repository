package com.company;

import java.util.HashMap;

public class TeamTester {

    public static void main(String[] args) {
        Player v1 = new Player("Janilou", "Senior", "Computer Science", "95");
        Player v2 = new Player("Jacob", "Junior", "Computer Science", "93");
        Player v3 = new Player("Hailey", "Sophomore", "Computer Science", "92");
        Player v4 = new Player("Adriana", "Freshman", "Computer Science", "93");

        Player f1 = new Player("Jlou", "Senior", "Sumo", "95");
        Player f2 = new Player("Jake", "Junior", "Karate", "95");
        Player f3 = new Player("Hai", "Junior", "Art", "95");
        Player f4 = new Player("Adria", "Junior", "Law", "95");

        Player br1 = new Player("Jamaye", "Senior", "Computer Science", "99");
        Player br2 = new Player("Jaco", "Junior", "Computer Science", "100");
        Player br3 = new Player("Ley", "Sophomore", "Art", "67");
        Player br4 = new Player("Ana", "Freshman", "Law", "23");

        Player bm1 = new Player("Jau", "Senior", "Computer Science", "95");
        Player bm2 = new Player("Jaco", "Senior", "Computer Science", "95");
        Player bm3 = new Player("Hail", "Senior", "Art", "95");
        Player bm4 = new Player("Adi", "Senior", "Law", "95");


        Team UAH = new Team();
        UAH.addPlayerVaults(v1, 3);
        UAH.addPlayerVaults(v2, 2);
        UAH.addPlayerVaults(v3, 1);
        UAH.addPlayerVaults(v4, 4);

        UAH.addPlayerFloors(f1, 3);
        UAH.addPlayerFloors(f2, 1);
        UAH.addPlayerFloors(f3, 2);
        UAH.addPlayerFloors(f4, 4);

        UAH.addPlayerBeam(bm1, 3);
        UAH.addPlayerBeam(bm2, 2);
        UAH.addPlayerBeam(bm3, 1);
        UAH.addPlayerBeam(bm4, 4);

        UAH.addPlayerBar(br1, 3);
        UAH.addPlayerBar(br2, 2);
        UAH.addPlayerBar(br3, 1);
        UAH.addPlayerBar(br4, 4);
        UAH.printAll();



    }
}
