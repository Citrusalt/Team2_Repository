package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class AddGymnasts {
    private JPanel addGymnasts;
    private JLabel teamNameLabel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton browseFilesButton;
    private JButton addGymnastButton;
    private JTable table1;
    private JScrollPane currentGymnastTable;

    public static void main(String[] args) {
        JFrame frame = new JFrame("AddGymnasts");
        frame.setContentPane(new AddGymnasts().addGymnasts);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public AddGymnasts(){
        createTable();
    }
    private void createTable(){
        Object[][] data = {
                {"Justin Bieber"},
        };
        table1.setModel(new DefaultTableModel(
            data,
                new String[]{"Current Gymnast"}
        ));
        TableColumnModel columns = table1.getColumnModel();
        columns.getColumn(0).setMinWidth(30);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        columns.getColumn(0).setCellRenderer(centerRenderer);
    }

}
