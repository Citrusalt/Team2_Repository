package com.company;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

//import static java.util.Arrays.sort;

public class PostMeetResults {
    private final String meetType;
    private final Team team1;
    private final Team team2;
    private final Team team3;
    private final Team team4;
    private FileWriter resultsFileW;
    private final List<List<Judge>> judges;


    //constructors
    public PostMeetResults(String meetType, Team team1, Team team2, File resultsFile, List<List<Judge>> judges) throws IOException {
        this.meetType = meetType;
        this.team1 = team1;
        this.team2 = team2;
        this.team3 = null;
        this.team4 = null;
        this.judges = judges;
        //        this.resultsFileW = new FileWriter(resultsFile.toString());
        try{
            this.resultsFileW = new FileWriter(resultsFile.toString());
        }catch(Exception e){
            System.out.println("There was an error in results file W");
        }

        writeResultsToFile();

    }

    public PostMeetResults(String meetType, Team team1, Team team2, Team team3, File resultsFile, List<List<Judge>> judges) throws IOException {
        this.meetType = meetType;
        this.team1 = team1;
        this.team2 = team2;
        this.team3 = team3;
        this.team4 = null;
        this.judges = judges;

        //this.resultsFileW = new FileWriter(resultsFile.toString());

        try{
            this.resultsFileW = new FileWriter(resultsFile.toString());
        }catch(Exception e){
            System.out.println("There was an error in results file W");
        }

        writeResultsToFile();
    }

    public PostMeetResults(String meetType, Team team1, Team team2, Team team3, Team team4, File resultsFile, List<List<Judge>> judges) throws IOException {
        this.meetType = meetType;
        this.team1 = team1;
        this.team2 = team2;
        this.team3 = team3;
        this.team4 = team4;
        this.judges = judges;

        //this.resultsFileW = new FileWriter(resultsFile.toString());

        try{
            this.resultsFileW = new FileWriter(resultsFile.toString());
        }catch(Exception e){
            System.out.println("There was an error in results file W");
        }

        writeResultsToFile();
    }

    //getters
    /*
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
    */

    //methods
    public void writeResultsToFile() throws IOException
    {
        resultsFileW.write("FINAL SCORES AND OVERALL PLACEMENT\n----------------------------------\n\n");

        try{
            addTeamStandingToFile();
            addTeamResultsToFile();
            addAllAroundToFile();
            addIndividualToFile();
            addJudgesToFile();

            resultsFileW.close();

        } catch (IOException e){
            JOptionPane.showMessageDialog(null, "Error with creating results file.");
            e.printStackTrace();
        }
    }

