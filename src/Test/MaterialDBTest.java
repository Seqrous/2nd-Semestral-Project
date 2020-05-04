package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Controllers.DataAccessException;
import Database.MaterialDB;

class MaterialDBTest {

	private static MaterialDB materialDB;
	
	public static void setup() {
		try {
			materialDB = new MaterialDB();
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void insertTest() {
		String name = "Amelinium";
		int locationNumber = 1;
		boolean success = false;
		
		try {
			success = materialDB.insertMaterial(name, locationNumber);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	
		assertTrue(success);
	}
	
	@Test
	public void updateTest() {
		double id = 1;
		String name = "Mythril";
		int locationNumber = 2;
		boolean success = false;
		
		try {
			success = materialDB.updateMaterial(id, name, locationNumber);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		assertTrue(success);
	}
	
	@Test
	public void findAll() {
		
	}

}
