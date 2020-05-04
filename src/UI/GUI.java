package UI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Controllers.CompanyController;
import Controllers.DataAccessException;
import Controllers.MaterialController;
import Controllers.OrderController;
import Controllers.OrderLineController;
import Controllers.ProductController;
import Model.Company;
import Model.Material;
import Model.Order;
import Model.OrderLine;
import Model.ProductDescription;

public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JTextField txtCompanysName;
	private JTextField txtEmail;
	private JTextField txtPhoneNumber;
	private JTextField txtCurrentCompany;
	private JTextField textSearchFieldCompany;
	private JTextField textOrderDiscount;
	
	private CardLayout cl;
	private JPanel contentPane;
	private JPanel contentPane3;
	private JPanel contentPane2;
	private JPanel contentPane1;
	private JPanel contentPane4;
	
	private JPanel panel_6;
	private JPanel panel_5;
	private JPanel panel_4;
	private JPanel panel_3;
	private JPanel panel_2;
	private JPanel panel_1;
	private JPanel panel;
	
	private AddProductFrame addProductFrame;
	private JMenuBar menuBar;
	private JButton btnFindCompany;
	private JButton btnInsertCompany ;
	private JButton addProductButtonFrame;
	private JButton btnInsertProduct;
	private JButton btnApplyChangesProduct;
	private JButton btnGoToStorage;
	private JButton btnAddProductOrder;
	private JButton btnRemoveProductOrder;
	private JButton btnApplyChangesOrder;
	private JButton btnBackOrder;
	private JButton btnGoToOrders;
	private JButton btnApplyChangesCompany;
	private JButton btnBackProduct;
	private AbstractButton btnGoToProducts;
	private JSpinner spinnerOrder;
	private JLabel lblDiscountOrder;
	private JLabel lblOrdersTotalOrder;
	private JLabel lblIdOrder;
	private JLabel lblNameOrder;
	private JLabel lblPriceOrder;
	private JLabel lblAmountOrder;
	private JLabel lblProductFullPriceOrder;
	private JLabel lblIdProductDisplay;
	private JLabel lblListOfProducts;
	private JLabel lblIdProduct;
	private JLabel lblNameProduct;
	private JLabel lblPriceProduct;
	private JLabel lblMaterialProduct;
	private JLabel lblNameCompany;
	private JLabel labelPercentage;
	private JTextArea textAreaNameProduct;
	private JTextArea textAreaPriceProduct;
	private JTextArea txtrAddNewCustomer;
	private JList<ProductDescription> listProducts;
	private JComboBox<String> comboBoxMaterial;
	private JSpinner spinnerHorizontal;
	private JSpinner spinnerVertical;
	
	private JMenu mnHelp;
	private JMenu menu;
	private JMenuItem mntmSendCrashReport;
	private JMenuItem mntmSoftwareInformation;
	
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_2;
	private JTable table;
	private JTable table_1;
	private JTable table_2;

	private OrderController orderCtrl;
	private OrderLineController orderLineCtrl;
	private CompanyController companyCtrl;
	private ProductController productCtrl;
	private MaterialController materialCtrl;
	
	private CompanyListTableModel companyListTableModel;
	private OrderListTableModel orderListTableModel;
	private OrderLineListTableModel orderLineListTableModel;
	
	private Company chosenCompany;
	
	private JButton[][] buttonTable;
	
	private static final Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	private static final Pattern companyNamePattern = Pattern.compile("^[a-zA-Z0-9&]*$", Pattern.CASE_INSENSITIVE);
	private static final Pattern phoneNumberPattern = Pattern.compile("\\d{8,10}");
	
	
	private JTextField textFieldOrderId;
	private JTextField textFieldOrderName;
	private JTextField textFieldProductPrice;
	private JTextField textFieldProductFullPrice;
	private JTextField textFieldOrderTotal;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //GUI frame setup
		this.setSize(800,420);
		this.setLocationRelativeTo(null);
		
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		cl = new CardLayout();
		contentPane.setLayout(cl);
		contentPane2 = new JPanel();
		contentPane3 = new JPanel();
		contentPane4 = new JPanel();
		contentPane1 = new JPanel();
		
		contentPane.add(contentPane1, "1");
		contentPane.add(contentPane2, "2");
		contentPane.add(contentPane3, "3");
		contentPane.add(contentPane4, "4");
		contentPane1.setLayout(null);
		contentPane2.setLayout(null);
		contentPane3.setLayout(null);
		contentPane4.setLayout(null);
		
		panel_6 = new JPanel();
		panel_6.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_6.setBounds(12, 26, 758, 232);
		panel_6.setLayout(null);
		contentPane4.add(panel_6);
		
		JLabel lblColumns = new JLabel("Columns:");
		lblColumns.setBounds(459, 266, 88, 25);
		contentPane4.add(lblColumns);
		
		JLabel lblRows = new JLabel("Rows:");
		lblRows.setBounds(597, 266, 88, 25);
		contentPane4.add(lblRows);
		
		spinnerHorizontal = new JSpinner();
		spinnerHorizontal.setBounds(526, 267, 41, 24);
		spinnerHorizontal.addChangeListener(new ChangeListener() {
			
			public void stateChanged(ChangeEvent e) {
				contentPane4ButtonBuild((int)spinnerHorizontal.getValue(), (int)spinnerVertical.getValue());
			}
		});
		
		contentPane4.add(spinnerHorizontal);
		
		spinnerVertical = new JSpinner();
		spinnerVertical.setBounds(644, 267, 41, 24);
		spinnerVertical.addChangeListener(new ChangeListener() {
			
			public void stateChanged(ChangeEvent e) {
				contentPane4ButtonBuild((int)spinnerHorizontal.getValue(), (int)spinnerVertical.getValue());
			}
		});
		
		contentPane4.add(spinnerVertical);
		
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 782, 26);
		contentPane1.add(menuBar);
		
		panel = new JPanel();
		panel.setBounds(0, 25, 782, 344);
		contentPane1.add(panel);
		panel.setLayout(null);
		
		mnHelp = new JMenu("Help...");
		menuBar.add(mnHelp);
		
		mntmSendCrashReport = new JMenuItem("Give feedback");
		mnHelp.add(mntmSendCrashReport);
		
		menu = new JMenu("About...");
		menuBar.add(menu);
		
		mntmSoftwareInformation = new JMenuItem("Software information");
		menu.add(mntmSoftwareInformation);
		
		panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(394, 57, 369, 230);
		panel_2.setLayout(new BorderLayout(0, 0));
		panel.add(panel_2);
		
		table = new JTable();
		table.setVisible(true);
		table.setSize(50, 50);
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		panel_2.add(scrollPane, BorderLayout.CENTER);
		
		
		panel_1 = new JPanel();
		panel_1.setBounds(12, 23, 370, 308);
		panel_1.setLayout(null);
		panel.add(panel_1);
		
		txtrAddNewCustomer = new JTextArea();
		txtrAddNewCustomer.setBackground(UIManager.getColor("Button.background"));
		txtrAddNewCustomer.setFont(new Font("Arial", Font.BOLD, 19));
		txtrAddNewCustomer.setText("Edit or insert a new customer");
		txtrAddNewCustomer.setBounds(51, 13, 273, 24);
		panel_1.add(txtrAddNewCustomer);
		
		
		txtCompanysName = new JTextField();
		txtCompanysName.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {
				if(txtCompanysName.getText().equals(""))
				txtCompanysName.setText("Company's name");
			}

			public void focusGained(FocusEvent e) {
				if(txtCompanysName.getText().equals("Company's name")) {
					txtCompanysName.setText("");
				}
			}
		});
		txtCompanysName.setText("Company's name");
		txtCompanysName.setBounds(51, 50, 273, 30);
		panel_1.add(txtCompanysName);
		
		
		txtEmail = new JTextField();
		txtEmail.setText("E-mail");
		txtEmail.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {
				if(txtEmail.getText().equals(""))
				txtEmail.setText("E-mail");
			}

			public void focusGained(FocusEvent e) {
				if(txtEmail.getText().equals("E-mail")) {
					txtEmail.setText("");
				}
			}
		});	
		txtEmail.setColumns(10);
		txtEmail.setBounds(51, 99, 273, 30);
		panel_1.add(txtEmail);
		
		txtPhoneNumber = new JTextField();
		txtPhoneNumber.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {
				if(txtPhoneNumber.getText().equals(""))
				txtPhoneNumber.setText("Phone Number");
			}

			public void focusGained(FocusEvent e) {
				if(txtPhoneNumber.getText().equals("Phone Number")) {
					txtPhoneNumber.setText("");
				}
			}
		});
		
		txtPhoneNumber.setText("Phone Number");
		txtPhoneNumber.setColumns(10);
		txtPhoneNumber.setBounds(51, 147, 273, 30);
		panel_1.add(txtPhoneNumber);
		
		btnInsertCompany = new JButton("Insert");
		btnInsertCompany.setBounds(51, 203, 131, 44);
		btnInsertCompany.addActionListener(this::insertButtonClicked);
		panel_1.add(btnInsertCompany);
		
		
		btnApplyChangesCompany = new JButton("Apply changes");
		btnApplyChangesCompany.setBounds(194, 203, 131, 44);
		btnApplyChangesCompany.addActionListener(this::applyChangesClicked);
		panel_1.add(btnApplyChangesCompany);
		
		btnFindCompany = new JButton("Find");
		btnFindCompany.setBounds(691, 12, 72, 33);
		btnFindCompany.addActionListener(this::findButtonClicked);
		panel.add(btnFindCompany);
		
		textSearchFieldCompany = new JTextField();
		textSearchFieldCompany.setBounds(470, 13, 209, 31);
		panel.add(textSearchFieldCompany);
		textSearchFieldCompany.setColumns(10);
		
		lblNameCompany = new JLabel("Name:");
		lblNameCompany.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNameCompany.setBounds(411, 13, 66, 31);
		panel.add(lblNameCompany);
		
		btnGoToOrders = new JButton("Go to orders...");
		btnGoToOrders.setBounds(612, 300, 151, 31);
		btnGoToOrders.addActionListener(this::goToOrdersClicked);
		panel.add(btnGoToOrders);
		
		btnGoToProducts = new JButton("Go to products...");
		btnGoToProducts.addActionListener(this::goToProductsClicked);
		btnGoToProducts.setBounds(394, 300, 151, 31);
		panel.add(btnGoToProducts);
	
		
		panel_5 = new JPanel();
		panel_5.setBounds(0, 25, 782, 348);
		contentPane3.add(panel_5);
		panel_5.setLayout(null);
		
		listProducts = new JList<>();
		listProducts.setBounds(12, 58, 197, 235);
		listProducts.setSelectionMode(1);
		panel_5.add(listProducts);
		
		lblListOfProducts = new JLabel("List of products");
		lblListOfProducts.setHorizontalAlignment(SwingConstants.CENTER);
		lblListOfProducts.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblListOfProducts.setBounds(12, 13, 197, 32);
		panel_5.add(lblListOfProducts);
		
		lblIdProduct = new JLabel("ID:");
		lblIdProduct.setBounds(221, 59, 66, 26);
		panel_5.add(lblIdProduct);
		
		lblNameProduct = new JLabel("Name: ");
		lblNameProduct.setBounds(221, 98, 66, 26);
		panel_5.add(lblNameProduct);
		
		lblPriceProduct = new JLabel("Price: ");
		lblPriceProduct.setBounds(221, 137, 66, 32);
		panel_5.add(lblPriceProduct);
		
		lblMaterialProduct = new JLabel("Material: ");
		lblMaterialProduct.setBounds(221, 182, 66, 26);
		panel_5.add(lblMaterialProduct);
		
		comboBoxMaterial = new JComboBox<>();
		comboBoxMaterial.setBounds(278, 182, 148, 26);
		panel_5.add(comboBoxMaterial);
		
		textAreaNameProduct = new JTextArea();
		textAreaNameProduct.setBounds(278, 105, 148, 26);
		textAreaNameProduct.addFocusListener(new FocusListener() {

			String id;
			String name;
			
			public void focusGained(FocusEvent arg0) {
				id = lblIdProductDisplay.getText();
				name = textAreaNameProduct.getText();
				lblIdProductDisplay.setText("");
			}

			public void focusLost(FocusEvent arg0) {
				if(textAreaNameProduct.getText().equals(name)) {
					lblIdProductDisplay.setText(id);
				}
			}
		
		});
		panel_5.add(textAreaNameProduct);
		
		textAreaPriceProduct = new JTextArea();
		textAreaPriceProduct.setBounds(278, 143, 148, 26);
		panel_5.add(textAreaPriceProduct);
		
		btnInsertProduct = new JButton("Insert Product");
		btnInsertProduct.addActionListener(this::productInsertClicked);
		btnInsertProduct.setBounds(221, 297, 128, 38);
		panel_5.add(btnInsertProduct);
		
		btnApplyChangesProduct = new JButton("Apply Changes");
		btnApplyChangesProduct.addActionListener(this::productApplyChangesClicked);
		btnApplyChangesProduct.setBounds(349, 297, 123, 38);
		panel_5.add(btnApplyChangesProduct);
		
		lblIdProductDisplay = new JLabel("");
		lblIdProductDisplay.setBounds(278, 59, 148, 26);
		panel_5.add(lblIdProductDisplay);
		
		btnGoToStorage = new JButton("Go to storage...");
		btnGoToStorage.addActionListener(this::goToStorageClicked);
		btnGoToStorage.setBounds(571, 297, 123, 38);
		panel_5.add(btnGoToStorage);
		
		btnBackProduct = new JButton("Back...");
		btnBackProduct.addActionListener(this::backButtonClicked1);
		btnBackProduct.setBounds(52, 297, 100, 38);
		panel_5.add(btnBackProduct);

		
		txtCurrentCompany = new JTextField();
		txtCurrentCompany.setEditable(false);
		txtCurrentCompany.setBackground(UIManager.getColor("Button.background"));
		txtCurrentCompany.setText("Current company: ");
		txtCurrentCompany.setBounds(0, 25, 268, 22);
		contentPane2.add(txtCurrentCompany);
		txtCurrentCompany.setColumns(10);
		
		panel_3 = new JPanel();
		panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_3.setBounds(10, 60, 268, 246);
		contentPane2.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		scrollPane_1 = new JScrollPane();
		panel_3.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		panel_4 = new JPanel();
		panel_4.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_4.setBounds(495, 60, 268, 246);
		contentPane2.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		scrollPane_2 = new JScrollPane();
		panel_4.add(scrollPane_2);
		
		table_2 = new JTable();
		scrollPane_2.setViewportView(table_2);
		
		lblIdOrder = new JLabel("ID: ");
		lblIdOrder.setBounds(283, 60, 27, 31);
		contentPane2.add(lblIdOrder);
		
		lblNameOrder = new JLabel("Name:");
		lblNameOrder.setBounds(283, 95, 48, 31);
		contentPane2.add(lblNameOrder);
		
		lblPriceOrder = new JLabel("Price: ");
		lblPriceOrder.setBounds(283, 130, 37, 31);
		contentPane2.add(lblPriceOrder);
		
		spinnerOrder = new JSpinner();
		spinnerOrder.setBounds(393, 169, 77, 22);
		contentPane2.add(spinnerOrder);
		
		lblAmountOrder = new JLabel("Quantity:");
		lblAmountOrder.setBounds(281, 165, 61, 31);
		contentPane2.add(lblAmountOrder);
		
		lblProductFullPriceOrder = new JLabel("Product full price:");
		lblProductFullPriceOrder.setBounds(283, 200, 109, 31);
		contentPane2.add(lblProductFullPriceOrder);
		
		lblDiscountOrder = new JLabel("Order discount:");
		lblDiscountOrder.setBounds(283, 235, 89, 31);
		contentPane2.add(lblDiscountOrder);
		
		lblOrdersTotalOrder = new JLabel("Order's total:");
		lblOrdersTotalOrder.setBounds(283, 270, 89, 34);
		contentPane2.add(lblOrdersTotalOrder);
		
		btnAddProductOrder = new JButton("Add product...");
		btnAddProductOrder.setBounds(495, 319, 131, 37);
		btnAddProductOrder.addActionListener(this::btnAddProductClicked);
		contentPane2.add(btnAddProductOrder);
		
		btnRemoveProductOrder = new JButton("Remove product");
		btnRemoveProductOrder.addActionListener(this::removeButtonClicked);
		btnRemoveProductOrder.setBounds(632, 319, 131, 37);
		contentPane2.add(btnRemoveProductOrder);
		
		btnApplyChangesOrder = new JButton("Apply Changes");
		btnApplyChangesOrder.addActionListener(this::applyChangesClickedOrder);
		btnApplyChangesOrder.setBounds(320, 319, 131, 37);
		contentPane2.add(btnApplyChangesOrder);
		
		btnBackOrder = new JButton("Back...");
		btnBackOrder.addActionListener(this::backButtonClicked);
		btnBackOrder.setBounds(12, 319, 131, 37);
		contentPane2.add(btnBackOrder);
		
		textOrderDiscount = new JTextField();
		textOrderDiscount.setBounds(406, 239, 64, 22);
		contentPane2.add(textOrderDiscount);
		textOrderDiscount.setColumns(10);
		
		labelPercentage = new JLabel("%");
		labelPercentage.setBounds(474, 242, 56, 16);

		contentPane2.add(labelPercentage);
		
		textFieldOrderId = new JTextField();
		textFieldOrderId.setEditable(false);
		textFieldOrderId.setBounds(358, 64, 109, 22);
		contentPane2.add(textFieldOrderId);
		textFieldOrderId.setColumns(10);
		
		textFieldOrderName = new JTextField();
		textFieldOrderName.setEditable(false);
		textFieldOrderName.setColumns(10);
		textFieldOrderName.setBounds(358, 99, 109, 22);
		contentPane2.add(textFieldOrderName);
		
		textFieldProductPrice = new JTextField();
		textFieldProductPrice.setEditable(false);
		textFieldProductPrice.setColumns(10);
		textFieldProductPrice.setBounds(358, 134, 109, 22);
		contentPane2.add(textFieldProductPrice);
		
		textFieldProductFullPrice = new JTextField();
		textFieldProductFullPrice.setEditable(false);
		textFieldProductFullPrice.setColumns(10);
		textFieldProductFullPrice.setBounds(393, 204, 77, 22);
		contentPane2.add(textFieldProductFullPrice);
		
		textFieldOrderTotal = new JTextField();
		textFieldOrderTotal.setEditable(false);
		textFieldOrderTotal.setColumns(10);
		textFieldOrderTotal.setBounds(371, 279, 99, 22);
		contentPane2.add(textFieldOrderTotal);
		txtCompanysName.setColumns(10);
		
		
		contentPane4ButtonBuild((int)spinnerHorizontal.getValue(), (int)spinnerVertical.getValue());
		init();
	}

	private void init() {

		try {
			companyCtrl = new CompanyController();
			orderCtrl = new OrderController();
			orderLineCtrl = new OrderLineController();
			productCtrl = new ProductController();
			materialCtrl = new MaterialController();
		} catch (DataAccessException e) {
			errorMessageException("GUI initiation", "Failed to initiate GUI elements", e);
			//e.printStackTrace();
		}
		
		this.companyListTableModel = new CompanyListTableModel();
		this.table.setModel(companyListTableModel);
		
		this.orderListTableModel = new OrderListTableModel();
		this.table_1.setModel(orderListTableModel);
		table_1.getColumnModel().getColumn(0).setHeaderValue("ID");
		table_1.getColumnModel().getColumn(1).setHeaderValue("State");
		
		this.orderLineListTableModel = new OrderLineListTableModel();
		this.table_2.setModel(orderLineListTableModel);
		table_2.getColumnModel().getColumn(0).setHeaderValue("Name");
		table_2.getColumnModel().getColumn(1).setHeaderValue("Quantity");
		table_2.getColumnModel().getColumn(2).setHeaderValue("Material");
		
		this.listProducts.setCellRenderer(new ProductCellRenderer());
		
		this.comboBoxMaterial.setRenderer(new MaterialCellRenderer());
		
		this.table.getSelectionModel().addListSelectionListener((e) -> displayCompany(getCurrentCompany()));
		
		this.table_1.getSelectionModel().addListSelectionListener((e) -> displayOrder(getCurrentOrder()));
		
		this.table_2.getSelectionModel().addListSelectionListener((e) -> displayOrderLine(getCurrentOrderLine()));
		
		this.spinnerOrder.addChangeListener((e) -> updatePrices((Integer)spinnerOrder.getValue()));
		
		this.listProducts.addListSelectionListener((e) -> displayProduct(getListCurrentProduct()));
		
		showProductList();
		fillMaterialComboBox();
	}
	
	private void contentPane4ButtonBuild(int amountHor, int amountVer) {
		
		panel_6.removeAll();
		panel_6.updateUI();
		
		int amountHorizontally = amountHor;
		int amountVertically = amountVer;
		
		buttonTable = new JButton[amountHorizontally][amountVertically];
		
		int paneWidth = panel_6.getWidth();
		int paneHeight = panel_6.getHeight();
		
		int spaceBetween = 5;
		
		int buttonWidth = 0;
		int buttonHeight = 0;
		
		try {
			 buttonWidth = (paneWidth - (amountHorizontally + 1) * spaceBetween)/amountHorizontally;
			 buttonHeight = (paneHeight - (amountVertically + 1) * spaceBetween)/amountVertically;
		} catch (ArithmeticException e1) {
			
		}
		
		int firstX = spaceBetween;
		int firstY = spaceBetween;
		
		int counter = 1;
		for(int j = 1; j <= amountVertically; j++) {
			for(int i = 1; i <= amountHorizontally; i++) {
				JButton button = new JButton();
				button.setText(counter + ". ");
				buttonTable[i-1][j-1] = button;
				counter++;
				button.setBounds(buttonWidth*(i-1) + firstX*i, buttonHeight*(j-1) + firstY*j, buttonWidth, buttonHeight);
				panel_6.add(button);
			}
		}
	}
	
	private void errorMessageException(String title, String text, Exception e) {
		JOptionPane.showMessageDialog(this, text + " (" + e.getMessage() + ") ", title, JOptionPane.OK_OPTION);
	}
	
	private void errorMessage(String title, String text) {
		JOptionPane.showMessageDialog(this, text + " ", title, JOptionPane.OK_OPTION);
	}
	
	private void goToStorageClicked(ActionEvent e) {
		contentPane4.add(menuBar);
		cl.show(contentPane, "4");
	}
	
	private void fillMaterialComboBox() {
		List<Material> materialList = null;
		try {
			materialList = materialCtrl.getAll();
		} catch (DataAccessException e) {
			errorMessageException("Display material", "Failed to display the materials", e);
			//e.printStackTrace();
		}
		ProductDescription product = getListCurrentProduct();

		DefaultComboBoxModel<String> materialComboBoxModel = new DefaultComboBoxModel<>();
		if(!materialList.isEmpty()) {
			for (int i = 0; i < materialList.size(); i++) {
				materialComboBoxModel.addElement(materialList.get(i).getName());
			}
			comboBoxMaterial.setModel(materialComboBoxModel);
		}
		this.comboBoxMaterial.setSelectedItem(product != null ? product.getMaterial().getName() : null);
	}
	
	private void showProductList() {

		DefaultListModel<ProductDescription> productsModel = new DefaultListModel<>();
		
		List<ProductDescription> products = null;
		
		try {
			products = productCtrl.findAll();
		} catch (DataAccessException e) {
			errorMessageException("Display products", "Failed to display the products", e);
		}
		if(products != null) {
			for(int i = 0; i < products.size(); i++) {
				productsModel.addElement(products.get(i));
			}
			listProducts.setModel(productsModel);
		}
		else
		{
			errorMessage("Display products", "The list of products is empty");
		}
		
	}
	
	private void backButtonClicked(ActionEvent e) {
		contentPane1.add(menuBar);
		cl.show(contentPane, "1");
		chosenCompany = null;
	}
	
	private void backButtonClicked1(ActionEvent e) {
		contentPane1.add(menuBar);
		cl.show(contentPane, "1");
	}
	
	private void btnAddProductClicked(ActionEvent e) { 
		addProductFrame = new AddProductFrame();
		addProductFrame.setVisible(true);
		addProductButtonFrame = addProductFrame.getAddProductButton();
		addProductButtonFrame.addActionListener(this::addProductConfirm);
		
		errorMessage("Product Amount", "Remember to change amount of the item and apply changes :)");
	}
	
	private void addProductConfirm(ActionEvent e) { //Confirming addition of a product in AddProductFrame frame
		Order order = getCurrentOrder();
		
		if(order == null) {
			int companyId = getCurrentCompany().getId();
			BigDecimal priceInTotal = new BigDecimal(0);
			int discount = 0;
			int orderId = 0;
			try {
				orderId = orderCtrl.addOrder(companyId, priceInTotal, discount, Order.State.ACCEPTED);
				order = orderCtrl.findByID(orderId);
				getCurrentCompany().getOrderList().add(order);
				orderListTableModel.setData(getCurrentCompany().getOrderList());
			} catch (DataAccessException e1) {
				errorMessageException("Order addition", "Failed to add an order", e1);
				e1.printStackTrace();
			}
			table_1.updateUI();
			table_1.setRowSelectionInterval(getCurrentCompany().getOrderList().size()-1, getCurrentCompany().getOrderList().size()-1);
			addProductConfirm(e);
		}
		else if(order.getState() == Order.State.ACCEPTED) { //when order is still in production phase, choose this order
			//order = getCurrentOrder();
		}
		else { //when order is being delivered/has been delivered, make a new order
			int companyId = getCurrentCompany().getId();
			BigDecimal priceInTotal = new BigDecimal(0);
			int discount = 0;
			int orderId = 0;
			try {
				orderId = orderCtrl.addOrder(companyId, priceInTotal, discount, Order.State.ACCEPTED);
				order = orderCtrl.findByID(orderId);
				getCurrentCompany().getOrderList().add(order);
				orderListTableModel.setData(getCurrentCompany().getOrderList());
			} catch (DataAccessException e1) {
				errorMessageException("Order addition", "Failed to add an order", e1);
				e1.printStackTrace();
			}
			table_1.updateUI();
			table_1.setRowSelectionInterval(getCurrentCompany().getOrderList().size()-1, getCurrentCompany().getOrderList().size()-1);
			addProductConfirm(e);
		}
			
		int orderID = order.getId();
		int companyID = addProductFrame.getCurrentProduct().getId();
		int amount = 0;
		BigDecimal historicalPrice = addProductFrame.getCurrentProduct().getPrice();
		OrderLine orderLine;
		try {
			int orderLineID = orderLineCtrl.addOrderLine(orderID, companyID, amount, historicalPrice);
			orderLine = orderLineCtrl.findByID(orderLineID);
			order.getOrderLineList().add(orderLine);
			orderLineListTableModel.setData(order.getOrderLineList());
				
		} catch (DataAccessException e1) {
			errorMessageException("Orderline creation", "Failed to create orderline", e1);
			//e1.printStackTrace();
		}
			
		displayOrder(getCurrentOrder());
	}
	
	private void updatePrices(int quantity) {
		
		OrderLine currentOrderLine = getCurrentOrderLine();
		if (currentOrderLine != null) {
			currentOrderLine.setAmount(quantity);
			BigDecimal historicalPrice = currentOrderLine.getHistoricalPrice();
			BigDecimal totalProductPrice = historicalPrice.multiply(new BigDecimal(quantity));
			textFieldProductFullPrice.setText("" + totalProductPrice);
			
			BigDecimal totalPrice = getCurrentOrder().getPriceInTotal();
			int discount = Integer.parseInt(textOrderDiscount.getText());
			totalPrice = totalPrice.multiply(new BigDecimal((100-discount)/100));
			textFieldOrderTotal.setText("" + totalPrice);
		}
	}
	
	private void removeButtonClicked(ActionEvent e) {
		//OrderLine currOrderLine = getCurrentOrderLine();
		//Order currOrder = getCurrentOrder();
		//remove orderline from order list
		
		//nothing to see here
		//doable when it's not being delivered/produced
	}
	
	private void goToOrdersClicked(ActionEvent e) {
		 chosenCompany = getCurrentCompany();
		
		if(chosenCompany != null) {
			try {
				chosenCompany.setOrderList(orderCtrl.findByCompanyID(chosenCompany.getId()));
			} catch (DataAccessException e1) {
				errorMessage("No orders", "Orders could not be loaded");
				e1.printStackTrace();
			}
			
			this.txtCurrentCompany.setText("Current company: " + chosenCompany.getName());
			this.orderListTableModel.setData(chosenCompany.getOrderList());
			contentPane1.remove(menuBar);
			contentPane2.add(menuBar);
			cl.show(contentPane, "2");
			contentPane2.updateUI();
		}
		else {
			errorMessage("Proceeding to orders", "You have to choose the company first");
		}
		
	}
	
	private void goToProductsClicked(ActionEvent e) {
		contentPane1.remove(menuBar);
		contentPane3.add(menuBar);
		cl.show(contentPane, "3");
		
	}
	
	private void insertButtonClicked(ActionEvent e) {
		
		String name;
		String email; 
		String phoneNumber;
		
		boolean correctName = validateCompanyName(this.txtCompanysName.getText());
		boolean correctEmail = validateEmail(this.txtEmail.getText());
		boolean correctPhoneNumber = true;
		
		try {
			//correctPhoneNumber = validatePhoneNumber(Double.parseDouble(this.txtPhoneNumber.getText().replaceAll(" ", "")));
		} catch(NumberFormatException e1) {
			errorMessageException("Phone Number", "Phone number has to be a number", e1);
		}
		
		if(correctName && correctEmail && correctPhoneNumber) {
			
			name = this.txtCompanysName.getText();
			email = this.txtEmail.getText();
			phoneNumber = this.txtPhoneNumber.getText().replaceAll(" ", "");
			
			boolean exists = false;
			
			try {
				exists = companyCtrl.findByExactName(name);
			} catch (DataAccessException e2) {
				//errorMessageException("Finding Company", "Failed to get results", e2);
				e2.printStackTrace();
			}
			
			if(exists) {
				try {
					companyCtrl.addCompany(name, email, phoneNumber);
					JOptionPane.showMessageDialog(null, "The new company has been inserted :)");
				} catch (DataAccessException e1) {
					//errorMessageException("Company insertion", "Failed to insert the company", e1);
					e1.printStackTrace();
				}
			}
			else {
				errorMessage("Company Insertion", "The company with this name already exists");
			}
		}	
		findButtonClicked(null);
	}
	
	private void productInsertClicked(ActionEvent e) {
		
		int materialID = 1; //change this
		String name = textAreaNameProduct.getText();
		BigDecimal price = new BigDecimal(textAreaPriceProduct.getText());
		
		ProductDescription product = null;
		
		try {
			product = productCtrl.findByExactName(name);
		} catch (DataAccessException e2) {
			errorMessageException("Find by name", "Failed to by the name", e2);
			//e2.printStackTrace();
		}
		
		if(product == null) {
			try {
				productCtrl.addProduct(materialID, name, price);
			} catch (DataAccessException e1) {
				errorMessageException("Product addition", "Failed to insert a product", e1);
				//e1.printStackTrace();
			}
		} 
		else {
			errorMessage("Product addition", "This product already exists");
		}
		showProductList();
	}
	
	private void productApplyChangesClicked(ActionEvent e) {
		ProductDescription product = getListCurrentProduct();
		
		if(product != null) {
			int materialID = 0; //to change
			String name = textAreaNameProduct.getText();
			int locationNumber = 0; //to change
			BigDecimal price = new BigDecimal(textAreaPriceProduct.getText());
			int id = product.getId();
			
			try {
				productCtrl.updateProduct(materialID, name, locationNumber, price, id);
				product.setName(name);
				product.setPrice(price);
				product.setId(id);
			} catch (DataAccessException e1) {
				errorMessageException("Product update", "Failed to update the product", e1);
				//e1.printStackTrace();
			}
		} else {
			errorMessage("Update product", "You have to choose product first");
		}
		
		showProductList();
	}
	
	private void applyChangesClickedOrder(ActionEvent e) {
		OrderLine orderLine = getCurrentOrderLine();
		Order order = getCurrentOrder();
		
		if(orderLine != null) {
			int quantity = (int)spinnerOrder.getValue();
			int discount = Integer.parseInt(textOrderDiscount.getText());
			orderLine.setAmount(quantity);
			order.setDiscount(discount);
		}
		orderLineListTableModel.setData(order.getOrderLineList());
	}
	
	private void applyChangesClicked(ActionEvent e) {
		Company currCompany = getCurrentCompany();
		
		// get data for update form text fields
		String name = this.txtCompanysName.getText();
		String email = this.txtEmail.getText();
		String phoneNumber = this.txtPhoneNumber.getText();
		
		// validation
		boolean correctName = validateCompanyName(name);
		boolean correctEmail = validateEmail(email);
		//boolean correctPhoneNumber = validatePhoneNumber(phoneNumber);
		
		if(correctName && correctEmail) {
			if(currCompany!=null) {
				boolean updateSuccess = false;
				int id = getCurrentCompany().getId();
				try {
					updateSuccess = companyCtrl.updateCompany(id, name, email, phoneNumber);
				} catch (DataAccessException e1) {
					errorMessageException("Applying changes", "Failed to apply changes", e1);
					// e1.printStackTrace();
				}
				if(updateSuccess) {
					// update targeted company parameters
					currCompany.setName(name);
					currCompany.setEmail(email);
					currCompany.setPhoneNumber(phoneNumber);
					JOptionPane.showMessageDialog(null, "Update succeeded");
				}
				else {
					errorMessage("Update", "Update failed, the name is already used");
				}
			}
			else {
				errorMessage("Applying changes", "You have to choose the company first");
			}
		}
		else {
			errorMessage("Applying data", "You have to provide valid data - Name, E-mail or phone number is incorrect");
		}
		
	}
	
	private void findButtonClicked(ActionEvent e) {
		
		SwingWorker<?, ?> swingWorker = new SwingWorker<Object, Object>() {
			protected Object doInBackground() throws Exception {
				String name = textSearchFieldCompany.getText();
				List<Company> companyList;
				try {
					if(name.equals("")) {
						companyList = companyCtrl.findAll();
					}
					else {
						companyList = companyCtrl.findByName(name);
					}
					
					if(companyList.isEmpty()) {
						JOptionPane.showMessageDialog(null, "No company found");
					}
					companyListTableModel.setData(companyList);
					
				} catch (DataAccessException e1) {
					errorMessageException("Searching company", "Failed to find the results", e1);
				}
			return null;
			}
		};
		swingWorker.execute();
	}
	
	private OrderLine getCurrentOrderLine() {
		int selectedRow = this.table_2.getSelectedRow();
		System.out.println(selectedRow);
		OrderLine currOrderLine = null;
		if(selectedRow > -1) {
			currOrderLine = this.orderLineListTableModel.getOrderLineOfRow(selectedRow);
		}
		return currOrderLine;
	}

	private Company getCurrentCompany() {
		int selectedRow = this.table.getSelectedRow();
		Company currEmployee = null;
		if (selectedRow > -1) {
			currEmployee = this.companyListTableModel.getCompanyOfRow(selectedRow);
		}
		return currEmployee;
	}
	
	private Order getCurrentOrder() {

		int selectedRow = this.table_1.getSelectedRow();
		Order currOrder = null;
		if(selectedRow > -1) {
			currOrder = this.orderListTableModel.getOrderOfRow(selectedRow);
		}
		return currOrder;
	}
	
	private ProductDescription getListCurrentProduct() {
		return (ProductDescription) listProducts.getSelectedValue();
	}
	
	private void displayOrderLine(OrderLine currentOrderLine) { //used to display list of products in Order
		if(currentOrderLine != null) {
			ProductDescription currentProduct = currentOrderLine.getProduct();
			int id = currentProduct.getId();
			String name = currentProduct.getName();
			BigDecimal historicalPrice = currentOrderLine.getHistoricalPrice();
			int quantity = currentOrderLine.getAmount();
			BigDecimal totalProductPrice = historicalPrice.multiply((BigDecimal) spinnerOrder.getValue());
			
			this.textFieldOrderId.setText("" + id);
			this.textFieldOrderName.setText("" + name);
			this.textFieldProductPrice.setText("" + historicalPrice);
			this.spinnerOrder.setValue(quantity);
			this.textFieldProductFullPrice.setText("" + totalProductPrice);
			
		}
		else {
			this.textFieldOrderId.setText("");
			this.textFieldOrderName.setText("");
			this.textFieldProductPrice.setText("");
			spinnerOrder.setValue(0);
			this.textFieldProductFullPrice.setText("");
		}
		//table_2.updateUI();
	}
	
	private void displayOrder(Order currentOrder) { //used to display order's information
		if(currentOrder != null) {
			int discount = currentOrder.getDiscount();
			BigDecimal totalPrice = currentOrder.getPriceInTotal();
			totalPrice = totalPrice.multiply(new BigDecimal((100-discount/100)));
			this.textOrderDiscount.setText("" + discount);
			this.textFieldOrderTotal.setText("" + totalPrice);
			
			this.orderLineListTableModel.setData(currentOrder.getOrderLineList());
		}
		else {
			this.textOrderDiscount.setText("");
			this.textFieldOrderTotal.setText("");
		}
	}
	
	private void displayCompany(Company currentCompany) {  //used to display company's information

		if(currentCompany != null) {
			String name = currentCompany.getName();
			String email = currentCompany.getEmail();
			String phoneNumber = currentCompany.getPhoneNumber();
			
			this.txtCompanysName.setText(name);
			this.txtEmail.setText(email);
			this.txtPhoneNumber.setText(phoneNumber);
			
		}
		else {
			this.txtCompanysName.setText("Company's name");
			this.txtEmail.setText("E-mail");
			this.txtPhoneNumber.setText("Phone Number");
		}
	}
	
	private void displayProduct(ProductDescription product) {
		
		if(product != null) {
			int id = product.getId();
			String name = product.getName(); 
			BigDecimal price = product.getPrice();
			
			this.lblIdProductDisplay.setText("" + (int) id);
			this.textAreaNameProduct.setText(name);
			this.textAreaPriceProduct.setText("" + price);
			int i = 0;
			int index = 0;
			while(i < comboBoxMaterial.getModel().getSize()) {
				if(comboBoxMaterial.getModel().getElementAt(index).equals(product.getName())) {
					index = i;
				}
				i++;
			}
			comboBoxMaterial.setSelectedIndex(index);
		}	
	}
	
	private boolean validateEmail(String email) {
		boolean matches = false;
		
		Matcher emailMatcher = emailPattern.matcher(email);
		matches = emailMatcher.find();
		if(!matches) {
			errorMessage("Invalid data", "Invalid email");
		}
		return matches;
	}
	
	private boolean validateCompanyName(String name) {
		boolean matches = false;
		
		Matcher nameMatcher = companyNamePattern.matcher(name);
		matches = nameMatcher.find();
		if(!matches) {
			errorMessage("Invalid data", "Invalid name");
		}
		return matches;
	}
	
	private boolean validatePhoneNumber(double phoneNumber) {
		boolean matches = false;
		
		Matcher phoneNumberMatcher = phoneNumberPattern.matcher("" + phoneNumber);
		matches = phoneNumberMatcher.matches();
		if(!matches) {
			errorMessage("Invalid data", "Invalid phone number");
		}
		return matches;
	}
}
