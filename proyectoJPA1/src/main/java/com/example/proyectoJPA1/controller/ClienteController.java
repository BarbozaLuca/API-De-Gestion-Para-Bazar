package com.example.proyectoJPA1.controller;

import com.example.proyectoJPA1.model.Cliente;
import com.example.proyectoJPA1.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClienteController {

    @Autowired
    private IClienteService interCliente;

    @GetMapping("/cliente/traer")
    public ResponseEntity<?> getProducto (){

        if (interCliente.getClientes().isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No hay clientes registrados");
        }
        return ResponseEntity.ok(interCliente.getClientes());
    }

    @PostMapping("/cliente/crear")
    public ResponseEntity<String> saveCliente (@RequestBody Cliente clie) {

        try {

            if (clie == null || clie.getDni() == null){
                return ResponseEntity.badRequest().body("Los datos del cliente son invalidos");
            }

            interCliente.saveCliente(clie);
            return ResponseEntity.status(HttpStatus.CREATED).body("Se cargo correctamente");

        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar al cliente" + e.getMessage());
        }
    }

    @DeleteMapping("/cliente/borrar/{id}")
    public ResponseEntity<?> deleteCliente (@PathVariable int id){

        try {
            if (interCliente.findCliente(id) == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro un cliente con el ID: " + id);
            }

            interCliente.deleteCliente(id);
            return ResponseEntity.ok("Cliente eliminado correctamente");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el cliente " + e.getMessage());
        }
    }

    @PutMapping("/cliente/editar")
    public ResponseEntity<?> editCliente(@RequestBody Cliente clie){
        if (interCliente.findCliente(clie.getId_cliente())==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro un cliente con el ID: " + clie.getId_cliente());
        }
        try{
            interCliente.editCliente(clie);
            return ResponseEntity.ok("Cliente editado correctamente");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al editar el cliente " + e.getMessage());
        }
    }
}
