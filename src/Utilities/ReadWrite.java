package Utilities;

import Classes.AmateurPlayer;
import Classes.Event;
import Classes.Player;
import Classes.ProPlayer;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * ReadWrite.java
 * 04/11/2017
 * @author Sam
 */
public class ReadWrite 
{
    /**
     * Writes a List of Players to a Binary File
     * @param fileName
     * @param playerList
     * @throws IOException
     */
    public static void writePlayersToFile(String fileName, ArrayList<Player> playerList) throws IOException
    {
        FileOutputStream fos = new FileOutputStream(fileName);
        DataOutputStream dos = new DataOutputStream(fos);
        
        // Loop Through
        for (Player p: playerList)
        {
            ProPlayer pPlay = null;
            double earnings = 0;
            String playerType = "";
            if (p instanceof AmateurPlayer)
            {
                playerType = "Amateur";
            }
            else
            {
                playerType = "Pro";
                pPlay = (ProPlayer)p;
                earnings = pPlay.getEarnings();
            }
            
            dos.writeInt(p.getPlayerID());
            dos.writeUTF(playerType);
            dos.writeUTF(p.getFirstName());
            dos.writeUTF(p.getLastName());
            dos.writeInt(p.getAge());
            dos.writeUTF(p.getPhone());
            dos.writeUTF(p.getGender());
            dos.writeInt(p.getStreetNum());
            dos.writeUTF(p.getStreetName());
            dos.writeUTF(p.getSuburb());
            dos.writeInt(p.getPostcode());
            dos.writeDouble(earnings);
            dos.writeInt(p.getBuyin());
            dos.writeInt(p.getEventID());
        }
        dos.close();
    }
    
    /**
     * Reads Binary File with a list of Players and returns ArrayList
     * @param fileName
     * @return ArrayList
     * @throws IOException
     */
    public static ArrayList<Player> readPlayerFromFile(String fileName) throws IOException
    {
        ArrayList<Player> list = new ArrayList();
        
        int playerID, age, streetNum, postcode, buyin, eventID;
        String playerType, firstName, lastName, phone, gender, streetName, suburb;
        double earnings;
        
        Player temp;
        
        FileInputStream fIn = new FileInputStream(fileName); 
        DataInputStream dIn = new DataInputStream(fIn);

        while (dIn.available() != 0)
        {
            playerID = dIn.readInt();
            playerType = dIn.readUTF();
            firstName = dIn.readUTF();
            lastName = dIn.readUTF();
            age = dIn.readInt();
            phone = dIn.readUTF();
            gender = dIn.readUTF();
            streetNum = dIn.readInt();
            streetName = dIn.readUTF();
            suburb = dIn.readUTF();
            postcode = dIn.readInt();
            earnings = dIn.readDouble();
            buyin = dIn.readInt();
            eventID = dIn.readInt();
            
            switch(playerType)
            {
                case "Amateur":
                    temp = new AmateurPlayer(playerID, firstName, lastName, age,
                                phone, gender, streetNum, streetName, suburb,
                                postcode, buyin, eventID);
                    break;
                case "Pro":
                    temp = new ProPlayer(playerID, firstName, lastName, age,
                                phone, gender, streetNum, streetName, suburb,
                                postcode, earnings, buyin, eventID);
                    break;
                default:
                    temp = null;
            }
            
            if (temp != null)
                list.add(temp);
        }
        dIn.close();
        
        return list;
    }
    
    /**
     * Writes A List of Events to Binary File
     * @param fileName
     * @param eventList
     * @throws IOException
     */
    public static void writeEventsToFile(String fileName, ArrayList<Event> eventList) throws IOException
    {
        FileOutputStream fos = new FileOutputStream(fileName);
        DataOutputStream dos = new DataOutputStream(fos);
        
        for (Event e: eventList)
        {
            dos.writeInt(e.getEventID());
            dos.writeUTF(e.getEventName());
        }
        dos.close();
    }
    
    /**
     * Reads Binary File of Events and returns a ArrayList
     * @param fileName
     * @return ArrayList
     * @throws IOException
     */
    public static ArrayList<Event> readEventFromFile(String fileName) throws IOException
    {
        ArrayList<Event> list = new ArrayList();
        
        int eventID;
        String eventName;
        
        Event temp;
        
        FileInputStream fIn = new FileInputStream(fileName); 
        DataInputStream dIn = new DataInputStream(fIn);

        while (dIn.available() != 0)
        {
            eventID = dIn.readInt();
            eventName = dIn.readUTF();
            
            temp = new Event(eventID, eventName);
            
            list.add(temp);
        }
        dIn.close();
        
        return list;
    }
}
