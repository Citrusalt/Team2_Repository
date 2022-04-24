package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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

                List<Double> scoresList = new ArrayList<>();
                List<JudgeScore> judgeScoreList = new ArrayList<>();

                try{

                    //Update Arena Screen Flash
                    updateColor(1, myQuadArenaScreen);

                    //do score stuff here


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

                try{



                    //Update Arena Screen Flash
                    updateColor(2, myQuadArenaScreen);

                } catch (Exception exception) {

                    System.out.println(exception);
                }

            }
        });
        updateScoreButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                List<Double> scoresList  = new ArrayList<>();
                List<JudgeScore> judgeScoreList = new ArrayList<>();

                try{
                    //Use these scores to update score for backend and arena screen


                    //Update Arena Screen Flash
                    updateColor(3, myQuadArenaScreen);

                } catch (Exception exception) {

                    System.out.println(exception);
                }

            }
        });
        updateScoreButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                List<Double> scoresList  = new ArrayList<>();
                List<JudgeScore> judgeScoreList = new ArrayList<>();

                try{

                    //Update Arena Screen Flash
                    updateColor(4, myQuadArenaScreen);

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
                //do stuff here
//                EditLineupScreen myScreen = new EditLineupScreen(homeCopy, visitorCopy, new Team("",""), rotation, "Quad");
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

    public void updateDisplay(Dual_Tri_ArenaScreen myArenaScreen, GuiCreator gC, Team home, Team visitor1, Team visitor2, Team visitor3,  int rotation) {
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
            myArenaScreen.updateGymnastInfo(home.getVaultGymnasts().get(team1Combo.getSelectedIndex()), 1, 0);
        } else if (currentevent1 == "Bars") {
            myArenaScreen.updateGymnastInfo(home.getBarGymnasts().get(team1Combo.getSelectedIndex()), 1, 1);
        } else if (currentevent1 == "Beam") {
            myArenaScreen.updateGymnastInfo(home.getBeamGymnasts().get(team1Combo.getSelectedIndex()), 1, 2);
        } else if (currentevent1 == "Floor") {
            myArenaScreen.updateGymnastInfo(home.getFloorGymnasts().get(team1Combo.getSelectedIndex()), 1, 3);
        }

        //Updates the arena display for visitor1
        if (currentevent2 == "Vault") {
            myArenaScreen.updateGymnastInfo(visitor1.getVaultGymnasts().get(team2Combo.getSelectedIndex()), 2, 0);
        } else if (currentevent2 == "Bars") {
            myArenaScreen.updateGymnastInfo(visitor1.getBarGymnasts().get(team2Combo.getSelectedIndex()), 2, 1);
        } else if (currentevent2 == "Beam") {
            myArenaScreen.updateGymnastInfo(visitor1.getBeamGymnasts().get(team2Combo.getSelectedIndex()), 2, 2);
        } else if (currentevent2 == "Floor") {
            myArenaScreen.updateGymnastInfo(visitor1.getFloorGymnasts().get(team2Combo.getSelectedIndex()), 2, 3);
        }
        //Updates the arena display for visitor2
        if (currentevent3 == "Vault") {
            myArenaScreen.updateGymnastInfo(visitor2.getVaultGymnasts().get(team3Combo.getSelectedIndex()), 3, 0);
        } else if (currentevent3 == "Bars") {
            myArenaScreen.updateGymnastInfo(visitor2.getBarGymnasts().get(team3Combo.getSelectedIndex()), 3, 1);
        } else if (currentevent3 == "Beam") {
            myArenaScreen.updateGymnastInfo(visitor2.getBeamGymnasts().get(team3Combo.getSelectedIndex()), 3, 2);
        } else if (currentevent3 == "Floor") {
            myArenaScreen.updateGymnastInfo(visitor2.getFloorGymnasts().get(team3Combo.getSelectedIndex()), 3, 3);
        }
        //Updates the arena display for visitor3
        if (currentevent4 == "Vault") {
            myArenaScreen.updateGymnastInfo(visitor3.getVaultGymnasts().get(team4Combo.getSelectedIndex()), 4, 0);
        } else if (currentevent4 == "Bars") {
            myArenaScreen.updateGymnastInfo(visitor3.getBarGymnasts().get(team4Combo.getSelectedIndex()), 4, 1);
        } else if (currentevent4 == "Beam") {
            myArenaScreen.updateGymnastInfo(visitor3.getBeamGymnasts().get(team4Combo.getSelectedIndex()), 4, 2);
        } else if (currentevent4 == "Floor") {
            myArenaScreen.updateGymnastInfo(visitor3.getFloorGymnasts().get(team4Combo.getSelectedIndex()), 4, 3);
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
