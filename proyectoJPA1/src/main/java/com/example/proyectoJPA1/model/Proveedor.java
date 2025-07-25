package com.example.proyectoJPA1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@Entity
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int codigo_proveedor;
    private String nombre;
    private String direccion;
    private int telefono;
    @OneToMany(mappedBy = "provee")
    @JsonIgnore
    private List <Producto> listaProducto;

    public Proveedor() {
    }

    public Proveedor(int codigo_proveedor, String nombre, String direccion, int telefono, List<Producto> listaProducto) {
        this.codigo_proveedor = codigo_proveedor;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.listaProducto = listaProducto;
    }

}
