package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuadScorekeeperScreen {

    public QuadScorekeeperScreen(GuiCreator gC, Team home, Team visitor1, Team visitor2, Team visitor3, List<List<String>> allJudges){


        JFrame frame = new JFrame("Quadrangular Scorekeeper Screen");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        QuadArenaScreen myQuadArenaScreen = new QuadArenaScreen(gC);
        myQuadArenaScreen.getFrame().setVisible(true);

        homeCopy = home;
        visitor1Copy = visitor1;
        visitor2Copy = visitor2;
        visitor3Copy = visitor3;


        //Set Arena Screen Fields
        myQuadArenaScreen.teamName1.setText(homeCopy.getTeamName());
        myQuadArenaScreen.teamName2.setText(visitor1Copy.getTeamName());
        myQuadArenaScreen.teamName3.setText(visitor2Copy.getTeamName());
        myQuadArenaScreen.teamName4.setText(visitor3Copy.getTeamName());

        //Team Logos
        ImageIcon imageIcon = new ImageIcon(picturePath + homeCopy.getTeamLogo());
        myQuadArenaScreen.logo1.setIcon(gC.scaleImageIcon(imageIcon, 90, 90));

        imageIcon = new ImageIcon(picturePath + visitor1Copy.getTeamLogo());
        myQuadArenaScreen.logo2.setIcon(gC.scaleImageIcon(imageIcon, 90, 90));

        imageIcon = new ImageIcon(picturePath + visitor2Copy.getTeamLogo());
        myQuadArenaScreen.logo3.setIcon(gC.scaleImageIcon(imageIcon, 90, 90));

        imageIcon = new ImageIcon(picturePath + visitor3Copy.getTeamLogo());
        myQuadArenaScreen.logo4.setIcon(gC.scaleImageIcon(imageIcon, 90, 90));

        //Set Scoreboard Fields
        team1Name.setText(homeCopy.getTeamName());
        team2Name.setText(visitor1Copy.getTeamName());
        team3Name.setText(visitor2Copy.getTeamName());
        team4Name.setText(visitor3Copy.getTeamName());

        vaultCombo1.setVisible(false);
        vaultCombo2.setVisible(false);
        vaultCombo3.setVisible(false);
        vaultCombo4.setVisible(false);

        myQuadArenaScreen.getFrame().setVisible(true);
        myQuadArenaScreen.changeCard("ArenaCard");


        //card layout start
        cardLayout = (CardLayout) mainPanel.getLayout();
        changeCard("CustomizeCard");
        
        createJudges(allJudges);


        defaultTemplateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeCard("QuadScorekeeperCard");
                updateRotation(myQuadArenaScreen, frame, 0, gC, homeCopy, visitor1Copy, visitor2Copy, visitor3Copy, allJudges);
            }
        });

        startTimerButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startTimer(startTimerButton1, myQuadArenaScreen,timer1Textfield, 1);
            }
        });
        startTimerButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startTimer(startTimerButton2, myQuadArenaScreen,timer2Textfield, 2);
            }
        });
        startTimerButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startTimer(startTimerButton3, myQuadArenaScreen,timer3Textfield, 3);
            }
        });
        startTimerButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startTimer(startTimerButton4, myQuadArenaScreen,timer4Textfield, 4);
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
        timer3Textfield.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                timer3Textfield.setText("");
            }
        });
        timer4Textfield.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                timer4Textfield.setText("");
            }
        });
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (showUpdate == false) {
                    if (gC.confirmDialog("Are you sure you want to end Rotation " + rotation + "?")){
                        updateRotation(myQuadArenaScreen, frame, 1, gC, homeCopy, visitor1Copy, visitor2Copy, visitor3Copy, allJudges);

                        //show update on arena screen
                        myQuadArenaScreen.nextUpdateDual(homeCopy, visitor1Copy, visitor2Copy, visitor3Copy, rotation);
                        myQuadArenaScreen.changeCard("ArenaCard");

                        myQuadArenaScreen.resetArenaTables(); //reset tables
                        showUpdate = true;
                    }
                    else{
                        myQuadArenaScreen.changeCard("ArenaCard");
                        showUpdate = true;
                    }
                }
                else {
                    myQuadArenaScreen.nextUpdateDual(homeCopy, visitor1Copy, visitor2Copy, visitor3Copy, rotation);
                    showUpdate = false;
                }
            }
        });

        team1Combo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED){
                    if (currentevent1 == "Vault") {
                        myQuadArenaScreen.updateGymnastInfo(homeCopy.getVaultGymnasts().get(team1Combo.getSelectedIndex()), 1, 0);
                    }
                    else if (currentevent1 == "Bars") {
                        myQuadArenaScreen.updateGymnastInfo(homeCopy.getBarGymnasts().get(team1Combo.getSelectedIndex()), 1, 1);
                    }
                    else if (currentevent1 == "Beam"){
                        myQuadArenaScreen.updateGymnastInfo(homeCopy.getBeamGymnasts().get(team1Combo.getSelectedIndex()), 1, 2);
                    }
                    else if (currentevent1 == "Floor"){
                        myQuadArenaScreen.updateGymnastInfo(homeCopy.getFloorGymnasts().get(team1Combo.getSelectedIndex()), 1, 3);
                    }
                }
            }
        });
        team2Combo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED){
                    if (currentevent2 == "Vault") {
                        myQuadArenaScreen.updateGymnastInfo(visitor1Copy.getVaultGymnasts().get(team2Combo.getSelectedIndex()), 2, 0);
                    }
                    else if (currentevent2 == "Bars") {
                        myQuadArenaScreen.updateGymnastInfo(visitor1Copy.getBarGymnasts().get(team2Combo.getSelectedIndex()), 2, 1);
                    }
                    else if (currentevent2 == "Beam"){
                        myQuadArenaScreen.updateGymnastInfo(visitor1Copy.getBeamGymnasts().get(team2Combo.getSelectedIndex()), 2, 2);
                    }
                    else if (currentevent2 == "Floor"){
                        myQuadArenaScreen.updateGymnastInfo(visitor1Copy.getFloorGymnasts().get(team2Combo.getSelectedIndex()), 2, 3);
                    }
                }
            }
        });
        team3Combo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED){
                    if (currentevent3 == "Vault") {
                        myQuadArenaScreen.updateGymnastInfo(visitor2Copy.getVaultGymnasts().get(team3Combo.getSelectedIndex()), 3, 0);
                    }
                    else if (currentevent3 == "Bars") {
                        myQuadArenaScreen.updateGymnastInfo(visitor2Copy.getBarGymnasts().get(team3Combo.getSelectedIndex()), 3, 1);
                    }
                    else if (currentevent3 == "Beam"){
                        myQuadArenaScreen.updateGymnastInfo(visitor2Copy.getBeamGymnasts().get(team3Combo.getSelectedIndex()), 3, 2);
                    }
                    else if (currentevent3 == "Floor"){
                        myQuadArenaScreen.updateGymnastInfo(visitor2Copy.getFloorGymnasts().get(team3Combo.getSelectedIndex()), 3, 3);
                    }
                }
            }
        });
        team4Combo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED){
                    if (currentevent4 == "Vault") {
                        myQuadArenaScreen.updateGymnastInfo(visitor3Copy.getVaultGymnasts().get(team4Combo.getSelectedIndex()), 4, 0);
                    }
                    else if (currentevent4 == "Bars") {
                        myQuadArenaScreen.updateGymnastInfo(visitor3Copy.getBarGymnasts().get(team4Combo.getSelectedIndex()), 4, 1);
                    }
                    else if (currentevent4 == "Beam"){
                        myQuadArenaScreen.updateGymnastInfo(visitor3Copy.getBeamGymnasts().get(team4Combo.getSelectedIndex()), 4, 2);
                    }
                    else if (currentevent4 == "Floor"){
                        myQuadArenaScreen.updateGymnastInfo(visitor3Copy.getFloorGymnasts().get(team4Combo.getSelectedIndex()), 4, 3);
                    }
                }
            }
        });
        updateScoreButton1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                boolean invalidScore = false;
                boolean emptyScores = false;
                List<Double> scoresList = new ArrayList<>();        //to store text field data
                List<JudgeScore> judgeScoreList = new ArrayList<>(); //to store new instance of judge score for every class.
                float scoreArray[] = new float[6];
                try{
/*                    //Use these scores to update score for backend and arena screen
                    scoreArray[0] = Integer.parseInt(j11.getText());
                    scoreArray[1] = Integer.parseInt(j12.getText());
                    scoreArray[2] = Integer.parseInt(j13.getText());
                    scoreArray[3] = Integer.parseInt(j14.getText());
                    scoreArray[4] = Integer.parseInt(j15.getText());
                    scoreArray[5] = Integer.parseInt(j16.getText());*/


                    for (int i = 0; i<getHomeJudgesTextbox().size(); i++)
                    {
                        if(getHomeJudgesTextbox().get(i).getText().isEmpty() && getHomeJudgesTextbox().get(i).isVisible()){
                            emptyScores = true;
                        }
                        else if(!getHomeJudgesTextbox().get(i).getText().isEmpty()){
                            scoresList.add(Double.parseDouble(getHomeJudgesTextbox().get(i).getText()));
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
                    /*if (j11.getText().isEmpty() == true && j12.getText().isEmpty() == true && j13.getText().isEmpty() == true && j14.getText().isEmpty() == true && j15.getText().isEmpty() == true && j16.getText().isEmpty() == true)
                    {
                        emptyScores = true;
                        JOptionPane.showMessageDialog(null, "No scores were input.");
                    }
                    if (j11.getText().isEmpty() == false && j12.getText().isEmpty() == false) {
                        if (Double.parseDouble(j11.getText()) > 10 || Double.parseDouble(j12.getText()) > 10 || Double.parseDouble(j11.getText()) < 0 || Double.parseDouble(j12.getText()) < 0) {
                            JOptionPane.showMessageDialog(null, "Invalid input.");
                            invalidScore =true;
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
                        if (Double.parseDouble(j13.getText()) > 10 || Double.parseDouble(j14.getText()) > 10 || Double.parseDouble(j13.getText()) <0 || Double.parseDouble(j14.getText()) < 0) {
                            JOptionPane.showMessageDialog(null, "Invalid input.");
                            invalidScore =true;
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
                        if (Double.parseDouble(j15.getText()) > 10 || Double.parseDouble(j16.getText()) > 10 || Double.parseDouble(j15.getText()) <0 || Double.parseDouble(j16.getText()) <0) {
                            JOptionPane.showMessageDialog(null, "Invalid Input.");
                            invalidScore =true;
                        } else {
                            JudgeScore judgeScore5 = new JudgeScore();
                            JudgeScore judgeScore6 = new JudgeScore();
                            judgeScoreList.add(judgeScore5);
                            judgeScoreList.add(judgeScore6);
                            scoresList.add(Double.parseDouble(j15.getText()));
                            scoresList.add(Double.parseDouble(j16.getText()));
                        }
                    }*/

                    // deduction stuff
                    double deduction = 0;
                    if (!nD1.getText().isEmpty()) {
                        deduction = Double.parseDouble(nD1.getText());
                    }
                    //only set and update if there were no invalid scores and the text field were not all empty.
                    if (!emptyScores && !invalidScore) {
                        //Update flash
                        updateColor(1, myQuadArenaScreen);
                        setHomePlayerandTeamScore(scoresList, deduction, judgeScoreList, myQuadArenaScreen);
                    }

                } catch (Exception exception) {
                    System.out.println(exception);
                }
            }
        });
        
      updateScoreButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean invalidScore = false;
                boolean emptyScores = false;
                List<Double> scoresList = new ArrayList<>();        //to store text field data
                List<JudgeScore> judgeScoreList = new ArrayList<>(); //to store new instance of judge score for every class.
                float scoreArray[] = new float[6];
                try{
                    //Use these scores to update score for backend and arena screen
                  /*  scoreArray[0] = Integer.parseInt(j21.getText());
                    scoreArray[1] = Integer.parseInt(j22.getText());
                    scoreArray[2] = Integer.parseInt(j23.getText());
                    scoreArray[3] = Integer.parseInt(j24.getText());
                    scoreArray[4] = Integer.parseInt(j25.getText());
                    scoreArray[5] = Integer.parseInt(j26.getText());*/

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

                   /* if (j21.getText().isEmpty() == true && j22.getText().isEmpty() == true && j23.getText().isEmpty() == true && j24.getText().isEmpty() == true && j25.getText().isEmpty() == true && j26.getText().isEmpty() == true)
                    {
                        emptyScores = true;
                        JOptionPane.showMessageDialog(null, "No scores were input.");
                    }
                    if (j21.getText().isEmpty() == false && j22.getText().isEmpty() == false) {
                        if (Double.parseDouble(j21.getText()) > 10 || Double.parseDouble(j22.getText()) > 10 || Double.parseDouble(j21.getText()) < 0 || Double.parseDouble(j22.getText()) < 0) {
                            JOptionPane.showMessageDialog(null, "Invalid Input.");
                            invalidScore =true;
                        } else {
                            JudgeScore judgeScore1 = new JudgeScore();
                            JudgeScore judgeScore2 = new JudgeScore();
                            judgeScoreList.add(judgeScore1);
                            judgeScoreList.add(judgeScore2);
                            scoresList.add(Double.parseDouble(j21.getText()));
                            scoresList.add(Double.parseDouble(j22.getText()));
                        }
                    }
                    if (j23.getText().isEmpty() == false && j24.getText().isEmpty() == false) {
                        if (Double.parseDouble(j23.getText()) > 10 || Double.parseDouble(j24.getText()) > 10 || Double.parseDouble(j23.getText()) <0 || Double.parseDouble(j24.getText()) < 0) {
                            JOptionPane.showMessageDialog(null, "Invalid Input.");
                            invalidScore =true;
                        } else {
                            JudgeScore judgeScore3 = new JudgeScore();
                            JudgeScore judgeScore4 = new JudgeScore();
                            judgeScoreList.add(judgeScore3);
                            judgeScoreList.add(judgeScore4);
                            scoresList.add(Double.parseDouble(j23.getText()));
                            scoresList.add(Double.parseDouble(j24.getText()));
                        }
                    }
                    if (j25.getText().isEmpty() == false && j26.getText().isEmpty() == false) {
                        if (Double.parseDouble(j25.getText()) > 10 || Double.parseDouble(j26.getText()) > 10 || Double.parseDouble(j25.getText()) <0 || Double.parseDouble(j26.getText()) <0) {
                            JOptionPane.showMessageDialog(null, "Invalid Input.");
                            invalidScore =true;
                        } else {
                            JudgeScore judgeScore5 = new JudgeScore();
                            JudgeScore judgeScore6 = new JudgeScore();
                            judgeScoreList.add(judgeScore5);
                            judgeScoreList.add(judgeScore6);
                            scoresList.add(Double.parseDouble(j25.getText()));
                            scoresList.add(Double.parseDouble(j26.getText()));
                        }
                    }*/
                    // deduction stuff
                    double deduction = 0;
                    if (!nD2.getText().isEmpty()) {
                        deduction = Double.parseDouble(nD2.getText());
                    }
                    if (!invalidScore && !emptyScores){
                        updateColor(2, myQuadArenaScreen);
                        setVisitor1PlayerandTeamScore(scoresList, deduction, judgeScoreList, myQuadArenaScreen);
                    }

                } catch (Exception exception) {

                    System.out.println(exception);
                }

            }
        });
        updateScoreButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean invalidScore = false;
                boolean emptyScores = false;
                List<Double> scoresList = new ArrayList<>();        //to store text field data
                List<JudgeScore> judgeScoreList = new ArrayList<>(); //to store new instance of judge score for every class.
                try{

                    for (int i = 0; i<getVisitor2JudgesTextbox().size(); i++)
                    {
                        if(getVisitor2JudgesTextbox().get(i).getText().isEmpty() && getVisitor2JudgesTextbox().get(i).isVisible()){
                            emptyScores = true;
                        }
                        else if(!getVisitor2JudgesTextbox().get(i).getText().isEmpty()){
                            scoresList.add(Double.parseDouble(getVisitor2JudgesTextbox().get(i).getText()));
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
                   /* if (j31.getText().isEmpty() == true && j32.getText().isEmpty() == true && j33.getText().isEmpty() == true && j34.getText().isEmpty() == true && j35.getText().isEmpty() == true && j36.getText().isEmpty() == true)
                    {
                        emptyScores = true;
                        JOptionPane.showMessageDialog(null, "No scores were input.");
                    }
                    if (j31.getText().isEmpty() == false && j32.getText().isEmpty() == false) {
                        if (Double.parseDouble(j31.getText()) > 10 || Double.parseDouble(j32.getText()) > 10 || Double.parseDouble(j31.getText()) < 0 || Double.parseDouble(j32.getText()) < 0) {
                            JOptionPane.showMessageDialog(null, "Invalid Input.");
                            invalidScore =true;
                        } else {
                            JudgeScore judgeScore1 = new JudgeScore();
                            JudgeScore judgeScore2 = new JudgeScore();
                            judgeScoreList.add(judgeScore1);
                            judgeScoreList.add(judgeScore2);
                            scoresList.add(Double.parseDouble(j41.getText()));
                            scoresList.add(Double.parseDouble(j42.getText()));
                        }
                    }
                    if (j33.getText().isEmpty() == false && j34.getText().isEmpty() == false) {
                        if (Double.parseDouble(j33.getText()) > 10 || Double.parseDouble(j34.getText()) > 10 || Double.parseDouble(j33.getText()) <0 || Double.parseDouble(j34.getText()) < 0) {
                            JOptionPane.showMessageDialog(null, "Invalid Input.");
                            invalidScore =true;
                        } else {
                            JudgeScore judgeScore3 = new JudgeScore();
                            JudgeScore judgeScore4 = new JudgeScore();
                            judgeScoreList.add(judgeScore3);
                            judgeScoreList.add(judgeScore4);
                            scoresList.add(Double.parseDouble(j33.getText()));
                            scoresList.add(Double.parseDouble(j34.getText()));
                        }
                    }
                    if (j35.getText().isEmpty() == false && j36.getText().isEmpty() == false) {
                        if (Double.parseDouble(j35.getText()) > 10 || Double.parseDouble(j36.getText()) > 10 || Double.parseDouble(j35.getText()) <0 || Double.parseDouble(j36.getText()) <0) {
                            JOptionPane.showMessageDialog(null, "Invalid Input.");
                            invalidScore =true;
                        } else {
                            JudgeScore judgeScore5 = new JudgeScore();
                            JudgeScore judgeScore6 = new JudgeScore();
                            judgeScoreList.add(judgeScore5);
                            judgeScoreList.add(judgeScore6);
                            scoresList.add(Double.parseDouble(j35.getText()));
                            scoresList.add(Double.parseDouble(j36.getText()));
                        }
                    }*/
                    // deduction stuff
                    double deduction = 0;
                    if (!nD3.getText().isEmpty()) {
                        deduction = Double.parseDouble(nD3.getText());
                    }

                    if (!invalidScore && !emptyScores){
                        updateColor(3, myQuadArenaScreen);
                        setVisitor2PlayerandTeamScore(scoresList, deduction, judgeScoreList, myQuadArenaScreen);
                    }

                } catch (Exception exception) {

                    System.out.println(exception);
                }

            }
        });
        updateScoreButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean invalidScore = false;
                boolean emptyScores = false;
                List<Double> scoresList = new ArrayList<>();        //to store text field data
                List<JudgeScore> judgeScoreList = new ArrayList<>(); //to store new instance of judge score for every class.
                try{

                    for (int i = 0; i<getVisitor3JudgesTextbox().size(); i++)
                    {
                        if(getVisitor3JudgesTextbox().get(i).getText().isEmpty() && getVisitor3JudgesTextbox().get(i).isVisible()){
                            emptyScores = true;
                        }
                        else if(!getVisitor3JudgesTextbox().get(i).getText().isEmpty()){
                            scoresList.add(Double.parseDouble(getVisitor3JudgesTextbox().get(i).getText()));
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

                   /* if (j41.getText().isEmpty() == true && j42.getText().isEmpty() == true && j43.getText().isEmpty() == true && j44.getText().isEmpty() == true && j45.getText().isEmpty() == true && j46.getText().isEmpty() == true)
                    {
                        emptyScores = true;
                        JOptionPane.showMessageDialog(null, "No scores were input.");
                    }
                    if (j41.getText().isEmpty() == false && j42.getText().isEmpty() == false) {
                        if (Double.parseDouble(j41.getText()) > 10 || Double.parseDouble(j42.getText()) > 10 || Double.parseDouble(j41.getText()) < 0 || Double.parseDouble(j42.getText()) < 0) {
                            JOptionPane.showMessageDialog(null, "A score cannot be greater than 10.");
                            invalidScore =true;
                        } else {
                            JudgeScore judgeScore1 = new JudgeScore();
                            JudgeScore judgeScore2 = new JudgeScore();
                            judgeScoreList.add(judgeScore1);
                            judgeScoreList.add(judgeScore2);
                            scoresList.add(Double.parseDouble(j41.getText()));
                            scoresList.add(Double.parseDouble(j42.getText()));
                        }
                    }
                    if (j43.getText().isEmpty() == false && j44.getText().isEmpty() == false) {
                        if (Double.parseDouble(j43.getText()) > 10 || Double.parseDouble(j44.getText()) > 10 || Double.parseDouble(j43.getText()) <0 || Double.parseDouble(j44.getText()) < 0) {
                            JOptionPane.showMessageDialog(null, "A score cannot be greater than 10.");
                            invalidScore =true;
                        } else {
                            JudgeScore judgeScore3 = new JudgeScore();
                            JudgeScore judgeScore4 = new JudgeScore();
                            judgeScoreList.add(judgeScore3);
                            judgeScoreList.add(judgeScore4);
                            scoresList.add(Double.parseDouble(j43.getText()));
                            scoresList.add(Double.parseDouble(j44.getText()));
                        }
                    }
                    if (j45.getText().isEmpty() == false && j46.getText().isEmpty() == false) {
                        if (Double.parseDouble(j45.getText()) > 10 || Double.parseDouble(j46.getText()) > 10 || Double.parseDouble(j45.getText()) <0 || Double.parseDouble(j46.getText()) <0) {
                            JOptionPane.showMessageDialog(null, "A score cannot be greater than 10.");
                            invalidScore =true;
                        } else {
                            JudgeScore judgeScore5 = new JudgeScore();
                            JudgeScore judgeScore6 = new JudgeScore();
                            judgeScoreList.add(judgeScore5);
                            judgeScoreList.add(judgeScore6);
                            scoresList.add(Double.parseDouble(j45.getText()));
                            scoresList.add(Double.parseDouble(j46.getText()));
                        }
                    }*/
                    // deduction stuff
                    double deduction = 0;
                    if (!nD4.getText().isEmpty()) {
                        deduction = Double.parseDouble(nD4.getText());
                    }
                    if (!invalidScore && !emptyScores) {
                        updateColor(4, myQuadArenaScreen);
                        setVisitor3PlayerandTeamScore(scoresList, deduction, judgeScoreList, myQuadArenaScreen);
                    }

                } catch (Exception exception) {

                    System.out.println(exception);
                }

            }
        });
        timerCheckbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED){
                    myQuadArenaScreen.clockLabel1.setVisible(true);
                    myQuadArenaScreen.clockLabel2.setVisible(true);
                    myQuadArenaScreen.clockLabel3.setVisible(true);
                    myQuadArenaScreen.clockLabel4.setVisible(true);
                }
                else{
                    myQuadArenaScreen.clockLabel1.setVisible(false);
                    myQuadArenaScreen.clockLabel2.setVisible(false);
                    myQuadArenaScreen.clockLabel3.setVisible(false);
                    myQuadArenaScreen.clockLabel4.setVisible(false);
                }
            }
        });
        gymnastNameCheckbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED){
                     myQuadArenaScreen.name1.setVisible(true);
                    myQuadArenaScreen.name2.setVisible(true);
                    myQuadArenaScreen.name3.setVisible(true);
                    myQuadArenaScreen.name4.setVisible(true);
                }
                else{
                    myQuadArenaScreen.name1.setVisible(false);
                    myQuadArenaScreen.name2.setVisible(false);
                    myQuadArenaScreen.name3.setVisible(false);
                    myQuadArenaScreen.name4.setVisible(false);
                }
            }
        });
        gymnastMajorCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED){
                    myQuadArenaScreen.major1.setVisible(true);
                    myQuadArenaScreen.major2.setVisible(true);
                    myQuadArenaScreen.major3.setVisible(true);
                    myQuadArenaScreen.major4.setVisible(true);
                }
                else{
                    myQuadArenaScreen.major1.setVisible(false);
                    myQuadArenaScreen.major2.setVisible(false);
                    myQuadArenaScreen.major3.setVisible(false);
                    myQuadArenaScreen.major4.setVisible(false);
                }
            }
        });
        gymnastYearCheckbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED){
                    myQuadArenaScreen.year1.setVisible(true);
                    myQuadArenaScreen.year2.setVisible(true);
                    myQuadArenaScreen.year3.setVisible(true);
                    myQuadArenaScreen.year4.setVisible(true);
                }
                else{
                    myQuadArenaScreen.year1.setVisible(false);
                    myQuadArenaScreen.year2.setVisible(false);
                    myQuadArenaScreen.year3.setVisible(false);
                    myQuadArenaScreen.year4.setVisible(false);
                }
            }
        });
        gymnastavgCheckbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED){
                    myQuadArenaScreen.appAvg1.setVisible(true);
                    myQuadArenaScreen.appAvg2.setVisible(true);
                    myQuadArenaScreen.appAvg3.setVisible(true);
                    myQuadArenaScreen.appAvg4.setVisible(true);
                }
                else{
                    myQuadArenaScreen.appAvg4.setVisible(false);
                    myQuadArenaScreen.appAvg2.setVisible(false);
                    myQuadArenaScreen.appAvg3.setVisible(false);
                    myQuadArenaScreen.appAvg4.setVisible(false);
                }
            }
        });
        gymnastScoreCheckbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED){
                    myQuadArenaScreen.score1.setVisible(true);
                    myQuadArenaScreen.score2.setVisible(true);
                    myQuadArenaScreen.score3.setVisible(true);
                    myQuadArenaScreen.score4.setVisible(true);
                }
                else{
                    myQuadArenaScreen.score1.setVisible(false);
                    myQuadArenaScreen.score2.setVisible(false);
                    myQuadArenaScreen.score3.setVisible(false);
                    myQuadArenaScreen.score4.setVisible(false);
                }
            }
        });
        gymnastPictureLogo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED){
                    myQuadArenaScreen.pic1.setVisible(true);
                    myQuadArenaScreen.pic2.setVisible(true);
                    myQuadArenaScreen.pic3.setVisible(true);
                    myQuadArenaScreen.pic4.setVisible(true);
                }
                else{
                    myQuadArenaScreen.pic1.setVisible(false);
                    myQuadArenaScreen.pic2.setVisible(false);
                    myQuadArenaScreen.pic3.setVisible(false);
                    myQuadArenaScreen.pic4.setVisible(false);
                }
            }
        });
        teamLogoCheckbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                //team logo logic here
            }
        });
        editLineupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Keep track of the last player on the apparatus
                int team1current = team1Combo.getSelectedIndex();
                int team2current = team2Combo.getSelectedIndex();
                int team3current = team3Combo.getSelectedIndex();
                int team4current = team4Combo.getSelectedIndex();
                EditLineupScreen myScreen = new EditLineupScreen(homeCopy, visitor1Copy, visitor2Copy, visitor3Copy, rotation, "Quad");

                homeCopy = myScreen.getEditHome();
                visitor1Copy = myScreen.getEditVisitor();
                visitor2Copy = myScreen.getEditVisitor2();
                visitor3Copy = myScreen.getEditVisitor3();

                homeCopy.updateApparatusLists();
                visitor1Copy.updateApparatusLists();
                visitor2Copy.updateApparatusLists();
                visitor3Copy.updateApparatusLists();

                updateDisplay(myQuadArenaScreen, gC, homeCopy, visitor1Copy, visitor2Copy, visitor3, rotation);

                team1Combo.setSelectedIndex(team1current);
                team2Combo.setSelectedIndex(team2current);
                team3Combo.setSelectedIndex(team3current);
                team4Combo.setSelectedIndex(team4current);
            }
        });
    }


    private void startTimer(JButton button, QuadArenaScreen myQuadArenaScreen, JTextField timerText, int selectedClock){
        if (button.getText() == "Start Timer") {
            try {
                Integer.parseInt(timerText.getText());
                if (Integer.parseInt(timerText.getText()) > 0) {

                    switch (selectedClock){
                        case 1:
                            myQuadArenaScreen.clock1(Integer.parseInt(timerText.getText()));
                            break;
                        case 2:
                            myQuadArenaScreen.clock2(Integer.parseInt(timerText.getText()));
                            break;
                        case 3:
                            myQuadArenaScreen.clock3(Integer.parseInt(timerText.getText()));
                            break;
                        case 4:
                            myQuadArenaScreen.clock4(Integer.parseInt(timerText.getText()));
                            break;
                    }
                    button.setText("Reset Timer");
                }

            } catch (Exception exception) {
                System.out.println("Invalid Input");
            }
        } else {

            switch(selectedClock){
            case 1:
                myQuadArenaScreen.resetClock1();
                break;
            case 2:
                myQuadArenaScreen.resetClock2();
                break;
            case 3:
                myQuadArenaScreen.resetClock3();
                break;
            case 4:
                myQuadArenaScreen.resetClock4();
                break;
        }
            button.setText("Start Timer");
        }
    }



    public void changeCard(String cardName){
        cardLayout.show(mainPanel, cardName);
    }


    //pass in the frames that need to be handled and 1 if next rotation, -1 if previous
    //yes, a lot of this is redundant, but it's easy to read and change later
    private void updateRotation(QuadArenaScreen myArenaScreen, JFrame thisFrame, int value, GuiCreator gC, Team home, Team visitor1, Team visitor2, Team visitor3, List<List<String>> allJudges){

        rotation = rotation + value;
        myArenaScreen.updateRotation(rotation);

        if (rotation == 0){
            QuadScorekeeperScreen myScorekeeperScreen = new QuadScorekeeperScreen(gC, home, visitor1, visitor2, visitor3, allJudges);
            myScorekeeperScreen.changeCard("CustomizeCard");
            myArenaScreen.getFrame().dispose();
            thisFrame.dispose();
        }
        else if (rotation == 1){

            currentevent1 = "Vault";
            currentevent2 = "Floor";
            currentevent3 = "Beam";
            currentevent4 = "Bars";

            myArenaScreen.updateEvent(vaultCombo1.getSelectedItem().toString() + " " + currentevent1, 1);
            myArenaScreen.updateEvent(currentevent2, 2);
            myArenaScreen.updateEvent(currentevent3, 3);
            myArenaScreen.updateEvent(currentevent4, 4);

            rotationLabel.setText("ROTATION 1");

            updateJudgeNames(allJudges, rotation);

            gC.updateCombobox(team1Combo, home.getVaultGymnasts());
            gC.updateCombobox(team2Combo, visitor1.getFloorGymnasts());
            gC.updateCombobox(team3Combo, visitor2.getBeamGymnasts());
            gC.updateCombobox(team4Combo, visitor3.getBarGymnasts());


            vaultCombo1.setVisible(true);
            vaultCombo2.setVisible(false);
            vaultCombo3.setVisible(false);
            vaultCombo4.setVisible(false);

        }
        else if (rotation == 2){

            currentevent1 = "Bars";
            currentevent2 = "Vault";
            currentevent3 = "Floor";
            currentevent4 = "Beam";

            myArenaScreen.updateEvent(vaultCombo1.getSelectedItem().toString() + " " + currentevent1, 1);
            myArenaScreen.updateEvent(currentevent2, 2);
            myArenaScreen.updateEvent(currentevent3, 3);
            myArenaScreen.updateEvent(currentevent4, 4);

            rotationLabel.setText("ROTATION 2");

            updateJudgeNames(allJudges, rotation);

            gC.updateCombobox(team1Combo, home.getBarGymnasts());
            gC.updateCombobox(team2Combo, visitor1.getVaultGymnasts());
            gC.updateCombobox(team3Combo, visitor2.getFloorGymnasts());
            gC.updateCombobox(team4Combo, visitor3.getBeamGymnasts());


            vaultCombo1.setVisible(false);
            vaultCombo2.setVisible(true);
            vaultCombo3.setVisible(false);
            vaultCombo4.setVisible(false);

        }
        else if (rotation == 3){

            currentevent1 = "Beam";
            currentevent2 = "Bars";
            currentevent3 = "Vault";
            currentevent4 = "Floor";

            myArenaScreen.updateEvent(vaultCombo1.getSelectedItem().toString() + " " + currentevent1, 1);
            myArenaScreen.updateEvent(currentevent2, 2);
            myArenaScreen.updateEvent(currentevent3, 3);
            myArenaScreen.updateEvent(currentevent4, 4);

            rotationLabel.setText("ROTATION 3");

            updateJudgeNames(allJudges, rotation);

            gC.updateCombobox(team1Combo, home.getBeamGymnasts());
            gC.updateCombobox(team2Combo, visitor1.getBarGymnasts());
            gC.updateCombobox(team3Combo, visitor2.getVaultGymnasts());
            gC.updateCombobox(team4Combo, visitor3.getFloorGymnasts());


            vaultCombo1.setVisible(false);
            vaultCombo2.setVisible(false);
            vaultCombo3.setVisible(true);
            vaultCombo4.setVisible(false);
        }
        else if (rotation == 4){
            currentevent1 = "Floor";
            currentevent2 = "Beam";
            currentevent3 = "Bars";
            currentevent4 = "Vault";

            myArenaScreen.updateEvent(vaultCombo1.getSelectedItem().toString() + " " + currentevent1, 1);
            myArenaScreen.updateEvent(currentevent2, 2);
            myArenaScreen.updateEvent(currentevent3, 3);
            myArenaScreen.updateEvent(currentevent4, 4);

            rotationLabel.setText("ROTATION 4");

            updateJudgeNames(allJudges, rotation);

            gC.updateCombobox(team1Combo, home.getBarGymnasts());
            gC.updateCombobox(team2Combo, visitor1.getVaultGymnasts());
            gC.updateCombobox(team3Combo, visitor2.getFloorGymnasts());
            gC.updateCombobox(team4Combo, visitor3.getBeamGymnasts());


            vaultCombo1.setVisible(false);
            vaultCombo2.setVisible(false);
            vaultCombo3.setVisible(false);
            vaultCombo4.setVisible(true);
        }
        else if(rotation == 5){
            if (gC.confirmDialog("Are you sure you want to end the tournament?")){
                List <Team> teams = new ArrayList<>();
                teams.add(home);
                teams.add(visitor1);
                teams.add(visitor2);
                teams.add(visitor3);
                PostMeetScreen myPostMode = new PostMeetScreen(gC, teams);
                myArenaScreen.getFrame().dispose();
                thisFrame.dispose();
            }
            else {
                updateRotation(myArenaScreen, thisFrame, -1, gC, home, visitor1, visitor2, visitor3,  allJudges);
            }
        }

    }

    //big bloated code yikes   0: Vault, 1: Bars, 2: Beam, 3: Floor
    private void updateJudgeNames(List<List <String>> allJudges, int rotation){

        int h = 0;
        int v1 = 0;
        int v2 = 0;
        int v3 = 0;
        //def a smarter way to do this...
        if (rotation == 1){
            h = 0;
            v1 = 3;
            v2 = 2;
            v3 = 1;
        }
        else if (rotation == 2){
            h = 1;
            v1 = 0;
            v2 = 3;
            v3 = 2;
        }
        else if (rotation == 3){
            h = 2;
            v1 = 1;
            v2 = 0;
            v3 = 3;
        }
        else if (rotation == 4){
            h = 3;
            v1 = 2;
            v2 = 1;
            v3 = 0;
        }


        for (int i = 0; i < allJudges.get(h).size(); i++){
            if (allJudges.get(h).get(i) == ""){
                getHomeJudges().get(i).setVisible(false);
                getHomeJudgesTextbox().get(i).setVisible(false);
            }
            else{
                getHomeJudges().get(i).setVisible(true);
                getHomeJudgesTextbox().get(i).setVisible(true);
            }
            getHomeJudges().get(i).setText(allJudges.get(h).get(i));
        }
        for (int i = 0; i < allJudges.get(v1).size(); i++){
            if (allJudges.get(v1).get(i) == ""){
                getVisitor1Judges().get(i).setVisible(false);
                getVisitor1JudgesTextbox().get(i).setVisible(false);
            }
            else{
                getVisitor1Judges().get(i).setVisible(true);
                getVisitor1JudgesTextbox().get(i).setVisible(true);
            }
            getVisitor1Judges().get(i).setText(allJudges.get(v1).get(i));
        }
        for (int i = 0; i < allJudges.get(v2).size(); i++){
            if (allJudges.get(v2).get(i) == ""){
                getVisitor2Judges().get(i).setVisible(false);
                getVisitor2JudgesTextbox().get(i).setVisible(false);
            }
            else{
                getVisitor2Judges().get(i).setVisible(true);
                getVisitor2JudgesTextbox().get(i).setVisible(true);
            }
            getVisitor2Judges().get(i).setText(allJudges.get(v2).get(i));
        }
        for (int i = 0; i < allJudges.get(v3).size(); i++){
            if (allJudges.get(v3).get(i) == ""){
                getVisitor3Judges().get(i).setVisible(false);
                getVisitor3JudgesTextbox().get(i).setVisible(false);
            }
            else{
                getVisitor3Judges().get(i).setVisible(true);
                getVisitor3JudgesTextbox().get(i).setVisible(true);
            }
            getVisitor3Judges().get(i).setText(allJudges.get(v3).get(i));
        }
    }

    List<List<Judge>> judges = new ArrayList<List<Judge>>();
    List<Judge> floorJudges = new ArrayList<Judge>();
    List<Judge> beamJudges = new ArrayList<Judge>();
    List<Judge> vaultJudges = new ArrayList<Judge>();
    List<Judge> barJudges = new ArrayList<Judge>();

