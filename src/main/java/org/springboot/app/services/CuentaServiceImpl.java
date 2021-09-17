package org.springboot.app.services;

import org.springboot.app.models.Banco;
import org.springboot.app.models.Cuenta;
import org.springboot.app.repositories.BancoRepositorio;
import org.springboot.app.repositories.CuentaRepositorio;

import java.math.BigDecimal;

public class CuentaServiceImpl implements CuentaService {
    private CuentaRepositorio cuentaRepositorio;
    private BancoRepositorio bancoRepositorio;

    public CuentaServiceImpl(CuentaRepositorio cuentaRepositorio, BancoRepositorio bancoRepositorio) {
        this.cuentaRepositorio = cuentaRepositorio;
        this.bancoRepositorio = bancoRepositorio;
    }

    @Override
    public Cuenta findById(Long id) {
        return cuentaRepositorio.findById(id);
    }

    @Override
    public int revisarTotalTranferencias(Long bancoId) {
        Banco banco = bancoRepositorio.findById(bancoId);
        return banco.getTotalTranferencia();
    }

    @Override
    public BigDecimal revisarSaldo(Long cuentaId) {
        Cuenta cuenta = cuentaRepositorio.findById(cuentaId);
        return cuenta.getSaldo();
    }

    @Override
    public void transferir(Long numCuentaOrigen, Long numCuentaDestino, BigDecimal monto) {
        Banco banco = bancoRepositorio.findById(1L);
        int totalTranferencia = banco.getTotalTranferencia();
        banco.setTotalTranferencia(++totalTranferencia);
        bancoRepositorio.update(banco);

        Cuenta cuentaOrigen = cuentaRepositorio.findById(numCuentaOrigen);
        cuentaOrigen.debito(monto);
        cuentaRepositorio.update(cuentaOrigen);
        Cuenta cuentaDestino = cuentaRepositorio.findById(numCuentaDestino);
        cuentaDestino.credito(monto);
        cuentaRepositorio.update(cuentaDestino);
    }
}
