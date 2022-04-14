package com.company;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

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

        if (meetType.equals("dual"))
        {
            Team[] teamStanding = {team1, team2};
            Arrays.sort(teamStanding);

            for (int i = 0; i < teamStanding.length; i++) {
                resultsFileW.write((i + 1) + teamStanding[i].getTeamName() + teamStanding[i].getTeamScore().getvaultScore() + teamStanding[i].getTeamScore().getbarScore() + teamStanding[i].getTeamScore().getbeamScore() + teamStanding[i].getTeamScore().getfloorScore() + teamStanding[i].getTeamScore().getvaultScore() + (teamStanding[i].getTeamScore().getvaultScore() + teamStanding[i].getTeamScore().getbarScore() + teamStanding[i].getTeamScore().getbeamScore() + teamStanding[i].getTeamScore().getfloorScore() + teamStanding[i].getTeamScore().getvaultScore()));
            }
        }

        if (meetType.equals("tri"))
        {
            Team[] teamStanding = {team1, team2, team3};
            Arrays.sort(teamStanding);

            for (int i = 0; i < teamStanding.length; i++) {
                resultsFileW.write((i + 1) + teamStanding[i].getTeamName() + teamStanding[i].getTeamScore().getvaultScore() + teamStanding[i].getTeamScore().getbarScore() + teamStanding[i].getTeamScore().getbeamScore() + teamStanding[i].getTeamScore().getfloorScore() + teamStanding[i].getTeamScore().getvaultScore() + (teamStanding[i].getTeamScore().getvaultScore() + teamStanding[i].getTeamScore().getbarScore() + teamStanding[i].getTeamScore().getbeamScore() + teamStanding[i].getTeamScore().getfloorScore() + teamStanding[i].getTeamScore().getvaultScore()));
            }
        }

        if (meetType.equals("quad"))
        {
            Team[] teamStanding = {team1, team2, team3, team4};
            Arrays.sort(teamStanding);

            for (int i = 0; i < teamStanding.length; i++) {
                resultsFileW.write((i + 1) + teamStanding[i].getTeamName() + teamStanding[i].getTeamScore().getvaultScore() + teamStanding[i].getTeamScore().getbarScore() + teamStanding[i].getTeamScore().getbeamScore() + teamStanding[i].getTeamScore().getfloorScore() + teamStanding[i].getTeamScore().getvaultScore() + (teamStanding[i].getTeamScore().getvaultScore() + teamStanding[i].getTeamScore().getbarScore() + teamStanding[i].getTeamScore().getbeamScore() + teamStanding[i].getTeamScore().getfloorScore() + teamStanding[i].getTeamScore().getvaultScore()));
            }
        }

    }

    public void writeTeamResults(Team team1) throws IOException {
        for(int i = 0; i < team1.getAllGymnasts().size(); i++)
        {
            String firstName = team1.getAllGymnasts().get(i).getPlayerfName();
            String lastName = team1.getAllGymnasts().get(i).getPlayerlName();
            double vault = team1.getAllGymnasts().get(i).getPlayerScore().getvaultScore();
            double bars = team1.getAllGymnasts().get(i).getPlayerScore().getbarScore();
            double beam = team1.getAllGymnasts().get(i).getPlayerScore().getbeamScore();
            double floor = team1.getAllGymnasts().get(i).getPlayerScore().getfloorScore();
            double allAround = 0;

            if(team1.getAllGymnasts().get(i).getApparatusStatusByIndex(0) && team1.getAllGymnasts().get(i).getApparatusStatusByIndex(1) && team1.getAllGymnasts().get(i).getApparatusStatusByIndex(2) && team1.getAllGymnasts().get(i).getApparatusStatusByIndex(3))
            {
                allAround = team1.getAllGymnasts().get(i).getPlayerScore().getTotalScore();
            }

            resultsFileW.write( (i+1) + firstName + "\t" + lastName + "\t" + vault + "\t" + bars + "\t" + beam + "\t" + floor + "\t" + allAround);
        }
    }

    public void addTeamResultsToFile() throws IOException
    {
        resultsFileW.write("TEAM RESULTS DETAILS\n\n");
        resultsFileW.write("TEAM: " + team1.getTeamName());
        resultsFileW.write("#\tNAME\tVAULT\tBARS\tBEAM\tFLOOR\tALL-AROUND\n");

        writeTeamResults(team1);

        resultsFileW.write("TEAM: " + team2.getTeamName());
        resultsFileW.write("#\tNAME\tVAULT\tBARS\tBEAM\tFLOOR\tALL-AROUND\n");

        writeTeamResults(team2);

        if (meetType.equals("tri"))
        {
            resultsFileW.write("TEAM: " + team3.getTeamName());
            resultsFileW.write("#\tNAME\tVAULT\tBARS\tBEAM\tFLOOR\tALL-AROUND\n");
            writeTeamResults(team3);
        }

        if (meetType.equals("quad"))
        {
            resultsFileW.write("TEAM: " + team3.getTeamName());
            resultsFileW.write("#\tNAME\tVAULT\tBARS\tBEAM\tFLOOR\tALL-AROUND\n");

            writeTeamResults(team3);

            resultsFileW.write("TEAM: " + team4.getTeamName());
            resultsFileW.write("#\tNAME\tVAULT\tBARS\tBEAM\tFLOOR\tALL-AROUND\n");

            writeTeamResults(team4);
        }
    }

    public void addAllAroundToFile() throws IOException
    {
        resultsFileW.write("ALL AROUND RESULTS\n\n");
        resultsFileW.write("PLACE\tNAME\tTEAM\tVAULT\tBARS\tBEAM\tFLOOR\tSCORE\n");

        List<Player> AA_Gymnasts = new ArrayList<>();

        for (int i = 0; i < team1.getAllGymnasts().size(); i++)
        {
            if(team1.getAllGymnasts().get(i).getApparatusStatusByIndex(0) && team1.getAllGymnasts().get(i).getApparatusStatusByIndex(1) && team1.getAllGymnasts().get(i).getApparatusStatusByIndex(2) && team1.getAllGymnasts().get(i).getApparatusStatusByIndex(3))
            {
                AA_Gymnasts.add(team1.getAllGymnasts().get(i));
            }
        }

        for (int i = 0; i < team2.getAllGymnasts().size(); i++)
        {
            if(team2.getAllGymnasts().get(i).getApparatusStatusByIndex(0) && team2.getAllGymnasts().get(i).getApparatusStatusByIndex(1) && team2.getAllGymnasts().get(i).getApparatusStatusByIndex(2) && team2.getAllGymnasts().get(i).getApparatusStatusByIndex(3))
            {
                AA_Gymnasts.add(team2.getAllGymnasts().get(i));
            }
        }

        if (meetType.equals("tri"))
        {
            for (int i = 0; i < team3.getAllGymnasts().size(); i++)
            {
                if(team3.getAllGymnasts().get(i).getApparatusStatusByIndex(0) && team3.getAllGymnasts().get(i).getApparatusStatusByIndex(1) && team3.getAllGymnasts().get(i).getApparatusStatusByIndex(2) && team3.getAllGymnasts().get(i).getApparatusStatusByIndex(3))
                {
                    AA_Gymnasts.add(team3.getAllGymnasts().get(i));
                }
            }
        }

        if (meetType.equals("quad"))
        {
            for (int i = 0; i < team3.getAllGymnasts().size(); i++)
            {
                if(team3.getAllGymnasts().get(i).getApparatusStatusByIndex(0) && team3.getAllGymnasts().get(i).getApparatusStatusByIndex(1) && team3.getAllGymnasts().get(i).getApparatusStatusByIndex(2) && team3.getAllGymnasts().get(i).getApparatusStatusByIndex(3))
                {
                    AA_Gymnasts.add(team3.getAllGymnasts().get(i));
                }
            }

            for (int i = 0; i < team4.getAllGymnasts().size(); i++)
            {
                if(team4.getAllGymnasts().get(i).getApparatusStatusByIndex(0) && team4.getAllGymnasts().get(i).getApparatusStatusByIndex(1) && team4.getAllGymnasts().get(i).getApparatusStatusByIndex(2) && team4.getAllGymnasts().get(i).getApparatusStatusByIndex(3))
                {
                    AA_Gymnasts.add(team4.getAllGymnasts().get(i));
                }
            }
        }


        //sort aa_array
        AA_Gymnasts.sort((o1, o2) -> 0);

        for (int i = 0; i < AA_Gymnasts.size(); i++)
        {
            String fName = AA_Gymnasts.get(i).getPlayerfName();
            String lName = AA_Gymnasts.get(i).getPlayerlName();
            String teamName = "Invalid";
            double vault = AA_Gymnasts.get(i).getPlayerScore().getvaultScore();
            double bars = AA_Gymnasts.get(i).getPlayerScore().getbarScore();
            double beam = AA_Gymnasts.get(i).getPlayerScore().getbeamScore();
            double floor = AA_Gymnasts.get(i).getPlayerScore().getfloorScore();
            double overall = AA_Gymnasts.get(i).getPlayerScore().getTotalScore();


            if (team1.getAllGymnasts().contains(AA_Gymnasts.get(i)))
            {
                teamName = team1.getTeamName();
            }

            if (team2.getAllGymnasts().contains(AA_Gymnasts.get(i)))
            {
                teamName = team2.getTeamName();
            }

            if (team2.getAllGymnasts().contains(AA_Gymnasts.get(i)))
            {
                teamName = team2.getTeamName();
            }

            if (team2.getAllGymnasts().contains(AA_Gymnasts.get(i)))
            {
                teamName = team2.getTeamName();
            }

            resultsFileW.write((i+1) + "\t" + fName + "\t" + lName + "\t" + teamName + "\t" + vault + "\t" + bars + "\t" + beam + "\t" + floor + "\t" + overall);
        }
    }

    public void addIndividualVaultToFile() throws IOException
    {
        resultsFileW.write("INDIVIDUAL VAULT\n\n");
        resultsFileW.write("NAME\tSCORE\tTEAM\tSCORE\n");

        List<Player> vault_Gymnasts = new ArrayList<>();

        for (int i = 0; i < team1.getAllGymnasts().size(); i++)
        {
            if(team1.getAllGymnasts().get(i).getApparatusStatusByIndex(0))
            {
                vault_Gymnasts.add(team1.getAllGymnasts().get(i));
            }
        }

        for (int i = 0; i < team2.getAllGymnasts().size(); i++)
        {
            if(team2.getAllGymnasts().get(i).getApparatusStatusByIndex(0))
            {
                vault_Gymnasts.add(team2.getAllGymnasts().get(i));
            }
        }

        if (meetType.equals("tri"))
        {
            for (int i = 0; i < team3.getAllGymnasts().size(); i++)
            {
                if(team3.getAllGymnasts().get(i).getApparatusStatusByIndex(0))
                {
                    vault_Gymnasts.add(team3.getAllGymnasts().get(i));
                }
            }
        }

        if (meetType.equals("quad"))
        {
            for (int i = 0; i < team3.getAllGymnasts().size(); i++)
            {
                if(team3.getAllGymnasts().get(i).getApparatusStatusByIndex(0))
                {
                    vault_Gymnasts.add(team3.getAllGymnasts().get(i));
                }
            }

            for (int i = 0; i < team4.getAllGymnasts().size(); i++)
            {
                if(team4.getAllGymnasts().get(i).getApparatusStatusByIndex(0))
                {
                    vault_Gymnasts.add(team4.getAllGymnasts().get(i));
                }
            }
        }

        //sort vault_gymnasts
        vault_Gymnasts.sort((o1, o2) -> 0);

        for (int i = 0; i < vault_Gymnasts.size(); i++)
        {
            String fName = vault_Gymnasts.get(i).getPlayerfName();
            String lName = vault_Gymnasts.get(i).getPlayerlName();
            String teamName = "Invalid";
            double vault = vault_Gymnasts.get(i).getPlayerScore().getvaultScore();
            double overall = vault_Gymnasts.get(i).getPlayerScore().getTotalScore();


            if (team1.getAllGymnasts().contains(vault_Gymnasts.get(i)))
            {
                teamName = team1.getTeamName();
            }

            if (team2.getAllGymnasts().contains(vault_Gymnasts.get(i)))
            {
                teamName = team2.getTeamName();
            }

            if (team2.getAllGymnasts().contains(vault_Gymnasts.get(i)))
            {
                teamName = team2.getTeamName();
            }

            if (team2.getAllGymnasts().contains(vault_Gymnasts.get(i)))
            {
                teamName = team2.getTeamName();
            }

            resultsFileW.write((i+1) + "\t" + fName + "\t" + lName + "\t" + vault + "\t" + teamName + "\t" + overall);
        }
    }

    public void addIndividualBarsToFile() throws IOException
    {
        resultsFileW.write("INDIVIDUAL BARS\n\n");
        resultsFileW.write("NAME\tSCORE\tTEAM\tSCORE\n");

        List<Player> bar_Gymnasts = new ArrayList<>();

        for (int i = 0; i < team1.getAllGymnasts().size(); i++)
        {
            if(team1.getAllGymnasts().get(i).getApparatusStatusByIndex(1))
            {
                bar_Gymnasts.add(team1.getAllGymnasts().get(i));
            }
        }

        for (int i = 0; i < team2.getAllGymnasts().size(); i++)
        {
            if(team2.getAllGymnasts().get(i).getApparatusStatusByIndex(1))
            {
                bar_Gymnasts.add(team2.getAllGymnasts().get(i));
            }
        }

        if (meetType.equals("tri"))
        {
            for (int i = 0; i < team3.getAllGymnasts().size(); i++)
            {
                if(team3.getAllGymnasts().get(i).getApparatusStatusByIndex(1))
                {
                    bar_Gymnasts.add(team3.getAllGymnasts().get(i));
                }
            }
        }

        if (meetType.equals("quad"))
        {
            for (int i = 0; i < team3.getAllGymnasts().size(); i++)
            {
                if(team3.getAllGymnasts().get(i).getApparatusStatusByIndex(1))
                {
                    bar_Gymnasts.add(team3.getAllGymnasts().get(i));
                }
            }

            for (int i = 0; i < team4.getAllGymnasts().size(); i++)
            {
                if(team4.getAllGymnasts().get(i).getApparatusStatusByIndex(1))
                {
                    bar_Gymnasts.add(team4.getAllGymnasts().get(i));
                }
            }
        }

        //sort vault_gymnasts
        bar_Gymnasts.sort((o1, o2) -> 0);

        for (int i = 0; i < bar_Gymnasts.size(); i++)
        {
            String fName = bar_Gymnasts.get(i).getPlayerfName();
            String lName = bar_Gymnasts.get(i).getPlayerlName();
            String teamName = "Invalid";
            double bars = bar_Gymnasts.get(i).getPlayerScore().getvaultScore();
            double overall = bar_Gymnasts.get(i).getPlayerScore().getTotalScore();


            if (team1.getAllGymnasts().contains(bar_Gymnasts.get(i)))
            {
                teamName = team1.getTeamName();
            }

            if (team2.getAllGymnasts().contains(bar_Gymnasts.get(i)))
            {
                teamName = team2.getTeamName();
            }

            if (team2.getAllGymnasts().contains(bar_Gymnasts.get(i)))
            {
                teamName = team2.getTeamName();
            }

            if (team2.getAllGymnasts().contains(bar_Gymnasts.get(i)))
            {
                teamName = team2.getTeamName();
            }

            resultsFileW.write((i+1) + "\t" + fName + "\t" + lName + "\t" + bars + "\t" + teamName + "\t" + overall);
        }
    }

    public void addIndividualBeamToFile() throws IOException
    {
        resultsFileW.write("INDIVIDUAL BEAM\n\n");
        resultsFileW.write("NAME\tSCORE\tTEAM\tSCORE\n");
        List<Player> beam_Gymnasts = new ArrayList<>();

        for (int i = 0; i < team1.getAllGymnasts().size(); i++)
        {
            if(team1.getAllGymnasts().get(i).getApparatusStatusByIndex(2))
            {
                beam_Gymnasts.add(team1.getAllGymnasts().get(i));
            }
        }

        for (int i = 0; i < team2.getAllGymnasts().size(); i++)
        {
            if(team2.getAllGymnasts().get(i).getApparatusStatusByIndex(2))
            {
                beam_Gymnasts.add(team2.getAllGymnasts().get(i));
            }
        }

        if (meetType.equals("tri"))
        {
            for (int i = 0; i < team3.getAllGymnasts().size(); i++)
            {
                if(team3.getAllGymnasts().get(i).getApparatusStatusByIndex(2))
                {
                    beam_Gymnasts.add(team3.getAllGymnasts().get(i));
                }
            }
        }

        if (meetType.equals("quad"))
        {
            for (int i = 0; i < team3.getAllGymnasts().size(); i++)
            {
                if(team3.getAllGymnasts().get(i).getApparatusStatusByIndex(2))
                {
                    beam_Gymnasts.add(team3.getAllGymnasts().get(i));
                }
            }

            for (int i = 0; i < team4.getAllGymnasts().size(); i++)
            {
                if(team4.getAllGymnasts().get(i).getApparatusStatusByIndex(2))
                {
                    beam_Gymnasts.add(team4.getAllGymnasts().get(i));
                }
            }
        }

        //sort vault_gymnasts
        beam_Gymnasts.sort((o1, o2) -> 0);

        for (int i = 0; i < beam_Gymnasts.size(); i++)
        {
            String fName = beam_Gymnasts.get(i).getPlayerfName();
            String lName = beam_Gymnasts.get(i).getPlayerlName();
            String teamName = "Invalid";
            double beam = beam_Gymnasts.get(i).getPlayerScore().getvaultScore();
            double overall = beam_Gymnasts.get(i).getPlayerScore().getTotalScore();


            if (team1.getAllGymnasts().contains(beam_Gymnasts.get(i)))
            {
                teamName = team1.getTeamName();
            }

            if (team2.getAllGymnasts().contains(beam_Gymnasts.get(i)))
            {
                teamName = team2.getTeamName();
            }

            if (team2.getAllGymnasts().contains(beam_Gymnasts.get(i)))
            {
                teamName = team2.getTeamName();
            }

            if (team2.getAllGymnasts().contains(beam_Gymnasts.get(i)))
            {
                teamName = team2.getTeamName();
            }

            resultsFileW.write((i+1) + "\t" + fName + "\t" + lName + "\t" + beam + "\t" + teamName + "\t" + overall);
        }
    }

    public void addIndividualFloorToFile() throws IOException
    {
        resultsFileW.write("INDIVIDUAL FLOOR\n\n");
        resultsFileW.write("NAME\tSCORE\tTEAM\tSCORE\n");
        List<Player> fl_Gymnasts = new ArrayList<>();

        for (int i = 0; i < team1.getAllGymnasts().size(); i++)
        {
            if(team1.getAllGymnasts().get(i).getApparatusStatusByIndex(3))
            {
                fl_Gymnasts.add(team1.getAllGymnasts().get(i));
            }
        }

        for (int i = 0; i < team2.getAllGymnasts().size(); i++)
        {
            if(team2.getAllGymnasts().get(i).getApparatusStatusByIndex(3))
            {
                fl_Gymnasts.add(team2.getAllGymnasts().get(i));
            }
        }

        if (meetType.equals("tri"))
        {
            for (int i = 0; i < team3.getAllGymnasts().size(); i++)
            {
                if(team3.getAllGymnasts().get(i).getApparatusStatusByIndex(3))
                {
                    fl_Gymnasts.add(team3.getAllGymnasts().get(i));
                }
            }
        }

        if (meetType.equals("quad"))
        {
            for (int i = 0; i < team3.getAllGymnasts().size(); i++)
            {
                if(team3.getAllGymnasts().get(i).getApparatusStatusByIndex(3))
                {
                    fl_Gymnasts.add(team3.getAllGymnasts().get(i));
                }
            }

            for (int i = 0; i < team4.getAllGymnasts().size(); i++)
            {
                if(team4.getAllGymnasts().get(i).getApparatusStatusByIndex(3))
                {
                    fl_Gymnasts.add(team4.getAllGymnasts().get(i));
                }
            }
        }

        //sort vault_gymnasts
        fl_Gymnasts.sort((o1, o2) -> 0);

        for (int i = 0; i < fl_Gymnasts.size(); i++)
        {
            String fName = fl_Gymnasts.get(i).getPlayerfName();
            String lName = fl_Gymnasts.get(i).getPlayerlName();
            String teamName = "Invalid";
            double floor = fl_Gymnasts.get(i).getPlayerScore().getvaultScore();
            double overall = fl_Gymnasts.get(i).getPlayerScore().getTotalScore();


            if (team1.getAllGymnasts().contains(fl_Gymnasts.get(i)))
            {
                teamName = team1.getTeamName();
            }

            if (team2.getAllGymnasts().contains(fl_Gymnasts.get(i)))
            {
                teamName = team2.getTeamName();
            }

            if (team2.getAllGymnasts().contains(fl_Gymnasts.get(i)))
            {
                teamName = team2.getTeamName();
            }

            if (team2.getAllGymnasts().contains(fl_Gymnasts.get(i)))
            {
                teamName = team2.getTeamName();
            }

            resultsFileW.write((i+1) + "\t" + fName + "\t" + lName + "\t" + floor + "\t" + teamName + "\t" + overall);
        }
    }

    public void addIndividualToFile() throws IOException
    {
        addIndividualVaultToFile();
        addIndividualBarsToFile();
        addIndividualBeamToFile();
        addIndividualFloorToFile();
    }

    public void addJudgesInfoToFile() throws IOException
    {
        resultsFileW.write("Judges Information\n");

        /*
        for (int i = 0; i < allJudges; i++)
        {
            for (int j = 0; j < allJudges.get(i).getScores(); j++)
            {
                resultsFileW.write(judgeFirstName + judgeLastName + Player + apparatus + apparatusScore);
            }
        }
         */
    }
}
