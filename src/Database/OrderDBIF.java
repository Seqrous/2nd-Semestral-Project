package Database;

import java.math.BigDecimal;
import java.util.List;
import Controllers.DataAccessException;
import Model.Company;
import Model.Order;
import Model.Order.State;

public interface OrderDBIF {

	public int insertOrder(int companyId, BigDecimal priceInTotal, int discount, State state) throws DataAccessException;
	
	public boolean updateOrder(int discount, BigDecimal priceInTotal, State state, int id) throws DataAccessException;

	public List<Order> findByCompanyID(int companyId) throws DataAccessException;
	
	public Order findByID(int id) throws DataAccessException;
	
	public double getLastID() throws DataAccessException;
}
