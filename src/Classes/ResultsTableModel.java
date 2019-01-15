package Classes;

import Utilities.DatabaseMethods;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 * ResultsTableModel.java
 * @author Sam
 * @date 17/10/2017
 */
public class ResultsTableModel extends AbstractTableModel
{
    private ArrayList<Player> list = new ArrayList();
    private String[] columnNames = {"Player ID", "PlayerType", "First Name", 
     "Last Name", "Age", "Phone", "Gender", "Street Number", "Street Name", 
     "Suburb", "Postcode", "Earnings", "Buyin", "Event ID"};
    // Search Parameters
    private String firstName = "";
    private String lastName = "";
    private int eventID = 0;

    /**
     * Sets First name search parameter
     * @param firstName
     */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    /**
     * Sets Last name search parameter
     * @param lastName
     */
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    /**
     * Sets Event ID search parameter
     * @param eventID
     */
    public void setEventID(int eventID)
    {
        this.eventID = eventID;
    }
    
    /**
     * Constructor
     */
    public ResultsTableModel()
    {
        getDataFromDatabase();
    }
    
    /**
     * 
     * @return int
     */
    @Override
    public int getRowCount()
    {
        return list.size();
    }
    
    /**
     *
     * @return int
     */
    @Override
    public int getColumnCount()
    {
        return columnNames.length;
    }
    
    /**
     * Get a value from a cell in a table 
     * @param row
     * @param col
     * @return Object 
     */
    @Override
    public Object getValueAt(int row, int col)
    {
        Player play = list.get(row);
        AmateurPlayer aPlay = null;
        ProPlayer pPlay = null;
        
        if (play instanceof AmateurPlayer)
            aPlay = (AmateurPlayer)play;
        else
            pPlay = (ProPlayer)play;
            
        switch(col)
        {
            case 0:
                return play.getPlayerID();
            case 1:
                if (play instanceof AmateurPlayer)
                    return "Amateur";
                else
                    return "Pro";
            case 2:
                return play.getFirstName();
            case 3:
                return play.getLastName();
            case 4:
                return play.getAge();
            case 5:
                return play.getPhone();
            case 6:
                return play.getGender();
            case 7:
                return play.getStreetNum();
            case 8:
                return play.getStreetName();
            case 9:
                return play.getSuburb();
            case 10:
                return play.getPostcode();
            case 11:
                if (play instanceof AmateurPlayer)
                    return "null";
                else
                    return pPlay.getEarnings();
            case 12:
                return play.getBuyin();
            case 13:
                return play.getEventID();
        }
        return null;
    }
    
    /**
     *
     * @param col
     * @return String
     */
    @Override
    public String getColumnName(int col)
    {
        return columnNames[col];
    }
    
    /**
     *
     * @param row
     * @return Player
     */
    public Player getRow(int row)
    {
        Player play = list.get(row);
        return play;
    }
    
    /**
     * Gets data from database using the search parameters
     */
    public void getDataFromDatabase()
    {
        list = DatabaseMethods.getPlayerList(firstName, lastName, eventID);
    }
}
