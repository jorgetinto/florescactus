/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.floresycactus.services;

import cl.duoc.floresycactus.entities.UsuarioEntity;
import java.util.List;

/**
 *
 * @author jpino
 */
public interface IUsuarioService {
    
    List<UsuarioEntity> findAll();

    void save(UsuarioEntity cliente);

    UsuarioEntity findById(Long id);

    void delete(UsuarioEntity cliente); 
    
    List<UsuarioEntity> findUsuarioByCorreoAndPass(String correo, String password);
}
