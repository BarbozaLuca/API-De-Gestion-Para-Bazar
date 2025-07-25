package com.example.proyectoJPA1.repository;

import com.example.proyectoJPA1.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IProductoRepository extends JpaRepository<Producto, Integer> {

}
