package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

public class GuiCreator {

    public GuiCreator(){

    }

    //temporary parameters, will take in team 1 and team 2 of type Team in the future
    public void createTeamTable(JTable table, DefaultTableModel model, DefaultTableCellRenderer cellRenderer, Font font, int numbTeams){
        model.addColumn("Home");
        for (int i = 1; i < numbTeams ; i++){
            model.addColumn("Visitor " + i);
        }
        table.setModel(model);
        centerColumns(table, cellRenderer);

        table.getTableHeader().setFont(font);
        centerColumns(table, cellRenderer);
        //individualTable.getColumnModel().getColumn(0).setPreferredWidth(1);
    }

    public void createJudgeTable(JTable table, DefaultTableModel model, DefaultTableCellRenderer cellRenderer, Font font){
        model.addColumn("Vault");
        model.addColumn("Bars");
        model.addColumn("Beam");
        model.addColumn("Floor");

        table.setModel(model);
        centerColumns(table, cellRenderer);

        table.getTableHeader().setFont(font);
        centerColumns(table, cellRenderer);

        //individualTable.getColumnModel().getColumn(0).setPreferredWidth(1);
    }

    public void addRowsJudgeTable(String vaultJudge,String barJudge, String beamJudge, String floorJudge, DefaultTableModel model){
        Vector<String> row = new Vector<String>();

        row.add(vaultJudge);
        row.add(barJudge);
        row.add(beamJudge);
        row.add(floorJudge);
        model.addRow(row);
    }

    //the parameters of this will probably change to accept either team or player class instead
    public void addRowTeamTable(String[] names, DefaultTableModel model){
        Vector<String> row = new Vector<String>();
        for (String s : names){
            row.add(s);
        }
        model.addRow(row);
    }

    public void createIndividualTable(JTable table, DefaultTableModel model, DefaultTableCellRenderer cellRenderer, Font font){
        model.addColumn("Place");
        model.addColumn("Gymnast Name");
        model.addColumn("Team");
        model.addColumn("Score");

        table.setModel(model);

        centerColumns(table, cellRenderer);
        table.getTableHeader().setFont(font);

        //individualTable.getColumnModel().getColumn(0).setPreferredWidth(1);
    }

    public void createIndividualTableDual(JTable table, DefaultTableModel model, DefaultTableCellRenderer cellRenderer, Font font){
        model.addColumn("Place");
        model.addColumn("Gymnast Name");
        model.addColumn("Score");

        table.setModel(model);

        centerColumns(table, cellRenderer);
        table.getTableHeader().setFont(font);

    }

    public void addRowIndividualTableDual(int place, String name, double score, DefaultTableModel model){
        Vector<String> row = new Vector<String>();
        row.add(String.valueOf(place));
        row.add(name);
        row.add(String.valueOf(score));
        model.addRow(row);
    }

    //the parameters of this will undoubtedly change to accept either team or player class instead
    public void addRowIndividualTable(int place, String name, String team, double score, DefaultTableModel model){
        Vector<String> row = new Vector<String>();
        row.add(String.valueOf(place));
        row.add(name);
        row.add(team);
        row.add(String.valueOf(score));
        model.addRow(row);
    }

    public void createTeamTablePost(JTable table, DefaultTableModel model, DefaultTableCellRenderer cellRenderer, Font font, String thirdColumn){
        model.addColumn("Place");
        model.addColumn("Team");
        model.addColumn(thirdColumn);
        table.setModel(model);

        centerColumns(table, cellRenderer); //center text in table
        table.getTableHeader().setFont(font); //messing with some table formatting

        //Doesn't work right now, some conflict with gui designer
        //teamTable.getColumnModel().getColumn(0).setPreferredWidth(10);
    }

    //the parameters of this will undoubtedly change to accept either team or player class instead
    public void addRowTeamTablePost(int place, String name, double score, DefaultTableModel model){
        Vector<String> row = new Vector<String>();
        row.add(String.valueOf(place));
        row.add(name);
        row.add(String.valueOf(score));
        model.addRow(row);
    }

    //used to center JTable elements
    public void centerColumns(JTable table, DefaultTableCellRenderer cellRenderer){
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int columnIndex = 0; columnIndex < table.getModel().getColumnCount(); columnIndex++){
            table.getColumnModel().getColumn(columnIndex).setCellRenderer(cellRenderer);
        }
    }

    //pass in an array of objects to see if they are all unique, returns true if yes, no if not
    public Boolean checkUnique(Object[] objects){

        int size = objects.length;

        Set<Object> set = new HashSet<Object>();

        for (Object i : objects){
            set.add(i);
        }
        boolean unique = set.size() == size;

        if (unique){
            return true;
        }
        else{
            return false;
        }
    }
    public void createAddGymnastTable(JTable table, DefaultTableModel model, DefaultTableCellRenderer cellRenderer, Font font){
        model.addColumn("Current Gymnasts");
        table.setModel(model);
        centerColumns(table, cellRenderer); //center text in table
        table.getTableHeader().setFont(font); //messing with some table formatting

        //Doesn't work right now, some conflict with gui designer
        //teamTable.getColumnModel().getColumn(0).setPreferredWidth(10);
    }
    public void addRow(String firstName, String lastName, DefaultTableModel model){
        Vector<String> row = new Vector<String>();
        row.add (firstName + " " + lastName);
        model.addRow(row);
    }


    public void updateCombobox(JComboBox comboBox, List<Player> playerList) {
//        comboBox.removeAll(); //don't use this

        comboBox.removeAllItems();

        for (Player p : playerList){
            comboBox.addItem(p.getPlayerfName() + " " + p.getPlayerlName());
        }
    }
}
