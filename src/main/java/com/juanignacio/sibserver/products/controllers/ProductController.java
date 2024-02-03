package com.juanignacio.sibserver.products.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.juanignacio.sibserver.products.models.entities.ProductColor;
import com.juanignacio.sibserver.products.models.entities.ProductImages;
import com.juanignacio.sibserver.products.models.entities.ProductTalles;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.juanignacio.sibserver.products.models.entities.Product;
import com.juanignacio.sibserver.products.services.ProductService;


@RestController // Esta clase es una API REST, va a devolver un JSON.
@CrossOrigin(origins = "*")
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService service;
	
	@GetMapping // Url para solicitar este servicio desde cliente
	public List<Product> list() {
		return service.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		Optional<Product> productOptional = service.findByID(id);
		
		if (productOptional.isPresent()) {
			return ResponseEntity.ok(productOptional.orElseThrow());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@RequestBody @Valid Product product) {

		if (product.getImgUrl() != null || !product.getImgUrl().isEmpty()) {
			List<ProductImages> imageList = new ArrayList<>();

			for (ProductImages img : product.getImgUrl()) {
				ProductImages image = new ProductImages();
				image.setImgUrl(img.getImgUrl());
				image.setProduct(product);
				imageList.add(image);
			}

			product.setImgUrl(imageList);
		}

		if (product.getColores() != null || !product.getColores().isEmpty()) {
			List<ProductColor> colorList = new ArrayList<>();

			for (ProductColor color : product.getColores()) {

				if (color.getTalles() != null || !color.getColor().isEmpty()) {

					List<ProductTalles> tallesList = new ArrayList<>();
					ProductColor col = new ProductColor();

					for (ProductTalles talle : color.getTalles()) {
						ProductTalles tall = new ProductTalles();
						tall.setTalle(talle.getTalle());
						tall.setStock(talle.getStock());
						tall.setColor(col);
						tallesList.add(tall);
					}

					col.setColor(color.getColor());
					col.setProduct(product);
					col.setTalles(tallesList);
					colorList.add(col);
				}
			}
			product.setColores(colorList);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(product));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Product product, @PathVariable Long id) {
		Optional<Product> prod = service.update(product, id);
		if(prod.isPresent()) {
			if (product.getImgUrl() != null || !product.getImgUrl().isEmpty()) {
				List<ProductImages> imageList = new ArrayList<>();

				for (ProductImages img : product.getImgUrl()) {
					ProductImages image = new ProductImages();
					image.setImgUrl(img.getImgUrl());
					image.setProduct(product);
					imageList.add(image);
				}

				product.setImgUrl(imageList);
			}

			if (product.getColores() != null || !product.getColores().isEmpty()) {
				List<ProductColor> colorList = new ArrayList<>();

				for (ProductColor color : product.getColores()) {

					if (color.getTalles() != null || !color.getColor().isEmpty()) {

						List<ProductTalles> tallesList = new ArrayList<>();
						ProductColor col = new ProductColor();

						for (ProductTalles talle : color.getTalles()) {
							ProductTalles tall = new ProductTalles();
							tall.setTalle(talle.getTalle());
							tall.setStock(talle.getStock());
							tall.setColor(col);
							tallesList.add(tall);
						}

						col.setColor(color.getColor());
						col.setProduct(product);
						col.setTalles(tallesList);
						colorList.add(col);
					}
				}
				product.setColores(colorList);
				return ResponseEntity.status(HttpStatus.CREATED).body(prod.orElseThrow());
			}
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remove(@PathVariable Long id) {
		Optional<Product> prod = service.findByID(id);
		if (prod.isPresent()) {
			service.remove(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
}
