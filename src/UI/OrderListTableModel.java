package UI;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import Model.Order;

public class OrderListTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private List<Order> data;
	private static final String[] COL_NAMES = { "ID", "State"};
	
	public OrderListTableModel() {
		setData(null);
	}

	public OrderListTableModel(List<Order> data) {
		setData(data);
	}
	
	public int getColumnCount() {
		return COL_NAMES.length;
	}

	public int getRowCount() {
		return data.size();
	}
	
	public void setData(List<Order> data) {
		if (data != null) {
			this.data = data;
		} else {
			this.data = new ArrayList<>(0);
		}
		super.fireTableDataChanged();
	}
	
	public Order getOrderOfRow(int index) {
		if (index < 0 || index >= data.size()) {
			return null;
		}
		return this.data.get(index);
	}

	public Object getValueAt(int row, int column) {
		Order o = data.get(row);

		switch (column) {
		case 0:
			if(o!=null) {
				return o.getId();
			}
			else {
				return "";
			}
		case 1:
			if(o!=null) {
				return o.getState();
			}
			else {
				return "";
			}
		default:
			return "UNKNOLWN COL NAME";
		}
	}

}
