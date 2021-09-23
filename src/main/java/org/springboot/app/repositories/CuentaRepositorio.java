package org.springboot.app.repositories;

import org.springboot.app.models.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CuentaRepositorio extends JpaRepository<Cuenta, Long> {
    //List<Cuenta> findAll();
    //Cuenta findById(Long id);
    //void update(Cuenta cuenta);
}
