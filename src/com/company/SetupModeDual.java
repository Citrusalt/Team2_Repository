package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.company.Team;

public class SetupModeDual {

    public SetupModeDual(GuiCreator gC){

        JFrame frame = new JFrame ("Women's Gymnastics Scoreboard");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        String meetType = "dual";
        DatabaseManager db = new DatabaseManager();         //Creates instance of DatabaseManager
        List<Team>allTeamfromDB = db.getAllTeams();

        //create summary screen tables
        //This will later be placed in the Judges next button action listener most likely
        gC.createTeamTable(vaultTable, vaultModel, vaultRenderer, headerFont, 2 );
        gC.createTeamTable(barsTable, barsModel, barsRenderer,headerFont , 2);
        gC.createTeamTable(beamTable, beamModel, beamRenderer, headerFont, 2);
        gC.createTeamTable(floorTable, floorModel, floorRenderer, headerFont, 2);
        gC.createJudgeTable(judgesTable, judgesModel, judgesRenderer, headerFont);
        //Test Table
//        testTable(gC);

        //Card Layout start
        cardLayout = (CardLayout) mainPanel.getLayout();

        changeCard("StartScreenCard");


        //Puts value in ComboBoxes
        comboBox7.addItem("-Select Team-");
        comboBox8.addItem("-Select Team-");
        for(int i = 0; i < allTeamfromDB.size(); i++){
            comboBox7.addItem(allTeamfromDB.get(i).getTeamName());
            comboBox8.addItem(allTeamfromDB.get(i).getTeamName());
        }
        int homeIndex =0, visitorIndex = 0;             //Stores the index of the user selection

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
                    //************POPULATES ALL COMBOBOXES*************
                    int homeIndex = comboBox7.getSelectedIndex();//Stores the index of the user selection
                    int visitorIndex = comboBox8.getSelectedIndex();

                    Team home = allTeamfromDB.get(homeIndex-1);             //This has the team data for the home team
                    Team visitor = allTeamfromDB.get(visitorIndex-1);       //This has the team data for the visitor team

                    changeCard("SelectVaultCard");

                    //Change the label for the team name
                    homeNameLabel.setText(home.getTeamName());
                    visitorNameLabel.setText(visitor.getTeamName());
                    homeNameLabelUB.setText(home.getTeamName());
                    visitorNameLabelUB.setText(visitor.getTeamName());
                    homeNameLabelBB.setText(home.getTeamName());
                    visitorNameLabelBB.setText(visitor.getTeamName());
                    homeNameLabelFX.setText(home.getTeamName());
                    visitorNameLabelFX.setText(visitor.getTeamName());


                    //Populates ALL ComboBoxes for the HomeTeam; This is where those Lists of comboboxes are used
                    List<JComboBox> homeCombo = gethomeCombo();
                    for(int i = 0; i < homeCombo.size(); i++){
                        homeCombo.get(i).removeAllItems();
                        homeCombo.get(i).addItem("- Select Gymnast -");
                        for(int j = 0; j < home.getAllGymnasts().size(); j++){
                            homeCombo.get(i).addItem(home.getAllGymnasts().get(j).getPlayerfName() + " " +home.getAllGymnasts().get(j).getPlayerlName());
                        }
                    }
                    //Populates ALL ComboBox for the VisitorTeam
                    List<JComboBox> visitorCombo = getvisitorCombo();
                    for(int i = 0; i < visitorCombo.size(); i++){
                        visitorCombo.get(i).removeAllItems();
                        visitorCombo.get(i).addItem("- Select Gymnast -");
                        for(int j = 0; j < visitor.getAllGymnasts().size(); j++){
                            visitorCombo.get(i).addItem(visitor.getAllGymnasts().get(j).getPlayerfName() + " " +visitor.getAllGymnasts().get(j).getPlayerlName());
                        }
                    }
                    //************END POPULATE******************
                }
            }
        });
        createTeamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                    CreateTeamScreen myTeamScreen = new CreateTeamScreen(); //instantiate createTeamScreen Class
