package org.springboot.app.repositories;

import org.springboot.app.models.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CuentaRepositorio extends JpaRepository<Cuenta, Long> {
    @Query("Select c from Cuenta c where c.persona=?1")
    Optional<Cuenta> findByPersona(String persona);


    //List<Cuenta> findAll();
    //Cuenta findById(Long id);
    //void update(Cuenta cuenta);
}
