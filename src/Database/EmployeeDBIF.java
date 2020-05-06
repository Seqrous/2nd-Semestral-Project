package Database;

import java.util.List;

import Controllers.DataAccessException;
import Model.Employee;

public interface EmployeeDBIF {

	public boolean insertEmployee(String firstName, String lastName, int phoneNumber, String address) throws DataAccessException;
	
	public boolean updateEmployee(String firstName, String lastName, int phoneNumber, String address, double id) throws DataAccessException;

	public List<Employee> findByName(String name) throws DataAccessException;

	public Employee findByID(double id) throws DataAccessException;
	
	public List<Employee> findAll() throws DataAccessException;
}
