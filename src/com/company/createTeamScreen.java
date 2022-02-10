package com.company;

import javax.swing.*;

public class createTeamScreen {

    private JPanel createTeamPanel;

    public createTeamScreen(){


    }




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
