package com.example.proyectoJPA1.service;

import com.example.proyectoJPA1.dto.ProductoVentaDTO;
import com.example.proyectoJPA1.dto.ventaMayorDTO;
import com.example.proyectoJPA1.model.Venta;

import java.time.LocalDate;
import java.util.List;

public interface IVentaService {

    public List<Venta> getVenta();

    public void saveVenta (Venta ven);

    public void deleteVenta (int id);

    public Venta findVenta (int id);

    public void editVenta (Venta ven);

    public ProductoVentaDTO productosPorVenta(int id_venta);

    public String resumenDia (LocalDate fecha);

    public ventaMayorDTO mayorVenta();
}
