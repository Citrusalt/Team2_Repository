package com.company;

import javax.sound.midi.SysexMessage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DualScorekeeperScreen {

    public DualScorekeeperScreen(GuiCreator gC, Team home, Team visitor, List<List<String>> allJudges) {





        JFrame frame = new JFrame("Scorekeeper Screen Prototype");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        DatabaseManager db = new DatabaseManager();


        Dual_Tri_ArenaScreen myArenaScreen = new Dual_Tri_ArenaScreen();

        //Set Arena Screen Fields
        myArenaScreen.teamName1.setText(home.getTeamName());
        myArenaScreen.teamName2.setText(visitor.getTeamName());
        myArenaScreen.logo1.setIcon(new ImageIcon("src/com/company/pictures/" + home.getTeamLogo()));
        myArenaScreen.logo2.setIcon(new ImageIcon("src/com/company/pictures/" + visitor.getTeamLogo()));

        homeTeamName.setText(home.getTeamName());
        visitorTeamName.setText(visitor.getTeamName());


        //logo
        //etc.

        myArenaScreen.getFrame().setVisible(true);


        //card layout start
        cardLayout = (CardLayout) mainPanel.getLayout();
        changeCard("CustomizeCard");

        createJudges(allJudges);




        defaultTemplateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeCard("SimulCard");
                if (selectedMode == 0){
                    updateRotationSimul(myArenaScreen, frame, 0, gC, home, visitor, allJudges);
                    //Fill Arena Screen
                }
                else if (selectedMode == 1){
                    team1App.setText("Vault");
                    team2App.setText("Vault");
                    myArenaScreen.event1.setText("Vault");
                    myArenaScreen.event2.setText("Vault");
                    //Fill Arena Screen
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
                if (selectedMode == 0){
                    updateRotationSimul(myArenaScreen, frame, 1, gC, home, visitor, allJudges);
                }
                else if (selectedMode == 1){
                    updateRotationNonSimul(myArenaScreen, frame, 1, gC, home, visitor, allJudges);
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedMode == 0){
                    updateRotationSimul(myArenaScreen, frame, -1, gC, home, visitor, allJudges);
                }
                else if (selectedMode == 1){
                    updateRotationNonSimul(myArenaScreen, frame, -1, gC, home, visitor, allJudges);
                }
            }
        });

        team1Combo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {

                    if (currentevent1 == "Vault") {
                        myArenaScreen.updateGymnastInfo(home.getVaultGymnasts().get(team1Combo.getSelectedIndex()), 1, 0);
                    }
                    else if (currentevent1 == "Bar") {
                        myArenaScreen.updateGymnastInfo(home.getBarGymnasts().get(team1Combo.getSelectedIndex()), 1, 1);
                    }
                    else if (currentevent1 == "Beam"){
                        myArenaScreen.updateGymnastInfo(home.getBeamGymnasts().get(team1Combo.getSelectedIndex()), 1, 2);
                    }
                    else if (currentevent1 == "Floor"){
                        myArenaScreen.updateGymnastInfo(home.getFloorGymnasts().get(team1Combo.getSelectedIndex()), 1, 3);
                    }
                }
            }

        });
        team2Combo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {

                    if (currentevent2 == "Vault") {
                        myArenaScreen.updateGymnastInfo(visitor.getVaultGymnasts().get(team2Combo.getSelectedIndex()), 2, 0);
                    }
                    else if (currentevent2 == "Bar") {
                        myArenaScreen.updateGymnastInfo(visitor.getBarGymnasts().get(team2Combo.getSelectedIndex()), 2, 1);
                    }
                    else if (currentevent2 == "Beam"){
                        myArenaScreen.updateGymnastInfo(visitor.getBeamGymnasts().get(team2Combo.getSelectedIndex()), 2, 2);
                    }
                    else if (currentevent2 == "Floor"){
                        myArenaScreen.updateGymnastInfo(visitor.getFloorGymnasts().get(team2Combo.getSelectedIndex()), 2, 3);
                    }
                }
            }
        });
        updateScoreButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float scoreArray[] = new float[7];
                List<Double> scoresList = new ArrayList<>();
                List<JudgeScore> judgeScoreList = new ArrayList<>();
                //List<Double> scoresList  = new ArrayList<>();
                //player instance here is just a placeholder
                // Player player1 = new Player("Name", "2022", "CS", "9.9");
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


                    System.out.println(myArenaScreen.gymnastCurrent1.getForeground());

                    myArenaScreen.gymnastCurrent1.setForeground(Color.RED);
                    myArenaScreen.overall1.setForeground(Color.RED);

                    myArenaScreen.gymnastCurrent2.setForeground(defaultColor);
                    myArenaScreen.overall2.setForeground(defaultColor);

                    //This is just a test input
