package com.example.proyectoJPA1.service;

import com.example.proyectoJPA1.model.Producto;

import java.util.List;

public interface IProductoService {

    public List<Producto> getProducto();

    public void saveProducto (Producto prod);

    public void deleteProducto (int id);

    public Producto findProducto (int id);

    public void editProducto (Producto prod);

    public List<Producto> getFaltaStock();
}
