package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TriScorekeeperScreen {

    public TriScorekeeperScreen(GuiCreator gC){

        JFrame frame = new JFrame("Triangular Scorekeeper Screen Prototype");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        Dual_Tri_ArenaScreen myDualTriArenaScreen = new Dual_Tri_ArenaScreen();
        myDualTriArenaScreen.getFrame().setVisible(true);


        //card layout start
        cardLayout = (CardLayout) mainPanel.getLayout();
        changeCard("CustomizeCard");

        defaultTemplateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeCard("TriScorekeeperCard");
                myDualTriArenaScreen.getFrame().setVisible(true);
                updateRotation(myDualTriArenaScreen, frame, 0, gC);
            }
        });

        startTimerButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (startTimerButton1.getText() == "Start Timer") {
                    try {
                        Integer.parseInt(timer1Textfield.getText());
                        if (Integer.parseInt(timer1Textfield.getText()) > 0) {
                            myDualTriArenaScreen.clock1(Integer.parseInt(timer1Textfield.getText()));
                            startTimerButton1.setText("Reset Timer");
                        }

                    } catch (Exception exception) {
                        System.out.println("Invalid Input");
                    }
                } else {
                    myDualTriArenaScreen.resetClock1();
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
                            myDualTriArenaScreen.clock2(Integer.parseInt(timer2Textfield.getText()));
                            startTimerButton2.setText("Reset Timer");
                        }

                    } catch (Exception exception) {
                        System.out.println("Invalid Input");
                    }
                } else {
                    myDualTriArenaScreen.resetClock2();
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
                updateRotation(myDualTriArenaScreen, frame, 1, gC);
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateRotation(myDualTriArenaScreen, frame, -1, gC);
            }
        });

        team1Combo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED){
                    Object item = e.getItem();
                    myDualTriArenaScreen.updateGymnast(item.toString(), 1);
                }
            }

        });
        team2Combo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED){
                    Object item = e.getItem();
                    myDualTriArenaScreen.updateGymnast(item.toString(), 2);
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

        timerCheckbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED){
                    myDualTriArenaScreen.clockLabel1.setVisible(true);
                    myDualTriArenaScreen.clockLabel2.setVisible(true);
                }
                else{
                    myDualTriArenaScreen.clockLabel1.setVisible(false);
                    myDualTriArenaScreen.clockLabel2.setVisible(false);
                }
            }
        });
        nameCheckbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED){
                    myDualTriArenaScreen.name1.setVisible(true);
                    myDualTriArenaScreen.name2.setVisible(true);
                }
                else{
                    myDualTriArenaScreen.name1.setVisible(false);
                    myDualTriArenaScreen.name2.setVisible(false);
                }
            }
        });
        majorCheckbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED){
                    myDualTriArenaScreen.major1.setVisible(true);
                    myDualTriArenaScreen.major2.setVisible(true);
                }
                else{
                    myDualTriArenaScreen.major1.setVisible(false);
                    myDualTriArenaScreen.major2.setVisible(false);
                }
            }
        });
        yearCheckbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED){
                    myDualTriArenaScreen.year1.setVisible(true);
                    myDualTriArenaScreen.year2.setVisible(true);
                }
                else{
                    myDualTriArenaScreen.year1.setVisible(false);
                    myDualTriArenaScreen.year2.setVisible(false);
                }
            }
        });
        avgCheckbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED){
                    myDualTriArenaScreen.avg1.setVisible(true);
                    myDualTriArenaScreen.avg2.setVisible(true);
                }
                else{
                    myDualTriArenaScreen.avg1.setVisible(false);
                    myDualTriArenaScreen.avg2.setVisible(false);
                }
            }
        });
        currentScoreCheckbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED){
                    myDualTriArenaScreen.gymnastCurrent1.setVisible(true);
                    myDualTriArenaScreen.gymnastCurrent2.setVisible(true);
                }
                else{
                    myDualTriArenaScreen.gymnastCurrent1.setVisible(false);
                    myDualTriArenaScreen.gymnastCurrent2.setVisible(false);
                }
            }
        });
        teamScoreCheckbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED){
                    myDualTriArenaScreen.overall1.setVisible(true);
                    myDualTriArenaScreen.overall2.setVisible(true);
                }
                else{
                    myDualTriArenaScreen.overall1.setVisible(false);
                    myDualTriArenaScreen.overall2.setVisible(false);
                }
            }
        });
        pictureCheckbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED){
                    myDualTriArenaScreen.pic1.setVisible(true);
                    myDualTriArenaScreen.pic2.setVisible(true);
                }
                else{
                    myDualTriArenaScreen.pic1.setVisible(false);
                    myDualTriArenaScreen.pic2.setVisible(false);
                }
            }
        });
    }


    public void changeCard(String cardName){
        cardLayout.show(mainPanel, cardName);
    }


    //pass in the frames that need to be handled and 1 if next rotation, -1 if previous
    //yes, a lot of this is redundant, but it's easy to read and change later
    private void updateRotation(Dual_Tri_ArenaScreen myArenaScreen, JFrame thisFrame, int value, GuiCreator gC){

        rotation = rotation + value;
        myArenaScreen.updateRotation(rotation);

        if (rotation == 0){
            SetupModeTri myTriSetup = new SetupModeTri(gC);
            myTriSetup.changeCard("SummaryCard");
            myArenaScreen.getFrame().dispose();
            thisFrame.dispose();
        }
        else if (rotation == 1){
            team1App.setText("Vault");
            team2App.setText("Bars");
            team1Name.setText("Home");
            team2Name.setText("Visitor 1");
            myArenaScreen.updateEvent("Vault", 1);
            myArenaScreen.updateEvent("Bars", 2);
            myArenaScreen.updateTeamName("Home", 1); //change to actual school names later
            myArenaScreen.updateTeamName("Visitor 1", 2); //""
            rotationLabel.setText("ROTATION 1");
            //update judges
            //update players
            //update scores
            //change combo boxes
            //etc.
        }
        else if (rotation == 2){
            team1App.setText("Vault");
            team2App.setText("Bars");
            team1Name.setText("Visitor 2");
            team2Name.setText("Home");
            myArenaScreen.updateEvent("Vault", 1);
            myArenaScreen.updateEvent("Bars", 2);
            myArenaScreen.updateTeamName("Visitor 2", 1); //change to actual school names later
            myArenaScreen.updateTeamName("Home", 2); //""
            rotationLabel.setText("ROTATION 2");
            //update judges
            //update players
            //update scores
            //change combo boxes
            //etc.
        }
        else if (rotation == 3){
            team1App.setText("Vault");
            team2App.setText("Bars");
            team1Name.setText("Visitor 1");
            team2Name.setText("Visitor 2");
            myArenaScreen.updateEvent("Vault", 1);
            myArenaScreen.updateEvent("Bars", 2);
            myArenaScreen.updateTeamName("Visitor 1", 1); //change to actual school names later
            myArenaScreen.updateTeamName("Visitor 2", 2); //""
            rotationLabel.setText("ROTATION 3");
        }
        else if (rotation == 4){
            team1App.setText("Beam");
            team2App.setText("Floor");
            team1Name.setText("Home");
            team2Name.setText("Visitor 2");
            myArenaScreen.updateEvent("Beam", 1);
            myArenaScreen.updateEvent("Floor", 2);
            myArenaScreen.updateTeamName("Home", 1); //change to actual school names later
            myArenaScreen.updateTeamName("Visitor 2", 2); //""
            rotationLabel.setText("ROTATION 4");
        }
        else if(rotation == 5){
            team1App.setText("Beam");
            team2App.setText("Floor");
            team1Name.setText("Visitor 2");
            team2Name.setText("Visitor 1");
            myArenaScreen.updateEvent("Beam", 1);
            myArenaScreen.updateEvent("Floor", 2);
            myArenaScreen.updateTeamName("Visitor 2", 1); //change to actual school names later
            myArenaScreen.updateTeamName("Visitor 1", 2); //""
            rotationLabel.setText("ROTATION 5");
        }
        else if (rotation == 6){
            team1App.setText("Beam");
            team2App.setText("Floor");
            team1Name.setText("Visitor 1");
            team2Name.setText("Home");
            myArenaScreen.updateEvent("Beam", 1);
            myArenaScreen.updateEvent("Floor", 2);
            myArenaScreen.updateTeamName("Visitor 1", 1); //change to actual school names later
            myArenaScreen.updateTeamName("Home", 2); //""
            rotationLabel.setText("ROTATION 6");
        }
        else if (rotation == 7){
            PostMeetScreen myPostMode = new PostMeetScreen(gC);
            myArenaScreen.getFrame().dispose();
            thisFrame.dispose();
        }

    }








    private int rotation = 1;
    private JPanel mainPanel;
    private JPanel triScorekeeperScreen;
    private JLabel rotationLabel;
    private JTextField j11;
    private JTextField j21;
    private JTextField j12;
    private JTextField j13;
    private JTextField j14;
    private JTextField j15;
    private JTextField j22;
    private JTextField j23;
    private JTextField j24;
    private JTextField j25;
    private JTextField j26;
    private JComboBox team1Combo;
    private JComboBox team2Combo;
    private JTextField timer1Textfield;
    private JButton startTimerButton1;
    private JTextField timer2Textfield;
    private JButton startTimerButton2;
    private JLabel team2App;
    private JTextField j16;
    private JLabel team1App;
    private JButton backButton;
    private JButton nextRotationButton;
    private JButton updateScoreButton1;
    private JButton updateScoreButton2;
    private JButton editLineupButton;
    private JButton defaultTemplateButton;
    private JLabel team1Name;
    private JLabel team2Name;
    private JCheckBox simultaneousCheckBox;
    private JPanel customizeScreen;
    private JCheckBox yearCheckbox;
    private JCheckBox pictureCheckbox;
    private JCheckBox majorCheckbox;
    private JCheckBox teamLogoCheckbox;
    private JCheckBox currentScoreCheckbox;
    private JCheckBox avgCheckbox;
    private JCheckBox teamScoreCheckbox;
    private JCheckBox nameCheckbox;
    private JCheckBox timerCheckbox;
    private CardLayout cardLayout;
}
