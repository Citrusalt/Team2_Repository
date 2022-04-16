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

        //card layout start
        cardLayout = (CardLayout) mainPanel.getLayout();
        changeCard("DefaultCard");

        //set logos
        homeLogo.setIcon(new ImageIcon("src/com/company/pictures/" + home.getTeamLogo()));
        visitorLogo.setIcon(new ImageIcon("src/com/company/pictures/" + visitor.getTeamLogo()));

       //At the start, set to currentRotation
        rotationNum = currentRotation;             //Current rotation it's at
        rotationCombo.setSelectedIndex(rotationNum-1);
        updateRotation(rotationCombo, home, visitor, rotationNum);

        homeSubButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Home Team Substitute Player Button Press");
                changeCard("SubGymnastCard");
            }
        });
        visitorSubButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Visitor Team Substitute Player Button Press");
                changeCard("SubGymnastCard");
            }
        });
        saveChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              if(checkSaveValid(home.getBarGymnasts(), visitor.getVaultGymnasts()))
                  dispose();
              else{}
            }
        });
        rotationCombo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                //feel free to change this
                updateRotation(rotationCombo, home, visitor, rotationNum);
            }
        });
        setVisible(true);   //KEEP THIS AT THE BOTTOM!!!
    }

    private void updateRotation(JComboBox rotationCombo, Team home, Team visitor, int rotationNum) {
        currentRotation = false;                            //This will help us determine how to populate comboboxes
        switch (rotationCombo.getSelectedIndex()) {
            case 0:
                if (rotationNum-1 == 0) currentRotation = true;       //Selected index is the currentRotation
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

    public List<Player> getcurrentHome(Team home){
        if(rotationCombo.getSelectedIndex() == 0){
            return home.getVaultGymnasts();
        }else if(rotationCombo.getSelectedIndex() == 1){
            return home.getBarGymnasts();
        }else if(rotationCombo.getSelectedIndex() == 2){
            return home.getBeamGymnasts();
        }else return home.getFloorGymnasts();
    }

    public List<Player> getcurrentVisitor(Team visitor){
        if(rotationCombo.getSelectedIndex() == 0){
            return visitor.getBarGymnasts();
        }else if(rotationCombo.getSelectedIndex() == 1){
            return visitor.getVaultGymnasts();
        }else if(rotationCombo.getSelectedIndex() == 2){
            return visitor.getFloorGymnasts();
        }else return visitor.getBeamGymnasts();
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

    //Method that will check if they can save
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
        //Loop that checks if there is a duplicate; sets to bool to false if one of the vault gymnasts are not present
        for(String name : homeNames){
            if(!Arrays.stream(cbhomeNames).toList().contains(name)){
                JOptionPane.showMessageDialog(null, "Please add " + name +" to the line up.");
                return false;
            }
        }
        for(String name : visitorNames){
            if(!Arrays.stream(cbvisitorNames).toList().contains(name)){
                JOptionPane.showMessageDialog(null, "Please add " + name +" to the line up.");
                return false;
            }
        }
        JOptionPane.showMessageDialog(null, "Success.");
        return true;
    }

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
}
