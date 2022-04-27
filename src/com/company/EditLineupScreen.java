package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EditLineupScreen extends JDialog {

    //Passing in home and visitor team into constructor, you might need to pass in more info
    public EditLineupScreen(Team home, Team visitor, Team visitor2, Team visitor3, int currentRotation, String meetType) {

        meet = meetType;           //We have to add this now in order for all meet type to use this        //
        //We have to add visitor 2, visitor 3 in parameter

        System.out.println("============== PRINTING ALL HOME ===============");         //
        home.printAll();
        System.out.println("============== END ALL HOME ===============");          //
        setContentPane(mainPanel);
        setTitle("Edit Lineup Screen");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        setModalityType(ModalityType.APPLICATION_MODAL);

        //This will be useful when passing back the home Team. If it's true, it will be modified, else, it will just as is
        editHome = home;            //Sets editHome to the passed in
        editVisitor = visitor;      //Sets editVisitor to the passed in
        editVisitor2 = visitor2;
        editVisitor3 = visitor3;

        //card layout start
        cardLayout = (CardLayout) mainPanel.getLayout();
        changeCard("DefaultCard");





        //Handles the image displayed on the editLineUp
        changeLogoDisplay(meetType);

        if(meetType.equals("Tri")){
            rotationCombo.addItem(5);rotationCombo.addItem(6);
        } else if (meetType.equals("Quad")) {
            changeCard("QuadCard");
            team1Event.setText("VAULT");        //THIS ONE IS SET, LOGO IS THE ONES THAT CHANGES AND VALUE IN CB
            team2Event.setText("UNEVEN BARS");
            team3Event.setText("BALANCED BEAM");
            team4Event.setText("FLOOR EXERCISE");
        }

       //At the start, set to currentRotation
        rotationNum = currentRotation;                   //Current rotation it's at in the run mode
        rotationCombo.setSelectedIndex(currentRotation-1);
        updateRotation(rotationCombo, home, visitor, visitor2, visitor3, rotationNum, meet);     //Change this
        changeLogoDisplay(meetType);

        homeSubButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Home Team Substitute Player Button Press");
                if(meetType.equals("Dual")){
                    //We populate the combobox now. If it's home,
                    //Populate currentCombo and allCombo
                    switch (rotationCombo.getSelectedIndex()) {
                        case 0: populateComboboxSub(home.getVaultGymnasts(), home); break;
                        case 1: populateComboboxSub(home.getBarGymnasts(), home); break;
                        case 2: populateComboboxSub(home.getBeamGymnasts(), home); break;
                        case 3: populateComboboxSub(home.getFloorGymnasts(), home); break;
                    }
                    changeCard("SubGymnastCard");
                } else if(meetType.equals("Tri")){
                    //We populate the combobox now for sub
                    switch (rotationCombo.getSelectedIndex()) {
                        case 0: populateComboboxSub(home.getVaultGymnasts(), home); break;
                        case 1: populateComboboxSub(visitor2.getVaultGymnasts(), home); break;
                        case 2: populateComboboxSub(visitor.getVaultGymnasts(), home); break;
                        case 3: populateComboboxSub(home.getBeamGymnasts(), home); break;
                        case 4: populateComboboxSub(visitor2.getBeamGymnasts(), home); break;
                        case 5: populateComboboxSub(visitor.getBeamGymnasts(), home); break;

                    }
                    changeCard("SubGymnastCard");
                }
            }
        });

        visitorSubButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Visitor Team Substitute Player Button Press");
                if(meet.equals("Dual")){
                    //Populate currentCombo and allCombo
                    switch (rotationCombo.getSelectedIndex()) {
                        case 0: populateComboboxSub(visitor.getBarGymnasts(), visitor); break;
                        case 1: populateComboboxSub(visitor.getVaultGymnasts(), visitor); break;
                        case 2: populateComboboxSub(visitor.getFloorGymnasts(), visitor); break;
                        case 3: populateComboboxSub(visitor.getBeamGymnasts(), visitor); break;
                    }
                    changeCard("SubGymnastCard");
                }
                else if(meet.equals("Tri")){
                    switch (rotationCombo.getSelectedIndex()) {
                        case 0: populateComboboxSub(visitor.getBarGymnasts(), visitor); break;
                        case 1: populateComboboxSub(home.getBarGymnasts(), visitor); break;
                        case 2: populateComboboxSub(visitor2.getBarGymnasts(), visitor); break;
                        case 3: populateComboboxSub(visitor2.getFloorGymnasts(), visitor); break;
                        case 4: populateComboboxSub(visitor.getFloorGymnasts(), visitor); break;
                        case 5: populateComboboxSub(home.getFloorGymnasts(), visitor); break;
                    }
                    changeCard("SubGymnastCard");
                }
            }
        });
