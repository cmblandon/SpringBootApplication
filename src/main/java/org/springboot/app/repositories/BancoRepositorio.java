package org.springboot.app.repositories;

import org.springboot.app.models.Banco;

import java.util.List;

public interface BancoRepositorio {
    List<Banco> findAll();

    Banco findById(Long id);

    void update(Banco banco);
}
