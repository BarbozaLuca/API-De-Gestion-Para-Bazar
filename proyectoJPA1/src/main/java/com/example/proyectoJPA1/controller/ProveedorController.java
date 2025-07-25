package com.example.proyectoJPA1.controller;

import com.example.proyectoJPA1.model.Proveedor;
import com.example.proyectoJPA1.service.IProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProveedorController {

    @Autowired
    IProveedorService interProvee;

    @GetMapping("/proveedor/traer")
    public ResponseEntity<?> getProveedor(){
        if (interProvee.getProveedor().isEmpty()){
            ResponseEntity.status(HttpStatus.NO_CONTENT).body("No hay proveedores registrados");
        }

        return ResponseEntity.ok(interProvee.getProveedor());
    }

    @PostMapping("/proveedor/crear")
    public  ResponseEntity<?> saveProveedor (@RequestBody Proveedor provee) {
        try{
            if ( provee == null || provee.getNombre() == null){

                return ResponseEntity.badRequest().body("Los datos del proveedor son invalidos");
            }

            interProvee.saveProveedor(provee);
            return ResponseEntity.status(HttpStatus.CREATED).body("Se cargo correctamente");

        } catch (Exception e){
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar al proveedor " + e.getMessage());
        }
    }

    @DeleteMapping("/proveedor/borrar/{id}")
    public ResponseEntity<?> deleteProveedor (@PathVariable int id){

        try {
            if (interProvee.findProveedor(id) == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro el proveedor con ID: " + id);
            }

            interProvee.deleteProveedor(id);
            return ResponseEntity.ok("Proveedor eliminado correctamente");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el proveedor " + e.getMessage());
        }
    }

    @PutMapping("/proveedor/editar")
    public ResponseEntity<?> editProvee (@RequestBody Proveedor provee){
        if (interProvee.findProveedor(provee.getCodigo_proveedor()) == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro el proveedor con ID " + provee.getCodigo_proveedor());
        }

        try {
            interProvee.saveProveedor(provee);
            return ResponseEntity.ok("Proveedor editado correctamente");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al editar " + e.getMessage());
        }
    }
}
