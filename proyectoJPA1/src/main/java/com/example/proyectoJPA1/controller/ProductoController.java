package com.example.proyectoJPA1.controller;

import com.example.proyectoJPA1.model.Producto;
import com.example.proyectoJPA1.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductoController {

    @Autowired
    private IProductoService interProd;

    @GetMapping("/producto/traer")
    public ResponseEntity<?> getProducto (){

        if (interProd.getProducto().isEmpty()){
           return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No hay productos registrados");
        }
        return ResponseEntity.ok(interProd.getProducto());
    }

    @PostMapping("/producto/crear")
    public ResponseEntity<String> saveProducto (@RequestBody Producto prod) {

        try {

            if (prod == null || prod.getCosto() == null){
               return ResponseEntity.badRequest().body("Los datos de la venta son invalidos");
            }

               interProd.saveProducto(prod);
               return ResponseEntity.status(HttpStatus.CREATED).body("Se cargo correctamente");

        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar la venta" + e.getMessage());
        }
    }

    @DeleteMapping("/producto/borrar/{id}")
    public ResponseEntity<?> deleteProducto (@PathVariable int id){

        try {
            if (interProd.findProducto(id) == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro un producto con el ID: " + id);
            }

            interProd.deleteProducto(id);
            return ResponseEntity.ok("Producto eliminado correctamente");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el producto " + e.getMessage());
        }
    }

    @PutMapping("/producto/editar")
    public ResponseEntity<?> editVenta(@RequestBody Producto prod){
        if (interProd.findProducto(prod.getCodigo_producto())==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro un producto con el ID: " + prod.getCodigo_producto());
        }
        try{
            interProd.editProducto(prod);
            return ResponseEntity.ok("Producto editado correctamente");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al editar el producto " + e.getMessage());
        }
    }

    @GetMapping("/producto/falta_stock")
    public ResponseEntity<?> faltaStock(){

        if (interProd.getFaltaStock().isEmpty()){
            ResponseEntity.status(HttpStatus.NO_CONTENT).body("No hay productos con falta de stock");
        }

        return ResponseEntity.ok(interProd.getFaltaStock());
    }
}
