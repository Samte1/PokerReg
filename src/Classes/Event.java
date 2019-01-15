package Classes;

/**
 * Event.java
 * @author Sam
 * @date 17/10/2017
 */
public class Event 
{
    private int eventID;
    private String eventName;

    /**
     * Constructor
     * @param eventID
     * @param eventName
     */
    public Event(int eventID, String eventName)
    {
        this.eventID = eventID;
        this.eventName = eventName;
    }

    /**
     *
     * @return int
     */
    public int getEventID()
    {
        return eventID;
    }

    /**
     *
     * @param eventID
     */
    public void setEventID(int eventID)
    {
        this.eventID = eventID;
    }

    /**
     *
     * @return String
     */
    public String getEventName()
    {
        return eventName;
    }

    /**
     *
     * @param eventName
     */
    public void setEventName(String eventName)
    {
        this.eventName = eventName;
    }

    /**
     * converts to string
     * @return
     */
    @Override
    public String toString()
    {
        return eventID + " - " + eventName;
    }
}
