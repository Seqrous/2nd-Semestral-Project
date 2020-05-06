package Model;

import java.math.BigDecimal;

public class OrderLine {

	private int id;
	private int amount;
	private BigDecimal historicalPrice;
	private ProductDescription product;
	public OrderLine(int id, int amount, BigDecimal historicalPrice, ProductDescription product) {
		this.id = id;
		this.amount = amount;
		this.historicalPrice = historicalPrice;
		this.product = product;
	}
	public int getID() {
		return id;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public BigDecimal getHistoricalPrice() {
		return historicalPrice;
	}
	public void setHistoricalPrice(BigDecimal historicalPrice) {
		this.historicalPrice = historicalPrice;
	}
	public ProductDescription getProduct() {
		return product;
	}
	public void setProduct(ProductDescription product) {
		this.product = product;
	}

}
