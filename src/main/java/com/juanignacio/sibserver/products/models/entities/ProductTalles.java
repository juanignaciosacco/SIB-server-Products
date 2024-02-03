package com.juanignacio.sibserver.products.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "productTalles")
public class ProductTalles {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String talle;
	private Integer stock;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "color_id")
    private ProductColor color;

	public ProductTalles() {
	}

	public String getTalle() {
		return talle;
	}

	public void setTalle(String talle) {
		this.talle = talle;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public ProductColor getColor() {
		return color;
	}

	public void setColor(ProductColor color) {
		this.color = color;
	}
}
