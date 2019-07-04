/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.floresycactus.services;

import cl.duoc.floresycactus.entities.MuroEntity;
import java.util.List;

/**
 *
 * @author jpino
 */
public interface IMuroService {
    
    List<MuroEntity> findAll();

    MuroEntity save(MuroEntity muro);

    MuroEntity findById(Long id);

    void delete(MuroEntity muro);   
    
    void CambiarEstadoMuroPorId(Boolean estado,Long id);
}
