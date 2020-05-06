package Database;

import java.math.BigDecimal;
import java.util.List;

import Controllers.DataAccessException;
import Model.OrderLine;

public interface OrderLineDBIF {

	public int insertOrderLine(int orderId, int productId, int amount, BigDecimal historicalPrice) throws DataAccessException;

	public boolean updateOrderLine(int id, int amount) throws DataAccessException;

	public List<OrderLine> findAllByOrderID(int orderId) throws DataAccessException;

	public int getLastID() throws DataAccessException;

	public OrderLine findByID(int id) throws DataAccessException;
}
