package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class AddGymnasts extends JDialog{

    public AddGymnasts(String teamNameDisplay, String teamName, String teamLogo){

         setContentPane(addGymnasts);
        setTitle("Add Gymnast Screen");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setModalityType(ModalityType.APPLICATION_MODAL);
        GuiCreator gC = new GuiCreator();
        gC.createAddGymnastTable(table, tableModel, tableRenderer, headerFont);
        this.teamName.setText(teamNameDisplay);        //Sets the jLabel for team name to the user input
        yearCB.addItem("- Select Year -");      //Sets value of yearCombobox
        yearCB.addItem("Freshman");
        yearCB.addItem("Sophomore");
        yearCB.addItem( "Junior");
        yearCB.addItem( "Senior");
        yearCB.addItem( "Graduate Student");
        DatabaseManager db = new DatabaseManager();              //This is the DatabaseManager instance
        Team currentTeam = new Team(teamName, teamLogo);      //This is the team instance

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
                boolean DNE = true;
                int match = 0;
                if (valid){
                    for(int i = 0; i<currentTeam.getAllGymnasts().size(); i++){
                        //Checks if the name from the team passed in is the same as the value in the table
                        if((currentTeam.getAllGymnasts().get(i).getPlayerfName() + " " +currentTeam.getAllGymnasts().get(i).getPlayerlName()).equals(
                                fname.getText() + " " + lname.getText())){
                            DNE = false;        //It exists
                            match = i;
                        }
                    }
                    if(DNE){
                        gC.addRow(fname.getText(), lname.getText(), tableModel);        //For Display
                        //Creates the Player
                        double[] scores = {VT, UB, BB, FX};
                        Player temp = new Player(fName_str, lName_str, yearCB.getSelectedItem().toString(), major_str, playerImage, scores);
                        currentTeam.addGymnasts(temp);
                    }else{
                        //Asks if they want to keep their changes
                        int option = JOptionPane.showConfirmDialog(null, "Gymnast Already Exist.\n Do you want to keep your changes?");
                        //Yes
                        if (option == 0){
                            //Delete Player
                            Player toDelete = currentTeam.getAllGymnasts().get(match);
                            currentTeam.deleteGymnasts(toDelete);

                            //Creates the Player
                            double[] scores = {VT, UB, BB, FX};
                            Player temp = new Player(fName_str, lName_str, yearCB.getSelectedItem().toString(), major_str, playerImage, scores);
                            currentTeam.addGymnasts(temp);
                        }
                    }

                    //Resets the widgets back to default
                    fname.setText(""); lname.setText(""); major.setText("");
                    vaultAvg.setText(""); barsAvg.setText(""); beamAvg.setText(""); floorAvg.setText("");
                    yearCB.setSelectedIndex(0);
                    browseFilesButton.setText("Browse Files");

                }
            }
        });

        saveTeamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //save team object and return setup mode, update combo boxes in team selection screen
                //Verifies Save
                int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to Save Team?");
                //Yes
                if (option == 0){
                    db.saveTeam(currentTeam);
                    JOptionPane.showMessageDialog(null, "Successfully Saved");
                    dispose();
                }
            }
        });
        browseFilesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerImage = uploadImg();
            }
        });

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2){
                    //Display all info back to the textFields
                    editSelectedGymnast(currentTeam, table.getSelectedRow(), table.getSelectedColumn());
                }
            }
        });
        deleteGymnastButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //int option = JOptionPane.showConfirmDialog(null, "Gymnast Already Exist.\n Do you want to keep your changes?");
                if(table.getSelectedRow() > -1){
                    deleteSelectedGymnast(currentTeam, table.getSelectedRow(), 0);
                }else{
                    JOptionPane.showMessageDialog(null, "Nothing to Delete.\n Please Double Click on a Gymnast from the table before Selecting this Button.");
                }
            }
        });

        setVisible(true);   //SA BABA LANG TO
    }

    public void editSelectedGymnast(Team team, int row, int col){
        String name = (String)table.getValueAt(row, col);       //Has the name
       for(int i = 0; i<team.getAllGymnasts().size(); i++){
            //Checks if the name from the team passed in is the same as the value in the table
            if((team.getAllGymnasts().get(i).getPlayerfName() + " " +team.getAllGymnasts().get(i).getPlayerlName()).equals(name)){
                fname.setText(team.getAllGymnasts().get(i).getPlayerfName());
                lname.setText(team.getAllGymnasts().get(i).getPlayerlName());
                major.setText(team.getAllGymnasts().get(i).getPlayerMajor());
                switch(team.getAllGymnasts().get(i).getPlayerClass()){
                    case "Freshman": yearCB.setSelectedIndex(1); break;
                    case "Sophomore": yearCB.setSelectedIndex(2); break;
                    case "Junior": yearCB.setSelectedIndex(3); break;
                    case "Senior": yearCB.setSelectedIndex(4); break;
                    case "Graduate Student": yearCB.setSelectedIndex(5); break;
                }
                vaultAvg.setText(String.valueOf(team.getAllGymnasts().get(i).getPlayerAvg()[ApparatusIndex.VT]));
                barsAvg.setText(String.valueOf(team.getAllGymnasts().get(i).getPlayerAvg()[ApparatusIndex.UB]));
                beamAvg.setText(String.valueOf(team.getAllGymnasts().get(i).getPlayerAvg()[ApparatusIndex.BB]));
                floorAvg.setText(String.valueOf(team.getAllGymnasts().get(i).getPlayerAvg()[ApparatusIndex.FX]));
            }
        }
    }
