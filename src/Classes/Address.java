package Classes;

/**
 * Address.java
 * 02/10/2017
 * @author Sam
 */
public class Address 
{
    private int streetNum;
    private String StreetName;
    private String Suburb;
    private int postcode;

    /**
     * Constructor
     * @param streetNo
     * @param StreetName
     * @param Suburb
     * @param postcode
     */
    public Address(int streetNo, String StreetName, String Suburb, int postcode)
    {
        this.streetNum = streetNo;
        this.StreetName = StreetName;
        this.Suburb = Suburb;
        this.postcode = postcode;
    }

    /**
     *
     * @return int
     */
    public int getStreetNum()
    {
        return streetNum;
    }

    /**
     *
     * @param streetNo
     */
    public void setStreetNum(int streetNo)
    {
        this.streetNum = streetNo;
    }

    /**
     *
     * @return String
     */
    public String getStreetName()
    {
        return StreetName;
    }

    /**
     *
     * @param StreetName
     */
    public void setStreetName(String StreetName)
    {
        this.StreetName = StreetName;
    }

    /**
     *
     * @return String
     */
    public String getSuburb()
    {
        return Suburb;
    }

    /**
     *
     * @param Suburb
     */
    public void setSuburb(String Suburb)
    {
        this.Suburb = Suburb;
    }

    /**
     *
     * @return int
     */
    public int getPostcode()
    {
        return postcode;
    }

    /**
     *
     * @param postcode
     */
    public void setPostcode(int postcode)
    {
        this.postcode = postcode;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString()
    {
        return streetNum + " " + StreetName + ", " + Suburb + ", " + postcode;
    }
}
