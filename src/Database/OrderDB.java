package Database;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import Controllers.DataAccessException;
import Controllers.OrderLineController;
import Model.Company;
import Model.Order;
import Model.OrderLine;
import Model.Order.State;

public class OrderDB implements OrderDBIF {
	
	private DBConnection dbConnection;
	private Connection connection;
	private OrderLineController orderLineCtrl;

	private static final String INSERT_ORDER_Q = "insert into Orderr (companyID, priceInTotal, discount, state) values (?,?,?,?) SELECT SCOPE_IDENTITY()";
	private PreparedStatement insertOrder;
	
	private static final String UPDATE_ORDER_Q = "update Orderr set discount = ?, priceInTotal = ?, state = ? where id = ?";
	private PreparedStatement updateOrder;
	
	private static final String FIND_BY_COMPANY_ID = "select id, priceInTotal, discount, state"
												   + " from Orderr where companyID = ?";
	private PreparedStatement findByCompanyID;
	
	private static final String FIND_BY_ID = "select id, priceInTotal, discount, state "
										   + "from Orderr where id = ?";
	private PreparedStatement findByID;
	
	private static final String GET_LAST_ID_Q = "Select IDENT_CURRENT('Orderr')";
	private PreparedStatement getLastID;
	
	public OrderDB() throws DataAccessException {
		init();
	}
	
	public void init() throws DataAccessException {
		dbConnection = DBConnection.getInstance();
		connection =  dbConnection.getConnection();
		
		try {
			dbConnection.startTransaction();
			insertOrder = connection.prepareStatement(INSERT_ORDER_Q);
			updateOrder = connection.prepareStatement(UPDATE_ORDER_Q);
			getLastID = connection.prepareStatement(GET_LAST_ID_Q);
			findByID = connection.prepareStatement(FIND_BY_ID);
			findByCompanyID = connection.prepareStatement(FIND_BY_COMPANY_ID);
			dbConnection.commitTransaction();
		} catch (SQLException e) {
			dbConnection.rollbackTransaction();
			throw new DataAccessException("Failed to prepare the statements", e);
			//e.printStackTrace();
		}
		
		
	}
	
	public int insertOrder(int companyId, BigDecimal priceInTotal, int discount, State state) throws DataAccessException {
		int toReturn = 0;
		ResultSet resultSet = null;
		String stateString = state.toString();
		
		try {
			dbConnection.startTransaction();
			insertOrder.setInt(1, companyId);
			insertOrder.setBigDecimal(2, priceInTotal);
			insertOrder.setInt(3, discount);
			insertOrder.setString(4, stateString);
			resultSet = insertOrder.executeQuery();
			while(resultSet.next()) {
				toReturn = resultSet.getInt(1);
			}
			dbConnection.commitTransaction();
		} catch (DataAccessException | SQLException e) {
			dbConnection.rollbackTransaction();
			//throw new DataAccessException("Failed to insert order", e);
			e.printStackTrace();
		}
		
		return toReturn;
	}

	public boolean updateOrder(int discount, BigDecimal priceInTotal, State state, int id) throws DataAccessException {
		boolean toReturn = false;
		String stateString = state.toString();
		
		
		try {
			dbConnection.startTransaction();
			updateOrder.setInt(1, discount);
			updateOrder.setBigDecimal(2, priceInTotal);
			updateOrder.setString(3, stateString);
			updateOrder.setDouble(4, id);
			toReturn = updateOrder.executeUpdate() > 0;
			dbConnection.commitTransaction();
		} catch (SQLException e) {
			dbConnection.rollbackTransaction();
			throw new DataAccessException("Failed to update the order", e);
			//e.printStackTrace();
		}
		return toReturn;
	}
	
	public List<Order> findByCompanyID(int companyId) throws DataAccessException {
		ResultSet resultSet;
		
		try {
			dbConnection.startTransaction();
			findByCompanyID.setInt(1, companyId);
			resultSet = findByCompanyID.executeQuery();
			dbConnection.commitTransaction();
		} catch (SQLException e) {
			dbConnection.rollbackTransaction();
			throw new DataAccessException("Failed to find customer's orders", e);
			//e.printStackTrace();
		}
		return buildObjects(resultSet);
	}
	
	public Order findByID(int id) throws DataAccessException {
		ResultSet resultSet = null;
		
		try {
			dbConnection.startTransaction();
			findByID.setInt(1, id);
			resultSet = findByID.executeQuery();
			dbConnection.commitTransaction();
		} catch (SQLException e) {
			dbConnection.rollbackTransaction();
			//throw new DataAccessException("Failed to find customer's orders", e);
			e.printStackTrace();
		}
		return buildObject(resultSet);
	}

	public double getLastID() throws DataAccessException {
		double toReturn = -1;
		
		try {
			dbConnection.startTransaction();
			toReturn = getLastID.executeQuery().getDouble(1);
			dbConnection.commitTransaction();
		} catch (SQLException e) {
			dbConnection.rollbackTransaction();
			throw new DataAccessException("Failed to retrieve the last id", e);
			//e.printStackTrace();
		}
		
		return toReturn;
	}
	
	private List<Order> buildObjects(ResultSet resultSet) throws DataAccessException {
		List<Order> orderList = new LinkedList<>();
		Order order = null;
		
		try{
			while(resultSet.next()) {
				order = buildObject(resultSet);
				orderList.add(order);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Failed to build orders", e);
			//e.printStackTrace();
		}
		return orderList;
	}
	
	private Order buildObject(ResultSet resultSet) throws DataAccessException {
		Order order = null;
		
		try {
			while(resultSet.next()) {
				int id = resultSet.getInt(1);
				BigDecimal priceInTotal = resultSet.getBigDecimal(2);
				int discount = resultSet.getInt(3);
				System.out.println(id + " " + priceInTotal + " " + discount);
				Order.State state = State.valueOf(resultSet.getString(4));
				System.out.println(id + " " + priceInTotal + " " + discount + " " + state);
				orderLineCtrl = new OrderLineController();
				List<OrderLine> orderLineList = orderLineCtrl.findAllByOrderID(id);
				order = new Order(id, priceInTotal, discount, state, orderLineList);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Failed to build orders", e);
			//e.printStackTrace();
		}
		return order;
	}

}
