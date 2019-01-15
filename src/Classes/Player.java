package Classes;

/**
 * Player.java
 * 02/10/2017
 * @author Sam
 */
public abstract class Player 
{
    private int playerID;
    private String firstName;
    private String lastName;
    private int age;
    private String phone;
    private String gender;
    
    private Address address; //streetNo, streetName, suburb, postcode
    private int buyin;
    private int eventID;
    
    // abstract methods

    /**
     * Set a value for buyin in child classes
     */
    public abstract void calcBuyin();

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
     */
    public Player(String firstName, String lastName, int age, String phone, 
                    String gender, int streetNo, String streetName,
                        String suburb, int postcode)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.phone = phone;
        this.gender = gender;
        this.address = new Address(streetNo, streetName, suburb, postcode);
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
     * @param buyin
     * @param eventID
     */
    public Player(int playerID, String firstName, String lastName, int age, String phone, 
                    String gender, int streetNo, String streetName,
                        String suburb, int postcode, int buyin, int eventID)
    {
        this.playerID = playerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.phone = phone;
        this.gender = gender;
        this.address = new Address(streetNo, streetName, suburb, postcode);
        this.buyin = buyin;
        this.eventID = eventID;
    }

    /**
     *
     * @return int
     */
    public int getPlayerID()
    {
        return playerID;
    }

    /**
     *
     * @param playerID
     */
    public void setPlayerID(int playerID)
    {
        this.playerID = playerID;
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
    public String getFirstName()
    {
        return firstName;
    }

    /**
     *
     * @param firstName
     */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    /**
     *
     * @return String
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     *
     * @param lastName
     */
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    /**
     *
     * @return int
     */
    public int getAge()
    {
        return age;
    }

    /**
     *
     * @param age
     */
    public void setAge(int age)
    {
        this.age = age;
    }

    /**
     *
     * @return String
     */
    public String getPhone()
    {
        return phone;
    }

    /**
     *
     * @param phone
     */
    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    /**
     *
     * @return String
     */
    public String getGender()
    {
        return gender;
    }

    /**
     *
     * @param gender
     */
    public void setGender(String gender)
    {
        this.gender = gender;
    }

    /**
     *
     * @return String
     */
    public String getAddress()
    {
        return address.toString();
    }

    /**
     *
     * @param address
     */
    public void setAddress(Address address)
    {
        this.address = address;
    }
    
    /**
     *
     * @return Int
     */
    public int getStreetNum()
    {
        return address.getStreetNum();
    }
    
    /**
     *
     * @return String
     */
    public String getStreetName()
    {
        return address.getStreetName();
    }
    
    /**
     *
     * @return String
     */
    public String getSuburb()
    {
        return address.getSuburb();
    }
    
    /**
     *
     * @return int
     */
    public int getPostcode()
    {
        return address.getPostcode();
    }

    /**
     *
     * @return int
     */
    public int getBuyin()
    {
        return buyin;
    }

    /**
     *
     * @param buyin
     */
    public void setBuyin(int buyin)
    {
        this.buyin = buyin;
    }

    /**
     * Convert to String
     * @return String
     */
    @Override
    public String toString()
    {
        return "Player{" + "firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + ", phone=" + phone + ", gender=" + gender + ", address=" + address + ", buyin=" + buyin + '\n';
    }
}
