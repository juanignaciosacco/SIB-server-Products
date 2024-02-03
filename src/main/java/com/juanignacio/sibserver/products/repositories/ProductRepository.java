package com.juanignacio.sibserver.products.repositories;

import org.springframework.data.repository.CrudRepository;

import com.juanignacio.sibserver.products.models.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
	
}
