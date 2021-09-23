package org.springboot.app;

import org.junit.jupiter.api.Test;
import org.springboot.app.models.Cuenta;
import org.springboot.app.repositories.CuentaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class IntegracionJpaTest {
    @Autowired
    CuentaRepositorio cuentaRepositorio;

    @Test
    void testFindById() {
        Optional<Cuenta> cuenta = cuentaRepositorio.findById(1L);
        assertTrue(cuenta.isPresent());
        assertEquals("Cristian", cuenta.orElseThrow().getPersona());
    }

    @Test
    void testFindByPersona() {
        Optional<Cuenta> cuenta = cuentaRepositorio.findByPersona("Cristian");
        assertTrue(cuenta.isPresent());
        assertEquals("Cristian", cuenta.orElseThrow().getPersona());
        assertEquals("1000.00", cuenta.orElseThrow().getSaldo().toPlainString());
    }

    @Test
    void testFindByPersonaTrueException() {
        Optional<Cuenta> cuenta = cuentaRepositorio.findByPersona("CristianB");
        assertThrows(NoSuchElementException.class, cuenta::orElseThrow);
        assertFalse(cuenta.isPresent());
    }

    @Test
    void findAll() {
        List<Cuenta> cuentas = cuentaRepositorio.findAll();
        assertFalse(cuentas.isEmpty());
        assertEquals(2, cuentas.size());
    }
}
