package com.company;

import javax.swing.*;
import java.awt.*;

public class EditLineupScreen {

    public EditLineupScreen(){

        frame = new JFrame ("Edit Lineup Screen Prototype");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        //card layout start
        cardLayout = (CardLayout)  mainPanel.getLayout();
        changeCard("DefaultCard");

    }

    public void changeCard(String cardName){
        cardLayout.show(mainPanel, cardName);
    }


    public CardLayout cardLayout;
    private JPanel mainPanel;
    private JFrame frame;
    private JPanel defaultPanel;
}
