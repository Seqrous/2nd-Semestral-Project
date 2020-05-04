package Controllers;

import java.math.BigDecimal;
import java.util.List;

import Database.OrderDB;
import Database.OrderDBIF;
import Model.Order;
import Model.Order.State;

public class OrderController {
	
	OrderDBIF orderDB;
	
	public OrderController() throws DataAccessException {
		orderDB = new OrderDB();
	}

	public int addOrder(int companyId, BigDecimal priceInTotal, int discount, State state) throws DataAccessException {
		return orderDB.insertOrder(companyId, priceInTotal, discount, state);
	}
	
	public boolean updateOrder(int discount, BigDecimal priceInTotal, State state, int id) throws DataAccessException {
		return orderDB.updateOrder(discount, priceInTotal, state, id);
	}

	public List<Order> findByCompanyID(int companyId) throws DataAccessException {
		return orderDB.findByCompanyID(companyId);
	}
	
	public Order findByID(int id) throws DataAccessException {
		return orderDB.findByID(id);
	}
	
	public double getLastID() throws DataAccessException {
		return orderDB.getLastID();
	}
}
