package com.juanignacio.sibserver.products.services;

import java.util.List;
import java.util.Optional;

import com.juanignacio.sibserver.products.models.entities.Product;

public interface ProductService {

	List<Product> findAll();
	
	Optional<Product> findByID(Long id);
	
	Product save(Product product);
	Optional<Product> update(Product product, Long id);
	
	void remove(Long id);

}