//dito
        saveChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               if(meet.equals("Dual")){
                   boolean valid = false;
                   switch(rotationCombo.getSelectedIndex()){
                       case 0:
                           if(valid = checkSaveValid(home.getVaultGymnasts(), visitor.getBarGymnasts())){      //Checks if valid
                               organizedList(home.getVaultGymnasts(), gethomeCombo(), ApparatusIndex.VT);        //
                               organizedList(visitor.getBarGymnasts(), getvisitorCombo(), ApparatusIndex.UB);

                               home.arrangeByOrderList(home.getVaultGymnasts(), ApparatusIndex.VT);
                               visitor.arrangeByOrderList(visitor.getBarGymnasts(), ApparatusIndex.UB);
                           } break;
                       case 1:
                           if(valid = checkSaveValid(home.getBarGymnasts(), visitor.getVaultGymnasts())){
                               organizedList(home.getBarGymnasts(), gethomeCombo(), ApparatusIndex.UB);        //
                               organizedList(visitor.getVaultGymnasts(), getvisitorCombo(), ApparatusIndex.VT);

                               home.arrangeByOrderList(home.getBarGymnasts(), ApparatusIndex.UB);
                               visitor.arrangeByOrderList(visitor.getVaultGymnasts(), ApparatusIndex.VT);
                           } break;
                       case 2:
                           if(valid = checkSaveValid(home.getBeamGymnasts(), visitor.getFloorGymnasts())){
                               organizedList(home.getBeamGymnasts(), gethomeCombo(), ApparatusIndex.BB);        //
                               organizedList(visitor.getFloorGymnasts(), getvisitorCombo(), ApparatusIndex.FX);

                               home.arrangeByOrderList(home.getBeamGymnasts(), ApparatusIndex.BB);
                               visitor.arrangeByOrderList(visitor.getFloorGymnasts(), ApparatusIndex.FX);
                           } break;
                       case 3:
                           if(valid =checkSaveValid(home.getFloorGymnasts(), visitor.getBeamGymnasts())){
                               organizedList(home.getFloorGymnasts(), gethomeCombo(), ApparatusIndex.FX);        //
                               organizedList(visitor.getBeamGymnasts(), getvisitorCombo(), ApparatusIndex.BB);

                               home.arrangeByOrderList(home.getFloorGymnasts(), ApparatusIndex.FX);
                               visitor.arrangeByOrderList(visitor.getBeamGymnasts(), ApparatusIndex.BB);
                           } break;
                   }
                   if(valid){
                       editHome = home;
                       editVisitor = visitor;
                       dispose();
                   }
               }else if(meet.equals("Tri")){
                   boolean valid = false;
                   switch(rotationCombo.getSelectedIndex()){
                       case 0:
                           if(valid = checkSaveValid(home.getVaultGymnasts(), visitor.getBarGymnasts())){      //Checks if valid
                               organizedList(home.getVaultGymnasts(), gethomeCombo(), ApparatusIndex.VT);        //
                               organizedList(visitor.getBarGymnasts(), getvisitorCombo(), ApparatusIndex.UB);

                               home.arrangeByOrderList(home.getVaultGymnasts(), ApparatusIndex.VT);
                               visitor.arrangeByOrderList(visitor.getBarGymnasts(), ApparatusIndex.UB);
                           } break;
                       case 1:
                           if(valid = checkSaveValid(visitor2.getVaultGymnasts(), home.getBarGymnasts())){
                               organizedList(visitor2.getVaultGymnasts(), gethomeCombo(), ApparatusIndex.VT);        //
                               organizedList(home.getBarGymnasts(), getvisitorCombo(), ApparatusIndex.UB);

                               visitor2.arrangeByOrderList(visitor2.getVaultGymnasts(), ApparatusIndex.VT);
                               home.arrangeByOrderList(home.getBarGymnasts(), ApparatusIndex.UB);
                           } break;
                       case 2:
                           if(valid = checkSaveValid(visitor.getVaultGymnasts(), visitor2.getBarGymnasts())){
                               organizedList(visitor.getVaultGymnasts(), gethomeCombo(), ApparatusIndex.VT);        //
                               organizedList(visitor2.getBarGymnasts(), getvisitorCombo(), ApparatusIndex.UB);

                               visitor.arrangeByOrderList(visitor.getVaultGymnasts(), ApparatusIndex.VT);
                               visitor2.arrangeByOrderList(visitor2.getBarGymnasts(), ApparatusIndex.UB);
                           } break;
                       case 3:
                           if(valid =checkSaveValid(home.getBeamGymnasts(), visitor2.getFloorGymnasts())){
                               organizedList(home.getBeamGymnasts(), gethomeCombo(), ApparatusIndex.BB);        //
                               organizedList(visitor2.getFloorGymnasts(), getvisitorCombo(), ApparatusIndex.FX);

                               home.arrangeByOrderList(home.getBeamGymnasts(), ApparatusIndex.BB);
                               visitor2.arrangeByOrderList(visitor2.getFloorGymnasts(), ApparatusIndex.FX);
                           } break;
                       case 4:
                           if(valid =checkSaveValid(visitor2.getBeamGymnasts(), visitor.getFloorGymnasts())){
                               organizedList(visitor2.getBeamGymnasts(), gethomeCombo(), ApparatusIndex.BB);        //
                               organizedList(visitor.getFloorGymnasts(), getvisitorCombo(), ApparatusIndex.FX);

                               visitor2.arrangeByOrderList(visitor2.getBeamGymnasts(), ApparatusIndex.BB);
                               visitor.arrangeByOrderList(visitor.getFloorGymnasts(), ApparatusIndex.FX);
                           } break;
                       case 5:
                           if(valid =checkSaveValid(visitor.getBeamGymnasts(), home.getFloorGymnasts())){
                               organizedList(visitor.getBeamGymnasts(), gethomeCombo(), ApparatusIndex.BB);        //
                               organizedList(home.getFloorGymnasts(), getvisitorCombo(), ApparatusIndex.FX);

                               visitor.arrangeByOrderList(visitor.getBeamGymnasts(), ApparatusIndex.BB);
                               home.arrangeByOrderList(home.getFloorGymnasts(), ApparatusIndex.FX);
                           } break;
                   }
                   if(valid){
                       editHome = home;
                       editVisitor = visitor;
                       editVisitor2 = visitor2;
                       dispose();
                   }
               }
            }
        });
        rotationCombo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                //feel free to change this
                updateRotation(rotationCombo, home, visitor, visitor2, visitor3, rotationNum, meetType);
                changeLogoDisplay(meetType);
            }
        });

        saveChangesButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(meet.equals("Dual")){
                    //We gotta determine if we selected from home or visitor
                    Team temp = null;
                    List<String> homeName = new ArrayList<>();
                    home.getAllGymnasts().forEach(
                            gymnast->{
                                homeName.add(gymnast.getPlayerfName() +" "+ gymnast.getPlayerlName());
                            }
                    );

                    int option = JOptionPane.showConfirmDialog(null, currentCombo.getSelectedItem().toString()
                            +"  will be replaced by " + allCombo.getSelectedItem().toString()+ "."
                            + "\nAre you sure you want to continue?");
                    if(option == 0){
                        if(homeName.contains(currentCombo.getSelectedItem().toString())){
                            //means they are home team
                            System.out.println("This is the home team");
                            switch (rotationCombo.getSelectedIndex()) {
                                case 0: temp = updatesubstituteGymnast(ApparatusIndex.VT, home); break;
                                case 1: temp = updatesubstituteGymnast(ApparatusIndex.UB, home); break;
                                case 2: temp = updatesubstituteGymnast(ApparatusIndex.BB, home); break;
                                case 3: temp = updatesubstituteGymnast(ApparatusIndex.FX, home); break;
                            }
                            temp.updateApparatusLists();
                            editHome= temp;
                        } else{
                            //means they are visitor
                            System.out.println("This is the visitor team");
                            switch (rotationCombo.getSelectedIndex()) {
                                case 0: temp = updatesubstituteGymnast(ApparatusIndex.UB, visitor); break;
                                case 1: temp = updatesubstituteGymnast(ApparatusIndex.VT, visitor); break;
                                case 2: temp = updatesubstituteGymnast(ApparatusIndex.FX, visitor); break;
                                case 3: temp = updatesubstituteGymnast(ApparatusIndex.BB, visitor); break;
                            }
                            temp.updateApparatusLists();
                            editVisitor = temp;

                        }
                        temp.updateApparatusLists();
                        temp.printAll();
                        updateRotation(rotationCombo, editHome, editVisitor, editVisitor2, editVisitor3, rotationNum, meetType);
                        changeCard("DefaultCard");
                    }
                }else if(meet.equals("Tri")){
                    //We gotta determine if we selected from home or visitor
                    Team temp = null;
                    List<String> homeName = new ArrayList<>();
                    home.getAllGymnasts().forEach(
                            gymnast->{
                                homeName.add(gymnast.getPlayerfName() +" "+ gymnast.getPlayerlName());
                            }
                    );
                    List<String> visitorName = new ArrayList<>();
                    visitor.getAllGymnasts().forEach(
                            gymnast->{
                                visitorName.add(gymnast.getPlayerfName() +" "+ gymnast.getPlayerlName());
                            }
                    );


                    int option = JOptionPane.showConfirmDialog(null, currentCombo.getSelectedItem().toString()
                            +"  will be replaced by " + allCombo.getSelectedItem().toString()+ "."
                            + "\nAre you sure you want to continue?");
                    if(option == 0){
                        if(homeName.contains(currentCombo.getSelectedItem().toString())){
                            //means they are home team
                            System.out.println("This is the home team");
                            switch (rotationCombo.getSelectedIndex()) {
                                case 0: temp = updatesubstituteGymnast(ApparatusIndex.VT, home); break;
                                case 1: temp = updatesubstituteGymnast(ApparatusIndex.UB, home); break;
                                //case 2: temp = updatesubstituteGymnast(ApparatusIndex.VT, visitor); break;
                                case 3: temp = updatesubstituteGymnast(ApparatusIndex.BB, home); break;
                                //case 4: temp = updatesubstituteGymnast(ApparatusIndex.BB, visitor2); break;
                                case 5: temp = updatesubstituteGymnast(ApparatusIndex.FX, home); break;
                            }
                            temp.updateApparatusLists();
                            editHome= temp;
                        } else if(visitorName.contains(currentCombo.getSelectedItem().toString())){
                            //means they are visitor
                            System.out.println("This is the visitor team");
                            switch (rotationCombo.getSelectedIndex()) {
                                case 0: temp = updatesubstituteGymnast(ApparatusIndex.UB, visitor); break;
                                //case 1: temp = updatesubstituteGymnast(ApparatusIndex.UB, visitor); break;
                                case 2: temp = updatesubstituteGymnast(ApparatusIndex.VT, visitor); break;
                                //case 3: temp = updatesubstituteGymnast(ApparatusIndex.BB, visitor); break;
                                case 4: temp = updatesubstituteGymnast(ApparatusIndex.FX, visitor); break;
                                case 5: temp = updatesubstituteGymnast(ApparatusIndex.BB, visitor); break;
                            }
                            temp.updateApparatusLists();
                            editVisitor = temp;
                        }else{
                            //means they are visitor2
                            System.out.println("This is the visitor2 team");
                            switch (rotationCombo.getSelectedIndex()) {
                                //case 0: temp = updatesubstituteGymnast(ApparatusIndex.UB, visitor2); break;
                                case 1: temp = updatesubstituteGymnast(ApparatusIndex.VT, visitor2); break;
                                case 2: temp = updatesubstituteGymnast(ApparatusIndex.UB, visitor2); break;
                                case 3: temp = updatesubstituteGymnast(ApparatusIndex.FX, visitor2); break;
                                case 4: temp = updatesubstituteGymnast(ApparatusIndex.BB, visitor2); break;
                                //case 5: temp = updatesubstituteGymnast(ApparatusIndex.BB, visitor2); break;
                            }
                            temp.updateApparatusLists();
                            editVisitor2 = temp;
                        }
                        temp.updateApparatusLists();
                        System.out.println("=======================THIS IS THE NEW TEAM AFTER SUB==========================");
                        temp.printAll();
                        updateRotation(rotationCombo, editHome, editVisitor, editVisitor2, editVisitor3, rotationNum, meetType);
                        changeCard("DefaultCard");
                    }
                }else if (meet.equals("Quad")){
                    //dito
                    //We gotta determine if we selected from home or visitor
                    Team temp = null;
                    List<String> homeName = new ArrayList<>();
                    home.getAllGymnasts().forEach(
                            gymnast->{
                                homeName.add(gymnast.getPlayerfName() +" "+ gymnast.getPlayerlName());
                            }
                    );
                    List<String> visitorName = new ArrayList<>();
                    visitor.getAllGymnasts().forEach(
                            gymnast->{
                                visitorName.add(gymnast.getPlayerfName() +" "+ gymnast.getPlayerlName());
                            }
                    );
                    List<String> visitor2Name = new ArrayList<>();
                    visitor2.getAllGymnasts().forEach(
                            gymnast->{
                                visitor2Name.add(gymnast.getPlayerfName() +" "+ gymnast.getPlayerlName());
                            }
                    );
                    int option = JOptionPane.showConfirmDialog(null, currentCombo.getSelectedItem().toString()
                            +"  will be replaced by " + allCombo.getSelectedItem().toString()+ "."
                            + "\nAre you sure you want to continue?");
                    if(option == 0){
                        if(homeName.contains(currentCombo.getSelectedItem().toString())){
                            //means they are home team
                            System.out.println("This is the home team");
                            switch (quadRotationCombo.getSelectedIndex()) {
                                case 0: temp = updatesubstituteGymnast(ApparatusIndex.VT, home); break;
                                case 1: temp = updatesubstituteGymnast(ApparatusIndex.UB, home); break;
                                case 2: temp = updatesubstituteGymnast(ApparatusIndex.BB, home); break;
                                case 3: temp = updatesubstituteGymnast(ApparatusIndex.FX, home); break;

                            }
                            temp.updateApparatusLists();
                            editHome= temp;
                        } else if(visitorName.contains(currentCombo.getSelectedItem().toString())){
                            //means they are visitor
                            System.out.println("This is the visitor team");
                            switch (quadRotationCombo.getSelectedIndex()) {
                                case 0: temp = updatesubstituteGymnast(ApparatusIndex.FX, visitor); break;
                                case 1: temp = updatesubstituteGymnast(ApparatusIndex.VT, visitor); break;
                                case 2: temp = updatesubstituteGymnast(ApparatusIndex.UB, visitor); break;
                                case 3: temp = updatesubstituteGymnast(ApparatusIndex.BB, visitor); break;
                            }
                            temp.updateApparatusLists();
                            editVisitor = temp;
                        }else if(visitor2Name.contains(currentCombo.getSelectedItem().toString())) {
                            //means they are visitor2
                            System.out.println("This is the visitor2 team");
                            switch (quadRotationCombo.getSelectedIndex()) {
                                case 0: temp = updatesubstituteGymnast(ApparatusIndex.BB, visitor2); break;
                                case 1: temp = updatesubstituteGymnast(ApparatusIndex.FX, visitor2); break;
                                case 2: temp = updatesubstituteGymnast(ApparatusIndex.VT, visitor2); break;
                                case 3: temp = updatesubstituteGymnast(ApparatusIndex.UB, visitor2); break;
                            }
                            temp.updateApparatusLists();
                            editVisitor2 = temp;
                        }else{
                            //means they are visitor3
                            System.out.println("This is the visitor3 team");
                            switch (quadRotationCombo.getSelectedIndex()) {
                                case 0: temp = updatesubstituteGymnast(ApparatusIndex.UB, visitor3); break;
                                case 1: temp = updatesubstituteGymnast(ApparatusIndex.BB, visitor3); break;
                                case 2: temp = updatesubstituteGymnast(ApparatusIndex.FX, visitor3); break;
                                case 3: temp = updatesubstituteGymnast(ApparatusIndex.VT, visitor3); break;
                            }
                            temp.updateApparatusLists();
                            editVisitor3 = temp;
                        }
                        temp.updateApparatusLists();
                        System.out.println("=======================THIS IS THE NEW TEAM AFTER SUB==========================");
                        temp.printAll();
                        updateRotation(rotationCombo, editHome, editVisitor, editVisitor2, editVisitor3, rotationNum, meetType);
                        changeLogoDisplay(meetType);
                        changeCard("QuadCard");
                    }
                }
            }
        });
        subbackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(meetType.equals("Quad")){
                    changeCard("QuadCard");
                }else changeCard("DefaultCard");


            }
        });
        sub1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Populate cb for home
                System.out.println("Home Team Substitute Player Button Press");
                switch (quadRotationCombo.getSelectedIndex()) {
                    case 0: populateComboboxSub(home.getVaultGymnasts(), home); break;
                    case 1: populateComboboxSub(visitor3.getVaultGymnasts(), visitor3); break;
                    case 2: populateComboboxSub(visitor2.getVaultGymnasts(), visitor2); break;
                    case 3: populateComboboxSub(visitor.getVaultGymnasts(), visitor); break;
                }
                changeCard("SubGymnastCard");
            }
        });
        sub2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Populate cb for visitor
                System.out.println("Visitor1 Substitute Player Button Press");
                switch (quadRotationCombo.getSelectedIndex()) {
                    case 0: populateComboboxSub(visitor3.getBarGymnasts(), visitor3); break;
                    case 1: populateComboboxSub(home.getBarGymnasts(), home); break;
                    case 2: populateComboboxSub(visitor.getBarGymnasts(), visitor); break;
                    case 3: populateComboboxSub(visitor2.getBarGymnasts(), visitor2); break;
                }
                changeCard("SubGymnastCard");
            }
        });
        sub3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Populate cb for visitor2
                System.out.println("Visitor2 Substitute Player Button Press");
                switch (quadRotationCombo.getSelectedIndex()) {
                    case 0: populateComboboxSub(visitor2.getBeamGymnasts(), visitor2); break;
                    case 1: populateComboboxSub(visitor3.getBeamGymnasts(), visitor3); break;
                    case 2: populateComboboxSub(home.getBeamGymnasts(), home); break;
                    case 3: populateComboboxSub(visitor.getBeamGymnasts(), visitor); break;
                }
                changeCard("SubGymnastCard");
            }
        });
        sub4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Populate cb for visitor3
                System.out.println("Visitor3 Substitute Player Button Press");
                switch (quadRotationCombo.getSelectedIndex()) {
                    case 0: populateComboboxSub(visitor.getFloorGymnasts(), visitor); break;
                    case 1: populateComboboxSub(visitor2.getFloorGymnasts(), visitor2); break;
                    case 2: populateComboboxSub(visitor3.getFloorGymnasts(), visitor3); break;
                    case 3: populateComboboxSub(home.getBeamGymnasts(), home); break;
                }
                changeCard("SubGymnastCard");
            }
        });
        //dito
        quadSaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean valid = false;
                switch(quadRotationCombo.getSelectedIndex()){
                    case 0:
                        if(valid = checkSaveValidQuad(home.getVaultGymnasts(), visitor.getFloorGymnasts(), visitor2.getBeamGymnasts(), visitor3.getBarGymnasts())){      //Checks if valid
                            organizedList(home.getVaultGymnasts(), getTeam1Combo(), ApparatusIndex.VT);        //
                            organizedList(visitor3.getBarGymnasts(), getTeam2Combo(), ApparatusIndex.UB);
                            organizedList(visitor2.getBeamGymnasts(), getTeam3Combo(), ApparatusIndex.BB);
                            organizedList(visitor.getFloorGymnasts(), getTeam4Combo(), ApparatusIndex.FX);

                            home.arrangeByOrderList(home.getVaultGymnasts(), ApparatusIndex.VT);
                            visitor3.arrangeByOrderList(visitor3.getBarGymnasts(), ApparatusIndex.UB);
                            visitor2.arrangeByOrderList(visitor2.getBeamGymnasts(), ApparatusIndex.BB);
                            visitor.arrangeByOrderList(visitor.getFloorGymnasts(), ApparatusIndex.FX);
                        } break;
                    case 1:
//                        valid = checkSaveValid(visitor.getVaultGymnasts(), home.getBarGymnasts());
//                        valid2 = checkSaveValid(visitor3.getBeamGymnasts(), visitor2.getFloorGymnasts());
                        if(valid = checkSaveValidQuad(home.getBarGymnasts(), visitor.getVaultGymnasts(), visitor2.getFloorGymnasts(), visitor3.getBeamGymnasts() )){
//                            organizedList(home.getBarGymnasts(), getTeam1Combo(), ApparatusIndex.VT);        //
//                            organizedList(visitor.getVaultGymnasts(), getTeam2Combo(), ApparatusIndex.UB);
//                            organizedList(visitor2.getFloorGymnasts(), getTeam3Combo(), ApparatusIndex.BB);
//                            organizedList(visitor3.getBeamGymnasts(), getTeam4Combo(), ApparatusIndex.FX);
//
//                            home.arrangeByOrderList(home.getBarGymnasts(), ApparatusIndex.VT);
//                            visitor.arrangeByOrderList(visitor.getVaultGymnasts(), ApparatusIndex.UB);
//                            visitor2.arrangeByOrderList(visitor2.getFloorGymnasts(), ApparatusIndex.BB);
//                            visitor3.arrangeByOrderList(visitor3.getBeamGymnasts(), ApparatusIndex.FX);
                            organizedList(visitor.getVaultGymnasts(), getTeam1Combo(), ApparatusIndex.VT);        //
                            organizedList(home.getBarGymnasts(), getTeam2Combo(), ApparatusIndex.UB);
                            organizedList(visitor3.getBeamGymnasts(), getTeam3Combo(), ApparatusIndex.BB);
                            organizedList(visitor2.getFloorGymnasts(), getTeam4Combo(), ApparatusIndex.FX);

                            visitor.arrangeByOrderList(visitor.getVaultGymnasts(), ApparatusIndex.VT);
                            home.arrangeByOrderList(home.getBarGymnasts(), ApparatusIndex.UB);
                            visitor3.arrangeByOrderList(visitor3.getBeamGymnasts(), ApparatusIndex.BB);
                            visitor2.arrangeByOrderList(visitor2.getFloorGymnasts(), ApparatusIndex.FX);
                        } break;
                    case 2:
//                        valid = checkSaveValid(visitor2.getVaultGymnasts(), visitor.getBarGymnasts());
//                        valid2 = checkSaveValid(home.getBeamGymnasts(), visitor3.getFloorGymnasts());
                        if(valid=checkSaveValidQuad(home.getBeamGymnasts(), visitor.getBarGymnasts(), visitor2.getVaultGymnasts(), visitor3.getFloorGymnasts())){
//                            organizedList(home.getBeamGymnasts(), getTeam1Combo(), ApparatusIndex.VT);        //
//                            organizedList(visitor.getBarGymnasts(), getTeam2Combo(), ApparatusIndex.UB);
//                            organizedList(visitor2.getVaultGymnasts(), getTeam3Combo(), ApparatusIndex.BB);
//                            organizedList(visitor3.getFloorGymnasts(), getTeam4Combo(), ApparatusIndex.FX);
//
//                            home.arrangeByOrderList(home.getBeamGymnasts(), ApparatusIndex.VT);
//                            visitor.arrangeByOrderList(visitor.getBarGymnasts(), ApparatusIndex.UB);
//                            visitor2.arrangeByOrderList(visitor2.getVaultGymnasts(), ApparatusIndex.BB);
//                            visitor3.arrangeByOrderList(visitor3.getFloorGymnasts(), ApparatusIndex.FX);
                            organizedList(visitor2.getVaultGymnasts(), getTeam1Combo(), ApparatusIndex.VT);        //
                            organizedList(visitor.getBarGymnasts(), getTeam2Combo(), ApparatusIndex.UB);
                            organizedList(home.getBeamGymnasts(), getTeam3Combo(), ApparatusIndex.BB);
                            organizedList(visitor3.getFloorGymnasts(), getTeam4Combo(), ApparatusIndex.FX);

                            visitor2.arrangeByOrderList(visitor2.getVaultGymnasts(), ApparatusIndex.VT);
                            visitor.arrangeByOrderList(visitor.getBarGymnasts(), ApparatusIndex.UB);
                            home.arrangeByOrderList(home.getBeamGymnasts(), ApparatusIndex.BB);
                            visitor3.arrangeByOrderList(visitor3.getFloorGymnasts(), ApparatusIndex.FX);
                        } break;
                    case 3:
//                        valid = checkSaveValid(visitor3.getVaultGymnasts(), visitor2.getBarGymnasts());
//                        valid2 = checkSaveValid(visitor.getBeamGymnasts(), home.getFloorGymnasts());
                        if(valid=checkSaveValidQuad(home.getFloorGymnasts(), visitor.getBeamGymnasts(), visitor2.getBarGymnasts(), visitor3.getVaultGymnasts())){
//                            organizedList(home.getFloorGymnasts(), getTeam1Combo(), ApparatusIndex.VT);        //
//                            organizedList(visitor.getBeamGymnasts(), getTeam2Combo(), ApparatusIndex.UB);
//                            organizedList(visitor2.getBarGymnasts(), getTeam3Combo(), ApparatusIndex.BB);
//                            organizedList(visitor3.getVaultGymnasts(), getTeam4Combo(), ApparatusIndex.FX);
//
//                            home.arrangeByOrderList(home.getFloorGymnasts(), ApparatusIndex.VT);
//                            visitor.arrangeByOrderList(visitor.getBeamGymnasts(), ApparatusIndex.UB);
//                            visitor2.arrangeByOrderList(visitor2.getBarGymnasts(), ApparatusIndex.BB);
//                            visitor3.arrangeByOrderList(visitor3.getVaultGymnasts(), ApparatusIndex.FX);
                            organizedList(visitor3.getVaultGymnasts(), getTeam1Combo(), ApparatusIndex.VT);        //
                            organizedList(visitor2.getBarGymnasts(), getTeam2Combo(), ApparatusIndex.UB);
                            organizedList(visitor.getBeamGymnasts(), getTeam3Combo(), ApparatusIndex.BB);
                            organizedList(home.getFloorGymnasts(), getTeam4Combo(), ApparatusIndex.FX);

                            visitor3.arrangeByOrderList(visitor3.getVaultGymnasts(), ApparatusIndex.VT);
                            visitor2.arrangeByOrderList(visitor2.getBarGymnasts(), ApparatusIndex.UB);
                            visitor.arrangeByOrderList(visitor.getBeamGymnasts(), ApparatusIndex.BB);
                            home.arrangeByOrderList(home.getFloorGymnasts(), ApparatusIndex.FX);
                        } break;
                }
                if(valid){
                    editHome = home;
                    editVisitor = visitor;
                    editVisitor2 = visitor2;
                    editVisitor3 = visitor3;
                    dispose();
                }
            }
        });
        quadRotationCombo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                updateRotation(rotationCombo, home, visitor, visitor2, visitor3, rotationNum, meetType);
                changeLogoDisplay(meetType);
            }
        });
        setVisible(true);   //KEEP THIS AT THE BOTTOM!!!


    }

    private void updateRotation(JComboBox rotationCombo, Team home, Team visitor, Team visitor2, Team visitor3, int rotationNum, String meet) {
        System.out.println("Rotation selected from combobox:" + quadRotationCombo.getSelectedIndex());
        if(meet.equals("Dual")){
            currentRotation = false;                            //This will help us determine how to populate comboboxes
            switch (rotationCombo.getSelectedIndex()) {
                case 0:
                    if (rotationNum-1 == 0) currentRotation = true;       //Selected index is it's the current rotation it's at in run
                    homeEventLabel.setText("VAULT");
                    visitorEventLabel.setText("BARS");
                    populateCombobox(gethomeCombo(), home.getVaultGymnasts(), currentRotation);
                    populateCombobox(getvisitorCombo(), visitor.getBarGymnasts(), currentRotation);
                    break;
                case 1:
                    if (rotationNum-1 == 1) currentRotation = true;       //Selected index is the currentRotation
                    homeEventLabel.setText("BARS");
                    visitorEventLabel.setText("VAULT");
                    populateCombobox(gethomeCombo(), home.getBarGymnasts(), currentRotation);
                    populateCombobox(getvisitorCombo(), visitor.getVaultGymnasts(),currentRotation);
                    break;
                case 2:
                    if (rotationNum-1 == 2) currentRotation = true;       //Selected index is the currentRotation
                    homeEventLabel.setText("BEAM");
                    visitorEventLabel.setText("FLOOR");
                    populateCombobox(gethomeCombo(), home.getBeamGymnasts(),currentRotation);
                    populateCombobox(getvisitorCombo(), visitor.getFloorGymnasts(), currentRotation);
                    break;
                case 3:
                    if (rotationNum-1 == 3) currentRotation = true;       //Selected index is the currentRotation
                    homeEventLabel.setText("FLOOR");
                    visitorEventLabel.setText("BEAM");
                    populateCombobox(gethomeCombo(), home.getFloorGymnasts(), currentRotation);
                    populateCombobox(getvisitorCombo(), visitor.getBeamGymnasts(), currentRotation);
                    break;
            }
        }else if (meet.equals("Tri")){
            currentRotation = false;                            //This will help us determine how to populate comboboxes
            switch (rotationCombo.getSelectedIndex()) {
                case 0:
                    if (rotationNum-1 == 0) currentRotation = true;       //Selected index is it's the current rotation it's at in run
                    homeEventLabel.setText("VAULT");
                    visitorEventLabel.setText("UNEVEN BARS");
                    populateCombobox(gethomeCombo(), home.getVaultGymnasts(), currentRotation);
                    populateCombobox(getvisitorCombo(), visitor.getBarGymnasts(), currentRotation);
                    break;
                case 1:
                    if (rotationNum-1 == 1) currentRotation = true;       //Selected index is the currentRotation
                    homeEventLabel.setText("VAULT");
                    visitorEventLabel.setText("UNEVEN BARS");
                    populateCombobox(gethomeCombo(), visitor2.getVaultGymnasts(), currentRotation);
                    populateCombobox(getvisitorCombo(), home.getBarGymnasts(),currentRotation);
                    break;
                case 2:
                    if (rotationNum-1 == 2) currentRotation = true;       //Selected index is the currentRotation
                    homeEventLabel.setText("VAULT");
                    visitorEventLabel.setText("BARS");
                    populateCombobox(gethomeCombo(), visitor.getVaultGymnasts(),currentRotation);
                    populateCombobox(getvisitorCombo(), visitor2.getBarGymnasts(), currentRotation);
                    break;
                case 3:
                    if (rotationNum-1 == 3) currentRotation = true;       //Selected index is the currentRotation
                    homeEventLabel.setText("BALANCED BEAM");
                    visitorEventLabel.setText("FLOOR EXERCISE");
                    populateCombobox(gethomeCombo(), home.getBeamGymnasts(), currentRotation);
                    populateCombobox(getvisitorCombo(), visitor2.getFloorGymnasts(), currentRotation);
                    break;
                case 4:
                    if (rotationNum-1 == 4) currentRotation = true;       //Selected index is the currentRotation
                    homeEventLabel.setText("BALANCED BEAM");
                    visitorEventLabel.setText("FLOOR EXERCISE");
                    populateCombobox(gethomeCombo(), visitor2.getBeamGymnasts(), currentRotation);
                    populateCombobox(getvisitorCombo(), visitor.getFloorGymnasts(), currentRotation);
                    break;
                case 5:
                    if (rotationNum-1 == 5) currentRotation = true;       //Selected index is the currentRotation
                    homeEventLabel.setText("BALANCED BEAM");
                    visitorEventLabel.setText("FLOOR EXERCISE");
                    populateCombobox(gethomeCombo(), visitor.getBeamGymnasts(), currentRotation);
                    populateCombobox(getvisitorCombo(), home.getFloorGymnasts(), currentRotation);
                    break;
            }
        } else if(meet.equals("Quad")){
            currentRotation = false;
            switch (quadRotationCombo.getSelectedIndex()) {
                case 0:
                    if (rotationNum-1 == 0) currentRotation = true;       //Selected index is it's the current rotation it's at in run
                    populateCombobox(getTeam1Combo(), home.getVaultGymnasts(), currentRotation);
                    populateCombobox(getTeam2Combo(), visitor3.getBarGymnasts(), currentRotation);
                    populateCombobox(getTeam3Combo(), visitor2.getBeamGymnasts(), currentRotation);
                    populateCombobox(getTeam4Combo(), visitor.getFloorGymnasts(), currentRotation);
                    break;
                case 1:
                    if (rotationNum-1 == 1) currentRotation = true;       //Selected index is the currentRotation
                    populateCombobox(getTeam1Combo(), visitor.getVaultGymnasts(), currentRotation);
                    populateCombobox(getTeam2Combo(), home.getBarGymnasts(), currentRotation);
                    populateCombobox(getTeam3Combo(), visitor3.getBeamGymnasts(), currentRotation);
                    populateCombobox(getTeam4Combo(), visitor2.getFloorGymnasts(), currentRotation);
                    break;
                case 2:
                    if (rotationNum-1 == 2) currentRotation = true;       //Selected index is the currentRotation
                    populateCombobox(getTeam1Combo(), visitor2.getVaultGymnasts(), currentRotation);
                    populateCombobox(getTeam2Combo(), visitor.getBarGymnasts(), currentRotation);
                    populateCombobox(getTeam3Combo(), home.getBeamGymnasts(), currentRotation);
                    populateCombobox(getTeam4Combo(), visitor3.getFloorGymnasts(), currentRotation);
                    break;
                case 3:
                    if (rotationNum-1 == 3) currentRotation = true;       //Selected index is the currentRotation
                    populateCombobox(getTeam1Combo(), visitor3.getVaultGymnasts(), currentRotation);
                    populateCombobox(getTeam2Combo(), visitor2.getBarGymnasts(), currentRotation);
                    populateCombobox(getTeam3Combo(), visitor.getBeamGymnasts(), currentRotation);
                    populateCombobox(getTeam4Combo(), home.getFloorGymnasts(), currentRotation);
                    break;
            }
        }
    }

    public void changeCard(String cardName) {
        cardLayout.show(mainPanel, cardName);
    }

    public List<JComboBox> gethomeCombo() {
        return Arrays.asList(h1, h2, h3, h4, h5, h6);
    }

    public List<JComboBox> getvisitorCombo() {
        return Arrays.asList(v1, v2, v3, v4, v5, v6);
    }

    //Method that will return the modified homeTeam
    public Team getEditHome() {
        return editHome;
    }

    //Method that will return the modified visitorTeam
    public Team getEditVisitor() {
        return editVisitor;
    }
    //Method that will return the modified visitor2Team
    public Team getEditVisitor2() {
        return editVisitor2;
    }
    //Method that will return the modified visitor3Team
    public Team getEditVisitor3() {
        return editVisitor3;
    }

    //Method that will populate the combobox depending on the passed in parameters
    public void populateCombobox(List<JComboBox> CBList, List<Player> eventGymnasts, boolean current) {
        System.out.println("populateCombobox: Is this the current?" + current);
        //Populates comboboxes depending on the list
        for (int i = 0; i < CBList.size(); i++) {
            CBList.get(i).removeAllItems();
            for (int j = 0; j < eventGymnasts.size(); j++) {
                if(current){    //Checks if this is the currentRotation it's at, if so, just populate the cb with 1 gymnast only
                    if(i == j){
                        CBList.get(i).addItem(eventGymnasts.get(j).getPlayerfName() + " " + eventGymnasts.get(j).getPlayerlName());
                    }
                }else{          //Populates the cb with all gymnast in the event, meaning we can rearrange order
                    CBList.get(i).addItem(eventGymnasts.get(j).getPlayerfName() + " " + eventGymnasts.get(j).getPlayerlName());
                    if(i == j){
                        CBList.get(i).setSelectedIndex(i);
                    }
                }
            }
        }
    }

    //Method that will check if they can save. Pass in the list of players for an event.
    public boolean checkSaveValid( List<Player> homeGymnast, List<Player> visitorGymnast){
        String[] homeNames = new String[6];
        String[] visitorNames = new String[6];
        String[] cbhomeNames = new String[6];
        String[] cbvisitorNames = new String[6];
        boolean valid = true;

        //Populate homeNames, visitorNames, cbhomeNames, cbvisitorNames,
        for(int i = 0; i < 6; i++){
            cbhomeNames[i] = gethomeCombo().get(i).getSelectedItem().toString();              //These are the names from the combobox
            cbvisitorNames[i] = getvisitorCombo().get(i).getSelectedItem().toString();        //These are the names from the combobox
            homeNames[i] = homeGymnast.get(i).getPlayerfName() + " " + homeGymnast.get(i).getPlayerlName();                   //These are the names from the list
            visitorNames[i] = visitorGymnast.get(i).getPlayerfName() + " " + visitorGymnast.get(i).getPlayerlName();          //These are the names from the list

        }
        //Loop that checks if there is a duplicate; Ideally the homeName should have all participating event gymnast.
        //                      If not in the cb, means they were not added
        for(String name : homeNames){
            if(!Arrays.asList(cbhomeNames).contains(name)){
                JOptionPane.showMessageDialog(null, "Please add " + name +" to the line up.");
                return false;
            }
        }
        for(String name : visitorNames){
            if(!Arrays.asList(cbvisitorNames).contains(name)){
                JOptionPane.showMessageDialog(null, "Please add " + name +" to the line up.");
                return false;
            }
        }
        JOptionPane.showMessageDialog(null, "Success.");
        return true;
    }

    //Method that will check if they can save. Pass in the list of players for an event.
    public boolean checkSaveValidQuad( List<Player> homeGymnast, List<Player> visitorGymnast, List<Player> visitor2Gymnast, List<Player> visitor3Gymnast){
        String[] homeNames = new String[6];
        String[] visitorNames = new String[6];
        String[] visitor2Names = new String[6];
        String[] visitor3Names = new String[6];

        String[] cbhomeNames = new String[6];
        String[] cbvisitorNames = new String[6];
        String[] cbvisitor2Names = new String[6];
        String[] cbvisitor3Names = new String[6];
        boolean valid = true;

        //Populate homeNames, visitorNames ...
        for(int i = 0; i < 6; i++){
            homeNames[i] = homeGymnast.get(i).getPlayerfName() + " " + homeGymnast.get(i).getPlayerlName();                   //These are the names from the list
            visitorNames[i] = visitorGymnast.get(i).getPlayerfName() + " " + visitorGymnast.get(i).getPlayerlName();          //These are the names from the list
            visitor2Names[i] = visitor2Gymnast.get(i).getPlayerfName() + " " + visitor2Gymnast.get(i).getPlayerlName();          //These are the names from the list
            visitor3Names[i] = visitor3Gymnast.get(i).getPlayerfName() + " " + visitor3Gymnast.get(i).getPlayerlName();          //These are the names from the list

        }
        //Populate the cbhomeNames based on the rotation
        for(int i = 0 ; i < 6 ; i++){
            switch(quadRotationCombo.getSelectedIndex()){
                case 0:
                    cbhomeNames[i] = getTeam1Combo().get(i).getSelectedItem().toString();              //These are the names from the combobox
                    cbvisitorNames[i] = getTeam4Combo().get(i).getSelectedItem().toString();        //These are the names from the combobox
                    cbvisitor2Names[i] = getTeam3Combo().get(i).getSelectedItem().toString();              //These are the names from the combobox
                    cbvisitor3Names[i] = getTeam2Combo().get(i).getSelectedItem().toString();        //These are the names from the combobox
                    break;
                case 1:
                    cbhomeNames[i] = getTeam2Combo().get(i).getSelectedItem().toString();              //These are the names from the combobox
                    cbvisitorNames[i] = getTeam1Combo().get(i).getSelectedItem().toString();        //These are the names from the combobox
                    cbvisitor2Names[i] = getTeam4Combo().get(i).getSelectedItem().toString();              //These are the names from the combobox
                    cbvisitor3Names[i] = getTeam3Combo().get(i).getSelectedItem().toString();        //These are the names from the combobox
                    break;
                case 2:
                    cbhomeNames[i] = getTeam3Combo().get(i).getSelectedItem().toString();              //These are the names from the combobox
                    cbvisitorNames[i] = getTeam2Combo().get(i).getSelectedItem().toString();        //These are the names from the combobox
                    cbvisitor2Names[i] = getTeam1Combo().get(i).getSelectedItem().toString();              //These are the names from the combobox
                    cbvisitor3Names[i] = getTeam4Combo().get(i).getSelectedItem().toString();        //These are the names from the combobox
                    break;
                case 3:
                    cbhomeNames[i] = getTeam4Combo().get(i).getSelectedItem().toString();              //These are the names from the combobox
                    cbvisitorNames[i] = getTeam3Combo().get(i).getSelectedItem().toString();        //These are the names from the combobox
                    cbvisitor2Names[i] = getTeam2Combo().get(i).getSelectedItem().toString();              //These are the names from the combobox
                    cbvisitor3Names[i] = getTeam1Combo().get(i).getSelectedItem().toString();        //These are the names from the combobox
                    break;
            }
        }
        //Loop that checks if there is a duplicate; Ideally the homeName should have all participating event gymnast.
        //                      If not in the cb, means they were not added
        for(String name : homeNames){
            if(!Arrays.asList(cbhomeNames).contains(name)){
                JOptionPane.showMessageDialog(null, "Please add " + name +" to the line up.");
                return false;
            }
        }
        for(String name : visitorNames){
            if(!Arrays.asList(cbvisitorNames).contains(name)){
                JOptionPane.showMessageDialog(null, "Please add " + name +" to the line up.");
                return false;
            }
        }
        for(String name : visitor2Names){
            if(!Arrays.asList(cbvisitor2Names).contains(name)){
                JOptionPane.showMessageDialog(null, "Please add " + name +" to the line up.");
                return false;
            }
        }
        for(String name : visitor3Names){
            if(!Arrays.asList(cbvisitor3Names).contains(name)){
                JOptionPane.showMessageDialog(null, "Please add " + name +" to the line up.");
                return false;
            }
        }
        JOptionPane.showMessageDialog(null, "Success.");
        return true;
    }

    //Method that returns the modified list of gymnast with their appropriate order number
    public void organizedList(List<Player> eventGymnast, List<JComboBox> CBList, int appratusIndex){
        //Match the name in the list passed in
        ArrayList<String> cbNames = new ArrayList<>();
        for(int i = 0; i < 6; i++){
            cbNames.add(CBList.get(i).getSelectedItem().toString());              //These are the names from the combobox
        }
        //At this point, the array of string has the right arrangement 0-1st, 1-2nd, etc
        for(int i = 0; i<6;i++) {
            if ((eventGymnast.get(i).getPlayerfName() + " " + eventGymnast.get(i).getPlayerlName()).equals(cbNames.get(i))){
            } else {
                //Means that the name in the CB is NOT aligned with the eventGymnast
                //Find the player with name from the CB to the eventGymnast
                int finalI = i;
                eventGymnast.stream().forEach(
                        gymnast -> {
                            if ((gymnast.getPlayerfName() + " " + gymnast.getPlayerlName()).equals(cbNames.get(finalI))) {
                                gymnast.setApparatusOrderByIndex(appratusIndex, finalI + 1);
                            }
                        }
                );
            }
        }
    }

    //Method that will populate comboboxes in the substitute mode
    public void populateComboboxSub(List<Player> eventGymnast, Team team){
        currentCombo.removeAllItems();      //Reset combobox
        allCombo.removeAllItems();      //Reset combobox
        //Fills up the combobox for the current gymnast in the event
        for(int i = 0; i < team.getAllGymnasts().size() ;i++){
            //Checks if the event gymnast list contains an element from allGymnast
            //AKA if the current gymnast in allGymnast is a player in current event
            if(eventGymnast.contains(team.getAllGymnasts().get(i))){
                currentCombo.addItem(team.getAllGymnasts().get(i).getPlayerfName() + " " + team.getAllGymnasts().get(i).getPlayerlName());
            }else {
                allCombo.addItem(team.getAllGymnasts().get(i).getPlayerfName() + " " + team.getAllGymnasts().get(i).getPlayerlName());
            }
        }
    }

    //Method that will change the passed in list of gymnast for an event (VT, UB, BB, FX), and update their team class
    public Team updatesubstituteGymnast(int apparatusIndex, Team team){
        List<Player> eventTemp = new ArrayList<>();
        Player playerTemp = null;
        int order = 0;

        switch(apparatusIndex){
            case (ApparatusIndex.VT): eventTemp = team.getVaultGymnasts(); break;
            case (ApparatusIndex.UB): eventTemp = team.getBarGymnasts(); break;
            case (ApparatusIndex.BB): eventTemp = team.getBeamGymnasts(); break;
            case (ApparatusIndex.FX): eventTemp = team.getFloorGymnasts(); break;
        }

        for(int i = 0; i < eventTemp.size();i++){
            if((eventTemp.get(i).getPlayerfName() + " " +eventTemp.get(i).getPlayerlName()).equals(currentCombo.getSelectedItem().toString())){
                playerTemp = eventTemp.get(i);
                order = eventTemp.get(i).getApparatusOrder()[apparatusIndex];
                eventTemp.get(i).setApparatusOrderByIndex(apparatusIndex, 0);
                eventTemp.get(i).setApparatusStatusByIndex(apparatusIndex, false);
            }
        }
        eventTemp.remove(playerTemp);
        for(int j = 0; j < team.getAllGymnasts().size() ; j++){
            if((team.getAllGymnasts().get(j).getPlayerfName() + " " +team.getAllGymnasts().get(j).getPlayerlName()).equals(allCombo.getSelectedItem().toString())){
                team.getAllGymnasts().get(j).setApparatusStatusByIndex(apparatusIndex, true);
                team.getAllGymnasts().get(j).setApparatusOrderByIndex(apparatusIndex, order);
                eventTemp.add(team.getAllGymnasts().get(j));
            }
        }

        switch(apparatusIndex){
            case (ApparatusIndex.VT): team.setVaultGymnasts(eventTemp); break;
            case (ApparatusIndex.UB): team.setBarGymnasts(eventTemp); break;
            case (ApparatusIndex.BB): team.setBeamGymnasts(eventTemp); break;
            case (ApparatusIndex.FX): team.setFloorGymnasts(eventTemp); break;
        }
        //team.printAll();            //
        return team;
    }


    //Function that will change the displayed team logo (who is on the left v right) depending on the rotation
    public void changeLogoDisplay(String meetType){
        GuiCreator gC = new GuiCreator();
        ImageIcon icon = new ImageIcon();
        // ImageIcon imageIcon = new ImageIcon(picturePath + homeCopy.getTeamLogo());
//            myDualTriArenaScreen.logo1.setIcon(gC.scaleImageIcon(imageIcon, 90, 90));
//            imageIcon = new ImageIcon(picturePath + visitor1Copy.getTeamLogo());
//            myDualTriArenaScreen.logo2.setIcon(gC.scaleImageIcon(imageIcon, 90, 90));

        //            homeLogo.setIcon(new ImageIcon("src/com/company/pictures/" + home.getTeamLogo()));

        if(meetType.equals("Dual")){
            icon = new ImageIcon(picturePath + editHome.getTeamLogo());
            homeLogo.setIcon(gC.scaleImageIcon(icon, 40,40));
            icon = new ImageIcon(picturePath + editVisitor.getTeamLogo());
            visitorLogo.setIcon(gC.scaleImageIcon(icon, 40,40));
        }else if (meetType.equals("Tri")){
            switch(rotationCombo.getSelectedIndex()){
                case 0:                 //Rotation 1; HOME, VISITOR
                    icon = new ImageIcon(picturePath + editHome.getTeamLogo());
                    homeLogo.setIcon(gC.scaleImageIcon(icon, 40,40));
                    icon = new ImageIcon(picturePath + editVisitor.getTeamLogo());
                    visitorLogo.setIcon(gC.scaleImageIcon(icon, 40,40));
                    break;
                case 1:                 //Rotation 2; VISITOR2, HOME
                    icon = new ImageIcon(picturePath + editVisitor2.getTeamLogo());
                    homeLogo.setIcon(gC.scaleImageIcon(icon, 40,40));
                    icon = new ImageIcon(picturePath + editHome.getTeamLogo());
                    visitorLogo.setIcon(gC.scaleImageIcon(icon, 40,40));
                    break;
                case 2:                 //Rotation 3; VISITOR, VISITOR2
                    icon = new ImageIcon(picturePath + editVisitor.getTeamLogo());
                    homeLogo.setIcon(gC.scaleImageIcon(icon, 40,40));
                    icon = new ImageIcon(picturePath + editVisitor2.getTeamLogo());
                    visitorLogo.setIcon(gC.scaleImageIcon(icon, 40,40));
                    break;
                case 3:                 //Rotation 4; HOME, VISITOR2
                    icon = new ImageIcon(picturePath + editHome.getTeamLogo());
                    homeLogo.setIcon(gC.scaleImageIcon(icon, 40,40));
                    icon = new ImageIcon(picturePath + editVisitor2.getTeamLogo());
                    visitorLogo.setIcon(gC.scaleImageIcon(icon, 40,40));
                    break;
                case 4:                 //Rotation 5;VISITOR2, VISITOR
                    icon = new ImageIcon(picturePath + editVisitor2.getTeamLogo());
                    homeLogo.setIcon(gC.scaleImageIcon(icon, 40,40));
                    icon = new ImageIcon(picturePath + editVisitor.getTeamLogo());
                    visitorLogo.setIcon(gC.scaleImageIcon(icon, 40,40));
                    break;
                case 5:                 //Rotation 6; VISITOR, HOME
                    icon = new ImageIcon(picturePath + editVisitor.getTeamLogo());
                    homeLogo.setIcon(gC.scaleImageIcon(icon, 40,40));
                    icon = new ImageIcon(picturePath + editHome.getTeamLogo());
                    visitorLogo.setIcon(gC.scaleImageIcon(icon, 40,40));
                    break;
                }

            }
        else if(meetType.equals("Quad")){
           switch (quadRotationCombo.getSelectedIndex()){
               case 0:      //Rotation 1; HOME, VISITOR3, VISITOR2, VISITOR1
                   icon = new ImageIcon(picturePath + editHome.getTeamLogo());
                   team1Logo.setIcon(gC.scaleImageIcon(icon, 40,40));
                   icon = new ImageIcon(picturePath + editVisitor3.getTeamLogo());
                   team2Logo.setIcon(gC.scaleImageIcon(icon, 40,40));
                   icon = new ImageIcon(picturePath + editVisitor2.getTeamLogo());
                   team3Logo.setIcon(gC.scaleImageIcon(icon, 40,40));
                   icon = new ImageIcon(picturePath + editVisitor.getTeamLogo());
                   team4Logo.setIcon(gC.scaleImageIcon(icon, 40,40));
                   break;
               case 1:      //Rotation 2; VISITOR1, HOME, VISITOR3, VISITOR2
                   icon = new ImageIcon(picturePath + editVisitor.getTeamLogo());
                   team1Logo.setIcon(gC.scaleImageIcon(icon, 40,40));
                   icon = new ImageIcon(picturePath + editHome.getTeamLogo());
                   team2Logo.setIcon(gC.scaleImageIcon(icon, 40,40));
                   icon = new ImageIcon(picturePath + editVisitor3.getTeamLogo());
                   team3Logo.setIcon(gC.scaleImageIcon(icon, 40,40));
                   icon = new ImageIcon(picturePath + editVisitor2.getTeamLogo());
                   team4Logo.setIcon(gC.scaleImageIcon(icon, 40,40));
                   break;
               case 2:      //Rotation 3; VISITOR2, VISITOR1, HOME, VISITOR3
                   icon = new ImageIcon(picturePath + editVisitor2.getTeamLogo());
                   team1Logo.setIcon(gC.scaleImageIcon(icon, 40,40));
                   icon = new ImageIcon(picturePath + editVisitor.getTeamLogo());
                   team2Logo.setIcon(gC.scaleImageIcon(icon, 40,40));
                   icon = new ImageIcon(picturePath + editHome.getTeamLogo());
                   team3Logo.setIcon(gC.scaleImageIcon(icon, 40,40));
                   icon = new ImageIcon(picturePath + editVisitor3.getTeamLogo());
                   team4Logo.setIcon(gC.scaleImageIcon(icon, 40,40));
                   break;
               case 3:      //Rotation 4; VISITOR3, VISITOR2, VISITOR1, HOME
                   icon = new ImageIcon(picturePath + editVisitor3.getTeamLogo());
                   team1Logo.setIcon(gC.scaleImageIcon(icon, 40,40));
                   icon = new ImageIcon(picturePath + editVisitor2.getTeamLogo());
                   team2Logo.setIcon(gC.scaleImageIcon(icon, 40,40));
                   icon = new ImageIcon(picturePath + editVisitor.getTeamLogo());
                   team3Logo.setIcon(gC.scaleImageIcon(icon, 40,40));
                   icon = new ImageIcon(picturePath + editHome.getTeamLogo());
                   team4Logo.setIcon(gC.scaleImageIcon(icon, 40,40));
                   break;

           }
        }

    }

    //Method that will return the comboboxes for team1
    public List<JComboBox> getTeam1Combo(){ return Arrays.asList(team11, team12, team13, team14, team15, team16);}
    //Method that will return the comboboxes for team2
    public List<JComboBox> getTeam2Combo(){ return Arrays.asList(team21, team22, team23, team24, team25, team26);}
    //Method that will return the comboboxes for team3
    public List<JComboBox> getTeam3Combo(){ return Arrays.asList(team31, team32, team33, team34, team35, team36);}
    //Method that will return the comboboxes for team4
    public List<JComboBox> getTeam4Combo(){ return Arrays.asList(team41, team42, team43, team44, team45, team46);}

    String picturePath = System.getProperty("user.dir") + "/pictures/";
    String meet;
    private Team editHome;
    private Team editVisitor;
    private Team editVisitor2;
    private Team editVisitor3;
    private int rotationNum;
    private boolean currentRotation;
    public CardLayout cardLayout;
    private JPanel mainPanel;
    private JFrame frame;
    private JPanel defaultPanel;
    private JComboBox h6;
    private JComboBox h1;
    private JComboBox h4;
    private JComboBox h5;
    private JComboBox h3;
    private JComboBox h2;
    private JComboBox v1;
    private JComboBox v2;
    private JComboBox v4;
    private JComboBox v5;
    private JComboBox v3;

    private JLabel homeEventLabel;
    private JLabel visitorEventLabel;
    private JComboBox v6;
    private JButton saveChangesButton;
    private JComboBox rotationCombo;
    private JLabel homeLogo;
    private JLabel visitorLogo;
    private JButton homeSubButton;
    private JButton visitorSubButton;
    private JPanel substituteGymnast;
    private JButton saveChangesButton1;
    private JComboBox currentCombo;
    private JComboBox allCombo;
    private JButton subbackButton;
    private JPanel quadPanel;
    private JLabel team1Event;
    private JLabel team2Event;
    private JLabel team3Event;
    private JLabel team1Logo;
    private JLabel team2Logo;
    private JLabel team3Logo;
    private JLabel team4Logo;
    private JLabel team4Event;
    private JComboBox team11;
    private JComboBox team12;
    private JComboBox team13;
    private JComboBox team14;
    private JComboBox team15;
    private JComboBox team16;
    private JComboBox team21;
    private JComboBox team22;
    private JComboBox team23;
    private JComboBox team24;
    private JComboBox team25;
    private JComboBox team26;
    private JComboBox team31;
    private JComboBox team32;
    private JComboBox team33;
    private JComboBox team34;
    private JComboBox team35;
    private JComboBox team36;
    private JComboBox team41;
    private JComboBox team42;
    private JComboBox team43;
    private JComboBox team44;
    private JComboBox team45;
    private JComboBox team46;
    private JComboBox quadRotationCombo;
    private JButton sub1;
    private JButton sub2;
    private JButton sub3;
    private JButton sub4;
    private JButton quadSaveButton;
}
