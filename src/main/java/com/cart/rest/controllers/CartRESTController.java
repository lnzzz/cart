package com.cart.rest.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cart.rest.dto.CartCreationDTO;
import com.cart.rest.dto.CartResponseDTO;
import com.cart.rest.dto.ProductCreationDTO;
import com.cart.rest.services.CartService;


@RestController
@RequestMapping("/carts")
public class CartRESTController {

	private CartService cartService = new CartService();
	

	
	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces = "application/json")
	public CartResponseDTO getCart(@PathVariable int id) {
		return cartService.getCart(id);
	}
	
	@RequestMapping(value="/{id}/products", method=RequestMethod.GET, produces = "application/json")
	public CartResponseDTO getCartProducts(@PathVariable int id) {
		return cartService.getCartProducts(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, produces="application/json")
	public CartResponseDTO createCart(@RequestBody CartCreationDTO request) {
		return cartService.createCart(request);
	}
	
	
	@RequestMapping(value="/{cartId}/products", method=RequestMethod.POST, produces="application/json")
	public CartResponseDTO addProduct(@RequestBody ProductCreationDTO request, @PathVariable int cartId)  {
		return cartService.addProduct(cartId, request);
	}
	
	@RequestMapping(value="/{cartId}/products/{productId}", method=RequestMethod.DELETE, produces="application/json")
	public CartResponseDTO deleteProduct(@PathVariable int cartId, @PathVariable int productId) {
		return cartService.deleteProduct(cartId, productId);
	}
	
	@RequestMapping(value="/{cartId}/checkout", method=RequestMethod.POST, produces="application/json")
	public CartResponseDTO checkout(@PathVariable int cartId) {
		return cartService.checkout(cartId);
	}
	
	
}