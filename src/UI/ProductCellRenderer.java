package UI;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

import Model.ProductDescription;

public class ProductCellRenderer extends DefaultListCellRenderer{

	private static final long serialVersionUID = 1L;
	
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		
		ProductDescription currentProduct = (ProductDescription) value;
		String toDisplay = "";
		
		if(currentProduct != null) {
			toDisplay = String.format("[%s] %s", (int) currentProduct.getId(), currentProduct.getName());
		}
		return new JLabel(toDisplay);
	}
}
