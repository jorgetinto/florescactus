/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.floresycactus.repositories;

import cl.duoc.floresycactus.entities.DecisionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jpino
 */
public interface IDecisionRepository extends JpaRepository<DecisionEntity, Long> {
    
}
