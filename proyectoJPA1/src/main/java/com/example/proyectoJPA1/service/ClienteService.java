package com.example.proyectoJPA1.service;

import com.example.proyectoJPA1.model.Cliente;
import com.example.proyectoJPA1.repository.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService implements IClienteService{

    @Autowired
    IClienteRepository repoCliente;

    @Override
    public List<Cliente> getClientes() {
        List<Cliente> listaCliente = repoCliente.findAll();
        return listaCliente;
    }

    @Override
    public void saveCliente(Cliente cli) {
        repoCliente.save(cli);
    }

    @Override
    public void deleteCliente(int id) {
        repoCliente.deleteById(id);
    }

    @Override
    public Cliente findCliente(int id) {
        return repoCliente.findById(id).orElse(null);
    }

    @Override
    public void editCliente(Cliente cli) {
        this.saveCliente(cli);
    }
}
