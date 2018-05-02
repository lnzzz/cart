package com.cart.rest.services;


import com.cart.rest.daos.CartDAO;
import com.cart.rest.interfaces.ICartService;
import com.cart.rest.dto.CartCreationDTO;
import com.cart.rest.dto.CartResponseDTO;
import com.cart.rest.dto.ProductCreationDTO;

public class CartService implements ICartService {
	
	private final CartDAO cartDAO = new CartDAO();

	
	public CartResponseDTO getCart(int id) {
		CartResponseDTO response = cartDAO.getCartById(id);
		return response;
	}
	
	public CartResponseDTO getCartProducts(int id) {
		CartResponseDTO response = cartDAO.getCartProductsById(id);
		return response;
	}
	
	
	public CartResponseDTO createCart(CartCreationDTO cart) {
		CartResponseDTO response = cartDAO.create(cart);
		return response;
	}
	
	public CartResponseDTO addProduct(int cartId, ProductCreationDTO product) {
		CartResponseDTO response = cartDAO.addProduct(cartId, product);
		return response;
	}
	
	public CartResponseDTO deleteProduct(int cartId, int productId) {
		CartResponseDTO response = cartDAO.deleteProduct(cartId, productId);
		return response;
	}
	
	public CartResponseDTO checkout(int cartId) {
		CartResponseDTO response = cartDAO.checkout(cartId);
		return response;
	}
}