package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ArenaScreen {

    public ArenaScreen(){

        JFrame frame = new JFrame ("Arena Screen Prototype");
        frame.setContentPane(arenaScreenPanel);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


    }



    public void clock(int mySeconds){

        seconds = mySeconds;
        seconds--; //to account for second delay of timer start, should probably be changed in the future

        //update timer every 1000ms aka 1 second
        timer = new Timer(1000, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {

                time = String.format("%02d:%02d", seconds/60, seconds % 60);
                clockLabel.setText(time);

                if (seconds <= 0){
                    resetClock();
                }
                else {
                    seconds--; //to account for second delay start
                }
            }
        });
        timer.start();
    }


    public void resetClock(){
        try{
            timer.stop();
            clockLabel.setText("00:00");
        } catch (Exception e) {
            System.out.println("Error, timer probably hasn't been started yet.");
        }
    }


    //Timer variables
    public Timer timer;
    String time;
    int seconds;


    private JPanel arenaScreenPanel;
    private JLabel name;
    private JLabel apparatus;
    private JLabel year;
    private JLabel major;
    private JLabel avgApparatus;
    private JLabel clockLabel;


}
