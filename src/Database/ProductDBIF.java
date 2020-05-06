package Database;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import Controllers.DataAccessException;
import Model.ProductDescription;

public interface ProductDBIF {

	public boolean insertProduct(int materialID, String name, BigDecimal price) throws DataAccessException;
	
	public boolean updateProduct(int materialID, String name, BigDecimal price, int id) throws DataAccessException;
	
	public List<ProductDescription> findAll() throws DataAccessException;
		
	public ProductDescription findByID(int id) throws DataAccessException;
	
	public ProductDescription findByExactName(String name) throws DataAccessException;
}