//                    float avg = 0;
//
//                    for (float i : scoreArray){
//                        avg += i;
//                    }
//                    avg = avg/6;
//                    myArenaScreen.gymnastCurrent1.setText(String.valueOf(avg));

                    if (j11.getText().isEmpty() == false && j12.getText().isEmpty() == false) {
                        if (Double.parseDouble(j11.getText()) > 10 || Double.parseDouble(j12.getText()) > 10) {
                            JOptionPane.showMessageDialog(null, "A score cannot be greater than 10.");
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
                            JOptionPane.showMessageDialog(null, "A score cannot be greater than 10.");
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
                            JOptionPane.showMessageDialog(null, "A score cannot be greater than 10.");
                        } else {
                            JudgeScore judgeScore5 = new JudgeScore();
                            JudgeScore judgeScore6 = new JudgeScore();
                            judgeScoreList.add(judgeScore5);
                            judgeScoreList.add(judgeScore6);
                            scoresList.add(Double.parseDouble(j15.getText()));
                            scoresList.add(Double.parseDouble(j16.getText()));
                        }
                    }
                    double deduction = 0;
                    if (!nD1.getText().isEmpty()) {
                        deduction = Double.parseDouble(nD1.getText());
                    }
                    PlayerScore tempScore = new PlayerScore(); //holder to use calculation method
                    double pscore = tempScore.calculateIndividualScore(scoresList, deduction);

                    myArenaScreen.gymnastCurrent1.setText("Gymnast Current Score: " + String.valueOf(pscore));       //update score on arena screen

                    //these set the appropriate gymnast's apparatus score depending on rotation and team score
                    //matches the judge score object with the judge from the apparatus


                    if (rotation == 1) {
                        home.getVaultGymnasts().get(team1Combo.getSelectedIndex()).getPlayerScore().setvaultScore(pscore);
                        home.getTeamScore().setvaultScore(home.getTeamScore().calculateTeamVaultScore(home.getVaultGymnasts()));
                        // go through the list of judges scores (these are directly from the text boxes
                        //set the createScore method in judgescore object, add it to the right judge object scores list
                        for (int k = 0; k < judgeScoreList.size(); k++)
                        {
                            //match the score to the appropriate judge object
                            judgeScoreList.get(k).createScore(team1App.getText(), home.getVaultGymnasts().get(team1Combo.getSelectedIndex()), 0, scoresList.get(k));
                            vaultJudges.get(k).addScore(judgeScoreList.get(k));
                        }

                    }
                    else if (rotation == 2) {
                        home.getBarGymnasts().get(team1Combo.getSelectedIndex()).getPlayerScore().setbarScore(pscore);
                        home.getTeamScore().setbarScore(home.getTeamScore().calculateTeamBarScore(home.getBarGymnasts()));
                        for (int k = 0; k < judgeScoreList.size(); k++)
                        {
                            //match the score to the appropriate judge object
                            judgeScoreList.get(k).createScore(team1App.getText(), home.getBarGymnasts().get(team1Combo.getSelectedIndex()), 0, scoresList.get(k));
                            barJudges.get(k).addScore(judgeScoreList.get(k));
                        }
                    }
                    else if (rotation == 3) {
                        home.getBeamGymnasts().get(team1Combo.getSelectedIndex()).getPlayerScore().setbeamScore(pscore);
                        home.getTeamScore().setbeamScore(home.getTeamScore().calculateTeamBeamScore(home.getBeamGymnasts()));
                        for (int k = 0; k < judgeScoreList.size(); k++)
                        {
                            //match the score to the appropriate judge object
                            judgeScoreList.get(k).createScore(team1App.getText(), home.getBeamGymnasts().get(team1Combo.getSelectedIndex()), 0, scoresList.get(k));
                            beamJudges.get(k).addScore(judgeScoreList.get(k));

                        }
                    }
                    else if (rotation == 4) {
                        home.getFloorGymnasts().get(team1Combo.getSelectedIndex()).getPlayerScore().setfloorScore(pscore);
                        home.getTeamScore().setfloorScore(home.getTeamScore().calculateTeamFloorScore(home.getFloorGymnasts()));
                        for (int k = 0; k < judgeScoreList.size(); k++)
                        {
                            //match the score to the appropriate judge object
                            judgeScoreList.get(k).createScore(team1App.getText(), home.getFloorGymnasts().get(team1Combo.getSelectedIndex()), 0, scoresList.get(k));
                            floorJudges.get(k).addScore(judgeScoreList.get(k));
                        }
                        System.out.println(judgeScoreList);
                    }
                    myArenaScreen.overall1.setText(String.valueOf("Running Team Score:     " + home.getTeamScore().getRunningScore()));


                    for (int i = 0 ; i < judges.size(); i++){
                        for (int j = 0; j < judges.get(i).size(); j++) {
                            System.out.println(judges.get(i).get(j).getFname());
                            for (int k = 0; k < judges.get(i).get(j).getScoreList().size(); k++){
                                System.out.println(judges.get(i).get(j).getScoreList().get(k).getScoreAmt() + " " + judges.get(i).get(j).getScoreList().get(k).getPlayer().getPlayerlName() + " " + judges.get(i).get(j).getScoreList().get(k).getApparatusName());
                             }
                         }
                     }

                } catch (Exception exception) {
                    System.out.println(exception);
                }
            }
        });
        updateScoreButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Double> scoresList  = new ArrayList<>();
                List<JudgeScore> judgeScoreList = new ArrayList<>();
                float scoreArray[] = new float[7];
                try{
                    //Use these scores to update score for backend and arena screen
                    //doesn't have to be entered into a "scoreArray" just an example
                   /* scoreArray[0] = Integer.parseInt(j21.getText());
                    scoreArray[1] = Integer.parseInt(j22.getText());
                    scoreArray[2] = Integer.parseInt(j23.getText());
                    scoreArray[3] = Integer.parseInt(j24.getText());
                    scoreArray[4] = Integer.parseInt(j25.getText());
                    scoreArray[5] = Integer.parseInt(j26.getText());
                    scoreArray[6] = Integer.parseInt(nD2.getText());*/

                    myArenaScreen.gymnastCurrent2.setForeground(Color.RED);
                    myArenaScreen.overall2.setForeground(Color.RED);

                    myArenaScreen.gymnastCurrent1.setForeground(defaultColor);
                    myArenaScreen.overall1.setForeground(defaultColor);

                    if (!j21.getText().isEmpty() && !j22.getText().isEmpty()){
                        if (Double.parseDouble(j21.getText()) > 10 || Double.parseDouble(j22.getText()) >10)
                        {
                            JOptionPane.showMessageDialog(null, "A score cannot be greater than 10.");
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
                        if (Double.parseDouble(j23.getText()) > 10 || Double.parseDouble(j24.getText()) >10)
                        {
                            JOptionPane.showMessageDialog(null, "A score cannot be greater than 10.");
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
                        if (Double.parseDouble(j25.getText()) > 10 || Double.parseDouble(j26.getText()) >10)
                        {
                            JOptionPane.showMessageDialog(null, "A score cannot be greater than 10.");
                        }
                        else {
                            JudgeScore judgeScore5 = new JudgeScore();
                            JudgeScore judgeScore6 = new JudgeScore();
                            judgeScoreList.add(judgeScore5);
                            judgeScoreList.add(judgeScore6);
                            scoresList.add(Double.parseDouble(j25.getText()));
                            scoresList.add(Double.parseDouble(j26.getText()));
                        }
                    }

                    double deduction = 0;
                    if (!nD1.getText().isEmpty()) {
                        deduction = Double.parseDouble(nD1.getText());
                    }

                    PlayerScore tempScore = new PlayerScore(); //holder to use calculation method
                    double pscore = tempScore.calculateIndividualScore(scoresList, deduction);

                    myArenaScreen.gymnastCurrent2.setText(String.valueOf("Gymnast Current Score: " + pscore));       //update score on arena screen

                    //these set the appropriate gymnast's apparatus score depending on rotation and team score
                    if (rotation == 1) {
                        visitor.getBarGymnasts().get(team2Combo.getSelectedIndex()).getPlayerScore().setbarScore(pscore);
                        visitor.getTeamScore().setbarScore(visitor.getTeamScore().calculateTeamBarScore(visitor.getBarGymnasts()));
                        for (int k = 0; k < judgeScoreList.size(); k++)
                        {
                            //match the score to the appropriate judge object
                            judgeScoreList.get(k).createScore(team2App.getText(), visitor.getBarGymnasts().get(team2Combo.getSelectedIndex()), 0, scoresList.get(k));
                            barJudges.get(k).addScore(judgeScoreList.get(k));
                        }
                    }
                    else if (rotation == 2) {
                        visitor.getVaultGymnasts().get(team2Combo.getSelectedIndex()).getPlayerScore().setvaultScore(pscore);
                        visitor.getTeamScore().setvaultScore(visitor.getTeamScore().calculateTeamVaultScore(visitor.getVaultGymnasts()));
                        for (int k = 0; k < judgeScoreList.size(); k++)
                        {
                            //match the score to the appropriate judge object
                            judgeScoreList.get(k).createScore(team2App.getText(), visitor.getVaultGymnasts().get(team2Combo.getSelectedIndex()), 0, scoresList.get(k));
                            vaultJudges.get(k).addScore(judgeScoreList.get(k));
                        }
                    }
                    else if (rotation == 3) {
                        visitor.getFloorGymnasts().get(team2Combo.getSelectedIndex()).getPlayerScore().setfloorScore(pscore);
                        visitor.getTeamScore().setfloorScore(visitor.getTeamScore().calculateTeamFloorScore(visitor.getFloorGymnasts()));
                        for (int k = 0; k < judgeScoreList.size(); k++)
                        {
                            //match the score to the appropriate judge object
                            judgeScoreList.get(k).createScore(team2App.getText(), visitor.getFloorGymnasts().get(team2Combo.getSelectedIndex()), 0, scoresList.get(k));
                            floorJudges.get(k).addScore(judgeScoreList.get(k));
                        }
                    }
                    else if (rotation == 4) {
                        visitor.getBeamGymnasts().get(team2Combo.getSelectedIndex()).getPlayerScore().setbeamScore(pscore);
                        visitor.getTeamScore().setbeamScore(visitor.getTeamScore().calculateTeamBeamScore(visitor.getBeamGymnasts()));
                        for (int k = 0; k < judgeScoreList.size(); k++)
                        {
                            //match the score to the appropriate judge object
                            judgeScoreList.get(k).createScore(team2App.getText(), visitor.getBeamGymnasts().get(team2Combo.getSelectedIndex()), 0, scoresList.get(k));
                            beamJudges.get(k).addScore(judgeScoreList.get(k));
                        }
                    }
                    myArenaScreen.overall2.setText(String.valueOf("Running Team Score:     " + visitor.getTeamScore().getRunningScore()));

                } catch (Exception exception) {

                    System.out.println(exception);
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
                EditLineupScreen myScreen = new EditLineupScreen(home, visitor, rotation);
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
    }


    public void changeCard(String cardName){
        cardLayout.show(mainPanel, cardName);
    }


    //pass in the frames that need to be handled and 1 if next rotation, -1 if previous
    private void updateRotationSimul(Dual_Tri_ArenaScreen myArenaScreen, JFrame thisFrame, int value, GuiCreator gC, Team home, Team visitor, List<List<String>> allJudges){
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
            currentevent2 = "Bar";
            team1App.setText(currentevent1);
            team2App.setText(currentevent2);
            myArenaScreen.updateEvent(currentevent1, 1);
            myArenaScreen.updateEvent(currentevent2, 2);
            rotationLabel.setText("ROTATION 1");

            updateJudgeNamesSimul(allJudges, rotation);

            gC.updateCombobox(team1Combo, home.getVaultGymnasts());
            gC.updateCombobox(team2Combo, visitor.getBarGymnasts());

        }
        else if (rotation == 2){
            currentevent1 = "Bar";
            currentevent2 = "Vault";
            team1App.setText(currentevent1);
            team2App.setText(currentevent2);
            myArenaScreen.updateEvent(currentevent1, 1);
            myArenaScreen.updateEvent(currentevent2, 2);
            rotationLabel.setText("ROTATION 2");

            updateJudgeNamesSimul(allJudges, rotation);

            gC.updateCombobox(team1Combo, home.getBarGymnasts());
            gC.updateCombobox(team2Combo, visitor.getVaultGymnasts());
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

            int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to end the tournament?");

            if (dialogResult == JOptionPane.YES_OPTION){
                List <Team> teams = new ArrayList<>();
                teams.add(home);
                teams.add(visitor);
                PostMeetScreen myPostMode = new PostMeetScreen(gC, teams);
                myArenaScreen.getFrame().dispose();
                thisFrame.dispose();
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
            team1App.setText("Vault");
            team2App.setText("Vault");
            myArenaScreen.updateEvent("Vault", 1);
            myArenaScreen.updateEvent("Vault", 2);
            rotationLabel.setText("ROTATION 1");
            //update judges
            //update players
            //update scores
            //etc.
        }
        else if (rotation == 2){
            team1App.setText("Bars");
            team2App.setText("Bars");
            myArenaScreen.updateEvent("Bars", 1);
            myArenaScreen.updateEvent("Bars", 2);
            rotationLabel.setText("ROTATION 2");
            //update judges
            //update players
            //update scores
            //etc.
        }
        else if (rotation == 3){
            team1App.setText("Beam");
            team2App.setText("Beam");
            myArenaScreen.updateEvent("Beam", 1);
            myArenaScreen.updateEvent("Beam", 2);
            rotationLabel.setText("ROTATION 3");
        }
        else if (rotation == 4){
            team1App.setText("Floor");
            team2App.setText("Floor");
            myArenaScreen.updateEvent("Floor", 1);
            myArenaScreen.updateEvent("Floor", 2);
            rotationLabel.setText("ROTATION 4");
        }
        else if(rotation == 5){
            List<Team> teams = new ArrayList<>();
            teams.add(home);
            teams.add(visitor);
            PostMeetScreen myPostMode = new PostMeetScreen(gC, teams);
            myArenaScreen.getFrame().dispose();
            thisFrame.dispose();
        }

    }

    private void updateJudgeNamesSimul(List<List <String>> allJudges, int rotation){

//        System.out.println("Starting Judge Update...");

        if (rotation == 1){
            for (int i = 0; i < allJudges.get(0).size(); i++){
                getHomeJudges().get(i).setText(allJudges.get(0).get(i));
            }
            for (int i = 0; i < allJudges.get(1).size(); i++){
                getVisitor1Judges().get(i).setText(allJudges.get(1).get(i));
            }
        }
        else if (rotation == 2){
            for (int i = 0; i < allJudges.get(1).size(); i++){
                getHomeJudges().get(i).setText(allJudges.get(1).get(i));
            }
            for (int i = 0; i < allJudges.get(0).size(); i++){
                getVisitor1Judges().get(i).setText(allJudges.get(0).get(i));
            }
        }
        else if (rotation == 3){
            for (int i = 0; i < allJudges.get(2).size(); i++){
                getHomeJudges().get(i).setText(allJudges.get(2).get(i));
            }
            for (int i = 0; i < allJudges.get(3).size(); i++){
                getVisitor1Judges().get(i).setText(allJudges.get(3).get(i));
            }
        }
        else if (rotation == 4){
            for (int i = 0; i < allJudges.get(3).size(); i++){
                getHomeJudges().get(i).setText(allJudges.get(3).get(i));
            }
            for (int i = 0; i < allJudges.get(2).size(); i++){
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
        //all this a placeholder, just need somewhere to put judge objects
       // List<Judge> floorJudges = new ArrayList<Judge>();
       // List<Judge> beamJudges = new ArrayList<Judge>();
        //List<Judge> vaultJudges = new ArrayList<Judge>();
       // List<Judge> barJudges = new ArrayList<Judge>();
       // List<List<Judge>> judges = new ArrayList<List<Judge>>();
        judges.add(floorJudges);
        judges.add(barJudges);
        judges.add(vaultJudges);
        judges.add(beamJudges);
        for (int j = 0; j < allJudges.get(0).size(); j++){
            Judge judge = new Judge();
            judge.setFname(allJudges.get(0).get(j).toString());
            judge.setLname(allJudges.get(0).get(j).toString()); //not right but need to put something there
            vaultJudges.add(judge);
        }
        for (int j = 0; j < allJudges.get(1).size(); j++){
            Judge judge = new Judge();
            judge.setFname(allJudges.get(1).get(j).toString());
            judge.setLname(allJudges.get(1).get(j).toString()); //not right but need to put something there
            barJudges.add(judge);
        }
        for (int j = 0; j < allJudges.get(2).size(); j++){
            Judge judge = new Judge();
            judge.setFname(allJudges.get(2).get(j).toString());
            judge.setLname(allJudges.get(2).get(j).toString()); //not right but need to put something there
            beamJudges.add(judge);
        }
        for (int j = 0; j < allJudges.get(3).size(); j++){
            Judge judge = new Judge();
            judge.setFname(allJudges.get(3).get(j).toString());
            judge.setLname(allJudges.get(3).get(j).toString()); //not right but need to put something there
            floorJudges.add(judge);
        }
    }

    private List<JLabel> getHomeJudges(){
        return Arrays.asList(jL11, jL12, jL13, jL14, jL15, jL16);
    }
    private List<JLabel> getVisitor1Judges(){
        return Arrays.asList(jL21, jL22, jL23, jL24, jL25, jL26);
    }


    public String currentevent1 = "Vault";
    public String currentevent2 = "Bar";


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
    private CardLayout cardLayout;
}

