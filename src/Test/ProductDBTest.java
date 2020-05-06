package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import Controllers.DataAccessException;
import Database.ProductDB;
import Model.Company;
import Model.ProductDescription;

class ProductDBTest {
	
	private static ProductDB productDB;

	public static void setup() {
		try {
			productDB = new ProductDB();
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void insertTest() {
		int materialID = 1;
		String name = "SomeMetalPart";
		BigDecimal price = new BigDecimal(29.99);
		boolean success = false;
		
		try {
			success = productDB.insertProduct(materialID, name, price);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		assertTrue(success);
	}
	
	@Test
	public void updateTest() {
		int id = 1;
		int materialID = 2;
		String name = "DifferentMetalPart";
		BigDecimal price = new BigDecimal(30.01);
		boolean success = false;
		
		try {
			success = productDB.updateProduct(materialID, name, price, id);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		assertTrue(success);
	}
	
	@Test
	public void findAllTest() {
		List<ProductDescription> list = new LinkedList<>();
		
		try {
			list = productDB.findAll();
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		assertFalse(list.isEmpty());
	}
	
	@Test
	public void testFindByExactName() {
		String name = "DifferentMetalPart";
		ProductDescription product = null;
		
		try {
			product = productDB.findByExactName(name);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		assertEquals(name, product.getName());
	}
	
	@Test
	public void testFindByID() {
		int id = 1;
		ProductDescription product = null;
		
		try {
			product = productDB.findByID(id);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		assertEquals(id, product.getId());
	}
}
