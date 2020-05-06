package Database;

import java.util.List;

import Controllers.DataAccessException;
import Model.Material;

public interface MaterialDBIF {

	public boolean insertMaterial(String name, int locationNumber) throws DataAccessException;
	
	public boolean updateMaterial(double id, String name, int locationNumber) throws DataAccessException;
	
	public List<Material> findAll() throws DataAccessException;
	
	public Material findByID(double id) throws DataAccessException;
	
}
