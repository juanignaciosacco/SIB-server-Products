package com.juanignacio.sibserver.products.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "productOrden")
public class ProductOrden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreProd;
    private Integer precioProd;
    private String imgUrlProd;
    private String colorProd;
    private String talleProd;
    private Integer cantidad;

    @ManyToOne
    @JoinColumn(name = "id_orden")
    private Orden orden;

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombreProd() {
        return nombreProd;
    }

    public void setNombreProd(String nombreProd) {
        this.nombreProd = nombreProd;
    }

    public Integer getPrecioProd() {
        return precioProd;
    }

    public void setPrecioProd(Integer precioProd) {
        this.precioProd = precioProd;
    }

    public String getImgUrlProd() {
        return imgUrlProd;
    }

    public void setImgUrlProd(String imgUrlProd) {
        this.imgUrlProd = imgUrlProd;
    }

    public String getColorProd() {
        return colorProd;
    }

    public void setColorProd(String colorProd) {
        this.colorProd = colorProd;
    }

    public String getTalleProd() {
        return talleProd;
    }

    public void setTalleProd(String talleProd) {
        this.talleProd = talleProd;
    }
}
