package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EditLineupScreen extends JDialog {

    //Passing in home and visitor team into constructor, you might need to pass in more info
    public EditLineupScreen(Team home, Team visitor, int currentRotation) {

        setContentPane(mainPanel);
        setTitle("Edit Lineup Screen");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        setModalityType(ModalityType.APPLICATION_MODAL);

        //This will be useful when passing back the home Team. If it's true, it will be modified, else, it will just as is
        editHome = home;            //Sets editHome to the passed in
        editVisitor = visitor;      //Sets editVisitor to the passed in

        //card layout start
        cardLayout = (CardLayout) mainPanel.getLayout();
        changeCard("DefaultCard");

        //set logos
        homeLogo.setIcon(new ImageIcon("src/com/company/pictures/" + home.getTeamLogo()));
        visitorLogo.setIcon(new ImageIcon("src/com/company/pictures/" + visitor.getTeamLogo()));


       //At the start, set to currentRotation
        rotationNum = currentRotation;                   //Current rotation it's at in the run mode
        rotationCombo.setSelectedIndex(currentRotation-1);
        updateRotation(rotationCombo, home, visitor, rotationNum);

        homeSubButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Home Team Substitute Player Button Press");
                //We populate the combobox now. If it's home,
                //Populate currentCombo and allCombo
                switch (rotationCombo.getSelectedIndex()) {
                    case 0: populateComboboxSub(home.getVaultGymnasts(), home); break;
                    case 1: populateComboboxSub(home.getBarGymnasts(), home); break;
                    case 2: populateComboboxSub(home.getBeamGymnasts(), home); break;
                    case 3: populateComboboxSub(home.getFloorGymnasts(), home); break;
                }
                changeCard("SubGymnastCard");
            }
        });

        visitorSubButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Visitor Team Substitute Player Button Press");
                //Populate currentCombo and allCombo
                switch (rotationCombo.getSelectedIndex()) {
                    case 0: populateComboboxSub(visitor.getBarGymnasts(), visitor); break;
                    case 1: populateComboboxSub(visitor.getVaultGymnasts(), visitor); break;
                    case 2: populateComboboxSub(visitor.getFloorGymnasts(), visitor); break;
                    case 3: populateComboboxSub(visitor.getBeamGymnasts(), visitor); break;
                }
                changeCard("SubGymnastCard");
            }
        });

        saveChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean valid = false;
                switch(rotationCombo.getSelectedIndex()){
                    case 0:
                        if(valid = checkSaveValid(home.getVaultGymnasts(), visitor.getBarGymnasts())){      //Checks if valid
                            organizedList(home.getVaultGymnasts(), gethomeCombo(), ApparatusIndex.VT);        //
                            organizedList(visitor.getBarGymnasts(), getvisitorCombo(), ApparatusIndex.UB);

                            home.arrangeByOrderList(home.getVaultGymnasts(), ApparatusIndex.VT);
                            visitor.arrangeByOrderList(visitor.getBarGymnasts(), ApparatusIndex.UB);
                        } break;
                    case 1:
                        if(valid = checkSaveValid(home.getBarGymnasts(), visitor.getVaultGymnasts())){
                            organizedList(home.getBarGymnasts(), gethomeCombo(), ApparatusIndex.UB);        //
                            organizedList(visitor.getVaultGymnasts(), getvisitorCombo(), ApparatusIndex.VT);

                            home.arrangeByOrderList(home.getBarGymnasts(), ApparatusIndex.UB);
                            visitor.arrangeByOrderList(visitor.getVaultGymnasts(), ApparatusIndex.VT);
                        } break;
                    case 2:
                        if(valid = checkSaveValid(home.getBeamGymnasts(), visitor.getFloorGymnasts())){
                            organizedList(home.getBeamGymnasts(), gethomeCombo(), ApparatusIndex.BB);        //
                            organizedList(visitor.getFloorGymnasts(), getvisitorCombo(), ApparatusIndex.FX);

                            home.arrangeByOrderList(home.getBeamGymnasts(), ApparatusIndex.BB);
                            visitor.arrangeByOrderList(visitor.getFloorGymnasts(), ApparatusIndex.FX);
                        } break;
                    case 3:
                        if(valid =checkSaveValid(home.getFloorGymnasts(), visitor.getBeamGymnasts())){
                            organizedList(home.getFloorGymnasts(), gethomeCombo(), ApparatusIndex.FX);        //
                            organizedList(visitor.getBeamGymnasts(), getvisitorCombo(), ApparatusIndex.BB);

                            home.arrangeByOrderList(home.getFloorGymnasts(), ApparatusIndex.FX);
                            visitor.arrangeByOrderList(visitor.getBeamGymnasts(), ApparatusIndex.BB);
                        } break;
                }
                if(valid){
                    editHome = home;
                    editVisitor = visitor;
                    dispose();
                }
            }
        });
        rotationCombo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                //feel free to change this
                updateRotation(rotationCombo, home, visitor, rotationNum);
            }
        });

        saveChangesButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //We gotta determine if we selected from home or visitor
                Team temp = null;
                List<String> homeName = new ArrayList<>();
                home.getAllGymnasts().forEach(
                        gymnast->{
                            homeName.add(gymnast.getPlayerfName() +" "+ gymnast.getPlayerlName());
                        }
                );

                int option = JOptionPane.showConfirmDialog(null, currentCombo.getSelectedItem().toString()
                        +"  will be replaced by " + allCombo.getSelectedItem().toString()+ "."
                        + "\nAre you sure you want to continue?");
                if(option == 0){
                    if(homeName.contains(currentCombo.getSelectedItem().toString())){
                        //means they are home team
                        System.out.println("This is the home team");
                        switch (rotationCombo.getSelectedIndex()) {
                            case 0: temp = updatesubstituteGymnast(ApparatusIndex.VT, home); break;
                            case 1: temp = updatesubstituteGymnast(ApparatusIndex.UB, home); break;
                            case 2: temp = updatesubstituteGymnast(ApparatusIndex.BB, home); break;
                            case 3: temp = updatesubstituteGymnast(ApparatusIndex.FX, home); break;
                        }
                        editHome= temp;
                    }else{
                        //means they are visitor
                        System.out.println("This is the visitor team");
                        switch (rotationCombo.getSelectedIndex()) {
                            case 0: temp = updatesubstituteGymnast(ApparatusIndex.UB, visitor); break;
                            case 1: temp = updatesubstituteGymnast(ApparatusIndex.VT, visitor); break;
                            case 2: temp = updatesubstituteGymnast(ApparatusIndex.FX, visitor); break;
                            case 3: temp = updatesubstituteGymnast(ApparatusIndex.BB, visitor); break;
                        }
                        editVisitor = temp;
                    }
                    temp.updateApparatusLists();
                    //temp.printAll();
                    updateRotation(rotationCombo, editHome, editVisitor, rotationNum);
                    changeCard("DefaultCard");
                }
            }
        });
        subbackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeCard("DefaultCard");
            }
        });


        setVisible(true);   //KEEP THIS AT THE BOTTOM!!!
    }

    private void updateRotation(JComboBox rotationCombo, Team home, Team visitor, int rotationNum) {
        currentRotation = false;                            //This will help us determine how to populate comboboxes
        switch (rotationCombo.getSelectedIndex()) {
            case 0:
                if (rotationNum-1 == 0) currentRotation = true;       //Selected index is it's the current rotation it's at in run
                homeEventLabel.setText("VAULT");
                visitorEventLabel.setText("BARS");
                populateCombobox(gethomeCombo(), home.getVaultGymnasts(), currentRotation);
                populateCombobox(getvisitorCombo(), visitor.getBarGymnasts(), currentRotation);
                break;
            case 1:
                if (rotationNum-1 == 1) currentRotation = true;       //Selected index is the currentRotation
                homeEventLabel.setText("BARS");
                visitorEventLabel.setText("VAULT");
                populateCombobox(gethomeCombo(), home.getBarGymnasts(), currentRotation);
                populateCombobox(getvisitorCombo(), visitor.getVaultGymnasts(),currentRotation);
                break;
            case 2:
                if (rotationNum-1 == 2) currentRotation = true;       //Selected index is the currentRotation
                homeEventLabel.setText("BEAM");
                visitorEventLabel.setText("FLOOR");
                populateCombobox(gethomeCombo(), home.getBeamGymnasts(),currentRotation);
                populateCombobox(getvisitorCombo(), visitor.getFloorGymnasts(), currentRotation);
                break;
            case 3:
                if (rotationNum-1 == 3) currentRotation = true;       //Selected index is the currentRotation
                homeEventLabel.setText("FLOOR");
                visitorEventLabel.setText("BEAM");
                populateCombobox(gethomeCombo(), home.getFloorGymnasts(), currentRotation);
                populateCombobox(getvisitorCombo(), visitor.getBeamGymnasts(), currentRotation);
                break;
        }
    }

    public void changeCard(String cardName) {
        cardLayout.show(mainPanel, cardName);
    }

    public List<JComboBox> gethomeCombo() {
        return Arrays.asList(h1, h2, h3, h4, h5, h6);
    }

    public List<JComboBox> getvisitorCombo() {
        return Arrays.asList(v1, v2, v3, v4, v5, v6);
    }

    //Method that will return the modified homeTeam
    public Team getEditHome() {
        return editHome;
    }

    //Method that will return the modified visitorTeam
    public Team getEditVisitor() {
        return editVisitor;
    }

    //Method that will populate the combobox depending on the passed in parameters
    public void populateCombobox(List<JComboBox> CBList, List<Player> eventGymnasts, boolean current) {
        System.out.println("populateCombobox: Is this the current?" + current);
        //Populates comboboxes depending on the list
        for (int i = 0; i < CBList.size(); i++) {
            CBList.get(i).removeAllItems();
            for (int j = 0; j < eventGymnasts.size(); j++) {
                if(current){    //Checks if this is the currentRotation it's at, if so, just populate the cb with 1 gymnast only
                    if(i == j){
                        CBList.get(i).addItem(eventGymnasts.get(j).getPlayerfName() + " " + eventGymnasts.get(j).getPlayerlName());
                    }
                }else{          //Populates the cb with all gymnast in the event, meaning we can rearrange order
                    CBList.get(i).addItem(eventGymnasts.get(j).getPlayerfName() + " " + eventGymnasts.get(j).getPlayerlName());
                    if(i == j){
                        CBList.get(i).setSelectedIndex(i);
                    }
                }
            }
        }
    }

    //Method that will check if they can save. Pass in the list of players for an event.
    public boolean checkSaveValid( List<Player> homeGymnast, List<Player> visitorGymnast){
        String[] homeNames = new String[6];
        String[] visitorNames = new String[6];
        String[] cbhomeNames = new String[6];
        String[] cbvisitorNames = new String[6];
        boolean valid = true;
        //Populate homeNames, visitorNames, cbhomeNames, cbvisitorNames,
        for(int i = 0; i < 6; i++){
            cbhomeNames[i] = gethomeCombo().get(i).getSelectedItem().toString();              //These are the names from the combobox
            cbvisitorNames[i] = getvisitorCombo().get(i).getSelectedItem().toString();        //These are the names from the combobox
            homeNames[i] = homeGymnast.get(i).getPlayerfName() + " " + homeGymnast.get(i).getPlayerlName();                   //These are the names from the list
            visitorNames[i] = visitorGymnast.get(i).getPlayerfName() + " " + visitorGymnast.get(i).getPlayerlName();          //These are the names from the list

        }
        //Loop that checks if there is a duplicate; Ideally the homeName should have all participating event gymnast.
        //                      If not in the cb, means they were not added
        for(String name : homeNames){
            if(!Arrays.asList(cbhomeNames).contains(name)){
                JOptionPane.showMessageDialog(null, "Please add " + name +" to the line up.");
                return false;
            }
        }
        for(String name : visitorNames){
            if(!Arrays.asList(cbvisitorNames).contains(name)){
                JOptionPane.showMessageDialog(null, "Please add " + name +" to the line up.");
                return false;
            }
        }
        JOptionPane.showMessageDialog(null, "Success.");
        return true;
    }

    //Method that returns the modified list of gymnast with their appropriate order number
    public void organizedList(List<Player> eventGymnast, List<JComboBox> CBList, int appratusIndex){
        //Match the name in the list passed in
        ArrayList<String> cbNames = new ArrayList<>();
        for(int i = 0; i < 6; i++){
            cbNames.add(CBList.get(i).getSelectedItem().toString());              //These are the names from the combobox
        }
        //At this point, the array of string has the right arrangement 0-1st, 1-2nd, etc
        for(int i = 0; i<6;i++) {
            if ((eventGymnast.get(i).getPlayerfName() + " " + eventGymnast.get(i).getPlayerlName()).equals(cbNames.get(i))){
            } else {
                //Means that the name in the CB is NOT aligned with the eventGymnast
                //Find the player with name from the CB to the eventGymnast
                int finalI = i;
                eventGymnast.stream().forEach(
                        gymnast -> {
                            if ((gymnast.getPlayerfName() + " " + gymnast.getPlayerlName()).equals(cbNames.get(finalI))) {
                                gymnast.setApparatusOrderByIndex(appratusIndex, finalI + 1);
                            }
                        }
                );
            }
        }
    }

    //Method that will populate comboboxes in the substitute mode
    public void populateComboboxSub(List<Player> eventGymnast, Team team){
        currentCombo.removeAllItems();      //Reset combobox
        allCombo.removeAllItems();      //Reset combobox
        //Fills up the combobox for the current gymnast in the event
        for(int i = 0; i < team.getAllGymnasts().size() ;i++){
            //Checks if the event gymnast list contains an element from allGymnast
            //AKA if the current gymnast in allGymnast is a player in current event
            if(eventGymnast.contains(team.getAllGymnasts().get(i))){
                currentCombo.addItem(team.getAllGymnasts().get(i).getPlayerfName() + " " + team.getAllGymnasts().get(i).getPlayerlName());
            }else {
                allCombo.addItem(team.getAllGymnasts().get(i).getPlayerfName() + " " + team.getAllGymnasts().get(i).getPlayerlName());
            }
        }
    }

    //Method that will change the passed in list of gymnast for an event (VT, UB, BB, FX), and update their team class
    public Team updatesubstituteGymnast(int apparatusIndex, Team team){
        List<Player> eventTemp = new ArrayList<>();
        Player playerTemp = null;
        int order = 0;

        switch(apparatusIndex){
            case (ApparatusIndex.VT): eventTemp = team.getVaultGymnasts(); break;
            case (ApparatusIndex.UB): eventTemp = team.getBarGymnasts(); break;
            case (ApparatusIndex.BB): eventTemp = team.getBeamGymnasts(); break;
            case (ApparatusIndex.FX): eventTemp = team.getFloorGymnasts(); break;
        }

        for(int i = 0; i < eventTemp.size();i++){
            if((eventTemp.get(i).getPlayerfName() + " " +eventTemp.get(i).getPlayerlName()).equals(currentCombo.getSelectedItem().toString())){
                playerTemp = eventTemp.get(i);
                order = eventTemp.get(i).getApparatusOrder()[apparatusIndex];
                eventTemp.get(i).setApparatusOrderByIndex(apparatusIndex, 0);
                eventTemp.get(i).setApparatusStatusByIndex(apparatusIndex, false);
            }
        }
        eventTemp.remove(playerTemp);
        for(int j = 0; j < team.getAllGymnasts().size() ; j++){
            if((team.getAllGymnasts().get(j).getPlayerfName() + " " +team.getAllGymnasts().get(j).getPlayerlName()).equals(allCombo.getSelectedItem().toString())){
                team.getAllGymnasts().get(j).setApparatusStatusByIndex(apparatusIndex, true);
                team.getAllGymnasts().get(j).setApparatusOrderByIndex(apparatusIndex, order);
                eventTemp.add(team.getAllGymnasts().get(j));
            }
        }

        switch(apparatusIndex){
            case (ApparatusIndex.VT): team.setVaultGymnasts(eventTemp); break;
            case (ApparatusIndex.UB): team.setBarGymnasts(eventTemp); break;
            case (ApparatusIndex.BB): team.setBeamGymnasts(eventTemp); break;
            case (ApparatusIndex.FX): team.setFloorGymnasts(eventTemp); break;
        }
        return team;
    }

    private Team editHome;
    private Team editVisitor;
    private int rotationNum;
    private boolean currentRotation;
    public CardLayout cardLayout;
    private JPanel mainPanel;
    private JFrame frame;
    private JPanel defaultPanel;
    private JComboBox h6;
    private JComboBox h1;
    private JComboBox h4;
    private JComboBox h5;
    private JComboBox h3;
    private JComboBox h2;
    private JComboBox v1;
    private JComboBox v2;
    private JComboBox v4;
    private JComboBox v5;
    private JComboBox v3;

    private JLabel homeEventLabel;
    private JLabel visitorEventLabel;
    private JComboBox v6;
    private JButton saveChangesButton;
    private JComboBox rotationCombo;
    private JLabel homeLogo;
    private JLabel visitorLogo;
    private JButton homeSubButton;
    private JButton visitorSubButton;
    private JPanel substituteGymnast;
    private JButton saveChangesButton1;
    private JComboBox currentCombo;
    private JComboBox allCombo;
    private JButton subbackButton;
}
