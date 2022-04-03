package com.company;
import java.util.ArrayList;
import java.util.List;

public class TeamScoreTester {
    public static void main(String[] args) {
       //data from SEC 2021 Championship
        //Team Alabama

        TeamScore AlabamaScore = new TeamScore();
        List<Double> vaultScores  = new ArrayList<>();
        List<Double> floorScores  = new ArrayList<>();
        List<Double> barsScores  = new ArrayList<>();
        List<Double> beamScores  = new ArrayList<>();

        floorScores.add(9.850);
        floorScores.add(9.825);
        floorScores.add(9.875);
        floorScores.add(9.750);
        floorScores.add(9.950);
        floorScores.add(9.950);
        double floorScore = AlabamaScore.calculateTeamApparatusScore(floorScores);
        AlabamaScore.setfloorScore(floorScore);
        System.out.println("Floor: " + AlabamaScore.getfloorScore());
        System.out.println("Running: " + AlabamaScore.getRunningScore());

        vaultScores.add(9.825);
        vaultScores.add(9.925);
        vaultScores.add(9.850);
        vaultScores.add(9.950);
        vaultScores.add(9.900);
        vaultScores.add(9.925);
        double vaultScore = AlabamaScore.calculateTeamApparatusScore(vaultScores);
        AlabamaScore.setvaultScore(vaultScore);
        System.out.println("Vault: " + AlabamaScore.getvaultScore());
        System.out.println("Running: " + AlabamaScore.getRunningScore());

        barsScores.add(9.800);
        barsScores.add(9.775);
        barsScores.add(9.750);
        barsScores.add(9.950);
        barsScores.add(9.950);
        barsScores.add(9.950);
        double barScore = AlabamaScore.calculateTeamApparatusScore(barsScores);
        AlabamaScore.setbarScore(barScore);
        System.out.println("Bar: " + AlabamaScore.getbarScore());
        System.out.println("Running: " + AlabamaScore.getRunningScore());

        beamScores.add(9.850);
        beamScores.add(9.850);
        beamScores.add(9.850);
        beamScores.add(9.950);
        beamScores.add(9.825);
        beamScores.add(9.950);
        double beamScore = AlabamaScore.calculateTeamApparatusScore(beamScores);
        AlabamaScore.setbeamScore(beamScore);
        System.out.println("Beam: " + AlabamaScore.getbeamScore());
        System.out.println("Running: " + AlabamaScore.getRunningScore());




    }

}
