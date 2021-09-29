package org.springboot.app.models;

import java.math.BigDecimal;

public class TransaccionDTO {
    private Long cuentaOrigenId;
    private Long getCuentaDestinoId;
    private BigDecimal monto;
    private Long bancoId;

    public Long getCuentaOrigenId() {
        return cuentaOrigenId;
    }

    public void setCuentaOrigenId(Long cuentaOrigenId) {
        this.cuentaOrigenId = cuentaOrigenId;
    }

    public Long getGetCuentaDestinoId() {
        return getCuentaDestinoId;
    }

    public void setGetCuentaDestinoId(Long getCuentaDestinoId) {
        this.getCuentaDestinoId = getCuentaDestinoId;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public Long getBancoId() {
        return bancoId;
    }

    public void setBancoId(Long bancoId) {
        this.bancoId = bancoId;
    }
}
