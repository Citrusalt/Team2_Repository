package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateTeamScreen2 extends JFrame {
    private JPanel createTeam2Panel;
    private JTextField textField1;
    private JButton browseFilesButton;
    private JButton continueButton;
    private JLabel enterTeamLabel;
    private JLabel uploadLabel;
    private JPanel createTeamScreen;
    private JButton backButton;


    public CreateTeamScreen2() {
        setContentPane(createTeam2Panel);
        setTitle("Welcome");
        setSize(450, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField1.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Enter Fields.");
                } else {
                    //We have to check if you can add images too
                    String userInput = textField1.getText().replaceAll("\\\s", "");    //Removes whitespace of textField1
                    new DatabaseManager().getAllTeams().stream().forEach(
                            team -> {
                                if (team.getTeamName().toUpperCase().equals(userInput.toUpperCase())) {
                                    JOptionPane.showMessageDialog(null, "Team already exist");
                                } else {
                                    //Continue to add Gymnast
                                    JOptionPane.showMessageDialog(null, "Team does not exist");
                                }
                            }
                    );
                }
            }
        });
    }

    //Use for test
    public static void main(String[] args) {
        CreateTeamScreen2 frame = new CreateTeamScreen2();

    }
}
