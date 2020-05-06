package Controllers;

import java.math.BigDecimal;
import java.util.List;

import Database.OrderLineDB;
import Database.OrderLineDBIF;
import Model.OrderLine;

public class OrderLineController {

	private OrderLineDBIF orderLineDB;
	
	public OrderLineController() throws DataAccessException {
		orderLineDB = new OrderLineDB();
	}
	
	public int addOrderLine(int orderId, int productId, int amount, BigDecimal historicalPrice) throws DataAccessException {
		return orderLineDB.insertOrderLine(orderId, productId, amount, historicalPrice);
	}
	
	public boolean updateOrderLine(int id, int amount) throws DataAccessException{
		return orderLineDB.updateOrderLine(id, amount);
	}
	
	public List<OrderLine> findAllByOrderID(int orderId) throws DataAccessException {
		return orderLineDB.findAllByOrderID(orderId);
	}

	public int getLastID() throws DataAccessException {
		return orderLineDB.getLastID();
	}

	public OrderLine findByID(int orderLineID) throws DataAccessException {
		return orderLineDB.findByID(orderLineID);
	}
}
