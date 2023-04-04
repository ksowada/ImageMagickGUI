package eu.sowada.fileUploader;

import javax.swing.JList;
import javax.swing.ListSelectionModel;

/**
 * extends JList for a custom ScriptList
 * @author Karl Sowada
 * @version 0.1
 * @since 180113
 */
public class ScriptList extends JList{
	
	
	public ScriptList(Object[] listItems) {
		super(listItems);
//		JList list = new JList(listItems); //data has type Object[]
		this.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		this.setLayoutOrientation(JList.VERTICAL);
		this.setVisibleRowCount(-1);

//		JScrollPane listScroller = new JScrollPane(list);
//		listScroller.setPreferredSize(new Dimension(250, 80));
//		return list;
	}

}
