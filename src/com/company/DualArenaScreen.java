package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DualArenaScreen {

     public void updateGymnast(String gymnast, int teamNumb){
         if (teamNumb == 1){
             name1.setText(gymnast);
         }
         else if (teamNumb == 2){
             name2.setText(gymnast);
         }
     }
     public void updateRotation(int rotationNumb){

         rotationLabel.setText("Rotation " + rotationNumb);

     }
     public void updateScore(){

     }

    public void clock1(int mySeconds){

        seconds1 = mySeconds;
        seconds1--; //to account for second delay of timer start, should probably be changed in the future

        //update timer every 1000ms aka 1 second
        timer1 = new Timer(1000, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {

                time1 = String.format("%02d:%02d", seconds1 /60, seconds1 % 60);
                clockLabel1.setText(time1);

                if (seconds1 <= 0){
                    resetClock1();
                }
                else {
                    seconds1--;
                }
            }
        });
        timer1.start();
    }

    public void clock2(int mySeconds){

        seconds2 = mySeconds;
        seconds2--; //to account for second delay of timer start, should probably be changed in the future

        //update timer every 1000ms aka 1 second
        timer2 = new Timer(1000, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {

                time2 = String.format("%02d:%02d", seconds2 /60, seconds2 % 60);
                clockLabel2.setText(time2);

                if (seconds2 <= 0){
                    resetClock2();
                }
                else {
                    seconds2--;
                }
            }
        });
        timer2.start();
    }

    public void resetClock1(){
        try{
            timer1.stop();
            clockLabel1.setText("00:00");
        } catch (Exception e) {
            System.out.println("Error, timer probably hasn't been started yet.");
        }
    }

    public void resetClock2(){
        try{
            timer2.stop();
            clockLabel2.setText("00:00");
        } catch (Exception e) {
            System.out.println("Error, timer probably hasn't been started yet.");
        }
    }

    public DualArenaScreen(){
        frame = new JFrame ("Arena Screen Prototype");
        frame.setContentPane(arenaScreenPanel);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    public final JFrame getFrame(){
        return frame;
    }


    //Timer variables
    public Timer timer1;
    String time1;
    int seconds1;

    public Timer timer2;
    String time2;
    int seconds2;

    //frame
    private final JFrame frame;



    private JPanel arenaScreenPanel;
    private JLabel clockLabel1;
    private JLabel clockLabel2;
    private JLabel name1;
    private JLabel major1;
    private JLabel year1;
    private JLabel avg1;
    private JLabel overall1;
    private JLabel teamName1;
    private JLabel teamName2;
    private JLabel name2;
    private JLabel major2;
    private JLabel major3;
    private JLabel avg2;
    private JLabel overall2;
    private JLabel rotationLabel;


}
