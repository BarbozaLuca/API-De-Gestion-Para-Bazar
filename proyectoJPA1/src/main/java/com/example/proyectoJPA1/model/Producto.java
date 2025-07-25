package com.example.proyectoJPA1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int codigo_producto;
    private String nombre;
    private String marca;
    private Double costo;
    private int cantidad_disponible;
    @ManyToMany(mappedBy = "listaProductos")
    @JsonIgnore
    private List<Venta> listaVentas;
    @ManyToOne
    @JoinColumn(name = "codigo_proveedor")
    private Proveedor provee;

    public Producto() {
    }

    public Producto(int codigo_producto, String nombre, String marca, Double costo, int cantidad_disponible, List<Venta> listaVentas, Proveedor provee) {
        this.codigo_producto = codigo_producto;
        this.nombre = nombre;
        this.marca = marca;
        this.costo = costo;
        this.cantidad_disponible = cantidad_disponible;
        this.listaVentas = listaVentas;
        this.provee = provee;
    }
}
