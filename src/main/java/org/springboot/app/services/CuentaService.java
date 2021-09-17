package org.springboot.app.services;

import org.springboot.app.models.Cuenta;

import java.math.BigDecimal;

public interface CuentaService {
    Cuenta findById(Long id);

    int revisarTotalTranferenccias(Long bancoId);

    BigDecimal revisarSaldo(Long cuentaId);

    void transferir(Long numCuentaOrigen, Long numCuentaDestino, BigDecimal monto);
}
