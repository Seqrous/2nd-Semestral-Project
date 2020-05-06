package UI;
//package GUI;
//
//import java.awt.BorderLayout;
//import java.awt.CardLayout;
//import java.awt.Color;
//import java.awt.EventQueue;
//import java.awt.Font;
//import java.awt.event.ActionEvent;
//import java.awt.event.FocusEvent;
//import java.awt.event.FocusListener;
//import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import javax.swing.AbstractButton;
//import javax.swing.JButton;
//import javax.swing.JComboBox;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JList;
//import javax.swing.JMenu;
//import javax.swing.JMenuBar;
//import javax.swing.JMenuItem;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JSpinner;
//import javax.swing.JTable;
//import javax.swing.JTextArea;
//import javax.swing.JTextField;
//import javax.swing.SwingWorker;
//import javax.swing.UIManager;
//import javax.swing.border.EtchedBorder;
//import javax.swing.border.LineBorder;
//import javax.swing.border.MatteBorder;
//
//import Controllers.CompanyController;
//import Controllers.DataAccessException;
//import Controllers.OrderController;
//import Controllers.OrderLineController;
//import Controllers.ProductController;
//import Model.Company;
//import Model.Material;
//import Model.Order;
//import Model.OrderLine;
//import Model.ProductDescription;
//
//public class GUIThreaded extends JFrame {
//
//	private static final long serialVersionUID = 1L;
//	
//	private JTextField txtCompanysName;
//	private JTextField txtEmail;
//	private JTextField txtPhoneNumber;
//	private JTextField txtCurrentCopmany;
//	private JTextField textSearchFieldCompany;
//	private JTextField textOrderDiscount;
//	
//	private CardLayout cl;
//	private JPanel contentPane;
//	private JPanel contentPane3;
//	private JPanel contentPane2;
//	private JPanel contentPane1;
//	private JPanel contentPane4;
//	private JPanel panel_5;
//	private JPanel panel_4;
//	private JPanel panel_3;
//	private JPanel panel_2;
//	private JPanel panel_1;
//	private JPanel panel;
//	
//	private AddProductFrame addProductFrame;
//	private JMenuBar menuBar;
//	private JButton btnFindCompany;
//	private JButton btnInsertCompany ;
//	private JButton addProductButtonFrame;
//	private JButton btnInsertProduct;
//	private JButton btnApplyChangesProduct;
//	private JButton btnGoToStorage;
//	private JButton btnAddProductOrder;
//	private JButton btnRemoveProductOrder;
//	private JButton btnApplyChangesOrder;
//	private JButton btnBackOrder;
//	private JButton btnGoToOrders;
//	private JButton btnApplyChangesCompany;
//	private AbstractButton btnGoToProducts;
//	private JSpinner spinnerOrder;
//	private JLabel lblDiscountOrder;
//	private JLabel lblOrdersTotalOrder;
//	private JLabel lblIdOrder;
//	private JLabel lblNameOrder;
//	private JLabel lblPriceOrder;
//	private JLabel lblAmountOrder;
//	private JLabel lblProductFullPriceOrder;
//	private JLabel lblIdProductDisplay;
//	private JLabel lblListOfProducts;
//	private JLabel lblIdProduct;
//	private JLabel lblNameProduct;
//	private JLabel lblPriceProduct;
//	private JLabel lblMaterialProduct;
//	private JLabel lblNameCompany;
//	private JLabel labelPercentage;
//	private JTextArea textAreaNameProduct;
//	private JTextArea textAreaPriceProduct;
//	private JTextArea txtrAddNewCustomer;
//	private JList<ProductDescription> listProducts;
//	private JComboBox<Material> comboBoxMaterial;
//	
//	private JMenu mnHelp;
//	private JMenu menu;
//	private JMenuItem mntmSendCrashReport;
//	private JMenuItem mntmSoftwareInformation;
//	
//	private JScrollPane scrollPane;
//	private JScrollPane scrollPane_1;
//	private JScrollPane scrollPane_2;
//	private JTable table;
//	private JTable table_1;
//	private JTable table_2;
//
//	private OrderController orderCtrl;
//	private OrderLineController orderLineCtrl;
//	private CompanyController companyCtrl;
//	private ProductController productCtrl;
//	
//	private CompanyListTableModel companyListTableModel;
//	private OrderListTableModel orderListTableModel;
//	private OrderLineListTableModel orderLineListTableModel;
//	
//	private Company chosenCompany;
//	
//	private static final Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
//	private static final Pattern companyNamePattern = Pattern.compile("^[a-zA-Z0-9&]*$", Pattern.CASE_INSENSITIVE);
//	private static final Pattern phoneNumberPattern = Pattern.compile("^[0-9]+([0-9])+(\\.[0-9]+)?$");
//	private JButton btnBackProduct;
//
//	private Runnable companyGUI;
//	private Runnable orderGUI;
//	private Runnable productGUI;
//
//	public static void main(String[] args) {
//
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					GUIThreaded frame = new GUIThreaded();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//	
//	public GUIThreaded() {
//		
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //GUI frame setup
//		this.setSize(800,420);
//		this.setLocationRelativeTo(null);
//		
//		contentPane = new JPanel();
//		setContentPane(contentPane);
//		cl = new CardLayout();
//		contentPane.setLayout(cl);
//		
//		companyGUI = new Runnable() { //GUI for company menu 
//
//			public void run() {
//
//
//				contentPane1 = new JPanel();
//				contentPane.add(contentPane1, "1");
//				contentPane1.setLayout(null);
//				
//				menuBar = new JMenuBar();
//				menuBar.setBounds(0, 0, 782, 26);
//				contentPane1.add(menuBar);
//				
//				panel = new JPanel();
//				panel.setBounds(0, 25, 782, 344);
//				contentPane1.add(panel);
//				panel.setLayout(null);
//				
//				mnHelp = new JMenu("Help...");
//				menuBar.add(mnHelp);
//				
//				mntmSendCrashReport = new JMenuItem("Give feedback");
//				mnHelp.add(mntmSendCrashReport);
//				
//				menu = new JMenu("About...");
//				menuBar.add(menu);
//				
//				mntmSoftwareInformation = new JMenuItem("Software information");
//				menu.add(mntmSoftwareInformation);
//				
//				panel_1 = new JPanel();
//				panel_1.setBounds(12, 23, 370, 308);
//				panel_1.setLayout(null);
//				panel.add(panel_1);
//				
//				txtrAddNewCustomer = new JTextArea();
//				txtrAddNewCustomer.setBackground(UIManager.getColor("Button.background"));
//				txtrAddNewCustomer.setFont(new Font("Arial", Font.BOLD, 19));
//				txtrAddNewCustomer.setText("Edit or insert a new customer");
//				txtrAddNewCustomer.setBounds(51, 13, 273, 24);
//				panel_1.add(txtrAddNewCustomer);
//				
//				txtCompanysName = new JTextField();
//				txtCompanysName.addFocusListener(new FocusListener() {
//					public void focusLost(FocusEvent e) {
//						if(txtCompanysName.getText().equals(""))
//						txtCompanysName.setText("Company's name");
//					}
//
//					@Override
//					public void focusGained(FocusEvent e) {
//						if(txtCompanysName.getText().equals("Company's name")) {
//							txtCompanysName.setText("");
//						}
//					}
//				});
//				txtCompanysName.setText("Company's name");
//				txtCompanysName.setBounds(51, 50, 273, 30);
//				panel_1.add(txtCompanysName);
//				
//				txtEmail = new JTextField();
//				txtEmail.setText("E-mail");
//				txtEmail.addFocusListener(new FocusListener() {
//					public void focusLost(FocusEvent e) {
//						if(txtEmail.getText().equals(""))
//						txtEmail.setText("E-mail");
//					}
//
//					public void focusGained(FocusEvent e) {
//						if(txtEmail.getText().equals("E-mail")) {
//							txtEmail.setText("");
//						}
//					}
//				});	
//				txtEmail.setColumns(10);
//				txtEmail.setBounds(51, 99, 273, 30);
//				panel_1.add(txtEmail);
//				
//				txtPhoneNumber = new JTextField();
//				txtPhoneNumber.addFocusListener(new FocusListener() {
//					public void focusLost(FocusEvent e) {
//						if(txtPhoneNumber.getText().equals(""))
//						txtPhoneNumber.setText("Phone Number");
//					}
//
//					public void focusGained(FocusEvent e) {
//						if(txtPhoneNumber.getText().equals("Phone Number")) {
//							txtPhoneNumber.setText("");
//						}
//					}
//				});
//				txtPhoneNumber.setText("Phone Number");
//				txtPhoneNumber.setColumns(10);
//				txtPhoneNumber.setBounds(51, 147, 273, 30);
//				panel_1.add(txtPhoneNumber);
//				
//				btnInsertCompany = new JButton("Insert");
//				btnInsertCompany.setBounds(51, 203, 131, 44);
//				btnInsertCompany.addActionListener(this::insertButtonClicked);
//				panel_1.add(btnInsertCompany);
//				
//				btnApplyChangesCompany = new JButton("Apply changes");
//				btnApplyChangesCompany.setBounds(194, 203, 131, 44);
//				btnApplyChangesCompany.addActionListener(this::applyChangesClicked);
//				panel_1.add(btnApplyChangesCompany);
//				
//				panel_2 = new JPanel();
//				panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
//				panel_2.setBounds(394, 57, 369, 230);
//				panel.add(panel_2);
//				panel_2.setLayout(null);
//				
//				scrollPane = new JScrollPane();
//				scrollPane.setBounds(0, 227, 367, -227);
//				scrollPane.setLayout(null);
//				panel_2.add(scrollPane, BorderLayout.CENTER);
//				
//				table = new JTable();
//				scrollPane.setViewportView(table);
//				
//				btnFindCompany = new JButton("Find");
//				btnFindCompany.setBounds(691, 12, 72, 33);
//				btnFindCompany.addActionListener(this::findButtonClicked);
//				panel.add(btnFindCompany);
//				
//				textSearchFieldCompany = new JTextField();
//				textSearchFieldCompany.setBounds(470, 13, 209, 31);
//				panel.add(textSearchFieldCompany);
//				textSearchFieldCompany.setColumns(10);
//				
//				lblNameCompany = new JLabel("Name:");
//				lblNameCompany.setFont(new Font("Tahoma", Font.PLAIN, 15));
//				lblNameCompany.setBounds(411, 13, 66, 31);
//				panel.add(lblNameCompany);
//				
//				btnGoToOrders = new JButton("Go to orders...");
//				btnGoToOrders.setBounds(612, 300, 151, 31);
//				btnGoToOrders.addActionListener(this::goToOrdersClicked);
//				panel.add(btnGoToOrders);
//				
//				btnGoToProducts = new JButton("Go to products...");
//				btnGoToProducts.addActionListener(this::goToProductsClicked);
//				btnGoToProducts.setBounds(394, 300, 151, 31);
//				panel.add(btnGoToProducts);
//			}
//			
//			private void insertButtonClicked(ActionEvent e) {
//
//				String name = txtCompanysName.getText();
//				String email = txtEmail.getText();
//				double phoneNumber = Double.parseDouble(txtPhoneNumber.getText().replaceAll(" ", ""));
//				
//				boolean correctName = validateCompanyName(name);
//				boolean correctEmail = validateEmail(email);
//				boolean correctPhoneNumber = validatePhoneNumber(phoneNumber);
//				
//				if(correctName && correctEmail && correctPhoneNumber) {
//					boolean exists = false;
//					
//					try {
//						exists = companyCtrl.findByExactName(name);
//					} catch (DataAccessException e2) {
//						errorMessageException("Finding Company", "Failed to get results", e2);
//						//e2.printStackTrace();
//					}
//					
//					if(exists) {
//						try {
//							companyCtrl.addCompany(name, email, phoneNumber);
//						} catch (DataAccessException e1) {
//							errorMessageException("Company insertion", "Failed to insert the company", e1);
//						}
//					}
//					else {
//						errorMessage("Company Insertion", "The company with this (argument) already exists");
//					}
//				}	
//			}
//			
//			private void applyChangesClicked(ActionEvent e) {
//
//				Company currCompany = getCurrentCompany();
//				
//				String name = txtCompanysName.getText();
//				String email = txtEmail.getText();
//				double phoneNumber = Double.parseDouble(txtPhoneNumber.getText());
//				
//				boolean correctName = validateCompanyName(name);
//				boolean correctEmail = validateEmail(email);
//				boolean correctPhoneNumber = validatePhoneNumber(phoneNumber);
//				
//				if(correctName && correctEmail && correctPhoneNumber) {
//					if(currCompany!=null) {
//						double id = getCurrentCompany().getId();
//						
//						try {
//							companyCtrl.updateCompany(name, email, phoneNumber, id);
//						} catch (DataAccessException e1) {
//							errorMessageException("Applying changes", "Failed to apply changes", e1);
//						}
//					}
//					else {
//						errorMessage("Applying changes", "You have to choose the company first");
//					}
//				}
//				else {
//					errorMessage("Applying data", "You have to provide valid data - Name, E-mail or phone number is incorrect");
//				}
//				
//			}
//			
//			private void findButtonClicked(ActionEvent e) {
//				SwingWorker swingWorker = new SwingWorker() {
//					protected Object doInBackground() throws Exception {
//						String name = textSearchFieldCompany.getText();
//						List<Company> companyList;
//						try {
//							if(name.equals("")) {
//								companyList = companyCtrl.findAll();
//							}
//							else {
//								companyList = companyCtrl.findByName(name);
//							}
//							
//							companyListTableModel.setData(companyList);
//							
//						} catch (DataAccessException e1) {
//							errorMessageException("Searching company", "Failed to find the results", e1);
//						}
//					return null;
//					}
//				};
//				swingWorker.execute();
//			}
//
//			private void goToProductsClicked(ActionEvent e) {
//				contentPane1.remove(menuBar);
//				contentPane3.add(menuBar);
//				cl.show(contentPane, "3");
//				
//			}
//			
//			private void goToOrdersClicked(ActionEvent e) {
//				 chosenCompany = getCurrentCompany();
//				
//				if(chosenCompany != null) {
//					txtCurrentCopmany.setText(chosenCompany.getName());
//					orderListTableModel.setData(chosenCompany.getOrderList());
//					contentPane1.remove(menuBar);
//					contentPane2.add(menuBar);
//					cl.show(contentPane, "2");
//				}
//				else {
//					errorMessage("Proceeding to orders", "You have to choose the company first");
//				}
//				
//			}
//		};
//		
//		orderGUI = new Runnable() { //GUI for order menu
//
//			
//			public void run() {
//				contentPane2 = new JPanel();
//				contentPane.add(contentPane2, "2");
//				contentPane2.setLayout(null);
//				
//				txtCurrentCopmany = new JTextField();
//				txtCurrentCopmany.setEditable(false);
//				txtCurrentCopmany.setBackground(UIManager.getColor("Button.background"));
//				txtCurrentCopmany.setText("Current company: ");
//				txtCurrentCopmany.setBounds(0, 25, 268, 22);
//				contentPane2.add(txtCurrentCopmany);
//				txtCurrentCopmany.setColumns(10);
//				
//				lblIdOrder = new JLabel("ID: ");
//				lblIdOrder.setBounds(283, 60, 40, 31);
//				contentPane2.add(lblIdOrder);
//				
//				lblNameOrder = new JLabel("Name:");
//				lblNameOrder.setBounds(283, 95, 40, 31);
//				contentPane2.add(lblNameOrder);
//				
//				lblPriceOrder = new JLabel("Price: ");
//				lblPriceOrder.setBounds(283, 130, 40, 31);
//				contentPane2.add(lblPriceOrder);
//				
//				spinnerOrder = new JSpinner();
//				spinnerOrder.setBounds(406, 169, 77, 22);
//				contentPane2.add(spinnerOrder);
//				
//				lblAmountOrder = new JLabel("Quantity:");
//				lblAmountOrder.setBounds(281, 165, 61, 31);
//				contentPane2.add(lblAmountOrder);
//				
//				lblProductFullPriceOrder = new JLabel("Product full price:");
//				lblProductFullPriceOrder.setBounds(283, 200, 101, 31);
//				contentPane2.add(lblProductFullPriceOrder);
//				
//				lblDiscountOrder = new JLabel("Order discount:");
//				lblDiscountOrder.setBounds(283, 235, 89, 31);
//				contentPane2.add(lblDiscountOrder);
//				
//				lblOrdersTotalOrder = new JLabel("Order's total:");
//				lblOrdersTotalOrder.setBounds(283, 270, 77, 34);
//				contentPane2.add(lblOrdersTotalOrder);
//				
//				btnAddProductOrder = new JButton("Add product...");
//				btnAddProductOrder.setBounds(495, 319, 131, 37);
//				btnAddProductOrder.addActionListener(this::btnAddProductClicked);
//				contentPane2.add(btnAddProductOrder);
//				
//				btnRemoveProductOrder = new JButton("Remove product");
//				btnRemoveProductOrder.addActionListener(this::removeButtonClicked);
//				btnRemoveProductOrder.setBounds(632, 319, 131, 37);
//				contentPane2.add(btnRemoveProductOrder);
//				
//				btnApplyChangesOrder = new JButton("Apply Changes");
//				btnApplyChangesOrder.addActionListener(this::applyChangesClicked);
//				btnApplyChangesOrder.setBounds(320, 319, 131, 37);
//				contentPane2.add(btnApplyChangesOrder);
//				
//				btnBackOrder = new JButton("Back...");
//				btnBackOrder.addActionListener(this::backButtonClicked);
//				btnBackOrder.setBounds(12, 319, 131, 37);
//				contentPane2.add(btnBackOrder);
//				
//				panel_3 = new JPanel();
//				panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
//				panel_3.setBounds(10, 60, 268, 246);
//				contentPane2.add(panel_3);
//				panel_3.setLayout(null);
//				
//				scrollPane_1 = new JScrollPane();
//				scrollPane_1.setBounds(0, 295, 267, -295);
//				panel_3.add(scrollPane_1);
//				
//				table_1 = new JTable();
//				scrollPane_1.setViewportView(table_1);
//				
//				panel_4 = new JPanel();
//				panel_4.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
//				panel_4.setLayout(null);
//				panel_4.setBounds(495, 60, 268, 246);
//				contentPane2.add(panel_4);
//				
//				scrollPane_2 = new JScrollPane();
//				scrollPane_2.setBounds(0, 295, 267, -295);
//				panel_4.add(scrollPane_2);
//				
//				table_2 = new JTable();
//				scrollPane_2.setViewportView(table_2);
//				
//			}	
//			
//			private void btnAddProductClicked(ActionEvent e) { 
//				addProductFrame = new AddProductFrame();
//				addProductFrame.setVisible(true);
//				addProductButtonFrame = addProductFrame.getAddProductButton();
//				addProductButtonFrame.addActionListener(this::addProductConfirm);
//				dispose();
//				errorMessage("Product Amount", "Remember to change amount of the item and apply changes :)");
//			}
//			
//			private void addProductConfirm(ActionEvent e) { //Confirming addition of a product in AddProductFrame frame
//				Order order = null;
//				
//				if(order.getState() == Order.State.ACCEPTED) {
//					order = getCurrentOrder();
//				}
//				else {
//					
//					double companyID = getCurrentCompany().getId();
//					
//					try {
//						orderCtrl.addOrder(companyID, Order.State.ACCEPTED);
//						double id = orderCtrl.getLastID();
//						order = orderCtrl.findByID(id);
//					} catch (DataAccessException e1) {
//						errorMessageException("New order creation", "Failed to create new order", e1);
//						//e1.printStackTrace();
//					}
//					
//				}
//				
//				double orderID = order.getId();
//				double companyID = addProductFrame.currentProduct.getId();
//				int amount = 0;
//				double historicalPrice = addProductFrame.currentProduct.getPrice();
//				try {
//					orderLineCtrl.addOrderLine(orderID, companyID, amount, historicalPrice);
//				} catch (DataAccessException e1) {
//					errorMessageException("Orderline creation", "Failed to create orderline", e1);
//					//e1.printStackTrace();
//				}
//			}
//		
//			private void removeButtonClicked(ActionEvent e) {
//				OrderLine currOrderLine = getCurrentOrderLine();
//				Order currOrder = getCurrentOrder();
//				//remove orderline from order list
//				
//				//nothing to see here
//				//doable when it's not being delivered/produced
//			}
//		
//			private void applyChangesClicked(ActionEvent e) {
//				Company currCompany = getCurrentCompany();
//				
//				String name = txtCompanysName.getText();
//				String email = txtEmail.getText();
//				double phoneNumber = Double.parseDouble(txtPhoneNumber.getText());
//				
//				boolean correctName = validateCompanyName(name);
//				boolean correctEmail = validateEmail(email);
//				boolean correctPhoneNumber = validatePhoneNumber(phoneNumber);
//				
//				if(correctName && correctEmail && correctPhoneNumber) {
//					if(currCompany!=null) {
//						double id = getCurrentCompany().getId();
//						
//						try {
//							companyCtrl.updateCompany(name, email, phoneNumber, id);
//						} catch (DataAccessException e1) {
//							errorMessageException("Applying changes", "Failed to apply changes", e1);
//						}
//					}
//					else {
//						errorMessage("Applying changes", "You have to choose the company first");
//					}
//				}
//				else {
//					errorMessage("Applying data", "You have to provide valid data - Name, E-mail or phone number is incorrect");
//				}
//				
//			}
//		
//			private void backButtonClicked(ActionEvent e) {
//				contentPane1.add(menuBar);
//				cl.show(contentPane, "1");
//				chosenCompany = null;
//			}
//		};
//		
//		productGUI = new Runnable() { //GUI for product menu
//
//			public void run() {
//				contentPane3 = new JPanel();
//				contentPane.add(contentPane3, "3");
//				contentPane3.setLayout(null);
//				
//				//to be finished
//			} 
//			
//		};
//		
//		companyGUI.run();
//		orderGUI.run();
//		productGUI.run();
//		init();
//	}
//
//	private void init() {
//		try {
//			companyCtrl = new CompanyController();
//			orderCtrl = new OrderController();
//			orderLineCtrl = new OrderLineController();
//			productCtrl = new ProductController();
//		} catch (DataAccessException e) {
//			errorMessageException("GUI initiation", "Failed to initiate GUI elements", e);
//			//e.printStackTrace();
//		}
//		
//		this.companyListTableModel = new CompanyListTableModel();
//		this.table.setModel(companyListTableModel);
//		
//		this.orderListTableModel = new OrderListTableModel();
//		this.table_1.setModel(orderListTableModel);
//		
//		this.orderLineListTableModel = new OrderLineListTableModel();
//		this.table_2.setModel(orderLineListTableModel);
//		
//		this.table.getSelectionModel().addListSelectionListener((e) -> displayCompany(getCurrentCompany()));
//		
//		this.table_1.getSelectionModel().addListSelectionListener((e) -> displayOrder(getCurrentOrder()));
//		
//		this.table_2.getSelectionModel().addListSelectionListener((e) -> displayOrderLine(getCurrentOrderLine()));
//		
//		this.spinnerOrder.addChangeListener((e) -> updatePrices((Integer)spinnerOrder.getValue()));
//		
//		this.listProducts.addListSelectionListener((e) -> displayProduct(getListCurrentProduct()));
//		
//		//when the db is ready
//		//try {
//			//showProductList();
//		//} catch (DataAccessException e1) {
//		//	errorMessageException("List Display", "Failed to display product list", e1);
//			//e1.printStackTrace();
//		//}
//		//this.textField_1.getDocument().addDocumentListener(this::discountFieldChanged);
//	}
//	
//	private void errorMessageException(String title, String text, Exception e) {
//		JOptionPane.showMessageDialog(this, text + " (" + e.getMessage() + ") ", title, JOptionPane.OK_OPTION);
//	}
//
//	private void errorMessage(String title, String text) {
//		JOptionPane.showMessageDialog(this, text + " ", title, JOptionPane.OK_OPTION);
//	}
//	
//	private boolean validateCompanyName(String name) {
//		boolean matches = false;
//	
//		Matcher nameMatcher = companyNamePattern.matcher(name);
//		matches = nameMatcher.matches();
//		if(!matches) {
//			errorMessage("Invalid data", "Invalid name");
//		}
//		return matches;
//	}
//	
//	private boolean validateEmail(String email) {
//		boolean matches = false;
//		
//		Matcher emailMatcher = emailPattern.matcher(email);
//		matches = emailMatcher.matches();
//		if(!matches) {
//			errorMessage("Invalid data", "Invalid email");
//		}
//		return matches;
//	}
//	
//	private boolean validatePhoneNumber(double phoneNumber) {
//
//		boolean matches = false;
//		
//		Matcher phoneNumberMatcher = phoneNumberPattern.matcher("" + phoneNumber);
//		matches = phoneNumberMatcher.matches();
//		if(!matches) {
//			errorMessage("Invalid data", "Invalid phone number");
//		}
//		return matches;
//	}
//	
//	private OrderLine getCurrentOrderLine() {
//		int selectedRow = this.table.getSelectedRow();
//		OrderLine currOrderLine = null;
//		if(selectedRow > -1) {
//			currOrderLine = this.orderLineListTableModel.getOrderLineOfRow(selectedRow);
//		}
//		return currOrderLine;
//	}
//
//	private Company getCurrentCompany() {
//		int selectedRow = this.table.getSelectedRow();
//		Company currEmployee = null;
//		if (selectedRow > -1) {
//			currEmployee = this.companyListTableModel.getCompanyOfRow(selectedRow);
//		}
//		return currEmployee;
//	}
//	
//	private Order getCurrentOrder() {
//
//		int selectedRow = this.table_1.getSelectedRow();
//		Order currOrder = null;
//		if(selectedRow > -1) {
//			currOrder = this.orderListTableModel.getOrderOfRow(selectedRow);
//		}
//		return currOrder;
//	}
//	
//	private ProductDescription getListCurrentProduct() {
//		return (ProductDescription) listProducts.getSelectedValue();
//	}
//	
//	private void displayOrderLine(OrderLine currentOrderLine) { //used to display list of products in Order
//		if(currentOrderLine != null) {
//			ProductDescription currentProduct = currentOrderLine.getProduct();
//			double id = currentProduct.getId();
//			String name = currentProduct.getName();
//			double historicalPrice = currentOrderLine.getHistoricalPrice();
//			int quantity = currentOrderLine.getAmount();
//			double totalProductPrice = historicalPrice * (Integer) spinnerOrder.getValue();
//			
//			this.lblIdOrder.setText("ID: " +  id);
//			this.lblNameOrder.setText("Name: " + name);
//			this.lblPriceOrder.setText("Price: " + historicalPrice);
//			spinnerOrder.setValue(quantity);
//
//			this.lblProductFullPriceOrder.setText("Product full price: " + totalProductPrice);
//		}
//		else {
//			this.lblIdOrder.setText("ID:");
//			this.lblNameOrder.setText("Name:");
//			this.lblPriceOrder.setText("Price:");
//			spinnerOrder.setValue(0);
//			this.lblProductFullPriceOrder.setText("Product full price:");
//		}
//	}
//	
//	private void displayOrder(Order currentOrder) { //used to display order's information
//		if(currentOrder != null) {
//			int discount = currentOrder.getDiscount();
//			double totalPrice = currentOrder.getPriceInTotal();
//			totalPrice = totalPrice*(100-discount/100);
//			this.textOrderDiscount.setText("" + discount);
//			this.lblOrdersTotalOrder.setText("Order's total: " + totalPrice);
//		}
//		else {
//			this.textOrderDiscount.setText("");
//			this.lblOrdersTotalOrder.setText("Order's total:");
//		}
//	}
//	
//	private void displayCompany(Company currentCompany) {  //used to display company's information
//
//		if(currentCompany != null) {
//			String name = currentCompany.getName();
//			String email = currentCompany.getEmail();
//			Double phoneNumber = currentCompany.getPhoneNumber();
//			
//			this.txtCompanysName.setText(name);
//			this.txtEmail.setText(email);
//			this.txtPhoneNumber.setText("" + phoneNumber);
//			
//		}
//		else {
//			this.txtCompanysName.setText("Company's name");
//			this.txtEmail.setText("E-mail");
//			this.txtPhoneNumber.setText("Phone Number");
//		}
//	}
//	
//	private void displayProduct(ProductDescription product) {
//		
//		if(product != null) {
//			double id = product.getId();
//			String name = product.getName();
//			double price = product.getPrice();
//			
//			this.lblIdProductDisplay.setText("" + id);
//			this.textAreaNameProduct.setText(name);
//			this.textAreaPriceProduct.setText("" + price);
//		}	
//	}
//
//	private void updatePrices(int quantity) {
//		
//		getCurrentOrderLine().setAmount(quantity);
//		double historicalPrice = getCurrentOrderLine().getHistoricalPrice();
//		double totalProductPrice = historicalPrice * quantity;
//		lblProductFullPriceOrder.setText("Product full price: " + totalProductPrice);
//		
//		double totalPrice = getCurrentOrder().getPriceInTotal();
//		int discount = getCurrentOrder().getDiscount();
//		totalPrice = totalPrice*((100-discount)/100);
//		lblOrdersTotalOrder.setText("Order's total: " + totalPrice);
//	}
//}
