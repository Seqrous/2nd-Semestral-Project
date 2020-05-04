package UI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import Controllers.DataAccessException;
import Controllers.ProductController;
import Model.Company;
import Model.ProductDescription;

public class AddProductFrame extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JLabel lblListOfProducts;
	private JScrollPane scrollPane;
	private JButton btnAddProduct;
	private JButton btnCancel;
	
	private ProductListTableModel productListTableModel;
	private JPanel panel_1;
	
	private ProductController productCtrl;

	public AddProductFrame() {
		
		this.setSize(458, 276);
		this.setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		panel.setLayout(null);
		getContentPane().add(panel);
		
		lblListOfProducts = new JLabel("List of products");
		lblListOfProducts.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblListOfProducts.setBounds(73, 13, 145, 22);
		panel.add(lblListOfProducts);
		
		btnAddProduct = new JButton("Add Product");
		btnAddProduct.setBounds(292, 33, 136, 48);
		panel.add(btnAddProduct);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(292, 168, 136, 48);
		btnCancel.addActionListener(this::btnCancelClicked);
		panel.add(btnCancel);
		
		panel_1 = new JPanel();
		panel_1.setBounds(12, 48, 257, 168);
		panel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setColumnHeaderView(table);
		scrollPane.setViewportView(table);
		
		init();
	}

	private void init() {
		
		try {
			productCtrl = new ProductController();
		} catch (DataAccessException e) {
			errorMessageException("Loading controller", "Failed to load the controllers" , e);
			//e.printStackTrace();
		}
		
		this.productListTableModel = new ProductListTableModel();
		this.table.setModel(productListTableModel);
		table.getColumnModel().getColumn(0).setHeaderValue("Name");
		table.getColumnModel().getColumn(1).setHeaderValue("Material");
		table.getColumnModel().getColumn(2).setHeaderValue("Price");
		showProductList();
	}
	
	private void showProductList() {
		List<ProductDescription> productList = null;
		
		try {
			productList = productCtrl.findAll();
		} catch (DataAccessException e) {
			errorMessageException("All products", "Failed to retrieve all products", e);
			//e.printStackTrace();
		}
		
		if(productList == null) {
			JOptionPane.showMessageDialog(this, "No products have been found");
		}
		
		productListTableModel.setData(productList);
	}
	
	protected JButton getAddProductButton() {
		return btnAddProduct;
	}
	
	private void btnCancelClicked(ActionEvent e) {
		this.dispose();
	}
	
	protected ProductDescription getCurrentProduct() {
		
		int selectedRow = this.table.getSelectedRow();
		ProductDescription currProduct = null;
		if (selectedRow > -1) {
			currProduct = this.productListTableModel.getProductOfRow(selectedRow);
		}
		return currProduct;
	}
	
	private void errorMessage(String title, String text) {
		JOptionPane.showMessageDialog(this, text + " ", title, JOptionPane.OK_OPTION);
	}
	
	private void errorMessageException(String title, String text, Exception e) {
		JOptionPane.showMessageDialog(this, text + " (" + e.getMessage() + ") ", title, JOptionPane.OK_OPTION);
	}
}
