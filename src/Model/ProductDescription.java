package Model;

import java.math.BigDecimal;

public class ProductDescription  {

	private int id;
	private String name;
	private BigDecimal price;
	private Material material;
	public ProductDescription(int id, String name, BigDecimal price, Material material) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.material = material;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Material getMaterial() {
		return material;
	}
	public void setMaterial(Material material) {
		this.material = material;
	}
}
