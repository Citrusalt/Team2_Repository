package com.company;

import java.io.FileWriter;
import java.util.List;

public class TeamWriter {

    public TeamWriter(){


    }

   public void createTeamFile(String teamName, List<Player> myPlayerList){

        try
        {
            FileWriter writer = new FileWriter(teamName + ".txt", true);

            for ( int i = 0;  i < myPlayerList.size(); i++){
                writer.write(myPlayerList.get(i).getPlayerName()+ ", ");
                writer.write(myPlayerList.get(i).getPlayerClass() + ", ");
                writer.write(myPlayerList.get(i).getPlayerMajor() + ", ");
                writer.write(myPlayerList.get(i).getPlayerAvg() + "\r\n");
            }
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


   }



}
