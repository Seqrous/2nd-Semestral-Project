package UI;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

import Model.Material;

public class MaterialCellRenderer extends DefaultListCellRenderer {

	private static final long serialVersionUID = 1L;
	private DefaultListCellRenderer dlcr = new DefaultListCellRenderer();

	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		String currentMaterial = (String) value;
		String representation = "";
		if (currentMaterial != null) {
			representation = currentMaterial;
		}
		JLabel renderer = (JLabel) dlcr.getListCellRendererComponent(list, representation, index, isSelected,
				cellHasFocus);
		return renderer;
		
	}
}
