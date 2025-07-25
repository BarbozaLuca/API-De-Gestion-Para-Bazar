package com.example.proyectoJPA1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@Entity
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int codigo_venta;
    private LocalDate fecha_venta;
    private Double total;
    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "detalle_venta",
    joinColumns = @JoinColumn(name = "venta_id"),
    inverseJoinColumns = @JoinColumn(name = "producto_id"))
    private List<Producto> listaProductos;
    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    private Cliente client;

    public Venta() {
    }

    public Venta(int codigo_venta, LocalDate fecha_venta, Double total, List<Producto> listaProductos, Cliente client) {
        this.codigo_venta = codigo_venta;
        this.fecha_venta = fecha_venta;
        this.total = total;
        this.listaProductos = listaProductos;
        this.client = client;
    }
}
