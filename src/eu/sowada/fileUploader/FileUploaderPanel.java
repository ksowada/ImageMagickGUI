package eu.sowada.fileUploader;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class FileUploaderPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1042945819029455828L;
	static private final String newline = "\n";
	JTextArea log;
	JFileChooser fileChooser;
	
    JMenuBar menuBar;
    JMenu menuFile;
    JMenuItem menuItemFileOpen;

	public FileUploaderPanel() {
		super(new BorderLayout());

//Create the log first, because the action listeners
//need to refer to it.
		log = new JTextArea(5, 20);
		log.setMargin(new Insets(5, 5, 5, 5));
		log.setEditable(false);
		JScrollPane logScrollPane = new JScrollPane(log);

//Create a file chooser
		fileChooser = new JFileChooser();
		fileChooser.setMultiSelectionEnabled(true);

		add(logScrollPane, BorderLayout.CENTER);
	}

    public JMenuBar createMenuBar() {
    	 
        //Create the menu bar.
        menuBar = new JMenuBar();
 
        //Build the first menu.
        menuFile = new JMenu("File");
        menuFile.setMnemonic(KeyEvent.VK_F);
        menuFile.getAccessibleContext().setAccessibleDescription(
                "File Menu open and save static Files");
        menuBar.add(menuFile);
 
        //a group of JMenuItems
        menuItemFileOpen = new JMenuItem("open", KeyEvent.VK_O);
        menuItemFileOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        menuItemFileOpen.getAccessibleContext().setAccessibleDescription("Select Files");
        menuItemFileOpen.addActionListener(this);
        menuFile.add(menuItemFileOpen);
        
        menuBar.add(menuFile);
 
        return menuBar;
    }

	public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menuItemFileOpen) {
            int returnVal = fileChooser.showOpenDialog(FileUploaderPanel.this); //fileUploader.g());//.getComponent(0));//contentPane); //frame.getComponent(0));//.getContentPane());
            
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File[] files = fileChooser.getSelectedFiles();

                for (File file : files) {
                //This is where a real application would open the file.
                log.append("" + file.getName() + "" + newline);
                }
            } else {
                log.append("Open command cancelled by user." + newline);
            }
            log.setCaretPosition(log.getDocument().getLength());

            System.out.println("something clicked in menuItemFileOpen");
        } 
	}

	/** Returns an ImageIcon, or null if the path was invalid. */
	protected static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = FileUploaderPanel.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be invoked
	 * from the event dispatch thread.
	 */
	private static void createAndShowGUI() {
//Create and set up the window.
		JFrame frame = new JFrame("FileChooserDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//Add content to the window.
		JPanel fileUploaderPanel = new FileUploaderPanel();
		frame.add(fileUploaderPanel);
        frame.setJMenuBar(((FileUploaderPanel) fileUploaderPanel).createMenuBar());

//Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
//Schedule a job for the event dispatch thread:
//creating and showing this application's GUI.
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
//Turn off metal's use of bold fonts
				UIManager.put("swing.boldMetal", Boolean.FALSE);
				createAndShowGUI();
			}
		});
	}
}