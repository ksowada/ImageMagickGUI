package eu.sowada.fileUploader;

import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.table.AbstractTableModel;

public class ScriptTableModel extends AbstractTableModel {
    
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
    
    private Object[][] data;
    
    public ScriptTableModel(ArrayList<TreeMap<String, String>> scriptsMap) {
    	
        data = new Object[scriptsMap.size()][columnNames.length];
        // go by Script
//        for (TreeMap scriptMap : scriptsMap) {
//	        for (String columnName : columnNames) {

        for (int scriptIx = 0; scriptIx<scriptsMap.size(); scriptIx++) {
	        for (int columnIx = 0; columnIx<columnNames.length; columnIx++) {
	        	data[scriptIx][columnIx] = scriptsMap.get(scriptIx).get(columnNames[columnIx]);
	        }
        }
    }

    
    public String getColumnName(int col) {
        return columnNames[col];
    }
    
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return data.length;
	}
	/** define editable Cells */
    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        if (col < 2) {
            return false;
        } else {
            return true;
        }
    }
	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return data[arg0][arg1];
	}

}
