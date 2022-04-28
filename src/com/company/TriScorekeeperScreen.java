package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TriScorekeeperScreen {

    public TriScorekeeperScreen(GuiCreator gC, Team home, Team visitor1, Team visitor2, List<List<String>> allJudges) {

        System.out.println(home.getTeamName());
        System.out.println(visitor1.getTeamName());
        System.out.println(visitor2.getTeamName());

        JFrame frame = new JFrame("Triangular Scorekeeper Screen Prototype");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        Dual_Tri_ArenaScreen myDualTriArenaScreen = new Dual_Tri_ArenaScreen();
        myDualTriArenaScreen.getFrame().setVisible(true);

        homeCopy = home;
        visitor1Copy = visitor1;
        visitor2Copy = visitor2;
        myDualTriArenaScreen.teamName1.setText(homeCopy.getTeamName());
        myDualTriArenaScreen.teamName2.setText(visitor1Copy.getTeamName());

        team1Name.setText(homeCopy.getTeamName());
        team2Name.setText(visitor1Copy.getTeamName());


        //This sets the team logo; must be updated every next rotation
        ImageIcon imageIcon = new ImageIcon(picturePath + homeCopy.getTeamLogo());
        myDualTriArenaScreen.logo1.setIcon(gC.scaleImageIcon(imageIcon, 90, 90));
        imageIcon = new ImageIcon(picturePath + visitor1Copy.getTeamLogo());
        myDualTriArenaScreen.logo2.setIcon(gC.scaleImageIcon(imageIcon, 90, 90));
        //card layout start
        cardLayout = (CardLayout) mainPanel.getLayout();
        changeCard("CustomizeCard");
        createJudges(allJudges);

        createJudges(allJudges);

        for (int i = 0; i < 4; i++){
            for (int j = 0; j<allJudges.get(i).size(); j++){
                System.out.println(allJudges.get(i).get(j));
            }
        }

        vaultCombo2.setVisible(false);

        defaultTemplateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeCard("TriScorekeeperCard");
                myDualTriArenaScreen.getFrame().setVisible(true);
                try {
                    updateRotation(myDualTriArenaScreen, frame, 0, gC, homeCopy, visitor1Copy, visitor2Copy, allJudges);
                } catch (Exception ex) {
                }
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
                if (showUpdate == false) {
                    if (gC.confirmDialog("Are you sure you want to end Rotation " + rotation + "?")) {
                        updateRotation(myDualTriArenaScreen, frame, 1, gC, homeCopy, visitor1Copy, visitor2Copy, allJudges);

                        //show update on arena screen
                        myDualTriArenaScreen.nextUpdateTri(homeCopy, visitor1Copy, visitor2Copy, rotation);
                        myDualTriArenaScreen.changeCard("SimulCard");

                        myDualTriArenaScreen.resetArenaTables(); //reset tables
                        showUpdate = true;
                    } else {
                        myDualTriArenaScreen.changeCard("SimulCard");
                        showUpdate = true;
                    }
                } else {
                    myDualTriArenaScreen.nextUpdateTri(homeCopy, visitor1Copy, visitor2Copy, rotation);
                    showUpdate = false;
                }
                changeLogoDisplay(rotation, picturePath,myDualTriArenaScreen, gC);
            }
        });

        team1Combo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    //We have to know which teams are participating
                    //We can use the rotation number to keep track
                    Team temp = new Team("", "");      //Temporary placeholder
                    if (rotation == 1 || rotation == 4) {          //Means that the team on the left comboboxes is the home team
                        temp = homeCopy;
                    } else if (rotation == 2 || rotation == 5) {  //Means that the team on the left comboboxes is the visitor 2 team
                        temp = visitor2Copy;
                    } else if (rotation == 3 || rotation == 6) { //Means that the team on the left comboboxes is the visitor 1 team
                        temp = visitor1Copy;

                    }
                    if (currentevent1 == "Vault") {
                        myDualTriArenaScreen.updateGymnastInfo(temp.getVaultGymnasts().get(team1Combo.getSelectedIndex()), 1, 0);

                    } else if (currentevent1 == "Uneven Bars") {
                        myDualTriArenaScreen.updateGymnastInfo(temp.getBarGymnasts().get(team1Combo.getSelectedIndex()), 1, 1);

                    } else if (currentevent1 == "Balanced Beam") {
                        myDualTriArenaScreen.updateGymnastInfo(temp.getBeamGymnasts().get(team1Combo.getSelectedIndex()), 1, 2);

                    } else if (currentevent1 == "Floor Exercise") {
                        myDualTriArenaScreen.updateGymnastInfo(temp.getFloorGymnasts().get(team1Combo.getSelectedIndex()), 1, 3);

                    }
                }
            }

        });
        team2Combo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    //We have to know which teams are participating
                    //We can use the rotation number to keep track
                    Team temp = new Team("", "");      //Temporary placeholder
                    if (rotation == 2 || rotation == 6) {          //Means that the team on the left comboboxes is the home team
                        temp = homeCopy;
                    } else if (rotation == 3 || rotation == 4) {  //Means that the team on the left comboboxes is the visitor 2 team
                        temp = visitor2Copy;
                    } else if (rotation == 1 || rotation == 5) { //Means that the team on the left comboboxes is the visitor 1 team
                        temp = visitor1Copy;

                    }
                    if (currentevent2 == "Vault") {
                        myDualTriArenaScreen.updateGymnastInfo(temp.getVaultGymnasts().get(team2Combo.getSelectedIndex()), 2, 0);

                    } else if (currentevent2 == "Uneven Bars") {
                        myDualTriArenaScreen.updateGymnastInfo(temp.getBarGymnasts().get(team2Combo.getSelectedIndex()), 2, 1);

                    } else if (currentevent2 == "Balanced Beam") {
                        myDualTriArenaScreen.updateGymnastInfo(temp.getBeamGymnasts().get(team2Combo.getSelectedIndex()), 2, 2);

                    } else if (currentevent2 == "Floor Exercise") {
                        myDualTriArenaScreen.updateGymnastInfo(temp.getFloorGymnasts().get(team2Combo.getSelectedIndex()), 2, 3);

                    }
                }
            }
        });
        updateScoreButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Double> scoresList  = new ArrayList<>();
                List<JudgeScore> judgeScoreList = new ArrayList<>();
                boolean invalidScore = false;
                boolean emptyScores = false;
                float scoreArray[] = new float[6];
                try{
                    for (int i = 0; i<getHomeJudgesTextbox().size(); i++)
                    {
                        //if the text field is empty and it is one of the visible ones set flag for later
                        if(getHomeJudgesTextbox().get(i).getText().isEmpty() && getHomeJudgesTextbox().get(i).isVisible()){
                            emptyScores = true;
                        }
                        //of it is not empty then get it
                        else if(!getHomeJudgesTextbox().get(i).getText().isEmpty()){
                            scoresList.add(Double.parseDouble(getHomeJudgesTextbox().get(i).getText()));
                            JudgeScore judgeScore = new JudgeScore();
                            judgeScoreList.add(judgeScore);
                        }
                    }

                    if (!emptyScores) {
                        for (int i = 0; i < scoresList.size(); i++) {
                            if (scoresList.get(i) < 0 || scoresList.get(i) > 10 || scoresList.size() % 2 != 0) {
                                System.out.println(scoresList.get(i) + " " + scoresList.size() + " " + emptyScores);
                                JOptionPane.showMessageDialog(null, "Invalid Input. Make sure scores are in the appropriate range.");
                                invalidScore = true;
                                break;
                            }
                        }
                    }
                    else if (emptyScores)
                        JOptionPane.showMessageDialog(null, "Invalid Input. Make sure all judges have a score.");


                    double deduction = 0;
                    if (!nD1.getText().isEmpty()) {
                        deduction = Double.parseDouble(nD1.getText());
                    }
                    //only want to try to calculate or update on arena screen if there were no invalid scores or if they were not empty
                    if (!invalidScore && !emptyScores && scoresList.size() % 2 == 0) {
                        myDualTriArenaScreen.gymnastCurrent1.setForeground(Color.RED);
                        myDualTriArenaScreen.overall1.setForeground(Color.RED);

                        myDualTriArenaScreen.gymnastCurrent2.setForeground(defaultColor);
                        myDualTriArenaScreen.overall2.setForeground(defaultColor);
                        setHomePlayerandTeamScore(scoresList, deduction, judgeScoreList, myDualTriArenaScreen);
                    }




                } catch (Exception exception) {

                    System.out.println(exception);
                }

                //Resets the combobox
                for (int i = 0; i < getLeftJudges().size(); i++) {
                    getHomeJudgesTextbox().get(i).setText("");
                }
            }
        });
        updateScoreButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Double> scoresList = new ArrayList<>();
                List<JudgeScore> judgeScoreList = new ArrayList<>();
                boolean invalidScore = false;
                boolean emptyScores = false;
                float scoreArray[] = new float[6];
                try {
                    for (int i = 0; i < getVisitor1JudgesTextbox().size(); i++) {
                        if (getVisitor1JudgesTextbox().get(i).getText().isEmpty() && getVisitor1JudgesTextbox().get(i).isVisible()) {
                            emptyScores = true;
                        } else if (!getVisitor1JudgesTextbox().get(i).getText().isEmpty()) {
                            scoresList.add(Double.parseDouble(getVisitor1JudgesTextbox().get(i).getText()));
                            JudgeScore judgeScore = new JudgeScore();
                            judgeScoreList.add(judgeScore);
                        }
                    }
                    if (!emptyScores) {
                        for (int i = 0; i < scoresList.size(); i++) {
                            if (scoresList.get(i) < 0 || scoresList.get(i) > 10 || scoresList.size() % 2 != 0) {
                                JOptionPane.showMessageDialog(null, "Invalid Input. Make sure scores are in the appropriate range.");
                                invalidScore = true;
                                break;
                            }
                        }
                    }
                    else if (emptyScores)
                        JOptionPane.showMessageDialog(null, "Invalid Input. Make sure all judges have a score.");

                    double deduction = 0;
                    if (!nD2.getText().isEmpty()) {
                        deduction = Double.parseDouble(nD2.getText());
                    }
                    if (!invalidScore && !emptyScores) {
                        myDualTriArenaScreen.gymnastCurrent2.setForeground(Color.RED);
                        myDualTriArenaScreen.overall2.setForeground(Color.RED);

                        myDualTriArenaScreen.gymnastCurrent1.setForeground(defaultColor);
                        myDualTriArenaScreen.overall1.setForeground(defaultColor);
                        setVisitor1andTeamScore(scoresList, deduction, judgeScoreList, myDualTriArenaScreen);
                    }




                } catch (Exception exception) {

                    System.out.println(exception);
                }
                //Resets the combobox
                for (int i = 0; i < getRightJudges().size(); i++) {
                    getRightJudgesTextbox().get(i).setText("");
                }
            }
        });

        timerCheckbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    myDualTriArenaScreen.clockLabel1.setVisible(true);
                    myDualTriArenaScreen.clockLabel2.setVisible(true);
                } else {
                    myDualTriArenaScreen.clockLabel1.setVisible(false);
                    myDualTriArenaScreen.clockLabel2.setVisible(false);
                }
            }
        });
        nameCheckbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    myDualTriArenaScreen.name1.setVisible(true);
                    myDualTriArenaScreen.name2.setVisible(true);
                } else {
                    myDualTriArenaScreen.name1.setVisible(false);
                    myDualTriArenaScreen.name2.setVisible(false);
                }
            }
        });
        majorCheckbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    myDualTriArenaScreen.major1.setVisible(true);
                    myDualTriArenaScreen.major2.setVisible(true);
                } else {
                    myDualTriArenaScreen.major1.setVisible(false);
                    myDualTriArenaScreen.major2.setVisible(false);
                }
            }
        });
        yearCheckbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    myDualTriArenaScreen.year1.setVisible(true);
                    myDualTriArenaScreen.year2.setVisible(true);
                } else {
                    myDualTriArenaScreen.year1.setVisible(false);
                    myDualTriArenaScreen.year2.setVisible(false);
                }
            }
        });
        avgCheckbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    myDualTriArenaScreen.avg1.setVisible(true);
                    myDualTriArenaScreen.avg2.setVisible(true);
                } else {
                    myDualTriArenaScreen.avg1.setVisible(false);
                    myDualTriArenaScreen.avg2.setVisible(false);
                }
            }
        });
        currentScoreCheckbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    myDualTriArenaScreen.gymnastCurrent1.setVisible(true);
                    myDualTriArenaScreen.gymnastCurrent2.setVisible(true);
                } else {
                    myDualTriArenaScreen.gymnastCurrent1.setVisible(false);
                    myDualTriArenaScreen.gymnastCurrent2.setVisible(false);
                }
            }
        });
        teamScoreCheckbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    myDualTriArenaScreen.overall1.setVisible(true);
                    myDualTriArenaScreen.overall2.setVisible(true);
                } else {
                    myDualTriArenaScreen.overall1.setVisible(false);
                    myDualTriArenaScreen.overall2.setVisible(false);
                }
            }
        });
        pictureCheckbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    myDualTriArenaScreen.pic1.setVisible(true);
                    myDualTriArenaScreen.pic2.setVisible(true);
                } else {
                    myDualTriArenaScreen.pic1.setVisible(false);
                    myDualTriArenaScreen.pic2.setVisible(false);
                }
            }
        });
        teamLogoCheckbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    myDualTriArenaScreen.logo1.setVisible(true);
                    myDualTriArenaScreen.logo2.setVisible(true);
                } else {
                    myDualTriArenaScreen.logo1.setVisible(false);
                    myDualTriArenaScreen.logo2.setVisible(false);
                }
            }
        });
        vaultCombo1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                myDualTriArenaScreen.updateEvent(vaultCombo1.getSelectedItem().toString() + " Vault", 1);
            }
        });
        vaultCombo2.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                myDualTriArenaScreen.updateEvent(vaultCombo2.getSelectedItem().toString() + " Vault", 2);
            }
        });
        editLineupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Keep track of the last player on the apparatus
                int team1current = team1Combo.getSelectedIndex();
                int team2current = team2Combo.getSelectedIndex();
                EditLineupScreen myScreen = new EditLineupScreen(homeCopy, visitor1Copy, visitor2Copy, new Team("", ""), rotation, "Tri");
                homeCopy = myScreen.getEditHome();
                visitor1Copy = myScreen.getEditVisitor();
                visitor2Copy = myScreen.getEditVisitor2();
                homeCopy.updateApparatusLists();
                visitor1Copy.updateApparatusLists();
                visitor2Copy.updateApparatusLists();
                updateDisplay(myDualTriArenaScreen, gC, homeCopy, visitor1Copy, visitor2Copy, rotation);

                team1Combo.setSelectedIndex(team1current);
                team2Combo.setSelectedIndex(team2current);
            }
        });
    }


    public void changeCard(String cardName) {
        cardLayout.show(mainPanel, cardName);
    }
    public void updateDisplay(Dual_Tri_ArenaScreen myArenaScreen, GuiCreator gC, Team home, Team visitor1, Team visitor2, int rotation) {
        team1Combo.removeAllItems();
        team2Combo.removeAllItems();
        System.out.println("Rotation: " + rotation);
        switch (rotation) {
            case 1:         //Rotation 1; HOME, VISITOR
                gC.updateCombobox(team1Combo, home.getVaultGymnasts());
                gC.updateCombobox(team2Combo, visitor1.getBarGymnasts());
                break;
            case 2:         //Rotation 2; VISITOR2, HOME
                gC.updateCombobox(team1Combo, visitor2.getVaultGymnasts());
                gC.updateCombobox(team2Combo, home.getBarGymnasts());
                break;
            case 3:         //Rotation 3; VISITOR, VISITOR2
                gC.updateCombobox(team1Combo, visitor1.getVaultGymnasts());
                gC.updateCombobox(team2Combo, visitor2.getBarGymnasts());
                break;
            case 4:         //Rotation 4; HOME, VISITOR2
                gC.updateCombobox(team1Combo, home.getBeamGymnasts());
                gC.updateCombobox(team2Combo, visitor2.getFloorGymnasts());
                break;
            case 5:         //Rotation 5;VISITOR2, VISITOR
                gC.updateCombobox(team1Combo, visitor2.getBeamGymnasts());
                gC.updateCombobox(team2Combo, visitor1.getFloorGymnasts());
                break;
            case 6:         //Rotation 6; VISITOR, HOME
                gC.updateCombobox(team1Combo, visitor1.getBeamGymnasts());
                gC.updateCombobox(team2Combo, home.getFloorGymnasts());
                break;

        }

        //Updates the arena display for left side
        if (currentevent1 == "Vault") {
            switch (rotation) {
                case 1:
                    myArenaScreen.updateGymnastInfo(home.getVaultGymnasts().get(team1Combo.getSelectedIndex()), 1, 0);
                    break;
                case 2:
                    myArenaScreen.updateGymnastInfo(visitor2.getVaultGymnasts().get(team1Combo.getSelectedIndex()), 1, 0);
                    break;
                case 3:
                    myArenaScreen.updateGymnastInfo(visitor1.getVaultGymnasts().get(team1Combo.getSelectedIndex()), 1, 0);
                    break;
            }
        } else if (currentevent1 == "Balanced Beam") {
            switch (rotation) {
                case 1:
                    myArenaScreen.updateGymnastInfo(home.getBeamGymnasts().get(team1Combo.getSelectedIndex()), 1, 2);
                    break;
                case 2:
                    myArenaScreen.updateGymnastInfo(visitor2.getBeamGymnasts().get(team1Combo.getSelectedIndex()), 1, 2);
                    break;
                case 3:
                    myArenaScreen.updateGymnastInfo(visitor1.getBeamGymnasts().get(team1Combo.getSelectedIndex()), 1, 2);
                    break;
            }
        }
        //Updates the arena display for right side
        if (currentevent1 == "Uneven Bars") {
            switch (rotation) {
                case 1:
                    myArenaScreen.updateGymnastInfo(visitor1.getBarGymnasts().get(team2Combo.getSelectedIndex()), 2, 1);
                    break;
                case 2:
                    myArenaScreen.updateGymnastInfo(home.getBarGymnasts().get(team2Combo.getSelectedIndex()), 2, 1);
                    break;
                case 3:
                    myArenaScreen.updateGymnastInfo(visitor2.getBarGymnasts().get(team2Combo.getSelectedIndex()), 2, 1);
                    break;
            }
        } else if (currentevent1 == "Floor Exercise") {
            switch (rotation) {
                case 1:
                    myArenaScreen.updateGymnastInfo(visitor2.getFloorGymnasts().get(team2Combo.getSelectedIndex()), 2, 3);
                    break;
                case 2:
                    myArenaScreen.updateGymnastInfo(visitor1.getFloorGymnasts().get(team2Combo.getSelectedIndex()), 2, 3);
                    break;
                case 3:
                    myArenaScreen.updateGymnastInfo(home.getFloorGymnasts().get(team2Combo.getSelectedIndex()), 2, 3);
                    break;
            }
        }

    }

    //Changes the display logo on the arena screen
    public void changeLogoDisplay(int rotation, String picturePath,
                                  Dual_Tri_ArenaScreen myDualTriArenaScreen, GuiCreator gC) {
        ImageIcon imageIcon;
        if (rotation == 1) {              //left: home, right: visitor1
            imageIcon = new ImageIcon(picturePath + homeCopy.getTeamLogo());        //Left
            myDualTriArenaScreen.logo1.setIcon(gC.scaleImageIcon(imageIcon, 90, 90));
            imageIcon = new ImageIcon(picturePath + visitor1Copy.getTeamLogo());    //Right
            myDualTriArenaScreen.logo2.setIcon(gC.scaleImageIcon(imageIcon, 90, 90));

        } else if (rotation == 2) {       //left: visitor 2, right: home
            imageIcon = new ImageIcon(picturePath + visitor2Copy.getTeamLogo());        //Left
            myDualTriArenaScreen.logo1.setIcon(gC.scaleImageIcon(imageIcon, 90, 90));
            imageIcon = new ImageIcon(picturePath + homeCopy.getTeamLogo());    //Right
            myDualTriArenaScreen.logo2.setIcon(gC.scaleImageIcon(imageIcon, 90, 90));
        } else if (rotation == 3) {        //left: visitor 1, right: visitor 2
            imageIcon = new ImageIcon(picturePath + visitor1Copy.getTeamLogo());        //Left
            myDualTriArenaScreen.logo1.setIcon(gC.scaleImageIcon(imageIcon, 90, 90));
            imageIcon = new ImageIcon(picturePath + visitor2Copy.getTeamLogo());    //Right
            myDualTriArenaScreen.logo2.setIcon(gC.scaleImageIcon(imageIcon, 90, 90));
        } else if (rotation == 4) {       //left: home, right: visitor 2
            imageIcon = new ImageIcon(picturePath + homeCopy.getTeamLogo());        //Left
            myDualTriArenaScreen.logo1.setIcon(gC.scaleImageIcon(imageIcon, 90, 90));
            imageIcon = new ImageIcon(picturePath + visitor2Copy.getTeamLogo());    //Right
            myDualTriArenaScreen.logo2.setIcon(gC.scaleImageIcon(imageIcon, 90, 90));
        } else if (rotation == 5) {       //left: visitor 2, right: visitor 1
            imageIcon = new ImageIcon(picturePath + visitor2Copy.getTeamLogo());        //Left
            myDualTriArenaScreen.logo1.setIcon(gC.scaleImageIcon(imageIcon, 90, 90));
            imageIcon = new ImageIcon(picturePath + visitor1Copy.getTeamLogo());    //Right
            myDualTriArenaScreen.logo2.setIcon(gC.scaleImageIcon(imageIcon, 90, 90));
        } else if (rotation == 6) {       //left: visitor 1, right: home
            imageIcon = new ImageIcon(picturePath + visitor1Copy.getTeamLogo());        //Left
            myDualTriArenaScreen.logo1.setIcon(gC.scaleImageIcon(imageIcon, 90, 90));
            imageIcon = new ImageIcon(picturePath + homeCopy.getTeamLogo());    //Right
            myDualTriArenaScreen.logo2.setIcon(gC.scaleImageIcon(imageIcon, 90, 90));

        }

    }

    //pass in the frames that need to be handled and 1 if next rotation, -1 if previous
    //yes, a lot of this is redundant, but it's easy to read and change later
    private void updateRotation(Dual_Tri_ArenaScreen myArenaScreen, JFrame thisFrame, int value, GuiCreator gC,
                                Team home, Team visitor1, Team visitor2, List<List<String>> allJudges) {

        rotation = rotation + value;
        myArenaScreen.updateRotation(rotation);

        if (rotation == 0) {
            SetupModeTri myTriSetup = new SetupModeTri(gC);
            myTriSetup.changeCard("SummaryCard");
            myArenaScreen.getFrame().dispose();
            thisFrame.dispose();
        } else if (rotation == 1) {
            //This section be the same for rot 1,2,3
            currentevent1 = "Vault";
            currentevent2 = "Uneven Bars";
            team1App.setText("Vault");
            team2App.setText("Uneven Bars");
            //End section

            team1Name.setText(home.getTeamName());
            team2Name.setText(visitor1.getTeamName());
            myArenaScreen.updateEvent(vaultCombo1.getSelectedItem().toString() + " Vaults", 1);
            myArenaScreen.updateEvent("Uneven Bars", 2);
            myArenaScreen.updateTeamName(home.getTeamName(), 1); //change to actual school names later
            myArenaScreen.updateTeamName(visitor1.getTeamName(), 2); //

            rotationLabel.setText("ROTATION 1");

            updateJudgeNames(allJudges, rotation);

            gC.updateCombobox(team1Combo, home.getVaultGymnasts());
            gC.updateCombobox(team2Combo, visitor1.getBarGymnasts());

            vaultCombo1.setVisible(true);
            vaultCombo2.setVisible(false);

        } else if (rotation == 2) {
            //This section be the same for rot 1,2,3
            currentevent1 = "Vault";
            currentevent2 = "Uneven Bars";
            team1App.setText("Vault");
            team2App.setText("Uneven Bars");
            //End section

            team1Name.setText(visitor2.getTeamName());          //Visitor 2 will be on the left side
            team2Name.setText(home.getTeamName());              //Home will be on the right side
            myArenaScreen.updateEvent(vaultCombo1.getSelectedItem().toString() + " Vaults", 1);
            myArenaScreen.updateEvent("Uneven Bars", 2);
            myArenaScreen.updateTeamName(visitor2.getTeamName(), 1); //change to actual school names later
            myArenaScreen.updateTeamName(home.getTeamName(), 2); //

            rotationLabel.setText("ROTATION 2");

            updateJudgeNames(allJudges, rotation);

            gC.updateCombobox(team1Combo, visitor2.getVaultGymnasts());
            gC.updateCombobox(team2Combo, home.getBarGymnasts());

            vaultCombo1.setVisible(true);
            vaultCombo2.setVisible(false);

        } else if (rotation == 3) {
            //This section be the same for rot 1,2,3
            currentevent1 = "Vault";
            currentevent2 = "Uneven Bars";
            team1App.setText("Vault");
            team2App.setText("Uneven Bars");
            //End section

            team1Name.setText(visitor1.getTeamName());              //Visitor 1 will be on the left side
            team2Name.setText(visitor2.getTeamName());              //visitor 2 will be on the right side
            myArenaScreen.updateEvent(vaultCombo1.getSelectedItem().toString() + " Vaults", 1);
            myArenaScreen.updateEvent("Uneven Bars", 2);
            myArenaScreen.updateTeamName(visitor1.getTeamName(), 1); //change to actual school names later
            myArenaScreen.updateTeamName(visitor2.getTeamName(), 2); //

            rotationLabel.setText("ROTATION 3");

            updateJudgeNames(allJudges, rotation);

            gC.updateCombobox(team1Combo, visitor1.getVaultGymnasts());
            gC.updateCombobox(team2Combo, visitor2.getBarGymnasts());

            vaultCombo1.setVisible(true);
            vaultCombo2.setVisible(false);

        } else if (rotation == 4) {
            //This section be the same for rot 4,5,6
            currentevent1 = "Balanced Beam";
            currentevent2 = "Floor Exercise";
            team1App.setText("Balanced Beam");
            team2App.setText("Floor Exercise");
            //End section

            team1Name.setText(home.getTeamName());                   //home will be on the left side
            team2Name.setText(visitor2.getTeamName());              //visitor 2 will be on the right side
            myArenaScreen.updateEvent(currentevent1, 1);
            myArenaScreen.updateEvent(currentevent2, 2);
            myArenaScreen.updateTeamName(home.getTeamName(), 1); //change to actual school names later
            myArenaScreen.updateTeamName(visitor2.getTeamName(), 2); //

            rotationLabel.setText("ROTATION 4");

            updateJudgeNames(allJudges, rotation);

            gC.updateCombobox(team1Combo, home.getBeamGymnasts());
            gC.updateCombobox(team2Combo, visitor2.getFloorGymnasts());

            vaultCombo1.setVisible(false);
            vaultCombo2.setVisible(false);

        } else if (rotation == 5) {
            //This section be the same for rot 4,5,6
            currentevent1 = "Balanced Beam";
            currentevent2 = "Floor Exercise";
            team1App.setText("Balanced Beam");
            team2App.setText("Floor Exercise");
            //End section

            team1Name.setText(visitor2.getTeamName());              //visitor 2 will be on the left side
            team2Name.setText(visitor1.getTeamName());              //visitor 1 will be on the right side
            myArenaScreen.updateEvent(currentevent1, 1);
            myArenaScreen.updateEvent(currentevent2, 2);
            myArenaScreen.updateTeamName(visitor2.getTeamName(), 1); //change to actual school names later
            myArenaScreen.updateTeamName(visitor1.getTeamName(), 2); //

            rotationLabel.setText("ROTATION 5");

            updateJudgeNames(allJudges, rotation);

            gC.updateCombobox(team1Combo, visitor2.getBeamGymnasts());
            gC.updateCombobox(team2Combo, visitor1.getFloorGymnasts());

            vaultCombo1.setVisible(false);
            vaultCombo2.setVisible(false);


        } else if (rotation == 6) {
            //This section be the same for rot 4,5,6
            currentevent1 = "Balanced Beam";
            currentevent2 = "Floor Exercise";
            team1App.setText("Balanced Beam");
            team2App.setText("Floor Exercise");
            //End section

            team1Name.setText(visitor1.getTeamName());              //visitor 1 will be on the left side
            team2Name.setText(home.getTeamName());              //home will be on the right side
            myArenaScreen.updateEvent(currentevent1, 1);
            myArenaScreen.updateEvent(currentevent2, 2);
            myArenaScreen.updateTeamName(visitor1.getTeamName(), 1); //change to actual school names later
            myArenaScreen.updateTeamName(home.getTeamName(), 2); //

            rotationLabel.setText("ROTATION 6");

            updateJudgeNames(allJudges, rotation);

            gC.updateCombobox(team1Combo, visitor1.getBeamGymnasts());
            gC.updateCombobox(team2Combo, home.getFloorGymnasts());

            vaultCombo1.setVisible(false);
            vaultCombo2.setVisible(false);

            try {
                PostMeetResults postresult = new PostMeetResults("tri", homeCopy, visitor1Copy, visitor2Copy, new File("RESULTS.txt"), judges);

            } catch (Exception e) {
                System.out.println("Cannot create new instance of postmeet result");
            }

        } else if (rotation == 7) {
            List<Team> teams = new ArrayList<>();
            PostMeetScreen myPostMode = new PostMeetScreen(gC, teams);
            myArenaScreen.getFrame().dispose();
            thisFrame.dispose();
        }

    }


    //Function that creates the judges
    public void createJudges(List<List<String>> allJudges) {
        judges.add(floorJudges);
        judges.add(barJudges);
        judges.add(vaultJudges);
        judges.add(beamJudges);
        for (int j = 0; j < allJudges.get(0).size(); j++) {

            if (!allJudges.get(0).get(j).equals("")) {
                Judge judge = new Judge();
                judge.setFname(allJudges.get(0).get(j).toString());
                judge.setLname(allJudges.get(0).get(j).toString()); //not right but need to put something there
                vaultJudges.add(judge);
            }
        }
        for (int j = 0; j < allJudges.get(1).size(); j++) {
            if (!allJudges.get(1).get(j).equals("")) {
                Judge judge = new Judge();
                judge.setFname(allJudges.get(1).get(j).toString());
                judge.setLname(allJudges.get(1).get(j).toString()); //not right but need to put something there
                barJudges.add(judge);
            }
        }
        for (int j = 0; j < allJudges.get(2).size(); j++) {
            if (!allJudges.get(2).get(j).equals("")) {
                Judge judge = new Judge();
                judge.setFname(allJudges.get(2).get(j).toString());
                judge.setLname(allJudges.get(2).get(j).toString()); //not right but need to put something there
                beamJudges.add(judge);
            }
        }
        for (int j = 0; j < allJudges.get(3).size(); j++) {
            if (!allJudges.get(3).get(j).equals("")) {
                Judge judge = new Judge();
                judge.setFname(allJudges.get(3).get(j).toString());
                judge.setLname(allJudges.get(3).get(j).toString()); //not right but need to put something there
                floorJudges.add(judge);
            }
        }
    }


    private void setHomePlayerandTeamScore(List<Double> scoresList, Double deduction, List<JudgeScore> judgeScoreList, Dual_Tri_ArenaScreen myArenaScreen) {
        PlayerScore tempScore = new PlayerScore(); //holder to use calculation method
        double pscore = tempScore.calculateIndividualScore(scoresList, deduction);
        //Home team --> vault, visitor1--> Bars, visitor2-->Bye
        //Home is on vaults
        if (rotation == 1) {
            homeCopy.getVaultGymnasts().get(team1Combo.getSelectedIndex()).getPlayerScore().setvaultScore(pscore);
            homeCopy.getTeamScore().setvaultScore(homeCopy.getTeamScore().calculateTeamVaultScore(homeCopy.getVaultGymnasts()));
            // go through the list of judges scores (these are directly from the text boxes
            //set the createScore method in judgescore object, add it to the right judge object scores list
            for (int k = 0; k < judgeScoreList.size(); k++) {
                //match the score to the appropriate judge object
                judgeScoreList.get(k).createScore(team1App.getText(), homeCopy.getVaultGymnasts().get(team1Combo.getSelectedIndex()), 0, scoresList.get(k));
                vaultJudges.get(k).addScore(judgeScoreList.get(k));
            }

            myArenaScreen.overall1.setText(String.valueOf("Running Team Score:     " + homeCopy.getTeamScore().getRunningScore()));
        }
        //Home team --> Bars, visitor1--> Bye, visitor2-->Vault
        //Visitor 2 is on Vaults
        else if (rotation == 2) {
            visitor2Copy.getVaultGymnasts().get(team1Combo.getSelectedIndex()).getPlayerScore().setvaultScore(pscore);
            visitor2Copy.getTeamScore().setvaultScore(visitor2Copy.getTeamScore().calculateTeamVaultScore(visitor2Copy.getVaultGymnasts()));
            for (int k = 0; k < judgeScoreList.size(); k++) {
                //match the score to the appropriate judge object
                judgeScoreList.get(k).createScore(team1App.getText(), visitor2Copy.getVaultGymnasts().get(team1Combo.getSelectedIndex()), 0, scoresList.get(k));
                vaultJudges.get(k).addScore(judgeScoreList.get(k));
            }
            myArenaScreen.overall1.setText(String.valueOf("Running Team Score:     " + visitor2Copy.getTeamScore().getRunningScore()));

        }
        //Home team --> Bye, visitor1--> Vault, visitor2-->Bars
        //Visitor 1 is on Vault
        else if (rotation == 3) {
            visitor1Copy.getVaultGymnasts().get(team1Combo.getSelectedIndex()).getPlayerScore().setvaultScore(pscore);
            visitor1Copy.getTeamScore().setvaultScore(visitor1Copy.getTeamScore().calculateTeamVaultScore(visitor1Copy.getVaultGymnasts()));
            for (int k = 0; k < judgeScoreList.size(); k++) {
                //match the score to the appropriate judge object
                judgeScoreList.get(k).createScore(team1App.getText(), visitor1Copy.getVaultGymnasts().get(team1Combo.getSelectedIndex()), 0, scoresList.get(k));
                vaultJudges.get(k).addScore(judgeScoreList.get(k));
            }
            myArenaScreen.overall1.setText(String.valueOf("Running Team Score:     " + visitor1Copy.getTeamScore().getRunningScore()));

        }
        //Home team --> Beam, visitor1--> Bye, visitor2-->Floor
        //Home is on beam
        else if (rotation == 4) {
            homeCopy.getBeamGymnasts().get(team1Combo.getSelectedIndex()).getPlayerScore().setbeamScore(pscore);
            homeCopy.getTeamScore().setbeamScore(homeCopy.getTeamScore().calculateTeamBeamScore(homeCopy.getBeamGymnasts()));
            for (int k = 0; k < judgeScoreList.size(); k++) {
                //match the score to the appropriate judge object
                judgeScoreList.get(k).createScore(team1App.getText(), homeCopy.getBeamGymnasts().get(team1Combo.getSelectedIndex()), 0, scoresList.get(k));
                beamJudges.get(k).addScore(judgeScoreList.get(k));
            }

            myArenaScreen.overall1.setText(String.valueOf("Running Team Score:     " + homeCopy.getTeamScore().getRunningScore()));

        }
        //Home team --> Bye, visitor1--> Floor, visitor2-->Beam
        //Visitor 2 on Beam
        else if (rotation == 5) {
            visitor2Copy.getBeamGymnasts().get(team1Combo.getSelectedIndex()).getPlayerScore().setbeamScore(pscore);
            visitor2Copy.getTeamScore().setbeamScore(visitor2Copy.getTeamScore().calculateTeamBeamScore(visitor2Copy.getBeamGymnasts()));
            for (int k = 0; k < judgeScoreList.size(); k++) {
                //match the score to the appropriate judge object
                judgeScoreList.get(k).createScore(team1App.getText(), visitor2Copy.getBeamGymnasts().get(team1Combo.getSelectedIndex()), 0, scoresList.get(k));
                beamJudges.get(k).addScore(judgeScoreList.get(k));
            }
            myArenaScreen.overall1.setText(String.valueOf("Running Team Score:     " + visitor2Copy.getTeamScore().getRunningScore()));

        }
        //Home team --> Floor, visitor1--> Beam, visitor2-->Visitor2
        //Visitor1 on Beam
        else if (rotation == 6) {
            visitor1Copy.getBeamGymnasts().get(team1Combo.getSelectedIndex()).getPlayerScore().setbeamScore(pscore);
            visitor1Copy.getTeamScore().setbeamScore(visitor1Copy.getTeamScore().calculateTeamBeamScore(visitor1Copy.getBeamGymnasts()));
            for (int k = 0; k < judgeScoreList.size(); k++) {
                //match the score to the appropriate judge object
                judgeScoreList.get(k).createScore(team1App.getText(), visitor1Copy.getBeamGymnasts().get(team1Combo.getSelectedIndex()), 0, scoresList.get(k));
                beamJudges.get(k).addScore(judgeScoreList.get(k));
            }
            myArenaScreen.overall1.setText(String.valueOf("Running Team Score:     " + visitor1Copy.getTeamScore().getRunningScore()));
        }
        myArenaScreen.gymnastCurrent1.setText(String.valueOf("Gymnast Current Score: " + pscore));       //update score on arena screen
    }


    private void setVisitor1andTeamScore(List<Double> scoresList, Double deduction, List<JudgeScore> judgeScoreList, Dual_Tri_ArenaScreen myArenaScreen) {
        PlayerScore tempScore = new PlayerScore(); //holder to use calculation method
        double pscore = tempScore.calculateIndividualScore(scoresList, deduction);
        //Home team --> vault, visitor1--> Bars, visitor2-->Bye
        //Visitor1 is on Bars
        if (rotation == 1) {
            visitor1Copy.getBarGymnasts().get(team2Combo.getSelectedIndex()).getPlayerScore().setbarScore(pscore);
            visitor1Copy.getTeamScore().setbarScore(visitor1Copy.getTeamScore().calculateTeamBarScore(visitor1Copy.getBarGymnasts()));
            // go through the list of judges scores (these are directly from the text boxes
            //set the createScore method in judgescore object, add it to the right judge object scores list
            for (int k = 0; k < judgeScoreList.size(); k++) {
                //match the score to the appropriate judge object
                judgeScoreList.get(k).createScore(team2App.getText(), visitor1Copy.getBarGymnasts().get(team2Combo.getSelectedIndex()), 0, scoresList.get(k));
                barJudges.get(k).addScore(judgeScoreList.get(k));
            }
            myArenaScreen.overall2.setText(String.valueOf("Running Team Score:     " + visitor1Copy.getTeamScore().getRunningScore()));

        }
        //Home team --> Bars, visitor1--> Bye, visitor2-->Vault
        //Home is on Bars
        else if (rotation == 2) {
            homeCopy.getBarGymnasts().get(team2Combo.getSelectedIndex()).getPlayerScore().setbarScore(pscore);
            homeCopy.getTeamScore().setbarScore(homeCopy.getTeamScore().calculateTeamBarScore(homeCopy.getBarGymnasts()));
            // go through the list of judges scores (these are directly from the text boxes
            //set the createScore method in judgescore object, add it to the right judge object scores list
            for (int k = 0; k < judgeScoreList.size(); k++) {
                //match the score to the appropriate judge object
                judgeScoreList.get(k).createScore(team2App.getText(), homeCopy.getBarGymnasts().get(team2Combo.getSelectedIndex()), 0, scoresList.get(k));
                barJudges.get(k).addScore(judgeScoreList.get(k));
            }
            myArenaScreen.overall2.setText(String.valueOf("Running Team Score:     " + homeCopy.getTeamScore().getRunningScore()));

        }
        //Home team --> Bye, visitor1--> Vault, visitor2-->Bars
        //Visitor2 on Bars
        else if (rotation == 3) {
            visitor2Copy.getBarGymnasts().get(team2Combo.getSelectedIndex()).getPlayerScore().setbarScore(pscore);
            visitor2Copy.getTeamScore().setbarScore(visitor2Copy.getTeamScore().calculateTeamBarScore(visitor2Copy.getBarGymnasts()));
            // go through the list of judges scores (these are directly from the text boxes
            //set the createScore method in judgescore object, add it to the right judge object scores list
            for (int k = 0; k < judgeScoreList.size(); k++) {
                //match the score to the appropriate judge object
                judgeScoreList.get(k).createScore(team2App.getText(), visitor2Copy.getBarGymnasts().get(team2Combo.getSelectedIndex()), 0, scoresList.get(k));
                barJudges.get(k).addScore(judgeScoreList.get(k));
            }
            myArenaScreen.overall2.setText(String.valueOf("Running Team Score:     " + visitor2Copy.getTeamScore().getRunningScore()));

        }
        //Home team --> Beam, visitor1--> Bye, visitor2-->Floor
        //Visitor2 on floor
        else if (rotation == 4) {
            visitor2Copy.getFloorGymnasts().get(team1Combo.getSelectedIndex()).getPlayerScore().setfloorScore(pscore);
            visitor2Copy.getTeamScore().setfloorScore(visitor2Copy.getTeamScore().calculateTeamFloorScore(visitor2Copy.getFloorGymnasts()));
            for (int k = 0; k < judgeScoreList.size(); k++) {
                //match the score to the appropriate judge object
                judgeScoreList.get(k).createScore(team2App.getText(), visitor2Copy.getFloorGymnasts().get(team2Combo.getSelectedIndex()), 0, scoresList.get(k));
                floorJudges.get(k).addScore(judgeScoreList.get(k));
            }
            myArenaScreen.overall2.setText(String.valueOf("Running Team Score:     " + visitor2Copy.getTeamScore().getRunningScore()));

        }
        //Home team --> Bye, visitor1--> Floor, visitor2-->Beam
        //visitor1 on floor
        else if (rotation == 5) {
            visitor1Copy.getFloorGymnasts().get(team1Combo.getSelectedIndex()).getPlayerScore().setfloorScore(pscore);
            visitor1Copy.getTeamScore().setfloorScore(visitor1Copy.getTeamScore().calculateTeamFloorScore(visitor1Copy.getFloorGymnasts()));
            for (int k = 0; k < judgeScoreList.size(); k++) {
                //match the score to the appropriate judge object
                judgeScoreList.get(k).createScore(team2App.getText(), visitor1Copy.getFloorGymnasts().get(team2Combo.getSelectedIndex()), 0, scoresList.get(k));
                floorJudges.get(k).addScore(judgeScoreList.get(k));
            }
            myArenaScreen.overall2.setText(String.valueOf("Running Team Score:     " + visitor1Copy.getTeamScore().getRunningScore()));
        }
        //Home team --> Floor, visitor1--> Beam, visitor2-->Visitor2
        else if (rotation == 6) {
            homeCopy.getFloorGymnasts().get(team1Combo.getSelectedIndex()).getPlayerScore().setfloorScore(pscore);
            homeCopy.getTeamScore().setfloorScore(homeCopy.getTeamScore().calculateTeamFloorScore(homeCopy.getFloorGymnasts()));
            for (int k = 0; k < judgeScoreList.size(); k++) {
                //match the score to the appropriate judge object
                judgeScoreList.get(k).createScore(team2App.getText(), homeCopy.getFloorGymnasts().get(team2Combo.getSelectedIndex()), 0, scoresList.get(k));
                floorJudges.get(k).addScore(judgeScoreList.get(k));
            }
            myArenaScreen.overall2.setText(String.valueOf("Running Team Score:     " + homeCopy.getTeamScore().getRunningScore()));
        }
        myArenaScreen.gymnastCurrent2.setText("Gymnast Current Score: " + String.valueOf(pscore));       //update score on arena screen
    }


    private List<JTextField> getHomeJudgesTextbox() {
        return Arrays.asList(j11, j12, j13, j14, j15, j16);
    }

    private List<JTextField> getVisitor1JudgesTextbox() {
        return Arrays.asList(j21, j22, j23, j24, j25, j26);
    }

    //Function that updates the labels and number of textfields of the judges
    private void updateJudgeNames(List<List<String>> allJudges, int rotation) {

        if (rotation == 1 || rotation == 2 || rotation == 3) {
            //Sets the judges on the left side; Vaults
            for (int i = 0; i < allJudges.get(ApparatusIndex.VT).size(); i++) {
                //Checks if there is a judge
                if (allJudges.get(ApparatusIndex.VT).get(i).equals("")) {
                    System.out.println(allJudges.get(ApparatusIndex.VT).get(i));
                    getLeftJudges().get(i).setVisible(false);
                    getLeftJudgesTextbox().get(i).setVisible(false);
                } else {
                    getLeftJudges().get(i).setVisible(true);
                    getLeftJudgesTextbox().get(i).setVisible(true);
                }
                getLeftJudges().get(i).setText(allJudges.get(ApparatusIndex.VT).get(i));       //Sets text of label
            }
            //Sets the judges on the right side; Bars
            for (int i = 0; i < allJudges.get(ApparatusIndex.UB).size(); i++) {
                //Checks if there is a judge
                if (allJudges.get(ApparatusIndex.UB).get(i).equals("")) {
                    getRightJudges().get(i).setVisible(false);
                    getRightJudgesTextbox().get(i).setVisible(false);
                } else {
                    getRightJudges().get(i).setVisible(true);
                    getRightJudgesTextbox().get(i).setVisible(true);
                }
                getRightJudges().get(i).setText(allJudges.get(ApparatusIndex.UB).get(i));       //Sets text of label
            }
        } else if (rotation == 4 || rotation == 5 || rotation == 6) {
            //Sets the judges on the left side; Beams
            for (int i = 0; i < allJudges.get(ApparatusIndex.BB).size(); i++) {
                //Checks if there is a judge
                if (allJudges.get(ApparatusIndex.BB).get(i).equals("")) {
                    getLeftJudges().get(i).setVisible(false);
                    getLeftJudgesTextbox().get(i).setVisible(false);
                } else {
                    getLeftJudges().get(i).setVisible(true);
                    getLeftJudgesTextbox().get(i).setVisible(true);
                }
                getLeftJudges().get(i).setText(allJudges.get(ApparatusIndex.BB).get(i));       //Sets text of label
            }
            //Sets the judges on the right side; Floor
            for (int i = 0; i < allJudges.get(ApparatusIndex.FX).size(); i++) {
                //Checks if there is a judge
                if (allJudges.get(ApparatusIndex.FX).get(i).equals("")) {
                    getRightJudges().get(i).setVisible(false);
                    getRightJudgesTextbox().get(i).setVisible(false);
                } else {
                    getRightJudges().get(i).setVisible(true);
                    getRightJudgesTextbox().get(i).setVisible(true);
                }
                getRightJudges().get(i).setText(allJudges.get(ApparatusIndex.FX).get(i));       //Sets text of label
            }
        }

    }

    //Returns the Judges Label on the left side
    private List<JLabel> getLeftJudges() {
        return Arrays.asList(jL11, jL12, jL13, jL14, jL15, jL16);
    }
    //Returns the Judges Label on the right side
    private List<JLabel> getRightJudges() {
        return Arrays.asList(jL21, jL22, jL23, jL24, jL25, jL26);
    }
    //Returns the Judges TextField on the left side
    private List<JTextField> getLeftJudgesTextbox() {
        return Arrays.asList(j11, j12, j13, j14, j15, j16);
    }
    //Returns the Judges TextField on the left side
    private List<JTextField> getRightJudgesTextbox() {
        return Arrays.asList(j21, j22, j23, j24, j25, j26);
    }

    //Variables
    private Boolean showUpdate = true; //if true, it's time to show update, if false, go to next rotation
    List<List<Judge>> judges = new ArrayList<List<Judge>>();
    List<Judge> floorJudges = new ArrayList<Judge>();
    List<Judge> beamJudges = new ArrayList<Judge>();
    List<Judge> vaultJudges = new ArrayList<Judge>();
    List<Judge> barJudges = new ArrayList<Judge>();
    public String currentevent1;
    public String currentevent2;
    public Team homeCopy, visitor1Copy, visitor2Copy;
    //Directory for pictures folder
    String picturePath = System.getProperty("user.dir") + "/pictures/";

    private Color defaultColor = new Color(51, 51, 51); //default font color
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
    private JTextField nD1;
    private JTextField nD2;
    private JComboBox vaultCombo1;
    private JComboBox vaultCombo2;
    private JLabel jL11;
    private JLabel jL12;
    private JLabel jL13;
    private JLabel jL14;
    private JLabel jL15;
    private JLabel jL16;
    private JLabel jL21;
    private JLabel jL22;
    private JLabel jL23;
    private JLabel jL24;
    private JLabel jL25;
    private JLabel jL26;
    private CardLayout cardLayout;
}
