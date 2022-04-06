package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dual_Tri_ArenaScreen {



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
     public void updateEvent(String event, int teamNumb){
         if(teamNumb == 1){
             event1.setText(event);
         }
         else if (teamNumb == 2){
             event2.setText(event);
         }
     }
     public void updateScore(){

     }

     public void updateTeamName (String name, int selectedTeam){
         if(selectedTeam == 1){
             teamName1.setText(name);
         }
         else if (selectedTeam == 2){
             teamName2.setText(name);
         }
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

    public Dual_Tri_ArenaScreen(){
        frame = new JFrame ("Arena Screen Prototype");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(false);

        //card layout start
        cardLayout = (CardLayout)  mainPanel.getLayout();
        changeCard("SimulCard");

    }


    public final JFrame getFrame(){
        return frame;
    }

    public final JPanel getPanel(){
         return arenaScreenPanel;
    }

    public void changeCard(String cardName){
         cardLayout.show(mainPanel, cardName);
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


    public CardLayout cardLayout;
    private JPanel arenaScreenPanel;
    public JLabel clockLabel1;
    public JLabel clockLabel2;
    public JLabel name1;
    public JLabel major1;
    public JLabel year1;
    public JLabel avg1;
    public JLabel overall1;
    public JLabel teamName1;
    public JLabel teamName2;
    public JLabel name2;
    public JLabel major2;
    public JLabel year2;
    public JLabel avg2;
    public JLabel overall2;
    public JLabel rotationLabel;
    public JLabel event1;
    public JLabel event2;
    public JLabel gymnastCurrent1;
    public JLabel gymnastCurrent2;
    public JLabel pic1;
    public JLabel pic2;
    private JPanel mainPanel;
    private JPanel nonSimulPanel;
    private JLabel teamName;
    private JLabel event;
    private JLabel name;
    private JLabel major;
    private JLabel avg;
    private JLabel year;
    private JLabel currentScore;
    private JLabel runningScore;
    private JLabel pic;
    private JLabel timer;
    private JLabel nonSimulRotation;


}
