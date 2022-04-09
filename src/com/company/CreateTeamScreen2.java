package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CreateTeamScreen2 extends JDialog {

    public CreateTeamScreen2() {
        setContentPane(createTeamScreen);
        setTitle("Welcome");
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
                    String userInput = textField1.getText().replaceAll("\\\s", "");    //Removes whitespace of textField1
                    new DatabaseManager().getAllTeams().stream().forEach(
                            team -> {
                                if (team.getTeamName().toUpperCase().equals(userInput.toUpperCase())) {
                                    JOptionPane.showMessageDialog(null, "Team already exist");
                                } else {
                                    //Continue to add Gymnast
//                                    JOptionPane.showMessageDialog(null, "Team does not exist");
                                    dispose();
                                    AddGymnasts myAddGymnasts = new AddGymnasts(); //pass in team object here through add gymnast constructor maybe?
                                }
                            }
                    );
//                    List<Team> temp = new DatabaseManager().getAllTeams();
//                    for(int i = 0; i < temp.size(); i++){
//                        if (temp.get(i).getTeamName().toUpperCase().equals(userInput.toUpperCase())){
//                            JOptionPane.showMessageDialog(null, "Team already exist");
//                        }else{
//                            //Continue to the Gymnast Panel!!!!!!!
//                            //Remove this OptionPane to a redirection the the addGymnast Panel
//                            JOptionPane.showMessageDialog(null, "Team does not exist");
//                        }
//
//                    }

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

