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

//alternative method of calculating score
        Player v1 = new Player("Janilou", "Senior", "Computer Science", 95);
        Player v2 = new Player("Jacob", "Junior", "Cybersecurity", 96);
        Player v3 = new Player("Hailey", "Sophomore", "Humanities", 95);
        Player v4 = new Player("Adriana", "Freshman", "Art", 98);
        Player v5 = new Player("Tyler", "Junior", "Business", 67);
        Player v6 = new Player("Hunter", "Freshman", "Law", 89);

        v1.getPlayerScore().setbeamScore(9.85);
        v2.getPlayerScore().setbeamScore(9.85);
        v3.getPlayerScore().setbeamScore(9.85);
        v4.getPlayerScore().setbeamScore(9.95);
        v5.getPlayerScore().setbeamScore(9.825);
        v6.getPlayerScore().setbeamScore(9.950);

        v1.getPlayerScore().setfloorScore(9.85);
        v2.getPlayerScore().setfloorScore(9.825);
        v3.getPlayerScore().setfloorScore(9.875);
        v4.getPlayerScore().setfloorScore(9.750);
        v5.getPlayerScore().setfloorScore(9.950);
        v6.getPlayerScore().setfloorScore(9.950);

        v1.getPlayerScore().setbarScore(9.8);
        v2.getPlayerScore().setbarScore(9.775);
        v3.getPlayerScore().setbarScore(9.750);
        v4.getPlayerScore().setbarScore(9.950);
        v5.getPlayerScore().setbarScore(9.950);
        v6.getPlayerScore().setbarScore(9.95);

        v1.getPlayerScore().setvaultScore(9.825);
        v2.getPlayerScore().setvaultScore(9.925);
        v3.getPlayerScore().setvaultScore(9.850);
        v4.getPlayerScore().setvaultScore(9.950);
        v5.getPlayerScore().setvaultScore(9.9);
        v6.getPlayerScore().setvaultScore(9.925);

        List<Player> playersList = new ArrayList<>();

        playersList.add(v1);
        playersList.add(v2);
        playersList.add(v3);
        playersList.add(v4);
        playersList.add(v5);
        playersList.add(v6);

        double beam = AlabamaScore.calculateTeamBeamScore(playersList);
        double vault = AlabamaScore.calculateTeamVaultScore(playersList);
        double floor =AlabamaScore.calculateTeamFloorScore(playersList);
        double bar =AlabamaScore.calculateTeamBarScore(playersList);

        System.out.println("\nAlternative scoring method");
        System.out.println("Bars : " + bar);
        System.out.println("Vault : " + vault);
        System.out.println("Floor : " + floor);
        System.out.println("Beam : " + beam);

    }

}