    public void addTeamStandingToFile() throws IOException
    {
        resultsFileW.write("TEAM STANDING\n----------------------------------\n");
        resultsFileW.write(String.format("%8s %8s %8s %8s %8s %8s %8s\n", "#", "TEAM", "VAULT", "BARS", "BEAM", "FLOOR", "FINAL"));

        if (meetType.equals("dual"))
        {
            ArrayList<Team> teamStanding = new ArrayList<>();
            teamStanding.add(team1);
            teamStanding.add(team2);

            Collections.sort(teamStanding, new Comparator<>() {
                @Override
                public int compare(Team t1, Team t2) {
                    return Double.compare(t1.getTeamScore().getRunningScore(), t2.getTeamScore().getRunningScore());
                }
            });

            for (int i = 0; i < teamStanding.size(); i++) {
                String teamName = teamStanding.get(i).getTeamName();
                double vaultScore = teamStanding.get(i).getTeamScore().getvaultScore();
                double barScore = teamStanding.get(i).getTeamScore().getbarScore();
                double beamScore = teamStanding.get(i).getTeamScore().getbeamScore();
                double floorScore = teamStanding.get(i).getTeamScore().getfloorScore();
                double overall = vaultScore + barScore + beamScore + floorScore;

                resultsFileW.write(String.format("%8s %8s %8s %8s %8s %8s %8s\n", (i + 1), teamName, vaultScore, barScore, beamScore, floorScore, overall));
            }
        }

        if (meetType.equals("tri"))
        {
            ArrayList<Team> teamStanding = new ArrayList<>();
            teamStanding.add(team1);
            teamStanding.add(team2);
            teamStanding.add(team3);

            Collections.sort(teamStanding, new Comparator<>() {
                @Override
                public int compare(Team t1, Team t2) {
                    return Double.compare(t1.getTeamScore().getRunningScore(), t2.getTeamScore().getRunningScore());
                }
            });

            for (int i = 0; i < teamStanding.size(); i++)
            {
                String teamName = teamStanding.get(i).getTeamName();
                double vaultScore = teamStanding.get(i).getTeamScore().getvaultScore();
                double barScore = teamStanding.get(i).getTeamScore().getbarScore();
                double beamScore = teamStanding.get(i).getTeamScore().getbeamScore();
                double floorScore = teamStanding.get(i).getTeamScore().getfloorScore();
                double overall = vaultScore + barScore + beamScore + floorScore;

                resultsFileW.write(String.format("%8s %8s %8s %8s %8s %8s %8s\n", (i + 1) , teamName , vaultScore , barScore , beamScore ,floorScore , overall));
            }
        }

        if (meetType.equals("quad"))
        {
            ArrayList<Team> teamStanding = new ArrayList<>();
            teamStanding.add(team1);
            teamStanding.add(team2);
            teamStanding.add(team3);
            teamStanding.add(team4);

            Collections.sort(teamStanding, new Comparator<>() {
                @Override
                public int compare(Team t1, Team t2) {
                    return Double.compare(t1.getTeamScore().getRunningScore(), t2.getTeamScore().getRunningScore());
                }
            });

            for (int i = 0; i < teamStanding.size(); i++)
            {
                String teamName = teamStanding.get(i).getTeamName();
                double vaultScore = teamStanding.get(i).getTeamScore().getvaultScore();
                double barScore = teamStanding.get(i).getTeamScore().getbarScore();
                double beamScore = teamStanding.get(i).getTeamScore().getbeamScore();
                double floorScore = teamStanding.get(i).getTeamScore().getfloorScore();
                double overall = vaultScore + barScore + beamScore + floorScore;

                resultsFileW.write(String.format("%8s %8s %8s %8s %8s %8s %8s\n", (i + 1) , teamName , vaultScore , barScore , beamScore, floorScore ,overall ));
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

            resultsFileW.write( String.format("%8s %8s %8s %8s %8s %8s %8s %8s\n", (i+1) ,firstName , lastName , vault , bars , beam , floor , allAround ));
        }
    }

    public void addTeamResultsToFile() throws IOException
    {
        resultsFileW.write("\nTEAM RESULTS DETAILS\n----------------------------------\n");
        resultsFileW.write("\nTEAM: " + team1.getTeamName() + "\n");
        resultsFileW.write(String.format("%8s %8s %8s %8s %8s %8s %8s %8s\n", "#", "FNAME", "LNAME", "VAULT", "BARS", "BEAM", "FLOOR", "ALL-AROUND"));

        writeTeamResults(team1);

        resultsFileW.write("\nTEAM: " + team2.getTeamName() + "\n");
        resultsFileW.write(String.format("%8s %8s %8s %8s %8s %8s %8s %8s\n", "#", "FNAME", "LNAME", "VAULT", "BARS", "BEAM", "FLOOR", "ALL-AROUND"));

        writeTeamResults(team2);

        if (meetType.equals("tri"))
        {
            resultsFileW.write("\nTEAM: " + team3.getTeamName() + "\n");
            resultsFileW.write(String.format("%8s %8s %8s %8s %8s %8s %8s %8s\n", "#", "FNAME", "LNAME", "VAULT", "BARS", "BEAM", "FLOOR", "ALL-AROUND"));
            writeTeamResults(team3);
        }

        if (meetType.equals("quad"))
        {
            resultsFileW.write("\nTEAM: " + team3.getTeamName() + "\n");
            resultsFileW.write(String.format("%8s %8s %8s %8s %8s %8s %8s %8s\n", "#", "FNAME", "LNAME", "VAULT", "BARS", "BEAM", "FLOOR", "ALL-AROUND"));

            writeTeamResults(team3);

            resultsFileW.write("\nTEAM: " + team4.getTeamName() + "\n");
            resultsFileW.write(String.format("%8s %8s %8s %8s %8s %8s %8s %8s\n", "#", "FNAME", "LNAME", "VAULT", "BARS", "BEAM", "FLOOR", "ALL-AROUND"));

            writeTeamResults(team4);
        }
    }

    public void addAllAroundToFile() throws IOException
    {
        resultsFileW.write("\nALL AROUND RESULTS\n----------------------------------\n");
        resultsFileW.write(String.format("%8s %8s %8s %8s %8s %8s %8s %8s %8s\n", "#", "FNAME", "LNAME", "TEAM", "VAULT", "BARS", "BEAM", "FLOOR", "SCORE"));

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

            resultsFileW.write(String.format("%8s %8s %8s %8s %8s %8s %8s %8s %8s\n", (i+1) ,fName , lName , teamName , vault ,bars , beam , floor , overall));
        }
    }

