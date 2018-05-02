package com.cart.rest.daos;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import com.cart.rest.models.Cart;
import com.cart.rest.models.CartCollection;
import com.cart.rest.dto.CartCreationDTO;
import com.cart.rest.dto.CartResponseDTO;
import com.cart.rest.dto.ProductCreationDTO;
import com.cart.rest.interfaces.ICartDAO;
import com.cart.rest.models.Product;
import com.cart.rest.models.ProductCollection;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CartDAO implements ICartDAO {
	
	private static final int SUCCESS_CODE = 200;
	private static final int ERROR_CODE = 500;
	private static final int NOT_FOUND_CODE = 404;
	private final ObjectMapper mapper = new ObjectMapper();
	private static final CartCollection carts = CartCollection.getInstance();
	private static final ProductCollection products = ProductCollection.getInstance();
	
	
	public CartResponseDTO getCartById(int id) {
		CartResponseDTO response = new CartResponseDTO();
		try {
			Cart cart = carts.getCarts().stream().filter(c -> c.getId() == id).findFirst().get();
			response.setCode(SUCCESS_CODE);
			response.setMessage(mapper.writeValueAsString(cart));
		} catch (Exception e) {
			response.setCode(NOT_FOUND_CODE);
			response.setMessage("Couldn't find cart.");
		}
		return response;
	}
	
	public CartResponseDTO getCartProductsById(int id) {
		CartResponseDTO response = new CartResponseDTO();
		try {
			Cart cart = carts.getCarts().stream().filter(c -> c.getId() == id).findFirst().get();
			response.setCode(SUCCESS_CODE);
			response.setMessage(mapper.writeValueAsString(cart.getProducts()));
		} catch (Exception e) {
			response.setCode(NOT_FOUND_CODE);
			response.setMessage("Couldn't find cart.");
		}
		return response;
	}

	public CartResponseDTO create(CartCreationDTO cart) {
		CartResponseDTO response = new CartResponseDTO();
		try {
			Cart crt = carts.getCarts().stream().max(Comparator.comparing(Cart::getId)).get();
			int newId = crt.getId()+1;
			Cart tmpCart = new Cart();
			tmpCart.setId(newId);
			tmpCart.setFullName(cart.getFullName());
			tmpCart.setEmail(cart.getEmail());
			tmpCart.setTotal(0);
			tmpCart.setCreationDate(new Date());
			tmpCart.setStatus("NEW");
			tmpCart.setProducts(new ArrayList<Product>());
			carts.addCart(tmpCart);
			response.setCode(SUCCESS_CODE);
			response.setMessage(mapper.writeValueAsString(tmpCart));
		} catch (Exception e) {
			response.setCode(ERROR_CODE);
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	public CartResponseDTO addProduct(int cartId, ProductCreationDTO product) {
		CartResponseDTO response = new CartResponseDTO();
		try {
			Cart crt = carts.getCarts().stream().filter(c -> c.getId() == cartId).findFirst().get();
			Product prd = products.getProducts().stream().filter(p -> p.getId() == product.getId()).findFirst().get();
			for (int i = 0; i<product.getQuantity(); i++) {
				crt.getProducts().add(prd);
			}
			response.setCode(SUCCESS_CODE);
			response.setMessage("Product successfully added.");
		} catch (Exception e) {
			response.setCode(ERROR_CODE);
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	public CartResponseDTO deleteProduct(int cartId, int productId) {
		CartResponseDTO response = new CartResponseDTO();
		try {
			Cart crt = carts.getCarts().stream().filter(c -> c.getId() == cartId).findFirst().get();
			Boolean removed = crt.getProducts().removeIf(item -> {
				if (item.getId() == productId) {
					return true;
				}
				return false;
			});
			if (removed) {
				response.setCode(SUCCESS_CODE);
				response.setMessage("Product deleted.");
			} else {
				response.setCode(NOT_FOUND_CODE);
				response.setMessage("Couldnt find product.");
			}
		} catch (Exception e) {
			response.setCode(ERROR_CODE);
			response.setMessage("Couldn't find cart.");
		}
		return response;
	}
	
	public CartResponseDTO checkout(int cartId) {
		CartResponseDTO response = new CartResponseDTO();
		try {
			Cart crt = carts.getCarts().stream().filter(c -> c.getId() == cartId).findFirst().get();
			crt.setStatus("READY");
			response.setCode(SUCCESS_CODE);
			response.setMessage("Checkout OK.");
		} catch (Exception e) {
			response.setCode(ERROR_CODE);
			response.setMessage("Couldn't find cart.");
		}
		return response;
	}
}