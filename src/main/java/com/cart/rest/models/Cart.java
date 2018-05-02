package com.cart.rest.models;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

public class Cart {
	private int id;
	private String fullName;
	private String email;
	private Date creationDate;
	private double total;
	private String status;
	private List<Product> products = new ArrayList<Product>();
	
	public Cart(int id, String fullName, String email, Date creationDate, double total, String status) {
		this.setId(id);
		this.setFullName(fullName);
		this.setEmail(email);
		this.setCreationDate(creationDate);
		this.setTotal(total);
		this.setStatus(status);
	}
	
	public Cart() {
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Date getCreationDate() {
		return creationDate;
	}
	
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	public double getTotal() {
		return total;
	}
	
	public void setTotal(double total) {
		this.total = total;
	}
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public ArrayList<Product> getProducts() {
		return (ArrayList<Product>) products;
	}
	
	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}
}