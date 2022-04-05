package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class PostMeetScreen {

    public PostMeetScreen(GuiCreator gC){

        JFrame frame = new JFrame ("Post-Meet Screen");
        frame.setContentPane(postMeetModePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        //placeholder functions
        gC.createTeamTablePost(teamTable, teamModel, teamRenderer, font);
        gC.createIndividualTable(individualTable, individualModel, individualRenderer, font);

        testTable(gC, teamModel, individualModel); //for testing and demonstration


        endButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });
    }

    //Test function
    private void testTable(GuiCreator gC, DefaultTableModel teamModel, DefaultTableModel individualModel){


        gC.addRowTeamTablePost(1, "UAH", teamModel);
        gC.addRowTeamTablePost(2, "Auburn", teamModel);
        gC.addRowTeamTablePost(3, "Alabama", teamModel);
        gC.addRowTeamTablePost(4, "LSU", teamModel);

        gC.addRowIndividualTable(1, "Jacob Drake", "UAH", 9.874, individualModel);
        gC.addRowIndividualTable(2, "Hailey Porter", "Auburn", 9.562, individualModel);
        gC.addRowIndividualTable(3, "Adriana Lanier", "Alabama", 9.423, individualModel);
        gC.addRowIndividualTable(4, "Janilou Sy", "LSU", 9.123, individualModel);
        gC.addRowIndividualTable(5, "John Smith", "Alabama", 8.567, individualModel);
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