package com.juanignacio.sibserver.products.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.juanignacio.sibserver.products.models.entities.Product;
import com.juanignacio.sibserver.products.repositories.ProductRepository;

@Service // Esto declarar esta clase como componente de Spring, es una clase de servicio (Maneja logica de negocio)
public class ProductServiceImpl implements ProductService {

	@Autowired // Esta notacion permite inyectar, porque ProductRepository es un @Bean, lo va a a buscar al container de Spring 
	private ProductRepository repository;
	
	@Override
	@Transactional(readOnly = true) // Esta notacion es para declarar transacciones en la base de datos, ademas de mantener la conexion abiera dentro del metodo y mas
	public List<Product> findAll() {
		return (List<Product>) repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Product> findByID(Long id) {
		return repository.findById(id);
	}

	@Override
	@Transactional
	public Product save(Product product) {
		return repository.save(product);
	}

	@Override
	@Transactional
	public Optional<Product> update(Product product, Long id) {
		Optional<Product> prod = this.findByID(id);
		Product prodOptional = null;
		if (prod.isPresent()) {
			Product prodDb = prod.orElseThrow();
			prodDb.setImgUrl(product.getImgUrl());
			prodDb.setNombreProd(product.getNombreProd());
			prodDb.setPrecioProd(product.getPrecioProd());
			prodDb.setColores(product.getColores());
			prodDb.setCategoriaProd(product.getCategoriaProd());
			prodDb.setNuevoIngresoProd(product.getNuevoIngresoProd());
			prodDb.setMaterialesProd(product.getMaterialesProd());
			prodOptional = this.save(prodDb);
		}
		return Optional.ofNullable(prodOptional);
	}

	@Override
	@Transactional
	public void remove(Long id) {
		repository.deleteById(id);		
	}

}
