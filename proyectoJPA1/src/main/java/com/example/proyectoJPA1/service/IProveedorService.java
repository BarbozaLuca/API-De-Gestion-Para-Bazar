package com.example.proyectoJPA1.service;

import com.example.proyectoJPA1.model.Proveedor;

import java.util.List;

public interface IProveedorService {

    public List<Proveedor> getProveedor ();

    public void saveProveedor (Proveedor provee);

    public void deleteProveedor (int id);

    public Proveedor findProveedor(int id);

    public void editProveedor(Proveedor provee);
}
