package GUI;

import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 * AddMember.java
 * 28/09/2017
 * @author Sam
 */

public class AddMember extends JFrame
{
    // Values for Size of Frame
    private final int X_LENGTH = 900;
    private final int Y_LENGTH = 700;
    
    public AddMember()
    {
        super("Add Member");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(X_LENGTH, Y_LENGTH);
        setVisible(true);
        setLayout(new BorderLayout());
        
    }
    
//    public static void main(String[] args)
//    {
//        AddMember frame = new AddMember();
//    }
}
