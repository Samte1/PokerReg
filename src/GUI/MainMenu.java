
package GUI;

import Classes.Event;
import Classes.Player;
import Utilities.DatabaseMethods;
import Utilities.ReadWrite;
import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/** 
 * MainMenu.java
 * 12/10/17
 * @author Sam Te
 */
public class MainMenu extends JFrame implements ActionListener
{
    // Values for Size of Frame
    private final int X_LENGTH = 700;
    private final int Y_LENGTH = 400;
    
    // Values from GridLayout for buttons
    private final int B_GRID_ROW = 2;
    private final int B_GRID_COL = 3;
    
    // ArrayList
    ArrayList<Event> eventList;
    ArrayList<Player> playerList;
     
    // outer first plane contains a borderlayout with 2 panels - north, center
    // layer1[0] contains a banner in north
    // layer1[1] contains layer2 in center
    JPanel[] layer1 = new JPanel[2]; 
    
    JLabel banner = new JLabel(new ImageIcon("banner.jpg"));
    
    // center section has another Borderlayout with 2 panels - north and center
    // layer2[0] contains title in north
    // layer2[1] contains layer3 in center
    JPanel[] layer2 = new JPanel[2];
    // layer3 contains a grid for buttons
    JPanel[] layer3 = new JPanel[6];
    
    // title
    JLabel title = new JLabel("Poker Registration");
    Font titleFont = new Font("Arial", Font.BOLD, 22);
    
    //Set the number of buttons
    JButton[] buttons = new JButton[6];
    
    /**
     * Constructor
     */
    public MainMenu()
    {
        super("Poker Registration");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(200, 50, X_LENGTH, Y_LENGTH);
        setVisible(true);
        setResizable(false);
        setLayout(new BorderLayout());
        
        runDatabaseScript();
        
        createPanels();
        setupButtons();
        setupPanels();
        validate();
    }
    
    /**
     * Runs a database script
     */
    public final void runDatabaseScript()
    {
        try
        {
            DatabaseMethods.runSQLScript("database.sql");
        }
        catch (ClassNotFoundException e)
        {
            System.err.println("Could not found class");
        }
        catch (SQLException e)
        {
            System.err.println("SQL File could not run");
        }
    }
    
    /**
     * Creates panel objects
     */
    public final void createPanels()
    {
        // two different loops because number of panels in the second layer is
        // increased then loop doesnt function but in this case could do one loop.
        for (int i = 0; i < layer1.length; ++i)
        {
            layer1[i] = new JPanel();
        }
        
        for (int i = 0; i < layer2.length; ++i)
        {
            layer2[i] = new JPanel();
        }
    }
    
    /**
     * Creates Button objects and add ActionListeners to them
     */
    public final void setupButtons()
    {
        // button creation
        for(int i = 0; i < buttons.length; ++i)
        {
            buttons[i] = new JButton();
            //add dimensions to the buttons
            //buttons[i].setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
            buttons[i].addActionListener(this);
        }
        
        // Add text to each button
        buttons[0].setText("Create new Event");
        buttons[1].setText("Sign up for Event");
        buttons[2].setText("Search for a Player");
        buttons[3].setText("Help Document");
        buttons[4].setText("Restore from Backup");
        buttons[5].setText("Save to Backup");
    }
    
    /**
     * Places objects to the panels and the Panels to the frame
     */
    public final void setupPanels()
    {
        // setup first layer 
        add(layer1[0], BorderLayout.NORTH);
        add(layer1[1], BorderLayout.CENTER);
        layer1[0].add(banner);
        
        layer1[1].setLayout(new BorderLayout());
        layer1[1].add(layer2[0], BorderLayout.NORTH);
        layer2[0].add(title);
        title.setFont(titleFont);
        
        layer1[1].add(layer2[1], BorderLayout.CENTER);
        layer2[1].setLayout(new GridLayout(B_GRID_ROW, B_GRID_COL));
        
        // layer3 of grid of buttons
        for (int i = 0; i < layer3.length; ++i)
        {
            layer3[i] = new JPanel(new BorderLayout());
            //layer3[i].setLayout(new FlowLayout(FlowLayout.CENTER));
            layer3[i].add(buttons[i]);
            layer2[1].add(layer3[i]);
        }
    }
    
    /**
     * Retrieves list of event and players and writes file
     * Output - Player.bin, Event.bin
     */
    public final void saveDatabase()
    {
        // Get List of events and Players
        eventList = DatabaseMethods.getEventList();
        playerList = DatabaseMethods.getPlayerList();

        // Save Player to File
        try
        {
            ReadWrite.writePlayersToFile("Player.bin", playerList);
            ReadWrite.writeEventsToFile("Event.bin", eventList);
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(null, "Problem with File");
        }
    }
    
    /**
     * Read from File and  then writes them into the database
     * Input - Player.bin, Event.bin
     */
    public final void restoreDatabase()
    {
        // Retrieve Databaase File.
        try
        {
            eventList = ReadWrite.readEventFromFile("Event.bin");
            playerList = ReadWrite.readPlayerFromFile("Player.bin");
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(null, "Problem with File");
        }

        //  Recreate database
        DatabaseMethods.backupDatabase(playerList, eventList);
    }
    
    /**
     * Action Event - Action for each button press.
     * @param event
     */
    @Override
    public void actionPerformed(ActionEvent event)
    {
        Object source = event.getSource();
        if (source == buttons[0])
        {
            String tempEvent = JOptionPane.showInputDialog("Enter Event Name");
            if (tempEvent != null)
                DatabaseMethods.insertEvent(tempEvent);
            //CreateEvent CEvent = new CreateEvent(this);
            //this.setVisible(false);
        }
        if (source == buttons[1])
        {
            // Sign up for a event
            SignUp signUp = new SignUp(this);
            this.setVisible(false);
        }
        if (source == buttons[2])
        {
            // Search for a Player
            SearchPlayer search = new SearchPlayer(this);
            this.setVisible(false);
        }
        if (source == buttons[3])
        {
            // Read Help Document
            File myFile = new File("help.pdf");
            
            try
            {
                //Open PDF File
                Desktop.getDesktop().open(myFile);
            }
            catch (IOException e)
            {
                JOptionPane.showMessageDialog(null, "Error Reading File");
            }
        }
        if (source == buttons[4])
        {
            // Prompt menu
            int input = JOptionPane.showConfirmDialog(null, 
                    "Do you wish to Restore Your Database"
                    + "\nNOTE: Restoring from "
                    + "Backup will cause the Current Data in the "
                    + "Database to be Deleted",
                                "Restore Database",  JOptionPane.YES_NO_OPTION);
            if (input == 0)
            {
                //Restore database
                restoreDatabase();
            }
        }
        if (source == buttons[5])
        {
            // Prompt menu
            int input = JOptionPane.showConfirmDialog(null, 
                    "Do you wish to Backup your Database to File\n"
                            + "NOTE: This is replace the Previous Backup",
                                "Save Database",  JOptionPane.YES_NO_OPTION);
            if (input == 0)
            {
                //Restore database
                saveDatabase();
            }
            
        }
    }
    
    // for testing

    /**
     *
     * @param args
     */
    public static void main(String args[])
    {
        MainMenu frame = new MainMenu();
    }
}
