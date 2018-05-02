package com.cart.rest.controllers;

import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cart.rest.models.Cart;
import com.cart.rest.collections.CartCollection;
import com.cart.rest.models.Product;
import com.cart.rest.collections.ProductCollection;

@Component
public class CartProcessingController {
	
	private ProductCollection products = ProductCollection.getInstance();
	private CartCollection carts = CartCollection.getInstance();
	final static Logger logger = Logger.getLogger(CartProcessingController.class);

	
	@Scheduled(fixedRate=5000)
	public void processCarts() {
		ExecutorService executor = Executors.newFixedThreadPool(500);
		executor.submit(() -> {
			try {
				
				Cart cart = carts.getCarts().stream().filter(c -> c.getStatus() == "NEW").findFirst().get();
					logger.info("Starting process...");
					logger.info("Processing cart with id: "+cart.getId());
					if (cart.getProducts().size() > 0) {
						for (Iterator<Product> iterator = cart.getProducts().iterator(); iterator.hasNext();) {
							Product product = (Product) iterator.next();
							Product productStock = products.getProducts().stream().filter(p -> p.getId() == product.getId()).findFirst().get();
							int tmpStock = productStock.getStock();
							
							logger.info("Checking product -> "+productStock.getDescription()+" with stock "+productStock.getStock());
							if (tmpStock > 0) {
								logger.info("Stock is ok");
								productStock.setStock(productStock.getStock()-1);
								logger.info("New stock is "+productStock.getStock());
							} else {
								logger.error("[STATUS-FAILED] Cart with id "+cart.getId()+" processing has failed due to stock shortage on the product with id : "+productStock.getId());
								cart.setStatus("FAILED");
								break;
							}
						}
						
						if (cart.getStatus() == "NEW") {
							logger.info("[STATUS-SUCCESS] Cart with id "+cart.getId()+" has been processed succesfully.");
							cart.setStatus("PROCESSED");
						}
					} else {
						logger.info("Cart has no products.");
					}
			} catch (Exception e) {
				logger.info("There are no carts to process.");
			} finally {
				executor.shutdownNow();
			}
		});
	}
	
	
	
    

}