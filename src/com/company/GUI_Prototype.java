package com.company;

import javax.swing.*;

public class GUI_Prototype {


    private JPanel mainPanel;

    //Constructor
    public void createGUI(){

        JFrame frame = new JFrame ("Test GUI");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

}
