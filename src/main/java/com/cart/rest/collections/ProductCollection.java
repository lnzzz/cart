package com.cart.rest.collections;

import java.util.List;

import com.cart.rest.models.Product;

import java.util.ArrayList;

public class ProductCollection {
	private static ProductCollection pc = new ProductCollection();
	
	private List<Product> products = new ArrayList<Product>();
	
	public static ProductCollection getInstance( ) {
		return pc;
	}

	
	private ProductCollection() {
		Product p1 = new Product(1, "Televisor LED 40 pulgadas", 2000, 0);
		Product p2 = new Product(2, "Televisor 4k 55 pulgadas", 4000, 88);
		Product p3 = new Product(3, "Lavarropas", 1000, 100);
		Product p4 = new Product(4, "Aspiradora", 999.99, 200);
		this.addProduct(p1);
		this.addProduct(p2);
		this.addProduct(p3);
		this.addProduct(p4);
	}
	
	public List<Product> getProducts() {
		return this.products;
	}
	
	public void addProduct(Product product) {
		this.products.add(product);
	}
	
}