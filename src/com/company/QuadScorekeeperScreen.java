package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class QuadScorekeeperScreen {

    public QuadScorekeeperScreen(GuiCreator gC){


        JFrame frame = new JFrame("Quadrangular Scorekeeper Screen Prototype");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        QuadArenaScreen myQuadArenaScreen = new QuadArenaScreen(gC);
        myQuadArenaScreen.getFrame().setVisible(true);


        //card layout start
        cardLayout = (CardLayout) mainPanel.getLayout();
        changeCard("CustomizeCard");


        defaultTemplateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeCard("QuadScorekeeperCard");
                myQuadArenaScreen.getFrame().setVisible(true);
                updateRotation(myQuadArenaScreen, frame, 0, gC);
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
                updateRotation(myQuadArenaScreen, frame, 1, gC);
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateRotation(myQuadArenaScreen, frame, -1, gC);
            }
        });

        team1Combo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED){
                    Object item = e.getItem();
                    myQuadArenaScreen.updateGymnast(item.toString(), 1);
                }
            }
        });
        team2Combo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED){
                    Object item = e.getItem();
                    myQuadArenaScreen.updateGymnast(item.toString(), 2);
                }
            }
        });
        team3Combo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED){
                    Object item = e.getItem();
                    myQuadArenaScreen.updateGymnast(item.toString(), 3);
                }
            }
        });
        team4Combo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED){
                    Object item = e.getItem();
                    myQuadArenaScreen.updateGymnast(item.toString(), 4);
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

                    //Update Arena Screen Flash
                    updateColor(1, myQuadArenaScreen);

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

                float scoreArray[] = new float[6];
                try{
                    //Use these scores to update score for backend and arena screen
                    scoreArray[0] = Integer.parseInt(j21.getText());
                    scoreArray[1] = Integer.parseInt(j22.getText());
                    scoreArray[2] = Integer.parseInt(j23.getText());
                    scoreArray[3] = Integer.parseInt(j24.getText());
                    scoreArray[4] = Integer.parseInt(j25.getText());
                    scoreArray[5] = Integer.parseInt(j26.getText());

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

                float scoreArray[] = new float[6];
                try{
                    //Use these scores to update score for backend and arena screen
                    scoreArray[0] = Integer.parseInt(j21.getText());
                    scoreArray[1] = Integer.parseInt(j22.getText());
                    scoreArray[2] = Integer.parseInt(j23.getText());
                    scoreArray[3] = Integer.parseInt(j24.getText());
                    scoreArray[4] = Integer.parseInt(j25.getText());
                    scoreArray[5] = Integer.parseInt(j26.getText());

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
    private void updateRotation(QuadArenaScreen myArenaScreen, JFrame thisFrame, int value, GuiCreator gC){

        rotation = rotation + value;
        myArenaScreen.updateRotation(rotation);

        if (rotation == 0){
            SetupModeQuad myQuadSetup = new SetupModeQuad(gC);
            myQuadSetup.changeCard("SummaryCard");
            myArenaScreen.getFrame().dispose();
            thisFrame.dispose();
        }
        else if (rotation == 1){
            team1App.setText("Vault");
            team2App.setText("Bars");
            team3App.setText("Beam");
            team4App.setText("Floor");

            myArenaScreen.updateEvent("Vault", 1);
            myArenaScreen.updateEvent("Bars", 2);
            myArenaScreen.updateEvent("Beam", 3);
            myArenaScreen.updateEvent("Floor", 4);

            rotationLabel.setText("ROTATION 1");
            //update judges
            //update players
            //update scores
            //change combo boxes
            //etc.
        }
        else if (rotation == 2){
            team1App.setText("Bars");
            team2App.setText("Vault");
            team3App.setText("Floor");
            team4App.setText("Beam");

            myArenaScreen.updateEvent("Bars", 1);
            myArenaScreen.updateEvent("Vault", 2);
            myArenaScreen.updateEvent("Floor", 3);
            myArenaScreen.updateEvent("Beam", 4);

            rotationLabel.setText("ROTATION 2");
            //update judges
            //update players
            //update scores
            //change combo boxes
            //etc.
        }
        else if (rotation == 3){
            team1App.setText("Beam");
            team2App.setText("Bars");
            team3App.setText("Vault");
            team4App.setText("Floor");

            myArenaScreen.updateEvent("Beam", 1);
            myArenaScreen.updateEvent("Bars", 2);
            myArenaScreen.updateEvent("Vault", 3);
            myArenaScreen.updateEvent("Floor", 4);

            rotationLabel.setText("ROTATION 3");
        }
        else if (rotation == 4){
            team1App.setText("Floor");
            team2App.setText("Beam");
            team3App.setText("Bars");
            team4App.setText("Vault");

            myArenaScreen.updateEvent("Floor", 1);
            myArenaScreen.updateEvent("Beam", 2);
            myArenaScreen.updateEvent("Bars", 3);
            myArenaScreen.updateEvent("Vault", 4);

            rotationLabel.setText("ROTATION 4");
        }
        else if(rotation == 5){
            List<Team> teams = new ArrayList<>();
            PostMeetScreen myPostMode = new PostMeetScreen(gC, teams);
            myArenaScreen.getFrame().dispose();
            thisFrame.dispose();
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

    private int rotation = 1;


    private Color defaultColor = new Color(51, 51, 51); //default font color

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
    private JTabbedPane tabbedPane1;
    private JPanel vaultTab;
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
    private CardLayout cardLayout;
}
