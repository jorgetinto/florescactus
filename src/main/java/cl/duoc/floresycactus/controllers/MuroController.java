/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.floresycactus.controllers;


import cl.duoc.floresycactus.entities.MuroEntity;
import cl.duoc.floresycactus.services.IMuroService;
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
public class MuroController {
     private static final Logger logger = LoggerFactory.getLogger(MuroController.class);  
     
    @Autowired
    private IMuroService service;
     
    @GetMapping("/muros")
    public List<MuroEntity> getAllMuros()
    {   
        return  this.service.findAll();
    }
    
    @GetMapping("/muros/{id}")
    public ResponseEntity<?> showMuros(@PathVariable Long id) {
        MuroEntity muros = null;
        Map<String, Object> response = new HashMap<>();

        try {
                muros = service.findById(id); 
                
                if(muros == null) {
                    response.put("mensaje", "El muro ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
                    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
                } 
                
                return new ResponseEntity<MuroEntity>(muros, HttpStatus.OK);
                
        } catch(DataAccessException e) {
                response.put("mensaje", "Error al realizar la consulta en la base de datos");
                response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }  
    }       
    
    @PostMapping("/muros")
    public ResponseEntity<?> createMuros(@Valid @RequestBody MuroEntity muro, BindingResult result) {

            MuroEntity muroNew = null;
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
                
                    muroNew = this.service.save(muro);
                    
            } catch(DataAccessException e) {
                    response.put("mensaje", "Error al realizar el insert en la base de datos");
                    response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
                    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            response.put("mensaje", "El muro ha sido creado con éxito!");
            response.put("muro", muroNew);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }       
    
    @PutMapping("/muros/{id}")
    public ResponseEntity<?> updateMuros(@Valid @RequestBody MuroEntity muro, BindingResult result, @PathVariable Long id) {

            MuroEntity muroActual = this.service.findById(id);
            MuroEntity muroUpdated = null;
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

                    if (muroActual == null) {
                        response.put("mensaje", "Error: no se pudo editar, el muro ID: "
                                        .concat(id.toString().concat(" no existe en la base de datos!")));
                        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
                    }
                    
                    muroActual.setNombre(muro.getNombre());
                    muroActual.setDescripcion(muro.getDescripcion());
                    muroActual.setCiudad(muro.getCiudad());
                    muroActual.setTipoIntalacion(muro.getTipoIntalacion());
                    muroActual.setEstado(muro.getEstado());
                    muroUpdated = this.service.save(muroActual);
                    
                    response.put("mensaje", "El muro ha sido actualizado con éxito!");
                    response.put("muro", muroUpdated);

                    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

            } catch (DataAccessException e) {
                    response.put("mensaje", "Error al actualizar el muro en la base de datos");
                    response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
                    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }
    
    @DeleteMapping("/muros/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

            Map<String, Object> response = new HashMap<>();

            try {
                    MuroEntity muro = this.service.findById(id);
                    this.service.delete(muro);                    
                    response.put("mensaje", "El muro eliminado con éxito!");
                    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
                    
            } catch (DataAccessException e) {
                    response.put("mensaje", "Error al eliminar el muro de la base de datos");
                    response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
                    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }
    
    @PutMapping("/muros/{id}/{estado}")
    public ResponseEntity<?> CambiarEstadoMuroPorId(@PathVariable Long id, @PathVariable Boolean estado) {

            Map<String, Object> response = new HashMap<>();

            try {
                    this.service.CambiarEstadoMuroPorId(estado, id);
                    response.put("mensaje", "Muro actualizado de foma exitosa!");
                    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
                    
            } catch (DataAccessException e) {
                    response.put("mensaje", "Error al actualizar el muro de la base de datos");
                    response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
                    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }
}
