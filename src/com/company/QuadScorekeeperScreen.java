package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QuadScorekeeperScreen {

    public QuadScorekeeperScreen(GuiCreator gC){


        JFrame frame = new JFrame("Quadrangular Scorekeeper Screen Prototype");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        QuadArenaScreen myQuadArenaScreen = new QuadArenaScreen(gC);


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
            PostMeetScreen myPostMode = new PostMeetScreen(gC);
            myArenaScreen.getFrame().dispose();
            thisFrame.dispose();
        }

    }



    private int rotation = 1;



    private JPanel mainPanel;
    private JPanel customizeScreen;
    private JButton defaultTemplateButton;
    private JButton customizeArenaButton;
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
    private CardLayout cardLayout;
}