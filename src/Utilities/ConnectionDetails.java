package Utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * ConnectionDetails.java
 * 09/10/2017
 * @author Sam
 */
public class ConnectionDetails 
{
    private static String username = "root";
    private static String password = "root";
    private static final String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/" ;

    /**
     *
     * @param username String
     */
    public static void setUsername(String username)
    {
        ConnectionDetails.username = username;
    }

    /**
     *
     * @param password String
     */
    public static void setPassword(String password)
    {
        ConnectionDetails.password = password;
    }
    
    /**
     * Connects to a MySql Database Server
     * @return Connection
     */
    public static Connection getConnection(String database){
        Connection con = null;
        url = "jdbc:mysql://localhost:3306/" + database + "?autoReconnect=true";
        try 
        {
            // load the MYSQl drivers
            Class.forName(driver);
            con = DriverManager.getConnection(url, username, password);
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        } 
        catch (ClassNotFoundException e) 
        {
            e.printStackTrace();
        } 
        finally 
        {
            // Place code in here that will always be run;
        }
        return con;
    }
    
    /**
     * Connections to MySQL Server with no Database specified.
     * @return Connection
     */
    public static Connection getConnection(){
        Connection con = null;
        try 
        {
            // load the MYSQl drivers
            Class.forName(driver);
            con = DriverManager.getConnection(url, username, password);
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        } 
        catch (ClassNotFoundException e) 
        {
            e.printStackTrace();
        } 
        finally 
        {
            // Place code in here that will always be run;
        }
        return con;
    }
}
