package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScorekeeperScreen {

    ArenaScreen myArenaScreen = new ArenaScreen();


    public ScorekeeperScreen() {

        JFrame frame = new JFrame ("Scorekeeper Screen Prototype");
        frame.setContentPane(scorekeeperScreenPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        startTimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (startTimerButton.getText() == "Start Timer"){
                    try{
                        Integer.parseInt(clockTextField.getText());
                        if(Integer.parseInt(clockTextField.getText()) > 0){
                            myArenaScreen.clock(Integer.parseInt(clockTextField.getText()));
                            startTimerButton.setText("Reset Timer");
                        }

                    }
                    catch (Exception exception) {
                        System.out.println("Invalid Input");
                    }
                }
                else{
                    myArenaScreen.resetClock();
                    startTimerButton.setText("Start Timer");
                }
            }
        });
        postMeetModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.setVisible(false);


                PostMeetScreen myPostMeetScreen = new PostMeetScreen();

            }
        });
    }

    private JButton startTimerButton;
    private JTextField clockTextField;
    private JPanel scorekeeperScreenPanel;
    private JButton postMeetModeButton;
}
