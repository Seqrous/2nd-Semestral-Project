package Database;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import Controllers.DataAccessException;
import Controllers.MaterialController;
import Model.Material;
import Model.ProductDescription;

public class ProductDB implements ProductDBIF {
	
	DBConnection dbConnection;
	Connection connection;

	private static final String INSERT_PRODUCT_Q = "Insert into Product (materialID, name, price) values (?,?,?)";
	private PreparedStatement insertProduct;
	
	private static final String UPDATE_PRODUCT_Q = "Update Product set materialID = ?, name = ?, price = ? where id = ?";
	private PreparedStatement updateProduct;
	
	private static final String FIND_BY_NAME_Q = "Select id, materialID, name, price "
											   + "from Product where name like ?";
	private PreparedStatement findByName;
	
	private static final String FIND_BY_EXACT_NAME_Q = "Select id, materialID, name, price "
													 + "from Product where name = ?";
	private PreparedStatement findByExactName;
	
	private static final String FIND_ALL_Q = "Select id, materialID, name, price "
										   + "from Product";
	private PreparedStatement findAll;
	
	private static final String FIND_BY_ID_Q = "Select id, materialID, name, price "
											 + "from Product where id = ?";
	private PreparedStatement findByID;
	
	public ProductDB() throws DataAccessException {
		init();
	}
	
	public void init() throws DataAccessException {
		dbConnection = DBConnection.getInstance();
		connection = dbConnection.getConnection();
		
		try {
			dbConnection.startTransaction();
			insertProduct = connection.prepareStatement(INSERT_PRODUCT_Q);
			updateProduct = connection.prepareStatement(UPDATE_PRODUCT_Q);
			findByName = connection.prepareStatement(FIND_BY_NAME_Q);
			findByExactName = connection.prepareStatement(FIND_BY_EXACT_NAME_Q);
			findAll = connection.prepareStatement(FIND_ALL_Q);
			findByID = connection.prepareStatement(FIND_BY_ID_Q);
			dbConnection.commitTransaction();
		} catch (SQLException e) {
			dbConnection.rollbackTransaction();
			throw new DataAccessException("Failed to prepare the statements", e);
			//e.printStackTrace();
		}
	}
	
	public boolean insertProduct(int materialID, String name, BigDecimal price) throws DataAccessException {
		boolean toReturn = false;
		
		try {
			dbConnection.startTransaction();
			insertProduct.setInt(1, materialID);
			insertProduct.setString(2, name);
			insertProduct.setBigDecimal(3, price);
			toReturn = insertProduct.executeUpdate() > 0;
			dbConnection.commitTransaction();
		} catch (DataAccessException | SQLException e) {
			dbConnection.rollbackTransaction();
			//throw new DataAccessException("Failed to insert the product", e);
			e.printStackTrace();
		}
		return toReturn;
	}
	
	public boolean updateProduct(int materialID, String name, BigDecimal price, int id) throws DataAccessException {
		boolean toReturn = false;
		
		try {
			dbConnection.startTransaction();
			updateProduct.setInt(1, materialID);
			updateProduct.setString(2, name);
			updateProduct.setBigDecimal(3, price);
			updateProduct.setInt(4, id);
			toReturn = updateProduct.executeUpdate() > 0;
			dbConnection.commitTransaction();
			
		} catch (DataAccessException | SQLException e) {
			dbConnection.rollbackTransaction();
			throw new DataAccessException("Failed to update the product", e);
			//e.printStackTrace();
		}
		
		return toReturn;
	}
	
	public List<ProductDescription> findAll() throws DataAccessException {
		ResultSet resultSet = null;
		
		try {
			dbConnection.startTransaction();
			resultSet = findAll.executeQuery();
			dbConnection.commitTransaction();
		} catch (DataAccessException | SQLException e) {
			dbConnection.rollbackTransaction();
			throw new DataAccessException("Failed to find all the products", e);
			// e.printStackTrace();
		}
		
		return buildObjects(resultSet);
	}
	
	public ProductDescription findByID(int id) throws DataAccessException {
		ResultSet resultSet = null;
		
		try {
			dbConnection.startTransaction();
			findByID.setInt(1,id);
			resultSet = findByID.executeQuery();
			dbConnection.commitTransaction();
			
		} catch (SQLException | DataAccessException e) {
			dbConnection.rollbackTransaction();
			//throw new DataAccessException("Failed to find product by id", e);
			e.printStackTrace();
		}
		
		return buildObject(resultSet);
	}
	
	public ProductDescription findByExactName(String name) throws DataAccessException {
		ResultSet resultSet;
		
		try {
			dbConnection.startTransaction();
			findByExactName.setString(1, name);
			resultSet = findByExactName.executeQuery();
			dbConnection.commitTransaction();
		} catch (DataAccessException | SQLException e) {
			dbConnection.rollbackTransaction();
			throw new DataAccessException("Failed to find product by name", e);
			//e.printStackTrace();
		}
		return buildObject(resultSet);
		
	}
	
	private List<ProductDescription> buildObjects(ResultSet resultSet) throws DataAccessException {
		List<ProductDescription> productList = new LinkedList<>();
		if(resultSet != null) {
			try {
				while(resultSet.next()) {
					int id = resultSet.getInt(1);
					int materialID = resultSet.getInt(2);
					String name = resultSet.getString(3);
					BigDecimal price = resultSet.getBigDecimal(4);
						
					MaterialController materialCtrl = new MaterialController();
					Material material = materialCtrl.findByID(materialID);
						
					ProductDescription product = new ProductDescription(id, name, price, material);
					productList.add(product);
				}
			} catch (SQLException | DataAccessException e) {
				throw new DataAccessException("Failed to build materials", e);
				//e.printStackTrace();
			}
			return productList;
		}
		else {
			return null;
		}
		
	}
	
	private ProductDescription buildObject(ResultSet resultSet) throws DataAccessException {
		ProductDescription product = null;
			
		try {
			while(resultSet.next()) {
				int id = resultSet.getInt(1);
				int materialID = resultSet.getInt(2);
				String name = resultSet.getString(3);
				BigDecimal price = resultSet.getBigDecimal(4);
					
				MaterialController materialCtrl = new MaterialController();
				Material material = materialCtrl.findByID(materialID);
					
				product = new ProductDescription(id, name, price, material);
			}
		} catch (SQLException | DataAccessException e) {
			throw new DataAccessException("Failed to build materials", e);
			//e.printStackTrace();
		}
		
		return product;
	}

}
