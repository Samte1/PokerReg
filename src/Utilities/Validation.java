package Utilities;

/**
 * Validation.java
 * 12/10/2017
 * @author Sam
 */
public class Validation 
{

    /**
     * Test the String for Numbers and returns True if there are no numbers
     * @param input
     * @return boolean
     */
    public static boolean noNumbers(String input)
    {
        boolean valid = true;
        for (int i = 0; i < input.length(); ++i)
        {
            char c = input.charAt(i);
            if (Character.isDigit(c))
            {
                valid = false;
            }
        }
        return valid;
    }
    
    /**
     * Test the String for Numbers and returns true if there the whole string is numbers
     * @param input
     * @return boolean
     */
    public static boolean isNumbers(String input)
    {
        boolean  valid = true;
        for (int i = 0; i < input.length(); ++i)
        {
            char c = input.charAt(i);
            if (!Character.isDigit(c))
            {
                valid = false;
            }
        }
        return valid;
    }
    
    /**
     * Test the String for letters and returns true if it is ALL Letters,
     * returns false if there are any special characters
     * @param input
     * @return boolean
     */
    public static boolean isLetters(String input)
    {
        boolean  valid = true;
        for (int i = 0; i < input.length(); ++i)
        {
            char c = input.charAt(i);
            if (!Character.isLetter(c) && c != ' ')
            {
                valid = false;
            }
        }
        return valid;
    }
}
