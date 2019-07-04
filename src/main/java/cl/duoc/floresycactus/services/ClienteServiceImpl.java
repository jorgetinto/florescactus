/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.floresycactus.services;

import cl.duoc.floresycactus.entities.ClienteEntity;
import cl.duoc.floresycactus.repositories.IClienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jpino
 */
@Service
public class ClienteServiceImpl implements IClienteService{

    @Autowired
    private IClienteRepository repository;
    
    @Override
    public List<ClienteEntity> findAll() {
        return (List<ClienteEntity>) repository.findAll();
    }
    
    @Override
    public ClienteEntity save(ClienteEntity cliente) {
        return repository.save(cliente);
    }

    @Override
    public ClienteEntity findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void delete(ClienteEntity cliente) {
        repository.delete(cliente);
    }    
}
