package Database;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import Controllers.DataAccessException;
import Controllers.ProductController;
import Model.OrderLine;
import Model.ProductDescription;

public class OrderLineDB implements OrderLineDBIF{
	
	DBConnection dbConnection;
	Connection connection;

	private static final String INSERT_ORDERLINE_Q = "Insert into OrderLine (orderID, productID, amount, historicalPrice) values (?,?,?,?) SELECT SCOPE_IDENTITY()";
	private PreparedStatement insertOrderLine;
	
	private static final String REMOVE_ORDERLIE_Q = "";
	private PreparedStatement removeOrderLine;
	
	private static final String UPDATE_ORDERLINE_Q = "Update OrderLine set amount = ? where id = ?";
	private PreparedStatement updateOrderLine;
	
	private static final String FIND_ALL_BY_ORDERID_Q = "Select id, productID, amount, historicalPrice "
													  + "from OrderLine where orderID = ?";
	private PreparedStatement findAllByOrderID;
	
	private static final String FIND_BY_ID_Q = "Select id, productID, amount, historicalPrice from OrderLine where id = ?";
	private PreparedStatement findByID;
	
	private static final String GET_LAST_ID_Q = "SELECT IDENT_CURRENT('Table')";
	private PreparedStatement getLastID;
	
	public OrderLineDB() throws DataAccessException {
		init();
	}
	
	public void init() throws DataAccessException {
		dbConnection = DBConnection.getInstance();
		connection = dbConnection.getConnection();
		
		try {
			dbConnection.startTransaction();
			insertOrderLine = connection.prepareStatement(INSERT_ORDERLINE_Q);
			updateOrderLine = connection.prepareStatement(UPDATE_ORDERLINE_Q);
			findAllByOrderID = connection.prepareStatement(FIND_ALL_BY_ORDERID_Q);
			findByID = connection.prepareStatement(FIND_BY_ID_Q);
			getLastID = connection.prepareStatement(GET_LAST_ID_Q);
			dbConnection.commitTransaction();
		} catch (SQLException e) {
			dbConnection.rollbackTransaction();
			throw new DataAccessException("Failed to prepare the statements", e);
			//e.printStackTrace();
		}
	}
	
	public int insertOrderLine(int orderId, int productId, int amount, BigDecimal historicalPrice) throws DataAccessException {
		int toReturn = 0;
		ResultSet resultSet = null;
		
		try {
			dbConnection.startTransaction();
			insertOrderLine.setInt(1, orderId);
			insertOrderLine.setInt(2, productId);
			insertOrderLine.setInt(3, amount);
			insertOrderLine.setBigDecimal(4, historicalPrice);
			resultSet = insertOrderLine.executeQuery();
			while(resultSet.next()) {
				// get the id of a new orderline
				toReturn = resultSet.getInt(1);
			}
			dbConnection.commitTransaction();
		} catch (SQLException | DataAccessException e) {
			dbConnection.rollbackTransaction();
			//throw new DataAccessException("Failed to insert the orderline", e);
			e.printStackTrace();
		}
		
		return toReturn;
	}
	
	public boolean updateOrderLine(int id, int amount) throws DataAccessException {
		boolean toReturn = false;
		
		try {
			dbConnection.startTransaction();
			updateOrderLine.setInt(1, amount);
			updateOrderLine.setInt(2, id);
			toReturn = updateOrderLine.executeUpdate() > 0;
			dbConnection.commitTransaction();
		} catch (DataAccessException | SQLException e) {
			dbConnection.rollbackTransaction();
			throw new DataAccessException("Failed to update the orderline", e);
			//e.printStackTrace();
		}
		return toReturn;
	}
	
	public List<OrderLine> findAllByOrderID(int orderId) throws DataAccessException {
		ResultSet resultSet;
		
		try {
			dbConnection.startTransaction();
			findAllByOrderID.setInt(1, orderId);
			resultSet = findAllByOrderID.executeQuery();
			dbConnection.commitTransaction();
		} catch (DataAccessException | SQLException e) {
			dbConnection.rollbackTransaction();
			throw new DataAccessException("Failed to find the orderlines", e);
			//e.printStackTrace();
		}
		return buildObjects(resultSet);
	}
	
	public OrderLine findByID(int id) throws DataAccessException {
		ResultSet resultSet = null;
		
		try {
			dbConnection.startTransaction();
			findByID.setInt(1, id);
			resultSet = findByID.executeQuery();
			dbConnection.commitTransaction();
		} catch (DataAccessException | SQLException e) {
			dbConnection.rollbackTransaction();
			throw new DataAccessException("Failed to find the orderline by ID", e);
			//e.printStackTrace();
		}
		
		
		return buildObject(resultSet);
	}
	
	public int getLastID() throws DataAccessException {
		int id = 0;
		
		try {
			dbConnection.startTransaction();
			id = getLastID.executeQuery().getInt(1);
			dbConnection.commitTransaction();
		} catch (DataAccessException | SQLException e) {
			dbConnection.rollbackTransaction();
			//throw new DataAccessException("Failed to retrieve id", e);
			e.printStackTrace();
		}
		
		return id;
	}
	
	private List<OrderLine> buildObjects(ResultSet resultSet) throws DataAccessException {
		List<OrderLine> orderLineList = new LinkedList<>();
		
		try {
			while(resultSet.next()) {
				int id = resultSet.getInt(1);
				int productID = resultSet.getInt(2);
				int amount = resultSet.getInt(3);
				BigDecimal historicalPrice = resultSet.getBigDecimal(4);
				
				ProductController productCtrl = new ProductController();
				ProductDescription product = productCtrl.findByID(productID);
				
				OrderLine orderLine = new OrderLine(id, amount, historicalPrice, product);
				orderLineList.add(orderLine);
			}
		} catch (DataAccessException | SQLException e) {
			throw new DataAccessException("Failed to build orderlines", e);
			//e.printStackTrace();
		}
		
		return orderLineList;
	}
	
	private OrderLine buildObject (ResultSet resultSet) throws DataAccessException {
		OrderLine orderLine = null;
		
		try {
			while(resultSet.next()) {
				int id = resultSet.getInt(1);
				int productID = resultSet.getInt(2);
				int amount = resultSet.getInt(3);
				BigDecimal historicalPrice = resultSet.getBigDecimal(4);
				
				ProductController productCtrl = new ProductController();
				ProductDescription product = productCtrl.findByID(productID);
				
				orderLine = new OrderLine(id, amount, historicalPrice, product);
			}
		} catch (SQLException | DataAccessException e) {
			throw new DataAccessException("Failed to build an order line", e);
			//e.printStackTrace();
		}
		
		return orderLine;
	}

}
