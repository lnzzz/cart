package com.cart.rest.models;

public class Product {
	private int id;
	private String description;
	private double unit_price;
	private int stock;
	
	public Product(int id, String description, double unit_price, int stock) {
		this.setId(id);
		this.setDescription(description);
		this.setUnit_price(unit_price);
		this.setStock(stock);
	}
	
	public Product(int id, String description, double unit_price) {
		this.setId(id);
		this.setDescription(description);
		this.setUnit_price(unit_price);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getUnit_price() {
		return unit_price;
	}
	public void setUnit_price(double unit_price) {
		this.unit_price = unit_price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
}