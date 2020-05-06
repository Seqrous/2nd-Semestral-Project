package UI;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import Model.Company;
import Model.Order;
import Model.ProductDescription;

public class ProductListTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private List<ProductDescription> data;
	private static final String[] COL_NAMES = { "Name", "Material", "Price"};

	public ProductListTableModel() {
		setData(null);
	}

	public ProductListTableModel(List<ProductDescription> data) {
		setData(data);
	}
	
	@Override
	public int getColumnCount() {
		return COL_NAMES.length;
	}

	@Override
	public int getRowCount() {
		return data.size();
	}
	
	public void setData(List<ProductDescription> data) {
		if (data != null) {
			this.data = data;
		} else {
			this.data = new ArrayList<>(0);
		}
		super.fireTableDataChanged();
	}
	
	public ProductDescription getProductOfRow(int index) {
		if (index < 0 || index >= data.size()) {
			return null;
		}
		return this.data.get(index);
	}

	@Override
	public Object getValueAt(int row, int column) {
		ProductDescription p = data.get(row);
		switch (column) {
		case 0:
			return p.getName();
		case 1:
			return p.getMaterial().getName();
		case 2:
			return p.getPrice();
		default:
			return "UNKNOLWN COL NAME";
		}
	}


}
