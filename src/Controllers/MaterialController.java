package Controllers;

import java.util.List;

import Database.MaterialDB;
import Database.MaterialDBIF;
import Model.Material;

public class MaterialController {

	private MaterialDBIF materialDB;
	
	public MaterialController() throws DataAccessException {
		materialDB = new MaterialDB();
	}
	
	public boolean addMaterial(String name, int locationNumber) throws DataAccessException {
		return materialDB.insertMaterial(name, locationNumber);
	}
	
	public Material findByID(double id) throws DataAccessException {
		return materialDB.findByID(id);
	}

	public List<Material> getAll() throws DataAccessException {
		return materialDB.findAll();
	}
}
