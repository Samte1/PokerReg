package GUI;

import Classes.Event;
import Classes.AmateurPlayer;
import Classes.ProPlayer;
import Classes.Player;
import Classes.ResultsTableModel;
import Utilities.DatabaseMethods;
import Utilities.StringManipulation;
import Utilities.Validation;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * UpdatePlayer.java
 * 28/09/2017
 * @author Sam
 */
public class UpdatePlayer extends JFrame implements ActionListener
{
    // Values for Size of Frame
    private final int X_LENGTH = 700;
    private final int Y_LENGTH = 800;
    
    // Sectioning
    private final int SECTION_ROWS = 3;
    private final int SECTION_COLS = 1;
    
    // Details Grid
    private final int DET_ROWS = 5;
    private final int DET_COLS = 2;
    
    // Address Grid
    private final int ADD_ROWS = 5;
    private final int ADD_COLS = 2;
    
    // Type Grid
    private final int TYPE_ROWS = 5;
    private final int TYPE_COLS = 2;
    
    // Radio Button values;
    private String gender = "Male";
    private int playerType = 1;
    
    // Arrays
    ArrayList<Player> playerList;
    ArrayList<Event> tempEventList = new ArrayList();
    Event[] eventList;
    
    JLabel banner = new JLabel(new ImageIcon("banner.jpg"));
    
    JLabel title = new JLabel("");
    Font titleFont = new Font("Arial", Font.BOLD, 22);
    
    //JPanels
    JPanel[] layer1 = new JPanel[3];
    JPanel[] layer2 = new JPanel[2];
    // layer 3 for each set of input fields
    JPanel[] layer3 = new JPanel[3];
    
    //Text fields
    JTextField txtFirst, txtLast, txtAge, txtPhone;
    JTextField txtStreetNo, txtStreetName, txtSuburb, txtPostcode, txtEarnings;
    
    // Radio Buttons
    JRadioButton radMale = new JRadioButton("Male");
    JRadioButton radFemale = new JRadioButton("Female");
    JRadioButton radAmateur = new JRadioButton("Amateur");
    JRadioButton radPro = new JRadioButton("Pro");
    
    // JLabel ref
    JLabel lblEarnings;
    
    // Combobox
    JComboBox cboEvents;
    
    //buttons
    JButton[] buttons = new JButton[3];
    
    Player player;
    
    // Menus and references
    SearchPlayer parent;
    ResultTable sTable;
    ResultsTableModel resModel;
    
    /**
     * Constructor
     * @param p
     * @param tempMenu
     * @param tFrame
     * @param model
     */
    public UpdatePlayer(Player p, SearchPlayer tempMenu, ResultTable tFrame, ResultsTableModel model)
    {
        super("Update Player");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(200, 50, X_LENGTH, Y_LENGTH);
        setVisible(true);
        setResizable(false);
        setLayout(new BorderLayout());
        parent = tempMenu;
        sTable = tFrame;
        resModel = model;
        player = p;
        
        createEventList();
        createPanels();
        createButtons();
        setupPanels();
        
        // add banner
        layer1[0].add(banner);
        
        // Add title under banner
        layer2[0].add(title);
        title.setFont(titleFont);
        
        setupDetailGrid();
        setupAddressGrid();
        setupType();
        setupButtons(); //add listeners as well\
        
        // Change text fields and button to match player
        inputPlayerInfo();
    }
    
    /**
     * Retrieve Event lIst from database
     */
    public final void createEventList()
    {
        tempEventList = DatabaseMethods.getEventList();
        eventList = tempEventList.toArray(new Event[tempEventList.size()]);
    }
    
