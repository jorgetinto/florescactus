/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.floresycactus.controllers;


import cl.duoc.floresycactus.entities.ClienteEntity;
import cl.duoc.floresycactus.entities.HorariosRiegoEntity;
import cl.duoc.floresycactus.services.IHorariosRiegoService;
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
public class HorariosRiegoController {
    
    private static final Logger logger = LoggerFactory.getLogger(HorariosRiegoController.class);
    
    @Autowired
    private IHorariosRiegoService service;
    
    @GetMapping("/horarios")
    public List<HorariosRiegoEntity> getAllHorarios()
    {   
        return  this.service.findAll();
    }
    
    @GetMapping("/horarios02/{id}")
    public ResponseEntity<?> showHorariosXMuro(@PathVariable Long id) {
        List<HorariosRiegoEntity> horarios = null;
        Map<String, Object> response = new HashMap<>();

        try {
                horarios = service.findHorariosPorMuro(id); 
                
                if(horarios == null) {
                    response.put("mensaje", "El Horario ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
                    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
                } 
                
                return new ResponseEntity<List<HorariosRiegoEntity>>(horarios, HttpStatus.OK);
                
        } catch(DataAccessException e) {
                response.put("mensaje", "Error al realizar la consulta en la base de datos");
                response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }  
    } 
    
    @GetMapping("/horarios/{id}")
    public ResponseEntity<?> showHorarios(@PathVariable Long id) {
        HorariosRiegoEntity horarios = null;
        Map<String, Object> response = new HashMap<>();

        try {
                horarios = service.findById(id); 
                
                if(horarios == null) {
                    response.put("mensaje", "El Horario ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
                    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
                } 
                
                return new ResponseEntity<HorariosRiegoEntity>(horarios, HttpStatus.OK);
                
        } catch(DataAccessException e) {
                response.put("mensaje", "Error al realizar la consulta en la base de datos");
                response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }  
    } 
    
    @PostMapping("/horarios")
    public ResponseEntity<?> createHorarios(@Valid @RequestBody HorariosRiegoEntity horario, BindingResult result) {

            HorariosRiegoEntity horarioNew = null;
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
                
                    horarioNew = this.service.save(horario);
                    
            } catch(DataAccessException e) {
                    response.put("mensaje", "Error al realizar el insert en la base de datos");
                    response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
                    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            response.put("mensaje", "El horario de riego ha sido creado con éxito!");
            response.put("cliente", horarioNew);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }   
    
    @PutMapping("/horarios/{id}")
    public ResponseEntity<?> updateHorarios(@Valid @RequestBody HorariosRiegoEntity horario, BindingResult result, @PathVariable Long id) {

            HorariosRiegoEntity horarioActual = this.service.findById(id);
            HorariosRiegoEntity horarioUpdated = null;
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

                    if (horarioActual == null) {
                        response.put("mensaje", "Error: no se pudo editar, el horario de riego ID: "
                                        .concat(id.toString().concat(" no existe en la base de datos!")));
                        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
                    }
                    
                    horarioActual.setHorario(horario.getHorario());
                    horarioActual.setEstado(horario.getEstado());
                    horarioActual.setMuroId(horario.getMuroId());
                    horarioUpdated = this.service.save(horarioActual);
                    
                    response.put("mensaje", "El horario de riego ha sido actualizado con éxito!");
                    response.put("cliente", horarioUpdated);

                    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

            } catch (DataAccessException e) {
                    response.put("mensaje", "Error al actualizar el horario en la base de datos");
                    response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
                    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }
    
    @DeleteMapping("/horarios/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

            Map<String, Object> response = new HashMap<>();

            try {
                    HorariosRiegoEntity horario = this.service.findById(id);
                    this.service.delete(horario);                    
                    response.put("mensaje", "El horario de riego  eliminado con éxito!");
                    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
                    
            } catch (DataAccessException e) {
                    response.put("mensaje", "Error al eliminar el horario de riego de la base de datos");
                    response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
                    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }
}
