package com.juanignacio.sibserver.products.repositories;

import com.juanignacio.sibserver.products.models.entities.Orden;
import org.springframework.data.repository.CrudRepository;

public interface OrdenRepository extends CrudRepository<Orden, Long> {
}
