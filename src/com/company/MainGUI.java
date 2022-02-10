package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI {

    public MainGUI(){

        //Card Layout start
        cardLayout = (CardLayout) mainPanel.getLayout();

        cardLayout.show(mainPanel, "startScreenCard");


        //GUI Setup
        limitJSpinners(); //limit ranges of order numbers


        //Action Listeners

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

                //cardLayout.show(mainPanel, "MeetTemplateCard");
                //cardLayout.show(mainPanel, "SelectVaultCard");
                //selectedMeetFormat.setText("Dual Meet Selected");

                cardLayout.show(mainPanel, "SetUpScreen2Card");


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

        //Go Back Button Action Listener
        vaultBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "SetUpScreen2Card");
            }
        });
        meetTemplateBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "MeetSelectCard");
            }
        });
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "MeetSelectCard");
            }
        });
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "SelectVaultCard");
            }
        });
        createTeamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                CreateTeamScreen myTeamScreen = new CreateTeamScreen(); //instantiate createTeamScreen Class
                myTeamScreen.createTeamScreen(); //call constructor

            }
        });
    }

    private void limitJSpinners(){

        int current = 0;
        int min = 0;
        int max = 6;
        int step = 1;


        //It ain't pretty, but it works
        //will most likely just remove anyways in favor of static numbers
        //no reason to overcomplicate these things
        SpinnerNumberModel spinnerModel1 = new SpinnerNumberModel(current, min, max, step);
        spinner1.setModel(spinnerModel1);
        SpinnerNumberModel spinnerModel2 = new SpinnerNumberModel(current, min, max, step);
        spinner2.setModel(spinnerModel2);
        SpinnerNumberModel spinnerModel3 = new SpinnerNumberModel(current, min, max, step);
        spinner3.setModel(spinnerModel3);
        SpinnerNumberModel spinnerModel4 = new SpinnerNumberModel(current, min, max, step);
        spinner4.setModel(spinnerModel4);
        SpinnerNumberModel spinnerModel5 = new SpinnerNumberModel(current, min, max, step);
        spinner5.setModel(spinnerModel5);
        SpinnerNumberModel spinnerModel6 = new SpinnerNumberModel(current, min, max, step);
        spinner6.setModel(spinnerModel6);
        SpinnerNumberModel spinnerModel7 = new SpinnerNumberModel(current, min, max, step);
        spinner7.setModel(spinnerModel7);
        SpinnerNumberModel spinnerModel8 = new SpinnerNumberModel(current, min, max, step);
        spinner8.setModel(spinnerModel8);
        SpinnerNumberModel spinnerModel9 = new SpinnerNumberModel(current, min, max, step);
        spinner9.setModel(spinnerModel9);
        SpinnerNumberModel spinnerModel10 = new SpinnerNumberModel(current, min, max, step);
        spinner10.setModel(spinnerModel10);
        SpinnerNumberModel spinnerModel11 = new SpinnerNumberModel(current, min, max, step);
        spinner11.setModel(spinnerModel11);
        SpinnerNumberModel spinnerModel12 = new SpinnerNumberModel(current, min, max, step);
        spinner12.setModel(spinnerModel12);

    }



    //Constructor
    public void createGUI(){

        JFrame frame = new JFrame ("GUI Prototype");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

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
    private JPanel setupVault;
    private JLabel AppLabel;
    private JButton uploadLogoButton;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JComboBox comboBox5;
    private JComboBox comboBox6;
    private JLabel selectGymNameLabel;
    private JSpinner spinner1;
    private JLabel selectOrderLabel;
    private JButton vaultBackButton;
    private JSpinner spinner2;
    private JSpinner spinner3;
    private JSpinner spinner4;
    private JSpinner spinner5;
    private JSpinner spinner6;
    private JButton meetTemplateBackButton;
    private JSpinner spinner7;
    private JSpinner spinner8;
    private JSpinner spinner9;
    private JSpinner spinner10;
    private JSpinner spinner11;
    private JSpinner spinner12;
    private JPanel setupScreen2;
    private JComboBox comboBox7;
    private JComboBox comboBox8;
    private JButton goBackButton;
    private JButton continueButton;
    private JButton createTeamButton;


}
