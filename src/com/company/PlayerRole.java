package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayerRole {

    //Order of apparatus: Vaults, Bars, Beams, Floors
    private static int vault = 0;               //Constant Value
    private static int bar = 1;
    private static int beam = 2;
    private static int floors = 3;
    private boolean[] apparatusStatus;
    private int[] orderApparatus;

    //Constructor
    public PlayerRole() {
        apparatusStatus = new boolean[4];
        orderApparatus = new int[4];
    }

    //Method that sets if they play apparatus. Pass in the index.
    //We should learn that 0 - Vaults, 1 - Bars, 2 - Beam, 3 - Floors
    public void setApparatusStatus(int i){
        apparatusStatus[i] = true;
    }
    //Method that set the player's order in apparatus. Pass in the index and the order
    public void setOrderApparatus(int i, int order){
        orderApparatus[i] = order;
    }

    //Method that gets the order. Pass in the index.
    public int[] getOrderApparatus(){
        return orderApparatus;
    }

    public boolean[] getApparatusStatus() {
        return apparatusStatus;
    }
}