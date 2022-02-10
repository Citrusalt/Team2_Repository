package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import com.company.TeamWriter;
import com.company.Player;


public class CreateTeamScreen {

    private JPanel createTeamPanel;
    private JTextField playerName4;
    private JTextField playerName5;
    private JTextField playerName3;
    private JTextField playerName6;
    private JTextField playerName2;
    private JTextField playerName1;
    private JTextField playerMajor1;
    private JTextField avg1;
    private JTextField teamSchoolName;
    private JButton createTeamButton;
    private JTextField playerClass1;
    private JTextField playerClass2;
    private JTextField playerClass3;
    private JTextField playerClass4;
    private JTextField playerClass5;
    private JTextField playerClass6;
    private JTextField playerMajor2;
    private JTextField playerMajor3;
    private JTextField playerMajor4;
    private JTextField playerMajor5;
    private JTextField playerMajor6;
    private JTextField avg2;
    private JTextField avg3;
    private JTextField avg4;
    private JTextField avg5;
    private JTextField avg6;

    List<Player> playerList = new ArrayList<>();
    TeamWriter myTeamWriter = new TeamWriter();

    public CreateTeamScreen(){


        createTeamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                fillPlayerInfo(); //fill playerList with Player objects filled from text fields

                myTeamWriter.createTeamFile(teamSchoolName.getText(), playerList);


            }
        });
    }


    private void fillPlayerInfo(){

        playerList.add(new Player(playerName1.getText(), playerClass1.getText(), playerMajor1.getText(), avg1.getText()));
        playerList.add(new Player(playerName2.getText(), playerClass2.getText(), playerMajor2.getText(), avg2.getText()));
        playerList.add(new Player(playerName3.getText(), playerClass3.getText(), playerMajor3.getText(), avg3.getText()));
        playerList.add(new Player(playerName4.getText(), playerClass4.getText(), playerMajor4.getText(), avg4.getText()));
        playerList.add(new Player(playerName5.getText(), playerClass5.getText(), playerMajor5.getText(), avg5.getText()));
        playerList.add(new Player(playerName6.getText(), playerClass6.getText(), playerMajor6.getText(), avg6.getText()));

    }

//    private boolean isEmpty(JTextField myTextField){
//
//        if (myTextField.getText() == ""){
//            return true;
//        }
//        else{
//            return false;
//        }
//
//    }


    //Constructor
    public void createTeamScreen(){

        //Should probably use Jdialog instead of another frame but eh

        JFrame frame = new JFrame ("Create Team Prototype");
        frame.setContentPane(createTeamPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}
