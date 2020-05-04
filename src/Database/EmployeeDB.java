package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import Controllers.DataAccessException;
import Model.Employee;

public class EmployeeDB implements EmployeeDBIF{

	Connection connection;
	DBConnection dbConnection;
	
	private final String INSERT_EMPLOYEE_Q = "Insert into Employee (firstName, lastName, phoneNumber, address) values (?,?,?,?)";
	private PreparedStatement insertEmployee;
	
	private final String UPDATE_EMPLOYEE_Q = "Update Employee set firstName = ?, lastName = ?, phoneNumber = ?, address = ?.  where id = ?";
	private PreparedStatement updateEmployee;
	
	private final String FIND_BY_NAME_Q = "Select id, firstName, lastName, phoneNumber, address "
										+ "from Employee where name like %?%";
	private PreparedStatement findByName;
	
	private final String FIND_BY_ID_Q = "Select id, firstName, lastName, phoneNumber, address "
									  + "from Employee where id = ?";
	private PreparedStatement findByID;
	
	private final String FIND_ALL_Q = "Select id, firstName, lastName, phoneNumber, address "
									+ "from Employee";
	private PreparedStatement findAll;
	
	private final String GET_LAST_ID_Q = "Select IDENT_CURRENT(Employee)";
	private PreparedStatement getLastID;
	
	public EmployeeDB() throws DataAccessException {
		init();
	}
	
	public void init() throws DataAccessException {
		
		dbConnection = DBConnection.getInstance();
		connection = dbConnection.getConnection();
		
		try {
			dbConnection.startTransaction();
			insertEmployee = connection.prepareStatement(INSERT_EMPLOYEE_Q);
			updateEmployee = connection.prepareStatement(UPDATE_EMPLOYEE_Q);
			findByName = connection.prepareStatement(FIND_BY_NAME_Q);
			findByID = connection.prepareStatement(FIND_BY_ID_Q);
			findAll = connection.prepareStatement(FIND_ALL_Q);
			getLastID = connection.prepareStatement(GET_LAST_ID_Q);
			dbConnection.commitTransaction();
			
		} catch (SQLException | DataAccessException e) {
			dbConnection.rollbackTransaction();
			throw new DataAccessException("Failed preparing the statements", e);
			//e.printStackTrace();
		}
		
	}

	public boolean insertEmployee(String firstName, String lastName, int phoneNumber, String address) throws DataAccessException {
		boolean toReturn = false;
		
		try {
			dbConnection.startTransaction();
			insertEmployee.setString(1, firstName);
			insertEmployee.setString(2, lastName);
			insertEmployee.setInt(3, phoneNumber);
			insertEmployee.setString(4, address);
			
			toReturn = insertEmployee.executeUpdate() > 0;
			dbConnection.commitTransaction();
		} catch (SQLException e) {
			dbConnection.rollbackTransaction();
			throw new DataAccessException("failed to insert Employee", e);
			//e.printStackTrace();
		}
		return toReturn;
	}
	
	public boolean updateEmployee(String firstName, String lastName, int phoneNumber, String address, double id) throws DataAccessException {
		boolean toReturn = false;
	
		try {
			dbConnection.startTransaction();
			updateEmployee.setString(1, firstName);
			updateEmployee.setString(2, lastName);
			updateEmployee.setInt(3, phoneNumber);
			updateEmployee.setString(4, address);
			updateEmployee.setDouble(5, id);
			
			toReturn = updateEmployee.executeUpdate() > 0;
			
			dbConnection.commitTransaction();
		} catch (SQLException e) {
			throw new DataAccessException("Failed to update ", e);
			//e.printStackTrace();
		}
		
		return toReturn;
	}

	public List<Employee> findByName(String name) throws DataAccessException {
		ResultSet resultSet;
		
		try {
			dbConnection.startTransaction();
			findByName.setString(1, name);
			resultSet = findByName.executeQuery();
			dbConnection.commitTransaction();
		} catch (SQLException e) {
			throw new DataAccessException("Failed to find employee by name", e);
			//e.printStackTrace();
		}
		
		return buildObjects(resultSet);
	}

	public Employee findByID(double id) throws DataAccessException {
		ResultSet resultSet;
		
		try {
			dbConnection.startTransaction();
			findByID.setDouble(1, id);
			resultSet = findByID.executeQuery();
			dbConnection.commitTransaction();
		} catch (SQLException e) {
			throw new DataAccessException("Failed to find employee by id", e);
			//e.printStackTrace();
		}
		
		return buildObject(resultSet);
	}
	
	public List<Employee> findAll() throws DataAccessException {
		
		ResultSet resultSet;
		
		try {
			dbConnection.startTransaction();
			resultSet = findAll.executeQuery();
			dbConnection.commitTransaction();
		} catch (SQLException e) {
			throw new DataAccessException("Failed to load all employees", e);
			//e.printStackTrace();
		}
		
		return buildObjects(resultSet);
	}

	
	public double getLastID() throws DataAccessException {
		double toReturn;
		
		try {
			dbConnection.startTransaction();
			toReturn = getLastID.executeQuery().getDouble(1);
		} catch (SQLException e) {
			dbConnection.rollbackTransaction();
			throw new DataAccessException("Failed to get last ID", e);
			//e.printStackTrace();
		}
		return toReturn;
	}
	
	public Employee buildObject(ResultSet resultSet) throws DataAccessException {
		Employee employee = null;
		
		try {
			while(resultSet.next()) {
				double id = resultSet.getDouble(1);
				String firstName = resultSet.getString(2);
				String lastName = resultSet.getString(3);
				int phoneNumber = resultSet.getInt(4);
				String address = resultSet.getString(5);
			
				employee = new Employee(id, firstName, lastName, phoneNumber, address);
			}
		} catch(SQLException e) {
			throw new DataAccessException("Failed to build an object", e);
		}
		return employee;
	}
	
	public List<Employee> buildObjects(ResultSet resultSet) throws DataAccessException {
		LinkedList<Employee> employeeList = new LinkedList<>();
		
		try {
			while(resultSet.next()) {
					
				double id = resultSet.getDouble(1);
				String firstName = resultSet.getString(2);
				String lastName = resultSet.getString(3);
				int phoneNumber = resultSet.getInt(4);
				String address = resultSet.getString(5);
				employeeList.add(new Employee(id, firstName, lastName, phoneNumber, address));
			}
				
		} catch (SQLException e) {
			throw new DataAccessException("Failed to build objects", e);
				//e.printStackTrace();
		}
		return employeeList;
	}
}