//dito
    public void updateDisplay( QuadArenaScreen myQuadArenaScreen, GuiCreator gC, Team home, Team visitor1, Team visitor2, Team visitor3,  int rotation) {
        team1Combo.removeAllItems();
        team2Combo.removeAllItems();
        team3Combo.removeAllItems();
        team4Combo.removeAllItems();
        System.out.println("Rotation: " + rotation);
        switch (rotation) {
            case 1:
                gC.updateCombobox(team1Combo, home.getVaultGymnasts());
                gC.updateCombobox(team2Combo, visitor1.getFloorGymnasts());
                gC.updateCombobox(team3Combo, visitor2.getBeamGymnasts());
                gC.updateCombobox(team4Combo, visitor3.getBarGymnasts());
                break;
            case 2:
                gC.updateCombobox(team1Combo, home.getBarGymnasts());
                gC.updateCombobox(team2Combo, visitor1.getVaultGymnasts());
                gC.updateCombobox(team3Combo, visitor2.getFloorGymnasts());
                gC.updateCombobox(team4Combo, visitor3.getBeamGymnasts());
                break;
            case 3:
                gC.updateCombobox(team1Combo, home.getBeamGymnasts());
                gC.updateCombobox(team2Combo, visitor1.getBarGymnasts());
                gC.updateCombobox(team3Combo, visitor2.getVaultGymnasts());
                gC.updateCombobox(team4Combo, visitor3.getFloorGymnasts());
                break;
            case 4:
                gC.updateCombobox(team1Combo, home.getFloorGymnasts());
                gC.updateCombobox(team2Combo, visitor1.getBeamGymnasts());
                gC.updateCombobox(team3Combo, visitor2.getBarGymnasts());
                gC.updateCombobox(team4Combo, visitor3.getVaultGymnasts());
                break;
        }

        //this logic is bad and cringe and redundant and did I say bad?
        //Updates the arena display for home
        if (currentevent1 == "Vault") {
            myQuadArenaScreen.updateGymnastInfo(home.getVaultGymnasts().get(team1Combo.getSelectedIndex()), 1, 0);
        } else if (currentevent1 == "Bars") {
            myQuadArenaScreen.updateGymnastInfo(home.getBarGymnasts().get(team1Combo.getSelectedIndex()), 1, 1);
        } else if (currentevent1 == "Beam") {
            myQuadArenaScreen.updateGymnastInfo(home.getBeamGymnasts().get(team1Combo.getSelectedIndex()), 1, 2);
        } else if (currentevent1 == "Floor") {
            myQuadArenaScreen.updateGymnastInfo(home.getFloorGymnasts().get(team1Combo.getSelectedIndex()), 1, 3);
        }

        //Updates the arena display for visitor1
        if (currentevent2 == "Vault") {
            myQuadArenaScreen.updateGymnastInfo(visitor1.getVaultGymnasts().get(team2Combo.getSelectedIndex()), 2, 0);
        } else if (currentevent2 == "Bars") {
            myQuadArenaScreen.updateGymnastInfo(visitor1.getBarGymnasts().get(team2Combo.getSelectedIndex()), 2, 1);
        } else if (currentevent2 == "Beam") {
            myQuadArenaScreen.updateGymnastInfo(visitor1.getBeamGymnasts().get(team2Combo.getSelectedIndex()), 2, 2);
        } else if (currentevent2 == "Floor") {
            myQuadArenaScreen.updateGymnastInfo(visitor1.getFloorGymnasts().get(team2Combo.getSelectedIndex()), 2, 3);
        }
        //Updates the arena display for visitor2
        if (currentevent3 == "Vault") {
            myQuadArenaScreen.updateGymnastInfo(visitor2.getVaultGymnasts().get(team3Combo.getSelectedIndex()), 3, 0);
        } else if (currentevent3 == "Bars") {
            myQuadArenaScreen.updateGymnastInfo(visitor2.getBarGymnasts().get(team3Combo.getSelectedIndex()), 3, 1);
        } else if (currentevent3 == "Beam") {
            myQuadArenaScreen.updateGymnastInfo(visitor2.getBeamGymnasts().get(team3Combo.getSelectedIndex()), 3, 2);
        } else if (currentevent3 == "Floor") {
            myQuadArenaScreen.updateGymnastInfo(visitor2.getFloorGymnasts().get(team3Combo.getSelectedIndex()), 3, 3);
        }
        //Updates the arena display for visitor3
        if (currentevent4 == "Vault") {
            myQuadArenaScreen.updateGymnastInfo(visitor3.getVaultGymnasts().get(team4Combo.getSelectedIndex()), 4, 0);
        } else if (currentevent4 == "Bars") {
            myQuadArenaScreen.updateGymnastInfo(visitor3.getBarGymnasts().get(team4Combo.getSelectedIndex()), 4, 1);
        } else if (currentevent4 == "Beam") {
            myQuadArenaScreen.updateGymnastInfo(visitor3.getBeamGymnasts().get(team4Combo.getSelectedIndex()), 4, 2);
        } else if (currentevent4 == "Floor") {
            myQuadArenaScreen.updateGymnastInfo(visitor3.getFloorGymnasts().get(team4Combo.getSelectedIndex()), 4, 3);
        }

    }

    private void updateColor(int selectedUpdate, QuadArenaScreen q){
        switch(selectedUpdate) {
            case 1:
                q.score1.setForeground(Color.RED);
                q.score2.setForeground(defaultColor);
                q.score3.setForeground(defaultColor);
                q.score4.setForeground(defaultColor);
                q.overall1.setForeground(Color.RED);
                q.overall2.setForeground(defaultColor);
                q.overall3.setForeground(defaultColor);
                q.overall4.setForeground(defaultColor);

                break;
            case 2:
                q.score1.setForeground(defaultColor);
                q.score2.setForeground(Color.RED);
                q.score3.setForeground(defaultColor);
                q.score4.setForeground(defaultColor);
                q.overall1.setForeground(defaultColor);
                q.overall2.setForeground(Color.RED);
                q.overall3.setForeground(defaultColor);
                q.overall4.setForeground(defaultColor);
                break;
            case 3:
                q.score1.setForeground(defaultColor);
                q.score2.setForeground(defaultColor);
                q.score3.setForeground(Color.RED);
                q.score4.setForeground(defaultColor);
                q.overall1.setForeground(defaultColor);
                q.overall2.setForeground(defaultColor);
                q.overall3.setForeground(Color.RED);
                q.overall4.setForeground(defaultColor);
                break;
            case 4:
                q.score1.setForeground(defaultColor);
                q.score2.setForeground(defaultColor);
                q.score3.setForeground(defaultColor);
                q.score4.setForeground(Color.RED);
                q.overall1.setForeground(defaultColor);
                q.overall2.setForeground(defaultColor);
                q.overall3.setForeground(defaultColor);
                q.overall4.setForeground(Color.RED);
                break;
        }
    }


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

    private void setHomePlayerandTeamScore(List<Double> scoresList, Double deduction, List<JudgeScore> judgeScoreList, QuadArenaScreen myQuadArenaScreen)
    {
        PlayerScore tempScore = new PlayerScore(); //holder to use calculation method
        double pscore = tempScore.calculateIndividualScore(scoresList, deduction);
        if (rotation == 1) {
            homeCopy.getVaultGymnasts().get(team1Combo.getSelectedIndex()).getPlayerScore().setvaultScore(pscore);
            homeCopy.getTeamScore().setvaultScore(homeCopy.getTeamScore().calculateTeamVaultScore(homeCopy.getVaultGymnasts()));
            // go through the list of judges scores (these are directly from the text boxes
            //set the createScore method in judges
            // score object, add it to the right judge object scores list
            for (int k = 0; k < judgeScoreList.size(); k++) {
                //match the score to the appropriate judge object
                judgeScoreList.get(k).createScore(team1App.getText(), homeCopy.getVaultGymnasts().get(team1Combo.getSelectedIndex()), 0, scoresList.get(k));
                vaultJudges.get(k).addScore(judgeScoreList.get(k));
            }

        } else if (rotation == 2) {
            homeCopy.getBarGymnasts().get(team1Combo.getSelectedIndex()).getPlayerScore().setbarScore(pscore);
            homeCopy.getTeamScore().setbarScore(homeCopy.getTeamScore().calculateTeamBarScore(homeCopy.getBarGymnasts()));
            for (int k = 0; k < judgeScoreList.size(); k++) {
                //match the score to the appropriate judge object
                judgeScoreList.get(k).createScore(team1App.getText(), homeCopy.getBarGymnasts().get(team1Combo.getSelectedIndex()), 0, scoresList.get(k));
                barJudges.get(k).addScore(judgeScoreList.get(k));
            }
        } else if (rotation == 3) {
            homeCopy.getBeamGymnasts().get(team1Combo.getSelectedIndex()).getPlayerScore().setbeamScore(pscore);
            homeCopy.getTeamScore().setbeamScore(homeCopy.getTeamScore().calculateTeamBeamScore(homeCopy.getBeamGymnasts()));
            for (int k = 0; k < judgeScoreList.size(); k++) {
                //match the score to the appropriate judge object
                judgeScoreList.get(k).createScore(team1App.getText(), homeCopy.getBeamGymnasts().get(team1Combo.getSelectedIndex()), 0, scoresList.get(k));
                beamJudges.get(k).addScore(judgeScoreList.get(k));

            }
        } else if (rotation == 4) {
            homeCopy.getFloorGymnasts().get(team1Combo.getSelectedIndex()).getPlayerScore().setfloorScore(pscore);
            homeCopy.getTeamScore().setfloorScore(homeCopy.getTeamScore().calculateTeamFloorScore(homeCopy.getFloorGymnasts()));
            for (int k = 0; k < judgeScoreList.size(); k++) {
                //match the score to the appropriate judge object
                judgeScoreList.get(k).createScore(team1App.getText(), homeCopy.getFloorGymnasts().get(team1Combo.getSelectedIndex()), 0, scoresList.get(k));
                floorJudges.get(k).addScore(judgeScoreList.get(k));
            }}
        myQuadArenaScreen.score1.setText("Gymnast Current Score: " + String.valueOf(pscore));       //update score on arena screen
        myQuadArenaScreen.overall1.setText(String.valueOf("Running Team Score:     " + homeCopy.getTeamScore().getRunningScore()));
    }

    private void setVisitor1PlayerandTeamScore(List<Double> scoresList, Double deduction, List<JudgeScore> judgeScoreList, QuadArenaScreen myQuadArenaScreen)
    {
        PlayerScore tempScore = new PlayerScore(); //holder to use calculation method
        double pscore = tempScore.calculateIndividualScore(scoresList, deduction);
        if (rotation == 1) {
            visitor1Copy.getFloorGymnasts().get(team2Combo.getSelectedIndex()).getPlayerScore().setfloorScore(pscore);
            visitor1Copy.getTeamScore().setfloorScore(visitor1Copy.getTeamScore().calculateTeamFloorScore(visitor1Copy.getFloorGymnasts()));
            for (int k = 0; k < judgeScoreList.size(); k++)
            {
                //match the score to the appropriate judge object
                judgeScoreList.get(k).createScore(team2App.getText(), visitor1Copy.getFloorGymnasts().get(team2Combo.getSelectedIndex()), 0, scoresList.get(k));
                floorJudges.get(k).addScore(judgeScoreList.get(k));
            }
        }
        else if (rotation == 2) {
            visitor1Copy.getVaultGymnasts().get(team2Combo.getSelectedIndex()).getPlayerScore().setvaultScore(pscore);
            visitor1Copy.getTeamScore().setvaultScore(visitor1Copy.getTeamScore().calculateTeamVaultScore(visitor1Copy.getVaultGymnasts()));
            for (int k = 0; k < judgeScoreList.size(); k++)
            {
                //match the score to the appropriate judge object
                judgeScoreList.get(k).createScore(team2App.getText(), visitor1Copy.getVaultGymnasts().get(team2Combo.getSelectedIndex()), 0, scoresList.get(k));
                vaultJudges.get(k).addScore(judgeScoreList.get(k));
            }
        }
        else if (rotation == 3) {
            visitor1Copy.getBarGymnasts().get(team2Combo.getSelectedIndex()).getPlayerScore().setbarScore(pscore);
            visitor1Copy.getTeamScore().setbarScore(visitor1Copy.getTeamScore().calculateTeamBarScore(visitor1Copy.getBarGymnasts()));
            for (int k = 0; k < judgeScoreList.size(); k++)
            {
                //match the score to the appropriate judge object
                judgeScoreList.get(k).createScore(team2App.getText(), visitor1Copy.getBarGymnasts().get(team2Combo.getSelectedIndex()), 0, scoresList.get(k));
                barJudges.get(k).addScore(judgeScoreList.get(k));
            }
        }
        else if (rotation == 4) {
            visitor1Copy.getBeamGymnasts().get(team2Combo.getSelectedIndex()).getPlayerScore().setbeamScore(pscore);
            visitor1Copy.getTeamScore().setbeamScore(visitor2Copy.getTeamScore().calculateTeamBeamScore(visitor2Copy.getBeamGymnasts()));
            for (int k = 0; k < judgeScoreList.size(); k++) {
                //match the score to the appropriate judge object
                judgeScoreList.get(k).createScore(team2App.getText(), visitor1Copy.getBeamGymnasts().get(team2Combo.getSelectedIndex()), 0, scoresList.get(k));
                beamJudges.get(k).addScore(judgeScoreList.get(k));

            }
        }
        myQuadArenaScreen.score2.setText("Gymnast Current Score: " + String.valueOf(pscore));       //update score on arena screen
        myQuadArenaScreen.overall2.setText(String.valueOf("Running Team Score:     " + visitor1Copy.getTeamScore().getRunningScore()));

    }

    private void setVisitor2PlayerandTeamScore(List<Double> scoresList, Double deduction, List<JudgeScore> judgeScoreList, QuadArenaScreen myQuadArenaScreen)
        {
            PlayerScore tempScore = new PlayerScore(); //holder to use calculation method
            double pscore = tempScore.calculateIndividualScore(scoresList, deduction);
            if (rotation == 1) {
                visitor2Copy.getBeamGymnasts().get(team3Combo.getSelectedIndex()).getPlayerScore().setbeamScore(pscore);
                visitor2Copy.getTeamScore().setbeamScore(visitor2Copy.getTeamScore().calculateTeamBeamScore(visitor2Copy.getBeamGymnasts()));
                for (int k = 0; k < judgeScoreList.size(); k++) {
                    //match the score to the appropriate judge object
                    judgeScoreList.get(k).createScore(team3App.getText(), visitor1Copy.getBeamGymnasts().get(team3Combo.getSelectedIndex()), 0, scoresList.get(k));
                    beamJudges.get(k).addScore(judgeScoreList.get(k));

                }
            } else if (rotation == 2) {
                visitor2Copy.getFloorGymnasts().get(team3Combo.getSelectedIndex()).getPlayerScore().setfloorScore(pscore);
                visitor2Copy.getTeamScore().setfloorScore(visitor2Copy.getTeamScore().calculateTeamFloorScore(visitor2Copy.getFloorGymnasts()));
                for (int k = 0; k < judgeScoreList.size(); k++) {
                    //match the score to the appropriate judge object
                    judgeScoreList.get(k).createScore(team3App.getText(), visitor2Copy.getFloorGymnasts().get(team3Combo.getSelectedIndex()), 0, scoresList.get(k));
                    floorJudges.get(k).addScore(judgeScoreList.get(k));
                }
            } else if (rotation == 3) {
                visitor2Copy.getVaultGymnasts().get(team3Combo.getSelectedIndex()).getPlayerScore().setvaultScore(pscore);
                visitor2Copy.getTeamScore().setvaultScore(visitor2Copy.getTeamScore().calculateTeamVaultScore(visitor2Copy.getVaultGymnasts()));
                for (int k = 0; k < judgeScoreList.size(); k++) {
                    //match the score to the appropriate judge object
                    judgeScoreList.get(k).createScore(team3App.getText(), visitor2Copy.getVaultGymnasts().get(team3Combo.getSelectedIndex()), 0, scoresList.get(k));
                    vaultJudges.get(k).addScore(judgeScoreList.get(k));
                }
            } else if (rotation == 4) {
                visitor2Copy.getBarGymnasts().get(team3Combo.getSelectedIndex()).getPlayerScore().setbarScore(pscore);
                visitor2Copy.getTeamScore().setbarScore(visitor2Copy.getTeamScore().calculateTeamBarScore(visitor2Copy.getBarGymnasts()));
                for (int k = 0; k < judgeScoreList.size(); k++) {
                    //match the score to the appropriate judge object
                    judgeScoreList.get(k).createScore(team3App.getText(), visitor2Copy.getBarGymnasts().get(team3Combo.getSelectedIndex()), 0, scoresList.get(k));
                    barJudges.get(k).addScore(judgeScoreList.get(k));
                }
            }
            myQuadArenaScreen.score3.setText("Gymnast Current Score: " + String.valueOf(pscore));       //update score on arena screen
            myQuadArenaScreen.overall3.setText(String.valueOf("Running Team Score:     " + visitor2Copy.getTeamScore().getRunningScore()));
        }

    private void setVisitor3PlayerandTeamScore(List<Double> scoresList, Double deduction, List<JudgeScore> judgeScoreList, QuadArenaScreen myQuadArenaScreen) throws IOException {
        PlayerScore tempScore = new PlayerScore(); //holder to use calculation method
        double pscore = tempScore.calculateIndividualScore(scoresList, deduction);
        if (rotation == 1) {
            visitor3Copy.getBarGymnasts().get(team4Combo.getSelectedIndex()).getPlayerScore().setbarScore(pscore);
            visitor3Copy.getTeamScore().setbarScore(visitor3Copy.getTeamScore().calculateTeamBarScore(visitor3Copy.getBarGymnasts()));
            for (int k = 0; k < judgeScoreList.size(); k++)
            {
                //match the score to the appropriate judge object
                judgeScoreList.get(k).createScore(team4App.getText(), visitor3Copy.getBarGymnasts().get(team4Combo.getSelectedIndex()), 0, scoresList.get(k));
                barJudges.get(k).addScore(judgeScoreList.get(k));
            }
        }
        else if (rotation == 2) {
            visitor3Copy.getBeamGymnasts().get(team4Combo.getSelectedIndex()).getPlayerScore().setbeamScore(pscore);
            visitor3Copy.getTeamScore().setbeamScore(visitor3Copy.getTeamScore().calculateTeamBeamScore(visitor3Copy.getBeamGymnasts()));
            for (int k = 0; k < judgeScoreList.size(); k++)
            {
                //match the score to the appropriate judge object
                judgeScoreList.get(k).createScore(team4App.getText(), visitor1Copy.getBeamGymnasts().get(team4Combo.getSelectedIndex()), 0, scoresList.get(k));
                beamJudges.get(k).addScore(judgeScoreList.get(k));

            }
        }
        else if (rotation == 3) {

            visitor3Copy.getFloorGymnasts().get(team4Combo.getSelectedIndex()).getPlayerScore().setfloorScore(pscore);
            visitor3Copy.getTeamScore().setfloorScore(visitor3Copy.getTeamScore().calculateTeamFloorScore(visitor3Copy.getFloorGymnasts()));
            for (int k = 0; k < judgeScoreList.size(); k++)
            {
                //match the score to the appropriate judge object
                judgeScoreList.get(k).createScore(team4App.getText(), visitor3Copy.getFloorGymnasts().get(team4Combo.getSelectedIndex()), 0, scoresList.get(k));
                floorJudges.get(k).addScore(judgeScoreList.get(k));
            }
        }
        else if (rotation == 4) {
            visitor3Copy.getVaultGymnasts().get(team4Combo.getSelectedIndex()).getPlayerScore().setvaultScore(pscore);
            visitor3Copy.getTeamScore().setvaultScore(visitor3Copy.getTeamScore().calculateTeamVaultScore(visitor3Copy.getVaultGymnasts()));
            for (int k = 0; k < judgeScoreList.size(); k++)
            {
                //match the score to the appropriate judge object
                judgeScoreList.get(k).createScore(team4App.getText(), visitor3Copy.getVaultGymnasts().get(team4Combo.getSelectedIndex()), 0, scoresList.get(k));
                vaultJudges.get(k).addScore(judgeScoreList.get(k));
            }
        }

            myQuadArenaScreen.score4.setText("Gymnast Current Score: " + String.valueOf(pscore));       //update score on arena screen
            myQuadArenaScreen.overall4.setText(String.valueOf("Running Team Score:     " + visitor3Copy.getTeamScore().getRunningScore()));

            PostMeetResults postresult = new PostMeetResults("quad", homeCopy, visitor1Copy, visitor2Copy, visitor3Copy, new File("RESULTS.txt"), judges);

    }

    public Team homeCopy;
    public Team visitor1Copy;
    public Team visitor2Copy;
    public Team visitor3Copy;

    private List<JLabel> getHomeJudges(){
        return Arrays.asList(jL11, jL12, jL13, jL14, jL15, jL16);
    }
    private List<JLabel> getVisitor1Judges(){
        return Arrays.asList(jL21, jL22, jL23, jL24, jL25, jL26);
    }
    private List<JLabel> getVisitor2Judges(){
        return Arrays.asList(jL31, jL32, jL33, jL34, jL35, jL36);
    }
    private List<JLabel> getVisitor3Judges(){
        return Arrays.asList(jL41, jL42, jL43, jL44, jL45, jL46);
    }

    private List<JTextField> getHomeJudgesTextbox(){
        return Arrays.asList(j11, j12, j13, j14, j15, j16);
    }
    private List<JTextField> getVisitor1JudgesTextbox(){
        return Arrays.asList(j21, j22, j23, j24, j25, j26);
    }
    private List<JTextField> getVisitor2JudgesTextbox(){
        return Arrays.asList(j31, j32, j33, j34, j35, j36);
    }
    private List<JTextField> getVisitor3JudgesTextbox(){
        return Arrays.asList(j41, j42, j43, j44, j45, j46);
    }


    public String currentevent1 = "Vault";
    public String currentevent2 = "Floor";
    public String currentevent3 = "Beam";
    public String currentevent4 = "Bars";



    private int rotation = 1;
    private Boolean showUpdate = true; //if true, it's time to show update, if false, go to next rotation

    private Color defaultColor = new Color(51, 51, 51); //default font color

    //Directory for pictures folder
    String picturePath = System.getProperty("user.dir") + "/pictures/";

    private JPanel mainPanel;
    private JPanel customizeScreen;
    private JButton defaultTemplateButton;
    private JCheckBox gymnastMajorCheckBox;
    private JPanel quadScorekeeperScreen;
    private JLabel rotationLabel;
    private JTextField j11;
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
    private JLabel team1Name;
    private JLabel team2Name;
    private JButton updateScoreButton1;
    private JButton updateScoreButton2;
    private JTextField j21;
    private JButton nextButton;
    private JTextField j31;
    private JTextField j32;
    private JTextField j33;
    private JTextField j34;
    private JTextField j35;
    private JTextField j36;
    private JTextField j41;
    private JTextField j42;
    private JTextField j43;
    private JTextField j44;
    private JTextField j45;
    private JTextField j46;
    private JButton updateScoreButton3;
    private JButton updateScoreButton4;
    private JTextField timer3Textfield;
    private JTextField timer4Textfield;
    private JButton startTimerButton3;
    private JButton startTimerButton4;
    private JComboBox team3Combo;
    private JComboBox team4Combo;
    private JLabel team3App;
    private JLabel team4App;
    private JLabel team3Name;
    private JLabel team4Name;
    private JButton editLineupButton;
    private JCheckBox gymnastNameCheckbox;
    private JCheckBox gymnastYearCheckbox;
    private JCheckBox gymnastavgCheckbox;
    private JCheckBox teamLogoCheckbox;
    private JCheckBox gymnastPictureLogo;
    private JCheckBox gymnastScoreCheckbox;
    private JCheckBox timerCheckbox;
    private JTextField nD1;
    private JTextField nD2;
    private JTextField nD3;
    private JTextField nD4;
    private JComboBox vaultCombo1;
    private JComboBox vaultCombo2;
    private JComboBox vaultCombo3;
    private JComboBox vaultCombo4;
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
    private JLabel jL31;
    private JLabel jL32;
    private JLabel jL33;
    private JLabel jL34;
    private JLabel jL35;
    private JLabel jL36;
    private JLabel jL41;
    private JLabel jL42;
    private JLabel jL43;
    private JLabel jL44;
    private JLabel jL45;
    private JLabel jL46;
    private CardLayout cardLayout;
}
