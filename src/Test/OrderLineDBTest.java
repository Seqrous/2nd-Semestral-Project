package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import Controllers.DataAccessException;
import Database.OrderLineDB;
import Model.OrderLine;

class OrderLineDBTest {

	static OrderLineDB orderLineDB;
	
	public static void setup() {
		try {
			orderLineDB = new OrderLineDB();
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void insertTest() {
		int orderID = 1;
		int productID = 1;
		int amount = 2;
		BigDecimal historicalPrice = new BigDecimal(4.23);
		int id = 0;
		
		try {
			id = orderLineDB.insertOrderLine(orderID, productID, amount, historicalPrice);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		assertNotEquals(0, id);
	}
	
	@Test
	public void updateTest() {
		int id = 1;
		int amount = 10;
		boolean success = false;
		
		try {
			success = orderLineDB.updateOrderLine(id, amount);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		assertTrue(success);
	}
	
}
