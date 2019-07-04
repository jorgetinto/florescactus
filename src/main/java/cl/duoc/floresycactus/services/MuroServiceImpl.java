/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.floresycactus.services;

import cl.duoc.floresycactus.entities.MuroEntity;
import cl.duoc.floresycactus.repositories.IMuroRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jpino
 */
@Service
public class MuroServiceImpl implements IMuroService{

    @Autowired
    private IMuroRepository repository;
        
    @Override
    public List<MuroEntity> findAll() {
         return (List<MuroEntity>) repository.findAll();
    }

    @Override
    public MuroEntity save(MuroEntity muro) {
        return repository.save(muro);
    }

    @Override
    public MuroEntity findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void delete(MuroEntity muro) {
        repository.delete(muro);
    }

    @Override
    public void CambiarEstadoMuroPorId(Boolean estado, Long id) {
       this.repository.CambiarEstadoMuroPorId(estado, id);
    }    
}
