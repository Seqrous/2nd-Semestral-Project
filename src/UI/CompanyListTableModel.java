package UI;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import Model.Company;

public class CompanyListTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private List<Company> data;
	private static final String[] COL_NAMES = { "ID", "Name", "E-mail" };

	public CompanyListTableModel() {
		setData(null);
	}

	public CompanyListTableModel(List<Company> data) {
		setData(data);
	}
	
	@Override
	public int getColumnCount() {
		return COL_NAMES.length;
	}
	
	public String getColumnName(int column) {
		return COL_NAMES[column];
	}
	
	public void setData(List<Company> data) {
		if (data != null) {
			this.data = data;
		} else {
			this.data = new ArrayList<>(0);
		}
		super.fireTableDataChanged();
	}
	
	public Company getCompanyOfRow(int index) {
		if (index < 0 || index >= data.size()) {
			return null;
		}
		return this.data.get(index);
	}

	@Override
	public Object getValueAt(int row, int column) {
		Company c = data.get(row);
		switch (column) {
		case 0:
			return (int)c.getId();
		case 1:
			return c.getName();
		case 2:
			return c.getEmail();
		default:
			return "UNKNOLWN COL NAME";
		}
	}
	
	@Override
	public int getRowCount() {
		return data.size();
	}

}
