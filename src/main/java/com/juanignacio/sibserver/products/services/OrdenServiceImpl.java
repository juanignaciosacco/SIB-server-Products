package com.juanignacio.sibserver.products.services;

import com.juanignacio.sibserver.products.models.entities.Orden;
import com.juanignacio.sibserver.products.repositories.OrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrdenServiceImpl implements OrdenService {

    @Autowired
    private OrdenRepository ordenRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Orden> findAll() {
        return (List<Orden>) ordenRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Orden> findById(Long id) {
        return ordenRepository.findById(id);
    }

    @Override
    @Transactional
    public Orden save(Orden orden) {
        return ordenRepository.save(orden);
    }

    @Override
    public Optional<Orden> update(Orden orden, Long id) {
        Optional<Orden> ord = this.findById(id);
        Orden ordOptional = null;

        if (ord.isPresent()) {
            Orden ordDb = ord.orElseThrow();
            ordDb.setProductos(orden.getProductos());
            ordDb.setIdOrden(orden.getIdOrden());
            ordDb.setOrdenEntregada(orden.getOrdenEntregada());
            ordDb.setIdPago(orden.getIdPago());
            ordDb.setUserEmail(orden.getUserEmail());
            ordDb.setPrecioTotal(orden.getPrecioTotal());
            ordDb.setUserLastName(orden.getUserLastName());
            ordDb.setUserPhoneNumber(orden.getUserPhoneNumber());
            ordDb.setUserName(orden.getUserName());
            ordDb.setEstadoDePago(orden.getEstadoDePago());
            ordOptional = this.save(ordDb);
        }
        return Optional.ofNullable(ordOptional);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        ordenRepository.deleteById(id);
    }
}
