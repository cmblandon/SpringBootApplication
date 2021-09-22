package org.springboot.app.models;

import javax.persistence.*;

@Entity
@Table(name = "bancos")
public class Banco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @Column(name = "total_tranferencias")
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
