package com.juanignacio.sibserver.products.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orden")
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double precioTotal;
    private String userEmail;
    private String userName;
    private String userLastName;
    private String userPhoneNumber;
    private String idPago;
    private Boolean ordenEntregada;
    private String idOrden;
    private String estadoDePago;

    @OneToMany(mappedBy = "orden", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("orden")
    List<ProductOrden> productos = new ArrayList<>();

    public List<ProductOrden> getProductos() {
        return productos;
    }

    public Long getId() {
        return id;
    }

    public void setProductos(List<ProductOrden> productos) {
        this.productos = productos;
    }

    public Double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIdPago() {
        return idPago;
    }

    public void setIdPago(String idPago) {
        this.idPago = idPago;
    }

    public Boolean getOrdenEntregada() {
        return ordenEntregada;
    }

    public void setOrdenEntregada(Boolean ordenEntregada) {
        this.ordenEntregada = ordenEntregada;
    }

    public String getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(String idOrden) {
        this.idOrden = idOrden;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getEstadoDePago() {
        return estadoDePago;
    }

    public void setEstadoDePago(String estadoDePago) {
        this.estadoDePago = estadoDePago;
    }
}
