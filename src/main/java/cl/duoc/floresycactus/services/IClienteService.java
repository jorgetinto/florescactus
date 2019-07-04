/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.floresycactus.services;

import cl.duoc.floresycactus.entities.ClienteEntity;
import java.util.List;

/**
 *
 * @author jpino
 */
public interface IClienteService {
      
    List<ClienteEntity> findAll();

    ClienteEntity save(ClienteEntity cliente);

    ClienteEntity findById(Long id);

    void delete(ClienteEntity cliente);   
}
