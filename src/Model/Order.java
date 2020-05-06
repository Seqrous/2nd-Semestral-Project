package Model;

import java.math.BigDecimal;
import java.util.List;

public class Order {

	private int id;
	private BigDecimal priceInTotal;
	private int discount;
	private List <OrderLine> orderLineList;
	private State state;
	public static enum State{ACCEPTED, DELIVERING, DELIVERED, CANCELLED};
	
	public Order(int id, BigDecimal priceInTotal, int discount, State state, List<OrderLine> orderLineList) {
		super();
		this.id = id;
		this.setPriceInTotal(priceInTotal);
		this.discount = discount;
		this.state = state;
		this.orderLineList = orderLineList;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public BigDecimal getPriceInTotal() {
		BigDecimal totalPrice = new BigDecimal(0);
		// multiply price of the product by its amount and increase the totalPrice
		for(OrderLine orderline : orderLineList) {
			totalPrice = totalPrice.add(orderline.getProduct().getPrice().multiply(new BigDecimal(orderline.getAmount())));
		}
		return totalPrice;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public List<OrderLine> getOrderLineList() {
		return orderLineList;
	}
	
	public void addOrderLine(OrderLine orderLine) {
		orderLineList.add(orderLine);
	}
	
	public void removeOrderLine(OrderLine orderLine) {
		orderLineList.remove(orderLine);
	}
	public void setPriceInTotal(BigDecimal priceInTotal) {
		this.priceInTotal = priceInTotal;
	}
}
