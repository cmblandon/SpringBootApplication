package org.springboot.app.models;

public class Banco {
    private Long id;
    private String nombre;
    private int totalTranferencia;

    public Banco() {
    }

    public Banco(Long id, String nombre, int totalTranferencia) {
        this.id = id;
        this.nombre = nombre;
        this.totalTranferencia = totalTranferencia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTotalTranferencia() {
        return totalTranferencia;
    }

    public void setTotalTranferencia(int totalTranferencia) {
        this.totalTranferencia = totalTranferencia;
    }

}
