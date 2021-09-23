package org.springboot.app;

import org.junit.jupiter.api.Test;
import org.springboot.app.models.Cuenta;
import org.springboot.app.repositories.CuentaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
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

    @Test
    void testSave() {
        //Given
        Cuenta cuentaCristian = new Cuenta(null, "CristianBL", new BigDecimal("3000"));
        //When
        Cuenta cuenta = cuentaRepositorio.save(cuentaCristian);
        //Cuenta cuenta = cuentaRepositorio.findByPersona("CristianBL").orElseThrow();
        //Cuenta cuenta = cuentaRepositorio.findById(save.getId()).orElseThrow();

        //Then
        assertEquals("CristianBL", cuenta.getPersona());
        assertEquals("3000", cuenta.getSaldo().toPlainString());
        //assertEquals(3, cuenta.getId());
    }

    @Test
    void testUpdate() {
        //Given
        Cuenta cuentaCristian = new Cuenta(null, "CristianBL", new BigDecimal("3000"));
        //When
        Cuenta cuenta = cuentaRepositorio.save(cuentaCristian);
        //Cuenta cuenta = cuentaRepositorio.findByPersona("CristianBL").orElseThrow();
        //Cuenta cuenta = cuentaRepositorio.findById(save.getId()).orElseThrow();

        //Then
        assertEquals("CristianBL", cuenta.getPersona());
        assertEquals("3000", cuenta.getSaldo().toPlainString());
        //assertEquals(3, cuenta.getId());

        cuenta.setSaldo(new BigDecimal("3800"));
        Cuenta cuentaActualizada = cuentaRepositorio.save(cuenta);
        assertEquals("CristianBL", cuentaActualizada.getPersona());
        assertEquals("3800", cuentaActualizada.getSaldo().toPlainString());
    }

    @Test
    void testDelete() {
        Cuenta cuenta = cuentaRepositorio.findById(2L).orElseThrow();
        assertEquals("John", cuenta.getPersona());
        cuentaRepositorio.delete(cuenta);
        assertThrows(NoSuchElementException.class, () -> {
            //cuentaRepositorio.findByPersona("John").orElseThrow();
            cuentaRepositorio.findById(2L).orElseThrow();
        });
        assertEquals(1, cuentaRepositorio.findAll().size());
    }
}
