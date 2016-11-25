package edu.mum.farmer.app.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Order_1")
public class Order implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;

	@Valid
	@OneToOne(cascade = CascadeType.PERSIST)
	private BillingDetails billingDetails;

	@Valid
	@OneToOne(cascade = CascadeType.PERSIST)
	private Address shippingAddress;

	@OneToOne(mappedBy = "order")
	private Member member;

	@DateTimeFormat(pattern = "yyyy-mm-dd")
	@Temporal(TemporalType.DATE)
	private Date shippingDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public BillingDetails getBillingDetails() {
		return billingDetails;
	}

	public void setBillingDetails(BillingDetails billingDetails) {
		this.billingDetails = billingDetails;
	}

	public Date getShippingDate() {
		return shippingDate;
	}

	public void setShippingDate(Date shippingDate) {
		this.shippingDate = shippingDate;
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	@OneToMany(cascade = CascadeType.ALL)
	private List<OrderLine> orderLineList = new ArrayList<OrderLine>();

	public List<OrderLine> getOrderLineList() {
		return orderLineList;
	}

	public void setOrderLineList(List<OrderLine> orderLineList) {
		this.orderLineList = orderLineList;
	}

	private double totalPrice;

	public void addProduct(Product p) {
		if (!getProductList().contains(p)) {
			OrderLine oL = new OrderLine(p);
			orderLineList.add(oL);
		} else {
			OrderLine oL = getOrderLineForProduct(p);
			oL.setQuantity(oL.getQuantity() + 1);
		}
		calculateTotalPrice();
	}

	public void removeProduct(Product p) {
		if (getProductList().contains(p)) {
			OrderLine oL = getOrderLineForProduct(p);
			if (oL.getQuantity() > 1) {
				oL.setQuantity(oL.getQuantity() - 1);
			} else {
				orderLineList.remove(oL);
			}
		}
		calculateTotalPrice();
	}

	public List<Product> getProductList() {
		List<Product> productList = new ArrayList<Product>();
		for (OrderLine oL : orderLineList) {
			productList.add(oL.getProduct());
		}
		return productList;
	}

	private OrderLine getOrderLineForProduct(Product p) {
		if (getProductList().contains(p)) {
			for (OrderLine oL : orderLineList) {
				if (oL.getProduct().equals(p)) {
					return oL;
				}
			}
		}
		return null;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public void calculateTotalPrice() {
		totalPrice = 0;
		for (OrderLine oL : orderLineList) {
			totalPrice += oL.getPrice();
		}
		setTotalPrice(totalPrice);
	}

}
