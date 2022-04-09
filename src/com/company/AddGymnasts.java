package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AddGymnasts extends JDialog{


//    public static void main(String[] args) {
//        JFrame frame = new JFrame("AddGymnasts");
//        frame.setContentPane(new AddGymnasts().addGymnasts);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setVisible(true);
//    }

    public AddGymnasts(){
        setContentPane(addGymnasts);
        setTitle("Add Gymnast Screen");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        setModalityType(ModalityType.APPLICATION_MODAL);

        GuiCreator gC = new GuiCreator();

        gC.createAddGymnastTable(table, tableModel, tableRenderer, headerFont);

        addGymnastButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //add gymnast to team if unique
                //check if gymnast was successfully added to team here, then add to table
                if (true){
                    gC.addRow(fname.getText(), lname.getText(), tableModel);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Player was not able to added"); //or whatever error message you want to say
                }
            }
        });

        saveTeamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //save team object and return setup mode, update combo boxes in team selection screen
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
//    private void createTable(){
//        Object[][] data = {
//                {"Justin Bieber"},
//        };
//        table.setModel(new DefaultTableModel(
//            data,
//                new String[]{"Current Gymnast"}
//        ));
//        TableColumnModel columns = table.getColumnModel();
//        columns.getColumn(0).setMinWidth(30);
//
//        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
//        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
//        columns.getColumn(0).setCellRenderer(centerRenderer);
//    }
private void uploadImg(){

        //WIP still trying to figure this out...
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Choose Your Picture");
    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

    int returnval = fileChooser.showOpenDialog(this);
    if (returnval == JFileChooser.APPROVE_OPTION)
    {
        try{
            File file = fileChooser.getSelectedFile();

        } catch (Exception e) {

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
    private JTextField year;
    private JButton browseFilesButton;
    private JButton addGymnastButton;
    private JTable table;
    private JTextField major;
    private JTextField vaultAvg;
    private JTextField barsAvg;
    private JTextField BeamAvg;
    private JTextField floorAvg;
    private JButton saveTeamButton;
    private JScrollPane scrollPane;
    private JLabel teamName;
    private JLabel logoLabel;

}
