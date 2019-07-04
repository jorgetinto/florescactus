/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.floresycactus.controllers;


import cl.duoc.floresycactus.entities.DecisionEntity;
import cl.duoc.floresycactus.services.IDecisionService;
import com.fasterxml.jackson.databind.JsonNode;
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
public class DecisionesController {
    
    private static final Logger logger = LoggerFactory.getLogger(DecisionesController.class);
    
    @Autowired
    private IDecisionService service;
            
    @GetMapping("/decisiones")
    public List<DecisionEntity> getAllDecisiones()
    {   
        return this.service.findAll();
    }
    
    @GetMapping("/decisiones/{id}")
    public ResponseEntity<?> showDecisiones(@PathVariable Long id) {
        DecisionEntity decisiones = null;
        Map<String, Object> response = new HashMap<>();

        try {
                decisiones = service.findById(id); 
                
                if(decisiones == null) {
                    response.put("mensaje", "La decision ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
                    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
                } 
                
                return new ResponseEntity<DecisionEntity>(decisiones, HttpStatus.OK);
                
        } catch(DataAccessException e) {
                response.put("mensaje", "Error al realizar la consulta en la base de datos");
                response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }  
    } 
    
    @PostMapping("/decisiones")
    public ResponseEntity<?> createDecisiones(@Valid @RequestBody DecisionEntity decision, BindingResult result) {

            DecisionEntity decisionNew = null;
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
                
                    decisionNew = this.service.save(decision);
                    
            } catch(DataAccessException e) {
                    response.put("mensaje", "Error al realizar el insert en la base de datos");
                    response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
                    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            response.put("mensaje", "La decision ha sido creado con éxito!");
            response.put("cliente", decisionNew);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }   
    
    @PutMapping("/decisiones/{id}")
    public ResponseEntity<?> updateCliente(@Valid @RequestBody DecisionEntity decision, BindingResult result, @PathVariable Long id) {

            DecisionEntity decisionActual = this.service.findById(id);
            DecisionEntity decisionUpdated = null;
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

                    if (decisionActual == null) {
                        response.put("mensaje", "Error: no se pudo editar, el cliente ID: "
                                        .concat(id.toString().concat(" no existe en la base de datos!")));
                        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
                    }
                    
                    decisionActual.setHumedad(decision.getHumedad());
                    decisionActual.setTemperatura(decision.getTemperatura());
                    decisionActual.setTemporada(decision.getTemporada());
                    decisionActual.setPronostico(decision.getPronostico());
                    decisionActual.setFechaCreacion(decision.getFechaCreacion());
                    decisionUpdated = this.service.save(decisionActual);
                    
                    response.put("mensaje", "La decision ha sido actualizado con éxito!");
                    response.put("cliente", decisionUpdated);

                    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

            } catch (DataAccessException e) {
                    response.put("mensaje", "Error al actualizar la decision en la base de datos");
                    response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
                    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }
    
    @DeleteMapping("/decisiones/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

            Map<String, Object> response = new HashMap<>();

            try {
                    DecisionEntity desicion = this.service.findById(id);
                    this.service.delete(desicion);                    
                    response.put("mensaje", "La decision eliminado con éxito!");
                    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
                    
            } catch (DataAccessException e) {
                    response.put("mensaje", "Error al eliminar decision de la base de datos");
                    response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
                    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }   
    
    @PostMapping("/decisiones/{humedad}/{temperatura}")
    public String createDecisionesPorParametros(@PathVariable Double humedad, @PathVariable Double temperatura) {
            String temporada = this.service.obtenerTemporada();
            return temporada;
    }  

}
