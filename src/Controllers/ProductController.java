package Controllers;

import java.math.BigDecimal;
import java.util.List;

import Database.ProductDB;
import Database.ProductDBIF;
import Model.ProductDescription;

public class ProductController {

	private ProductDBIF productDB;
	
	public ProductController() throws DataAccessException {
		productDB = new ProductDB();
	}
	
	public boolean addProduct(int materialID, String name, BigDecimal price) throws DataAccessException {
		return productDB.insertProduct(materialID, name, price);
	}
	
	public boolean updateProduct(int materialID, String name, int locationNumber, BigDecimal price, int id) throws DataAccessException {
		return productDB.updateProduct(materialID, name, price, id);
	}
	
	public ProductDescription findByID(int id) throws DataAccessException {
		return productDB.findByID(id);
	}
	
	public List<ProductDescription> findAll() throws DataAccessException {
		return productDB.findAll();
	}
	
	public ProductDescription findByExactName(String name) throws DataAccessException {
		return productDB.findByExactName(name);
	}
}
