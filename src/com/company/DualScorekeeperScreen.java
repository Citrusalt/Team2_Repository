package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class DualScorekeeperScreen {

    public DualScorekeeperScreen(GuiCreator gC) {

        JFrame frame = new JFrame("Scorekeeper Screen Prototype");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        Dual_Tri_ArenaScreen myArenaScreen = new Dual_Tri_ArenaScreen();

        //card layout start
        cardLayout = (CardLayout) mainPanel.getLayout();
        changeCard("CustomizeCard");


        defaultTemplateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (simultaneousCheckBox.isSelected()){
                    cardLayout.show(mainPanel, "SimulCard");
                    myArenaScreen.getFrame().setVisible(true);

                }
                else{
                    //non simultaneous arena screen
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
                updateRotation(myArenaScreen, frame, 1, gC);
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateRotation(myArenaScreen, frame, -1, gC);
            }
        });

        team1Combo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED){
                    Object item = e.getItem();
                    myArenaScreen.updateGymnast(item.toString(), 1);
                }
            }

        });
        team2Combo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED){
                    Object item = e.getItem();
                    myArenaScreen.updateGymnast(item.toString(), 2);
                }
            }
        });
        updateScoreButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float scoreArray[] = new float[6];
                //List<Double> scoresList  = new ArrayList<>();
                //player instance here is just a placeholder
               // Player player1 = new Player("Name", "2022", "CS", "9.9");
                try{
                    //Use these scores to update score for backend and arena screen
                    //doesn't have to be entered into a "scoreArray" just an example
                    scoreArray[0] = Integer.parseInt(j11.getText());
                    scoreArray[1] = Integer.parseInt(j12.getText());
                    scoreArray[2] = Integer.parseInt(j13.getText());
                    scoreArray[3] = Integer.parseInt(j14.getText());
                    scoreArray[4] = Integer.parseInt(j15.getText());
                    scoreArray[5] = Integer.parseInt(j16.getText());

                /*    // the score calculation function won't work if there is not 2, 4, or 6 judges.
                    //probably not the cleanest way to handle this, but it works for now

                    if (j11.getText().isEmpty() == false && j12.getText().isEmpty() ==false ){
                        scoresList.add(Double.parseDouble(j11.getText()));
                        scoresList.add(Double.parseDouble(j12.getText()));
                    }
                    if (j13.getText().isEmpty() == false && j14.getText().isEmpty() ==false ){
                        scoresList.add(Double.parseDouble(j13.getText()));
                        scoresList.add(Double.parseDouble(j14.getText()));
                    }
                    if (j15.getText().isEmpty() == false && j16.getText().isEmpty() ==false ){
                        scoresList.add(Double.parseDouble(j15.getText()));
                        scoresList.add(Double.parseDouble(j16.getText()));
                    }

                    //need to set the correct apparatus score for the player
                    //neutral deduction set equal to 0 is a placeholder until the text field is made
                    if (rotation == 1) {
                        player1.playerScore.setvaultScore(player1.playerScore.calculateIndividualScore(scoresList,0));
                    }
                    else if (rotation == 2) {
                        player1.playerScore.setbarScore(player1.playerScore.calculateIndividualScore(scoresList,0));
                    }
                    else if (rotation == 3) {
                        player1.playerScore.setbeamScore(player1.playerScore.calculateIndividualScore(scoresList,0));
                    }
                    else if (rotation == 4) {
                        player1.playerScore.setfloorScore(player1.playerScore.calculateIndividualScore(scoresList,0));
                    }*/

                } catch (Exception exception) {

                    System.out.println(exception);
                }
            }
        });
        updateScoreButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //List<Double> scoresList  = new ArrayList<>();
                //placeholder
               // Player player2 = new Player("Name2", "2022", "CS", "9.9");
                float scoreArray[] = new float[6];
                try{
                    //Use these scores to update score for backend and arena screen
                    //doesn't have to be entered into a "scoreArray" just an example
                    scoreArray[0] = Integer.parseInt(j21.getText());
                    scoreArray[1] = Integer.parseInt(j22.getText());
                    scoreArray[2] = Integer.parseInt(j23.getText());
                    scoreArray[3] = Integer.parseInt(j24.getText());
                    scoreArray[4] = Integer.parseInt(j25.getText());
                    scoreArray[5] = Integer.parseInt(j26.getText());

                  /*  // the score calculation function won't work if there is not 2, 4, or 6 judges.
                    //probably not the cleanest way to handle this, but it works for now
                    if (j21.getText().isEmpty() == false && j21.getText().isEmpty() ==false ){
                        scoresList.add(Double.parseDouble(j21.getText()));
                        scoresList.add(Double.parseDouble(j22.getText()));
                    }
                    if (j23.getText().isEmpty() == false && j24.getText().isEmpty() ==false ){
                        scoresList.add(Double.parseDouble(j23.getText()));
                        scoresList.add(Double.parseDouble(j24.getText()));
                    }
                    if (j25.getText().isEmpty() == false && j26.getText().isEmpty() ==false ){
                        scoresList.add(Double.parseDouble(j25.getText()));
                        scoresList.add(Double.parseDouble(j26.getText()));
                    }

                    //need to set the correct apparatus score for the player
                    //neutral deduction set equal to 0 is a placeholder until the text field is made
                    if (rotation == 1) {
                        player2.playerScore.setbarScore(player2.playerScore.calculateIndividualScore(scoresList,0));
                    }
                    else if (rotation == 2) {
                        player2.playerScore.setvaultScore(player2.playerScore.calculateIndividualScore(scoresList,0));
                    }
                    else if (rotation == 3) {
                        player2.playerScore.setfloorScore(player2.playerScore.calculateIndividualScore(scoresList,0));
                    }
                    else if (rotation == 4) {
                        player2.playerScore.setbeamScore(player2.playerScore.calculateIndividualScore(scoresList,0));
                    }*/

                } catch (Exception exception) {

                    System.out.println(exception);
                }

            }
        });
    }


    public void changeCard(String cardName){
        cardLayout.show(mainPanel, cardName);
    }


    //pass in the frames that need to be handled and 1 if next rotation, -1 if previous
    private void updateRotation(Dual_Tri_ArenaScreen myArenaScreen, JFrame thisFrame, int value, GuiCreator gC){
        rotation = rotation + value;
        myArenaScreen.updateRotation(rotation);

        if (rotation == 0){
            SetupModeDual myDualSetup = new SetupModeDual(gC);
            myDualSetup.changeCard("SummaryCard");
            myArenaScreen.getFrame().dispose();
            thisFrame.dispose();
        }
        else if (rotation == 1){
            team1App.setText("Vault");
            team2App.setText("Bars");
            myArenaScreen.updateEvent("Vault", 1);
            myArenaScreen.updateEvent("Bars", 2);
            rotationLabel.setText("ROTATION 1");
            //update judges
            //update players
            //update scores
            //etc.
        }
        else if (rotation == 2){
            team1App.setText("Bars");
            team2App.setText("Vault");
            myArenaScreen.updateEvent("Bars", 1);
            myArenaScreen.updateEvent("Vault", 2);
            rotationLabel.setText("ROTATION 2");
            //update judges
            //update players
            //update scores
            //etc.
        }
        else if (rotation == 3){
            team1App.setText("Beam");
            team2App.setText("Floor");
            myArenaScreen.updateEvent("Beam", 1);
            myArenaScreen.updateEvent("Floor", 2);
            rotationLabel.setText("ROTATION 3");
        }
        else if (rotation == 4){
            team1App.setText("Floor");
            team2App.setText("Beam");
            myArenaScreen.updateEvent("Floor", 1);
            myArenaScreen.updateEvent("Beam", 2);
            rotationLabel.setText("ROTATION 4");
        }
        else if(rotation == 5){
            PostMeetScreen myPostMode = new PostMeetScreen(gC);
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
    private JCheckBox gymnastMajorCheckBox;
    private JCheckBox simultaneousCheckBox;
    private CardLayout cardLayout;
}
