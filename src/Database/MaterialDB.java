package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import Controllers.DataAccessException;
import Model.Material;

public class MaterialDB implements MaterialDBIF {

	DBConnection dbConnection;
	Connection connection;
	
	private static final String INSERT_MATERIAL_Q = "Insert into Material (name) values (?)";
	private PreparedStatement insertMaterial;
	
	private static final String UPDATE_MATERIAL_Q = "Update Material set name = ? where id = ?";
	private PreparedStatement updateMaterial;
	
	private static final String FIND_ALL_Q = "Select id, name from Material";
	private PreparedStatement findAll;
	
	private static final String FIND_BY_NAME_Q = "Select id, name from Material where name = ?";
	private PreparedStatement findByName;
	
	private static final String FIND_BY_ID_Q = "Select id, name from Material where id = ?";
	private PreparedStatement findByID;
	
	public MaterialDB() throws DataAccessException {
		init();
	}
	
	public void init() throws DataAccessException {
		dbConnection = DBConnection.getInstance();
		connection = dbConnection.getConnection();
		
		try {
			dbConnection.startTransaction();
			insertMaterial = connection.prepareStatement(INSERT_MATERIAL_Q);
			updateMaterial = connection.prepareStatement(UPDATE_MATERIAL_Q);
			findAll = connection.prepareStatement(FIND_ALL_Q);
			findByName = connection.prepareStatement(FIND_BY_NAME_Q);
			findByID = connection.prepareStatement(FIND_BY_ID_Q);
		} catch (SQLException e) {
			dbConnection.rollbackTransaction();
			throw new DataAccessException("Failed to prepare the statements", e);
			//e.printStackTrace();
		}
		
	}
	public boolean insertMaterial(String name, int locationNumber) throws DataAccessException {
		boolean toReturn = false;
		
		try {
			dbConnection.startTransaction();
			insertMaterial.setString(1, name);
			insertMaterial.setInt(2, locationNumber);
			toReturn = insertMaterial.executeUpdate() > 0;
			dbConnection.commitTransaction();
			
		} catch (SQLException e) {
			dbConnection.rollbackTransaction();
			throw new DataAccessException("Failed to insert the material", e);
			//e.printStackTrace();
		}
		
		return toReturn;
	}
	
	public boolean updateMaterial(double id, String name, int locationNumber) throws DataAccessException {
		boolean toReturn = false;
		
		try {
			dbConnection.startTransaction();
			updateMaterial.setString(1, name);
			updateMaterial.setInt(2, locationNumber);
			updateMaterial.setDouble(3, id);
			toReturn = updateMaterial.executeUpdate() > 0;
			dbConnection.commitTransaction();
		} catch (DataAccessException | SQLException e) {
			dbConnection.rollbackTransaction();
			throw new DataAccessException("Failed to update the Material", e);
			//e.printStackTrace();
		}
		
		return toReturn;
	}
	
	public List<Material> findAll() throws DataAccessException {
		ResultSet resultSet;
		
		try {
			dbConnection.startTransaction();
			resultSet = findAll.executeQuery();
			dbConnection.commitTransaction();
		} catch (DataAccessException | SQLException e) {
			dbConnection.rollbackTransaction();
			throw new DataAccessException("Failed to find all materials", e);
			//e.printStackTrace();
		}
		
		return buildObjects(resultSet);
	}
	
	public Material findByID(double id) throws DataAccessException {
		ResultSet resultSet;
		
		try {
			dbConnection.startTransaction();
			findByID.setDouble(1, id);
			resultSet = findByID.executeQuery();
			dbConnection.commitTransaction();
		} catch (SQLException e) {
			dbConnection.rollbackTransaction();
			throw new DataAccessException("Failed to find the material by id", e);
			//e.printStackTrace();
		}
		
		return buildObject(resultSet);
	}
	
	private List <Material> buildObjects(ResultSet resultSet) throws DataAccessException {
		List<Material> materialList = new LinkedList<>();
		
		try {
			while(resultSet.next()) {
				double id = resultSet.getDouble(1);
				String name = resultSet.getString(2);
				
				Material material = new Material(id, name);
				
				materialList.add(material);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Failed to build objects", e);
			//e.printStackTrace();
		}
		return materialList;
	}
	
	private Material buildObject(ResultSet resultSet) throws DataAccessException {
		Material material = null;
		
		try {
			while(resultSet.next()) {
				double id = resultSet.getDouble(1);
				String name = resultSet.getString(2);
				
				material = new Material(id, name);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Failed to build objects", e);
			//e.printStackTrace();
		}
		return material;
	}

}
