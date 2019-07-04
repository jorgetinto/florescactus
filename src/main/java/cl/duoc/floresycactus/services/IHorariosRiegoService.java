/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.floresycactus.services;

import cl.duoc.floresycactus.entities.HorariosRiegoEntity;
import java.util.List;

/**
 *
 * @author jpino
 */
public interface IHorariosRiegoService {
    
    List<HorariosRiegoEntity> findAll();

    HorariosRiegoEntity save(HorariosRiegoEntity horarios);

    HorariosRiegoEntity findById(Long id);

    void delete(HorariosRiegoEntity horarios); 
    
    List<HorariosRiegoEntity> findHorariosPorMuro(Long Id);
}
