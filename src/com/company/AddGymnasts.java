package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class AddGymnasts extends JDialog{

    public AddGymnasts(String teamName){

        setContentPane(addGymnasts);
        setTitle("Add Gymnast Screen");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setModalityType(ModalityType.APPLICATION_MODAL);
        GuiCreator gC = new GuiCreator();
        gC.createAddGymnastTable(table, tableModel, tableRenderer, headerFont);
        this.teamName.setText(teamName);        //Sets the jLabel for team name to the user input
        yearCB.addItem("- Select Year -");      //Sets value of yearCombobox
        yearCB.addItem("Freshman");
        yearCB.addItem("Sophomore");
        yearCB.addItem( "Junior");
        yearCB.addItem( "Senior");
        DatabaseManager db = new DatabaseManager();         //This is the DatabaseManager instance
        Team currentTeam = new Team(teamName, "");      //This is the team instance

        addGymnastButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //add gymnast to team if unique
                //check if gymnast was successfully added to team here, then add to table

                boolean valid = true;
                String fName_str = null, lName_str = null, major_str = null;         //Temp place for the strings
                double VT = 0, BB = 0, UB = 0, FX = 0;
                //Check if any of the textField is empty
                if(fname.getText().isEmpty() || lname.getText().isEmpty()
                        ||major.getText().isEmpty()
                        ||vaultAvg.getText().isEmpty()||barsAvg.getText().isEmpty()
                        ||beamAvg.getText().isEmpty()||floorAvg.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Please Complete All Fields.");
                    valid = false;
                }
                else if(yearCB.getSelectedIndex() == 0){
                    JOptionPane.showMessageDialog(null, "Please Select Valid Gymnast Year.");
                    valid = false;
                }
                //This point means that they have all info in all textfields
                // Check if the name and major is text.
                //If not decline, else parse to string and it gets a pass on adding it to the player object
                else{

                    if(fname.getText().replaceAll("\\\s", "").matches("[a-zA-Z_]+")
                                &&lname.getText().replaceAll("\\\s", "").matches("[a-zA-Z_]+")
                                &&major.getText().replaceAll("\\\s", "").matches("[a-zA-Z_]+")){
                            fName_str = fname.getText();
                            lName_str = lname.getText();
                            major_str = major.getText();
                        }else {
                            JOptionPane.showMessageDialog(null, "Enter valid name.");
                            valid = false;
                        }
                    if(valid){
                        //Checks if vault is a double
                        try{
                            VT = Double.parseDouble(vaultAvg.getText().trim());
                        } catch (Exception i){
                            JOptionPane.showMessageDialog(null, "Vault Average is invalid.");
                            valid = false;
                        }
                    }
                    if(valid){
                        //Checks if bar is a double
                        try{
                            UB = Double.parseDouble(barsAvg.getText().trim());
                        } catch (Exception i){
                            JOptionPane.showMessageDialog(null, "Bar Average is invalid.");
                            valid = false;
                        }
                    }
                    if(valid){
                        //Checks if beam is a double
                        try{
                            BB = Double.parseDouble(beamAvg.getText().trim());
                        } catch (Exception i){
                            JOptionPane.showMessageDialog(null, "Beam Average is invalid.");
                            valid = false;
                        }
                    }
                    if(valid){
                        //Checks if floor is a double
                        try{
                            FX = Double.parseDouble(floorAvg.getText().trim());
                        } catch (Exception i){
                            JOptionPane.showMessageDialog(null, "Floor Average is invalid.");
                            valid = false;
                        }
                    }
                }

                if (valid){
                    gC.addRow(fname.getText(), lname.getText(), tableModel);        //For Display

                    //Creates the Player
//                    Player temp = new Player(fName_str, lName_str, yearCB.getSelectedItem().toString(),major_str, VT);
//                    currentTeam.addGymnasts(temp);

                    //Resets the widgets back to default
                    fname.setText(""); lname.setText(""); major.setText("");
                    vaultAvg.setText(""); barsAvg.setText(""); beamAvg.setText(""); floorAvg.setText("");
                    yearCB.setSelectedIndex(0);

                }
                else{
                    //JOptionPane.showMessageDialog(null, "Player was not able to added"); //or whatever error message you want to say
                }
            }
        });

        saveTeamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //save team object and return setup mode, update combo boxes in team selection screen
                //Verifies Save
                db.saveTeam(currentTeam);
                JOptionPane.showMessageDialog(null, "Successfully Saved");
                dispose();
            }
        });
        browseFilesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uploadImg();
            }
        });
        setVisible(true);
    }

private void uploadImg(){

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
            JOptionPane.showMessageDialog(null, fileName + " was saved as the gymnast's photo.");
            //save file name to gymnast picture string

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error. Selected file was not saved.");
            System.out.println("Error. Selected file was not saved.");
        }
        this.pack();
    }
}

    private DefaultTableModel tableModel = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
            //all cells false
            return false;
        }
    };
    private DefaultTableCellRenderer tableRenderer = new DefaultTableCellRenderer();

    //Table Header Font
    Font headerFont = new Font ("Verdana", Font.PLAIN, 18);

    private JPanel addGymnasts;
    private JLabel teamNameLabel;
    private JTextField fname;
    private JTextField lname;
    private JButton browseFilesButton;
    private JButton addGymnastButton;
    private JTable table;
    private JTextField major;
    private JTextField vaultAvg;
    private JTextField barsAvg;
    private JTextField beamAvg;
    private JTextField floorAvg;
    private JButton saveTeamButton;
    private JScrollPane scrollPane;
    private JLabel teamName;
    private JLabel logoLabel;
    private JComboBox yearCB;

}
