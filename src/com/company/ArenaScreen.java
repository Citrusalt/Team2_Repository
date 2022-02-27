package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ArenaScreen {

    public ArenaScreen(){


    }


    public void ArenaScreen(){
            //In the future, I'm going to make this a JDialog instead of another JFrame

            JFrame frame = new JFrame ("Arena Screen Prototype");
            frame.setContentPane(arenaScreenPanel);
            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
    }



    public void clock(int mySeconds){

        seconds = mySeconds;

        //update timer every 1000ms aka 1 second
        timer = new Timer(1000, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (seconds <= 0){
                    resetClock();
                }
                else {
                    seconds--; //to account for second delay start
                }
                time = String.format("%02d:%02d", seconds/60, seconds % 60);
                clockLabel.setText(time);
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
