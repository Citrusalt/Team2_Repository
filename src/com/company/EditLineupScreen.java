package com.company;

import javax.swing.*;
import java.awt.*;

public class EditLineupScreen  extends JDialog{

    public EditLineupScreen(){

        setContentPane(mainPanel);
        setTitle("Edit Lineup Screen");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        setModalityType(ModalityType.APPLICATION_MODAL);


        //card layout start
        cardLayout = (CardLayout)  mainPanel.getLayout();
        changeCard("DefaultCard");



        setVisible(true);
    }

    public void changeCard(String cardName){
        cardLayout.show(mainPanel, cardName);
    }


    public CardLayout cardLayout;
    private JPanel mainPanel;
    private JFrame frame;
    private JPanel defaultPanel;
}
