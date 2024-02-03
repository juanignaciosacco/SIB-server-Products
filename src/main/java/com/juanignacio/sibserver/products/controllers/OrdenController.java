package com.juanignacio.sibserver.products.controllers;

import com.juanignacio.sibserver.products.models.entities.Orden;
import com.juanignacio.sibserver.products.models.entities.ProductOrden;
import com.juanignacio.sibserver.products.services.OrdenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/ordenes")
public class OrdenController {

    @Autowired
    private OrdenService service;

    @GetMapping
    public List<Orden> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        Optional<Orden> ordenOptional = service.findById(id);

        if (ordenOptional.isPresent()) {
            return ResponseEntity.ok(ordenOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody @Valid Orden orden) {

        if (orden.getProductos() != null) {

            List<ProductOrden> prodsList = new ArrayList<>();
            for (ProductOrden prod : orden.getProductos()) {
                ProductOrden prodOrd = new ProductOrden();
                prodOrd.setNombreProd(prod.getNombreProd());
                prodOrd.setCantidad(prod.getCantidad());
                prodOrd.setPrecioProd(prod.getPrecioProd());
                prodOrd.setColorProd(prod.getColorProd());
                prodOrd.setTalleProd(prod.getTalleProd());
                prodOrd.setImgUrlProd(prod.getImgUrlProd());
                prodOrd.setOrden(orden);
                prodsList.add(prodOrd);
            }

            orden.setProductos(prodsList);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(orden));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Orden orden, @PathVariable Long id) {
        Optional<Orden> ord = service.update(orden, id);
        if (ord.isPresent()) {
            if (orden.getProductos() != null) {

                List<ProductOrden> prodsList = new ArrayList<>();
                for (ProductOrden prod : orden.getProductos()) {
                    ProductOrden prodOrd = new ProductOrden();
                    prodOrd.setNombreProd(prod.getNombreProd());
                    prodOrd.setCantidad(prod.getCantidad());
                    prodOrd.setPrecioProd(prod.getPrecioProd());
                    prodOrd.setColorProd(prod.getColorProd());
                    prodOrd.setTalleProd(prod.getTalleProd());
                    prodOrd.setImgUrlProd(prod.getImgUrlProd());
                    prodOrd.setOrden(orden);
                    prodsList.add(prodOrd);
                }

                orden.setProductos(prodsList);
                return ResponseEntity.status(HttpStatus.CREATED).body(ord.orElseThrow());
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        Optional<Orden> ord = service.findById(id);
        if (ord.isPresent()) {
            service.remove(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
