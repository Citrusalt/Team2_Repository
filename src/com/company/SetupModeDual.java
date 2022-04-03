package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

public class SetupModeDual {



    public SetupModeDual(){

        JFrame frame = new JFrame ("Women's Gymnastics Scoreboard");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        String meetType = "dual";

        //create summary screen tables
        createTeamTable(vaultTable, vaultModel, vaultRenderer);
        createTeamTable(barsTable, barsModel, barsRenderer);
        createTeamTable(beamTable, beamModel, beamRenderer);
        createTeamTable(floorTable, floorModel, floorRenderer);
        createJudgeTable(judgesTable, judgesModel, judgesRenderer);

        //Test Table
        testTable();

        //Card Layout start
        cardLayout = (CardLayout) mainPanel.getLayout();

        cardLayout.show(mainPanel, "startScreenCard");

        //GUI Setup
        limitJSpinners(); //limit ranges of order numbers


        //Go Back Button Action Listener
        vaultBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "TeamSetupCard");
            }
        });
        meetTemplateBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "MeetSelectCard");
            }
        });
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                MeetFormatScreen myMeetFormatScreen = new MeetFormatScreen("meetFormatCard");
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
                    cardLayout.show(mainPanel, "SelectVaultCard");
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
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "SelectBarCard");
            }
        });
        barsBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "SelectVaultCard");
            }
        });
        barsNextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "SelectBalanceBeamCard");
            }
        });
        balanceBeamBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "SelectBarCard");
            }
        });
        balanceBeamNextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "SelectFloorCard");
            }
        });
        floorBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "SelectBalanceBeamCard");
            }
        });
        floorNextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "SelectJudgesCard");
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "SelectFloorCard");
            }
        });
        judgesNextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                cardLayout.show(mainPanel, "SummaryCard");
            }
        });
        vaultEditButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "SelectVaultCard");
            }
        });
        barEditButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "SelectBarCard");
            }
        });
        editBeamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "SelectBalanceBeamCard");
            }
        });
        editFloorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "SelectFloorCard");
            }
        });
        editJudgesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "SelectJudgesCard");
            }
        });
        summaryContinueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.dispose();
                DualScorekeeperScreen myScorekeeper = new DualScorekeeperScreen();
            }
        });
    }

    private void limitJSpinners(){

        int current = 0;
        int min = 0;
        int max = 6;
        int step = 1;


        //It ain't pretty, but it works
        //will most likely just remove anyways in favor of static numbers
        //no reason to overcomplicate these things
        SpinnerNumberModel spinnerModel1 = new SpinnerNumberModel(current, min, max, step);
        spinner1.setModel(spinnerModel1);
        SpinnerNumberModel spinnerModel2 = new SpinnerNumberModel(current, min, max, step);
        spinner2.setModel(spinnerModel2);
        SpinnerNumberModel spinnerModel3 = new SpinnerNumberModel(current, min, max, step);
        spinner3.setModel(spinnerModel3);
        SpinnerNumberModel spinnerModel4 = new SpinnerNumberModel(current, min, max, step);
        spinner4.setModel(spinnerModel4);
        SpinnerNumberModel spinnerModel5 = new SpinnerNumberModel(current, min, max, step);
        spinner5.setModel(spinnerModel5);
        SpinnerNumberModel spinnerModel6 = new SpinnerNumberModel(current, min, max, step);
        spinner6.setModel(spinnerModel6);
        SpinnerNumberModel spinnerModel7 = new SpinnerNumberModel(current, min, max, step);
        spinner7.setModel(spinnerModel7);
        SpinnerNumberModel spinnerModel8 = new SpinnerNumberModel(current, min, max, step);
        spinner8.setModel(spinnerModel8);
        SpinnerNumberModel spinnerModel9 = new SpinnerNumberModel(current, min, max, step);
        spinner9.setModel(spinnerModel9);
        SpinnerNumberModel spinnerModel10 = new SpinnerNumberModel(current, min, max, step);
        spinner10.setModel(spinnerModel10);
        SpinnerNumberModel spinnerModel11 = new SpinnerNumberModel(current, min, max, step);
        spinner11.setModel(spinnerModel11);
        SpinnerNumberModel spinnerModel12 = new SpinnerNumberModel(current, min, max, step);
        spinner12.setModel(spinnerModel12);

    }

    public void changeCard(String cardName){
        cardLayout.show(mainPanel, cardName);
    }

    //temporary parameters, will take in team 1 and team 2 of type Team in the future
    private void createTeamTable(JTable table, DefaultTableModel model, DefaultTableCellRenderer cellRenderer){

        model.addColumn("Team 1");
        model.addColumn("Team 2");
        table.setModel(model);
        centerColumns(table, cellRenderer);

        table.getTableHeader().setFont(font);
        centerColumns(table, cellRenderer);

        //individualTable.getColumnModel().getColumn(0).setPreferredWidth(1);
    }

    private void createJudgeTable(JTable table, DefaultTableModel model, DefaultTableCellRenderer cellRenderer){
        model.addColumn("Vault");
        model.addColumn("Bars");
        model.addColumn("Beam");
        model.addColumn("Floor");

        table.setModel(model);
        centerColumns(table, cellRenderer);

        table.getTableHeader().setFont(font);
        centerColumns(table, cellRenderer);

        //individualTable.getColumnModel().getColumn(0).setPreferredWidth(1);
    }

    private void addRowsJudgeTable(String vaultJudge,String barJudge, String beamJudge, String floorJudge, DefaultTableModel model){
        Vector<String> row = new Vector<String>();

        row.add(vaultJudge);
        row.add(barJudge);
        row.add(beamJudge);
        row.add(floorJudge);
        model.addRow(row);

}

    //the parameters of this will undoubtedly change to accept either team or player class instead
    public void addRowTeamTable(String name1, String name2, DefaultTableModel model){
        Vector<String> row = new Vector<String>();
        row.add(name1);
        row.add(name2);
        model.addRow(row);
    }

    private void centerColumns(JTable table, DefaultTableCellRenderer cellRenderer){
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int columnIndex = 0; columnIndex < table.getModel().getColumnCount(); columnIndex++){
            table.getColumnModel().getColumn(columnIndex).setCellRenderer(cellRenderer);
        }
    }

    //Test function
    private void testTable(){

        addRowTeamTable("Jacob Drake", "John Smith", vaultModel);
        addRowTeamTable("Janilou Sy","Jane Doe", vaultModel);
        addRowTeamTable("Hailey Porter", "Adriana Lanier",vaultModel);
        addRowTeamTable("Adriana Lanier","Hailey Porter", vaultModel);
        addRowTeamTable("John Smith", "Janilou Sy" ,vaultModel);
        addRowTeamTable("Jane Doe", "Jacob Drake",vaultModel);

        addRowTeamTable("Jacob Drake", "John Smith", barsModel);
        addRowTeamTable("Janilou Sy","Jane Doe", barsModel);
        addRowTeamTable("Hailey Porter", "Adriana Lanier",barsModel);
        addRowTeamTable("Adriana Lanier","Hailey Porter", barsModel);
        addRowTeamTable("John Smith", "Janilou Sy" ,barsModel);
        addRowTeamTable("Jane Doe", "Jacob Drake",barsModel);

        addRowTeamTable("Jacob Drake", "John Smith", beamModel);
        addRowTeamTable("Janilou Sy","Jane Doe", beamModel);
        addRowTeamTable("Hailey Porter", "Adriana Lanier",beamModel);
        addRowTeamTable("Adriana Lanier","Hailey Porter", beamModel);
        addRowTeamTable("John Smith", "Janilou Sy" ,beamModel);
        addRowTeamTable("Jane Doe", "Jacob Drake",beamModel);

        addRowTeamTable("Jacob Drake", "John Smith", floorModel);
        addRowTeamTable("Janilou Sy","Jane Doe", floorModel);
        addRowTeamTable("Hailey Porter", "Adriana Lanier",floorModel);
        addRowTeamTable("Adriana Lanier","Hailey Porter", floorModel);
        addRowTeamTable("John Smith", "Janilou Sy" ,floorModel);
        addRowTeamTable("Jane Doe", "Jacob Drake",floorModel);


        String[] vaultJudges = new String[]{"Jacob Drake", "Adriana Lanier", "Hailey Porter","Janilou Sy", "John Smith", "Jane Doe"};
        String[] barJudges = new String[]{"Adriana Lanier", "Janilou Sy", "Jacob Drake","Janilou Sy", "John Smith", "Jane Doe"};
        String[] beamJudges = new String[]{"John Smith", "Adriana Lanier", "Hailey Porter","Janilou Sy", "Jacob Drake", "Jane Doe"};
        String[] floorJudges = new String[]{"Jacob Drake", "Jane Doe", "Hailey Porter","Janilou Sy", "John Smith", "Adriana Lanier"};

        for (int i = 0; i <= 5; i++){
            addRowsJudgeTable(vaultJudges[i], barJudges[i], beamJudges[i], floorJudges[i], judgesModel);
        }

    }


    //Table Header Font
    Font font = new Font ("Verdana", Font.PLAIN, 18);

    //Summary Screen Table Variables
    private DefaultTableModel vaultModel = new DefaultTableModel();
    private DefaultTableModel barsModel = new DefaultTableModel();
    private DefaultTableModel beamModel = new DefaultTableModel();
    private DefaultTableModel floorModel = new DefaultTableModel();
    private DefaultTableModel judgesModel = new DefaultTableModel();


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
    private JPanel meetTemplate;
    private CardLayout cardLayout;
    private JLabel selectedMeetFormat;
    private JPanel setupVault;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JComboBox comboBox5;
    private JComboBox comboBox6;
    private JSpinner spinner1;
    private JButton vaultBackButton;
    private JSpinner spinner2;
    private JSpinner spinner3;
    private JSpinner spinner4;
    private JSpinner spinner5;
    private JSpinner spinner6;
    private JButton meetTemplateBackButton;
    private JSpinner spinner7;
    private JSpinner spinner8;
    private JSpinner spinner9;
    private JSpinner spinner10;
    private JSpinner spinner11;
    private JSpinner spinner12;
    private JComboBox comboBox7;
    private JComboBox comboBox8;
    private JButton goBackButton;
    private JButton continueButton;
    private JButton createTeamButton;
    private JButton nextButton;
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
    private JButton backButton;
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
