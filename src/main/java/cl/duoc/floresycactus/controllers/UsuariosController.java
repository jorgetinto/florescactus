/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.floresycactus.controllers;

import cl.duoc.floresycactus.entities.UsuarioEntity;
import cl.duoc.floresycactus.services.IUsuarioService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jpino
 */
@RestController
@RequestMapping("/api")
public class UsuariosController {
    
     private static final Logger logger = LoggerFactory.getLogger(UsuariosController.class);
     
    @Autowired
    private IUsuarioService service;
    
    @GetMapping(value = "/usuarios")
    public List<UsuarioEntity> getAllUsuarios ()
    {   
        List<UsuarioEntity> usuarios = service.findAll();
        return  usuarios;
    }
    
    @GetMapping("/usuarios/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        UsuarioEntity usuario = null;
        Map<String, Object> response = new HashMap<>();

        try {
                usuario = service.findById(id); 
                
                if(usuario == null) {
                    response.put("mensaje", "El usuario ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
                    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
                } 
                
                return new ResponseEntity<UsuarioEntity>(usuario, HttpStatus.OK);
                
        } catch(DataAccessException e) {
                response.put("mensaje", "Error al realizar la consulta en la base de datos");
                response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }  
    }  
    
    @PostMapping("/usuarios/login/{correo}/{password}")
    public ResponseEntity<?> showLogin(@PathVariable String correo, @PathVariable String password) {
        List<UsuarioEntity> usuario = null;
        Map<String, Object> response = new HashMap<>();

        try {
                usuario = service.findUsuarioByCorreoAndPass(correo, password);
                
                if(usuario == null) {
                    response.put("mensaje", "Usuario o contraseña no validos");
                    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
                } 
                
                return new ResponseEntity<List<UsuarioEntity>>(usuario, HttpStatus.OK);
                
        } catch(DataAccessException e) {
                response.put("mensaje", "Error al realizar la consulta en la base de datos");
                response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }  
    }  
}