    public void addIndividualVaultToFile() throws IOException
    {
        resultsFileW.write("\nINDIVIDUAL VAULT\n----------------------------------\n");
        resultsFileW.write(String.format("%8s %8s %8s %8s %8s %8s\n", "#", "FNAME", "LNAME", "SCORE", "TEAM", "SCORE"));

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

            resultsFileW.write(String.format("%8s %8s %8s %8s %8s %8s\n", (i+1) , fName , lName , vault, teamName , overall));
        }
    }

    public void addIndividualBarsToFile() throws IOException
    {
        resultsFileW.write("\nINDIVIDUAL BARS\n----------------------------------\n");
        resultsFileW.write(String.format("%8s %8s %8s %8s %8s \n","#", "FNAME", "LNAME", "SCORE", "TEAM", "SCORE"));

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

            resultsFileW.write(String.format("%8s %8s %8s %8s %8s \n", (i+1) ,fName , lName , bars , teamName , overall));
        }
    }

    public void addIndividualBeamToFile() throws IOException
    {
        resultsFileW.write("\nINDIVIDUAL BEAM\n----------------------------------\n");
        resultsFileW.write(String.format("%8s %8s %8s %8s %8s %8s\n", "#", "FNAME", "LNAME", "SCORE", "TEAM", "TOTAL_SCORE"));
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

            resultsFileW.write(String.format("%8s %8s %8s %8s %8s %8s\n", (i+1) ,fName ,lName , beam, teamName , overall));
        }
    }

    public void addIndividualFloorToFile() throws IOException
    {
        resultsFileW.write("\nINDIVIDUAL FLOOR\n----------------------------------\n");
        resultsFileW.write(String.format("%8s %8s %8s %8s %8s %8s\n", "#", "FNAME", "LNAME", "SCORE", "TEAM", "TOTAL_SCORE"));
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

            resultsFileW.write(String.format("%8s %8s %8s %8s %8s %8s\n", (i+1) ,fName,lName , floor , teamName , overall));
        }
    }

    public void addIndividualToFile() throws IOException
    {
        addIndividualVaultToFile();
        addIndividualBarsToFile();
        addIndividualBeamToFile();
        addIndividualFloorToFile();
    }

    public void addJudgesToFile() throws IOException
    {
        resultsFileW.write("\nJUDGES SCORES\n----------------------------------\n");
        resultsFileW.write(String.format("%8s %8s %8s %8s %8s %8s\n", "JUDGE_NAME", "PLAYER_FNAME", "PLAYER_LNAME", "APPAR", "ATPT#", "SCORE"));
        for (List<Judge> judge : judges) {
            for (Judge value : judge) {
                for (int k = 0; k < value.getScoreList().size(); k++) {
                    String judgeName = value.getFname();
                    String playerFname = value.getScoreList().get(k).getPlayer().getPlayerfName();
                    String playerLname = value.getScoreList().get(k).getPlayer().getPlayerlName();
                    String apparatus = value.getScoreList().get(k).getApparatusName();
                    int attemptNum = value.getScoreList().get(k).getAttemptNum();
                    double score = value.getScoreList().get(k).getScoreAmt();
                    resultsFileW.write(String.format("%8s %8s %8s %8s %8s %8s\n", judgeName , playerFname , playerLname , apparatus , attemptNum , score ));
                }

            }
        }
    }

}