//                    myTeamScreen.createTeamScreen(); //call constructor     //
//                frame.dispose();

                CreateTeamScreen2 myScreen = new CreateTeamScreen2 (); //numb of teams

            }
        });
        vaultNextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                Object[] team1 = {
//                        v11.getSelectedItem(),
//                        v12.getSelectedItem(),
//                        v13.getSelectedItem(),
//                        v14.getSelectedItem(),
//                        v15.getSelectedItem(),
//                        v16.getSelectedItem(),
//                };
//                Object[] team2 = {
//                        v21.getSelectedItem(),
//                        v22.getSelectedItem(),
//                        v23.getSelectedItem(),
//                        v24.getSelectedItem(),
//                        v25.getSelectedItem(),
//                        v26.getSelectedItem(),
//                };
//                if (gC.checkUnique(team1) && gC.checkUnique(team2)) {
//                    changeCard("SelectBarCard");
//                }
//                else{
//                    JOptionPane.showMessageDialog(null, "Team members cannot be the same. Try Again.");
//                }

                //Verify stuff if they're valid

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
//                Object[] team1 = {
//                        b11.getSelectedItem(),
//                        b12.getSelectedItem(),
//                        b13.getSelectedItem(),
//                        b14.getSelectedItem(),
//                        b15.getSelectedItem(),
//                        b16.getSelectedItem(),
//                };
//                Object[] team2 = {
//                        b21.getSelectedItem(),
//                        b22.getSelectedItem(),
//                        b23.getSelectedItem(),
//                        b24.getSelectedItem(),
//                        b25.getSelectedItem(),
//                        b26.getSelectedItem(),
//                };
//                if (gC.checkUnique(team1) && gC.checkUnique(team2)) {
//                    changeCard("SelectBalanceBeamCard");
//                }
//                else{
//                    JOptionPane.showMessageDialog(null, "Team members cannot be the same. Try Again.");
//                }

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
//                Object[] team1 = {
//                        bb11.getSelectedItem(),
//                        bb12.getSelectedItem(),
//                        bb13.getSelectedItem(),
//                        bb14.getSelectedItem(),
//                        bb15.getSelectedItem(),
//                        bb16.getSelectedItem(),
//                };
//                Object[] team2 = {
//                        bb21.getSelectedItem(),
//                        bb22.getSelectedItem(),
//                        bb23.getSelectedItem(),
//                        bb24.getSelectedItem(),
//                        bb25.getSelectedItem(),
//                        bb26.getSelectedItem(),
//                };
//                if (gC.checkUnique(team1) && gC.checkUnique(team2)) {
//                    changeCard("SelectFloorCard");
//                }
//                else{
//                    JOptionPane.showMessageDialog(null, "Team members cannot be the same. Try Again.");
//                }
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

//                Object[] team1 = {
//                        f11.getSelectedItem(),
//                        f12.getSelectedItem(),
//                        f13.getSelectedItem(),
//                        f14.getSelectedItem(),
//                        f15.getSelectedItem(),
//                        f16.getSelectedItem(),
//                };
//                Object[] team2 = {
//                        f21.getSelectedItem(),
//                        f22.getSelectedItem(),
//                        f23.getSelectedItem(),
//                        f24.getSelectedItem(),
//                        f25.getSelectedItem(),
//                        f26.getSelectedItem(),
//                };
//                if (gC.checkUnique(team1) && gC.checkUnique(team2)) {
//                    changeCard("SelectJudgesCard");
//                }
//                else{
//                    JOptionPane.showMessageDialog(null, "Team members cannot be the same. Try Again.");
//                }

                //Populate Judges Combobox
                String[] judgesNames = {"Kevin McNamara", "Patricia Boudreaux", "Kaitlyn Adams", "Michelle Martin"};
                List<JComboBox> judgesCombo = getjudgesCombo();
                for (int k = 0; k<judgesCombo.size(); k++){
                    judgesCombo.get(k).removeAllItems();
                    judgesCombo.get(k).addItem("- Select Judges -");
                    for (int l = 0 ; l < judgesNames.length; l++){
                        judgesCombo.get(k).addItem(judgesNames[l]);
                    }
                }

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
            public void actionPerformed(ActionEvent e) {
//                Object[] vaultJudges = {
//                        j11.getSelectedItem(),
//                        j12.getSelectedItem(),
//                        j13.getSelectedItem(),
//                        j14.getSelectedItem(),
//                        j15.getSelectedItem(),
//                        j16.getSelectedItem(),
//                };
//                Object[] barJudges = {
//                        j21.getSelectedItem(),
//                        j22.getSelectedItem(),
//                        j23.getSelectedItem(),
//                        j24.getSelectedItem(),
//                        j25.getSelectedItem(),
//                        j26.getSelectedItem(),
//                };
//                Object[] beamJudges = {
//                        j31.getSelectedItem(),
//                        j32.getSelectedItem(),
//                        j33.getSelectedItem(),
//                        j34.getSelectedItem(),
//                        j35.getSelectedItem(),
//                        j36.getSelectedItem(),
//                };
//                Object[] floorJudges = {
//                        j41.getSelectedItem(),
//                        j42.getSelectedItem(),
//                        j43.getSelectedItem(),
//                        j44.getSelectedItem(),
//                        j45.getSelectedItem(),
//                        j46.getSelectedItem(),
//                };


                //!!!!!!!!!!!
                // If u look at the scoresheet for NCAA, a judge can judge 2 events.
                // I notice that on the scoresheet for dual, there's 4 (2^2) judges, quad there was 16 (2^4)
                // Coincidence?                 -jlou (4.11.2022)
                //!!!!!!!!!!!


//                if (gC.checkUnique(vaultJudges) && gC.checkUnique(barJudges) && gC.checkUnique(beamJudges) && gC.checkUnique(floorJudges)) {
//                    changeCard("SummaryCard");
//                }
//                else{
//                    JOptionPane.showMessageDialog(null, "Judges cannot be the same. Try Again.");
//                }
                //Calls the testTable class
                testTable(gC);

                changeCard("SummaryCard");
            }
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
    //Method that contains the comboboxes for the home team
    public List<JComboBox> gethomeCombo(){
        return Arrays.asList(v11, v12, v13, v14, v15, v16,          //Vaults
                b11, b12, b13, b14, b15, b16,                       //Bars
                bb11, bb12, bb13, bb14, bb15, bb16,                 //Beams
                f11, f12, f13, f14, f15, f16);                      //Floors
    }

    //Method that contains the comboboxes for the visitor team
    public List<JComboBox> getvisitorCombo(){
        return Arrays.asList(v21, v22, v23, v24, v25, v26,         //Vaults
                b21, b22, b23, b24, b25, b26,                      //Bars
                bb21, bb22, bb23,bb24, bb25, bb26,                 //Beams
                f21, f22,f23,f24, f25,f26);                        //Floors
    }

    //Method that contains the comboboxes for the visitor
    public List<JComboBox> getjudgesCombo(){
        return Arrays.asList(j11, j12, j13, j14, j15, j16,      //Vaults
                j21, j22, j23, j24, j25, j26,                   //Bars
                j31, j32, j33, j34, j35, j36,                   //Beams
                j41, j42, j43, j44, j45, j46);                  //Floors
    }

    //Test function made to fill tables
    private void testTable(GuiCreator gC){
        List<JComboBox> home = gethomeCombo();
        List<JComboBox> visitor = getvisitorCombo();
        List<JComboBox> judges = getjudgesCombo();

        String[] homeGymnasts= new String[24];
        for(int i = 0; i < home.size(); i++){
            homeGymnasts[i] = home.get(i).getSelectedItem().toString();
        }
        String[] visitorGymnasts = new String[24];
        for(int i = 0; i < visitor.size(); i++){
            visitorGymnasts[i] = visitor.get(i).getSelectedItem().toString();
        }
        String[] judgesNames = new String[24];
        for(int i = 0; i < judges.size(); i++){
            judgesNames[i] = judges.get(i).getSelectedItem().toString();
        }

        String[] team1Example = {"Jacob Drake", "Janilou Sy", "Hailey Porter", "Adriana Lanier", "John Smith", "Jane Doe"};
        String[] team2Example = {"John Smith", "Jane Doe", "Adriana Lanier", "Hailey Porter", "Janilou Sy", "Jacob Drake"};

        //Fill Vault Table Example
        // This can all be combined into a single for-loop, just showing how to do one at a time
        //Use different string []'s for different events obviously
//        for (int i = 0; i <= 5; i++){
//            String[] row = {team1Example[i], team2Example[i]};
//            gC.addRowTeamTable(row, vaultModel);
//        }

        for (int i = 0; i < 6; i++){
            String[] row = {homeGymnasts[i], visitorGymnasts[i]};
            gC.addRowTeamTable(row, vaultModel);
        }

        //Fill Bar Table Example
//        for (int i = 0; i <= 5; i++){
//            String[] row = {team1Example[i], team2Example[i]};
//            gC.addRowTeamTable(row, barsModel);
//        }
        for (int i = 6; i < 12; i++){
            String[] row = {homeGymnasts[i], visitorGymnasts[i]};
            gC.addRowTeamTable(row, barsModel);
        }
        //Fill Beam Table Example
//        for (int i = 0; i <= 5; i++){
//            String[] row = {team1Example[i], team2Example[i]};
//            gC.addRowTeamTable(row, beamModel);
//        }
        for (int i = 12; i < 18; i++){
            String[] row = {homeGymnasts[i], visitorGymnasts[i]};
            gC.addRowTeamTable(row, beamModel);
        }
        //Fill Floor Table Example
//        for (int i = 0; i <= 5; i++){
//            String[] row = {team1Example[i], team2Example[i]};
//            gC.addRowTeamTable(row, floorModel);
//        }
        for (int i = 18; i < 24; i++){
            String[] row = {homeGymnasts[i], visitorGymnasts[i]};
            gC.addRowTeamTable(row, floorModel);
        }
//        String[] vaultJudges = new String[]{"Jacob Drake", "Adriana Lanier", "Hailey Porter","Janilou Sy", "John Smith", "Jane Doe"};
//        String[] barJudges = new String[]{"Adriana Lanier", "Janilou Sy", "Jacob Drake","Janilou Sy", "John Smith", "Jane Doe"};
//        String[] beamJudges = new String[]{"John Smith", "Adriana Lanier", "Hailey Porter","Janilou Sy", "Jacob Drake", "Jane Doe"};
//        String[] floorJudges = new String[]{"Jacob Drake", "Jane Doe", "Hailey Porter","Janilou Sy", "John Smith", "Adriana Lanier"};

        for (int i = 0; i <= 5; i++){
//            gC.addRowsJudgeTable(vaultJudges[i], barJudges[i], beamJudges[i], floorJudges[i], judgesModel);
            gC.addRowsJudgeTable(judgesNames[i],judgesNames[i+6],judgesNames[i+12],judgesNames[i+18], judgesModel);
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
    private JComboBox v16;
    private JComboBox v11;
    private JComboBox v14;
    private JComboBox v15;
    private JComboBox v13;
    private JComboBox v12;
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
    private JComboBox j11;
    private JComboBox j21;
    private JComboBox j31;
    private JComboBox j41;
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
    private JComboBox v21;
    private JComboBox v22;
    private JComboBox v23;
    private JComboBox v24;
    private JComboBox v25;
    private JComboBox v26;
    private JComboBox b11;
    private JComboBox b12;
    private JComboBox b13;
    private JComboBox b14;
    private JComboBox b15;
    private JComboBox b16;
    private JComboBox b21;
    private JComboBox b22;
    private JComboBox b23;
    private JComboBox b24;
    private JComboBox b25;
    private JComboBox b26;
    private JComboBox bb11;
    private JComboBox bb12;
    private JComboBox bb13;
    private JComboBox bb14;
    private JComboBox bb15;
    private JComboBox bb16;
    private JComboBox bb21;
    private JComboBox bb22;
    private JComboBox bb23;
    private JComboBox bb24;
    private JComboBox bb25;
    private JComboBox bb26;
    private JComboBox f11;
    private JComboBox f12;
    private JComboBox f13;
    private JComboBox f14;
    private JComboBox f15;
    private JComboBox f16;
    private JComboBox f21;
    private JComboBox f22;
    private JComboBox f23;
    private JComboBox f24;
    private JComboBox f25;
    private JComboBox f26;
    private JComboBox j12;
    private JComboBox j13;
    private JComboBox j14;
    private JComboBox j15;
    private JComboBox j16;
    private JComboBox j22;
    private JComboBox j23;
    private JComboBox j24;
    private JComboBox j25;
    private JComboBox j26;
    private JComboBox j32;
    private JComboBox j33;
    private JComboBox j34;
    private JComboBox j35;
    private JComboBox j36;
    private JComboBox j42;
    private JComboBox j43;
    private JComboBox j44;
    private JComboBox j45;
    private JComboBox j46;
    private JLabel homeNameLabel;
    private JLabel visitorNameLabel;
    private JLabel homeNameLabelUB;
    private JLabel visitorNameLabelUB;
    private JLabel homeNameLabelBB;
    private JLabel visitorNameLabelBB;
    private JLabel homeNameLabelFX;
    private JLabel visitorNameLabelFX;
    private JTextField clockTextField;
    private JButton startTimerButton;
    private JButton resetTimerButton;



}
