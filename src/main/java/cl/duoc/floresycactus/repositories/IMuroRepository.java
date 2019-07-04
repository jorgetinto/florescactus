/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.floresycactus.repositories;

import cl.duoc.floresycactus.entities.MuroEntity;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jpino
 */
@Repository
public interface IMuroRepository extends JpaRepository<MuroEntity, Long> {
    
    @Transactional
    @Modifying    
    @Query( value = "UPDATE muro SET estado = ?1 WHERE id = ?2", nativeQuery = true)
    void CambiarEstadoMuroPorId(Boolean estado, Long id);
}
