package com.example.proyectoJPA1.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ventaMayorDTO {

    private int codigo_venta;
    private double total;
    private int cantidad_producto;
    private String nombre_cliente;
    private String apellido_cliente;

    public ventaMayorDTO() {
    }

    public ventaMayorDTO(int codigo_venta, double total, int cantidad_producto, String nombre_cliente, String apellido_cliente) {
        this.codigo_venta = codigo_venta;
        this.total = total;
        this.cantidad_producto = cantidad_producto;
        this.nombre_cliente = nombre_cliente;
        this.apellido_cliente = apellido_cliente;
    }
}
