package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import Controllers.DataAccessException;
import Database.OrderDB;
import Model.Order;
import Model.Order.State;

class OrderDBTest {

	static OrderDB orderDB;
	
	public static void setup() {
		try {
			orderDB = new OrderDB();
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void insertTest() {
		int companyID = 1;
		BigDecimal priceInTotal = new BigDecimal(0);
		int discount = 0;
		State state = Order.State.ACCEPTED;
		int id = 0;
		
		try {
			id = orderDB.insertOrder(companyID, priceInTotal, discount, state);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		assertNotEquals(0, id);
	}

	@Test
	public void updateTest() {
		int discount = 10;
		BigDecimal priceInTotal = new BigDecimal(200);
		State state = Order.State.ACCEPTED;
		int id = 1;
		boolean success = false;
		
		try {
			success = orderDB.updateOrder(discount, priceInTotal, state, id);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		assertTrue(success);
	}
	
	
	@Test
	public void findByIDTest() {
		int id = 1;
		Order order = null;
		
		try {
			order = orderDB.findByID(id);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		assertEquals(id, order.getId());
	}
}
