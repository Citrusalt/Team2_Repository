package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetupModeDual {

    public SetupModeDual(GuiCreator gC){

        JFrame frame = new JFrame ("Women's Gymnastics Scoreboard");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        String meetType = "dual";

        //create summary screen tables
        //This will later be placed in the Judges next button action listener most likely
        gC.createTeamTable(vaultTable, vaultModel, vaultRenderer, headerFont, 2);
        gC.createTeamTable(barsTable, barsModel, barsRenderer,headerFont , 2);
        gC.createTeamTable(beamTable, beamModel, beamRenderer, headerFont, 2);
        gC.createTeamTable(floorTable, floorModel, floorRenderer, headerFont, 2);
        gC.createJudgeTable(judgesTable, judgesModel, judgesRenderer, headerFont);

        //Test Table
        testTable(gC);

        //Card Layout start
        cardLayout = (CardLayout) mainPanel.getLayout();

        changeCard("StartScreenCard");


        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                MeetFormatScreen myMeetFormatScreen = new MeetFormatScreen("meetFormatCard", gC);
            }
        });
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //cardLayout.show(mainPanel, "SelectVaultCard");

                //This verifies if the user selected a valid item
                Object homeTeam = comboBox7.getSelectedItem();
                Object visitorTeam = comboBox8.getSelectedItem();

                //Assuming that index 0 is default label for the comboBox
                if(comboBox7.getSelectedIndex() == 0 || comboBox8.getSelectedIndex() == 0 ){
                    JOptionPane.showMessageDialog(null, "Please Select a Valid Team.");
                }
                else if(homeTeam.toString().equals(visitorTeam.toString())){ //
                    JOptionPane.showMessageDialog(null, "Teams cannot be the same. Try Again.");
                }
                else {
                    changeCard("SelectVaultCard");
                }
            }
        });
        createTeamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    CreateTeamScreen myTeamScreen = new CreateTeamScreen(); //instantiate createTeamScreen Class
                    myTeamScreen.createTeamScreen(); //call constructor     //
            }
        });
        vaultNextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean errorThrown = false;

                Object gymnast1 = comboBox1.getSelectedItem();
                Object gymnast2 = comboBox2.getSelectedItem();
                Object gymnast3 = comboBox3.getSelectedItem();
                Object gymnast4 = comboBox4.getSelectedItem();
                Object gymnast5 = comboBox5.getSelectedItem();
                Object gymnast6 = comboBox6.getSelectedItem();

                String[] gyms = {gymnast1.toString(), gymnast2.toString(), gymnast3.toString(), gymnast4.toString(), gymnast5.toString(), gymnast6.toString()};

                for(int i = 0; i < gyms.length - 1; i++){
                    for(int j = i+1; j < gyms.length; j++)
                    {
                        if (!gyms[i].equals("Select Gymnast") && gyms[i].equals(gyms[j]))
                        {
                            //throw error
                            errorThrown = true;
                            JOptionPane.showMessageDialog(null, "Gymnasts cannot compete more than once per apparatus.");
                        }
                    }

                }

                if(!errorThrown)
                {
                    changeCard("SelectBarCard");
                }
            }
        });
        vaultBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeCard("TeamSetupCard");
            }
        });
        barsBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeCard("SelectVaultCard");
            }
        });
        barsNextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeCard("SelectBalanceBeamCard");
            }
        });
        balanceBeamBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeCard("SelectBarCard");
            }
        });
        balanceBeamNextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeCard("SelectFloorCard");
            }
        });
        floorBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeCard("SelectBalanceBeamCard");
            }
        });
        floorNextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeCard("SelectJudgesCard");
            }
        });
        judgesBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeCard("SelectFloorCard");
            }
        });
        judgesNextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {changeCard("SummaryCard");}
        });
        vaultEditButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeCard("SelectVaultCard");
            }
        });
        barEditButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeCard("SelectBarCard");
            }
        });
        editBeamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeCard("SelectBalanceBeamCard");
            }
        });
        editFloorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeCard("SelectFloorCard");
            }
        });
        editJudgesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeCard("SelectJudgesCard");
            }
        });
        summaryContinueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                DualScorekeeperScreen myScorekeeper = new DualScorekeeperScreen(gC);
            }
        });

    }

    public void changeCard(String cardName){
        cardLayout.show(mainPanel, cardName);
    }




    //Test function made to fill tables
    private void testTable(GuiCreator gC){

        String[] team1Example = {"Jacob Drake", "Janilou Sy", "Hailey Porter", "Adriana Lanier", "John Smith", "Jane Doe"};
        String[] team2Example = {"John Smith", "Jane Doe", "Adriana Lanier", "Hailey Porter", "Janilou Sy", "Jacob Drake"};

        //Fill Vault Table Example
        // This can all be combined into a single for-loop, just showing how to do one at a time
        //Use different string []'s for different events obviously
        for (int i = 0; i <= 5; i++){
            String[] row = {team1Example[i], team2Example[i]};
            gC.addRowTeamTable(row, vaultModel);
        }
        //Fill Bar Table Example
        for (int i = 0; i <= 5; i++){
            String[] row = {team1Example[i], team2Example[i]};
            gC.addRowTeamTable(row, barsModel);
        }
        //Fill Beam Table Example
        for (int i = 0; i <= 5; i++){
            String[] row = {team1Example[i], team2Example[i]};
            gC.addRowTeamTable(row, beamModel);
        }
        //Fill Floor Table Example
        for (int i = 0; i <= 5; i++){
            String[] row = {team1Example[i], team2Example[i]};
            gC.addRowTeamTable(row, floorModel);
        }

        String[] vaultJudges = new String[]{"Jacob Drake", "Adriana Lanier", "Hailey Porter","Janilou Sy", "John Smith", "Jane Doe"};
        String[] barJudges = new String[]{"Adriana Lanier", "Janilou Sy", "Jacob Drake","Janilou Sy", "John Smith", "Jane Doe"};
        String[] beamJudges = new String[]{"John Smith", "Adriana Lanier", "Hailey Porter","Janilou Sy", "Jacob Drake", "Jane Doe"};
        String[] floorJudges = new String[]{"Jacob Drake", "Jane Doe", "Hailey Porter","Janilou Sy", "John Smith", "Adriana Lanier"};

        for (int i = 0; i <= 5; i++){
            gC.addRowsJudgeTable(vaultJudges[i], barJudges[i], beamJudges[i], floorJudges[i], judgesModel);
        }
    }

    //Table Header Font
    Font headerFont = new Font ("Verdana", Font.PLAIN, 18);

    //Summary Screen Table Variables
    //I don't know how else to make this tables non-editable besides doing this
    private DefaultTableModel vaultModel = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
            //all cells false
            return false;
        }
    };
    private DefaultTableModel barsModel = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
            //all cells false
            return false;
        }
    };;
    private DefaultTableModel beamModel = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
            //all cells false
            return false;
        }
    };;
    private DefaultTableModel floorModel = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
            //all cells false
            return false;
        }
    };;
    private DefaultTableModel judgesModel = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
            //all cells false
            return false;
        }
    };;

    private DefaultTableCellRenderer vaultRenderer = new DefaultTableCellRenderer();
    private DefaultTableCellRenderer barsRenderer = new DefaultTableCellRenderer();
    private DefaultTableCellRenderer beamRenderer = new DefaultTableCellRenderer();
    private DefaultTableCellRenderer floorRenderer = new DefaultTableCellRenderer();
    private DefaultTableCellRenderer judgesRenderer = new DefaultTableCellRenderer();




    private JPanel mainPanel;
    private JButton startButton;
    private JButton dualButton;
    private JButton triangularButton;
    private JButton quadrangularButton;
    private CardLayout cardLayout;
    private JPanel setupVault;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JComboBox comboBox5;
    private JComboBox comboBox6;
    private JButton vaultBackButton;
    private JButton meetTemplateBackButton;
    private JComboBox comboBox7;
    private JComboBox comboBox8;
    private JButton goBackButton;
    private JButton continueButton;
    private JButton createTeamButton;
    private JButton vaultNextButton;
    private JCheckBox thisApparatusRequiresACheckBox;
    private JPanel setupBars;
    private JPanel setupFloor;
    private JPanel setupBalanceBeam;
    private JButton barsBackButton;
    private JButton barsNextButton;
    private JButton balanceBeamBackButton;
    private JButton balanceBeamNextButton;
    private JButton floorBackButton;
    private JButton floorNextButton;
    private JPanel selectJudges;
    private JButton judgesBackButton;
    private JComboBox comboBox9;
    private JComboBox comboBox10;
    private JComboBox comboBox11;
    private JComboBox comboBox12;
    private JButton judgesNextButton;
    private JPanel summaryScreen;
    private JButton summaryContinueButton;
    private JButton vaultEditButton;
    private JButton barEditButton;
    private JButton editBeamButton;
    private JButton editFloorButton;
    private JButton editJudgesButton;
    private JPanel teamSetup;
    private JTable vaultTable;
    private JTable barsTable;
    private JTable beamTable;
    private JTable floorTable;
    private JTable judgesTable;
    private JTextField clockTextField;
    private JButton startTimerButton;
    private JButton resetTimerButton;



}
