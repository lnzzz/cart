package com.cart.rest.controllers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cart.rest.models.Cart;
import com.cart.rest.models.CartCollection;
import com.cart.rest.models.Product;
import com.cart.rest.models.ProductCollection;

@Component
public class CartProcessingController {
	
	private ProductCollection products = ProductCollection.getInstance();
	private CartCollection carts = CartCollection.getInstance();
	final static Logger logger = Logger.getLogger(CartProcessingController.class);

	
	@Scheduled(fixedRate=10000)
	public void processCarts() {
		ExecutorService executor = Executors.newFixedThreadPool(5);
		executor.submit(() -> {
			try {
				
				Cart cart = carts.getCarts().stream().filter(c -> c.getStatus() == "NEW").findFirst().get();
				
				logger.debug("Starting process...");
				logger.debug("Processing cart with id: "+cart.getId());
				cart.getProducts().forEach(product -> {
					Product productStock = products.getProducts().stream().filter(p -> p.getId() == product.getId()).findFirst().get();
					int tmpStock = productStock.getStock();
					logger.debug("Checking product -> "+productStock.getDescription()+" with stock "+productStock.getStock());
					
					if (tmpStock > 0) {
						logger.debug("Stock is ok");
						productStock.setStock(productStock.getStock()-1);
						logger.debug("New stock is "+productStock.getStock());
					} else {
						logger.error("[STATUS-FAILED] Cart with id "+cart.getId()+" processing has failed due to stock shortage on the product with id : "+productStock.getId());
						cart.setStatus("FAILED");
						
					}
				});
				
				if (cart.getStatus() == "NEW") {
					System.out.println("[STATUS-SUCCESS] Cart with id "+cart.getId()+" has been processed succesfully.");
					cart.setStatus("PROCESSED");
				}
				
			} catch (Exception e) {
				throw new IllegalStateException("task interrupted", e);
			} finally {
				executor.shutdownNow();
			}
		});
	}
	
	
	
    

}