package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetupModeDual {



    public SetupModeDual(){

        JFrame frame = new JFrame ("Women's Gymnastics Scoreboard");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        //Card Layout start
        cardLayout = (CardLayout) mainPanel.getLayout();

        cardLayout.show(mainPanel, "startScreenCard");

        //GUI Setup
        limitJSpinners(); //limit ranges of order numbers


        //Go Back Button Action Listener
        vaultBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "TeamSetupCard");
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
                frame.dispose();
                MeetFormatScreen myMeetFormatScreen = new MeetFormatScreen("meetFormatCard");
            }
        });
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //cardLayout.show(mainPanel, "SelectVaultCard");

                //This verifies if the user selected a valid item
                Object homeTeam = comboBox7.getSelectedItem();
                Object visitorTeam = comboBox8.getSelectedItem();
                //Assuming that index 0 is default label for the comboBox
                if(comboBox7.getSelectedIndex() == 0 || comboBox7.getSelectedIndex() == 0 ){
                    JOptionPane.showMessageDialog(null, "Please Select a Valid Team.");
                }
                else if(homeTeam.toString().equals(visitorTeam.toString())){ //
                    JOptionPane.showMessageDialog(null, "Teams cannot be the same. Try Again.");
                }
                else {
                    cardLayout.show(mainPanel, "SelectVaultCard");
                }
            }
        });
        createTeamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    CreateTeamScreen myTeamScreen = new CreateTeamScreen(); //instantiate createTeamScreen Class
                    myTeamScreen.createTeamScreen(); //call constructor     //
            }
        });
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "SelectBarCard");
            }
        });
        barsBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "SelectVaultCard");
            }
        });
        barsNextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "SelectBalanceBeamCard");
            }
        });
        balanceBeamBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "SelectBarCard");
            }
        });
        balanceBeamNextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "SelectFloorCard");
            }
        });
        floorBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "SelectBalanceBeamCard");
            }
        });
        floorNextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "SelectJudgesCard");
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "SelectFloorCard");
            }
        });
        judgesNextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "SummaryCard");
            }
        });
        vaultEditButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "SelectVaultCard");
            }
        });
        barEditButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "SelectBarCard");
            }
        });
        editBeamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "SelectBalanceBeamCard");
            }
        });
        editFloorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "SelectFloorCard");
            }
        });
        editJudgesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "SelectJudgesCard");
            }
        });
        summaryContinueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.dispose();
                DualScorekeeperScreen myScorekeeper = new DualScorekeeperScreen();
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

    public void changeCard(String cardName){
        cardLayout.show(mainPanel, cardName);
    }


    private JPanel mainPanel;
    private JButton startButton;
    private JButton dualButton;
    private JButton triangularButton;
    private JButton quadrangularButton;
    private JPanel meetTemplate;
    private CardLayout cardLayout;
    private JLabel selectedMeetFormat;
    private JPanel setupVault;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JComboBox comboBox5;
    private JComboBox comboBox6;
    private JSpinner spinner1;
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
    private JComboBox comboBox7;
    private JComboBox comboBox8;
    private JButton goBackButton;
    private JButton continueButton;
    private JButton createTeamButton;
    private JButton nextButton;
    private JCheckBox thisApparatusRequiresACheckBox;
    private JPanel setupBars;
    private JPanel setupFloor;
    private JPanel setupBalanceBeam;
    private JButton barsBackButton;
    private JButton barsNextButton;
    private JButton balanceBeamBackButton;
    private JButton balanceBeamNextButton;
    private JButton floorBackButton;
    private JButton floorNextButton;
    private JPanel selectJudges;
    private JButton backButton;
    private JComboBox comboBox9;
    private JComboBox comboBox10;
    private JComboBox comboBox11;
    private JComboBox comboBox12;
    private JButton judgesNextButton;
    private JPanel summaryScreen;
    private JList list1;
    private JButton summaryContinueButton;
    private JButton vaultEditButton;
    private JButton barEditButton;
    private JButton editBeamButton;
    private JButton editFloorButton;
    private JButton editJudgesButton;
    private JPanel teamSetup;
    private JTextField clockTextField;
    private JButton startTimerButton;
    private JButton resetTimerButton;



}
