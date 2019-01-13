/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.sowada.fileUploader;

/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
 
import java.awt.*;
import java.awt.event.*;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.KeyStroke;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
 
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
 
/* FileUploader.java requires images/middle.gif. */
 
/*
 * This class exists solely to show you what menus look like.
 * It has no menu-related event handling.
 */
public class FileUploader extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	JTextArea output;
    JScrollPane scrollPane;
    JFileChooser fileChooser;
    JMenuBar menuBar;
    JMenu menuFile;
    JMenuItem menuItemFileOpen;
    JPanel contentPane;
//    static JFrame frame;

    static FileUploader fileUploader;// = new FileUploader();
 
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
 
    public Container createContentPane() {
        //Create the content-pane-to-be.
        contentPane = new JPanel(new BorderLayout());
        contentPane.setOpaque(true);
 
        //Create a scrolled text area.
        output = new JTextArea(5, 30);
        output.setEditable(false);
        scrollPane = new JScrollPane(output);
 
        //Add the text area to the content pane.
        contentPane.add(scrollPane, BorderLayout.CENTER);
 
        return contentPane;
    }
 
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
    	JFrame frame = new JFrame("FileUploader");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Create and set up the content pane.
        fileUploader = new FileUploader();
//        FileUploader fileUploader = new FileUploader();
        frame.setJMenuBar(fileUploader.createMenuBar());
        frame.setContentPane(fileUploader.createContentPane());
 
        //Display the window.
//              frame.pack();
        frame.setSize(450, 260);
        frame.setVisible(true);
        
//                //Create and set up the window.
//                JFrame frame = new JFrame("FileChooserDemo");
//                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         
//                //Add content to the window.
//                frame.add(new FileChooserDemo());
//         
//                //Display the window.
//                frame.pack();
//                frame.setVisible(true);
    }
     public void actionPerformed(ActionEvent e) {
 
        //Handle open button action.
        if (e.getSource() == menuItemFileOpen) {
            int returnVal = fileChooser.showOpenDialog(contentPane); //fileUploader.g());//.getComponent(0));//contentPane); //frame.getComponent(0));//.getContentPane());
            
//            if (returnVal == JFileChooser.APPROVE_OPTION) {
//                File file = fc.getSelectedFile();
//                //This is where a real application would open the file.
//                log.append("Opening: " + file.getName() + "." + newline);
//            } else {
//                log.append("Open command cancelled by user." + newline);
//            }
//            log.setCaretPosition(log.getDocument().getLength());
        } 
        System.out.println("something clicked in menu");
    }
     
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}