package com.example.proyectoJPA1.controller;

import com.example.proyectoJPA1.dto.ProductoVentaDTO;
import com.example.proyectoJPA1.dto.ventaMayorDTO;
import com.example.proyectoJPA1.model.Cliente;
import com.example.proyectoJPA1.model.Producto;
import com.example.proyectoJPA1.model.Venta;
import com.example.proyectoJPA1.service.IClienteService;
import com.example.proyectoJPA1.service.IProductoService;
import com.example.proyectoJPA1.service.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
public class VentaController {

    @Autowired
    private IVentaService interVenta;
    @Autowired
    private IClienteService interCliente;
    @Autowired
    private IProductoService interProducto;

    @GetMapping("/venta/traer")
    public ResponseEntity<?> getVentas (){

        if (interVenta.getVenta().isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No hay ventas registradas");
        }
        return ResponseEntity.ok(interVenta.getVenta());
    }

    @PostMapping("/venta/crear")
    public ResponseEntity<String> saveVentas(@RequestBody Venta ven){

        try{
            if (ven == null || ven.getListaProductos() == null || ven.getClient() == null){
                return ResponseEntity.badRequest().body("Los datos de la venta son invalidos");
            }

            // Buscar cliente en la base de datos
            Cliente clienteExistente = interCliente.findCliente(ven.getClient().getId_cliente());
            if (clienteExistente == null) {
                return ResponseEntity.badRequest().body("El cliente no existe en la base de datos.");
            }
            ven.setClient(clienteExistente); // Asignar cliente existente

            // Buscar productos en la base de datos
            List<Producto> productosExistentes = new ArrayList<>();
            for (Producto prod : ven.getListaProductos()) {
                Producto productoExistente = interProducto.findProducto(prod.getCodigo_producto());
                if (productoExistente == null) {
                    return ResponseEntity.badRequest().body("El producto con ID " + prod.getCodigo_producto() + " no existe.");
                }
                if (productoExistente.getCantidad_disponible() == 0){
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No hay cantidad disponible de este producto");
                }
                productosExistentes.add(productoExistente);
            }
            ven.setListaProductos(productosExistentes); // Asignar productos existentes

            interVenta.saveVenta(ven);
            return ResponseEntity.status(HttpStatus.CREATED).body("Se cargo correctamente");

        } catch (Exception e){
            return ResponseEntity.internalServerError().body("Error al guardar la venta " + e.getMessage());
        }
    }

    @DeleteMapping("/venta/borrar/{id}")
    public ResponseEntity<String> deleteVentas(@PathVariable int id){
        if (interVenta.findVenta(id) == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro una venta con el ID: " + id);
        }
        try{
            interVenta.deleteVenta(id);
            return ResponseEntity.ok("Eliminado correctamente");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar la venta " + e.getMessage());
        }
    }

    @PutMapping("/venta/editar")
    public ResponseEntity<?> editVenta(@RequestBody Venta ven){
        if (interVenta.findVenta(ven.getCodigo_venta())==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro una venta con el ID: " + ven.getCodigo_venta());
        }
        try{
            interVenta.editVenta(ven);
            return ResponseEntity.ok("Venta editada correctamente");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al editar la venta");
        }
    }

    @GetMapping("/venta/productos/{codigo_venta}")
    public ResponseEntity<ProductoVentaDTO> faltaStock(@PathVariable int codigo_venta){

        if (interVenta.findVenta(codigo_venta) == null){
            return ResponseEntity.notFound().build();
        }
        try{

            return ResponseEntity.ok(interVenta.productosPorVenta(codigo_venta));
        } catch (Exception e){
            e.printStackTrace();//muestra el error en la consola
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/venta/{fecha_venta}")
    public ResponseEntity<?> resumenDia (@PathVariable LocalDate fecha_venta){

        if ( interVenta.resumenDia(fecha_venta).equals("0")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay ventas registradas en la fecha: " + fecha_venta);
        }
        return ResponseEntity.ok(interVenta.resumenDia(fecha_venta));
    }

    @GetMapping("/venta/mayor_venta")
    public ResponseEntity<ventaMayorDTO> mayorVenta (){

        if (interVenta.getVenta().size() == 0 ){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(interVenta.mayorVenta());
    }
}
