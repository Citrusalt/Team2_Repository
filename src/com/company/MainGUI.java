package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI {

    private JPanel mainPanel;
    private JPanel startScreen;
    private JLabel startImage;
    private JButton startButton;
    private JPanel meetSelect;
    private JButton dualButton;
    private JButton triangularButton;
    private JButton quadrangularButton;
    private JPanel meetTemplate;
    private CardLayout cardLayout;
    private JLabel selectedMeetFormat;


    public MainGUI(){


        cardLayout = (CardLayout) mainPanel.getLayout();

        cardLayout.show(mainPanel, "startScreenCard");

        System.out.println("Test message");


        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                cardLayout.show(mainPanel, "MeetSelectCard");
                System.out.println("Button Pressed");
            }
        });

        //Dual Meet Button Action Listener
        dualButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                cardLayout.show(mainPanel, "MeetTemplateCard");
                selectedMeetFormat.setText("Dual Meet Selected");


            }
        });

        //Triangular Meet Button Action Listener
        triangularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                cardLayout.show(mainPanel, "MeetTemplateCard");
                selectedMeetFormat.setText("Triangular Meet Selected");

            }
        });

        //Quadrangular Meet Button Action Listener
        quadrangularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                cardLayout.show(mainPanel, "MeetTemplateCard");
                selectedMeetFormat.setText("Quadrangular Meet Selected");

            }
        });
    }



    //Constructor
    public void createGUI(){

        JFrame frame = new JFrame ("GUI Prototype");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }


}
