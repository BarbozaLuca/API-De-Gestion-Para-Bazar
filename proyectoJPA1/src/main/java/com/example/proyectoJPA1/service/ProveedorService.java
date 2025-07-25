package com.example.proyectoJPA1.service;

import com.example.proyectoJPA1.model.Proveedor;
import com.example.proyectoJPA1.repository.IProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorService implements IProveedorService{

    @Autowired
    IProveedorRepository repoProvee;

    @Override
    public List<Proveedor> getProveedor() {
        return repoProvee.findAll();
    }

    @Override
    public void saveProveedor(Proveedor provee) {
        repoProvee.save(provee);
    }

    @Override
    public void deleteProveedor(int id) {
        repoProvee.deleteById(id);
    }

    @Override
    public Proveedor findProveedor(int id) {
         return repoProvee.findById(id).orElse(null);
    }

    @Override
    public void editProveedor(Proveedor provee) {
        this.saveProveedor(provee);
    }
}
