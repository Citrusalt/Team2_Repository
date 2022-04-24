package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class SetupModeQuad {

    public SetupModeQuad(GuiCreator gC){

        JFrame frame = new JFrame ("Quadrangular Setup Mode");
        frame.setContentPane(setupModeQuadPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        DatabaseManager db = new DatabaseManager();         //Creates instance of DatabaseManager
        List<Team> allTeamfromDB = db.getAllTeams();


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

        //Puts values in comboboxes
        homeCombo.addItem("-Select Team-");
        visitor1Combo.addItem("-Select Team-");
        visitor2Combo.addItem("-Select Team-");
        visitor3Combo.addItem("-Select Team-");

        for(int i = 0; i < allTeamfromDB.size(); i++){
            homeCombo.addItem(allTeamfromDB.get(i).getTeamName());
            visitor1Combo.addItem(allTeamfromDB.get(i).getTeamName());
            visitor2Combo.addItem(allTeamfromDB.get(i).getTeamName());
            visitor3Combo.addItem(allTeamfromDB.get(i).getTeamName());
        }

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

                        //************POPULATES ALL COMBOBOXES*************
                        int homeIndex = homeCombo.getSelectedIndex();//Stores the index of the user selection
                        int visitor1Index = visitor1Combo.getSelectedIndex();
                        int visitor2Index = visitor2Combo.getSelectedIndex();
                        int visitor3Index = visitor3Combo.getSelectedIndex();

                        Team home = allTeamfromDB.get(homeIndex-1);             //This has the team data for the home team
                        Team visitor1 = allTeamfromDB.get(visitor1Index-1);       //This has the team data for the visitor team
                        Team visitor2 = allTeamfromDB.get(visitor2Index-1);
                        Team visitor3 = allTeamfromDB.get(visitor3Index-1);

                        //Vault Screen
                        homeVaultLabel.setText(home.getTeamName());
                        visitor1VaultLabel.setText(visitor1.getTeamName());
                        visitor2VaultLabel.setText(visitor2.getTeamName());
                        visitor3VaultLabel.setText(visitor3.getTeamName());

                        //Uneven Bars Screen
                        homeBarsLabel.setText(home.getTeamName());
                        visitor1BarsLabel.setText(visitor1.getTeamName());
                        visitor2BarsLabel.setText(visitor2.getTeamName());
                        visitor3BarsLabel.setText(visitor3.getTeamName());

                        //Balance Beam Screen
                        homeBBLabel.setText(home.getTeamName());
                        visitor1BBLabel.setText(visitor1.getTeamName());
                        visitor2BBLabel.setText(visitor2.getTeamName());
                        visitor3BBLabel.setText(visitor3.getTeamName());

                        //Floor Screen
                        homeFloorLabel.setText(home.getTeamName());
                        visitor1FloorLabel.setText(visitor1.getTeamName());
                        visitor2FloorLabel.setText(visitor2.getTeamName());
                        visitor3FloorLabel.setText(visitor3.getTeamName());

                        //Populates ALL ComboBoxes for the HomeTeam; This is where those Lists of comboboxes are used
                        List<JComboBox> homeCombo = gethomeCombo();
                        for(int i = 0; i < homeCombo.size(); i++){
                            homeCombo.get(i).removeAllItems();
                            homeCombo.get(i).addItem("- Select Gymnast -");
                            for(int j = 0; j < home.getAllGymnasts().size(); j++){
                                homeCombo.get(i).addItem(home.getAllGymnasts().get(j).getPlayerfName() + " " +home.getAllGymnasts().get(j).getPlayerlName());
                            }
                        }
                        //Populates ALL ComboBox for the Visitor1Team
                        List<JComboBox> visitor1Combo = getvisitor1Combo();
                        for(int i = 0; i < visitor1Combo.size(); i++){
                            visitor1Combo.get(i).removeAllItems();
                            visitor1Combo.get(i).addItem("- Select Gymnast -");
                            for(int j = 0; j < visitor1.getAllGymnasts().size(); j++){
                                visitor1Combo.get(i).addItem(visitor1.getAllGymnasts().get(j).getPlayerfName() + " " +visitor1.getAllGymnasts().get(j).getPlayerlName());
                            }
                        }
                        //Populates ALL ComboBox for the Visitor2Team
                        List<JComboBox> visitor2Combo = getvisitor2Combo();
                        for(int i = 0; i < visitor2Combo.size(); i++){
                            visitor2Combo.get(i).removeAllItems();
                            visitor2Combo.get(i).addItem("- Select Gymnast -");
                            for(int j = 0; j < visitor2.getAllGymnasts().size(); j++){
                                visitor2Combo.get(i).addItem(visitor2.getAllGymnasts().get(j).getPlayerfName() + " " +visitor2.getAllGymnasts().get(j).getPlayerlName());
                            }
                        }
                        //Populates ALL ComboBox for the Visitor2Team
                        List<JComboBox> visitor3Combo = getvisitor3Combo();
                        for(int i = 0; i < visitor3Combo.size(); i++){
                            visitor3Combo.get(i).removeAllItems();
                            visitor3Combo.get(i).addItem("- Select Gymnast -");
                            for(int j = 0; j < visitor3.getAllGymnasts().size(); j++){
                                visitor3Combo.get(i).addItem(visitor3.getAllGymnasts().get(j).getPlayerfName() + " " +visitor3.getAllGymnasts().get(j).getPlayerlName());
                            }
                        }

                        //Populate Judges Comboboxes
                        String[] judgesNames = {
                                "Kevin McNamara", "Patricia Boudreaux", "Kaitlyn Adams", "Michelle Martin",
                                "Beth Renwick", "Mark Welch", "Kathryn Kunn", "Debra Yohman", "Melissa Phillips",
                                "Jane Droese", "Joy Schmidt", "Jane Flynn"
                        };

                        List<JComboBox> judgesCombo = getjudgesCombo();
                        for (int k = 0; k<judgesCombo.size(); k++){
                            judgesCombo.get(k).removeAllItems();
                            judgesCombo.get(k).addItem("- Select Judges -");
                            for (int l = 0 ; l < judgesNames.length; l++){
                                judgesCombo.get(k).addItem(judgesNames[l]);
                            }
                        }
                        //************END POPULATE******************

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

                Object[]homeTeam = new Object[6];
                for (int i = 0; i < 6; i++){
                    homeTeam[i] = gethomeCombo().get(i).getSelectedItem();
                }
                Object[]visitor1Team = new Object[6];
                for (int i = 0; i < 6; i++){
                    visitor1Team[i] = getvisitor1Combo().get(i).getSelectedItem();
                }
                Object[]visitor2Team = new Object[6];
                for (int i = 0; i < 6; i++){
                    visitor2Team[i] = getvisitor2Combo().get(i).getSelectedItem();
                }
                Object[]visitor3Team = new Object[6];
                for (int i = 0; i < 6; i++){
                    visitor3Team[i] = getvisitor3Combo().get(i).getSelectedItem();
                }

                if (gC.checkUnique(homeTeam) && gC.checkUnique(visitor1Team) && gC.checkUnique(visitor2Team) && gC.checkUnique(visitor3Team)){
                    changeCard("SelectBarCard");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Error. All spots must be filled and no duplicate players.");
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

                Object[]homeTeam = new Object[6];
                for (int i = 0; i < 6; i++){
                    homeTeam[i] = gethomeCombo().get(i + 6).getSelectedItem();
                }
                Object[]visitor1Team = new Object[6];
                for (int i = 0; i < 6; i++){
                    visitor1Team[i] = getvisitor1Combo().get(i + 6).getSelectedItem();
                }
                Object[]visitor2Team = new Object[6];
                for (int i = 0; i < 6; i++){
                    visitor2Team[i] = getvisitor2Combo().get(i + 6).getSelectedItem();
                }
                Object[]visitor3Team = new Object[6];
                for (int i = 0; i < 6; i++){
                    visitor3Team[i] = getvisitor3Combo().get(i + 6).getSelectedItem();
                }

                if (gC.checkUnique(homeTeam) && gC.checkUnique(visitor1Team) && gC.checkUnique(visitor2Team) && gC.checkUnique(visitor3Team)){
                    changeCard("SelectBalanceBeamCard");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Error. All spots must be filled and no duplicate players.");
                }
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
                Object[]homeTeam = new Object[6];
                for (int i = 0; i < 6; i++){
                    homeTeam[i] = gethomeCombo().get(i + 12).getSelectedItem();
                }
                Object[]visitor1Team = new Object[6];
                for (int i = 0; i < 6; i++){
                    visitor1Team[i] = getvisitor1Combo().get(i + 12).getSelectedItem();
                }
                Object[]visitor2Team = new Object[6];
                for (int i = 0; i < 6; i++){
                    visitor2Team[i] = getvisitor2Combo().get(i + 12).getSelectedItem();
                }
                Object[]visitor3Team = new Object[6];
                for (int i = 0; i < 6; i++){
                    visitor3Team[i] = getvisitor3Combo().get(i + 12).getSelectedItem();
                }

                if (gC.checkUnique(homeTeam) && gC.checkUnique(visitor1Team) && gC.checkUnique(visitor2Team)&& gC.checkUnique(visitor3Team)){
                    changeCard("SelectFloorCard");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Error. All spots must be filled and no duplicate players.");
                }
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
                Object[]homeTeam = new Object[6];
                for (int i = 0; i < 6; i++){
                    homeTeam[i] = gethomeCombo().get(i + 18).getSelectedItem();
                }
                Object[]visitor1Team = new Object[6];
                for (int i = 0; i < 6; i++){
                    visitor1Team[i] = getvisitor1Combo().get(i + 18).getSelectedItem();
                }
                Object[]visitor2Team = new Object[6];
                for (int i = 0; i < 6; i++){
                    visitor2Team[i] = getvisitor2Combo().get(i + 18).getSelectedItem();
                }
                Object[]visitor3Team = new Object[6];
                for (int i = 0; i < 6; i++){
                    visitor3Team[i] = getvisitor3Combo().get(i + 18).getSelectedItem();
                }

                if (gC.checkUnique(homeTeam) && gC.checkUnique(visitor1Team) && gC.checkUnique(visitor2Team) && gC.checkUnique(visitor3Team)){
                    changeCard("SelectJudgesCard");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Error. All spots must be filled and no duplicate players.");
                }
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
                Object[]vaultJudges = new Object[6];
                for (int i = 0; i < 6; i++){
                    vaultJudges[i] = getjudgesCombo().get(i).getSelectedItem();
                }
                Object[]barJudges = new Object[6];
                for (int i = 0; i < 6; i++){
                    barJudges[i] = getjudgesCombo().get(i + 6).getSelectedItem();
                }
                Object[]beamJudges = new Object[6];
                for (int i = 0; i < 6; i++){
                    beamJudges[i] = getjudgesCombo().get(i + 12).getSelectedItem();
                }
                Object[]floorJudges = new Object[6];
                for (int i = 0; i < 6; i++){
                    floorJudges[i] = getjudgesCombo().get(i + 18).getSelectedItem();
                }

                if (gC.checkUnique(vaultJudges) && gC.checkUnique(barJudges) && gC.checkUnique(beamJudges) && gC.checkUnique(floorJudges)){
                    //Calls the testTable class
                    resetTables(); //resets tables so that table isnt still filled with old values
                    testTable(gC);
                    changeCard("SummaryCard");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Error. At least two judges must be selected per event and no duplicate judges on the same event.");
                }
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
                List<Team> myTeams = updateTeamOrder(db);
                Team home = myTeams.get(0);
                Team visitor1 = myTeams.get(1);
                Team visitor2 = myTeams.get(2);
                Team visitor3 = myTeams.get(3);
                QuadScorekeeperScreen myQuadScorekeeperScreen = new QuadScorekeeperScreen(gC, home, visitor1, visitor2, visitor3, allJudges);
                frame.dispose();
            }
        });
    }

    public void changeCard(String cardName){
        cardLayout.show(setupModeQuadPanel, cardName);
    }
    public List<JComboBox> gethomeCombo(){
        return Arrays.asList(v11, v12, v13, v14, v15, v16,          //Vaults
                b11, b12, b13, b14, b15, b16,                       //Bars
                bb11, bb12, bb13, bb14, bb15, bb16,                 //Beams
                f11, f12, f13, f14, f15, f16);                      //Floors
    }

    //Method that contains the comboboxes for the visitor team
    public List<JComboBox> getvisitor1Combo(){
        return Arrays.asList(v21, v22, v23, v24, v25, v26,         //Vaults
                b21, b22, b23, b24, b25, b26,                      //Bars
                bb21, bb22, bb23,bb24, bb25, bb26,                 //Beams
                f21, f22,f23,f24, f25,f26);                        //Floors
    }
    //Method that contains the comboboxes for the visitor team
    public List<JComboBox> getvisitor2Combo(){
        return Arrays.asList(v31, v32, v33, v34, v35, v36,         //Vaults
                b31, b32, b33, b34, b35, b36,                      //Bars
                bb31, bb32, bb33,bb34, bb35, bb36,                 //Beams
                f31, f32,f33,f34, f35,f36);                        //Floors
    }
    //Method that contains the comboboxes for the visitor team
    public List<JComboBox> getvisitor3Combo(){
        return Arrays.asList(v41, v42, v43, v44, v45, v46,         //Vaults
                b41, b42, b43, b44, b45, b46,                      //Bars
                bb41, bb42, bb43,bb44, bb45, bb46,                 //Beams
                f41, f42,f43,f44, f45,f46);                        //Floors
    }

    //Method that contains the comboboxes for the visitor
    public List<JComboBox> getjudgesCombo(){
        return Arrays.asList(j11, j12, j13, j14, j15, j16,      //Vaults
                j21, j22, j23, j24, j25, j26,                   //Bars
                j31, j32, j33, j34, j35, j36,                   //Beams
                j41, j42, j43, j44, j45, j46);                  //Floors
    }

    private List<Team> updateTeamOrder(DatabaseManager db){

        int homeIndex = homeCombo.getSelectedIndex();
        int visitor1Index = visitor1Combo.getSelectedIndex();
        int visitor2Index = visitor2Combo.getSelectedIndex();
        int visitor3Index = visitor3Combo.getSelectedIndex();

        List<Team>allTeamfromDB = db.getAllTeams();

        //Data for each team inside these objects
        Team home = allTeamfromDB.get(homeIndex-1);
        Team visitor1 = allTeamfromDB.get(visitor1Index-1);
        Team visitor2 = allTeamfromDB.get(visitor2Index-1);
        Team visitor3 = allTeamfromDB.get(visitor3Index-1);


        List<JComboBox> homeBoxes = gethomeCombo();
        List<JComboBox> visitor1Boxes = getvisitor1Combo();
        List<JComboBox> visitor2Boxes = getvisitor2Combo();
        List<JComboBox> visitor3Boxes = getvisitor3Combo();


        //may god have mercy on me for this code
        //Vault Gymnasts
        for (int i = 0; i < 6; i++){
            for(int j = 0; j < home.getAllGymnasts().size(); j++){
                if (homeBoxes.get(i).getSelectedItem().toString() .equals (home.getAllGymnasts().get(j).getPlayerfName() + " " +  home.getAllGymnasts().get(j).getPlayerlName())){
                    home.getAllGymnasts().get(j).setApparatusStatusByIndex(ApparatusIndex.VT, true);
                    home.getAllGymnasts().get(j).setApparatusOrderByIndex(ApparatusIndex.VT, i + 1);
                }
            }
            for(int j = 0; j < visitor1.getAllGymnasts().size(); j++){
                if (visitor1Boxes.get(i).getSelectedItem().toString() .equals (visitor1.getAllGymnasts().get(j).getPlayerfName() + " " +  visitor1.getAllGymnasts().get(j).getPlayerlName())){
                    visitor1.getAllGymnasts().get(j).setApparatusStatusByIndex(ApparatusIndex.VT, true);
                    visitor1.getAllGymnasts().get(j).setApparatusOrderByIndex(ApparatusIndex.VT, i + 1);
                }
            }
            for(int j = 0; j < visitor2.getAllGymnasts().size(); j++){
                if (visitor2Boxes.get(i).getSelectedItem().toString() .equals (visitor2.getAllGymnasts().get(j).getPlayerfName() + " " +  visitor2.getAllGymnasts().get(j).getPlayerlName())){
                    visitor2.getAllGymnasts().get(j).setApparatusStatusByIndex(ApparatusIndex.VT, true);
                    visitor2.getAllGymnasts().get(j).setApparatusOrderByIndex(ApparatusIndex.VT, i + 1);
                }
            }
            for(int j = 0; j < visitor3.getAllGymnasts().size(); j++){
                if (visitor3Boxes.get(i).getSelectedItem().toString() .equals (visitor3.getAllGymnasts().get(j).getPlayerfName() + " " +  visitor3.getAllGymnasts().get(j).getPlayerlName())){
                    visitor3.getAllGymnasts().get(j).setApparatusStatusByIndex(ApparatusIndex.VT, true);
                    visitor3.getAllGymnasts().get(j).setApparatusOrderByIndex(ApparatusIndex.VT, i + 1);
                }
            }
        }
        //Bar Gymnasts
        for (int i = 6; i < 12; i++){
            for(int j = 0; j < home.getAllGymnasts().size(); j++){
                if (homeBoxes.get(i).getSelectedItem().toString() .equals (home.getAllGymnasts().get(j).getPlayerfName() + " " +  home.getAllGymnasts().get(j).getPlayerlName())){
                    home.getAllGymnasts().get(j).setApparatusStatusByIndex(ApparatusIndex.UB, true);
                    home.getAllGymnasts().get(j).setApparatusOrderByIndex(ApparatusIndex.UB, i - 5);
                }
            }
            for(int j = 0; j < visitor1.getAllGymnasts().size(); j++){
                if (visitor1Boxes.get(i).getSelectedItem().toString() .equals (visitor1.getAllGymnasts().get(j).getPlayerfName() + " " +  visitor1.getAllGymnasts().get(j).getPlayerlName())){
                    visitor1.getAllGymnasts().get(j).setApparatusStatusByIndex(ApparatusIndex.UB, true);
                    visitor1.getAllGymnasts().get(j).setApparatusOrderByIndex(ApparatusIndex.UB, i - 5);
                }
            }
            for(int j = 0; j < visitor2.getAllGymnasts().size(); j++){
                if (visitor2Boxes.get(i).getSelectedItem().toString() .equals (visitor2.getAllGymnasts().get(j).getPlayerfName() + " " +  visitor2.getAllGymnasts().get(j).getPlayerlName())){
                    visitor2.getAllGymnasts().get(j).setApparatusStatusByIndex(ApparatusIndex.UB, true);
                    visitor2.getAllGymnasts().get(j).setApparatusOrderByIndex(ApparatusIndex.UB, i - 5);
                }
            }
            for(int j = 0; j < visitor3.getAllGymnasts().size(); j++){
                if (visitor3Boxes.get(i).getSelectedItem().toString() .equals (visitor3.getAllGymnasts().get(j).getPlayerfName() + " " +  visitor3.getAllGymnasts().get(j).getPlayerlName())){
                    visitor3.getAllGymnasts().get(j).setApparatusStatusByIndex(ApparatusIndex.UB, true);
                    visitor3.getAllGymnasts().get(j).setApparatusOrderByIndex(ApparatusIndex.UB, i - 5);
                }
            }
        }
        //Balance Beam Gymnasts
        for (int i = 12; i < 18; i++){
            for(int j = 0; j < home.getAllGymnasts().size(); j++){
                if (homeBoxes.get(i).getSelectedItem().toString() .equals (home.getAllGymnasts().get(j).getPlayerfName() + " " +  home.getAllGymnasts().get(j).getPlayerlName())){
                    home.getAllGymnasts().get(j).setApparatusStatusByIndex(ApparatusIndex.BB, true);
                    home.getAllGymnasts().get(j).setApparatusOrderByIndex(ApparatusIndex.BB, i -11);
                }
            }
            for(int j = 0; j < visitor1.getAllGymnasts().size(); j++){
                if (visitor1Boxes.get(i).getSelectedItem().toString() .equals (visitor1.getAllGymnasts().get(j).getPlayerfName() + " " +  visitor1.getAllGymnasts().get(j).getPlayerlName())){
                    visitor1.getAllGymnasts().get(j).setApparatusStatusByIndex(ApparatusIndex.BB, true);
                    visitor1.getAllGymnasts().get(j).setApparatusOrderByIndex(ApparatusIndex.BB, i - 11);
                }
            }
            for(int j = 0; j < visitor2.getAllGymnasts().size(); j++){
                if (visitor2Boxes.get(i).getSelectedItem().toString() .equals (visitor2.getAllGymnasts().get(j).getPlayerfName() + " " +  visitor2.getAllGymnasts().get(j).getPlayerlName())){
                    visitor2.getAllGymnasts().get(j).setApparatusStatusByIndex(ApparatusIndex.BB, true);
                    visitor2.getAllGymnasts().get(j).setApparatusOrderByIndex(ApparatusIndex.BB, i - 11);
                }
            }
            for(int j = 0; j < visitor3.getAllGymnasts().size(); j++){
                if (visitor3Boxes.get(i).getSelectedItem().toString() .equals (visitor3.getAllGymnasts().get(j).getPlayerfName() + " " +  visitor3.getAllGymnasts().get(j).getPlayerlName())){
                    visitor3.getAllGymnasts().get(j).setApparatusStatusByIndex(ApparatusIndex.BB, true);
                    visitor3.getAllGymnasts().get(j).setApparatusOrderByIndex(ApparatusIndex.BB, i - 11);
                }
            }
        }
        //Floor Gymnasts
        for (int i = 18; i < 24; i++){
            for(int j = 0; j < home.getAllGymnasts().size(); j++){
                if (homeBoxes.get(i).getSelectedItem().toString() .equals (home.getAllGymnasts().get(j).getPlayerfName() + " " +  home.getAllGymnasts().get(j).getPlayerlName())){
                    home.getAllGymnasts().get(j).setApparatusStatusByIndex(ApparatusIndex.FX, true);
                    home.getAllGymnasts().get(j).setApparatusOrderByIndex(ApparatusIndex.FX, i -17);
                }
            }
            for(int j = 0; j < visitor1.getAllGymnasts().size(); j++){
                if (visitor1Boxes.get(i).getSelectedItem().toString() .equals (visitor1.getAllGymnasts().get(j).getPlayerfName() + " " +  visitor1.getAllGymnasts().get(j).getPlayerlName())){
                    visitor1.getAllGymnasts().get(j).setApparatusStatusByIndex(ApparatusIndex.FX, true);
                    visitor1.getAllGymnasts().get(j).setApparatusOrderByIndex(ApparatusIndex.FX, i - 17);
                }
            }
            for(int j = 0; j < visitor2.getAllGymnasts().size(); j++){
                if (visitor2Boxes.get(i).getSelectedItem().toString() .equals (visitor2.getAllGymnasts().get(j).getPlayerfName() + " " +  visitor2.getAllGymnasts().get(j).getPlayerlName())){
                    visitor2.getAllGymnasts().get(j).setApparatusStatusByIndex(ApparatusIndex.FX, true);
                    visitor2.getAllGymnasts().get(j).setApparatusOrderByIndex(ApparatusIndex.FX, i - 17);
                }
            }
            for(int j = 0; j < visitor3.getAllGymnasts().size(); j++){
                if (visitor3Boxes.get(i).getSelectedItem().toString().equals (visitor3.getAllGymnasts().get(j).getPlayerfName() + " " +  visitor3.getAllGymnasts().get(j).getPlayerlName())){
                    visitor3.getAllGymnasts().get(j).setApparatusStatusByIndex(ApparatusIndex.FX, true);
                    visitor3.getAllGymnasts().get(j).setApparatusOrderByIndex(ApparatusIndex.FX, i - 17);
                }
            }
        }

        //Save teams to database here
        home.updateApparatusLists();
        visitor1.updateApparatusLists();
        visitor2.updateApparatusLists();
        visitor3.updateApparatusLists();

        List<Team>myTeams = new ArrayList<>();
        myTeams.add(home);
        myTeams.add(visitor1);
        myTeams.add(visitor2);
        myTeams.add(visitor3);

        return myTeams;
    }


    private void testTable(GuiCreator gC){

        List<JComboBox> home = gethomeCombo();
        List<JComboBox> visitor1 = getvisitor1Combo();
        List<JComboBox> visitor2 = getvisitor2Combo();
        List<JComboBox> visitor3 = getvisitor3Combo();
        List<JComboBox> judges = getjudgesCombo();

        //clear judges list
        allJudges.clear();
        vaultJudges.clear();
        barJudges.clear();
        beamJudges.clear();
        floorJudges.clear();

        String[] homeGymnasts= new String[24];
        for(int i = 0; i < home.size(); i++){
            homeGymnasts[i] = home.get(i).getSelectedItem().toString();
        }
        String[] visitor1Gymnasts = new String[24];
        for(int i = 0; i < visitor1.size(); i++){
            visitor1Gymnasts[i] = visitor1.get(i).getSelectedItem().toString();
        }
        String[] visitor2Gymnasts = new String[24];
        for(int i = 0; i < visitor2.size(); i++){
            visitor2Gymnasts[i] = visitor2.get(i).getSelectedItem().toString();
        }
        String[] visitor3Gymnasts = new String[24];
        for(int i = 0; i < visitor3.size(); i++){
            visitor3Gymnasts[i] = visitor3.get(i).getSelectedItem().toString();
        }
        String[] judgesNames = new String[24];
        for(int i = 0; i < judges.size(); i++){
            judgesNames[i] = judges.get(i).getSelectedItem().toString();
        }

        //Fill Tables
        for (int i = 0; i < 6; i++){
            String[] row = {homeGymnasts[i], visitor1Gymnasts[i], visitor2Gymnasts[i],visitor3Gymnasts[i]};
            gC.addRowTeamTable(row, vaultModel);
        }
        for (int i = 6; i < 12; i++){
            String[] row = {homeGymnasts[i], visitor1Gymnasts[i], visitor2Gymnasts[i], visitor3Gymnasts[i]};
            gC.addRowTeamTable(row, barsModel);
        }
        for (int i = 12; i < 18; i++){
            String[] row = {homeGymnasts[i], visitor1Gymnasts[i], visitor2Gymnasts[i],visitor3Gymnasts[i]};
            gC.addRowTeamTable(row, beamModel);
        }
        for (int i = 18; i < 24; i++){
            String[] row = {homeGymnasts[i], visitor1Gymnasts[i], visitor2Gymnasts[i],visitor3Gymnasts[i]};
            gC.addRowTeamTable(row, floorModel);
        }

        for (int i = 0; i <= 5; i++){
            gC.addRowsJudgeTable(judgesNames[i],judgesNames[i+6],judgesNames[i+12],judgesNames[i+18], judgesModel);
            vaultJudges.add(judgesNames[i]);
            barJudges.add(judgesNames[i+6]);
            beamJudges.add(judgesNames[i+12]);
            floorJudges.add(judgesNames[i+18]);
        }

        allJudges.add(vaultJudges);
        allJudges.add(barJudges);
        allJudges.add(beamJudges);
        allJudges.add(floorJudges);

    }
    //clears tables
    private void resetTables(){
        vaultModel.setRowCount(0);
        barsModel.setRowCount(0);
        beamModel.setRowCount(0);
        floorModel.setRowCount(0);
        judgesModel.setRowCount(0);
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

    private List<List<String>> allJudges = new ArrayList<>();
    private List<String> vaultJudges = new ArrayList<>();
    private List<String> barJudges  = new ArrayList<>();
    private List<String> beamJudges = new ArrayList<>();
    private List<String> floorJudges = new ArrayList<>();


    private CardLayout cardLayout;
    private JButton meetTemplateBackButton;
    private JPanel setupModeQuadPanel;
    private JPanel teamSetup;
    private JButton goBackButton;
    private JComboBox homeCombo;
    private JComboBox visitor2Combo;
    private JButton continueButton;
    private JPanel setupVault;
    private JComboBox v16;
    private JComboBox v11;
    private JComboBox v14;
    private JComboBox v15;
    private JComboBox v13;
    private JComboBox v12;
    private JButton vaultBackButton;
    private JCheckBox thisApparatusRequiresACheckBox;
    private JButton vaultNextButton;
    private JPanel selectJudges;
    private JButton judgesBackButton;
    private JComboBox j11;
    private JComboBox j21;
    private JComboBox j31;
    private JComboBox j41;
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
    private JLabel homeVaultLabel;
    private JLabel visitor1VaultLabel;
    private JLabel visitor2VaultLabel;
    private JLabel visitor3VaultLabel;
    private JComboBox v21;
    private JComboBox v22;
    private JComboBox v23;
    private JComboBox v24;
    private JComboBox v25;
    private JComboBox v26;
    private JComboBox v31;
    private JComboBox v32;
    private JComboBox v33;
    private JComboBox v34;
    private JComboBox v35;
    private JComboBox v36;
    private JComboBox v41;
    private JComboBox v42;
    private JComboBox v43;
    private JComboBox v44;
    private JComboBox v45;
    private JComboBox v46;
    private JLabel homeBarsLabel;
    private JLabel visitor1BarsLabel;
    private JLabel visitor2BarsLabel;
    private JLabel visitor3BarsLabel;
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
    private JComboBox b31;
    private JComboBox b32;
    private JComboBox b33;
    private JComboBox b34;
    private JComboBox b35;
    private JComboBox b36;
    private JComboBox b41;
    private JComboBox b42;
    private JComboBox b43;
    private JComboBox b44;
    private JComboBox b45;
    private JComboBox b46;
    private JLabel homeBBLabel;
    private JLabel visitor1BBLabel;
    private JLabel visitor2BBLabel;
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
    private JComboBox bb31;
    private JComboBox bb32;
    private JComboBox bb33;
    private JComboBox bb34;
    private JComboBox bb35;
    private JComboBox bb36;
    private JComboBox bb41;
    private JComboBox bb42;
    private JComboBox bb43;
    private JComboBox bb44;
    private JComboBox bb45;
    private JComboBox bb46;
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
    private JComboBox f31;
    private JComboBox f32;
    private JComboBox f33;
    private JComboBox f34;
    private JComboBox f35;
    private JComboBox f36;
    private JComboBox f41;
    private JComboBox f42;
    private JComboBox f43;
    private JComboBox f44;
    private JComboBox f45;
    private JComboBox f46;
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
    private JLabel visitor3BBLabel;
    private JLabel homeFloorLabel;
    private JLabel visitor1FloorLabel;
    private JLabel visitor2FloorLabel;
    private JLabel visitor3FloorLabel;

}
