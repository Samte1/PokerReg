package Classes;

/**
 * ProPlayer.java
 * 02/10/2017
 * @author Sam
 */
public class ProPlayer extends Player
{
    private double earnings;

    /**
     * Constructor - Used before in Player in put into the Database
     * @param firstName
     * @param lastName
     * @param age
     * @param phone
     * @param gender
     * @param streetNo
     * @param streetName
     * @param suburb
     * @param postcode
     * @param earnings
     */
    public ProPlayer(String firstName, String lastName, int age, String phone, 
                    String gender, int streetNo, String streetName,
                        String suburb, int postcode, double earnings)
    {
        super(firstName, lastName, age, phone, gender, streetNo, streetName, 
                                                            suburb, postcode);
        this.earnings = earnings;
        calcBuyin();
    }
    
    /**
     * Constructor - For when data is extracted from database
     * @param playerID
     * @param firstName
     * @param lastName
     * @param age
     * @param phone
     * @param gender
     * @param streetNo
     * @param streetName
     * @param suburb
     * @param postcode
     * @param earnings
     * @param buyin
     * @param eventID
     */
    public ProPlayer(int playerID, String firstName, String lastName, int age, String phone, 
                    String gender, int streetNo, String streetName,
                        String suburb, int postcode, double earnings, int buyin, int eventID)
    {
        super(playerID, firstName, lastName, age, phone, gender, streetNo, streetName, 
                                            suburb, postcode, buyin, eventID);
        this.earnings = earnings;
    }

    /**
     *
     * @return Double
     */
    public double getEarnings()
    {
        return earnings;
    }
    
    @Override
    public void calcBuyin()
    {
        setBuyin(100);
    }
}
