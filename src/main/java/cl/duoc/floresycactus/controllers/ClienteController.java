/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.floresycactus.controllers;

import cl.duoc.floresycactus.entities.ClienteEntity;
import cl.duoc.floresycactus.services.IClienteService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jpino
 */
@RestController
@RequestMapping("/api")
public class ClienteController {
    
     private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);
     
    @Autowired
    private IClienteService service;
    
    @GetMapping("/clientes")
    public List<ClienteEntity> getAllClientes()
    {   
        return  this.service.findAll();
    }
    
    @GetMapping("/clientes/{id}")
    public ResponseEntity<?> showCliente(@PathVariable Long id) {
        ClienteEntity cliente = null;
        Map<String, Object> response = new HashMap<>();

        try {
                cliente = service.findById(id); 
                
                if(cliente == null) {
                    response.put("mensaje", "El cliente ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
                    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
                } 
                
                return new ResponseEntity<ClienteEntity>(cliente, HttpStatus.OK);
                
        } catch(DataAccessException e) {
                response.put("mensaje", "Error al realizar la consulta en la base de datos");
                response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }  
    }      
    
    @PostMapping("/clientes")
    public ResponseEntity<?> createCliente(@Valid @RequestBody ClienteEntity cliente, BindingResult result) {

            ClienteEntity clienteNew = null;
            Map<String, Object> response = new HashMap<>();
            try {
                    if(result.hasErrors()) {
                            List<String> errors = result.getFieldErrors()
                                                        .stream()
                                                        .map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
                                                        .collect(Collectors.toList());
                            response.put("errors", errors);
                            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
                    }
                
                    clienteNew = this.service.save(cliente);
                    
            } catch(DataAccessException e) {
                    response.put("mensaje", "Error al realizar el insert en la base de datos");
                    response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
                    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            response.put("mensaje", "El cliente ha sido creado con éxito!");
            response.put("cliente", clienteNew);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }    
    
    @PutMapping("/clientes/{id}")
    public ResponseEntity<?> updateCliente(@Valid @RequestBody ClienteEntity cliente, BindingResult result, @PathVariable Long id) {

            ClienteEntity clienteActual = this.service.findById(id);
            ClienteEntity clienteUpdated = null;
            Map<String, Object> response = new HashMap<>();

            try {                
                    if(result.hasErrors()) {
                        List<String> errors = result.getFieldErrors()
                                        .stream()
                                        .map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
                                        .collect(Collectors.toList());

                        response.put("errors", errors);
                        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
                    }

                    if (clienteActual == null) {
                        response.put("mensaje", "Error: no se pudo editar, el cliente ID: "
                                        .concat(id.toString().concat(" no existe en la base de datos!")));
                        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
                    }
                    
                    clienteActual.setNombre(cliente.getNombre());
                    clienteActual.setRazonSocial(cliente.getRazonSocial());
                    clienteActual.setComuna(cliente.getComuna());
                    clienteActual.setEstado(cliente.getEstado());
                    clienteUpdated = this.service.save(clienteActual);
                    
                    response.put("mensaje", "El cliente ha sido actualizado con éxito!");
                    response.put("cliente", clienteUpdated);

                    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

            } catch (DataAccessException e) {
                    response.put("mensaje", "Error al actualizar el cliente en la base de datos");
                    response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
                    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }

    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

            Map<String, Object> response = new HashMap<>();

            try {
                    ClienteEntity cliente = this.service.findById(id);
                    this.service.delete(cliente);                    
                    response.put("mensaje", "El cliente eliminado con éxito!");
                    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
                    
            } catch (DataAccessException e) {
                    response.put("mensaje", "Error al eliminar el cliente de la base de datos");
                    response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
                    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }
}
