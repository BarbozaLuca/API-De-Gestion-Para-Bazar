package com.example.proyectoJPA1.service;

import com.example.proyectoJPA1.model.Cliente;

import java.util.List;

public interface IClienteService {

    public List<Cliente> getClientes();

    public void saveCliente (Cliente cli);

    public void deleteCliente (int id);

    public Cliente findCliente (int id);

    public void editCliente (Cliente cli);
}
