package Classes;

/**
 * AmateurPlayer.java
 * 02/10/2017
 * @author Sam
 */
public class AmateurPlayer extends Player
{

    /**
     * Constructor - Used when not all information about the object is known
     * @param firstName
     * @param lastName
     * @param age
     * @param phone
     * @param gender
     * @param streetNo
     * @param streetName
     * @param suburb
     * @param postcode
     */
    public AmateurPlayer(String firstName, String lastName, int age, String phone, 
                        String gender, int streetNo, String streetName,
                            String suburb, int postcode)
    {
        super(firstName, lastName, age, phone, gender, streetNo, streetName, 
                                                            suburb, postcode);
        calcBuyin();
    }
    
    /**
     * Constructor - Has Every value as input
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
     * @param buyin
     * @param eventID
     */
    public AmateurPlayer(int playerID, String firstName, String lastName, int age, String phone, 
                    String gender, int streetNo, String streetName,
                        String suburb, int postcode, int buyin, int eventID)
    {
        super(playerID, firstName, lastName, age, phone, gender, streetNo, streetName, 
                                            suburb, postcode, buyin, eventID);
    }
    
    /**
     * Sets the buying for the Player
     */
    @Override
    public void calcBuyin()
    {
        setBuyin(300);
    }
}
