package org.springboot.app.repositories;

import org.springboot.app.models.Cuenta;

import java.util.List;

public interface CuentaRepositorio {
    List<Cuenta> findAll();

    Cuenta findById(Long id);

    void update(Cuenta cuenta);
}
