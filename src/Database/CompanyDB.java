package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import Controllers.DataAccessException;
import Controllers.OrderController;
import Model.Company;
import Model.Order;

public class CompanyDB implements CompanyDBIF{

	private Connection connection;
	private DBConnection dbConnection;
	private OrderController orderCtrl;
	
	private final String INSERT_COMPANY_Q = "Insert into Company (name, email, phoneNumber) values (?,?,?)";
	private PreparedStatement insertCompany;
	
	private final String UPDATE_COMPANY_Q = "Update Company set name = ?, email = ?, phoneNumber = ? Where id = ?";
	private PreparedStatement updateCompany;
	
	private final String FIND_BY_NAME_Q = "Select id, name, email, phoneNumber"
										+ " from Company where name like ?";
	private PreparedStatement findByName;
	
	private final String FIND_BY_EXACT_NAME_Q = "Select name"
											  + " from Company where name = ?";
	private PreparedStatement findByExactName;
	
	private final String FIND_BY_ID_Q = "Select id, name, email, phoneNumber"
									  + " from Company where id = ?";
	private PreparedStatement findByID;
	
	private final String FIND_ALL_Q = "Select id, name, email, phoneNumber from Company";
	private PreparedStatement findAll;
	
	private final String GET_LAST_ID_Q = "Select IDENT_CURRENT('Company')";
	private PreparedStatement getLastID;
	
	public CompanyDB() throws DataAccessException {
		init();
	}
	
	public void init() throws DataAccessException {

		dbConnection = DBConnection.getInstance();
		connection = dbConnection.getConnection();
		
		try {
			dbConnection.startTransaction();
			
			insertCompany = connection.prepareStatement(INSERT_COMPANY_Q);
			updateCompany = connection.prepareStatement(UPDATE_COMPANY_Q);
			findByName = connection.prepareStatement(FIND_BY_NAME_Q);
			findByExactName = connection.prepareStatement(FIND_BY_EXACT_NAME_Q);
			findByID = connection.prepareStatement(FIND_BY_ID_Q);
			findAll = connection.prepareStatement(FIND_ALL_Q);
			getLastID = connection.prepareStatement(GET_LAST_ID_Q);
			
			dbConnection.commitTransaction();
		} catch (SQLException e) {
			dbConnection.rollbackTransaction();
			throw new DataAccessException("Failed to prepare statements", e);
			//e.printStackTrace();
		}
		
	}
	
	public boolean insertCompany(String name, String email, String phoneNumber) throws DataAccessException {

		boolean toReturn = false;
		
		try {
			dbConnection.startTransaction();
			insertCompany.setString(1, name);
			insertCompany.setString(2, email);
			insertCompany.setString(3, phoneNumber);
			toReturn = insertCompany.executeUpdate() > 0;
			dbConnection.commitTransaction();
			
		} catch (DataAccessException | SQLException e) {
			dbConnection.rollbackTransaction();
			throw new DataAccessException("Failed to insert Company", e);
			//e.printStackTrace();
		}
		
		return toReturn;
	}
	
	public boolean updateCompany(int id, String name, String email, String phoneNumber) throws DataAccessException {

		boolean toReturn = false;
		
		try {
			dbConnection.startTransaction();
			updateCompany.setString(1, name);
			updateCompany.setString(2, email);
			updateCompany.setString(3, phoneNumber);
			updateCompany.setInt(4, id);	
	
			toReturn = updateCompany.executeUpdate() > 0;
			dbConnection.commitTransaction();
		} catch (DataAccessException | SQLException e) {
			dbConnection.rollbackTransaction();
			throw new DataAccessException("Failed to update company " +  name, e);
		}
		
		return toReturn;
	}
	
	public List<Company> findByName(String name) throws DataAccessException {
		
		ResultSet resultSet = null;
		try {
			//dbConnection.startTransaction();
			findByName.setString(1, "%" + name + "%");
			resultSet = findByName.executeQuery();
			//dbConnection.commitTransaction();
		} catch (SQLException e) {
			//dbConnection.rollbackTransaction();
			throw new DataAccessException("Failed to find by name", e);
			//e.printStackTrace();
		}
		return buildObjects(resultSet);
	}
	
	public boolean findByExactName(String name) throws DataAccessException {
		boolean toReturn = false;
		ResultSet resultSet;

		try {
			dbConnection.startTransaction();
			findByExactName.setString(1, name);
			resultSet = findByExactName.executeQuery();
			dbConnection.commitTransaction();
		} catch (DataAccessException | SQLException e) {
			dbConnection.rollbackTransaction();
			throw new DataAccessException("Failed to find by exact name", e);
			//e.printStackTrace();
		}
		try {
			if(!resultSet.isBeforeFirst()) {
				toReturn = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return toReturn;
		
	}
	
	public Company findByID(double id) throws DataAccessException {

		ResultSet resultSet;
		
		try {
			dbConnection.startTransaction();
			findByID.setDouble(1, id);
			resultSet = findByID.executeQuery();
			dbConnection.commitTransaction();
		} catch (SQLException e) {
			dbConnection.rollbackTransaction();
			throw new DataAccessException("Failed to find by id", e);
			//e.printStackTrace();
		}
		return buildObject(resultSet);
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

	public List<Company> findAll() throws DataAccessException {
		
		ResultSet resultSet = null;
		
		try {
			dbConnection.startTransaction();
			resultSet = findAll.executeQuery();
			dbConnection.commitTransaction();
		} catch (SQLException e) {
			dbConnection.rollbackTransaction();
			throw new DataAccessException("Failed to load all companies", e);
			
			//e.printStackTrace();
		} 
		
		System.out.println(resultSet);
		return buildObjects(resultSet);
		
	}
	
	public Company buildObject(ResultSet resultSet) throws DataAccessException {
	
		Company company = null;
		
		try {
			int id = resultSet.getInt(1);
			String name = resultSet.getString(2);
			String email = resultSet.getString(3);
			String phoneNumber = resultSet.getString(4);
			
			company = new Company(id, name, email, phoneNumber, new ArrayList<Order>());
		} catch (SQLException e) {
			throw new DataAccessException("Failed to build a company object", e);
			//e.printStackTrace();
		} 
		return company;
			
	}
	
	public List<Company> buildObjects(ResultSet resultSet) throws DataAccessException {
		
		Company company = null;
		LinkedList <Company> companyList = new LinkedList<>();

		try {

			while(resultSet.next()) {
				System.out.println("works");
				company = buildObject(resultSet);
				companyList.add(company);
			}
			
		} catch (SQLException e) {
			//throw new DataAccessException("Failed to build a company object", e);
			e.printStackTrace();
		}
		return companyList;
			
	}
}
