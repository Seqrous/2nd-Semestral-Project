package UI;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import Model.OrderLine;
import Model.ProductDescription;

public class OrderLineListTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private List<OrderLine> data;
	private static final String[] COL_NAMES = { "Name", "Quantity", "Material"};

	public OrderLineListTableModel() {
		setData(null);
	}

	public OrderLineListTableModel(List<OrderLine> data) {
		setData(data);
	}
	
	public int getColumnCount() {
		return COL_NAMES.length;
	}

	public int getRowCount() {
		return data.size();
	}
	
	public void setData(List<OrderLine> data) {
		if (data != null) {
			this.data = data;
		} else {
			this.data = new ArrayList<>(0);
		}
		super.fireTableDataChanged();
	}
	
	public OrderLine getOrderLineOfRow(int index) {
		if (index < 0 || index >= data.size()) {
			return null;
		}
		return this.data.get(index);
	}

	public Object getValueAt(int row, int column) {
		OrderLine o = data.get(row);
		switch (column) {
		case 0:
			return o.getProduct().getName();
		case 1:
			return o.getAmount();
		case 2:
			return o.getProduct().getMaterial().getName();
		default:
			return "UNKNOLWN COL NAME";
		}
	}

}
