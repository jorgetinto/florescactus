/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.floresycactus.repositories;

import cl.duoc.floresycactus.entities.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jpino
 */
@Repository
public interface IClienteRepository extends JpaRepository<ClienteEntity, Long>{
    
}
