package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import Controllers.DataAccessException;
import Database.CompanyDB;
import Model.Company;

class CompanyDBTest {

	static CompanyDB companyDB;
	
	public static void setup() {
		try {
			companyDB = new CompanyDB();
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testInsert() {
		String name = "YouTube";
		String email = "youtube@gmail.com";
		String phoneNumber = "111222333";
		boolean success = false;
		
		try {
			success = companyDB.insertCompany(name, email, phoneNumber);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		assertTrue(success);
	}
	
	@Test
	public void testUpdate() {
		String name = "YouTube123321ebuTouY";
		String email = "younottube123@gmail.com";
		String phoneNumber = "333222111";
		int id = 1;
		boolean success = false;
		
		try {
			success = companyDB.updateCompany(id, name, email, phoneNumber);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		assertTrue(success);
	}
	
	@Test
	public void testFindAll() {
		List<Company> list = new LinkedList<>();
		try {
			list = companyDB.findAll();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertFalse(list.isEmpty());
	}
	
	@Test
	public void testFindByName() {
		String name = "a";
		List<Company> list = new LinkedList<>();
		boolean nameNotFound = false;
		
		try {
			list = companyDB.findByName(name);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		int i = 0;
		while(i < list.size() && !nameNotFound) {
			if(!list.get(i).getName().contains(name)) {
				nameNotFound = true;
			}
		}
		assertFalse(nameNotFound);
	}
	
	@Test
	public void testFindByExactName() {
		String name = "YouTube";
		boolean exists = false;
		
		try {
			exists = companyDB.findByExactName(name);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		assertTrue(exists);
	}
	
	@Test
	public void testFindByID() {
		double id = 2;
		Company company = null;
		
		try {
			company = companyDB.findByID(id);
		} catch (DataAccessException | NullPointerException e) {
			e.printStackTrace();
		}
		
		assertEquals(id, company.getId());
	}
}
