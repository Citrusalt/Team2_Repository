package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DualScorekeeperScreen {

    public DualScorekeeperScreen() {

        JFrame frame = new JFrame("Scorekeeper Screen Prototype");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


        DualArenaScreen myDualArenaScreen = new DualArenaScreen();


        //card layout start
        cardLayout = (CardLayout) mainPanel.getLayout();
        cardLayout.show(mainPanel, "startScreenCard");

        postMeetModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.dispose();

                PostMeetScreen myPostMeetScreen = new PostMeetScreen();

            }
        });
        defaultTemplateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                cardLayout.show(mainPanel, "ScorekeeperCard");
            }
        });

        startTimerButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (startTimerButton1.getText() == "Start Timer") {
                    try {
                        Integer.parseInt(timer1Textfield.getText());
                        if (Integer.parseInt(timer1Textfield.getText()) > 0) {
                            myDualArenaScreen.clock1(Integer.parseInt(timer1Textfield.getText()));
                            startTimerButton1.setText("Reset Timer");
                        }

                    } catch (Exception exception) {
                        System.out.println("Invalid Input");
                    }
                } else {
                    myDualArenaScreen.resetClock1();
                    startTimerButton1.setText("Start Timer");
                }
            }
        });
        startTimerButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (startTimerButton2.getText() == "Start Timer") {
                    try {
                        Integer.parseInt(timer2Textfield.getText());
                        if (Integer.parseInt(timer2Textfield.getText()) > 0) {
                            myDualArenaScreen.clock2(Integer.parseInt(timer2Textfield.getText()));
                            startTimerButton2.setText("Reset Timer");
                        }

                    } catch (Exception exception) {
                        System.out.println("Invalid Input");
                    }
                } else {
                    myDualArenaScreen.resetClock2();
                    startTimerButton2.setText("Start Timer");
                }
            }
        });


        timer1Textfield.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                timer1Textfield.setText("");
            }
        });
        timer2Textfield.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                timer2Textfield.setText("");
            }
        });
        nextRotationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateRotation(myDualArenaScreen, frame, 1);
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateRotation(myDualArenaScreen, frame, -1);
            }
        });

        team1Combo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED){
                    Object item = e.getItem();
                    myDualArenaScreen.updateGymnast(item.toString(), 1);
                }
            }

        });
        team2Combo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED){
                    Object item = e.getItem();
                    myDualArenaScreen.updateGymnast(item.toString(), 2);
                }
            }
        });
        updateScoreButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float scoreArray[] = new float[6];
                try{
                    //Use these scores to update score for backend and arena screen
                    scoreArray[0] = Integer.parseInt(j11.getText());
                    scoreArray[1] = Integer.parseInt(j12.getText());
                    scoreArray[2] = Integer.parseInt(j13.getText());
                    scoreArray[3] = Integer.parseInt(j14.getText());
                    scoreArray[4] = Integer.parseInt(j15.getText());
                    scoreArray[5] = Integer.parseInt(j16.getText());
                } catch (Exception exception) {

                    System.out.println(exception);
                }
            }
        });
        updateScoreButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                float scoreArray[] = new float[6];
                try{
                    //Use these scores to update score for backend and arena screen
                    scoreArray[0] = Integer.parseInt(j21.getText());
                    scoreArray[1] = Integer.parseInt(j22.getText());
                    scoreArray[2] = Integer.parseInt(j23.getText());
                    scoreArray[3] = Integer.parseInt(j24.getText());
                    scoreArray[4] = Integer.parseInt(j25.getText());
                    scoreArray[5] = Integer.parseInt(j26.getText());
                } catch (Exception exception) {

                    System.out.println(exception);
                }

            }
        });
    }

    //pass in the frames that need to be handled and 1 if next rotation, -1 if previous
    private void updateRotation(DualArenaScreen myArenaScreen, JFrame thisFrame, int value){

        rotation = rotation + value;
        myArenaScreen.updateRotation(rotation);

        if (rotation == 0){
            SetupModeDual myDualSetup = new SetupModeDual();
            myDualSetup.changeCard("SummaryCard");
            myArenaScreen.getFrame().dispose();
            thisFrame.dispose();
        }
        else if (rotation == 1){
            team1App.setText("Vault");
            team2App.setText("Bars");
            rotationLabel.setText("ROTATION 1");
            //update judges
            //update players
            //update scores
            //etc.
        }
        else if (rotation == 2){
            team1App.setText("Bars");
            team2App.setText("Vault");
            rotationLabel.setText("ROTATION 2");
            //update judges
            //update players
            //update scores
            //etc.
        }
        else if (rotation == 3){
            team1App.setText("Beam");
            team2App.setText("Floor");
            rotationLabel.setText("ROTATION 3");
        }
        else if (rotation == 4){
            team1App.setText("Floor");
            team2App.setText("Beam");
            rotationLabel.setText("ROTATION 4");
        }
        else if(rotation == 5){
            PostMeetScreen myPostMode = new PostMeetScreen();
            myArenaScreen.getFrame().dispose();
            thisFrame.dispose();
        }

    }


    private int rotation = 1;
    private JButton startTimerButton;
    private JTextField clockTextField;
    private JPanel mainPanel;
    private JButton postMeetModeButton;
    private JPanel scorekeeperScreen;
    private JPanel customizeScreen;
    private JButton defaultTemplateButton;
    private JButton customizeArenaButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;
    private JTextField textField10;
    private JTextField textField11;
    private JTextField textField12;
    private JTextField textField13;
    private JTextField textField14;
    private JTextField textField15;
    private JTextField textField16;
    private JTextField textField17;
    private JTextField textField18;
    private JTextField textField19;
    private JTextField textField20;
    private JTextField textField21;
    private JTextField textField22;
    private JTextField textField23;
    private JTextField textField24;
    private JComboBox comboBox1;
    private JTextField textField3;
    private JTextField textField4;
    private JPanel dualSimulScreen;
    private JTextField j11;
    private JTextField j21;
    private JComboBox team1Combo;
    private JTextField timer1Textfield;
    private JButton startTimerButton1;
    private JButton startTimerButton2;
    private JTextField timer2Textfield;
    private JButton backButton;
    private JButton nextRotationButton;
    private JLabel team1App;
    private JLabel team2App;
    private JLabel rotationLabel;
    private JButton updateScoreButton1;
    private JComboBox team2Combo;
    private JButton updateScoreButton2;
    private JTextField j13;
    private JTextField j12;
    private JTextField j14;
    private JTextField j15;
    private JTextField j16;
    private JTextField j22;
    private JTextField j23;
    private JTextField j24;
    private JTextField j25;
    private JTextField j26;
    private JButton editLineupButton;
    private CardLayout cardLayout;
}
