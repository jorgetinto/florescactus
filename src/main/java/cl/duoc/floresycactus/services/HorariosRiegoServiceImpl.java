/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.floresycactus.services;

import cl.duoc.floresycactus.entities.HorariosRiegoEntity;
import cl.duoc.floresycactus.repositories.IHorariosRiegoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jpino
 */
@Service
public class HorariosRiegoServiceImpl implements IHorariosRiegoService{
    
    @Autowired
    private IHorariosRiegoRepository repository;

    @Override
    public List<HorariosRiegoEntity> findAll() {
        return (List<HorariosRiegoEntity>) this.repository.findAll();
    }

    @Override
    public HorariosRiegoEntity save(HorariosRiegoEntity horarios) {
        return this.repository.save(horarios);
    }

    @Override
    public HorariosRiegoEntity findById(Long id) {
        return this.repository.findById(id).orElse(null);
    }

    @Override
    public void delete(HorariosRiegoEntity horarios) {
        repository.delete(horarios);
    }  

    @Override
    public List<HorariosRiegoEntity> findHorariosPorMuro(Long Id) {
        return (List<HorariosRiegoEntity>) this.repository.findHorariosPorMuro(Id);
    }
}
