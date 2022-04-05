package com.company;

import java.util.ArrayList;
import java.util.List;

public class PlayerScoreTester {
    public static void main(String[] args) {
        PlayerScore ShaniaAdams = new PlayerScore();
        List<Double> barScores= new ArrayList<>();
        List<Double> vaultScores= new ArrayList<>();
        List<Double> floorScores=  new ArrayList<>();
        List<Double> beamScores=  new ArrayList<>();
        barScores.add(9.9);
        barScores.add(9.95);
        barScores.add(9.95);
        barScores.add(9.95);
        ShaniaAdams.setbarScore(ShaniaAdams.calculateIndividualScore(barScores, 0));

        vaultScores.add(9.85);
        vaultScores.add(9.80);
        vaultScores.add(9.80);
        vaultScores.add(9.85);
        ShaniaAdams.setvaultScore(ShaniaAdams.calculateIndividualScore(vaultScores, 0));

        floorScores.add(9.85);
        floorScores.add(9.85);
        floorScores.add(9.85);
        floorScores.add(9.80);
        ShaniaAdams.setfloorScore(ShaniaAdams.calculateIndividualScore(floorScores, 0.1));

        beamScores.add(9.85);
        beamScores.add(9.85);
        beamScores.add(9.85);
        beamScores.add(9.75);
        ShaniaAdams.setbeamScore(ShaniaAdams.calculateIndividualScore(beamScores, 0));

        System.out.println("Vault: " + ShaniaAdams.getvaultScore());
        System.out.println("Bar: " + ShaniaAdams.getbarScore());
        System.out.println("Beam: " + ShaniaAdams.getbeamScore());
        System.out.println("Floor: " + ShaniaAdams.getfloorScore());
        System.out.println("Total: " + ShaniaAdams.getTotalScore());

    }
}
