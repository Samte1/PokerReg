package Utilities;

import Classes.Event;
import Classes.AmateurPlayer;
import Classes.Player;
import Classes.ProPlayer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.apache.ibatis.jdbc.ScriptRunner;

/**
 * DatabaseMethods.java
 * 17/10/2017
 * @author Student
 */
public class DatabaseMethods 
{
    /**
     * Insert player into Database with player without player ID
     * @param player - Player
     * @param event - Event
     */
    public static void insertPlayer(Player player, Event event)
    {
        Connection con = null;
        Statement stmt = null;
        
        String playerType;
        String earnings = "null";
        ProPlayer pPlay;
        if (player instanceof AmateurPlayer)
        {
            playerType = "Amateur";
        }
        else
        {
            playerType = "Pro";
            pPlay = (ProPlayer)player;
            earnings = "'" + pPlay.getEarnings() + "'";
        }
        
        try
        {
            //connect to database
            con = ConnectionDetails.getConnection("Poker");
            stmt = con.createStatement();
            
            String sql = "INSERT INTO player (playerType, firstName, lastName, age, phone, "
                + "gender, streetNum, streetName, suburb, postcode, earnings, buyin, eventID)"
                + " VALUES ('" + playerType + "','"
                + player.getFirstName() + "','" + player.getLastName() + "','"
                + player.getAge() + "','" + player.getPhone() + "','"
                + player.getGender() + "','" + player.getStreetNum() + "','"
                + player.getStreetName() + "','" + player.getSuburb() + "','"
                + player.getPostcode() + "'," + earnings + ",'"
                + player.getBuyin() + "','"
                + event.getEventID() + "');";
            
            stmt.executeUpdate(sql);
            
            JOptionPane.showMessageDialog(null, "Player added");
            
            con.close();
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    
    /**
     * Insert Player into database with an playerID and eventID - used for 
     * reinserting records back into database
     * @param player - Player
     */
    public static void insertPlayer(Player player)
    {
        Connection con = null;
        Statement stmt = null;
        
        String playerType;
        String earnings = "null";
        ProPlayer pPlay;
        if (player instanceof AmateurPlayer)
        {
            playerType = "Amateur";
        }
        else
        {
            playerType = "Pro";
            pPlay = (ProPlayer)player;
            earnings = "'" + pPlay.getEarnings() + "'";
        }
        
        try
        {
            //connect to database
            con = ConnectionDetails.getConnection("Poker");
            stmt = con.createStatement();
            
            String sql = "INSERT INTO player (playerID, playerType, firstName, lastName, age, phone, "
                + "gender, streetNum, streetName, suburb, postcode, earnings, buyin, eventID)"
                + " VALUES ('" + player.getPlayerID() + "','"+ playerType + "','"
                + player.getFirstName() + "','" + player.getLastName() + "','"
                + player.getAge() + "','" + player.getPhone() + "','"
                + player.getGender() + "','" + player.getStreetNum() + "','"
                + player.getStreetName() + "','" + player.getSuburb() + "','"
                + player.getPostcode() + "'," + earnings + ",'"
                + player.getBuyin() + "','"
                + player.getEventID() + "');";
            
            stmt.executeUpdate(sql);
            
            con.close();
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    
    /**
     * Insert Event into database
     * @param eventName - String
     */
    public static void insertEvent(String eventName)
    {
        Connection con = null;
        Statement stmt = null;
        
        try
        {
            con = ConnectionDetails.getConnection("Poker");
            stmt = con.createStatement();
            
            String sql = "INSERT INTO event (eventName) VALUES ('" + eventName
                    + "');";
            stmt.executeUpdate(sql);
            
            JOptionPane.showMessageDialog(null, "Event added");
            
            con.close();   
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    
    /**
     * Insert Event into database using event object - used for reinserting
     * records back into database
     * @param ev - Event
     */
    public static void insertEvent(Event ev)
    {
        Connection con = null;
        Statement stmt = null;
        
        try
        {
            con = ConnectionDetails.getConnection("Poker");
            stmt = con.createStatement();
            
            String sql = "INSERT INTO event (eventID, eventName) VALUES ('" 
                    + ev.getEventID()+ "','" + ev.getEventName() + "');";
            stmt.executeUpdate(sql);
            
            con.close();   
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    
    /**
     * Delete Player from database
     * @param p - Player
     */
    public static void deletePlayer(Player p)
    {
        Connection con = null;
        
        try{
            // Create Connection
            con = ConnectionDetails.getConnection("Poker");
            Statement stmt = con.createStatement();
            // Sql Statement
            String sql = "DELETE FROM player WHERE playerID=" + p.getPlayerID();
            
            //System.out.println(sql); //for testing purposes
            stmt.executeUpdate(sql);
            
            con.close();
        } catch (SQLException ex){
            ex.printStackTrace();
        } finally {
            // place code here that will always run.
        }
    }
    
    /**
     * Retrieves All Event list from database and returns an ArrayList of Event
     * @return ArrayList
     */
    public static ArrayList<Event> getEventList()
    {
        Connection con = null;
        Statement stmt = null;
        ResultSet r = null;
        ArrayList<Event> eventList = new ArrayList();
        try
        {
            con = ConnectionDetails.getConnection("Poker");
            
            stmt = con.createStatement();
            
            String sql = "SELECT * FROM event";
            
            r = stmt.executeQuery(sql);
            
            while (r.next())
            {
                Event tempEvent = new Event(r.getInt("eventID"), r.getString("eventName"));
                eventList.add(tempEvent);
            }
            con.close();
        }
        catch (SQLException ev)
        {
            System.out.println(ev.getMessage());
        }
        return eventList;
    }
    
    /**
     * Retrieves All Player list from database and returns an ArrayList of Players
     * @return ArrayList
     */
    public static ArrayList<Player> getPlayerList()
    {
        ArrayList<Player> list = new ArrayList();
        
        Connection con = null;
        Statement stmt = null;
        ResultSet r = null;
        
        try 
        {
            con = ConnectionDetails.getConnection("Poker");
            stmt = con.createStatement();
            String sql = "SELECT * FROM player";
            r = stmt.executeQuery(sql);
            // Retrive records and place them in ArrayList
            list.clear(); //remove any data from the ArrayList
            
            //loop through the extracted records and add to ArrayList
            while (r.next()) 
            {
                if (r.getString("playerType").equals("Amateur"))
                    list.add(new AmateurPlayer(r.getInt("playerID"),
                        r.getString("firstName"), r.getString("lastName"),
                        r.getInt("age"), r.getString("Phone"),
                        r.getString("gender"), r.getInt("streetNum"),
                        r.getString("streetName"), r.getString("suburb"),
                        r.getInt("postcode"), r.getInt("buyin"),
                        r.getInt("eventID")));
                else
                    list.add(new ProPlayer(r.getInt("playerID"),
                        r.getString("firstName"), r.getString("lastName"),
                        r.getInt("age"), r.getString("Phone"),
                        r.getString("gender"), r.getInt("streetNum"),
                        r.getString("streetName"), r.getString("suburb"),
                        r.getInt("postcode"), r.getDouble("earnings"),
                        r.getInt("buyin"), r.getInt("eventID")));
            }
            con.close();
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        } 
        finally 
        {
            
        }
        return list;
    }
    
    /**
     * Retrieves Player List from the database based in input parameters, if 
     * String parameters are "" or int = 0 then those values are not included in
     * the WHERE CLAUSE.
     * @param firstName - String
     * @param lastName - String
     * @param eventID - int
     * @return ArrayList
     */
    public static ArrayList<Player> getPlayerList(String firstName, String lastName,
                                                    int eventID)
    {
        ArrayList<Player> list = new ArrayList();
        
        Connection con = null;
        Statement stmt = null;
        ResultSet r = null;
        
        try 
        {
            con = ConnectionDetails.getConnection("Poker");
            stmt = con.createStatement();
            String where = "";
            // testing values for first name, last name and eventID to determine
            // Where clause.
            if (!firstName.equals(""))
            {
                where += "WHERE firstName ='" + firstName + "'";
                // have to test if last name is null
                if (!lastName.equals(""))
                    where += " AND lastName ='" + lastName + "'";
                if (eventID != 0)
                    where += " AND eventID =" + eventID;
            }
            else if (!lastName.equals(""))
            {
                where += "WHERE lastName ='" + lastName + "'";
                if (eventID != 0)
                    where += " AND eventID =" + eventID;
            }
            else if (eventID != 0)// if first name = "" and last name = ""
            {
                where += "WHERE eventID =" + eventID;
            }
            else
                where = "";

            String sql = "SELECT * FROM player " + where;
            r = stmt.executeQuery(sql);
            // Retrive records and place them in ArrayList
            list.clear(); //remove any data from the ArrayList
            
            //loop through the extracted records and add to ArrayList
            while (r.next()) 
            {
                if (r.getString("playerType").equals("Amateur"))
                    list.add(new AmateurPlayer(r.getInt("playerID"),
                        r.getString("firstName"), r.getString("lastName"),
                        r.getInt("age"), r.getString("Phone"),
                        r.getString("gender"), r.getInt("streetNum"),
                        r.getString("streetName"), r.getString("suburb"),
                        r.getInt("postcode"), r.getInt("buyin"),
                        r.getInt("eventID")));
                else
                    list.add(new ProPlayer(r.getInt("playerID"),
                        r.getString("firstName"), r.getString("lastName"),
                        r.getInt("age"), r.getString("Phone"),
                        r.getString("gender"), r.getInt("streetNum"),
                        r.getString("streetName"), r.getString("suburb"),
                        r.getInt("postcode"), r.getDouble("earnings"),
                        r.getInt("buyin"), r.getInt("eventID")));
            }
            con.close();
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        } 
        finally 
        {
            
        }
        return list;
    }
    
    /**
     * Runs Database script 'Backup.sql', which should delete and recreate your
     * database, then inserts List of events and list of players into the database
     * @param playerList - ArrayList
     * @param eventList - ArrayList
     */
    public static void backupDatabase(ArrayList<Player> playerList, 
                                            ArrayList<Event> eventList)
    {
        //SCRIPT TO DROP CURRENT DATABASE AND THEN CREATE DATABASE
        try
        {
            runSQLScript("Backup.sql");
        }
        catch (ClassNotFoundException e)
        {
            System.err.println("Class was not found");
        }
        catch (SQLException e)
        {
            System.err.println("Problem with SQL Execution");
        }
        
        // Loop through events
        for (Event e: eventList)
        {
            insertEvent(e);
        }
        // Loop through and insert players.
        for (Player p: playerList)
        {
            insertPlayer(p);
        }
    }
    
    /**
     * Run SQL Script located at input String after connecting to the database.
     * @param SQLFilePath - String
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static void runSQLScript(String SQLFilePath) throws ClassNotFoundException,
        SQLException
    {
        // Create MySql Connection
        Connection con = ConnectionDetails.getConnection("Poker");
        com.mysql.jdbc.Statement stmt = null;

        try {
            // Initialize object for ScriptRunner
            ScriptRunner sr = new ScriptRunner(con);

            // Give the input file to Reader
            Reader reader = new BufferedReader(new FileReader(SQLFilePath));

            // Execute script
            sr.runScript(reader);
            
        }catch (Exception e) {
                System.err.println("Failed to Execute" + SQLFilePath
                                + " The error is " + e.getMessage());
        }
    }
    
    /**
     * Changed the details of the Player within the database.
     * @param p - Player
     */
    public static void alterPlayer(Player p)
    {
        Connection con = null;
        Statement stmt = null;
        
        String playerType;
        ProPlayer pPlay;
        String earnings = "null";
        if (p instanceof AmateurPlayer)
        {
            playerType = "Amateur";
        }
        else
        {
            playerType = "Pro";
            pPlay = (ProPlayer)p;
            earnings = "'" + pPlay.getEarnings() + "'";
        }
        
        try {
            con = ConnectionDetails.getConnection("Poker");
            stmt = con.createStatement();
            
            String sql =
                    "UPDATE player SET playerType= '" + playerType + "',"
                    +  "firstName='" + p.getFirstName() + "', "
                    +  "lastName='" + p.getLastName() + "', "
                    +  "age='" + p.getAge() + "', "
                    +  "phone='" + p.getPhone() + "', "
                    +  "gender='" + p.getGender() + "', "
                    +  "streetNum='" + p.getStreetNum() + "', "
                    +  "streetName='" + p.getStreetName() + "', "
                    +  "suburb='" + p.getSuburb() + "', "
                    +  "postcode='" + p.getPostcode() + "', "
                    +  "earnings=" + earnings + ", "
                    +  "buyin='" + p.getBuyin() + "', "
                    +  "eventID='" + p.getEventID() + "' "
                            + "WHERE playerID=" + p.getPlayerID();
            
            // System.out.println(sql); // for testing
            stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(null,
                    "Student ID: " + p.getPlayerID() + " - Details has been Changed");
            
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            // Place code in here that will always be run.
        }
    }
}
