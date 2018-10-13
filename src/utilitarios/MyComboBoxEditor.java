package utilitarios;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class MyComboBoxEditor extends DefaultCellEditor {
        
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyComboBoxEditor(String[] items) {
			super(new JComboBox(items));
		}
	}


