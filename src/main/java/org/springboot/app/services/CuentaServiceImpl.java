package org.springboot.app.services;

import org.springboot.app.models.Banco;
import org.springboot.app.models.Cuenta;
import org.springboot.app.repositories.BancoRepositorio;
import org.springboot.app.repositories.CuentaRepositorio;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CuentaServiceImpl implements CuentaService {

    private CuentaRepositorio cuentaRepositorio;
    private BancoRepositorio bancoRepositorio;

    public CuentaServiceImpl(CuentaRepositorio cuentaRepositorio, BancoRepositorio bancoRepositorio) {
        this.cuentaRepositorio = cuentaRepositorio;
        this.bancoRepositorio = bancoRepositorio;
    }

    @Override
    public Cuenta findById(Long id) {
        return cuentaRepositorio.findById(id).orElseThrow();
    }

    @Override
    public int revisarTotalTranferencias(Long bancoId) {
        Banco banco = bancoRepositorio.findById(bancoId);
        return banco.getTotalTranferencia();
    }

    @Override
    public BigDecimal revisarSaldo(Long cuentaId) {
        Cuenta cuenta = cuentaRepositorio.findById(cuentaId).orElseThrow();
        return cuenta.getSaldo();
    }

    @Override
    public void transferir(Long numCuentaOrigen, Long numCuentaDestino, BigDecimal monto,
                           Long bancoId) {
        Cuenta cuentaOrigen = cuentaRepositorio.findById(numCuentaOrigen).orElseThrow();
        cuentaOrigen.debito(monto);
        cuentaRepositorio.save(cuentaOrigen);

        Cuenta cuentaDestino = cuentaRepositorio.findById(numCuentaDestino).orElseThrow();
        cuentaDestino.credito(monto);
        cuentaRepositorio.save(cuentaDestino);

        Banco banco = bancoRepositorio.findById(bancoId);
        int totalTranferencia = banco.getTotalTranferencia();
        banco.setTotalTranferencia(++totalTranferencia);
        bancoRepositorio.update(banco);
    }
}
