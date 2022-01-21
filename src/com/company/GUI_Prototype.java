package com.company;

import javax.swing.*;

public class GUI_Prototype {


    private JPanel mainPanel;
    private JTextField helloTextField;

    //Constructor
    public void TestGUI(){

        JFrame frame = new JFrame ("Test GUI");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

}
