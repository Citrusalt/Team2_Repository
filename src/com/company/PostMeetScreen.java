package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class PostMeetScreen {

    public PostMeetScreen(){

        JFrame frame = new JFrame ("Post-Meet Screen Prototype");
        frame.setContentPane(postMeetModePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        createTeamTable();
        createIndividualTable();

        testTable(); //for testing and demonstration


        endButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });
    }

    //the parameters of this will undoubtedly change to accept either team or player class instead
    public void addRowIndividualTable(int place, String name, String team, double score){
        Vector<String> row = new Vector<String>();
        row.add(String.valueOf(place));
        row.add(name);
        row.add(team);
        row.add(String.valueOf(score));
        individualModel.addRow(row);
    }

    //the parameters of this will undoubtedly change to accept either team or player class instead
    public void addRowTeamTable(int place, String name){
        Vector<String> row = new Vector<String>();
        row.add(String.valueOf(place));
        row.add(name);
        teamModel.addRow(row);
    }

    private void createTeamTable(){
        teamModel.addColumn("Place");
        teamModel.addColumn("Team");
        teamTable.setModel(teamModel);

    }

    private void createIndividualTable(){
        individualModel.addColumn("Place");
        individualModel.addColumn("Gymnast Name");
        individualModel.addColumn("Team");
        individualModel.addColumn("Score");
        individualTable.setModel(individualModel);
    }

    //Test function
    private void testTable(){

        addRowTeamTable(1, "UAH");
        addRowTeamTable(2, "Auburn");
        addRowTeamTable(3, "Alabama");
        addRowTeamTable(4, "LSU");

        addRowIndividualTable(1, "Jacob Drake", "UAH", 9.874);
        addRowIndividualTable(2, "Hailey Porter", "Auburn", 9.562);
        addRowIndividualTable(3, "Adriana Lanier", "Alabama", 9.423);
        addRowIndividualTable(4, "Janilou Sy", "LSU", 9.123);
        addRowIndividualTable(5, "John Smith", "Alabama", 8.567);
    }

    private DefaultTableModel teamModel = new DefaultTableModel();
    private DefaultTableModel individualModel = new DefaultTableModel();
    private JPanel postMeetModePanel;
    private JPanel dualPost;
    private JButton endButton;
    private JTable teamTable;
    private JTable individualTable;


}