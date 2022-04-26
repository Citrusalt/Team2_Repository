package com.company;

public class JudgeScore {
    private String apparatusName;
    private Player player;
    private int attemptNum;
    private double scoreAmt;

    //constructor
    public void createScore(String apparatusName, Player player, int attemptNum, double scoreAmt)
    {
        this.apparatusName = apparatusName;
        this.player = player;
        this.attemptNum = attemptNum;
        this.scoreAmt = scoreAmt;
    }


    //getters
    public String getApparatusName() {return apparatusName;}
    public Player getPlayer() {return player;}
    public int getAttemptNum() {return attemptNum;}
    public double getScoreAmt() {return scoreAmt;}

    //setters

    //public void setApparatusName(String apparatusName) {this.apparatusName = apparatusName;}
    //public void setAttemptNum(int attemptNum) {this.attemptNum = attemptNum;}
    //public void setScoreAmt(double scoreAmt) {this.scoreAmt = scoreAmt;}
    public void setPlayer(Player player) {this.player = player;}
}
