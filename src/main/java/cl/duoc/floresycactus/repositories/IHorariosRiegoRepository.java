/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.floresycactus.repositories;

import cl.duoc.floresycactus.entities.HorariosRiegoEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author jpino
 */
public interface IHorariosRiegoRepository extends JpaRepository<HorariosRiegoEntity, Long> {
    
    @Query( value = "SELECT * FROM horarios_riego WHERE muro_id = ?1", nativeQuery = true)
    List<HorariosRiegoEntity> findHorariosPorMuro(Long Id);
}
