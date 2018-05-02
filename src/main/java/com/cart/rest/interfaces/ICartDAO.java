package com.cart.rest.interfaces;

import com.cart.rest.dto.CartCreationDTO;
import com.cart.rest.dto.CartResponseDTO;
import com.cart.rest.dto.ProductCreationDTO;

public interface ICartDAO {
	public CartResponseDTO getCartById(int id);
	public CartResponseDTO getCartProductsById(int id);
	public CartResponseDTO create(CartCreationDTO cart);
	public CartResponseDTO addProduct(int cartId, ProductCreationDTO product);
	public CartResponseDTO deleteProduct(int cartId, int productId);
	public CartResponseDTO checkout(int cartId);
}