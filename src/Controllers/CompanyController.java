package Controllers;

import java.util.List;

import Database.CompanyDB;
import Database.CompanyDBIF;
import Model.Company;

public class CompanyController {

	CompanyDBIF companyDB;
	
	public CompanyController() throws DataAccessException{
		companyDB = new CompanyDB();
	}
	
	public boolean addCompany(String name, String email, String phoneNumber) throws DataAccessException {
		return companyDB.insertCompany(name, email, phoneNumber);
	}
	
	public boolean updateCompany(int id, String name, String email, String phoneNumber) throws DataAccessException {
		boolean exists = false;
		exists = companyDB.findByExactName(name);
		System.out.println(exists);
		// if name is not used already - let update
		if(!exists) {
			return companyDB.updateCompany(id, name, email, phoneNumber);
		}
		// if name is used - do not allow to update
		else {
			return false;
		}
	}
	
	public List<Company> findByName(String name) throws DataAccessException {
		return companyDB.findByName(name);
	}
	
	public Company findByID(double id) throws DataAccessException {
		return companyDB.findByID(id);
	}
	
	public List<Company> findAll() throws DataAccessException {
		return companyDB.findAll();
	}

	public boolean findByExactName(String name) throws DataAccessException {
		return companyDB.findByExactName(name);
	}
	
}
