package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        gC.createTeamTablePost(teamTable, teamModel, teamRenderer, font);
        gC.createIndividualTable(individualTable, individualModel, individualRenderer, font);


        if (teams.size() == 2){

            Team home = teams.get(0);
            Team visitor1 = teams.get(1);

            dualTable(gC, teamModel, individualModel, home, visitor1);

        }
        else{
            testTable(gC, teamModel, individualModel); //for testing and demonstration
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
        gC.addRowTeamTablePost(3, "Alabama", 124,  teamModel);
        gC.addRowTeamTablePost(4, "LSU", 100, teamModel);

        gC.addRowIndividualTable(1, "Jacob Drake", "UAH", 9.874, individualModel);
        gC.addRowIndividualTable(2, "Hailey Porter", "Auburn", 9.562, individualModel);
        gC.addRowIndividualTable(3, "Adriana Lanier", "Alabama", 9.423, individualModel);
        gC.addRowIndividualTable(4, "Janilou Sy", "LSU", 9.123, individualModel);
        gC.addRowIndividualTable(5, "John Smith", "Alabama", 8.567, individualModel);
    }

    //Test function
    private void dualTable(GuiCreator gC, DefaultTableModel teamModel, DefaultTableModel individualModel, Team home, Team visitor1){

        if (home.getTeamScore().getRunningScore() > visitor1.getTeamScore().getRunningScore()){
            gC.addRowTeamTablePost(1, home.getTeamName(), home.getTeamScore().getRunningScore(), teamModel);
            gC.addRowTeamTablePost(2, visitor1.getTeamName(), visitor1.getTeamScore().getRunningScore(), teamModel);
        }
        else
        {
            gC.addRowTeamTablePost(1, visitor1.getTeamName(), visitor1.getTeamScore().getRunningScore(), teamModel);
            gC.addRowTeamTablePost(2, home.getTeamName(), home.getTeamScore().getRunningScore(), teamModel);
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


}