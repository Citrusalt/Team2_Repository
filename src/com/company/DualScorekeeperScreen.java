package com.company;

import javax.sound.midi.SysexMessage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DualScorekeeperScreen {

    public DualScorekeeperScreen(GuiCreator gC, Team home, Team visitor, List<List<String>> allJudges) {

        JFrame frame = new JFrame("Dual Scorekeeper Screen");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        DatabaseManager db = new DatabaseManager();


        Dual_Tri_ArenaScreen myArenaScreen = new Dual_Tri_ArenaScreen();

        homeCopy = home;
        visitorCopy = visitor;


        //Set Arena Screen Fields
        myArenaScreen.teamName1.setText(homeCopy.getTeamName());
        myArenaScreen.teamName2.setText(visitorCopy.getTeamName());


        ImageIcon imageIcon = new ImageIcon(picturePath + homeCopy.getTeamLogo());
        myArenaScreen.logo1.setIcon(gC.scaleImageIcon(imageIcon, 90, 90));

        imageIcon = new ImageIcon(picturePath + visitorCopy.getTeamLogo());
        myArenaScreen.logo2.setIcon(gC.scaleImageIcon(imageIcon, 90, 90));

        homeTeamName.setText(homeCopy.getTeamName());
        visitorTeamName.setText(visitorCopy.getTeamName());


        vaultCombo1.setVisible(false);
        vaultCombo2.setVisible(false);

        //logo
        //etc.

        myArenaScreen.getFrame().setVisible(true);


        //card layout start
        cardLayout = (CardLayout) mainPanel.getLayout();
        changeCard("CustomizeCard");

        createJudges(allJudges);

        //*************************************
//         Sets the team participants. So tired setting up in the set-up mode smh.
//        List<Player> temp = new ArrayList<>();
//        homeCopy.getVaultGymnasts().clear();
//        homeCopy.getBarGymnasts().clear();
//        //Adds the vault players
//        for(int i = 0; i < 6; i++){
//            homeCopy.getAllGymnasts().get(i).setApparatusStatusByIndex(ApparatusIndex.VT, true);
//            homeCopy.getAllGymnasts().get(i).setApparatusOrderByIndex(ApparatusIndex.VT, i+1);
//            temp.add(homeCopy.getAllGymnasts().get(i));
//        }
//        homeCopy.setVaultGymnasts(temp);
//        temp = new ArrayList<>();
//
//        //Adds the bar players
//        homeCopy.getAllGymnasts().get(4).setApparatusStatusByIndex(ApparatusIndex.UB, true);
//        homeCopy.getAllGymnasts().get(4).setApparatusOrderByIndex(ApparatusIndex.UB, 1);
//        temp.add(homeCopy.getAllGymnasts().get(4));
//
//        homeCopy.getAllGymnasts().get(1).setApparatusStatusByIndex(ApparatusIndex.UB, true);
//        homeCopy.getAllGymnasts().get(1).setApparatusOrderByIndex(ApparatusIndex.UB, 2);
//        temp.add(homeCopy.getAllGymnasts().get(1));
//
//        homeCopy.getAllGymnasts().get(6).setApparatusStatusByIndex(ApparatusIndex.UB, true);
//        homeCopy.getAllGymnasts().get(6).setApparatusOrderByIndex(ApparatusIndex.UB, 3);
//        temp.add(homeCopy.getAllGymnasts().get(6));
//
//        homeCopy.getAllGymnasts().get(5).setApparatusStatusByIndex(ApparatusIndex.UB, true);
//        homeCopy.getAllGymnasts().get(5).setApparatusOrderByIndex(ApparatusIndex.UB, 4);
//        temp.add(homeCopy.getAllGymnasts().get(5));
//
//        homeCopy.getAllGymnasts().get(7).setApparatusStatusByIndex(ApparatusIndex.UB, true);
//        homeCopy.getAllGymnasts().get(7).setApparatusOrderByIndex(ApparatusIndex.UB, 5);
//        temp.add(homeCopy.getAllGymnasts().get(7));
//
//        homeCopy.getAllGymnasts().get(8).setApparatusStatusByIndex(ApparatusIndex.UB, true);
//        homeCopy.getAllGymnasts().get(8).setApparatusOrderByIndex(ApparatusIndex.UB, 6);
//        temp.add(homeCopy.getAllGymnasts().get(8));
//        homeCopy.setBarGymnasts(temp);
//
//        /////VISITOR
//        //ub
//        temp = new ArrayList<>();
//        visitorCopy.getBarGymnasts().clear();
//        visitorCopy.getVaultGymnasts().clear();
//
//        visitorCopy.getAllGymnasts().get(10).setApparatusStatusByIndex(ApparatusIndex.UB, true);
//        visitorCopy.getAllGymnasts().get(10).setApparatusOrderByIndex(ApparatusIndex.UB, 1);
//        temp.add(visitorCopy.getAllGymnasts().get(10));
//
//        visitorCopy.getAllGymnasts().get(7).setApparatusStatusByIndex(ApparatusIndex.UB, true);
//        visitorCopy.getAllGymnasts().get(7).setApparatusOrderByIndex(ApparatusIndex.UB, 2);
//        temp.add(visitorCopy.getAllGymnasts().get(7));
//
//        visitorCopy.getAllGymnasts().get(8).setApparatusStatusByIndex(ApparatusIndex.UB, true);
//        visitorCopy.getAllGymnasts().get(8).setApparatusOrderByIndex(ApparatusIndex.UB, 3);
//        temp.add(visitorCopy.getAllGymnasts().get(8));
//
//        visitorCopy.getAllGymnasts().get(0).setApparatusStatusByIndex(ApparatusIndex.UB, true);
//        visitorCopy.getAllGymnasts().get(0).setApparatusOrderByIndex(ApparatusIndex.UB, 4);
//        temp.add(visitorCopy.getAllGymnasts().get(0));
//
//        visitorCopy.getAllGymnasts().get(1).setApparatusStatusByIndex(ApparatusIndex.UB, true);
//        visitorCopy.getAllGymnasts().get(1).setApparatusOrderByIndex(ApparatusIndex.UB, 5);
//        temp.add(visitorCopy.getAllGymnasts().get(1));
//
//        visitorCopy.getAllGymnasts().get(5).setApparatusStatusByIndex(ApparatusIndex.UB, true);
//        visitorCopy.getAllGymnasts().get(5).setApparatusOrderByIndex(ApparatusIndex.UB, 6);
//        temp.add(visitorCopy.getAllGymnasts().get(5));
//        visitorCopy.setBarGymnasts(temp);
//
//        temp = new ArrayList<>();;
//
//        //vt
//
//        visitorCopy.getAllGymnasts().get(9).setApparatusStatusByIndex(ApparatusIndex.VT, true);
//        visitorCopy.getAllGymnasts().get(9).setApparatusOrderByIndex(ApparatusIndex.VT, 1);
//        temp.add(visitorCopy.getAllGymnasts().get(9));
//
//        visitorCopy.getAllGymnasts().get(2).setApparatusStatusByIndex(ApparatusIndex.VT, true);
//        visitorCopy.getAllGymnasts().get(2).setApparatusOrderByIndex(ApparatusIndex.VT, 2);
//        temp.add(visitorCopy.getAllGymnasts().get(2));
//
//        visitorCopy.getAllGymnasts().get(5).setApparatusStatusByIndex(ApparatusIndex.VT, true);
//        visitorCopy.getAllGymnasts().get(5).setApparatusOrderByIndex(ApparatusIndex.VT, 3);
//        temp.add(visitorCopy.getAllGymnasts().get(5));
//
//        visitorCopy.getAllGymnasts().get(4).setApparatusStatusByIndex(ApparatusIndex.VT, true);
//        visitorCopy.getAllGymnasts().get(4).setApparatusOrderByIndex(ApparatusIndex.VT, 4);
//        temp.add(visitorCopy.getAllGymnasts().get(4));
//
//        visitorCopy.getAllGymnasts().get(10).setApparatusStatusByIndex(ApparatusIndex.VT, true);
//        visitorCopy.getAllGymnasts().get(10).setApparatusOrderByIndex(ApparatusIndex.VT, 5);
//        temp.add(visitorCopy.getAllGymnasts().get(10));
//
//        visitorCopy.getAllGymnasts().get(1).setApparatusStatusByIndex(ApparatusIndex.VT, true);
//        visitorCopy.getAllGymnasts().get(1).setApparatusOrderByIndex(ApparatusIndex.VT, 6);
//        temp.add(visitorCopy.getAllGymnasts().get(1));
//
//        visitorCopy.setVaultGymnasts(temp);
//        //************************************
//        System.out.println("----------VISITOR-------");
//        visitorCopy.printAll();
//        System.out.println("----------HOME-------");
//        homeCopy.printAll();


        defaultTemplateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeCard("SimulCard");
                if (selectedMode == 0){
                    try {
                        updateRotationSimul(myArenaScreen, frame, 0, gC, homeCopy, visitorCopy, allJudges);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                else if (selectedMode == 1){
                        updateRotationNonSimul(myArenaScreen, frame, 0, gC, homeCopy, visitorCopy, allJudges);
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
                            myArenaScreen.clock1(Integer.parseInt(timer1Textfield.getText()));
                            startTimerButton1.setText("Reset Timer");
                        }

                    } catch (Exception exception) {
                        System.out.println("Invalid Input");
                    }
                } else {
                    myArenaScreen.resetClock1();
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
                            myArenaScreen.clock2(Integer.parseInt(timer2Textfield.getText()));
                            startTimerButton2.setText("Reset Timer");
                        }

                    } catch (Exception exception) {
                        System.out.println("Invalid Input");
                    }
                } else {
                    myArenaScreen.resetClock2();
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

                if (showUpdate == false){
                    if (gC.confirmDialog("Are you sure you want to end Rotation " + rotation + "?")){
                        if (selectedMode == 0){
                            try {
                                updateRotationSimul(myArenaScreen, frame, 1, gC, homeCopy, visitorCopy, allJudges);
                            } catch (IOException ex) {
                                System.out.println("Some Error with Post Meet Mode");
                            }
                            //show update on arena screen
                            myArenaScreen.nextUpdateDual(homeCopy, visitorCopy, rotation);
                            myArenaScreen.changeCard("SimulCard");
                        }
                        else if (selectedMode == 1){
                            try{
                                updateRotationNonSimul(myArenaScreen, frame, 1, gC, homeCopy, visitorCopy, allJudges);
                            } catch (Exception exception) {
                                System.out.println("Some Error with Post Meet Mode");
                            }
                            myArenaScreen.nextUpdateDual(homeCopy, visitorCopy, rotation);
                            myArenaScreen.changeCard("SimulCard");
                        }
                        myArenaScreen.resetArenaTables(); //reset tables
                        showUpdate = true;
                    }
                    else{
                        myArenaScreen.changeCard("SimulCard");
                        showUpdate = true;
                    }
                }
                else{
                    myArenaScreen.nextUpdateDual(homeCopy, visitorCopy, rotation);
                    showUpdate = false;
                }

            }
        });

        //Deleted for simplicity
