package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IndividualScore {

    private double vaultScore = 0.0;
    private double beamScore = 0.0;
    private double floorScore = 0.0;
    private double barScore = 0.0;


    public IndividualScore() {
    }

    public void setvaultScore(double newScore) {
        this.vaultScore = newScore;
    }

    public void setbeamScore(double newScore) {
        this.beamScore = newScore;
    }

    public void setfloorScore(double newScore) {
        this.floorScore = newScore;
    }

    public void setbarScore(double newScore) {
        this.barScore = newScore;
    }

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
    public double getTotalScore() {
        return this.floorScore + this.barScore + this.beamScore + this.vaultScore;
    }

    //2 judges : average
    //4 or 6 judges : drop lowest and highest average remaining scores
    public double calculateIndividualScore(List<Double> scoresList, double neutralDeduction) {
        double minScore =  Collections.min(scoresList);
        double maxScore =  Collections.max(scoresList);
        double sum = 0.0;
        double Score =0.0;
        if (scoresList.size() == 2) {
            for (int i=0; i< scoresList.size(); i++) {
                sum += scoresList.get(i);
            }
            Score = sum / (scoresList.size())*1000;
            Score = Math.floor(Math.round(Score)) /1000;
        }
        else  if (scoresList.size() == 4 || scoresList.size() == 6){
            for (int i=0; i< scoresList.size(); i++) {
                sum += scoresList.get(i);
            }
            sum = sum - minScore - maxScore;
            Score = (sum / (scoresList.size() -2))*1000;
            Score = Math.floor(Math.round(Score)) /1000;
        }

        return Score -neutralDeduction;
    }
}
