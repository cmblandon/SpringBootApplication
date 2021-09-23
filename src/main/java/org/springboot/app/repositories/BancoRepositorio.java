package org.springboot.app.repositories;

import org.springboot.app.models.Banco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BancoRepositorio extends JpaRepository<Banco, Long> {
    //List<Banco> findAll();
    //Banco findById(Long id);
    //void update(Banco banco);
}
