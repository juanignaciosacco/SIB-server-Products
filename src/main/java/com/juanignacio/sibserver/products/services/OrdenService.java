package com.juanignacio.sibserver.products.services;

import com.juanignacio.sibserver.products.models.entities.Orden;

import java.util.List;
import java.util.Optional;

public interface OrdenService {

    List<Orden> findAll();

    Optional<Orden> findById(Long id);

    Orden save(Orden orden);

    Optional<Orden> update(Orden orden, Long id);

    void remove(Long id);
}