    /**
     * Create Panel Objects
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
        
        for (int i = 0; i < layer3.length; ++i)
        {
            layer3[i] = new JPanel();
        }
    }
    
    /**
     * Create Button objects
     */
    public final void createButtons()
    {
        // button creation
        for(int i = 0; i < buttons.length; ++i)
        {
            buttons[i] = new JButton();
            // add dimensions to the buttons
            //buttons[i].setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        }
        
        // Add text to each button
        buttons[0].setText("Save Details");
        buttons[1].setText("Clear Details");
        buttons[2].setText("Main Menu");
    }
    
    /**
     * Set panels to frame
     */
    public final void setupPanels()
    {
        this.add(layer1[0], BorderLayout.NORTH);
        this.add(layer1[1], BorderLayout.CENTER);
        this.add(layer1[2], BorderLayout.SOUTH);
        
        layer1[1].setLayout(new BorderLayout());
        layer1[1].add(layer2[0], BorderLayout.NORTH);
        layer1[1].add(layer2[1], BorderLayout.CENTER);
        
        layer2[1].setLayout(new GridLayout(SECTION_ROWS, SECTION_COLS));
        
        for (int i = 0; i < layer3.length; ++i)
        {
            layer2[1].add(layer3[i]);
        }
    }
    
    /**
     * Sets up personal information form
     */
    public final void setupDetailGrid()
    {
        layer3[0].setLayout(new GridLayout(DET_ROWS, DET_COLS));
        layer3[0].setBorder(BorderFactory.createTitledBorder("Player Details"));
        layer3[0].add(new JLabel("First Name"));
        layer3[0].add(txtFirst = new JTextField());
        layer3[0].add(new JLabel("Last Name"));
        layer3[0].add(txtLast = new JTextField());
        layer3[0].add(new JLabel("Age"));
        layer3[0].add(txtAge = new JTextField());
        layer3[0].add(new JLabel("Contact Number"));
        layer3[0].add(txtPhone = new JTextField());
        layer3[0].add(new JLabel("Gender"));
        // ---- Grid Pos 10 ---
        // create panel and button group to add radio buttons
        ButtonGroup bgGender = new ButtonGroup();
        bgGender.add(radMale);
        bgGender.add(radFemale);
        JPanel pnlGender = new JPanel();
        layer3[0].add(pnlGender);
        pnlGender.add(radMale);
        pnlGender.add(radFemale);
        radMale.setSelected(true);
        radMale.addActionListener(this);
        radFemale.addActionListener(this);
    }
    
    /**
     * places Address form 
     */
    public final void setupAddressGrid()
    {
        layer3[1].setLayout(new GridLayout(ADD_ROWS, ADD_COLS));
        layer3[1].setBorder(BorderFactory.createTitledBorder("Player Address"));
        layer3[1].add(new JLabel("Street Number"));
        layer3[1].add(txtStreetNo = new JTextField());
        layer3[1].add(new JLabel("Street Name"));
        layer3[1].add(txtStreetName = new JTextField());
        layer3[1].add(new JLabel("Suburb"));
        layer3[1].add(txtSuburb = new JTextField());
        layer3[1].add(new JLabel("Postcode"));
        layer3[1].add(txtPostcode = new JTextField());
        layer3[1].add(new JLabel(""));
        layer3[1].add(new JLabel(""));
    }
    
    /**
     * Places player type form
     */
    public final void setupType()
    {
        layer3[2].setLayout(new GridLayout(TYPE_ROWS, TYPE_COLS));
        layer3[2].setBorder(BorderFactory.createTitledBorder("Player Type"));
        layer3[2].add(new JLabel("Event"));
        cboEvents = new JComboBox(eventList);
        layer3[2].add(cboEvents);
        layer3[2].add(new JLabel("Player Type"));
        // --- Grid Pos 4 ---
        // Panel and Buttongroup for RadioButton
        ButtonGroup bgType = new ButtonGroup();
        bgType.add(radAmateur);
        bgType.add(radPro);
        JPanel pnlType = new JPanel();
        layer3[2].add(pnlType);
        pnlType.add(radAmateur);
        pnlType.add(radPro);
        radAmateur.setSelected(true);
        radAmateur.addActionListener(this);
        radPro.addActionListener(this);
        
        // --- Grid Pos 5 ---
        layer3[2].add(new JLabel(""));
        layer3[2].add(new JLabel(""));
        layer3[2].add(lblEarnings = new JLabel("Earnings"));
        layer3[2].add(txtEarnings = new JTextField());
        
        layer3[2].add(new JLabel(""));
        layer3[2].add(new JLabel(""));
        
        lblEarnings.setVisible(false);
        txtEarnings.setVisible(false);
    }
    
    /**
     * Create button objects and add ActionlIstenter 
     */
    public final void setupButtons()
    {
        for (int i = 0; i < buttons.length; ++i)
        {
            layer1[2].add(buttons[i]);
            buttons[i].addActionListener(this);
        }
    }
    
    /**
     * Create new player and the alter player details in the database
     */
    public void createPlayer()
    {
        String firstName = null;
        String lastName = null;
        int age = 0;
        String phone = null;
        int streetNo = 0;
        String streetName = null;
        String suburb = null;
        int postcode = 0;
        double earnings = 0;
        String tempString;
        
        boolean valid = true;
        
        // --- Player Information --- 
        tempString = txtFirst.getText();
        if (Validation.isLetters(tempString) && tempString.length() > 0)
            firstName = StringManipulation.toPropercase(tempString);
        else
        {
            valid = false;
            JOptionPane.showMessageDialog(null, "Invalid First Name");
        }
        
        tempString = txtLast.getText();
        if (Validation.isLetters(tempString) && tempString.length() > 0)
            lastName = StringManipulation.toPropercase(tempString);
        else
        {
            valid = false;
            JOptionPane.showMessageDialog(null, "Invalid Last Name");
        }
        
        tempString = txtAge.getText();
        if (Validation.isNumbers(tempString) && tempString.length() > 0
                && tempString.length() <= 3)
            age = Integer.parseInt(tempString);
        else
        {
            valid = false;
            JOptionPane.showMessageDialog(null, "Invalid Age");
        }
        
        tempString = txtPhone.getText();
        if (Validation.isNumbers(tempString) && tempString.length() == 10)
            phone = tempString;
        else
        {
            valid = false;
            if (tempString.length() != 10)
                JOptionPane.showMessageDialog(null, "Phone Number has to be 10 numbers long");
            else
                JOptionPane.showMessageDialog(null, "Invalid Phone");
        }
        
        // ---- Address ------
        tempString = txtStreetNo.getText();
        if (Validation.isNumbers(tempString) && tempString.length() > 0
                && tempString.length() <= 4)
            streetNo = Integer.parseInt(tempString);
        else
        {
            valid = false;
            JOptionPane.showMessageDialog(null, "Invalid Street Number");
        }
        
        tempString = txtStreetName.getText();
        if (Validation.isLetters(tempString) && tempString.length() > 0)
            streetName = StringManipulation.toPropercase(tempString);
        else
        {
            valid = false;
            JOptionPane.showMessageDialog(null, "Invalid Street Name");
        }
        
        tempString = txtSuburb.getText();
        if (Validation.isLetters(tempString) && tempString.length() > 0)
            suburb = StringManipulation.toPropercase(tempString);
        else
        {
            valid = false;
            JOptionPane.showMessageDialog(null, "Invalid Suburb");
        }
        
        tempString = txtPostcode.getText();
        if (Validation.isNumbers(tempString) && tempString.length() > 0
                && tempString.length() <= 5)
            postcode = Integer.parseInt(tempString);
        else
        {
            valid = false;
            JOptionPane.showMessageDialog(null, "Invalid Postcode");
        }
        
        switch(playerType)
        {
            case 1:
                break;
            case 2:
                tempString = txtEarnings.getText();
                earnings = Double.parseDouble(tempString);
                break;
        }
        
        Player tempPlayer = null;
        
        if (valid)
        {
            //create a player using both constructors because if the user changes
            //event or player because the first constuctor needs to be used to 
            //find out the buyin of the player
            Event tempEvent = (Event)cboEvents.getSelectedItem();
            switch(playerType)
            {
                case 1:
                    tempPlayer = new AmateurPlayer(firstName, lastName, age, phone, gender, 
                            streetNo, streetName, suburb, postcode);
                    tempPlayer = new AmateurPlayer(player.getPlayerID(), firstName, 
                            lastName, age, phone, gender, streetNo, streetName,
                            suburb, postcode, tempPlayer.getBuyin(), 
                            tempEvent.getEventID());
                    break;
                case 2:
                    tempPlayer = new ProPlayer(firstName, lastName, age, phone, gender, 
                            streetNo, streetName, suburb, postcode, earnings);
                    
                    tempPlayer = new ProPlayer(player.getPlayerID(), firstName, 
                            lastName, age, phone, gender, streetNo, streetName,
                            suburb, postcode, earnings, tempPlayer.getBuyin(), 
                            tempEvent.getEventID());
                    break;
            }
        }
        
        // Alter Player in database
        if (tempPlayer != null)
        {
            DatabaseMethods.alterPlayer(tempPlayer);
            resModel.getDataFromDatabase();
            resModel.fireTableDataChanged();
            returnToMenu();
        }
    }
    
    /**
     * Change form details to player details
     */
    public void inputPlayerInfo()
    {
        txtFirst.setText(player.getFirstName());
        txtLast.setText(player.getLastName());
        txtAge.setText("" + player.getAge());
        txtPhone.setText(player.getPhone());
        
        //  test players gender
        String tempGender = player.getGender();
        
        if(tempGender.equals("Male"))
        {
            radMale.setSelected(true);
            gender = "Male";
        }
        else // gender = male
        {
            radFemale.setSelected(true);
            gender = "Female";
        }
        
        // Player Address
        txtStreetNo.setText("" + player.getStreetNum());
        txtStreetName.setText(player.getStreetName());
        txtSuburb.setText(player.getSuburb());
        txtPostcode.setText("" + player.getPostcode());
        
        // Player Type
        if (player instanceof AmateurPlayer)
        {
            radAmateur.setSelected(true);
            playerType = 1;
        }
        else
        {
            radPro.setSelected(true);
            txtEarnings.setVisible(true);
            lblEarnings.setVisible(true);
            // create a ProPlayer refernce
            ProPlayer pPlayer = (ProPlayer)player;
            txtEarnings.setText("" + pPlayer.getEarnings());
            playerType = 2;
        }
        
        // Event List
        for (int i = 0; i < eventList.length; ++i)
        {
            if (player.getEventID() == eventList[i].getEventID())
            {
                cboEvents.setSelectedIndex(i);
            }
        }
    }
    
    /**
     * Clear form
     */
    public void clearDetails()
    {
        // Player Details
        txtFirst.setText("");
        txtLast.setText("");
        txtAge.setText("");
        txtPhone.setText("");
        radMale.setSelected(true);
        gender = "Male";
        
        // Player Address
        txtStreetNo.setText("");
        txtStreetName.setText("");
        txtSuburb.setText("");
        txtPostcode.setText("");
        
        // Player Type
        txtPostcode.setText("");
        radAmateur.setSelected(true);
        playerType = 1;
        
        // Event List
        cboEvents.setSelectedIndex(0);
    }
    
    /**
     * Return to parent frame
     */
    public void returnToMenu()
    {
        this.dispose();
        parent.setVisible(true);
        sTable.setVisible(true);
    }
    
    /**
     *
     * @param event
     */
    @Override
    public void actionPerformed(ActionEvent event)
    {
        Object source = event.getSource();
        
        // Button Listenters
        if (source == buttons[0])
        {
            createPlayer();
            //JOptionPane.showMessageDialog(null, gender + "; " + playerType);
        }
        if (source == buttons[1])
        {
            clearDetails();
        }
        if (source == buttons[2])
        {
            returnToMenu();
        }
        
        // radio button listeners
        if (source == radMale)
        {
            gender = "Male";
        }
        if (source == radFemale)
        {
            gender = "Female";
        }
        if (source == radAmateur)
        {
            playerType = 1;
            txtEarnings.setVisible(false);
            lblEarnings.setVisible(false);
        }
        if (source == radPro)
        {
            playerType = 2;
            txtEarnings.setVisible(true);
            lblEarnings.setVisible(true);
        }
    }
}
