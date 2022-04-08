package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

public class SetupModeQuad {

    public SetupModeQuad(GuiCreator gC){

        JFrame frame = new JFrame ("Quadrangular Setup Mode");
        frame.setContentPane(setupModeQuadPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        //card layout start
        cardLayout = (CardLayout) setupModeQuadPanel.getLayout();
        changeCard("TeamSetupCard");

        //create summary screen tables
        //This will later be placed in the Judges next button action listener most likely
        gC.createTeamTable(vaultTable, vaultModel, vaultRenderer, headerFont, 4);
        gC.createTeamTable(barsTable, barsModel, barsRenderer, headerFont, 4);
        gC.createTeamTable(beamTable, beamModel, beamRenderer, headerFont, 4);
        gC.createTeamTable(floorTable, floorModel, floorRenderer, headerFont, 4);
        gC.createJudgeTable(judgesTable, judgesModel, judgesRenderer, headerFont);

        //Test Table
        testTable(gC);


        createTeamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateTeamScreen myTeamScreen = new CreateTeamScreen(); //instantiate createTeamScreen Class
                myTeamScreen.createTeamScreen(); //call constructor
            }
        });
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

                if (homeCombo.getSelectedIndex() == 0 || visitor1Combo.getSelectedIndex() == 0 || visitor2Combo.getSelectedIndex() == 0 || visitor3Combo.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(null, "Please Select a Valid Team.");
                }
                else {
                    Object[] teams = {homeCombo.getSelectedItem(), visitor1Combo.getSelectedItem(), visitor2Combo.getSelectedItem(), visitor3Combo.getSelectedItem()};
                    if (gC.checkUnique(teams)){
                        changeCard("SelectVaultCard");
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Teams cannot be the same. Try Again.");
                    }
                }
            }
        });

        vaultNextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeCard("SelectBarCard");
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
                QuadScorekeeperScreen myQuadScorekeeperScreen = new QuadScorekeeperScreen(gC);
                frame.dispose();
            }
        });
    }

    public void changeCard(String cardName){
        cardLayout.show(setupModeQuadPanel, cardName);
    }

    private void testTable(GuiCreator gC){


        String[] team1Example = {"Jacob Drake", "Janilou Sy", "Hailey Porter", "Adriana Lanier", "John Smith", "Jane Doe"};
        String[] team2Example = {"John Smith", "Jane Doe", "Adriana Lanier", "Hailey Porter", "Janilou Sy", "Jacob Drake"};
        String[] team3Example = {"Janilou Sy", "Adriana Lanier", "Jane Doe", "Hailey Porter", "Jacob Drake", "John Smith"};
        String[] team4Example = {"Janilou Sy", "Adriana Lanier", "Jane Doe", "Hailey Porter", "Jacob Drake", "John Smith"};

        //Fill Vault Table Example
        // This can all be combined into a single for-loop, just showing how to do one at a time
        //Use different string []'s for different events obviously
        for (int i = 0; i <= 5; i++){
            String[] row = {team1Example[i], team2Example[i], team3Example[i], team4Example[i]};
            gC.addRowTeamTable(row, vaultModel);
        }
        //Fill Bar Table Example
        for (int i = 0; i <= 5; i++){
            String[] row = {team1Example[i], team2Example[i], team3Example[i], team4Example[i]};
            gC.addRowTeamTable(row, barsModel);
        }
        //Fill Beam Table Example
        for (int i = 0; i <= 5; i++){
            String[] row = {team1Example[i], team2Example[i], team3Example[i], team4Example[i]};
            gC.addRowTeamTable(row, beamModel);
        }
        //Fill Floor Table Example
        for (int i = 0; i <= 5; i++){
            String[] row = {team1Example[i], team2Example[i], team3Example[i], team4Example[i]};
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

    //Table header font
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

    private CardLayout cardLayout;
    private JButton meetTemplateBackButton;
    private JPanel setupModeQuadPanel;
    private JPanel teamSetup;
    private JButton goBackButton;
    private JComboBox homeCombo;
    private JComboBox visitor2Combo;
    private JButton continueButton;
    private JButton createTeamButton;
    private JPanel setupVault;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JComboBox comboBox5;
    private JComboBox comboBox6;
    private JButton vaultBackButton;
    private JCheckBox thisApparatusRequiresACheckBox;
    private JButton vaultNextButton;
    private JPanel selectJudges;
    private JButton judgesBackButton;
    private JComboBox comboBox9;
    private JComboBox comboBox10;
    private JComboBox comboBox11;
    private JComboBox comboBox12;
    private JButton judgesNextButton;
    private JPanel summaryScreen;
    private JButton summaryContinueButton;
    private JTable vaultTable;
    private JTable barsTable;
    private JTable beamTable;
    private JTable floorTable;
    private JTable judgesTable;
    private JButton vaultEditButton;
    private JButton barEditButton;
    private JButton editFloorButton;
    private JButton editBeamButton;
    private JButton editJudgesButton;
    private JPanel setupBars;
    private JPanel setupBalanceBeam;
    private JPanel setupFloor;
    private JButton barsNextButton;
    private JButton barsBackButton;
    private JButton balanceBeamBackButton;
    private JButton balanceBeamNextButton;
    private JButton floorBackButton;
    private JButton floorNextButton;
    private JComboBox visitor1Combo;
    private JComboBox visitor3Combo;

}
