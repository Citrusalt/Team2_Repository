package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class QuadArenaScreen {

    //maybe accept Player object here?
    public void updateGymnast(String gymnast, int teamNumb){
        if (teamNumb == 1){
            name1.setText(gymnast);
            //change major
            //year
            //etc
        }
        else if (teamNumb == 2){
            name2.setText(gymnast);
        }
        else if (teamNumb == 3){
            name3.setText(gymnast);
        }
        else if (teamNumb == 4){
            name4.setText(gymnast);
        }
    }

    public void updateGymnastInfo(Player player, int teamNumb, int event){

        if (teamNumb == 1){
            name1.setText(player.getPlayerfName() + " " + player.getPlayerlName());
            major1.setText(player.getPlayerMajor());
            year1.setText(player.getPlayerClass());
            appAvg1.setText(String.valueOf("Event Average: " + player.getPlayerAvg()[event]));
            if (event == 0){
                score1.setText("Gymnast Current Score: " + player.getPlayerScore().getvaultScore());
            }
            else if (event == 1){
                score1.setText("Gymnast Current Score: " + player.getPlayerScore().getbarScore());

            }
            else if (event == 2){
                score1.setText("Gymnast Current Score: " + player.getPlayerScore().getbeamScore());

            }
            else if (event == 3){
                score1.setText("Gymnast Current Score: " + player.getPlayerScore().getfloorScore());
            }

            ImageIcon imageIcon = new ImageIcon(picturePath + player.getPlayerPicture());
            pic1.setIcon(gC.scaleImageIcon(imageIcon, 100, 137));
        }

        else if (teamNumb == 2){
            name2.setText(player.getPlayerfName() + " " + player.getPlayerlName());
            major2.setText(player.getPlayerMajor());
            year2.setText(player.getPlayerClass());
            appAvg2.setText(String.valueOf("Event Average: " + player.getPlayerAvg()[event]));
            if (event == 0){
                score2.setText("Gymnast Current Score: " + player.getPlayerScore().getvaultScore());
            }
            else if (event == 1){
                score2.setText("Gymnast Current Score: " + player.getPlayerScore().getbarScore());

            }
            else if (event == 2){
                score2.setText("Gymnast Current Score: " + player.getPlayerScore().getbeamScore());

            }
            else if (event == 3){
                score2.setText("Gymnast Current Score: " + player.getPlayerScore().getfloorScore());
            }
            ImageIcon imageIcon = new ImageIcon(picturePath + player.getPlayerPicture());
            pic2.setIcon(gC.scaleImageIcon(imageIcon, 100, 137));
        }
        else if (teamNumb == 3){
            name3.setText(player.getPlayerfName() + " " + player.getPlayerlName());
            major3.setText(player.getPlayerMajor());
            year3.setText(player.getPlayerClass());
            appAvg3.setText(String.valueOf("Event Average: " + player.getPlayerAvg()[event]));
            if (event == 0){
                score3.setText("Gymnast Current Score: " + player.getPlayerScore().getvaultScore());
            }
            else if (event == 1){
                score3.setText("Gymnast Current Score: " + player.getPlayerScore().getbarScore());

            }
            else if (event == 2){
                score3.setText("Gymnast Current Score: " + player.getPlayerScore().getbeamScore());

            }
            else if (event == 3){
                score3.setText("Gymnast Current Score: " + player.getPlayerScore().getfloorScore());
            }
            ImageIcon imageIcon = new ImageIcon(picturePath + player.getPlayerPicture());
            pic3.setIcon(gC.scaleImageIcon(imageIcon, 100, 137));
        }
        else if (teamNumb == 4){
            name4.setText(player.getPlayerfName() + " " + player.getPlayerlName());
            major4.setText(player.getPlayerMajor());
            year4.setText(player.getPlayerClass());
            appAvg4.setText(String.valueOf("Event Average: " + player.getPlayerAvg()[event]));
            if (event == 0){
                score4.setText("Gymnast Current Score: " + player.getPlayerScore().getvaultScore());
            }
            else if (event == 1){
                score4.setText("Gymnast Current Score: " + player.getPlayerScore().getbarScore());

            }
            else if (event == 2){
                score4.setText("Gymnast Current Score: " + player.getPlayerScore().getbeamScore());

            }
            else if (event == 3){
                score4.setText("Gymnast Current Score: " + player.getPlayerScore().getfloorScore());
            }
            ImageIcon imageIcon = new ImageIcon(picturePath + player.getPlayerPicture());
            pic4.setIcon(gC.scaleImageIcon(imageIcon, 100, 137));
        }


    }


    public void nextUpdateDual(Team home, Team visitor1, Team visitor2, Team visitor3, int rotation){

        teamList.add(home);
        teamList.add(visitor1);
        teamList.add(visitor2);
        teamList.add(visitor3);

        //sort list of teams by their running score
        Collections.sort(teamList, new Comparator<Team>() {
            @Override
            public int compare(Team o1, Team o2) {
                return Double.compare(o1.getTeamScore().getRunningScore(), o2.getTeamScore().getRunningScore());
            }
        });

        //reverse list in descending order
        Collections.reverse(teamList);

        for (int i = 0; i < teamList.size(); i++){
            gC.addRowTeamTablePost(i+1, teamList.get(i).getTeamName(), teamList.get(i).getTeamScore().getRunningScore(), teamModel);
        }

        //iterate through list of teams, iterate through players, if all around player, add to playerList
        for (Team t : teamList){
            for (Player p : t.getAllGymnasts()){
                if (gC.checkAllAround(p)){
                    playerList.add(p);
                }
            }
        }

        //sort players by their total score
        Collections.sort(playerList, new Comparator<Player>() {
            @Override
            public int compare(Player p1, Player p2) {
                return Double.compare(p1.getPlayerScore().getTotalScore(), p2.getPlayerScore().getTotalScore());
            }
        });

        //reverse list in descending order
        Collections.reverse(playerList);

        //fill tables
        for (int i = 0; i < playerList.size(); i++){
            gC.addRowIndividualTableDual(i + 1, playerList.get(i).getPlayerfName() + " " + playerList.get(i).getPlayerlName(), playerList.get(i).getPlayerScore().getTotalScore(), individualModel);
        }

        updateLabel.setText("Rotation " + rotation + " Results");

        changeCard("UpdatePanel");

        playerList.clear();
        teamList.clear();
    }

    public void resetArenaTables(){
        individualModel.setRowCount(0);
        teamModel.setRowCount(0);
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
        else if (teamNumb == 3){
            event3.setText(event);
        }
        else if (teamNumb == 4){
            event4.setText(event);
        }
    }
    public void updateScore(){

    }

    //all of these clock functions are really redundant, will change later
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
    public void clock3(int mySeconds){

        seconds3 = mySeconds;
        seconds3--; //to account for second delay of timer start, should probably be changed in the future

        //update timer every 1000ms aka 1 second
        timer3 = new Timer(1000, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {

                time3 = String.format("%02d:%02d", seconds3 /60, seconds3 % 60);
                clockLabel3.setText(time3);

                if (seconds3 <= 0){
                    resetClock2();
                }
                else {
                    seconds3--;
                }
            }
        });
        timer3.start();
    }
    public void clock4(int mySeconds){

        seconds4 = mySeconds;
        seconds4--; //to account for second delay of timer start, should probably be changed in the future

        //update timer every 1000ms aka 1 second
        timer4 = new Timer(1000, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {

                time4 = String.format("%02d:%02d", seconds4 /60, seconds4 % 60);
                clockLabel4.setText(time4);

                if (seconds4 <= 0){
                    resetClock2();
                }
                else {
                    seconds4--;
                }
            }
        });
        timer4.start();
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
    public void resetClock3(){
        try{
            timer3.stop();
            clockLabel3.setText("00:00");
        } catch (Exception e) {
            System.out.println("Error, timer probably hasn't been started yet.");
        }
    }
    public void resetClock4(){
        try{
            timer4.stop();
            clockLabel4.setText("00:00");
        } catch (Exception e) {
            System.out.println("Error, timer probably hasn't been started yet.");
        }
    }

    public QuadArenaScreen(GuiCreator gC){
        frame = new JFrame ("Quad Arena Screen");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);

        //card layout start
        cardLayout = (CardLayout)  mainPanel.getLayout();
        changeCard("ArenaCard");

        createTables(4);
        teamTable.getPreferredScrollableViewportSize().setSize(-1,-1);
        teamPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        individualPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        ImageIcon imageIcon = new ImageIcon(picturePath + "default_picture.png");
        imageIcon = gC.scaleImageIcon(imageIcon, 100, 137);
        pic1.setIcon(imageIcon);
        pic2.setIcon(imageIcon);
        pic3.setIcon(imageIcon);
        pic4.setIcon(imageIcon);
    }


    public final JFrame getFrame(){
        return frame;
    }

    private void createTables(int numbTeams){

        gC.createIndividualTableDual(individualTable, individualModel, individualRenderer, font);
        gC.createTeamTablePost(teamTable, teamModel, teamRenderer, font, "Current Score");
    }

    //Timer variables
    public Timer timer1;
    String time1;
    int seconds1;

    public Timer timer2;
    String time2;
    int seconds2;

    public Timer timer3;
    String time3;
    int seconds3;

    public Timer timer4;
    String time4;
    int seconds4;

    //frame
    private final JFrame frame;

    //Directory for pictures
    String picturePath = System.getProperty("user.dir") + "/pictures/";

    GuiCreator gC = new GuiCreator();

    //Table Header Font
    Font font = new Font ("Verdana", Font.PLAIN, 18);

    //List of players for Update Screen
    private java.util.List<Player> playerList = new ArrayList<>();
    //List of teams
    private java.util.List<Team> teamList = new ArrayList<>();

    //Table models
    private DefaultTableModel teamModel = new DefaultTableModel();
    private DefaultTableModel individualModel = new DefaultTableModel();

    private DefaultTableCellRenderer teamRenderer = new DefaultTableCellRenderer();
    private DefaultTableCellRenderer individualRenderer = new DefaultTableCellRenderer();

    public CardLayout cardLayout;

    public void changeCard(String cardName){
        cardLayout.show(mainPanel, cardName);
    }

    private JPanel arenaScreenPanel;
    public JLabel name1;
    public JLabel major1;
    public JLabel year1;
    public JLabel clockLabel1;
    public JLabel major2;
    public JLabel year2;
    public JLabel score2;
    public JLabel clockLabel2;
    public JLabel teamName1;
    public JLabel teamName2;
    public JLabel name2;
    public JLabel rotationLabel;
    public JLabel overall1;
    public JLabel overall2;
    public JLabel event1;
    public JLabel event2;
    public JLabel clockLabel3;
    public JLabel clockLabel4;
    public JLabel name3;
    public JLabel name4;
    public JLabel major3;
    public JLabel major4;
    public JLabel year3;
    public JLabel year4;
    public JLabel score3;
    public JLabel score4;
    public JLabel overall3;
    public JLabel overall4;
    public JLabel pic1;
    public JLabel pic2;
    public JLabel pic3;
    public JLabel pic4;
    public JLabel event3;
    public JLabel event4;
    public JLabel appAvg1;
    public JLabel score1;
    public JLabel appAvg3;
    public JLabel appAvg2;
    public JLabel appAvg4;
    public JLabel teamName3;
    public JLabel teamName4;
    public JLabel logo1;
    public JLabel logo2;
    public JLabel logo3;
    public JLabel logo4;
    private JPanel updatePanel;
    private JLabel updateLabel;
    private JScrollPane individualPane;
    private JTable individualTable;
    private JScrollPane teamPane;
    private JTable teamTable;
    private JPanel mainPanel;
}