//        backButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (showUpdate == true){
//
//                    if (selectedMode == 0){
//                        updateRotationSimul(myArenaScreen, frame, -1, gC, home, visitor, allJudges);
//                    }
//                    else if (selectedMode == 1 ){
//                        updateRotationNonSimul(myArenaScreen, frame, -1, gC, home, visitor, allJudges);
//                    }
//                    myArenaScreen.resetArenaTables();
//                    myArenaScreen.nextUpdateDual(home, visitor, rotation);
//                    myArenaScreen.changeCard("UpdatePanel");
//                    showUpdate = false;
//                }
//                else{
//                    if (selectedMode == 0){
//                        myArenaScreen.changeCard("SimulCard");
//                    }
//                    else if (selectedMode == 1){
//                        myArenaScreen.changeCard("NonSimulCard");
//                    }
//
//                    myArenaScreen.resetArenaTables();
//                    showUpdate = true;
//                }
//
//            }
//        });

        team1Combo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {

                    if (currentevent1 == "Vault") {
                        myArenaScreen.updateGymnastInfo(homeCopy.getVaultGymnasts().get(team1Combo.getSelectedIndex()), 1, 0);
                    }
                    else if (currentevent1 == "Bars") {
                        myArenaScreen.updateGymnastInfo(homeCopy.getBarGymnasts().get(team1Combo.getSelectedIndex()), 1, 1);
                    }
                    else if (currentevent1 == "Beam"){
                        myArenaScreen.updateGymnastInfo(homeCopy.getBeamGymnasts().get(team1Combo.getSelectedIndex()), 1, 2);
                    }
                    else if (currentevent1 == "Floor"){
                        myArenaScreen.updateGymnastInfo(homeCopy.getFloorGymnasts().get(team1Combo.getSelectedIndex()), 1, 3);
                    }
                }
            }

        });
        team2Combo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {

                    if (currentevent2 == "Vault") {
                        myArenaScreen.updateGymnastInfo(visitorCopy.getVaultGymnasts().get(team2Combo.getSelectedIndex()), 2, 0);
                    }
                    else if (currentevent2 == "Bars") {
                        myArenaScreen.updateGymnastInfo(visitorCopy.getBarGymnasts().get(team2Combo.getSelectedIndex()), 2, 1);
                    }
                    else if (currentevent2 == "Beam"){
                        myArenaScreen.updateGymnastInfo(visitorCopy.getBeamGymnasts().get(team2Combo.getSelectedIndex()), 2, 2);
                    }
                    else if (currentevent2 == "Floor"){
                        myArenaScreen.updateGymnastInfo(visitorCopy.getFloorGymnasts().get(team2Combo.getSelectedIndex()), 2, 3);
                    }
                }
            }
        });
        updateScoreButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                List<Double> scoresList = new ArrayList<>();
                List<JudgeScore> judgeScoreList = new ArrayList<>();

                boolean invalidScore = false;
                boolean emptyScores = false;
                boolean oddScores;
                try{
                    //Use these scores to update score for backend and arena screen
                    //doesn't have to be entered into a "scoreArray" just an example
               /*     scoreArray[0] = Integer.parseInt(j11.getText());
                    scoreArray[1] = Integer.parseInt(j12.getText());
                    scoreArray[2] = Integer.parseInt(j13.getText());
                    scoreArray[3] = Integer.parseInt(j14.getText());
                    scoreArray[4] = Integer.parseInt(j15.getText());
                    scoreArray[5] = Integer.parseInt(j16.getText());
                    scoreArray[6] = Integer.parseInt(nD1.getText()); //deduction textboxes are called nD1, nD2, nD3, nD4*/

//                    System.out.println(myArenaScreen.gymnastCurrent1.getForeground());

                    //This is just a test input
//                    float avg = 0;
//
//                    for (float i : scoreArray){
//                        avg += i;
//                    }
//                    avg = avg/6;
//                    myArenaScreen.gymnastCurrent1.setText(String.valueOf(avg));
           /*         if (j11.getText().isEmpty() == false && j12.getText().isEmpty() == false && j13.getText().isEmpty() == false && j14.getText().isEmpty() == false && j15.getText().isEmpty() == false && j16.getText().isEmpty() == false)
                    {
                        JOptionPane.showMessageDialog(null, "No scores were entered.");
                        emptyScores = true;
                    }*/
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
                    for (int i = 0; i< scoresList.size(); i++)
                    {
                        if (scoresList.get(i)<0 || scoresList.get(i)>10 ||scoresList.size() % 2 != 0||emptyScores)
                        {   JOptionPane.showMessageDialog(null, "Invalid Input. Make sure scores are in the appropriate range and all judges have a score.");
                            invalidScore=true;
                            break; }
                    }


/*
                    if (j11.getText().isEmpty() == false && j12.getText().isEmpty() == false) {
                        if (Double.parseDouble(j11.getText()) > 10 || Double.parseDouble(j12.getText()) > 10 || Double.parseDouble(j11.getText()) < 0 || Double.parseDouble(j12.getText()) < 0) {
                            JOptionPane.showMessageDialog(null, "Invalid input.");
                        } else {
                            JudgeScore judgeScore1 = new JudgeScore();
                            JudgeScore judgeScore2 = new JudgeScore();
                            judgeScoreList.add(judgeScore1);
                            judgeScoreList.add(judgeScore2);
                            scoresList.add(Double.parseDouble(j11.getText()));
                            scoresList.add(Double.parseDouble(j12.getText()));
                        }
                    }
                   if (j13.getText().isEmpty() == false && j14.getText().isEmpty() == false) {
                        if (Double.parseDouble(j13.getText()) > 10 || Double.parseDouble(j14.getText()) > 10) {
                            JOptionPane.showMessageDialog(null, "Invalid input.");
                        } else {
                            JudgeScore judgeScore3 = new JudgeScore();
                            JudgeScore judgeScore4 = new JudgeScore();
                            judgeScoreList.add(judgeScore3);
                            judgeScoreList.add(judgeScore4);
                            scoresList.add(Double.parseDouble(j13.getText()));
                            scoresList.add(Double.parseDouble(j14.getText()));
                        }
                    }
                    if (j15.getText().isEmpty() == false && j16.getText().isEmpty() == false) {
                        if (Double.parseDouble(j15.getText()) > 10 || Double.parseDouble(j16.getText()) > 10) {
                            JOptionPane.showMessageDialog(null, "Invalid input.");
                        } else {
                            JudgeScore judgeScore5 = new JudgeScore();
                            JudgeScore judgeScore6 = new JudgeScore();
                            judgeScoreList.add(judgeScore5);
                            judgeScoreList.add(judgeScore6);
                            scoresList.add(Double.parseDouble(j15.getText()));
                            scoresList.add(Double.parseDouble(j16.getText()));
                        }
                    }*/
                    double deduction = 0;
                    if (!nD1.getText().isEmpty()) {
                        deduction = Double.parseDouble(nD1.getText());
                    }
                    //only want to try to calculate or update on arena screen if there were no invalid scores or if they were not empty
                    if (!invalidScore && !emptyScores && scoresList.size() % 2 == 0) {
                        myArenaScreen.gymnastCurrent1.setForeground(Color.RED);
                        myArenaScreen.overall1.setForeground(Color.RED);
                        myArenaScreen.gymnastCurrent2.setForeground(defaultColor);
                        myArenaScreen.overall2.setForeground(defaultColor);
                        setPlayer1andTeamScore(scoresList, deduction, judgeScoreList, myArenaScreen);
                    }
                } catch (Exception exception) {
                    System.out.println(exception);
                }

                //Resets the combobox
                for(int i = 0; i < getHomeJudges().size(); i++){
                    getHomeJudgesTextbox().get(i).setText("");
                }
            }
        });
        updateScoreButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Double> scoresList  = new ArrayList<>();
                List<JudgeScore> judgeScoreList = new ArrayList<>();

                boolean invalidScore = false;
                boolean emptyScores = false;
                float scoreArray[] = new float[7];


                try{



                    for (int i = 0; i<getVisitor1JudgesTextbox().size(); i++)
                    {
                        if(getVisitor1JudgesTextbox().get(i).getText().isEmpty() && getVisitor1JudgesTextbox().get(i).isVisible()){
                            emptyScores = true;
                        }
                        else if(!getVisitor1JudgesTextbox().get(i).getText().isEmpty()){
                            scoresList.add(Double.parseDouble(getVisitor1JudgesTextbox().get(i).getText()));
                            JudgeScore judgeScore = new JudgeScore();
                            judgeScoreList.add(judgeScore);
                        }
                    }
                    for (int i = 0; i< scoresList.size(); i++)
                    {
                        if (scoresList.get(i)<0 || scoresList.get(i)>10 || scoresList.size() % 2 != 0 || emptyScores)
                        {   JOptionPane.showMessageDialog(null, "Invalid Input. Make sure scores are in the appropriate range and all judges have a score.");
                            invalidScore=true;
                            break; }
                    }

                    /*if (!j21.getText().isEmpty() && !j22.getText().isEmpty()){
                        if (Double.parseDouble(j21.getText()) > 10 || Double.parseDouble(j22.getText()) >10 || Double.parseDouble(j21.getText()) < 0 || Double.parseDouble(j22.getText()) <0)
                        {
                            JOptionPane.showMessageDialog(null, "Invalid input.");
                        }
                        else {
                            JudgeScore judgeScore1 = new JudgeScore();
                            JudgeScore judgeScore2 = new JudgeScore();
                            judgeScoreList.add(judgeScore1);
                            judgeScoreList.add(judgeScore2);        //set the score itself comes later
                            scoresList.add(Double.parseDouble(j21.getText()));
                            scoresList.add(Double.parseDouble(j22.getText()));
                        }
                    }
                    if (!j23.getText().isEmpty() && !j24.getText().isEmpty()){
                        if (Double.parseDouble(j23.getText()) > 10 || Double.parseDouble(j24.getText()) >10 || Double.parseDouble(j23.getText()) < 0 || Double.parseDouble(j24.getText()) <0)
                        {
                            JOptionPane.showMessageDialog(null, "Invalid input.");
                        }
                        else {
                            JudgeScore judgeScore3 = new JudgeScore();
                            JudgeScore judgeScore4 = new JudgeScore();
                            judgeScoreList.add(judgeScore3);
                            judgeScoreList.add(judgeScore4);
                            scoresList.add(Double.parseDouble(j23.getText()));
                            scoresList.add(Double.parseDouble(j24.getText()));
                        }
                    }
                    if (!j25.getText().isEmpty() && !j26.getText().isEmpty()){
                        if (Double.parseDouble(j25.getText()) > 10 || Double.parseDouble(j26.getText()) >10 || Double.parseDouble(j25.getText()) < 0 || Double.parseDouble(j26.getText()) < 0)
                        {
                            JOptionPane.showMessageDialog(null, "Invalid input.");
                        }
                        else {
                            JudgeScore judgeScore5 = new JudgeScore();
                            JudgeScore judgeScore6 = new JudgeScore();
                            judgeScoreList.add(judgeScore5);
                            judgeScoreList.add(judgeScore6);
                            scoresList.add(Double.parseDouble(j25.getText()));
                            scoresList.add(Double.parseDouble(j26.getText()));
                        }
                    }*/
                    double deduction = 0;
                    if (!nD2.getText().isEmpty()) {
                        deduction = Double.parseDouble(nD2.getText());
                    }
                    if (!invalidScore && !emptyScores) {
                        myArenaScreen.gymnastCurrent2.setForeground(Color.RED);
                        myArenaScreen.overall2.setForeground(Color.RED);
                        myArenaScreen.gymnastCurrent1.setForeground(defaultColor);
                        myArenaScreen.overall1.setForeground(defaultColor);
                        setPlayer2andTeamScore(scoresList, deduction, judgeScoreList, myArenaScreen);
                    }

                } catch (Exception exception) {

                    System.out.println(exception);
                }
                //Resets the combobox
                for(int i = 0; i < getVisitor1Judges().size(); i++){
                    getVisitor1JudgesTextbox().get(i).setText("");
                }
            }
        });

        timerCheckbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED){
                    myArenaScreen.clockLabel1.setVisible(true);
                    myArenaScreen.clockLabel2.setVisible(true);
                }
                else{
                    myArenaScreen.clockLabel1.setVisible(false);
                    myArenaScreen.clockLabel2.setVisible(false);
                }
            }
        });
        nameCheckbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED){
                    myArenaScreen.name1.setVisible(true);
                    myArenaScreen.name2.setVisible(true);
                }
                else{
                    myArenaScreen.name1.setVisible(false);
                    myArenaScreen.name2.setVisible(false);
                }
            }
        });
        majorCheckbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED){
                    myArenaScreen.major1.setVisible(true);
                    myArenaScreen.major2.setVisible(true);
                }
                else{
                    myArenaScreen.major1.setVisible(false);
                    myArenaScreen.major2.setVisible(false);
                }
            }
        });
        yearCheckbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED){
                    myArenaScreen.year1.setVisible(true);
                    myArenaScreen.year2.setVisible(true);
                }
                else{
                    myArenaScreen.year1.setVisible(false);
                    myArenaScreen.year2.setVisible(false);
                }
            }
        });
        avgCheckbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED){
                    myArenaScreen.avg1.setVisible(true);
                    myArenaScreen.avg2.setVisible(true);
                }
                else{
                    myArenaScreen.avg1.setVisible(false);
                    myArenaScreen.avg2.setVisible(false);
                }
            }
        });
        currentScoreCheckbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED){
                    myArenaScreen.gymnastCurrent1.setVisible(true);
                    myArenaScreen.gymnastCurrent2.setVisible(true);
                }
                else{
                    myArenaScreen.gymnastCurrent1.setVisible(false);
                    myArenaScreen.gymnastCurrent2.setVisible(false);
                }
            }
        });
        teamScoreCheckbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED){
                    myArenaScreen.overall1.setVisible(true);
                    myArenaScreen.overall2.setVisible(true);
                }
                else{
                    myArenaScreen.overall1.setVisible(false);
                    myArenaScreen.overall2.setVisible(false);
                }
            }
        });
        pictureCheckbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED){
                    myArenaScreen.pic1.setVisible(true);
                    myArenaScreen.pic2.setVisible(true);
                }
                else{
                    myArenaScreen.pic1.setVisible(false);
                    myArenaScreen.pic2.setVisible(false);
                }
            }
        });
        simultaneousCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED){
                    selectedMode = 0;
                }
                else{
                    selectedMode = 1;
                }
            }
        });

        editLineupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditLineupScreen myScreen = new EditLineupScreen(homeCopy, visitorCopy, new Team("",""), rotation, "Dual");
                homeCopy = myScreen.getEditHome();
                visitorCopy = myScreen.getEditVisitor();
                homeCopy.updateApparatusLists();
                visitorCopy.updateApparatusLists();
                updateDisplay(myArenaScreen, gC, homeCopy, visitorCopy, rotation);
            }
        });
        teamLogoCheckbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED){
                    myArenaScreen.logo1.setVisible(true);
                    myArenaScreen.logo2.setVisible(true);
                }
                else{
                    myArenaScreen.logo1.setVisible(false);
                    myArenaScreen.logo2.setVisible(false);
                }
            }
        });
        vaultCombo1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                myArenaScreen.updateEvent(vaultCombo1.getSelectedItem().toString() + " Vault", 1);
            }
        });
        vaultCombo2.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                myArenaScreen.updateEvent(vaultCombo2.getSelectedItem().toString() + " Vault", 2);
            }
        });
    }


    public void changeCard(String cardName){
        cardLayout.show(mainPanel, cardName);
    }


    //pass in the frames that need to be handled and 1 if next rotation, -1 if previous
    private void updateRotationSimul(Dual_Tri_ArenaScreen myArenaScreen, JFrame thisFrame, int value, GuiCreator gC, Team home, Team visitor, List<List<String>> allJudges) throws IOException {
        rotation = rotation + value;
        myArenaScreen.updateRotation(rotation);

        if (rotation == 0){
            DualScorekeeperScreen myScoreKeeperScreen = new DualScorekeeperScreen(gC, home, visitor, allJudges);
            myScoreKeeperScreen.changeCard("CustomizeCard");
            myArenaScreen.getFrame().dispose();
            thisFrame.dispose();
        }
        else if (rotation == 1){
            currentevent1 = "Vault";
            currentevent2 = "Bars";
            team1App.setText(currentevent1);
            team2App.setText(currentevent2);
            myArenaScreen.updateEvent(vaultCombo1.getSelectedItem().toString() + " " +currentevent1, 1);
            myArenaScreen.updateEvent(currentevent2, 2);
            rotationLabel.setText("ROTATION 1");

            updateJudgeNamesSimul(allJudges, rotation);

            gC.updateCombobox(team1Combo, home.getVaultGymnasts());
            gC.updateCombobox(team2Combo, visitor.getBarGymnasts());

            vaultCombo1.setVisible(true);
            vaultCombo2.setVisible(false);

        }
        else if (rotation == 2){
            currentevent1 = "Bars";
            currentevent2 = "Vault";
            team1App.setText(currentevent1);
            team2App.setText(currentevent2);
            myArenaScreen.updateEvent(currentevent1, 1);
            myArenaScreen.updateEvent(vaultCombo2.getSelectedItem().toString() + " "+ currentevent2, 2);
            rotationLabel.setText("ROTATION 2");

            updateJudgeNamesSimul(allJudges, rotation);

            gC.updateCombobox(team1Combo, home.getBarGymnasts());
            gC.updateCombobox(team2Combo, visitor.getVaultGymnasts());

            vaultCombo1.setVisible(false);
            vaultCombo2.setVisible(true);
        }
        else if (rotation == 3){
            currentevent1 = "Beam";
            currentevent2 = "Floor";
            team1App.setText(currentevent1);
            team2App.setText(currentevent2);
            myArenaScreen.updateEvent(currentevent1, 1);
            myArenaScreen.updateEvent(currentevent2, 2);
            rotationLabel.setText("ROTATION 3");

            updateJudgeNamesSimul(allJudges, rotation);

            gC.updateCombobox(team1Combo, home.getBeamGymnasts());
            gC.updateCombobox(team2Combo, visitor.getFloorGymnasts());

            vaultCombo1.setVisible(false);
            vaultCombo2.setVisible(false);
        }
        else if (rotation == 4){
            currentevent1 = "Floor";
            currentevent2 = "Beam";
            team1App.setText(currentevent1);
            team2App.setText(currentevent2);
            myArenaScreen.updateEvent(currentevent1, 1);
            myArenaScreen.updateEvent(currentevent2, 2);
            rotationLabel.setText("ROTATION 4");

            updateJudgeNamesSimul(allJudges, rotation);

            gC.updateCombobox(team1Combo, home.getFloorGymnasts());
            gC.updateCombobox(team2Combo, visitor.getBeamGymnasts());
        }
        else if(rotation == 5){

                if (gC.confirmDialog("Are you sure you want to end the tournament?")){
                    List <Team> teams = new ArrayList<>();
                    teams.add(home);
                    teams.add(visitor);
                    PostMeetScreen myPostMode = new PostMeetScreen(gC, teams);
                    myArenaScreen.getFrame().dispose();
                    thisFrame.dispose();

                    //Just testing getting the judges                       //Potato
                    PostMeetResults postresult = new PostMeetResults("dual", home, visitor, new File("RESULTS.txt"), judges);

                }
                else {
                    updateRotationSimul(myArenaScreen, thisFrame, -1, gC, home, visitor,  allJudges);
                }

        }

    }


    private void updateRotationNonSimul(Dual_Tri_ArenaScreen myArenaScreen, JFrame thisFrame, int value, GuiCreator gC, Team home, Team visitor, List<List<String>> allJudges){
        rotation = rotation + value;
        myArenaScreen.updateRotation(rotation);

        if (rotation == 0){
            DualScorekeeperScreen myScoreKeeperScreen = new DualScorekeeperScreen(gC, home, visitor, allJudges);
            myScoreKeeperScreen.changeCard("CustomizeCard");
            myArenaScreen.getFrame().dispose();
            thisFrame.dispose();
        }
        else if (rotation == 1){
            currentevent1 = "Vault";
            currentevent2 = "Vault";
            team1App.setText(currentevent1);
            team2App.setText(currentevent2);
            myArenaScreen.updateEvent(vaultCombo1.getSelectedItem().toString() + " " + currentevent1, 1);
            myArenaScreen.updateEvent(vaultCombo2.getSelectedItem().toString() + " " + currentevent2, 2);
            rotationLabel.setText("ROTATION 1");

            updateJudgeNamesSimul(allJudges, rotation);

            gC.updateCombobox(team1Combo, home.getVaultGymnasts());
            gC.updateCombobox(team2Combo, visitor.getVaultGymnasts());

            vaultCombo1.setVisible(true);
            vaultCombo2.setVisible(true);

        }
        else if (rotation == 2){
            currentevent1 = "Bars";
            currentevent2 = "Bars";
            team1App.setText(currentevent1);
            team2App.setText(currentevent2);
            myArenaScreen.updateEvent(currentevent1, 1);
            myArenaScreen.updateEvent(currentevent2, 2);
            rotationLabel.setText("ROTATION 2");

            updateJudgeNamesSimul(allJudges, rotation);

            gC.updateCombobox(team1Combo, home.getBarGymnasts());
            gC.updateCombobox(team2Combo, visitor.getBarGymnasts());

            vaultCombo1.setVisible(false);
            vaultCombo2.setVisible(false);
        }
        else if (rotation == 3){
            currentevent1 = "Beam";
            currentevent2 = "Beam";
            team1App.setText(currentevent1);
            team2App.setText(currentevent2);
            myArenaScreen.updateEvent(currentevent1, 1);
            myArenaScreen.updateEvent(currentevent2, 2);
            rotationLabel.setText("ROTATION 3");

            updateJudgeNamesSimul(allJudges, rotation);

            gC.updateCombobox(team1Combo, home.getBeamGymnasts());
            gC.updateCombobox(team2Combo, visitor.getBeamGymnasts());

        }
        else if (rotation == 4){
            currentevent1 = "Floor";
            currentevent2 = "Floor";
            team1App.setText(currentevent1);
            team2App.setText(currentevent2);
            myArenaScreen.updateEvent(currentevent1, 1);
            myArenaScreen.updateEvent(currentevent2, 2);
            rotationLabel.setText("ROTATION 4");

            updateJudgeNamesSimul(allJudges, rotation);

            gC.updateCombobox(team1Combo, home.getBarGymnasts());
            gC.updateCombobox(team2Combo, visitor.getBarGymnasts());
        }
        else if(rotation == 5){
            if (gC.confirmDialog("Are you sure you want to end the tournament?")){
                List <Team> teams = new ArrayList<>();
                teams.add(home);
                teams.add(visitor);
                PostMeetScreen myPostMode = new PostMeetScreen(gC, teams);
                myArenaScreen.getFrame().dispose();
                thisFrame.dispose();

                //Just testing getting the judges                       //Potato
                try{
                    PostMeetResults postresult = new PostMeetResults("dual", home, visitor, new File("RESULTS.txt"), judges);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else {
                try{
                    updateRotationSimul(myArenaScreen, thisFrame, -1, gC, home, visitor,  allJudges);

                } catch (Exception e) {

                }
            }
        }

    }

    private void updateJudgeNamesSimul(List<List <String>> allJudges, int rotation){

//        System.out.println("Starting Judge Update...");

        if (rotation == 1){
            for (int i = 0; i < allJudges.get(0).size(); i++){
                if (allJudges.get(0).get(i) == ""){
                    getHomeJudges().get(i).setVisible(false);
                    getHomeJudgesTextbox().get(i).setVisible(false);
                }
                else{
                    getHomeJudges().get(i).setVisible(true);
                    getHomeJudgesTextbox().get(i).setVisible(true);
                }
                getHomeJudges().get(i).setText(allJudges.get(0).get(i));
            }
            for (int i = 0; i < allJudges.get(1).size(); i++){
                if (allJudges.get(1).get(i) == ""){
                    getVisitor1Judges().get(i).setVisible(false);
                    getVisitor1JudgesTextbox().get(i).setVisible(false);
                }
                else{
                    getVisitor1Judges().get(i).setVisible(true);
                    getVisitor1JudgesTextbox().get(i).setVisible(true);
                }
                getVisitor1Judges().get(i).setText(allJudges.get(1).get(i));
            }
        }
        else if (rotation == 2){
            for (int i = 0; i < allJudges.get(1).size(); i++){
                if (allJudges.get(1).get(i) == ""){
                    getHomeJudges().get(i).setVisible(false);
                    getHomeJudgesTextbox().get(i).setVisible(false);
                }
                else{
                    getHomeJudges().get(i).setVisible(true);
                    getHomeJudgesTextbox().get(i).setVisible(true);
                }
                getHomeJudges().get(i).setText(allJudges.get(1).get(i));
            }
            for (int i = 0; i < allJudges.get(0).size(); i++){
                if (allJudges.get(0).get(i) == ""){
                    getVisitor1Judges().get(i).setVisible(false);
                    getVisitor1JudgesTextbox().get(i).setVisible(false);
                }
                else{
                    getVisitor1Judges().get(i).setVisible(true);
                    getVisitor1JudgesTextbox().get(i).setVisible(true);
                }
                getVisitor1Judges().get(i).setText(allJudges.get(0).get(i));
            }
        }
        else if (rotation == 3){
            for (int i = 0; i < allJudges.get(2).size(); i++){
                if (allJudges.get(2).get(i) == ""){
                    getHomeJudges().get(i).setVisible(false);
                    getHomeJudgesTextbox().get(i).setVisible(false);
                }
                else{
                    getHomeJudges().get(i).setVisible(true);
                    getHomeJudgesTextbox().get(i).setVisible(true);
                }
                getHomeJudges().get(i).setText(allJudges.get(2).get(i));
            }
            for (int i = 0; i < allJudges.get(3).size(); i++){
                if (allJudges.get(3).get(i) == ""){
                    getVisitor1Judges().get(i).setVisible(false);
                    getVisitor1JudgesTextbox().get(i).setVisible(false);
                }
                else{
                    getVisitor1Judges().get(i).setVisible(true);
                    getVisitor1JudgesTextbox().get(i).setVisible(true);
                }
                getVisitor1Judges().get(i).setText(allJudges.get(3).get(i));
            }
        }
        else if (rotation == 4){
            for (int i = 0; i < allJudges.get(3).size(); i++){
                if (allJudges.get(3).get(i) == ""){
                    getHomeJudges().get(i).setVisible(false);
                    getHomeJudgesTextbox().get(i).setVisible(false);
                }
                else{
                    getHomeJudges().get(i).setVisible(true);
                    getHomeJudgesTextbox().get(i).setVisible(true);
                }
                getHomeJudges().get(i).setText(allJudges.get(3).get(i));
            }
            for (int i = 0; i < allJudges.get(2).size(); i++){
                if (allJudges.get(2).get(i) == ""){
                    getVisitor1Judges().get(i).setVisible(false);
                    getVisitor1JudgesTextbox().get(i).setVisible(false);
                }
                else{
                    getVisitor1Judges().get(i).setVisible(true);
                    getVisitor1JudgesTextbox().get(i).setVisible(true);
                }
                getVisitor1Judges().get(i).setText(allJudges.get(2).get(i));
            }
        }

    }
    List<List<Judge>> judges = new ArrayList<List<Judge>>();
    List<Judge> floorJudges = new ArrayList<Judge>();
    List<Judge> beamJudges = new ArrayList<Judge>();
    List<Judge> vaultJudges = new ArrayList<Judge>();
    List<Judge> barJudges = new ArrayList<Judge>();



    public void createJudges(List<List<String>> allJudges){
        judges.add(floorJudges);
        judges.add(barJudges);
        judges.add(vaultJudges);
        judges.add(beamJudges);
        for (int j = 0; j < allJudges.get(0).size(); j++){
            if (!allJudges.get(0).get(j).equals("- Select Judges -")){
            Judge judge = new Judge();
            judge.setFname(allJudges.get(0).get(j).toString());
            judge.setLname(allJudges.get(0).get(j).toString()); //not right but need to put something there
            vaultJudges.add(judge);}
        }
        for (int j = 0; j < allJudges.get(1).size(); j++){
            if (!allJudges.get(1).get(j).equals("- Select Judges -")){
            Judge judge = new Judge();
            judge.setFname(allJudges.get(1).get(j).toString());
            judge.setLname(allJudges.get(1).get(j).toString()); //not right but need to put something there
            barJudges.add(judge);}
        }
        for (int j = 0; j < allJudges.get(2).size(); j++){
            if (!allJudges.get(2).get(j).equals("- Select Judges -")){
            Judge judge = new Judge();
            judge.setFname(allJudges.get(2).get(j).toString());
            judge.setLname(allJudges.get(2).get(j).toString()); //not right but need to put something there
            beamJudges.add(judge);}
        }
        for (int j = 0; j < allJudges.get(3).size(); j++){
            if (!allJudges.get(3).get(j).equals("- Select Judges -")){
            Judge judge = new Judge();
            judge.setFname(allJudges.get(3).get(j).toString());
            judge.setLname(allJudges.get(3).get(j).toString()); //not right but need to put something there
            floorJudges.add(judge);}
        }
    }
    public void updateDisplay(Dual_Tri_ArenaScreen myArenaScreen, GuiCreator gC, Team home, Team visitor, int rotation) {
        team1Combo.removeAllItems();
        team2Combo.removeAllItems();
        System.out.println("Rotation: " + rotation);
        switch (rotation) {
            case 1:
                gC.updateCombobox(team1Combo, home.getVaultGymnasts());
                gC.updateCombobox(team2Combo, visitor.getBarGymnasts());
                break;
            case 2:
                gC.updateCombobox(team1Combo, home.getBarGymnasts());
                gC.updateCombobox(team2Combo, visitor.getVaultGymnasts());
                break;
            case 3:
                gC.updateCombobox(team1Combo, home.getFloorGymnasts());
                gC.updateCombobox(team2Combo, visitor.getBeamGymnasts());
                break;
            case 4:
                gC.updateCombobox(team1Combo, home.getBeamGymnasts());
                gC.updateCombobox(team2Combo, visitor.getFloorGymnasts());
                break;
        }

        //Updates the arena display for home
        if (currentevent1 == "Vault") {
            myArenaScreen.updateGymnastInfo(home.getVaultGymnasts().get(team1Combo.getSelectedIndex()), 1, 0);
        } else if (currentevent1 == "Bars") {
            myArenaScreen.updateGymnastInfo(home.getBarGymnasts().get(team1Combo.getSelectedIndex()), 1, 1);
        } else if (currentevent1 == "Beam") {
            myArenaScreen.updateGymnastInfo(home.getBeamGymnasts().get(team1Combo.getSelectedIndex()), 1, 2);
        } else if (currentevent1 == "Floor") {
            myArenaScreen.updateGymnastInfo(home.getFloorGymnasts().get(team1Combo.getSelectedIndex()), 1, 3);
        }

        //Updates the arena display for home
        if (currentevent2 == "Vault") {
            myArenaScreen.updateGymnastInfo(visitor.getVaultGymnasts().get(team2Combo.getSelectedIndex()), 2, 0);
        } else if (currentevent2 == "Bars") {
            myArenaScreen.updateGymnastInfo(visitor.getBarGymnasts().get(team2Combo.getSelectedIndex()), 2, 1);
        } else if (currentevent2 == "Beam") {
            myArenaScreen.updateGymnastInfo(visitor.getBeamGymnasts().get(team2Combo.getSelectedIndex()), 2, 2);
        } else if (currentevent2 == "Floor") {
            myArenaScreen.updateGymnastInfo(visitor.getFloorGymnasts().get(team2Combo.getSelectedIndex()), 2, 3);
        }
    }

    private void setPlayer1andTeamScore(List<Double> scoresList, Double deduction, List<JudgeScore> judgeScoreList, Dual_Tri_ArenaScreen myArenaScreen)
    {
        PlayerScore tempScore = new PlayerScore(); //holder to use calculation method
        double pscore = tempScore.calculateIndividualScore(scoresList, deduction);
        if (rotation == 1) {
            homeCopy.getVaultGymnasts().get(team1Combo.getSelectedIndex()).getPlayerScore().setvaultScore(pscore);
            homeCopy.getTeamScore().setvaultScore(homeCopy.getTeamScore().calculateTeamVaultScore(homeCopy.getVaultGymnasts()));
            // go through the list of judges scores (these are directly from the text boxes
            //set the createScore method in judgescore object, add it to the right judge object scores list
            for (int k = 0; k < judgeScoreList.size(); k++)
            {
                //match the score to the appropriate judge object
                judgeScoreList.get(k).createScore(team1App.getText(), homeCopy.getVaultGymnasts().get(team1Combo.getSelectedIndex()), 0, scoresList.get(k));
                vaultJudges.get(k).addScore(judgeScoreList.get(k));
            }

        }
        else if (rotation == 2) {
            homeCopy.getBarGymnasts().get(team1Combo.getSelectedIndex()).getPlayerScore().setbarScore(pscore);
            homeCopy.getTeamScore().setbarScore(homeCopy.getTeamScore().calculateTeamBarScore(homeCopy.getBarGymnasts()));
            for (int k = 0; k < judgeScoreList.size(); k++)
            {
                //match the score to the appropriate judge object
                judgeScoreList.get(k).createScore(team1App.getText(), homeCopy.getBarGymnasts().get(team1Combo.getSelectedIndex()), 0, scoresList.get(k));
                barJudges.get(k).addScore(judgeScoreList.get(k));
            }
        }
        else if (rotation == 3) {
            homeCopy.getBeamGymnasts().get(team1Combo.getSelectedIndex()).getPlayerScore().setbeamScore(pscore);
            homeCopy.getTeamScore().setbeamScore(homeCopy.getTeamScore().calculateTeamBeamScore(homeCopy.getBeamGymnasts()));
            for (int k = 0; k < judgeScoreList.size(); k++)
            {
                //match the score to the appropriate judge object
                judgeScoreList.get(k).createScore(team1App.getText(), homeCopy.getBeamGymnasts().get(team1Combo.getSelectedIndex()), 0, scoresList.get(k));
                beamJudges.get(k).addScore(judgeScoreList.get(k));

            }
        }
        else if (rotation == 4) {
            homeCopy.getFloorGymnasts().get(team1Combo.getSelectedIndex()).getPlayerScore().setfloorScore(pscore);
            homeCopy.getTeamScore().setfloorScore(homeCopy.getTeamScore().calculateTeamFloorScore(homeCopy.getFloorGymnasts()));
            for (int k = 0; k < judgeScoreList.size(); k++)
            {
                //match the score to the appropriate judge object
                judgeScoreList.get(k).createScore(team1App.getText(), homeCopy.getFloorGymnasts().get(team1Combo.getSelectedIndex()), 0, scoresList.get(k));
                floorJudges.get(k).addScore(judgeScoreList.get(k));
            }
            System.out.println(judgeScoreList);
        }

        myArenaScreen.gymnastCurrent1.setText("Gymnast Current Score: " + String.valueOf(pscore));       //update score on arena screen
        myArenaScreen.overall1.setText(String.valueOf("Running Team Score:     " + homeCopy.getTeamScore().getRunningScore()));
    }

    private void setPlayer2andTeamScore(List<Double> scoresList, Double deduction, List<JudgeScore> judgeScoreList, Dual_Tri_ArenaScreen myArenaScreen)
    {PlayerScore tempScore = new PlayerScore(); //holder to use calculation method
        double pscore = tempScore.calculateIndividualScore(scoresList, deduction);

        //these set the appropriate gymnast's apparatus score depending on rotation and team score
        if (rotation == 1) {
            visitorCopy.getBarGymnasts().get(team2Combo.getSelectedIndex()).getPlayerScore().setbarScore(pscore);
            visitorCopy.getTeamScore().setbarScore(visitorCopy.getTeamScore().calculateTeamBarScore(visitorCopy.getBarGymnasts()));
            for (int k = 0; k < judgeScoreList.size(); k++)
            {
                //match the score to the appropriate judge object
                judgeScoreList.get(k).createScore(team2App.getText(), visitorCopy.getBarGymnasts().get(team2Combo.getSelectedIndex()), 0, scoresList.get(k));
                barJudges.get(k).addScore(judgeScoreList.get(k));
            }
        }
        else if (rotation == 2) {
            visitorCopy.getVaultGymnasts().get(team2Combo.getSelectedIndex()).getPlayerScore().setvaultScore(pscore);
            visitorCopy.getTeamScore().setvaultScore(visitorCopy.getTeamScore().calculateTeamVaultScore(visitorCopy.getVaultGymnasts()));
            for (int k = 0; k < judgeScoreList.size(); k++)
            {
                //match the score to the appropriate judge object
                judgeScoreList.get(k).createScore(team2App.getText(), visitorCopy.getVaultGymnasts().get(team2Combo.getSelectedIndex()), 0, scoresList.get(k));
                vaultJudges.get(k).addScore(judgeScoreList.get(k));
            }
        }
        else if (rotation == 3) {
            visitorCopy.getFloorGymnasts().get(team2Combo.getSelectedIndex()).getPlayerScore().setfloorScore(pscore);
            visitorCopy.getTeamScore().setfloorScore(visitorCopy.getTeamScore().calculateTeamFloorScore(visitorCopy.getFloorGymnasts()));
            for (int k = 0; k < judgeScoreList.size(); k++)
            {
                //match the score to the appropriate judge object
                judgeScoreList.get(k).createScore(team2App.getText(), visitorCopy.getFloorGymnasts().get(team2Combo.getSelectedIndex()), 0, scoresList.get(k));
                floorJudges.get(k).addScore(judgeScoreList.get(k));
            }
        }
        else if (rotation == 4) {
            visitorCopy.getBeamGymnasts().get(team2Combo.getSelectedIndex()).getPlayerScore().setbeamScore(pscore);
            visitorCopy.getTeamScore().setbeamScore(visitorCopy.getTeamScore().calculateTeamBeamScore(visitorCopy.getBeamGymnasts()));
            for (int k = 0; k < judgeScoreList.size(); k++)
            {
                //match the score to the appropriate judge object
                judgeScoreList.get(k).createScore(team2App.getText(), visitorCopy.getBeamGymnasts().get(team2Combo.getSelectedIndex()), 0, scoresList.get(k));
                beamJudges.get(k).addScore(judgeScoreList.get(k));
            }
        }
        myArenaScreen.gymnastCurrent2.setText(String.valueOf("Gymnast Current Score: " + pscore));       //update score on arena screen
        myArenaScreen.overall2.setText(String.valueOf("Running Team Score:     " + visitorCopy.getTeamScore().getRunningScore()));
    }

    public Team homeCopy;
    public Team visitorCopy;

    private List<JLabel> getHomeJudges(){
        return Arrays.asList(jL11, jL12, jL13, jL14, jL15, jL16);
    }
    private List<JLabel> getVisitor1Judges(){
        return Arrays.asList(jL21, jL22, jL23, jL24, jL25, jL26);
    }
    private List<JTextField> getHomeJudgesTextbox(){
        return Arrays.asList(j11, j12, j13, j14, j15, j16);
    }
    private List<JTextField> getVisitor1JudgesTextbox(){
        return Arrays.asList(j21, j22, j23, j24, j25, j26);
    }






    //Directory for pictures folder
    String picturePath = System.getProperty("user.dir") + "/pictures/";



    public String currentevent1 = "Vault";
    public String currentevent2 = "Bars";


    private Boolean showUpdate = true; //if true, it's time to show update, if false, go to next rotation
    private Color defaultColor = new Color(51, 51, 51);
    private int selectedMode = 0;
    private int rotation = 1;
    private JButton startTimerButton;
    private JTextField clockTextField;
    private JPanel mainPanel;
    private JButton postMeetModeButton;
    private JPanel scorekeeperScreen;
    private JPanel customizeScreen;
    private JButton defaultTemplateButton;
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
    private JCheckBox nameCheckbox;
    private JCheckBox simultaneousCheckBox;
    private JCheckBox timerCheckbox;
    private JCheckBox majorCheckbox;
    private JCheckBox yearCheckbox;
    private JCheckBox avgCheckbox;
    private JCheckBox currentScoreCheckbox;
    private JCheckBox teamScoreCheckbox;
    private JCheckBox pictureCheckbox;
    private JCheckBox teamLogoCheckbox;
    private JTextField nD1;
    private JTextField nD2;
    private JLabel homeTeamName;
    private JLabel visitorTeamName;
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
    private JComboBox vaultCombo1;
    private JComboBox vaultCombo2;
    private CardLayout cardLayout;

}
