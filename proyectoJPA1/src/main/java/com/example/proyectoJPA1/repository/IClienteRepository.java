package com.example.proyectoJPA1.repository;

import com.example.proyectoJPA1.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClienteRepository extends JpaRepository< Cliente, Integer> {

}
