package Utilities;

/**
 * StringManipulation.java
 * 18/10/2017
 * @author Sam
 */
public class StringManipulation 
{

    /**
     * Converts first letter of every word into Uppercase,
     * Removes spaces before first word and last word.
     * @param input
     * @return String
     */
    public static String toPropercase(String input) 
    {
        StringBuilder temp = new StringBuilder(input);
        String output = null;
        
        for (int i = 0; i < temp.length(); ++i)
        {
            // if first char is space delete
            if (temp.charAt(0) == ' ')
            {
                temp.delete(0, 1);
                --i;
            }
            else if (i == temp.length() - 1)
            {
                if (temp.charAt(i) == ' ')
                {
                    temp.delete(i, i + 1);
                    --i;
                }
            }
            else
            {
                temp.replace(0, 1, ("" + temp.charAt(0)).toUpperCase());
                char c = temp.charAt(i);
                if (c == ' ')
                {
                    temp.replace(i + 1, i + 2, ("" + temp.charAt(i + 1)).toUpperCase());
                }
            }   
        }
        output = temp.toString();
        return output;
    }
    
    
//    public static void main(String[] args)
//    {
//        String input = "      hello world   ";
//        String output = toPropercase(input);
//        
//        System.out.println(output);
//    }

    
}
