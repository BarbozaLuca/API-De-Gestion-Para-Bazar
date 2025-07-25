package com.example.proyectoJPA1.repository;

import com.example.proyectoJPA1.model.Venta;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVentaRepository extends JpaRepository< Venta, Integer> {
}
