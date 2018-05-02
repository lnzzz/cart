package com.cart.rest.interfaces;

import com.cart.rest.dto.CartCreationDTO;
import com.cart.rest.dto.CartResponseDTO;
import com.cart.rest.dto.ProductCreationDTO;

public interface ICartService {
	public CartResponseDTO getCart (int id);
	public CartResponseDTO createCart(CartCreationDTO cart);
	public CartResponseDTO getCartProducts(int id);
	public CartResponseDTO addProduct(int cartId, ProductCreationDTO product);
	public CartResponseDTO deleteProduct(int cartId, int productId);
	public CartResponseDTO checkout(int cartId);
}