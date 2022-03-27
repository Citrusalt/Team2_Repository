package com.company;

import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileWriter;   // Import the FileWriter class

public class PostMeetResultsTester {
    public static void main(String[] args) {
        File resultsFile = new File("testResults.txt");
        try{
            FileWriter resultsFileW = new FileWriter("testResults.txt");
            resultsFileW.write("FINAL SCORES AND OVERALL PLACEMENT\n----------------------------------\n\n");
            resultsFileW.write("TEAM STANDING\n#\tTEAM\tVAULT\tBARS\tBEAM\tFLOOR\tFINAL\n\n");

            /*
            * for every team print team name
            *   print team results
            */

            resultsFileW.write("TEAM RESULTS DETAILS\n\n");
            resultsFileW.write("TEAM:");
            //print #1 team name
            resultsFileW.write("#\tNAME\tVAULT\tBARS\tBEAM\tFLOOR\tALLAROUND\n");
            /*
            * for every Player in Team
            *   print score for each apparatus
            */

            resultsFileW.write("TEAM:");
            //print #2 team name
            resultsFileW.write("#\tNAME\tVAULT\tBARS\tBEAM\tFLOOR\tALLAROUND\n");
            /*
             * for every Player in Team
             *   print score for each apparatus
             */

            resultsFileW.write("TEAM:");
            //print #3 team name
            resultsFileW.write("#\tNAME\tVAULT\tBARS\tBEAM\tFLOOR\tALLAROUND\n");
            /*
             * for every Player in Team
             *   print score for each apparatus
             */

            resultsFileW.write("TEAM:");
            //print #4 team name
            resultsFileW.write("#\tNAME\tVAULT\tBARS\tBEAM\tFLOOR\tALLAROUND\n");
            /*
             * for every Player in Team
             *   print score for each apparatus
             */

            resultsFileW.write("ALL AROUND RESULTS\n\n");
            resultsFileW.write("PLACE\tNUM\tNAME\tTEAM\tVAULT\tBARS\tBEAM\tFLOOR\tSCORE\n");
            /*
            * print all around array and scores
            */

            resultsFileW.write("INDIVIDUAL VAULT\n\n");
            resultsFileW.write("NAME\tSCORE\tTEAM\tSCORE\n");
            //print vault scores for all teams in order

            resultsFileW.write("INDIVIDUAL BARS\n\n");
            resultsFileW.write("NAME\tSCORE\tTEAM\tSCORE\n");
            //print bars scores for all teams in order

            resultsFileW.write("INDIVIDUAL BEAM\n\n");
            resultsFileW.write("NAME\tSCORE\tTEAM\tSCORE\n");
            //print beam scores for all teams in order

            resultsFileW.write("INDIVIDUAL FLOOR\n\n");
            resultsFileW.write("NAME\tSCORE\tTEAM\tSCORE\n");
            //print floor scores for all teams in order

            resultsFileW.close();
        } catch (IOException e){
            System.out.println("Error with printing to results file.");
            e.printStackTrace();
        }



    }

    }