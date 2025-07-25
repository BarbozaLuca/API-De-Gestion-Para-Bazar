package com.example.proyectoJPA1.service;

import com.example.proyectoJPA1.model.Producto;
import com.example.proyectoJPA1.repository.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoService implements IProductoService{

    @Autowired
    private IProductoRepository repoProd;

    @Override
    public List<Producto> getProducto() {
        List<Producto> listaProducto = repoProd.findAll();
        return listaProducto;
    }

    @Override
    public void saveProducto(Producto prod) {
        repoProd.save(prod);
    }

    @Override
    public void deleteProducto(int id) {
        repoProd.deleteById(id);

    }

    @Override
    public Producto findProducto(int id) {
        Producto ProdBusca = repoProd.findById(id).orElse(null);
        return ProdBusca;
    }

    @Override
    public void editProducto(Producto prod) {
        this.saveProducto(prod);
    }

    @Override
    public List<Producto> getFaltaStock() {
        List<Producto> listaStock = new ArrayList<>();
        for (Producto prod : getProducto()){
            if (prod.getCantidad_disponible() < 5){
                listaStock.add(prod);
            }
        }
        return listaStock;
    }
}
