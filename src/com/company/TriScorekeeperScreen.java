package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TriScorekeeperScreen {

    public TriScorekeeperScreen(GuiCreator gC, Team home, Team visitor1, Team visitor2, List<List<String>> allJudges){

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
        visitor2Copy =visitor2;

        //card layout start
        cardLayout = (CardLayout) mainPanel.getLayout();
        changeCard("CustomizeCard");
        createJudges(allJudges);

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
            List<Double> scoresList  = new ArrayList<>();
            List<JudgeScore> judgeScoreList = new ArrayList<>();
            boolean invalidScore = false;
            boolean emptyScores = false;
            @Override
            public void actionPerformed(ActionEvent e) {
                float scoreArray[] = new float[6];
                try{
                    //Use these scores to update score for backend and arena screen
           /*         scoreArray[0] = Integer.parseInt(j11.getText());
                    scoreArray[1] = Integer.parseInt(j12.getText());
                    scoreArray[2] = Integer.parseInt(j13.getText());
                    scoreArray[3] = Integer.parseInt(j14.getText());
                    scoreArray[4] = Integer.parseInt(j15.getText());
                    scoreArray[5] = Integer.parseInt(j16.getText());*/
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
            }
        });
        updateScoreButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Double> scoresList  = new ArrayList<>();
                List<JudgeScore> judgeScoreList = new ArrayList<>();
                boolean invalidScore = false;
                boolean emptyScores = false;
                float scoreArray[] = new float[6];
                try{
                    //Use these scores to update score for backend and arena screen
                   /* scoreArray[0] = Integer.parseInt(j21.getText());
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
            List<Team> teams = new ArrayList<>();
            PostMeetScreen myPostMode = new PostMeetScreen(gC, teams);
            myArenaScreen.getFrame().dispose();
            thisFrame.dispose();
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


    private void setHomePlayerandTeamScore(List<Double> scoresList, Double deduction, List<JudgeScore> judgeScoreList, Dual_Tri_ArenaScreen myArenaScreen)
    {PlayerScore tempScore = new PlayerScore(); //holder to use calculation method
        double pscore = tempScore.calculateIndividualScore(scoresList, deduction);
        //Home team --> vault, visitor1--> Bars, visitor2-->Bye
        //Home is on vaults
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

            myArenaScreen.overall1.setText(String.valueOf("Running Team Score:     " + homeCopy.getTeamScore().getRunningScore()));
        }
        //Home team --> Bars, visitor1--> Bye, visitor2-->Vault
        //Visitor 2 is on Vaults
        else if (rotation == 2) {
            visitor2Copy.getVaultGymnasts().get(team1Combo.getSelectedIndex()).getPlayerScore().setvaultScore(pscore);
            visitor2Copy.getTeamScore().setvaultScore(visitor2Copy.getTeamScore().calculateTeamVaultScore(visitor2Copy.getVaultGymnasts()));
            for (int k = 0; k < judgeScoreList.size(); k++)
            {
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
            for (int k = 0; k < judgeScoreList.size(); k++)
            {
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
            for (int k = 0; k < judgeScoreList.size(); k++)
            {
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
            for (int k = 0; k < judgeScoreList.size(); k++)
            {
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
            for (int k = 0; k < judgeScoreList.size(); k++)
            {
                //match the score to the appropriate judge object
                judgeScoreList.get(k).createScore(team1App.getText(), visitor1Copy.getBeamGymnasts().get(team1Combo.getSelectedIndex()), 0, scoresList.get(k));
                beamJudges.get(k).addScore(judgeScoreList.get(k));
            }
            myArenaScreen.overall1.setText(String.valueOf("Running Team Score:     " + visitor1Copy.getTeamScore().getRunningScore()));
        }
        myArenaScreen.gymnastCurrent2.setText(String.valueOf("Gymnast Current Score: " + pscore));       //update score on arena screen
    }


    private void setVisitor1andTeamScore(List<Double> scoresList, Double deduction, List<JudgeScore> judgeScoreList, Dual_Tri_ArenaScreen myArenaScreen) {
        PlayerScore tempScore = new PlayerScore(); //holder to use calculation method
        double pscore = tempScore.calculateIndividualScore(scoresList, deduction);
        //Home team --> vault, visitor1--> Bars, visitor2-->Bye
        //Visitor1 is on Bars
        if (rotation == 1) {
            visitor1Copy.getBarGymnasts().get(team2Combo.getSelectedIndex()).getPlayerScore().setbarScore(pscore);
            visitor1Copy.getTeamScore().setbarScore(visitor1Copy.getTeamScore().calculateTeamBarScore(visitor1Copy.getVaultGymnasts()));
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
    }


    private List<JTextField> getHomeJudgesTextbox(){
        return Arrays.asList(j11, j12, j13, j14, j15, j16);
    }
    private List<JTextField> getVisitor1JudgesTextbox(){
        return Arrays.asList(j21, j22, j23, j24, j25, j26);
    }

    public Team homeCopy;
    public Team visitor1Copy;
    public Team visitor2Copy;


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
    private JTextField nD1;
    private JTextField nD2;
    private CardLayout cardLayout;
}
