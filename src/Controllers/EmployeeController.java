package Controllers;

import java.util.List;

import Database.EmployeeDB;
import Database.EmployeeDBIF;
import Model.Employee;

public class EmployeeController {

	EmployeeDBIF employeeDB;
	
	public EmployeeController() throws DataAccessException {
		employeeDB = new EmployeeDB();
	}
	
	public boolean addEmployee(String firstName, String lastName, int phoneNumber, String address) throws DataAccessException {
		return employeeDB.insertEmployee(firstName, lastName, phoneNumber, address);
	}
	
	public boolean updateEmployee(String firstName, String lastName, int phoneNumber, String address, double id) throws DataAccessException {
		return employeeDB.updateEmployee(firstName, lastName, phoneNumber, address, id);
	}
	
	public List<Employee> findByName(String name) throws DataAccessException{
		return employeeDB.findByName(name);
	}
	
	public List<Employee> findyAll() throws DataAccessException {
		return employeeDB.findAll();
	}
	
	public Employee findByID(double id) throws DataAccessException {
		return employeeDB.findByID(id);
	}
}
