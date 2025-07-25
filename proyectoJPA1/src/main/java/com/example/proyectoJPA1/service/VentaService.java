package com.example.proyectoJPA1.service;

import com.example.proyectoJPA1.dto.ProductoVentaDTO;
import com.example.proyectoJPA1.dto.ventaMayorDTO;
import com.example.proyectoJPA1.model.Producto;
import com.example.proyectoJPA1.model.Venta;
import com.example.proyectoJPA1.repository.IVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VentaService implements IVentaService{

    @Autowired
    IVentaRepository repoVent;

    @Autowired
    IProductoService interProd;

    @Override
    public List<Venta> getVenta() {
        return repoVent.findAll();
    }

    @Override
    public void saveVenta(Venta ven) {

        double suma = 0;

        for (Producto prod : ven.getListaProductos()){
            for (Producto produc : interProd.getProducto()){
                if (prod.getCodigo_producto() == produc.getCodigo_producto()){
                    suma = suma + produc.getCosto();
                    produc.setCantidad_disponible(prod.getCantidad_disponible()-1);
                }
            }
        }

        ven.setTotal(suma);
        ven.setFecha_venta(LocalDate.now());

        repoVent.save(ven);
    }

    @Override
    public void deleteVenta(int id) {
        repoVent.deleteById(id);
    }

    @Override
    public Venta findVenta(int id) {
        return repoVent.findById(id).orElse(null);
    }

    @Override
    public void editVenta(Venta ven) {
        this.saveVenta(ven);
    }

    @Override
    public ProductoVentaDTO productosPorVenta(int id_venta) {

        ProductoVentaDTO ProdVentDTO = new ProductoVentaDTO();
        Venta venta = this.findVenta(id_venta);

        ProdVentDTO.setCodigo_venta(venta.getCodigo_venta());
        ProdVentDTO.setListaProductos(venta.getListaProductos());

        return ProdVentDTO;
    }

    @Override
    public String resumenDia(LocalDate fecha) {

        double MontoDDia = 0;
        int CantVentas = 0;

        for (Venta ven : this.getVenta()){
            if (ven.getFecha_venta().equals(fecha)){

                MontoDDia = MontoDDia + ven.getTotal();
                CantVentas = CantVentas + 1;

            }
        }
        if (CantVentas == 0){
            return "0";
        }

        return "Datos del dia " + fecha + " Monto total: " + MontoDDia + " ventas totales: " + CantVentas ;
    }

    @Override
    public ventaMayorDTO mayorVenta() {

        Venta ventaMax = new Venta();
        ventaMax.setTotal(0.0);
        ventaMayorDTO ventaMayorDTO = new ventaMayorDTO();

        for (Venta vent: this.getVenta()){
            if (vent.getTotal() > ventaMax.getTotal()){
                ventaMax = this.findVenta(vent.getCodigo_venta());
            }
        }

        ventaMayorDTO.setCodigo_venta(ventaMax.getCodigo_venta());
        ventaMayorDTO.setTotal(ventaMax.getTotal());
        ventaMayorDTO.setCantidad_producto(ventaMax.getListaProductos().size());
        ventaMayorDTO.setNombre_cliente(ventaMax.getClient().getNombre());
        ventaMayorDTO.setApellido_cliente(ventaMax.getClient().getApellido());

        return ventaMayorDTO;
    }
}
