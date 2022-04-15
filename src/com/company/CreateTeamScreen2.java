package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
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
                        AddGymnasts myAddGymnasts = new AddGymnasts(textField1.getText(),userInput, teamLogo); //pass in team object here through add gymnast constructor maybe?
                    }
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        browseFilesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                teamLogo = uploadImg();
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

    private String uploadImg(){

        //WIP still trying to figure this out...
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose Your Picture");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new FileNameExtensionFilter("*.png", "png"));

        BufferedImage img;

        int returnval = fileChooser.showOpenDialog(this);
        if (returnval == JFileChooser.APPROVE_OPTION)
        {
            File file = fileChooser.getSelectedFile();
            try{
                String fileName = file.getName();
                img = ImageIO.read(file);
                ImageIO.write(img, "png", new File ("src/com/company/pictures/" + fileName));
                //save file name to teamlogo string
                JOptionPane.showMessageDialog(null, fileName + " was saved as the team logo.");

                return fileName;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error. Selected file not saved");
                System.out.println("Error. Selected file was not saved.");
            }
            this.pack();
        }
        return"";
    }

    private String teamLogo;

    private JTextField textField1;
    private JButton browseFilesButton;
    private JButton continueButton;
    private JLabel enterTeamLabel;
    private JLabel uploadLabel;
    private JPanel createTeamScreen;
    private JButton backButton;

}

