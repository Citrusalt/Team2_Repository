package com.company;
//this class holds all apparatus data for a single team.
//getters and setters for each apparatus 
//method to calculate team score for each apparatus because the lowest scoring person per 
//apparatus is dropped. So a team apparatus is the top 5 scores
//method to get the running score for all apparatuses for the team
// 50 max points per apparatus 	(5 members * max 10 points per member) * 4 apparatuses = max 200 points

/*
 At each meet, six gymnasts are selected to compete in every event.
 The top five scores from each event are taken and counted toward the team’s 
 combined score. Then, the combined score from each event—vault, beam, floor,
 and bars—is added together to get the team’s final score.
 Therefore, the highest score possible score a team could have is 200.
 Source: https://www.ncsasports.org/college-gymnastics/college-gymnastics-levels
*/

import java.util.Collections;
import java.util.List;

public class TeamScore {


    private double vaultScore = 0;
    private double beamScore = 0;
    private double floorScore = 0;
    private double barScore = 0;

    public TeamScore() {
    }

    //setters
    public void setVaultScore(double vaultScore) {this.vaultScore =vaultScore;}
    public void setBeamScore(double beamScore) {this.beamScore = beamScore;}
    public void setFloorScore(double floorScore) {this.floorScore = floorScore;}
    public void setBarScore(double barScore) {this.barScore = barScore;}


    /*
    updaters
    not sure if these are necessary
    will the score board flash for a team after each individual person? if so these might be necessary
    */

    public void updateVault(double newVaultScore) {
        this.vaultScore += newVaultScore;
    }
    public void updateBeam(double newBeamScore) {
        this.beamScore += newBeamScore;
    }
    public void updateFloor(double newFloorScore) {
        this.floorScore += newFloorScore;
    }
    public void updateBar(double newBarScore) {
        this.barScore += newBarScore;
    }

    //getters
    public double getvaultScore() {
        return this.vaultScore;
    }
    public double getbeamScore() {
        return this.beamScore;
    }
    public double getfloorScore() {
        return this.floorScore;
    }
    public double getbarScore() {
        return this.barScore;
    }

    // A players running score is the total score from all apparatuses
    //Returns running score
    public double getRunningScore() {
        return this.floorScore + this.barScore + this.beamScore + this.vaultScore;
    }

//need to get the top 5 score from a total of 6 individual scores for a single apparatus
//these add up to get the team score for that apparatus
    public double calculateTeamApparatusScore(List<Double> scoresList) {
        double minScore = Collections.min(scoresList);
        double sum = 0;
        for (double i : scoresList)
            sum = sum + i;
        return sum - minScore;
    }

    //attempt at calculating score based on list of players
    /*public double calculateTeamVaultScore(List<Player> playersList) {
        Player minPlayer = playersList.stream()
                .min(Comparator.comparingDouble(Player.playerScore::getvaultScore))
                .get();
        double minScore = Collections.min(scoresList.get());
        double sum = 0;
        for (double i : scoresList)
            sum = sum + i;
        return sum - minScore;
    }*/
}