package Database;

import java.util.List;

import Controllers.DataAccessException;
import Model.Company;

public interface CompanyDBIF {

	public boolean insertCompany(String name, String email, String phoneNumber) throws DataAccessException;
	
	public boolean updateCompany(int id, String name, String email, String phoneNumber) throws DataAccessException;

	public List<Company> findByName(String name) throws DataAccessException;
	
	public boolean findByExactName(String name) throws DataAccessException;

	public Company findByID(double id) throws DataAccessException;
	
	public List<Company> findAll() throws DataAccessException;
	
}
