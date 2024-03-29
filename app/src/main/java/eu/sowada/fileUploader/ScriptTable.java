package eu.sowada.fileUploader;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableModel;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.TreeMap;

public class ScriptTable extends JPanel {
    private boolean DEBUG = false;

    private final JTable table;
	private ScriptTableModel scriptTableModel;
    
    private static final String[] columnNames = { 
        "name"             ,
        "outFile"          ,
        "example"          ,
        "command"          ,
        "operator"         ,
        "setting"          ,
        "channelOperator"  ,
        "sequenceOperator" ,
        "geometry"         };

    public ScriptTable(ArrayList<TreeMap<String, String>> scriptsMap) {
        super(new GridLayout(1,0));

//        String[] columnNames = {"First Name",
//                                "Last Name",
//                                "Sport",
//                                "# of Years",
//                                "Vegetarian"};
//        String[] columnNames = scriptsMap.

//        Object[][] data = new Object[scriptsMap.size()][columnNames.length];
        // go by Script
//        for (TreeMap scriptMap : scriptsMap) {
//	        for (String columnName : columnNames) {

//        for (int scriptIx = 0; scriptIx<scriptsMap.size(); scriptIx++) {
//	        for (int columnIx = 0; columnIx<columnNames.length; columnIx++) {
//	        	data[scriptIx][columnIx] = scriptsMap.get(scriptIx).get(columnNames[columnIx]);
//	        }
//        }

//        Object[][] data = {
//	    {"Kathy", "Smith",
//	     "Snowboarding", new Integer(5), new Boolean(false)},
//	    {"John", "Doe",
//	     "Rowing", new Integer(3), new Boolean(true)},
//	    {"Sue", "Black",
//	     "Knitting", new Integer(2), new Boolean(false)},
//	    {"Jane", "White",
//	     "Speed reading", new Integer(20), new Boolean(true)},
//	    {"Joe", "Brown",
//	     "Pool", new Integer(10), new Boolean(false)}
//        };

//        final JTable table = new JTable(data, columnNames);
        scriptTableModel = new ScriptTableModel(scriptsMap);
        table = new JTable(scriptTableModel) {

	        //Implement table cell tool tips.           
	        public String getToolTipText(MouseEvent e) {
	            String tip = null;
	            java.awt.Point p = e.getPoint();
	            int rowIndex = rowAtPoint(p);
	            int colIndex = columnAtPoint(p);
	
	            try {
	                tip = getValueAt(rowIndex, colIndex).toString();
	            } catch (RuntimeException e1) {
	                //catch null pointer exception if mouse is over an empty line
	            }
	
	            return tip;
	        }
        };
        
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        
        // allow multiple Scripts to be Selected
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        
        // allow sorting for each column
        table.setAutoCreateRowSorter(true);

        if (DEBUG) {
            table.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    printDebugData(table);
                }
            });
        }

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        add(scrollPane);
    }

    private void printDebugData(JTable table) {
        int numRows = table.getRowCount();
        int numCols = table.getColumnCount();
        javax.swing.table.TableModel model = table.getModel();

        System.out.println("Value of data: ");
        for (int i=0; i < numRows; i++) {
            System.out.print("    row " + i + ":");
            for (int j=0; j < numCols; j++) {
                System.out.print("  " + model.getValueAt(i, j));
            }
            System.out.println();
        }
        System.out.println("--------------------------");
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
    
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("SimpleTableDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Create and set up the content pane.
        ScriptTable newContentPane = new ScriptTable();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    } */
}