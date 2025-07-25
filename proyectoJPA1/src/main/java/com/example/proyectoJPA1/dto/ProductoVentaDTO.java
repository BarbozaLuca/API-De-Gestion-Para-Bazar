package com.example.proyectoJPA1.dto;


import com.example.proyectoJPA1.model.Producto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ProductoVentaDTO{

    private int codigo_venta;
    private List< Producto> listaProductos;

    public ProductoVentaDTO() {
    }

    public ProductoVentaDTO(int codigo_venta, List<Producto> listaProductos) {
        this.codigo_venta = codigo_venta;
        this.listaProductos = listaProductos;
    }
}
