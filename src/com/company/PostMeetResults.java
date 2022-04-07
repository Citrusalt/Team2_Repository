package com.company;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import Team.java;

public class PostMeetResults {
    private String meetType;
    private Team team1;
    private Team team2;
    private Team team3;
    private Team team4;
    private File resultsFile;
    private FileWriter resultsFileW;

    //constructors
    public PostMeetResults(String meetType, Team team1, Team team2, File resultsFile) throws IOException {
        this.meetType = meetType;
        this.team1 = team1;
        this.team2 = team2;
        this.team3 = null;
        this.team4 = null;
        this.resultsFile = resultsFile;
        this.resultsFileW = new FileWriter(resultsFile.toString());
    }

    public PostMeetResults(String meetType, Team team1, Team team2, Team team3, File resultsFile) throws IOException {
        this.meetType = meetType;
        this.team1 = team1;
        this.team2 = team2;
        this.team3 = team3;
        this.team4 = null;
        this.resultsFile = resultsFile;
        this.resultsFileW = new FileWriter(resultsFile.toString());
    }

    public PostMeetResults(String meetType, Team team1, Team team2, Team team3, Team team4, File resultsFile) throws IOException {
        this.meetType = meetType;
        this.team1 = team1;
        this.team2 = team2;
        this.team3 = team3;
        this.team4 = team4;
        this.resultsFile = resultsFile;
        this.resultsFileW = new FileWriter(resultsFile.toString());
    }

    //getters
    public File getResultsFile() {return resultsFile;}
    public String getMeetType() {return meetType;}
    public Team getTeam1() {return team1;}
    public Team getTeam2() {return team2;}
    public Team getTeam3() {return team3;}
    public Team getTeam4() {return team4;}

    //setters
    public void setMeetType(String meetType) {this.meetType = meetType;}
    public void setResultsFile(File resultsFile) {this.resultsFile = resultsFile;}
    public void setTeam1(Team team1) {this.team1 = team1;}
    public void setTeam2(Team team2) {this.team2 = team2;}
    public void setTeam3(Team team3) {this.team3 = team3;}
    public void setTeam4(Team team4) {this.team4 = team4;}

    //methods
    public void writeResultsToFile() throws IOException
    {
        resultsFileW.write("FINAL SCORES AND OVERALL PLACEMENT\n----------------------------------\n\n");

        try{
            addTeamStandingToFile();
            addTeamResultsToFile();
            addAllAroundToFile();
            addIndividualToFile();

            resultsFileW.close();

        } catch (IOException e){
            JOptionPane.showMessageDialog(null, "Error with creating results file.");
            e.printStackTrace();
        }
    }
    public void addTeamStandingToFile() throws IOException
    {
        resultsFileW.write("TEAM STANDING\n#\tTEAM\tVAULT\tBARS\tBEAM\tFLOOR\tFINAL\n\n");

           if (meetType == "dual")
            {
                Team[] teamStanding = {team1, team2};
                Arrays.sort(teamStanding, new sortByOverallScore());

                for (int i = 0; i < teamStanding.length; i++) {
                    resultsFileW.write((i + 1) + teamStanding[i].getTeamName() + teamStanding[i].getTeamScore().getvaultScore() + teamStanding[i].getTeamScore().getbarScore() + teamStanding[i].getTeamScore().getbeamScore() + teamStanding[i].getTeamScore().getfloorScore() + teamStanding[i].getTeamScore().getvaultScore() + (teamStanding[i].getTeamScore().getvaultScore() + teamStanding[i].getTeamScore().getbarScore() + teamStanding[i].getTeamScore().getbeamScore() + teamStanding[i].getTeamScore().getfloorScore() + teamStanding[i].getTeamScore().getvaultScore()));
                }
            }

            if (meetType == "tri")
            {
                Team[] teamStanding = {team1, team2, team3};
                Arrays.sort(teamStanding, new sortByOverallScore());

                for (int i = 0; i < teamStanding.length; i++) {
                    resultsFileW.write((i + 1) + teamStanding[i].getTeamName() + teamStanding[i].getTeamScore().getvaultScore() + teamStanding[i].getTeamScore().getbarScore() + teamStanding[i].getTeamScore().getbeamScore() + teamStanding[i].getTeamScore().getfloorScore() + teamStanding[i].getTeamScore().getvaultScore() + (teamStanding[i].getTeamScore().getvaultScore() + teamStanding[i].getTeamScore().getbarScore() + teamStanding[i].getTeamScore().getbeamScore() + teamStanding[i].getTeamScore().getfloorScore() + teamStanding[i].getTeamScore().getvaultScore()));
                }
            }

            if (meetType == "quad")
            {
                Team[] teamStanding = {team1, team2, team3, team4};
                Arrays.sort(teamStanding, new sortByOverallScore());

                for (int i = 0; i < teamStanding.length; i++) {
                    resultsFileW.write((i + 1) + teamStanding[i].getTeamName() + teamStanding[i].getTeamScore().getvaultScore() + teamStanding[i].getTeamScore().getbarScore() + teamStanding[i].getTeamScore().getbeamScore() + teamStanding[i].getTeamScore().getfloorScore() + teamStanding[i].getTeamScore().getvaultScore() + (teamStanding[i].getTeamScore().getvaultScore() + teamStanding[i].getTeamScore().getbarScore() + teamStanding[i].getTeamScore().getbeamScore() + teamStanding[i].getTeamScore().getfloorScore() + teamStanding[i].getTeamScore().getvaultScore()));
                }
            }

    }

