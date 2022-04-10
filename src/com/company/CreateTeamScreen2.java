package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicBoolean;

public class CreateTeamScreen2 extends JDialog {
    public CreateTeamScreen2() {
        setContentPane(createTeamScreen);
        setTitle("Create Team");
//        setSize(450, 300);
        pack();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setModalityType(ModalityType.APPLICATION_MODAL);
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField1.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Enter Fields.");
                } else {
                    //We have to check if you can add images too
                    String userInput = textField1.getText().replaceAll("\\\s", "").toUpperCase();    //Removes whitespace of textField1
                    DatabaseManager db = new DatabaseManager();
                    AtomicBoolean status = new AtomicBoolean(true);
                    db.getAllTeams().stream().forEach(
                            team -> {
                                if (team.getTeamName().toUpperCase().equals(userInput)) {
                                    JOptionPane.showMessageDialog(null, "Team already exist");
                                    status.set(false);

                                }
                            }
                    );
                    if(status.get()){
                        JOptionPane.showMessageDialog(null, "Team does not exist");
                        dispose();
                        AddGymnasts myAddGymnasts = new AddGymnasts(userInput); //pass in team object here through add gymnast constructor maybe?
                    }
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                System.out.println("test");
            }
        });

        //had to move after action listeners for modal
        setVisible(true);

    }

//    //Use for test
//    public static void main(String[] args) {
//        CreateTeamScreen2 frame = new CreateTeamScreen2();
//
//    }



    private JTextField textField1;
    private JButton browseFilesButton;
    private JButton continueButton;
    private JLabel enterTeamLabel;
    private JLabel uploadLabel;
    private JPanel createTeamScreen;
    private JButton backButton;

}

