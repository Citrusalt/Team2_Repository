package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PostMeetScreen {


    private JPanel postMeetModePanel;
    private JButton endButton;

    public PostMeetScreen(){

        JFrame frame = new JFrame ("Post-Meet Screen Prototype");
        frame.setContentPane(postMeetModePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


        endButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });
    }


}