    public void addTeamResultsToFile() throws IOException
    {
        resultsFileW.write("TEAM RESULTS DETAILS\n\n");
        resultsFileW.write("TEAM:");
        //print #1 team name
        resultsFileW.write("#\tNAME\tVAULT\tBARS\tBEAM\tFLOOR\tALLAROUND\n");
        /*
         * for every Player in Team
         *   print score for each apparatus
         */

        resultsFileW.write("TEAM:");
        //print #2 team name
        resultsFileW.write("#\tNAME\tVAULT\tBARS\tBEAM\tFLOOR\tALLAROUND\n");
        /*
         * for every Player in Team
         *   print score for each apparatus
         */

        resultsFileW.write("TEAM:");
        //print #3 team name
        resultsFileW.write("#\tNAME\tVAULT\tBARS\tBEAM\tFLOOR\tALLAROUND\n");
        /*
         * for every Player in Team
         *   print score for each apparatus
         */

        resultsFileW.write("TEAM:");
        //print #4 team name
        resultsFileW.write("#\tNAME\tVAULT\tBARS\tBEAM\tFLOOR\tALLAROUND\n");
        /*
         * for every Player in Team
         *   print score for each apparatus
         */
    }

    public void addAllAroundToFile() throws IOException
    {
        resultsFileW.write("ALL AROUND RESULTS\n\n");
        resultsFileW.write("PLACE\tNUM\tNAME\tTEAM\tVAULT\tBARS\tBEAM\tFLOOR\tSCORE\n");
        /*
         * print all around array and scores
         */
    }

    public void addIndividualToFile() throws IOException
    {
        addIndividualVaultToFile();
        addIndividualBarsToFile();
        addIndividualBeamToFile();
        addIndividualFloorToFile();
    }

    public void addIndividualVaultToFile() throws IOException
    {
        resultsFileW.write("INDIVIDUAL VAULT\n\n");
        resultsFileW.write("NAME\tSCORE\tTEAM\tSCORE\n");
        //print vault scores for all teams in order
    }

    public void addIndividualBarsToFile() throws IOException
    {
        resultsFileW.write("INDIVIDUAL BARS\n\n");
        resultsFileW.write("NAME\tSCORE\tTEAM\tSCORE\n");
        //print bars scores for all teams in order
    }

    public void addIndividualBeamToFile() throws IOException
    {
        resultsFileW.write("INDIVIDUAL BEAM\n\n");
        resultsFileW.write("NAME\tSCORE\tTEAM\tSCORE\n");
        //print beam scores for all teams in order
    }

    public void addIndividualFloorToFile() throws IOException
    {
        resultsFileW.write("INDIVIDUAL FLOOR\n\n");
        resultsFileW.write("NAME\tSCORE\tTEAM\tSCORE\n");
        //print floor scores for all teams in order
    }
}
