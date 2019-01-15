package GUI;

import Classes.Event;
import Classes.ResultsTableModel;
import Utilities.DatabaseMethods;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 * SearchPlayer.java
 * 03/10/2017
 * @author Sam
 */
public class SearchPlayer extends JFrame implements ActionListener
{
    // Values for Size of Frame
    private final int X_LENGTH = 700;
    private final int Y_LENGTH = 460;
    
    // Details Grid
    JLabel title = new JLabel("Search Player");
    Font titleFont = new Font("Arial", Font.BOLD, 22);
    
    // Panels
    JPanel[] layer1 = new JPanel[2];
    JPanel[] layer2 = new JPanel[3];
    JPanel pnlTitle = new JPanel();
    JPanel pnlSearch = new JPanel();
    JPanel pnlResults = new JPanel();
    
    //ArrayList
    ArrayList<Event> tempEventList = new ArrayList();
    Event[] eventList;
    
    //Text box
    JTextField txtFirstName, txtLastName;
    
    // ComboBox
    JComboBox cboEvents;
    
    //Buttons
    JButton[] buttons = new JButton[2];
    //JButton[] updateButtons = new JButton[2];
    
    //Table
    JTable tblSearch = new JTable();
    JScrollPane scroll = new JScrollPane(tblSearch, 
                        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    ResultsTableModel resModel = new ResultsTableModel();
    
    // Banner
    JLabel banner = new JLabel(new ImageIcon("banner.jpg"));
    
    // First menu
    MainMenu mainMenu;
    ResultTable searchFrame = null;

    /**
     * Constructor
     * @param tempMenu
     */
    public SearchPlayer(MainMenu tempMenu)
    {
        super("Search Players");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(200, 50, X_LENGTH, Y_LENGTH);
        setVisible(true);
        setResizable(false);
        setLayout(new BorderLayout());
        mainMenu = tempMenu;
        searchFrame = new ResultTable(this, resModel);
        
        createPanels();
        createButtons();
        createEventList();
        
        setupPanels();
        layer1[0].add(banner);
        
        layer2[0].add(title);
        title.setFont(titleFont);
        
        setupSearchGrid();
        validate();
    }
    
    /**
     * Creates Panel Objects
     */
    public final void createPanels()
    {
        
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
     * Create Button objects and Add actionListenters to them
     */
    public final void createButtons()
    {
        buttons[0] = new JButton("Search");
        buttons[1] = new JButton("Main Menu");
        
        buttons[0].addActionListener(this);
        buttons[1].addActionListener(this);
    }
    
    /**
     * Attach panels to the frame
     */
    public final void setupPanels()
    {
        this.add(layer1[0], BorderLayout.NORTH);
        this.add(layer1[1], BorderLayout.CENTER);
        
        layer1[1].setLayout(new BorderLayout());
        layer1[1].add(layer2[0], BorderLayout.NORTH); //Title
        
        layer1[1].add(layer2[1], BorderLayout.CENTER); // Search fields
        layer1[1].add(layer2[2], BorderLayout.SOUTH); // Search buttons
    }
    
    /**
     * Attach elements of the search grid to the frame
     */
    public final void setupSearchGrid()
    {
        layer2[1].setLayout(new GridLayout(3, 2));
        layer2[1].setBorder(BorderFactory.createTitledBorder("Search Database"));
        
        layer2[1].add(new JLabel("First Name"));
        layer2[1].add(txtFirstName = new JTextField());
        
        layer2[1].add(new JLabel("Last Name"));
        layer2[1].add(txtLastName = new JTextField());
        
        layer2[1].add(new JLabel("Event"));
        cboEvents = new JComboBox(eventList);
        layer2[1].add(cboEvents);
        
        layer2[2].setLayout(new GridLayout(1, 2));
        layer2[2].add(buttons[0]);
        layer2[2].add(buttons[1]);
    }
    
    /**
     * Creates an Event List
     */
    public final void createEventList()
    {
        tempEventList = DatabaseMethods.getEventList();
        Event tempEvent = new Event(0, "Select An Event to Search by Event");
        tempEventList.add(0, tempEvent);
        eventList = tempEventList.toArray(new Event[tempEventList.size()]);
    }
    
    /**
     *
     * @param ev
     */
    @Override
    public void actionPerformed(ActionEvent ev)
    {
        Object source = ev.getSource();
        
        if (source == buttons[0])
        {
            // Set search parameters for ResultTableModel
            try
            {
                resModel.setFirstName(txtFirstName.getText());
            }
            catch (NullPointerException e)
            {
                resModel.setFirstName("");
            }
            try
            {
                resModel.setLastName(txtLastName.getText());
            }
            catch (NullPointerException e)
            {
                resModel.setLastName("");
            }
            
            Event tempEvent = (Event)cboEvents.getSelectedItem();
            resModel.setEventID(tempEvent.getEventID());
            
            // Change resModel so that the table changes
            resModel.getDataFromDatabase();
            resModel.fireTableDataChanged();
            searchFrame.setVisible(true);
        }
        if (source == buttons[1])
        {
            // return to mainmenu
            this.dispose();
            searchFrame.dispose();
            mainMenu.setVisible(true);
        }
    }
}
