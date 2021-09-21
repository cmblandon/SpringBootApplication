package org.springboot.app;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springboot.app.Datos.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springboot.app.exceptions.DineroInsuficienteException;
import org.springboot.app.models.Banco;
import org.springboot.app.models.Cuenta;
import org.springboot.app.repositories.BancoRepositorio;
import org.springboot.app.repositories.CuentaRepositorio;
import org.springboot.app.services.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;

@SpringBootTest
class SpringbootAppApplicationTests {

    @MockBean
    CuentaRepositorio cuentaRepositorio;
    @MockBean
    BancoRepositorio bancoRepositorio;
    @Autowired
    CuentaService service;

    @BeforeEach
    void setUp() {
        //cuentaRepositorio = mock(CuentaRepositorio.class);
        //bancoRepositorio = mock(BancoRepositorio.class);
        //service = new CuentaServiceImpl(cuentaRepositorio, bancoRepositorio);
        // Datos.CUENTA_001.setSaldo(new BigDecimal("1000"));
        // Datos.CUENTA_002.setSaldo(new BigDecimal("2000"));
        // Datos.BANCO.setTotalTranferencia(0);
    }

    @Test
    void contextLoads() {
        when(cuentaRepositorio.findById(1L)).thenReturn(crearCuenta001());
        when(cuentaRepositorio.findById(2L)).thenReturn(crearCuenta002());
        when(bancoRepositorio.findById(1L)).thenReturn(crearBanco());

        BigDecimal saldoOrigen = service.revisarSaldo(1L);
        BigDecimal saldoDestino = service.revisarSaldo(2L);
        assertEquals("1000", saldoOrigen.toPlainString());
        assertEquals("2000", saldoDestino.toPlainString());

        service.transferir(1L, 2L, new BigDecimal("100"), 1L);
        saldoOrigen = service.revisarSaldo(1L);
        saldoDestino = service.revisarSaldo(2L);

        assertEquals("900", saldoOrigen.toPlainString());
        assertEquals("2100", saldoDestino.toPlainString());

        int total = service.revisarTotalTranferencias(1L);
        assertEquals(1, total);

        verify(cuentaRepositorio, times(3)).findById(1L);
        verify(cuentaRepositorio, times(3)).findById(2L);
        verify(cuentaRepositorio, times(2)).update(any(Cuenta.class));
        verify(bancoRepositorio, times(2)).findById(1L);
        verify(bancoRepositorio).update(any(Banco.class));

        verify(cuentaRepositorio, times(6)).findById(anyLong());
        verify(cuentaRepositorio, never()).findAll();

    }

    @Test
    void contextLoads2() {
        when(cuentaRepositorio.findById(1L)).thenReturn(crearCuenta001());
        when(cuentaRepositorio.findById(2L)).thenReturn(crearCuenta002());
        when(bancoRepositorio.findById(1L)).thenReturn(crearBanco());

        BigDecimal saldoOrigen = service.revisarSaldo(1L);
        BigDecimal saldoDestino = service.revisarSaldo(2L);
        assertEquals("1000", saldoOrigen.toPlainString());
        assertEquals("2000", saldoDestino.toPlainString());

        assertThrows(DineroInsuficienteException.class, () -> {
            service.transferir(1L, 2L, new BigDecimal("1200"), 1L);

        });

        saldoOrigen = service.revisarSaldo(1L);
        saldoDestino = service.revisarSaldo(2L);

        assertEquals("1000", saldoOrigen.toPlainString());
        assertEquals("2000", saldoDestino.toPlainString());

        int total = service.revisarTotalTranferencias(1L);
        assertEquals(0, total);

        verify(cuentaRepositorio, times(3)).findById(1L);
        verify(cuentaRepositorio, times(2)).findById(2L);
        verify(cuentaRepositorio, never()).update(any(Cuenta.class));
        verify(bancoRepositorio, times(1)).findById(1L);
        verify(bancoRepositorio, never()).update(any(Banco.class));
        verify(cuentaRepositorio, times(5)).findById(anyLong());
        verify(cuentaRepositorio, never()).findAll();
    }

    @Test
    void contextLoad3() {
        when(cuentaRepositorio.findById(1L)).thenReturn(crearCuenta001());
        Cuenta cuenta1 = service.findById(1L);
        Cuenta cuenta2 = service.findById(1L);

        assertSame(cuenta1, cuenta2);
        assertEquals("Cristian", cuenta1.getPersona());
        assertEquals("Cristian", cuenta2.getPersona());

        verify(cuentaRepositorio, times(2)).findById(1L);
    }
}
