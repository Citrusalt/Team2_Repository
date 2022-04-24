package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class PostMeetScreen {

    public PostMeetScreen(GuiCreator gC, List<Team> teams){

        JFrame frame = new JFrame ("Post-Meet Screen");
        frame.setContentPane(postMeetModePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        //placeholder functions
        gC.createTeamTablePost(teamTable, teamModel, teamRenderer, font, "Final Score");
        gC.createIndividualTableDual(individualTable, individualModel, individualRenderer, font);


        if (teams.size() == 2){

            Team home = teams.get(0);
            Team visitor1 = teams.get(1);

            dualTables(gC, teamModel, individualModel, home, visitor1);

            teamTable.getPreferredScrollableViewportSize().setSize(-1, -1);
            teamScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

            individualScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        }
        else if (teams.size() == 3){

        }
        else if (teams.size() == 4){

            quadTables(gC, teamModel, individualModel, teams);

        }
        else{
            System.out.println("Invalid Number of Teams");
        }


        endButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });
    }

    //Test function
    private void testTable(GuiCreator gC, DefaultTableModel teamModel, DefaultTableModel individualModel){

        gC.addRowTeamTablePost(1, "UAH",150, teamModel);
        gC.addRowTeamTablePost(2, "Auburn", 135, teamModel);
//        gC.addRowTeamTablePost(3, "Alabama", 124,  teamModel);
//        gC.addRowTeamTablePost(4, "LSU", 100, teamModel);

        gC.addRowIndividualTableDual(1, "Jacob Drake", 9.874, individualModel);
        gC.addRowIndividualTableDual(2, "Hailey Porter",  9.562, individualModel);
        gC.addRowIndividualTableDual(3, "Adriana Lanier", 9.423, individualModel);
        gC.addRowIndividualTableDual(4, "Janilou Sy", 9.123, individualModel);
        gC.addRowIndividualTableDual(5, "John Smith",  8.567, individualModel);
        gC.addRowIndividualTableDual(5, "John Smith",  8.567, individualModel);
        gC.addRowIndividualTableDual(5, "John Smith",  8.567, individualModel);
        gC.addRowIndividualTableDual(5, "John Smith",  8.567, individualModel);

    }


    //Test function
    private void dualTables(GuiCreator gC, DefaultTableModel teamModel, DefaultTableModel individualModel, Team home, Team visitor1){

        if (home.getTeamScore().getRunningScore() > visitor1.getTeamScore().getRunningScore()){
            gC.addRowTeamTablePost(1, home.getTeamName(), home.getTeamScore().getRunningScore(), teamModel);
            gC.addRowTeamTablePost(2, visitor1.getTeamName(), visitor1.getTeamScore().getRunningScore(), teamModel);
        }
        else
        {
            gC.addRowTeamTablePost(1, visitor1.getTeamName(), visitor1.getTeamScore().getRunningScore(), teamModel);
            gC.addRowTeamTablePost(2, home.getTeamName(), home.getTeamScore().getRunningScore(), teamModel);
        }

        List<Player> allAroundPlayers = new ArrayList<>();

        for (int i = 0; i < home.getAllGymnasts().size(); i++){
            if (gC.checkAllAround(home.getAllGymnasts().get(i))){
                allAroundPlayers.add(home.getAllGymnasts().get(i));
            }
        }
        for (int i = 0; i < visitor1.getAllGymnasts().size(); i++){
            if (gC.checkAllAround(visitor1.getAllGymnasts().get(i))){
                allAroundPlayers.add(visitor1.getAllGymnasts().get(i));
            }
        }

        //sort players by total score
        Collections.sort(allAroundPlayers, new Comparator<Player>() {
            @Override
            public int compare(Player p1, Player p2) {
                return Double.compare(p1.getPlayerScore().getTotalScore(), p2.getPlayerScore().getTotalScore());
            }
        });

        Collections.reverse(allAroundPlayers); //sort in descending order

        for (int i = 0; i < allAroundPlayers.size(); i++){
            gC.addRowIndividualTableDual(i + 1,allAroundPlayers.get(i).getPlayerfName() + " " + allAroundPlayers.get(i).getPlayerlName(), allAroundPlayers.get(i).getPlayerScore().getTotalScore(), individualModel);
        }
    }

    //Test function
    private void quadTables(GuiCreator gC, DefaultTableModel teamModel, DefaultTableModel individualModel, List<Team> teamList){

        List<Team>myTeamList = new ArrayList<>();
        List<Player>myPlayerList = new ArrayList<>();
        myTeamList = teamList;

        //sort list of teams by their running score
        Collections.sort(teamList, new Comparator<Team>() {
            @Override
            public int compare(Team o1, Team o2) {
                return Double.compare(o1.getTeamScore().getRunningScore(), o2.getTeamScore().getRunningScore());
            }
        });

        //reverse list in descending order
        Collections.reverse(teamList);


        //iterate through list of teams, iterate through players, if all around player, add to playerList
        for (Team t : teamList){
            for (Player p : t.getAllGymnasts()){
                if (gC.checkAllAround(p)){
                    myPlayerList.add(p);
                }
            }
        }

        //sort players by their total score
        Collections.sort(myPlayerList, new Comparator<Player>() {
            @Override
            public int compare(Player p1, Player p2) {
                return Double.compare(p1.getPlayerScore().getTotalScore(), p2.getPlayerScore().getTotalScore());
            }
        });


        //reverse list in descending order
        Collections.reverse(myPlayerList);

        for (int i = 0; i < teamList.size(); i++){
            gC.addRowTeamTablePost(i+1, teamList.get(i).getTeamName(), teamList.get(i).getTeamScore().getRunningScore(), teamModel);
        }



        for (int i = 0; i < myPlayerList.size(); i++){
            gC.addRowIndividualTableDual(i + 1,myPlayerList.get(i).getPlayerfName() + " " + myPlayerList.get(i).getPlayerlName(), myPlayerList.get(i).getPlayerScore().getTotalScore(), individualModel);
        }
    }


    //Table Header Font
    Font font = new Font ("Verdana", Font.PLAIN, 18);

    private DefaultTableModel teamModel = new DefaultTableModel();
    private DefaultTableModel individualModel = new DefaultTableModel();

    private DefaultTableCellRenderer teamRenderer = new DefaultTableCellRenderer();
    private DefaultTableCellRenderer individualRenderer = new DefaultTableCellRenderer();


    private JPanel postMeetModePanel;
    private JPanel dualPost;
    private JButton endButton;
    private JTable teamTable;
    private JTable individualTable;
    private JScrollPane teamScrollPane;
    private JScrollPane individualScrollPane;


}