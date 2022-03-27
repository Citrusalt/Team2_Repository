package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetupModeQuad {

    public SetupModeQuad(){

        JFrame frame = new JFrame ("Post-Meet Screen Prototype");
        frame.setContentPane(setupModeQuadPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        meetTemplateBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                MeetFormatScreen mySetup = new MeetFormatScreen("meetFormatCard");
            }
        });
    }




    private JPanel meetTemplate;
    private JButton meetTemplateBackButton;
    private JLabel selectedMeetFormat;
    private JPanel setupModeQuadPanel;

}