private void deleteSelectedGymnast(Team team, int row, int col){
    String name = (String)table.getValueAt(row, col);       //Has the name
    int option = JOptionPane.showConfirmDialog(null, "Gymnast Selected: "
            + table.getValueAt(table.getSelectedRow(), 0)
            + "\nAre you sure you want to delete player?");
    if(option == 0){
        for(int i = 0; i<team.getAllGymnasts().size(); i++){
            //Checks if the name from the team passed in is the same as the value in the table
            if((team.getAllGymnasts().get(i).getPlayerfName() + " " + team.getAllGymnasts().get(i).getPlayerlName()).equals(name)){
                team.deleteGymnasts(team.getAllGymnasts().get(i));
                tableModel.removeRow(row);
            }
        }
    }
    //Resets the widgets back to default
    fname.setText(""); lname.setText(""); major.setText("");
    vaultAvg.setText(""); barsAvg.setText(""); beamAvg.setText(""); floorAvg.setText("");
    yearCB.setSelectedIndex(0); browseFilesButton.setText("Browse Files");

}

private String uploadImg(){

        //WIP still trying to figure this out...
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Choose Your Picture");
    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    fileChooser.setFileFilter(new FileNameExtensionFilter("*.png", "png"));

    String picturePath = System.getProperty("user.dir") + "/pictures/";

    BufferedImage img;
    boolean valid = true;
    String fileName ="";
    int returnval = fileChooser.showOpenDialog(this);
    if (returnval == JFileChooser.APPROVE_OPTION)
    {
        File file = fileChooser.getSelectedFile();
        try{
            fileName = file.getName();
            img = ImageIO.read(file);
            ImageIO.write(img, "png", new File (picturePath + fileName));
            JOptionPane.showMessageDialog(null, fileName + " was saved as the gymnast's photo.");
            //save file name to gymnast picture string

            //change button label
            browseFilesButton.setText(fileName);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error. Selected file was not saved.");
            System.out.println("Error. Selected file was not saved.");
            valid = false;
        }
        this.pack();
    }
    if(valid){
        return fileName;
    }else return "";
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
    String playerImage;
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
    private JComboBox yearCB;
    private JButton deleteGymnastButton;
    private JPanel jpanelRed;

}
