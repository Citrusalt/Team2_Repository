package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class EditLineupScreen  extends JDialog{

    //Passing in home and visitor team into constructor, you might need to pass in more info

    public EditLineupScreen(Team home, Team visitor){

        setContentPane(mainPanel);
        setTitle("Edit Lineup Screen");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        setModalityType(ModalityType.APPLICATION_MODAL);

        //card layout start
        cardLayout = (CardLayout)  mainPanel.getLayout();
        changeCard("DefaultCard");

        //set logos
        homeLogo.setIcon(new ImageIcon ("src/com/company/pictures/" + home.getTeamLogo()));
        visitorLogo.setIcon(new ImageIcon ("src/com/company/pictures/" + visitor.getTeamLogo()));


        homeSubButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Home Team Substitute Player Button Press");
            }
        });
        visitorSubButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Visitor Team Substitute Player Button Press");
            }
        });
        saveChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        rotationCombo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                //feel free to change this
                updateRotation(rotationCombo);
            }
        });
        setVisible(true);
    }

    private void updateRotation(JComboBox rotationCombo){

        switch (rotationCombo.getSelectedIndex()){
            case 0: homeEventLabel.setText("VAULT");
                    visitorEventLabel.setText("BARS");
                    break;
            case 1: homeEventLabel.setText("BARS");
                    visitorEventLabel.setText("VAULT");
                    break;
            case 2: homeEventLabel.setText("BEAM");
                    visitorEventLabel.setText("FLOOR");
                    break;
            case 3: homeEventLabel.setText("FLOOR");
                    visitorEventLabel.setText("BEAM");
                    break;
        }
    }

    public void changeCard(String cardName){
        cardLayout.show(mainPanel, cardName);
    }


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
}
