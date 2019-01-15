package GUI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * CreateEvent.java
 * 03/10/2017
 * @author Sam
 */

public class CreateEvent extends JFrame implements ActionListener
{
    // Values for Size of Frame
    private final int X_LENGTH = 500;
    private final int Y_LENGTH = 300;
    
    JLabel title = new JLabel("Create Event");
    Font titleFont = new Font("Arial", Font.BOLD, 22);
    
    //Panels
    JPanel[] layer1 = new JPanel[3];
    
    // Buttons
    JButton[] buttons = new JButton[3];
    
    // First menu
    MainMenu mainMenu;
    
    // 
    public CreateEvent(MainMenu tempMenu)
    {
        super("Create Event");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(200, 100, X_LENGTH, Y_LENGTH);
        setVisible(true);
        setLayout(new BorderLayout());
        mainMenu = tempMenu;
        
        createPanels();
        createButtons();
        setupPanels();
        
        layer1[0].add(title);
        title.setFont(titleFont);
        
        
        setupDetails();
        setupButtons();
    }
    
    public final void createPanels()
    {
        for (int i = 0; i < layer1.length; ++i)
        {
            layer1[i] = new JPanel();
        }
    }
    
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
    
    public final void setupPanels()
    {
        this.add(layer1[0], BorderLayout.NORTH);
        this.add(layer1[1], BorderLayout.CENTER);
        this.add(layer1[2], BorderLayout.SOUTH);
        
    }
    
    public final void setupDetails()
    {
        layer1[1].setLayout(new GridLayout(5,2));
        layer1[1].setBorder(BorderFactory.createTitledBorder("Event Details"));
        layer1[1].add(new JLabel("Event Name"));
        layer1[1].add(new JTextField());
        layer1[1].add(new JLabel("Nerd"));
        layer1[1].add(new JLabel("Nerd"));
        layer1[1].add(new JLabel("Nerd"));
        layer1[1].add(new JLabel("Nerd"));
    }
    
    public final void setupButtons()
    {
        for (int i = 0; i < buttons.length; ++i)
        {
            layer1[2].add(buttons[i]);
            buttons[i].addActionListener(this);
        }
    }
    
    public void createEvent()
    {
        
    }
    
    public void clearDetails()
    {
        
    }
    
    @Override
    public void actionPerformed(ActionEvent event)
    {
        Object source = event.getSource();
        
        if (source == buttons[0])
        {
            createEvent();
        }
        if (source == buttons[1])
        {
            clearDetails();
        }
        if (source == buttons[2])
        {
            this.dispose();
            mainMenu.setVisible(true);
        }
    }
}
