package GUI;


import Classes.Player;
import Classes.ResultsTableModel;
import Utilities.DatabaseMethods;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * ResultTable.java
 * 03/11/2017
 * @author Sam
 */
public class ResultTable extends JFrame implements ActionListener
{
    // Values for Size of Frame
    private final int X_LENGTH = 900;
    private final int Y_LENGTH = 300;
    
    // Value for position of Frame
    private final int X_POS = 500;
    private final int Y_POS = 200;
    
    // row Selected
    private int selectedRow;
    
    //Panels
    JPanel pnlButtons = new JPanel(new GridLayout(1, 2));
    
    //Buttons
    JButton btnUpdate = new JButton("Update");
    JButton btnDelete = new JButton("Delete");
    
    // Table
    JTable tblPlayer = new JTable();
    // add table to the Scrollpane constructor
    JScrollPane scroll = new JScrollPane(tblPlayer, 
                        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    Container con = getContentPane();
    
    ResultsTableModel resModel;
    SearchPlayer parent;
    
    /**
     * Constructor
     * @param frame
     * @param model
     */
    public ResultTable(SearchPlayer frame, ResultsTableModel model)
    {
        super("Players");
        this.setVisible(false);
        this.setBounds(X_POS, Y_POS, X_LENGTH, Y_LENGTH);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        parent = frame;
        resModel = model;
        tblPlayer.setModel(resModel);
        tblPlayer.setAutoCreateRowSorter(true);
        
        con.add(scroll, BorderLayout.CENTER);
        con.add(pnlButtons, BorderLayout.SOUTH);
        
        //pnlButtons.setLayout(new GridLayout(1,2));
        pnlButtons.add(btnUpdate);
        pnlButtons.add(btnDelete);
        
        // make table only able to select 1 row
        tblPlayer.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        ListSelectionModel rowSM = tblPlayer.getSelectionModel();
        rowSM.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e){
                ListSelectionModel lsm = (ListSelectionModel) e.getSource();
                selectedRow = lsm.getMinSelectionIndex();
            }
        });
        
        validate();
        btnUpdate.addActionListener(this);
        btnDelete.addActionListener(this);
    }
    
    /**
     *
     * @param ev
     */
    @Override
    public void actionPerformed(ActionEvent ev)
    {
        Object source = ev.getSource();
        
        if (source == btnUpdate)
        {
            // open UpdateFrame
            Player p = resModel.getRow(selectedRow);
            UpdatePlayer updateFrame = new UpdatePlayer(p, parent, this, resModel);
            // Hide other frames
            this.setVisible(false);
            parent.setVisible(false);
        }
        if (source == btnDelete)
        {
            Player p = resModel.getRow(selectedRow);
            int input = JOptionPane.showConfirmDialog(null, "Delete " + 
                    p.getFirstName() + " " + p.getLastName() + "'s Record"
                    , "Delete A Record",  JOptionPane.YES_NO_OPTION);
            if (input == 0)
            {
                //Delete Record
                DatabaseMethods.deletePlayer(p);
                //refresh table model
                resModel.getDataFromDatabase();
                resModel.fireTableRowsDeleted(selectedRow, selectedRow);
            }
        }
    }
}
