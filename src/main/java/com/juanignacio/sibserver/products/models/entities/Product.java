package com.juanignacio.sibserver.products.models.entities;

import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// ESTA CLASE REPRESENTA UNA TABLA EN SQL
// Anotacion Entity, clase de persistencia. Es de jakarta
@Entity
@Table(name = "producto") // Le indicamos que la tabla de SQL se llama products
public class Product {

	@Id // Le indicamos el id de la tabla
	@GeneratedValue(strategy = GenerationType.IDENTITY) // El ID Auto incrementado
	private Long idProduct;

	@NotBlank
	private String nombreProd;
	@NotNull
	private Double precioProd;
	@NotBlank
	private String materialesProd;
	private String largo;
	private String ancho;
	private String ruedo;
	private String nuevoIngresoProd;
	@NotBlank
	private String categoriaProd;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnoreProperties("product")
	List<ProductImages> imgUrl = new ArrayList<>();

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnoreProperties("product")
	List<ProductColor> colores = new ArrayList<>();

	public Product() {
	}

	public List<ProductColor> getColores() {
		return colores;
	}

	public void setColores(List<ProductColor> colores) {
		this.colores = colores;
	}

	public Long getId() {
		return idProduct;
	}

	public void setId(Long id) {
		this.idProduct = id;
	}

	public String getNombreProd() {
		return nombreProd;
	}

	public void setNombreProd(String nombreProd) {
		this.nombreProd = nombreProd;
	}

	public Double getPrecioProd() {
		return precioProd;
	}

	public void setPrecioProd(Double precioProd) {
		this.precioProd = precioProd;
	}

	public List<ProductImages> getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(List<ProductImages> imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getMaterialesProd() {
		return materialesProd;
	}

	public void setMaterialesProd(String materialesProd) {
		this.materialesProd = materialesProd;
	}

	public String getLargo() {
		return largo;
	}

	public void setLargo(String largo) {
		this.largo = largo;
	}

	public String getAncho() {
		return ancho;
	}

	public void setAncho(String ancho) {
		this.ancho = ancho;
	}

	public String getRuedo() {
		return ruedo;
	}

	public void setRuedo(String ruedo) {
		this.ruedo = ruedo;
	}

	public String getNuevoIngresoProd() {
		return nuevoIngresoProd;
	}

	public void setNuevoIngresoProd(String nuevoIngresoProd) {
		this.nuevoIngresoProd = nuevoIngresoProd;
	}

	public String getCategoriaProd() {
		return categoriaProd;
	}

	public void setCategoriaProd(String categoriaProd) {
		this.categoriaProd = categoriaProd;
	}

}
