package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MeetFormatScreen {

    public MeetFormatScreen(String card, GuiCreator gC) {

        JFrame frame = new JFrame("Meet Format Screen");
        frame.setContentPane(meetFormatPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        cardLayout = (CardLayout) meetFormatPanel.getLayout();

        cardLayout.show(meetFormatPanel, card);
        frame.setVisible(true);


        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(meetFormatPanel,"meetFormatCard");
            }
        });
        dualButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SetupModeDual myDualMode = new SetupModeDual(gC);
                frame.dispose();
            }
        });
        triangularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SetupModeTri myDualMode = new SetupModeTri(gC);
                frame.dispose();

            }
        });
        quadrangularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SetupModeQuad myDualMode = new SetupModeQuad(gC);
                frame.dispose();
            }
        });
    }



    private CardLayout cardLayout;
    private JPanel meetFormatPanel;
    private JPanel meetSelect;
    private JButton dualButton;
    private JButton triangularButton;
    private JButton quadrangularButton;
    private JPanel startScreen;
    private JLabel startImage;
    private JButton startButton;
}
