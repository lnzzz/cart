package com.cart.rest.collections;

import java.util.List;

import com.cart.rest.models.Cart;
import com.cart.rest.models.Product;

import java.util.ArrayList;
import java.util.Date;

public class CartCollection {
	private static CartCollection cc = new CartCollection(); 
	
	
	private List<Cart> carts = new ArrayList<Cart>();

	
	public static CartCollection getInstance( ) {
		return cc;
	}

	
	private CartCollection() {
		Cart cart1 = new Cart(1, "Luis Recchini", "luisrecchini@gmail.com", new Date(), 2132.22, "NEW");
		cart1.getProducts().add(new Product(1, "Televisor LED 40 pulgadas", 2000));
		cart1.getProducts().add(new Product(2, "Televisor 4k 55 pulgadas", 4000));
		cart1.getProducts().add(new Product(3, "Lavarropas", 1000));
		Cart cart2 = new Cart(2, "Jorge Lopez", "jorgelopez@gmail.com", new Date(), 2132.22, "NEW");
		cart2.getProducts().add(new Product(1, "Televisor LED 40 pulgadas", 2000));
		Cart cart3 = new Cart(3, "Juan Perez", "juanperez@gmail.com", new Date(), 2132.22, "NEW");
		cart3.getProducts().add(new Product(4, "Aspiradora", 999.99));

		this.addCart(cart1);
		this.addCart(cart2);
		this.addCart(cart3);
		
	}
	
	public List<Cart> getCarts() {
		return this.carts;
	}
	
	public void addCart(Cart cart) {
		this.carts.add(cart);
	}
	
}