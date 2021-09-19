package org.springboot.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springboot.app.repositories.BancoRepositorio;
import org.springboot.app.repositories.CuentaRepositorio;
import org.springboot.app.services.CuentaService;
import org.springboot.app.services.CuentaServiceImpl;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class SpringbootAppApplicationTests {

	CuentaRepositorio cuentaRepositorio;
	BancoRepositorio bancoRepositorio;

	CuentaService service;

	@BeforeEach
	void setUp() {
		cuentaRepositorio = mock(CuentaRepositorio.class);
		bancoRepositorio = mock(BancoRepositorio.class);
		service = new CuentaServiceImpl(cuentaRepositorio, bancoRepositorio);
	}

	@Test
	void contextLoads() {
		when(cuentaRepositorio.findById(1L)).thenReturn(Datos.CUENTA_001);
		when(cuentaRepositorio.findById(2L)).thenReturn(Datos.CUENTA_002);
		when(bancoRepositorio.findById(1L)).thenReturn(Datos.BANCO);

		BigDecimal saldoOrigen = service.revisarSaldo(1L);
		BigDecimal saldoDestino = service.revisarSaldo(2L);
		assertEquals("1000", saldoOrigen.toPlainString());
		assertEquals("2000", saldoDestino.toPlainString());

		service.transferir(1L, 2L, new BigDecimal("100"), 1L);
		saldoOrigen = service.revisarSaldo(1L);
		saldoDestino = service.revisarSaldo(2L);

		assertEquals("900", saldoOrigen.toPlainString());
		assertEquals("2100", saldoDestino.toPlainString());
	}

}
