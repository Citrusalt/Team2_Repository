package com.company;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.io.ObjectOutputStream;

/***
 * This class shall manage all the teams to a database. This class will load all teams from the database,
 * save information for each team and delete a team from the database.
 */
//
////This will help load data
//class OutputStreamHelper extends ObjectOutputStream{
//    //Default Constructor
//    OutputStreamHelper() throws IOException{
//        super();
//    }
//    OutputStreamHelper(OutputStream s) throws IOException{
//        super(s);
//    }
//    public void writeStreamHeader() throws IOException{
//        return;
//    }
//}
public class DatabaseManager {
    String teamDBPath;
    File teamDB;

    //******** ALL OUR TEAM DATA IS HERE *******
    List<Team> allTeams;
    //******************************************

    //Constructor
    public DatabaseManager() {
        allTeams = new ArrayList<>();
        //Creates the Database Directory for all Teams
        teamDBPath = System.getProperty("user.dir");
        for(int i  = 0; i < teamDBPath.length(); i++){
            if(teamDBPath.charAt(i) == '\\'){
                teamDBPath = new StringBuilder(teamDBPath).insert(i, '\\').toString();
                i++;
            }
        }//Duplicates the backslash for the new File
        teamDBPath += "\\\\TeamDatabase";
        teamDB = new File(teamDBPath);
        try{
            if(teamDB.mkdir()) {
                System.out.println("TeamDatabase Created");
            } else {
                System.out.println("TeamDatabase already exist");
                loadAllTeams();  // Loads all the teams to the allTeams list
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    //Getter for allTeams
    public List<Team> getAllTeams() {
        return allTeams;
    }

    //Method that will save EACH team into the TeamDatabase
    //If the team already exists, it updates it.
   public void saveTeam(Team t){
       try{
           String teamName = t.getTeamName().toUpperCase();
           FileOutputStream file = new FileOutputStream(teamDBPath + "\\\\" + teamName +".dat");
           ObjectOutputStream outputFile = new ObjectOutputStream(file);
           outputFile.writeObject(t);
           outputFile.close();
           System.out.println("Successfully created the .dat file for " + teamName );

       }catch(IOException e){
           System.out.println(e);
       }
    }

    //Method that will return an ArrayList of ALL teams from the database
    public void loadAllTeams(){
       String[] teamList = teamDB.list();
       for(String s: teamList){
           if(s.endsWith(".dat")){
               try{
                   FileInputStream file = new FileInputStream(teamDBPath+ "\\\\" + s);
                   ObjectInputStream inputFile = new ObjectInputStream(file);
                   boolean endOfFile = false;
                   while(!endOfFile){
                       try{
                           allTeams.add((Team)inputFile.readObject());      //Add Team data from dat
                           inputFile.readObject();      //Add Team data from dat
                       }catch(EOFException e){
                           endOfFile = true;
                       }catch (Exception f){
                           System.out.println(f);
                       }
                   }
                   inputFile.close();
               }catch(IOException e) {
                   System.out.println(e);
               }
           }
       }
    }

    //Method that deletes a team from the database
    public void deleteTeam(Team t) {
        Team temp = new Team("", "");
        String[] teamList = teamDB.list();      //This has all the team list
        for (String s : teamList) {
            if(s.equals(t.getTeamName()+".dat")){       //means that the team is in the database
                try{
                    File f = new File(teamDBPath+ "\\\\" + s);
                    f.delete();
                    System.out.println("Successfully Deleted");
                }catch(Exception e){
                    System.out.println(e);
                }
            }
        }
    }
//    //Read from the file
//    public boolean readData() {
//        String[] teamList = teamDB.list();
//        boolean status = false;
//        for (String s : teamList) {
//            if (s.endsWith(".dat")) {
//                File datafile = new File(teamDBPath + "\\\\" + s);
//                try {
//                    datafile.createNewFile();
//                } catch (Exception e) {
//                }
//                if (datafile.length() != 0) {
//                    try {
//                        FileInputStream fis = null;
//                        fis = new FileInputStream(teamDBPath + "\\\\" + s);
//                        ObjectInputStream ois = new ObjectInputStream(fis);
//                        Team temp = null;
//
//                        while (fis.available() != 0) {
//                            temp = (Team) ois.readObject();
//                            System.out.println("Team Name:" + temp.getTeamName());
//                            allTeams.add(temp);
//                        }
//                        ois.close();
//                        fis.close();
//
//                        status = true;
//                    } catch (Exception e) {
//                        System.out.println("Error Occurred" + e);
//                    }
//                }else System.out.println("Sad :(");
//            }
//        }
//        return status;
//    }
//
//    public void addTeam(Team t){
//        String teamName = t.getTeamName().toUpperCase();
//        File f = new File(teamDBPath + "\\\\" + teamName + ".dat");
//
//        if(t != null){
//            try{
//                FileOutputStream fos = null;
//
//                fos = new FileOutputStream(f);
//                if(f == null){
//                    ObjectOutputStream oos = new ObjectOutputStream(fos);
//                    oos.writeObject(t);
//                    oos.close();
//                }else{
//                    OutputStreamHelper oos = null;
//                    oos = new OutputStreamHelper(fos);
//                    oos.writeObject(t);
//                    oos.close();
//                }
//                fos.close();
//
//            }catch(Exception e){
//                System.out.println("Error Occurred" + e);
//            }
//        }
//    }

}
