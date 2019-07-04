/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.floresycactus.services;

import cl.duoc.floresycactus.entities.UsuarioEntity;
import cl.duoc.floresycactus.repositories.IUsuarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jpino
 */
@Service
public class UsuarioServiceImpl implements IUsuarioService{
 
    @Autowired
    private IUsuarioRepository repository;

    @Override
    public List<UsuarioEntity> findAll() {
        return (List<UsuarioEntity>) repository.findAll();
    }

    @Override
    public void save(UsuarioEntity cliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UsuarioEntity findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void delete(UsuarioEntity cliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UsuarioEntity> findUsuarioByCorreoAndPass(String correo, String password) {
        return (List<UsuarioEntity>) this.repository.findUsuarioByCorreoAndPass(correo, password);
    }
}
