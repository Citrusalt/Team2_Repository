package com.company;

import java.util.ArrayList;
import java.util.List;

public class Judge {
    private String fname;
    private String lname;
    private List<JudgeScore> scoreList = new ArrayList<>();


    //constructor
    public void Judge(String fname, String lname)
    {
        this.fname = fname;
        this.lname = lname;
    }


    //getters
    public String getFname(){return fname;}
    public String getLname(){return lname;}
    public List<JudgeScore> getScoreList(){return scoreList;}

    //setters
    public void setFname(String fname){this.fname = fname;}
    public void setLname(String lname){this.lname = lname;}
    //public void setScoreList(List<JudgeScore> scoreList){this.scoreList = scoreList;}

    //methods
    public void addScore(JudgeScore score)
    {
        scoreList.add(score);
    }

}
