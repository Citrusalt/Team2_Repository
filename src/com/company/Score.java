package com.company;
/*
 At each meet, six gymnasts are selected to compete in every event.
 The top five scores from each event are taken and counted toward the team’s c
 ombined score. Then, the combined score from each event—vault, beam, floor,
 and bars—is added together to get the team’s final score.
 Therefore, the highest score possible score a team could have is 200.
 Source: https://www.ncsasports.org/college-gymnastics/college-gymnastics-levels
*/

import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//import java.lang.Math;

public class Score {

    private double vaultScore;
    private double beamScore;
    private double floorScore;
    private double barScore;

    public Score() {
    }

    public void setvaultScore(double newScore)
    {
        this.vaultScore = newScore;
    }

    public void setbeamScore(double newScore)
    {
        this.beamScore = newScore;
    }

    public void setfloorScore(double newScore)
    {
        this.floorScore = newScore;
    }

    public void setbarScore(double newScore)
    {
        this.barScore = newScore;
    }

    // A players running score is the total score from all apparatuses
    //Returns running score
    public double getRunningScore(){
        return this.floorScore + this.barScore + this.beamScore + this.vaultScore;

    }



//need to get the top 5 score from a total of 6 individual scores for a single apparatus
//these add up to get the team score for that apparatus
//this will only be necessary for a team itself not an individual participant
//maybe make a separate type of score class for the team or have an abstract class

   /* public void calculateTeamApparatusScore(List<Double> scoresList) {
        double minScore =  Collections.min(scoresList);
        int TeamApparatusScore = Math.Sum(scoresList) - minScore;
    }*/

    public void calculateTeamApparatusScore(double score1, double score2, double score3, double score4, double score5, double score6 ) {
        double[] scores={score1, score2, score3, score3, score4, score5, score6};
        Arrays.sort(scores);
        double sum = 0;
        for (double value : scores) {
            sum += value;
        }
        //TeamApparatusScore = sum - score[0]

        }
    }